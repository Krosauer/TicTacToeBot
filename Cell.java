public class Cell {
    public static final int BLANK = 0;
    public static final int O = 1;
    public static final int X = 2;
    private int value;
    public Cell() {
        value = BLANK;
    }

    public Cell(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setX(){
        this.value = X;
    }

    public void setO(){
        this.value = O;
    }

    public void setBlank() { this.value = BLANK; }

}
