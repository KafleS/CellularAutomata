public class Neighborhood {
    private char chr1, chr2, chr3;

    // to make the constructor for neighbor class
    Neighborhood(char a, char b, char c) {
        chr1 = a;
        chr2 = b;
        chr3 = c;
    }

    // to make the boolean method
    public boolean equals(final char leftCell, final char middleCell, final char rightCell) {
        if (chr1 == leftCell && chr2 == middleCell && chr3 == rightCell) {
            return true;
        }else{
            return false;
        }
    }
}

