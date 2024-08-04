import java.util.Scanner;

public class Main {

    public static int miniMax(boolean isMaximizing,Board board) {
        //Base Case
        GameState result = board.gameState();
        if(!result.equals(GameState.INPROGRESS)) {
            //board.printBoard();
            return switch (result) {
                case TIE -> 0;
                case OWINS -> 1;
                case XWINS -> -1;
                default -> Integer.MAX_VALUE;
            };
        }
        if (isMaximizing) {
            int bestScore = Integer.MIN_VALUE;
            for(int i = 0;i<9;i++) {
                if(board.getCellValue(i) == Cell.BLANK) {
                    board.setCellValue(i,false);
                    int currentScore = miniMax(false,board);
                    board.clearCell(i);
                    //System.out.println("Minimizing: "+currentScore);
                    bestScore = Math.max(currentScore,bestScore);
                }
            }
            return bestScore;
        }

        else {
            int bestScore = Integer.MAX_VALUE;
            for(int i = 0;i<9;i++) {
                if(board.getCellValue(i) == Cell.BLANK) {
                    board.setCellValue(i,true);
                    int currentScore = miniMax(true,board);
                    board.clearCell(i);
                    //System.out.println("Maximizing: " + currentScore);
                    bestScore = Math.min(currentScore,bestScore);
                }
            }
            return bestScore;
        }
    }


    public static void main(String[] args) {
        Board board = new Board();
        boolean isHumanTurn = false;
        boolean isGameOver = false;

        while(!isGameOver) {
            if(isHumanTurn) {
                Scanner scanner = new Scanner(System.in);
                int userInput = -1; // Initialize with an invalid value

                while (userInput < 0 || userInput > 8) {
                    System.out.print("Please enter a number between 0 and 8: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("That's not a valid number!");
                        System.out.print("Please enter a number between 0 and 8: ");
                        scanner.next();
                    }
                    userInput = scanner.nextInt();
                    if (userInput < 0 || userInput > 8) {
                        System.out.println("Number out of range. Try again.");
                    }
                }


                board.setCellValue(userInput,true);
                board.printBoard();
                System.out.println("Computer Turn:");
                isHumanTurn = false;

            }
            else {
                int bestScore = Integer.MIN_VALUE;
                int bestPosition = -1;
                for(int i = 0;i<9;i++) {
                    if(board.getCellValue(i) == Cell.BLANK) {
                        board.setCellValue(i,false);
                        int currentScore = miniMax(false,board);
                        board.clearCell(i);
                        if(currentScore > bestScore) {
                            bestScore = currentScore;
                            bestPosition = i;
                        }

                    }
                }
                board.setCellValue(bestPosition,false);
                board.printBoard();
                isHumanTurn = true;

            }
            isGameOver = !board.gameState().equals(GameState.INPROGRESS);

        }
    }
}
