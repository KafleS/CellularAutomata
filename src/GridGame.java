/**
 * Name : Suman Kafle
 * CS 251 Project 5
 *
 * Project description : ALl the methods are filled as per the instruction of professor. One extra neighbor class is made
 * to help to the neighbor cell.
 */
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class GridGame implements Grid2D {
    // Used by JavaFX to display the visualization
    private final GridPane GRID_PANE;
    // Width/height of cell
    private final double CELL_SIZE;

    private final int row, column;

    // Current generation of cells
    private List<List<ECACell>> currentGen;


    public GridGame(GridPane gridPane,
                    int row,
                    int column,
                    List<List<ECACell>> currentGen,
                    double cellSize) {
        this.GRID_PANE = gridPane;
        this.row = row;
        this.column = column;
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
        final List<List<ECACell>> newGen = new ArrayList<>();
        for(int i = 0; i < this.row;i++) {
            List<ECACell> row = new ArrayList<>();
            for(int j = 0; j < column;j++) {
                int neighbors = 0;
                // to check the matching neighbors
                if(currentGen.get(Math.floorMod(i-1,this.row)).get(j).isOn()){neighbors++;}
                if(currentGen.get(Math.floorMod(i-1,this.row)).get(Math.floorMod(j+1,column)).isOn()){neighbors++;}
                if(currentGen.get(Math.floorMod(i-1,this.row)).get(Math.floorMod(j-1,column)).isOn()){neighbors++;}
                if(currentGen.get(Math.floorMod(i+1,this.row)).get(j).isOn()){neighbors++;}
                if(currentGen.get(Math.floorMod(i+1,this.row)).get(Math.floorMod(j+1,column)).isOn()){neighbors++;}
                if(currentGen.get(Math.floorMod(i+1,this.row)).get(Math.floorMod(j-1,column)).isOn()){neighbors++;}
                if(currentGen.get(i).get(Math.floorMod(j+1,column)).isOn()){neighbors++;}
                if(currentGen.get(i).get(Math.floorMod(j-1,column)).isOn()){neighbors++;}

                if(neighbors < 2&& currentGen.get(i).get(j).isOn()){
                    row.add(ECACell.WHITE);
                }else if((neighbors == 2||neighbors == 3)&& currentGen.get(i).get(j).isOn()){
                    row.add(ECACell.BLACK);
                }else if(neighbors > 3 && currentGen.get(i).get(j).isOn()){
                    row.add(ECACell.WHITE);
                }else if(neighbors == 3&& !currentGen.get(i).get(j).isOn()){
                    row.add(ECACell.BLACK);
                }else{
                    row.add(currentGen.get(i).get(j));
                }
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
        for (List<ECACell> cells : currentGen) {
            colIndex = 0;
            for (ECACell cell : cells) {
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
