# AppDama
For this assignment we wrote a simple application that runs a game of Checkers from a .csv file containing a list of moves. The application is able to enforce the following rules:

- Checkers pieces only move in diagonals
- Pawns can only move one diagonal space forward
- Pawns get promoted to Queens upon reaching the opposite end of the board
- Queens can move any number of diagonal spaces in any direction, given that there are no pieces in the way
- A valid capture happens when a piece moves towards another piece of the opposite color, "jumps" over it and lands on the adjacent diagonal space; the captured piece is then removed from the board
- If there are one or more pieces available to be captured, the player must necessarily capture one of them; consecutive captures are allowed

The source files can be found in the [src](src/) directory, and the compiled bytecode binaries can be found in the [bin](bin/) directory.

## Installation

### Compiling

**Requirements**: Java JDK (preferrably v8, but older versions should be fine)

If for some reason you would like to compile from source: on UNIX systems, `cd` into the `bin` folder and run the following command:
```
$ javac src/mc322/*/*.java -d bin/ 
```
### Running

**Requirements**: Java JRE (preferrably v8, but older versions should be fine)

To run a game, `cd` into the `bin` folder and run the following command:
```
$ java mc322.lab05b.AppDama <path-to-csv> <path-to-output-file>
```
Where `<path-to-csv>` is the csv containing the sequence of moves to be executed (a few examples are provided in the `data` folder) and `<path-to-output-file>` is where the program will save an output file containing the final state of the board after the game is finished.
