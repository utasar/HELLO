;===============================
; main1.asm  (Lab 6)
;===============================
        .ORIG x3000

;--------------------------------
; int main()
;--------------------------------

; Main's return value (ignored, but stack base)
LD   R6, STACK_BASE        ; R6 = x6000

; Push main's return address
ADD  R6, R6, #-1           ; R6 = x5FFF
STR  R7, R6, #0

; Push previous frame pointer
ADD  R6, R6, #-1           ; R6 = x5FFE
STR  R5, R6, #0

; Set frame pointer
ADD  R5, R6, #0            ; R5 = x5FFE

; node_t *head (local)
ADD  R6, R6, #-1           ; R6 = x5FFD
AND  R0, R0, #0
STR  R0, R6, #0            ; head = NULL at [R5, #-1]

; char selection (local)
ADD  R6, R6, #-1           ; R6 = x5FFC
; selection is at [R5, #-2]

;--------------------------------
; while (selection != 'q')
;--------------------------------
CHECK_WHILE
    ; load selection
    LDR  R0, R5, #-2       ; R0 = selection
    LD   R1, LOWERCASE_Q
    NOT  R1, R1
    ADD  R1, R1, #1        ; R1 = -'q'
    ADD  R1, R0, R1        ; R1 = selection - 'q'
    BRz  BREAK_WHILE_LOOP  ; if selection == 'q', exit loop

    ; print the menu
    LEA  R0, PROMPT_MENU
    PUTS

    ; read new selection character
    GETC
    STR  R0, R5, #-2       ; selection = R0

    ;--------------------------------
    ; if (selection == 'p')
    ;--------------------------------
    LD   R1, LOWERCASE_P
    NOT  R1, R1
    ADD  R1, R1, #1        ; -'p'
    ADD  R1, R0, R1        ; selection - 'p'
    BRnp ELSE_IF_A         ; if not 'p', go check 'a'

    ; --- selection == 'p' ---
    LEA  R0, PROMPT_PRINT
    PUTS

    ; push node_t **head (&head) as argument
    ADD  R6, R6, #-1
    ADD  R0, R5, #-1       ; R0 = &head
    STR  R0, R6, #0

    ; push return slot (unused)
    ADD  R6, R6, #-1
    JSR  PRINT_LIST

    ; pop return slot
    ADD  R6, R6, #1

    ; pop &head
    ADD  R6, R6, #1

    BRnzp CONTINUE_WHILE_LOOP


;--------------------------------
; else if (selection == 'a')
;--------------------------------
ELSE_IF_A
    LD   R1, LOWERCASE_A
    NOT  R1, R1
    ADD  R1, R1, #1        ; -'a'
    ADD  R1, R0, R1        ; selection - 'a'
    BRnp ELSE_IF_R         ; if not 'a', check 'r'

    ; --- selection == 'a' ---
    ; allocate local int a (not really used, but matches C)
    ADD  R6, R6, #-1
    AND  R0, R0, #0
    STR  R0, R6, #0        ; int a at [R5, #-3]

    LEA  R0, PROMPT_ADD
    PUTS

    ; call TRAP x40 to read integer into R0
    TRAP x40               ; result in R0

    ; store into local a
    STR  R0, R5, #-3

    ; push node_t **head (&head)
    ADD  R6, R6, #-1
    ADD  R0, R5, #-1       ; &head
    STR  R0, R6, #0

    ; push int added (a)
    ADD  R6, R6, #-1
    LDR  R0, R5, #-3
    STR  R0, R6, #0

    ; push return slot
    ADD  R6, R6, #-1
    JSR  ADD_VALUE

    ; pop return slot
    ADD  R6, R6, #1

    ; pop int added
    ADD  R6, R6, #1

    ; pop &head
    ADD  R6, R6, #1

    ; pop int a
    ADD  R6, R6, #1

    BRnzp CONTINUE_WHILE_LOOP


;--------------------------------
; else if (selection == 'r')
;--------------------------------
ELSE_IF_R
    LD   R1, LOWERCASE_R
    NOT  R1, R1
    ADD  R1, R1, #1        ; -'r'
    ADD  R1, R0, R1        ; selection - 'r'
    BRnp CONTINUE_WHILE_LOOP

    ; --- selection == 'r' ---
    ; allocate local int r
    ADD  R6, R6, #-1
    AND  R0, R0, #0
    STR  R0, R6, #0        ; int r at [R5, #-3]

    LEA  R0, PROMPT_REMOVE
    PUTS

    ; read integer to remove via TRAP x40
    TRAP x40
    STR  R0, R5, #-3

    ; push node_t **head (&head)
    ADD  R6, R6, #-1
    ADD  R0, R5, #-1
    STR  R0, R6, #0

    ; push int removed (r)
    ADD  R6, R6, #-1
    LDR  R0, R5, #-3
    STR  R0, R6, #0

    ; push return slot
    ADD  R6, R6, #-1
    JSR  REMOVE_VALUE

    ; pop return slot
    ADD  R6, R6, #1

    ; pop int removed
    ADD  R6, R6, #1

    ; pop &head
    ADD  R6, R6, #1

    ; pop int r
    ADD  R6, R6, #1

    BRnzp CONTINUE_WHILE_LOOP


;--------------------------------
; loop back
;--------------------------------
CONTINUE_WHILE_LOOP
    BRnzp CHECK_WHILE


;--------------------------------
; after while loop
;--------------------------------
BREAK_WHILE_LOOP
    ; pop locals: head + selection
    ADD  R6, R6, #2        ; R6 = x5FFE

    ; restore old frame pointer
    LDR  R5, R6, #0
    ADD  R6, R6, #1        ; R6 = x5FFF

    ; restore return address
    LDR  R7, R6, #0
    ADD  R6, R6, #1        ; R6 = x6000

    HALT


;--------------------------------
; Data, constants, and helper routines
;--------------------------------
STACK_BASE   .FILL x6000

LOWERCASE_A  .FILL x0061
LOWERCASE_P  .FILL x0070
LOWERCASE_Q  .FILL x0071
LOWERCASE_R  .FILL x0072

PROMPT_MENU  .STRINGZ "Available options:\np - Print linked list\na - Add value to linked list\nr - Remove value from linked list\nq - Quit\nChoose an option: "
PROMPT_PRINT .STRINGZ "\nContents of the linked list:\n"
PROMPT_ADD   .STRINGZ "\nType a number to add:\n"
PROMPT_REMOVE.STRINGZ "\nType a number to remove:\n"

;------------------------
; Simple heap for nodes
; Each node_t is 2 words:
;   [0] = value (int)
;   [1] = next pointer
;------------------------
ALLOC_PTR     .FILL x8000      ; next free address for nodes
PRINT_ARROW   .STRINGZ " -> "
PRINT_NEWLINE .STRINGZ "\n"

;========================
; void printList(node_t **head)
; Stack at entry:
; [R6,#0] = return slot (ignored)
; [R6,#1] = &head
;========================
PRINT_LIST
    ; get &head and *head
    LDR  R2, R6, #1      ; R2 = &head
    LDR  R3, R2, #0      ; R3 = *head (current node)

    ADD  R0, R3, #0
    BRz  PL_DONE         ; empty list -> nothing printed

PL_LOOP
    ; print current->value
    LDR  R0, R3, #0      ; R0 = current->value
    TRAP x41             ; print integer via DISPLAY

    ; get next = current->next
    LDR  R4, R3, #1
    ADD  R0, R4, #0
    BRz  PL_NEWLINE      ; no next -> print newline and finish

    ; print " -> "
    LEA  R0, PRINT_ARROW
    PUTS

    ; move to next
    ADD  R3, R4, #0
    BRnzp PL_LOOP

PL_NEWLINE
    LEA  R0, PRINT_NEWLINE
    PUTS
PL_DONE
    RET


;========================
; void addValue(node_t **head, int added)
; Stack at entry:
; [R6,#0] = return slot (ignored)
; [R6,#1] = added
; [R6,#2] = &head
;========================
ADD_VALUE
    ; load args
    LDR  R1, R6, #1      ; R1 = added
    LDR  R2, R6, #2      ; R2 = &head
    LDR  R3, R2, #0      ; R3 = *head (head pointer)

    ; allocate new node from ALLOC_PTR
    LD   R4, ALLOC_PTR   ; R4 = newNode
    STR  R1, R4, #0      ; newNode->value = added
    AND  R5, R5, #0
    STR  R5, R4, #1      ; newNode->next = NULL

    ; update ALLOC_PTR += 2
    ADD  R5, R4, #2
    ST   R5, ALLOC_PTR

    ; if list empty: *head = newNode; return;
    ADD  R0, R3, #0
    BRnp ADD_TRAVERSE    ; if head != NULL, go traverse

    STR  R4, R2, #0      ; *head = newNode
    RET

ADD_TRAVERSE
    ; R3 = current = head
ADDV_LOOP
    LDR  R5, R3, #1      ; R5 = current->next
    ADD  R0, R5, #0
    BRz  ADDV_INSERT     ; if next == NULL, insert here
    ADD  R3, R5, #0      ; current = current->next
    BRnzp ADDV_LOOP

ADDV_INSERT
    STR  R4, R3, #1      ; current->next = newNode
    RET


;========================
; void removeValue(node_t **head, int removed)
; Stack at entry:
; [R6,#0] = return slot (ignored)
; [R6,#1] = removed
; [R6,#2] = &head
;========================
REMOVE_VALUE
    ; load args
    LDR  R1, R6, #1      ; R1 = removed
    LDR  R2, R6, #2      ; R2 = &head
    LDR  R3, R2, #0      ; R3 = current = *head
    AND  R4, R4, #0      ; R4 = prev = NULL

REM_LOOP
    ADD  R0, R3, #0
    BRz  REM_DONE        ; reached end, not found

    ; if (current->value == removed)
    LDR  R0, R3, #0      ; current->value
    NOT  R5, R1
    ADD  R5, R5, #1      ; R5 = -removed
    ADD  R5, R0, R5      ; R5 = value - removed
    BRz  REM_FOUND

    ; prev = current; current = current->next;
    ADD  R4, R3, #0
    LDR  R3, R3, #1      ; current = current->next
    BRnzp REM_LOOP

REM_FOUND
    ; removing head?
    ADD  R0, R4, #0
    BRnp REM_LINK        ; if prev != NULL, normal unlink

    ; removing first node: *head = current->next
    LDR  R0, R3, #1      ; current->next
    STR  R0, R2, #0      ; *head = next
    BRnzp REM_DONE

REM_LINK
    ; prev->next = current->next
    LDR  R0, R3, #1
    STR  R0, R4, #1
    BRnzp REM_DONE

REM_DONE
    RET

        .END
