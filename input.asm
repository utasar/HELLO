;=========================================
; input.asm  —  TRAP x40
; Reads 2-digit decimal number from 00–99
; Returns the final integer value in R0
;========================================

.ORIG x4000        

TRAP_X40
    ; Save registers
    ST R1, SAVE_R1
    ST R2, SAVE_R2
    ST R3, SAVE_R3
    ST R7, SAVE_R7

    AND R0, R0, #0     ; R0  = 0

    ;-------------------
    ; Read first digit
    ;-------------------
READ_FIRST
    GETC               ; char -> R0
    OUT                ; echo it to the screen
    ADD R1, R0, #0     ; store char in R1

    ; Convert char -> digit
    LD R2, ASCII_ZERO
    ADD R1, R1, R2     ; R1 = 1st_digit (0–9)

    ; Save first digit in R3
    ADD R3, R1, #0

    ;------------------
    ; Read second digit
    ;------------------
READ_SECOND
    GETC
    OUT
    ADD R1, R0, #0

    ; Convert char -> digit
    LD R2, ASCII_ZERO
    ADD R1, R1, R2     ; R1 = 2nd digit (0–9)

    ;-------------------------
    ; Compute final value
    ; value = 1st digital * 10 + 2nd digit
    ;-------------------------

   ; Multiply first digit (R3) by 10
    ADD R0, R3, #0     ; R0 = 1st digit
    ADD R0, R0, R3     ; R0 = 2 * 1st digit
    ADD R0, R0, R3     ; R0 = 3 * 1st digit
    ADD R0, R0, R3     ; R0 = 4 * 1st digit
    ADD R0, R0, R3     ; R0 = 5 * 1st digit  (x5 then double)

    ADD R0, R0, R0     ; R0 = 10 * 1st dance

    ; add the second digit
    ADD R0, R0, R1     ; final value in R0

    ;----------------------------
    ; Restore registers & return 
    ;----------------------------
RESTORE_AND_RTI
    LD R1, SAVE_R1
    LD R2, SAVE_R2
    LD R3, SAVE_R3
    LD R7, SAVE_R7
    RTI               ; required for TRAP handlers

;-------------------------
; Constants + Saved Regs
;-------------------------

ASCII_ZERO .FILL xFFD0   ; -48 -> converts ASCII to numeric digit

SAVE_R1 .FILL 0
SAVE_R2 .FILL 0
SAVE_R3 .FILL 0
SAVE_R7 .FILL 0

.END
