import com.sun.source.tree.WhileLoopTree;
import javafx.scene.paint.Color;

public enum ECACell {
    WHITE(false),
    BLACK(true);

    private final boolean STATE;

    ECACell(boolean state) {
        this.STATE = state;
    }

    public boolean isOn() {
        return STATE;
    }

    // to return the  color in the cell
    public Color getColor() {
        if (this == WHITE) {
            return Color.WHITE;
        }
        else {
            return Color.BLACK;
        }
    }

    //to return the char into the cell

    public static ECACell fromChar(char c) throws IllegalArgumentException {
        if (c == '0') {
            return WHITE;
        }
        else if (c == '1') {
            return BLACK;
        }
        else {
            throw new IllegalArgumentException("Input char must be either 0 or 1.");
        }
    }

    // to return the char
    public char getChar(){
        if (STATE){
            return '0';
        }else{
            return '1';
        }
    }
}
