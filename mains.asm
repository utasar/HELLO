.ORIG x3000      ; === Main Program Start ===

; === Stack Setup ===
LD R6, STACK_BASE      
ADD R6, R6, #-1        
STR R7, R6, #0
ADD R6, R6, #-1       
STR R5, R6, #0
ADD R5, R6, #0         

ADD R6, R6, #-1        
AND R0, R0, #0         
STR R0, R6, #0

ADD R6, R6, #-1      

; === Main Loop ===
WHILE_LOOP
LDR R0, R5, #-2        
LD R1, LOWERCASE_Q
NOT R1, R1
ADD R1, R1, #1
ADD R1, R0, R1
BRz HALT_MAIN          

LEA R0, PROMPT_MENU
PUTS                   

GETC                   
STR R0, R5, #-2

; === Print Option ===
LD R1, LOWERCASE_P
NOT R1, R1
ADD R1, R1, #1
ADD R1, R0, R1
BRnp CHECK_ADD

LEA R0, PROMPT_PRINT
PUTS

ADD R6, R6, #-1        
ADD R0, R5, #-1
STR R0, R6, #0
JSR PRINT_LIST
ADD R6, R6, #1
BRnzp WHILE_LOOP

; === Add Option ===
CHECK_ADD
LD R1, LOWERCASE_A
NOT R1, R1
ADD R1, R1, #1
ADD R1, R0, R1
BRnp CHECK_REMOVE

LEA R0, PROMPT_ADD
PUTS
TRAP x40              
STR R0, R5, #-3       

ADD R6, R6, #-1       
ADD R0, R5, #-1
STR R0, R6, #0

ADD R6, R6, #-1       
LDR R0, R5, #-3
STR R0, R6, #0

JSR ADD_VALUE
ADD R6, R6, #2        
BRnzp WHILE_LOOP

; === Remove Option ===
CHECK_REMOVE
LD R1, LOWERCASE_R
NOT R1, R1
ADD R1, R1, #1
ADD R1, R0, R1
BRnp WHILE_LOOP

LEA R0, PROMPT_REMOVE
PUTS
TRAP x40              
STR R0, R5, #-3

ADD R6, R6, #-1       
ADD R0, R5, #-1
STR R0, R6, #0

ADD R6, R6, #-1       
LDR R0, R5, #-3
STR R0, R6, #0

JSR REMOVE_VALUE
ADD R6, R6, #2        
BRnzp WHILE_LOOP

; === Exit Sequence ===
HALT_MAIN
ADD R6, R6, #2        
LDR R5, R6, #0
ADD R6, R6, #1
LDR R7, R6, #0
ADD R6, R6, #1
HALT

; === Constants & Prompts ===
STACK_BASE      .FILL x6000
LOWERCASE_A     .FILL x0061
LOWERCASE_P     .FILL x0070
LOWERCASE_Q     .FILL x0071
LOWERCASE_R     .FILL x0072

PROMPT_MENU     .STRINGZ "Options:\np - Print\na - Add\nr - Remove\nq - Quit\nChoose: "
PROMPT_PRINT    .STRINGZ "\nLinked list contents:\n"
PROMPT_ADD      .STRINGZ "\nEnter value to add:\n"
PROMPT_REMOVE   .STRINGZ "\nEnter value to remove:\n"

; === Subroutine Stubs (for linking) ===
PRINT_LIST
ADD R6, R6, #-1
RET

ADD_VALUE
ADD R6, R6, #-1
RET

REMOVE_VALUE
ADD R6, R6, #-1
RET

.END
