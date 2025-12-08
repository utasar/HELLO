; Sample Code for Lab 6
; This sample code implements the int main() function

.ORIG x3000
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; int main()
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

; Main's return value
LD R6, STACK_BASE        ; R6 = x6000

; Main's return address
ADD R6, R6, #-1          ; R6 = x5FFF
STR R7, R6, #0

; Previous frame pointer
ADD R6, R6, #-1          ; R6 = x5FFE
STR R5, R6, #0

; Set frame pointer
ADD R5, R6, #0           ; R5 = R6 = x5FFE

; node_t *head
ADD R6, R6, #-1          ; R6 = x5FFD
AND R0, R0, #0           ; R0 = 0
STR R0, R6, #0           ; head = NULL at [R5, #-1]

; char selection
ADD R6, R6, #-1          ; R6 = x5FFC
; selection is at [R5, #-2]

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; while(selection != 'q')
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

CHECK_WHILE
LDR R0, R5, #-2          ; R0 = char selection
LD  R1, LOWERCASE_Q
NOT R1, R1
ADD R1, R1, #1
ADD R1, R0, R1
BRz BREAK_WHILE_LOOP     ; if selection == 'q' break

; Print the options menu
LEA R0, PROMPT_MENU
PUTS

; Get a character and store in selection
GETC
STR R0, R5, #-2          ; selection = R0

;;;;;;;;;;;;;;;;;;;;;;;;;
; if(selection == 'p')
;;;;;;;;;;;;;;;;;;;;;;;;;
LD  R1, LOWERCASE_P
NOT R1, R1
ADD R1, R1, #1
ADD R1, R0, R1
BRnp ELSE_IF_A           ; If not equal to 'p' check 'a'

; Display prompt
LEA R0, PROMPT_PRINT
PUTS

; node_t **head (&head) (input to printList())
ADD R6, R6, #-1          ; push &head
ADD R0, R5, #-1          ; R0 = &head = x5FFD
STR R0, R6, #0

; push return slot (unused)
ADD R6, R6, #-1
JSR PRINT_LIST

; Pop return slot
ADD R6, R6, #1

; Pop node_t **head
ADD R6, R6, #1

BRnzp CONTINUE_WHILE_LOOP

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; else if(selection == 'a')
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

ELSE_IF_A
LD  R1, LOWERCASE_A
NOT R1, R1
ADD R1, R1, #1
ADD R1, R0, R1
BRnp ELSE_IF_R

; int a
ADD R6, R6, #-1          ; allocate local a
AND R0, R0, #0
STR R0, R6, #0           ; a at [R5, #-3]

; Display prompt
LEA R0, PROMPT_ADD
PUTS

; get number via TRAP x40
TRAP x40                 ; result in R0

; Store number entered into int a
STR R0, R5, #-3

; node_t **head (&head) (input to addValue())
ADD R6, R6, #-1
ADD R0, R5, #-1          ; R0 = &head
STR R0, R6, #0

; int added (a) (input to addValue())
ADD R6, R6, #-1
LDR R0, R5, #-3          ; R0 = a
STR R0, R6, #0

; push return slot
ADD R6, R6, #-1
JSR ADD_VALUE

; Pop return slot
ADD R6, R6, #1

; Pop int added
ADD R6, R6, #1

; Pop node_t **head
ADD R6, R6, #1

; Pop int a
ADD R6, R6, #1

BRnzp CONTINUE_WHILE_LOOP

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; else if(selection == 'r')
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

ELSE_IF_R
LD  R1, LOWERCASE_R
NOT R1, R1
ADD R1, R1, #1
ADD R1, R0, R1
BRnp CONTINUE_WHILE_LOOP

; int r
ADD R6, R6, #-1          ; allocate local r
AND R0, R0, #0
STR R0, R6, #0           ; r at [R5, #-3]

; Display prompt
LEA R0, PROMPT_REMOVE
PUTS

; get number via TRAP x40
TRAP x40                 ; result in R0

; Store number entered into int r
STR R0, R5, #-3

; node_t **head (&head) (input to removeValue())
ADD R6, R6, #-1
ADD R0, R5, #-1          ; R0 = &head
STR R0, R6, #0

; int removed (r) (input to removeValue())
ADD R6, R6, #-1
LDR R0, R5, #-3
STR R0, R6, #0

; push return slot
ADD R6, R6, #-1
JSR REMOVE_VALUE

; Pop return slot
ADD R6, R6, #1

; Pop int removed
ADD R6, R6, #1

; Pop node_t **head
ADD R6, R6, #1

; Pop int r
ADD R6, R6, #1

BRnzp CONTINUE_WHILE_LOOP

;-----------------------------
; loop back to while condition
;-----------------------------
CONTINUE_WHILE_LOOP
BRnzp CHECK_WHILE

;-----------------------------
; exit while and clean up main
;-----------------------------
BREAK_WHILE_LOOP
; Pop local variables in main: head + selection
ADD R6, R6, #2           ; R6 = x5FFE

; Pop previous frame pointer
LDR R5, R6, #0
ADD R6, R6, #1           ; R6 = x5FFF

; Pop return address
LDR R7, R6, #0
ADD R6, R6, #1           ; R6 = x6000

HALT

STACK_BASE .FILL x6000

LOWERCASE_A .FILL x0061
LOWERCASE_P .FILL x0070
LOWERCASE_Q .FILL x0071
LOWERCASE_R .FILL x0072
LOWERCASE_S .FILL x0073

PROMPT_MENU   .STRINGz "Available options:\np - Print linked list\na - Add value to linked list\nr - Remove value from linked list\nq - Quit\nChoose an option: "
PROMPT_PRINT  .STRINGz "\nContents of the linked list: \n"
PROMPT_ADD    .STRINGz "\nType a number to add: \n"
PROMPT_REMOVE .STRINGz "\nType a number to remove: \n"

;====================
; simple heap for nodes
; Each node_t = 2 words: [0]=value, [1]=next
;====================
ALLOC_PTR      .FILL x8000      ; next free address
PRINT_ARROW    .STRINGZ " -> "
PRINT_NEWLINE  .STRINGZ "\n"

;====================
; void printList(node_t **head)
; Stack at entry:
; [R6,#0] = return slot (ignored)
; [R6,#1] = &head
;====================
PRINT_LIST
    LDR R2, R6, #1      ; R2 = &head
    LDR R3, R2, #0      ; R3 = *head (current node)

    ADD R0, R3, #0
    BRz PL_DONE         ; empty list

PL_LOOP
    LDR R0, R3, #0      ; R0 = current->value
    TRAP x41            ; print integer via outputs.asm

    LDR R4, R3, #1      ; R4 = current->next
    ADD R0, R4, #0
    BRz PL_NEWLINE      ; no next -> newline and finish

    LEA R0, PRINT_ARROW
    PUTS

    ADD R3, R4, #0      ; current = next
    BRnzp PL_LOOP

PL_NEWLINE
    LEA R0, PRINT_NEWLINE
    PUTS
PL_DONE
    RET

;====================
; void addValue(node_t **head, int added)
; Stack at entry:
; [R6,#0] = return slot (ignored)
; [R6,#1] = added
; [R6,#2] = &head
;====================
ADD_VALUE
    LDR R1, R6, #1      ; R1 = added
    LDR R2, R6, #2      ; R2 = &head
    LDR R3, R2, #0      ; R3 = *head (head pointer)

    ; allocate new node from ALLOC_PTR
    LD  R4, ALLOC_PTR   ; R4 = newNode
    STR R1, R4, #0      ; newNode->value = added
    AND R5, R5, #0
    STR R5, R4, #1      ; newNode->next = NULL

    ; update ALLOC_PTR += 2 words
    ADD R5, R4, #2
    ST  R5, ALLOC_PTR

    ; if list empty: *head = newNode; return;
    ADD R0, R3, #0
    BRnp ADD_TRAVERSE   ; if head != NULL, go traverse

    STR R4, R2, #0      ; *head = newNode
    RET

ADD_TRAVERSE
ADDV_LOOP
    LDR R5, R3, #1      ; R5 = current->next
    ADD R0, R5, #0
    BRz ADDV_INSERT     ; if next == NULL, insert here
    ADD R3, R5, #0      ; current = current->next
    BRnzp ADDV_LOOP

ADDV_INSERT
    STR R4, R3, #1      ; current->next = newNode
    RET

;====================
; void removeValue(node_t **head, int removed)
; Stack at entry:
; [R6,#0] = return slot (ignored)
; [R6,#1] = removed
; [R6,#2] = &head
;====================
REMOVE_VALUE
    LDR R1, R6, #1      ; R1 = removed
    LDR R2, R6, #2      ; R2 = &head
    LDR R3, R2, #0      ; R3 = current = *head
    AND R4, R4, #0      ; R4 = prev = NULL

REM_LOOP
    ADD R0, R3, #0
    BRz REM_DONE        ; reached end, not found

    LDR R0, R3, #0      ; current->value
    NOT R5, R1
    ADD R5, R5, #1      ; R5 = -removed
    ADD R5, R0, R5      ; R5 = value - removed
    BRz REM_FOUND

    ADD R4, R3, #0      ; prev = current
    LDR R3, R3, #1      ; current = current->next
    BRnzp REM_LOOP

REM_FOUND
    LDR R5, R3, #1      ; R5 = current->next

    ADD R0, R4, #0
    BRnp REM_NOT_HEAD   ; if prev != NULL, normal unlink

    STR R5, R2, #0      ; *head = next
    BRnzp REM_DONE

REM_NOT_HEAD
    STR R5, R4, #1      ; prev->next = next

REM_DONE
    RET

.END
