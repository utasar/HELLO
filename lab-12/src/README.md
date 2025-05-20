# Quiz Game Project
I forget the name but (trapizia challenge or something) but basically it was a something quiz game for us (project 1)

## Project Description
The Quiz Game Project is an interactive console-based application that asks users a series of questions. The game consists of different types of questions: multiple choice, true/false, and short answers. The user answers the questions, and the program keeps track of their score, giving feedback after each question. At the end of the game, the final score is displayed as a percentage of correct answers. This project reinforces key programming concepts such as conditionals, loops, input validation, and basic data types (integers, strings, booleans, etc.).

## Project Guide
Guide was available in pdf in dropbox ,while we have some lab problem and class activities in CS1180 which give more guidance .

### Dependencies
To run this project, you need the following installed on your system:
- **Java JDK**: Version 8 or above is required to compile and run the Java program. You can download it from the official [Java website](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html).
but  in detail if you want
  Environment Setup
  These instruction apply generically to any OS.

Install Adopt Open JDK 17 (select the default options)
Download from ninite.com
Link to all versions: https://adoptium.net/temurin/releases/?version=17
Mac users, see Mac Notes
JDK = Java Development Kit
Includes a java code compiler and virtual environment where your compiled code runs
NOTE: JRE = Java Runtime Environment. Runs compiled Java code. You cannot compile Java source code if you only have a JRE installed.
Install Visual Studio Code
Download from ninite.com
Link to all versions: https://visualstudio.microsoft.com/downloads/
Code is a text editor at the start - extensions give it support for languages you want to work with
In VSCode, go to Extensions, search for the WSU CSE extension
This extension includes extensions for other languages you’ll find in future courses
Link to extension: https://marketplace.visualstudio.com/items?itemName=WrightStateUniversity-ComputerScience.wsu-cse
Restart VSCode in order for extensions to take effect
For more help with using Java in VSCode, look here.

Alternative JDK installation via VSCode
This is recommended for Mac Users

Install Visual Studio Code
Install the WSU CSE Extension pack - this includes the "Language Support for Java(TM) by Red Hat".
Once this extension is installed, you'll be prompted to "Install JDK" - click in and make sure JDK 17 is selected.
Mac Notes
There are now two Mac chips - Intel / AMD (x64) and ARM64 (aarch64). You do need to know which you have to install the correct software.

These instructions from Adoptium are to install from the .pkg file once you select the correct one for your hardware.

Special Notes on VSCode
Create a Java Project in VSCode with Command Palette

Ctrl + Shift + P = Command Palette
Type “Java”, then select “Java: Create Java Project”
Project names don’t matter (for class, keep an eye on assignment instructions)
Turning off Parameter Names

Parameter names are the variable names used by methods we call. One of the extensions has these display by default. They are not part of your code, they are just reminders.
Ctrl + Shift + P (to open VSCode Command Palette) -> Search for “Settings”, select “Preferences: Open User Settings (JSON)”
Add this line: "editor.inlayHints.enabled": "off"
You can choose to not do this, but it annoys me, so I’m turning it off.
Change Color Theme

File -> Preferences -> Color Theme
Colorblind Themes

Install "Slack" Color Theme package
Can now select Slack Color Themes for "Protanopia & Deuteranopia" and "Tritanopia"
Troubleshooting VSCode + Java Extension
Restart VSCode in order for extensions to take effect
https://stackoverflow.com/questions/57148090/java-project-in-vs-code-no-delegatecommandhandler-for-vscode-java-validatelaun
https://github.com/Microsoft/vscode-java-debug/blob/main/Troubleshooting.md
Multiple JDK Versions
Problem: you've been asked to install a new version of the JDK, but how do you check?
In a terminal (Powershell or Powershell in VSCode), type: java --version
If you see the accurate version you just installed, skip the rest, get to coding.
Else:

Find where your JDK is installed:
Powershell / CMD: where java
Mac / Linux: which java
Example path to java executable within JDK: C:\Program Files\Eclipse Adoptium\jdk-17.0.8.7-hotspot\bin\java.exe
Add the location to JDK binaries to your PATH environment variable:
Example path to JDK: C:\Program Files\Eclipse Adoptium\jdk-17.0.8.7-hotspot\bin
Windows: Go to System Properties -> Edit System Environment variables -> look for Path, select Edit, select New, enter path to JDK, move to top of list. Removing the "old" JDK path is optional as long as the "new" one is listed before the old one.
Restart all terminals (or your PC). Verify changes took effect using java --version

### How to run the project
Once the dependencies are installed, follow these steps to run the project:
1. Download or Zip file by Developer or clone the project repository to your local machine 
2. Open a terminal or command prompt.
3. Navigate to the folder containing the `Project1.java` file.
4. Compile the Java program using the command:


### How to play the game
Once the program runs, the game will present a series of 6 questions. You will need to provide your answers as prompted:
1. For the first question, input an integer.
2. For the second question, input a text string.
3. The third question is a true/false question, and you need to input "true" or "false".
4. The fourth question expects a decimal number (with two decimal places).
5. The fifth question asks for a single character.
6. The sixth question is multiple choice, where you select one of the options.

After each question, the game will immediately inform you if your answer was correct or incorrect, and it will update your score accordingly. At the end of the game, your total score will be displayed as a percentage.

#### Special Details:
- The program will handle input validation for the decimal number in question 4, ensuring that you enter a valid number.
- The program is case-insensitive for text and character-based questions to provide a more user-friendly experience.

## Lessons Learned
Throughout this project, I reinforced several key programming concepts:
- **Conditionals**: I practiced using `if`, `else`, and `else if` statements to check user input and provide the appropriate response.
- **Loops**: I used a `while` loop to ensure valid input for the decimal question, preventing invalid input from breaking the program.
- **Data Types**: I worked with various data types such as integers, strings, booleans, characters, and doubles to handle different kinds of input and output.
- **Input Validation**: I gained a deeper understanding of validating input to ensure the program behaves as expected and doesn't crash due to invalid input (e.g., handling non-numeric input for decimal numbers).
- **Bug Fixing**: One of the challenges I encountered was ensuring that the true/false question handled both answers correctly, regardless of case. I had to adjust my conditionals to properly evaluate user input for this question.

By the end of this project, I gained more confidence in working with Java, and I learned how to build a simple interactive program that provides immediate feedback to the user.

