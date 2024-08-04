public class Board {

    private Cell[] board = new Cell[9];

    public Board() {
        for(int i =0; i < 9;i++) {
            board[i] = new Cell();
        }
    }

    public GameState gameState() {
        GameState state = GameState.INPROGRESS;
        int winner = Cell.BLANK;
        boolean isBoardOpen = true;
        boolean blankFound = false;
        if(checkRow(0,4,8)) {
            winner = board[0].getValue();
        }
        if(checkRow(2,4,6)) {
            winner = board[2].getValue();
        }
        for(int i = 0;i<3;i+=3) {
            if (checkRow(i,i+1,i+2)) {
                winner = board[i].getValue();
            }
        }
        for(int j =0;j<3;j+=1) {
            if(checkRow(j,j+3,j+6)) {
                winner = board[j].getValue();
            }
        }
        for(int k=0;k<9;k++) {
            if(board[k].getValue() == Cell.BLANK) {
                blankFound = true;
                break;
            }
        }
        if(winner == Cell.X) {
            state = GameState.XWINS;
        } else if (winner == Cell.O) {
            state = GameState.OWINS;

        } else if (!blankFound) {
            state = GameState.TIE;
        }
        return state;
    }

    public boolean checkRow(int a,int b,int c) {
        int one = board[a].getValue();
        int two = board[b].getValue();
        int three = board[c].getValue();
        if (one == two && two == three) {
           return true;
        }
        return false;
    }

    public void printBoard() {
        System.out.printf("%s|%s|%s\n",getSymbol(0),getSymbol(1),getSymbol(2));
        System.out.println("-----");
        System.out.printf("%s|%s|%s\n",getSymbol(3),getSymbol(4),getSymbol(5));
        System.out.println("-----");
        System.out.printf("%s|%s|%s\n",getSymbol(6),getSymbol(7),getSymbol(8));
        System.out.println();
    }

    public String getSymbol(int number) {
        int value = board[number].getValue();
        return switch (value) {
            case Cell.X -> "X";
            case Cell.O -> "O";
            default -> " ";
        };

    }

    public void setCellValue(int spot,boolean isX) {
        if(isX) {
            board[spot].setX();
        }
        else {
            board[spot].setO();
        }
    }
    public void clearCell(int spot) {
        board[spot].setBlank();
    }
    public int getCellValue(int spot) {
        return board[spot].getValue();
    }

    public Board copyBoard() {
        Board newBoard = new Board();

        for(int i = 0;i<9;i++) {
            newBoard.board[i] = new Cell(this.board[i].getValue());
        }
        return newBoard;
    }
}
