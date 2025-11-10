.ORIG x3000

; #~#~#~#~#~#~#~#~#
; =-=-=-=-=-=-=-=-=
; INITIALIZER CODE
; # DO NOT TOUCH #
; - - - - - - - - -
LD   R6, STACK_PTR              ; load stack pointer
LEA  R4, STATIC_STORAGE         ; load global vars pointer (strings live here)
ADD  R5, R6, #0                 ; set frame pointer
; current stack pointer is sitting on main's return slot
; there are no arguments to our main function
JSR  MAIN
HALT
; SETUP VARS
STACK_PTR .FILL x6000
STATIC_STORAGE
; -------- GLOBAL/STRINGS --------
PROMPT_STR .STRINGZ "Please enter a number n: "
F_OPEN     .STRINGZ "F("
CLOSE_EQ   .STRINGZ ") = "
NEWLINE    .FILL x000A
CR         .FILL x000D
LF         .FILL x000A
ASCII_0    .FILL #48
TEN        .FILL #10
; -------- END GLOBALS --------
; INITIALIZER OVER
; =-=-=-=-=-=-=-=-=
; #~#~#~#~#~#~#~#~#



; #~#~#~#~#~#~#~#~#
; =-=-=-=-=-=-=-=-=
; MAIN(void)
; 1) Print prompt
; 2) Read single digit 0..9
; 3) Call FIB(n)
; 4) Print "F(n) = value"
; Locals:
;   R5,#-1 : result
;   R5,#-2 : n
; =-=-=-=-=-=-=-=-=
MAIN
; push return address
ADD  R6, R6, #-1
STR  R7, R6, #0
; push previous frame pointer
ADD  R6, R6, #-1
STR  R5, R6, #0
; set current frame pointer
ADD  R5, R6, #0
; allocate locals
ADD  R6, R6, #-2

; ---- prompt ----
LEA  R0, PROMPT_STR
PUTS

; ---- read n in [0..9] ----
JSR  READ_DIGIT_0_9
STR  R0, R5, #-2                  ; save n

; ---- call FIB(n) ----
LDR  R0, R5, #-2                  ; arg n
ADD  R6, R6, #-1
STR  R0, R6, #0                   ; push arg
JSR  FIB
ADD  R6, R6, #1                   ; pop arg
STR  R0, R5, #-1                  ; result

; ---- print "F(" n ") = " result ----
LEA  R0, F_OPEN
PUTS
LDR  R0, R5, #-2
JSR  PRINT_UINT_DEC
LEA  R0, CLOSE_EQ
PUTS
LDR  R0, R5, #-1
JSR  PRINT_UINT_DEC
LD   R0, NEWLINE
OUT

; deallocate locals
ADD  R6, R6, #2
; restore FP
LDR  R5, R6, #0
ADD  R6, R6, #1
; restore RA
LDR  R7, R6, #0
ADD  R6, R6, #1
; return
RET



; #~#~#~#~#~#~#~#~#
; =-=-=-=-=-=-=-=-=
; FIB(int n) -> int
; Arg at R5,#2
; Locals:
;   R5,#-1 : local_n
;   R5,#-2 : temp_Fn1
;   R5,#-3 : temp_Fn2
; =-=-=-=-=-=-=-=-=
FIB
; push RA
ADD  R6, R6, #-1
STR  R7, R6, #0
; push old FP
ADD  R6, R6, #-1
STR  R5, R6, #0
; set FP
ADD  R5, R6, #0
; locals
ADD  R6, R6, #-3

; local_n = n
LDR  R0, R5, #2
STR  R0, R5, #-1

; ---- base cases ----
; if (n == 0) return 0
LDR  R0, R5, #-1
BRp  FIB_GT_ZERO
AND  R0, R0, #0
BR   FIB_RET

FIB_GT_ZERO
ADD  R0, R0, #-1                  ; n-1
BRp  FIB_GENERAL                  ; n >= 2 -> general
AND  R0, R0, #0
ADD  R0, R0, #1                   ; return 1 for n==1
BR   FIB_RET

; ---- general: F(n) = F(n-1) + F(n-2) ----
FIB_GENERAL
; F(n-1)
LDR  R0, R5, #-1
ADD  R0, R0, #-1
ADD  R6, R6, #-1
STR  R0, R6, #0                   ; push (n-1)
JSR  FIB
ADD  R6, R6, #1
STR  R0, R5, #-2                  ; temp_Fn1 = F(n-1)

; F(n-2)
LDR  R0, R5, #-1
ADD  R0, R0, #-2
ADD  R6, R6, #-1
STR  R0, R6, #0                   ; push (n-2)
JSR  FIB
ADD  R6, R6, #1
STR  R0, R5, #-3                  ; temp_Fn2 = F(n-2)

; return sum
LDR  R1, R5, #-2
LDR  R2, R5, #-3
ADD  R0, R1, R2

; epilogue
FIB_RET
ADD  R6, R6, #3
LDR  R5, R6, #0
ADD  R6, R6, #1
LDR  R7, R6, #0
ADD  R6, R6, #1
RET



; #~#~#~#~#~#~#~#~#
; =-=-=-=-=-=-=-=-=
; READ_DIGIT_0_9() -> R0 in [0..9]
; ignores CR/LF and re-prompts itself until a digit is typed
; =-=-=-=-=-=-=-=-=
READ_DIGIT_0_9
; prologue
ADD  R6, R6, #-1
STR  R7, R6, #0
ADD  R6, R6, #-1
STR  R5, R6, #0
ADD  R5, R6, #0

RD_LOOP
GETC                               ; R0 = char
ADD  R1, R0, #0
OUT                                ; echo

; ignore CR
LD   R2, CR
NOT  R2, R2
ADD  R2, R2, #1
ADD  R3, R0, R2
BRz  RD_LOOP

; ignore LF
LD   R2, LF
NOT  R2, R2
ADD  R2, R2, #1
ADD  R3, R0, R2
BRz  RD_LOOP

; accept only '0'..'9'
ADD  R3, R0, #-48                  ; char - '0'
BRn  RD_LOOP
ADD  R3, R0, #-57                  ; char - '9'
BRp  RD_LOOP

; convert to 0..9
ADD  R0, R0, #-48

; epilogue
LDR  R5, R6, #0
ADD  R6, R6, #1
LDR  R7, R6, #0
ADD  R6, R6, #1
RET



; #~#~#~#~#~#~#~#~#
; =-=-=-=-=-=-=-=-=
; PRINT_UINT_DEC(R0) : prints 0..99 (F(9)=34 max)
; two-digit path saves ones before printing tens
; =-=-=-=-=-=-=-=-=
PRINT_UINT_DEC
; prologue
ADD  R6, R6, #-1
STR  R7, R6, #0
ADD  R6, R6, #-1
STR  R5, R6, #0
ADD  R5, R6, #0

; if R0 < 10: single digit
ADD  R1, R0, #-10
BRn  ONE_DIGIT

; compute tens and ones by repeated subtraction
AND  R2, R2, #0                   ; tens = 0
PD_SUB10
ADD  R0, R0, #-10
ADD  R2, R2, #1
ADD  R1, R0, #-10
BRzp PD_SUB10
; now: R2=tens, R0=ones (0..9)

; backup ones
ADD  R1, R0, #0                   ; ones -> R1

; print tens
LD   R3, ASCII_0
ADD  R0, R2, R3
OUT

; print ones (from backup)
LD   R3, ASCII_0
ADD  R0, R1, R3
OUT
BR   PD_DONE

ONE_DIGIT
LD   R3, ASCII_0
ADD  R0, R0, R3
OUT

PD_DONE
; epilogue
LDR  R5, R6, #0
ADD  R6, R6, #1
LDR  R7, R6, #0
ADD  R6, R6, #1
RET


.END
