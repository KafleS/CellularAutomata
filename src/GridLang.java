import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class GridLang implements Grid2D {
    // Used by JavaFX to display the visualization
    private final GridPane GRID_PANE;
    // 8 bit string representing the behavior of the CA
    private final List<String> BEHAVIOR;
    // Width/height of cell
    private final double CELL_SIZE;

    private final int row, column;

    // Current generation of cells
    private List<List<LLCell>> currentGen;


    public GridLang(GridPane gridPane,
                    int row,
                    int column,
                    List<String> behavior,
                    List<List<LLCell>> currentGen,
                    double cellSize) {
        this.GRID_PANE = gridPane;
        this.row = row;
        this.column = column;
        this.BEHAVIOR = behavior;
        this.currentGen = currentGen;
        this.CELL_SIZE = cellSize;
        // Show the initial generation to the screen
        show();
    }

    /**
     * TODO: Fill in the logic below
     * I would suggest starting off by hard coding one of the rules,
     * such as rule 30, then generalize from there.
     * This function evolves the current generation to the next generation
     * using the current rule set given by the behavior string.
     */
    private void evolve() {
        final List<List<LLCell>> newGen = new ArrayList<>();
        for(int i = 0; i < this.row;i++) {
            List<LLCell> row = new ArrayList<>();
            for(int j = 0; j < column;j++) {
                List<String> neighbors = new ArrayList<>();
                // to get the matching neighbors

                String c = currentGen.get(i).get(j).getStr();
                String n = currentGen.get(Math.floorMod(i-1,this.row)).get(j).getStr();
                String e = currentGen.get(i).get(Math.floorMod(j+1,column)).getStr();
                String s = currentGen.get(Math.floorMod(i+1,this.row)).get(j).getStr();
                String w = currentGen.get(i).get(Math.floorMod(j-1,column)).getStr();

                neighbors.add(c+n+e+s+w);
                neighbors.add(c+e+s+w+n);
                neighbors.add(c+s+w+n+e);
                neighbors.add(c+w+n+e+s);

                Boolean found = false;
                for(String behavior : BEHAVIOR){
                    for(String neighbor: neighbors){
                        if(neighbor.equals(behavior.substring(0,5))){
                            row.add(LLCell.fromChar(behavior.charAt(5)));
                            found = true;
                            break;
                        }
                    }
                    if(found){
                        break;
                    }
                }
                neighbors.clear();
            }
            newGen.add(row);
        }
        currentGen = newGen;
    }

    /**
     * This function shows the current generation to the JavaFX window
     */
    public void show() {
        int colIndex = 0;
        // Current generation (row) being shown to the screen
        int currentGenIndex = 0;
        // Create new rectangles to show for the current generation
        for (List<LLCell> cells : currentGen) {
            colIndex = 0;
            for (LLCell cell : cells) {
                // Create a rectangle to represent the cell
                Rectangle rect = new Rectangle(CELL_SIZE, CELL_SIZE, cell.getColor());
                // Add it to the JavaFX graph
                GRID_PANE.add(rect, colIndex, currentGenIndex);
                // Tell it where to show it on the screen
                //GridPane.setConstraints(rect, );
                // Go to the next cell
                colIndex++;
            }
            currentGenIndex++;
        }

    }

    /**
     * This function advances the state of the class to the next generation.
     * It then shows this new generation to the Java FX window.
     */
    @Override
    public void nextGeneration() {
        evolve();
        show();
    }


}
