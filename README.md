# Project 1 Documentation

## Compiling and Running
Before running the programs, it is imperative that you enter the main.java file and modify the PLAYER2 String at Line 12 to the name of the opposing player.  Additionally, the depth variable at Line 10 can also be adjusted to produce more reasonable times.

To compile, use “javac ClassName.java” or “javac *.java” to compile all the files in the directory. Once it has been compiled, run the program by using “java main” because our program’s main function is located in the main.java class. 

Alternatively, running the main method in main.java assuming a shared directory with the opponent should also be viable.

## Utility Function
Since there is no method dedicated towards the utility function, the lines relevant to the utility function are line 47 in Node.java and lines 79-92 in Board.java. The Terminal-Test is invoked in line 46 in Node.java, findBestMove():
if(depth == 0 || n.board.findSequences(n.maxPlayerColor, 5) > 0 || n.board.findSequences(n.minPlayerColor, 5) > 0){
The latter two conditions check whether there are 5 stones in a row, of any color. Then findBestMove() evaluates the current board as the player and the opponent by calling getHeuristic() twice on line 47. The difference in heuristics will result in infinity, negative infinity, or 0, depending on whether the current board indicates a win, loss or draw respectively. Because the difference is taken, we do not explicitly check for a loss in Board.getHeuristic().

## Evaluation Function
The evaluation function to estimate state desirability simply checks which number provided by the heuristic is greater. The heuristic provides a number for each possible move on the board but gives higher values to moves that will lead to more pieces connected in a horizontal, vertical or diagonal direction. By comparing these numbers, the evaluation function evaluates the most desired move by selecting the option with the highest heuristic value. 

## Heuristics
The heuristic consists of several methods that can be found in the Board.java file that look for pieces that are connected in a vertical, horizontal or diagonal line. After the board has been checked, the getHeuristic() (Line79, Board.java) method weighs them according to their length with 5 being weighed the highest and decreasing as line length decreases. With this heuristic, the program will prioritize placing pieces such that it can get the highest possible number of pieces connected in a row.  Additionally, sequences that do not have opposing pieces at either end of the sequence are prioritized over sequences that have an opposing piece on either end, while sequences that have opposing pieces capping both ends do not count toward the heuristic.  Another heuristic is one that checks where the current pieces on the board are and ignores moves with no pieces in adjacent spaces to avoid expanding the whole board. 

## Results
### Tests
	The testing was conducted by having the program play against itself. One of the 
	programs had a depth of 1 and the other had a depth of 3. As expected, the one 
	with the depth of 3 won. 
### Weaknesses/Strengths
	One of the strengths of the program is that it is always looking to expand on 
	already-existing lines because it prioritizes the spaces that will lead to longer 
connections. This is important because to eventually win the game, it must reach 
five in a row.  One weakness of the program is that because it tries to expand 
connections that have already been made, it will not necessarily pick locations 
that will lead to a better chance of winning if the opponent blocks off its 
connected pieces. Additionally, because moves that are not adjacent to current pieces are not considered, there may be some missed opportunities.

## Discussion
The evaluation function and heuristic the program uses are simple but effective. The decision to build on connecting pieces allows the program to get closer to its desired state of having five in a row every turn. Furthermore, the heuristic that makes it so that the program only places pieces that are connected to other pieces on the board is useful because that keeps all of the pieces together in a relatively small area, leading to more possible connecting pieces in different directions as the game progresses
