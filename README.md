# software-development-card-game
This project is a coursework assignment for the module ECM2414 which consists of a multi-threaded card game.

Requires JDK 17 or later.
Tested using JUnit 5 (Jupiter), /lib already includes the .jar for JUnit.
Built with OpenJDK 25.0.4.1 LTS

## File Structure:
```
software-development-card-game/
    README.md
    src/    Source code
       Card.java
       CardDeck.java
       CardGame.java
       Hand.java
       Pack.java
       Player.java
    test/   JUnit 5 (Jupiter) tests
        CardDeckTest.java
        CardGameTest.java
        CardTest.java
        HandTest.java
        PackTest.java
        PlayerTest.java
    bin/    Compiled .class
    packs/  Test packs
        valid_n2.txt, valid_n4.txt, immediate_win_n2.txt
        invalid_chars_n2.txt, negative_value_n2.txt, too_few_n2.txt
        too_many_n2.txt, empty.txt
    lib/
        junit-platform-console-standalone-1.14.4.jar
```
## How to run the game
Using the jar run the following command in the terminal:
```
java -jar cards.jar
```

Running from the source run from root the following commands in the terminal:
```
javac --release 17 -d bin src/*.java
java -cp bin CardGame
```

(Example) You will then be prompted to choose how many players and what pack to run the game with:
```
Please enter the number of players:
2
Please enter location of pack to load:
packs/valid_n2.txt
player 2 wins
```

Winners will vary on a valid pack, except for the immediate_win_n2.txt which is meant to always return "player 1 wins"

- On an invalid input, the program will show an error and prompt the user for another input until it is a valid, whole, positive integer; or until the pack file path loads a valid pack with 8*n cards.
- When the game ends, all "playerN_output.txt" and "deckN_output.txt" files will be created in the folder from which the game was run.

## How to run the tests
To run the JUnit 5 tests, the user must run the following commands from root in the terminal:
```
javac --release 17 -d bin -cp lib/junit-platform-console-standalone-1.14.4.jar src/*.java test/*.java
java -jar lib/junit-platform-console-standalone-1.14.4.jar execute --class-path bin --scan-class-path
```
This assures the tests run via the JUnit Platform Console Standalone 1.14.4

The JUnit suite will then run and give a breakdown of how many tests passed, listing each one with a check or an X
The breakdown should show 55 successful tests with 0 failures.

- After running the tests the terminal output shows multiple "player x wins" and will create multiple "playerN_output.txt" and "deckN_output.txt" files, these are expected.
- The files in packs/ are all required for tests so it is expected to run the tests without modifying those files.