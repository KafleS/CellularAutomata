import javafx.scene.paint.Color;

import javax.management.ObjectName;

public enum LLCell {
    GRAY(0),
    BLACK(1),
    GREEN(2),
    BLUE(3),
    RED(4),
    YELLOW(5),
    ORANGE(6),
    PURPLE(7);

    private final int STATE;

    LLCell(int state) {
        this.STATE = state;
    }


    // to return the color of cell
    public Color getColor() {
        if (this == GRAY) {
            return Color.GRAY;
        } else if (this == GREEN) {
            return Color.GREEN;
        } else if (this == RED) {
            return Color.RED;
        }else if (this == YELLOW) {
            return Color.YELLOW;
        }else if (this == PURPLE) {
            return Color.PURPLE;
        }else if (this == BLUE) {
            return Color.BLUE;
        }else if (this == ORANGE) {
            return Color.ORANGE;
        }
        else {
            return Color.BLACK;
        }
    }


    // to crete the cell from char
    public static LLCell fromChar(char c) throws IllegalArgumentException {
        if (c == '0') {
            return GRAY;
        }
        else if (c == '1') {
            return BLACK;
        } else if (c == '2') {
            return GREEN;
        } else if (c == '3') {
            return BLUE;
        }else if (c == '4') {
            return RED;
        }else if (c == '5') {
            return YELLOW;
        }else if (c == '6') {
            return ORANGE;
        }else if (c == '7') {
            return PURPLE;
        }
        else {
            throw new IllegalArgumentException("Input char must be either 0 or 1.");
        }
    }

    // to return the char
    public char getChar(){
        return (char)STATE;
    }

    // to return the string
    public String getStr() {
        return Integer.toString(STATE);
    }
}
