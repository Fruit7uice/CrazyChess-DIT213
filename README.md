# CrazyChess - Group 25
**Authors**

_David Einhaus_ &
_Alva Johansson_ &

_Johannes Höher_ &
_Joel Leiditz Thorsson_ &
_Jeffrey Wolff_





--------
**Info**

For this project we chose to implement an application which is our personal take on chess. Our application partly consists of the Classic chess-implementation, and it also consists of a "Crazy" chess-implementation(not fully implmented yet).

The classical version is a normal take on chess with classic rules and normal behaviors for the pieces.
The "Crazy" version of our Chess however, acts a bit differently than the classic one, it consists of an implementation with special rules and behaviors for the pieces.





-----
**How to run**

Our program is internally built by initially running the main-menu view. This initializes in the game-class, here the game-class builds the application by running the main-method in the main-menu-view. Once here the user will be able to navigate to all the different parts of the application.

To run our application start by cloning the git-repo or download the zip-file. Once downloaded navigate to the top-level directory of the project which in our case would be equivalent to CrazyChess-DIT213. 
From here you should execute the following command in the terminal: " _**mvn clean javafx:run**_ "
as this will launch the main-menu of the game. 


----
**Rules for Chess**




**Basic chess rules**
In chess, each player takes turns to make a single move. Players cannot choose to skip a turn - they must move a piece. Each chess piece moves in a specific way, and must be moved according to its legal movement.

Except for the knight, which may jump over pieces, pieces cannot move through pieces of either colour without either stopping (in the same of a piece of the same colour) or capturing them (in the case of a piece of the opposite colour).

How to capture pieces
If a piece lands on a space with an opponent’s piece, that piece is captured and removed from the board. Pieces cannot be placed on the same square as a piece of the same colour. When a piece captures an opponent’s piece, it must finish its current move action and end the player’s turn.





**How to capture pieces**

If a piece lands on a space with an opponent’s piece, that piece is captured and removed from the board. Pieces cannot be placed on the same square as a piece of the same colour. When a piece captures an opponent’s piece, it must finish its current move action and end the player’s turn.








_Source: How to play chess for beginners: setup, moves and basic rules explained - Matt Jarvis Nov. 9, 2021_
