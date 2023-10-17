
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ElementaryCA extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    // to create the grids for elementary cell
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("1D Cellular Automata");

        GridPane root = new GridPane();
        double width = 1200;
        double height = 1000;
        Scene scene = new Scene(root, width, height);

        primaryStage.setScene(scene);
        primaryStage.show();

        String firstGenStr = "";
        String behavior = "";
        Scanner in = new Scanner(System.in);
        int choice;
        do {
            // to provide option to the user
            System.out.println("How do you want to input your initial state and rules: ");
            System.out.println("1) From file");
            System.out.println("2) Input manually");
            choice = in.nextInt();
        } while (choice != 1 && choice != 2);

        if(choice == 1){
            // if 1 is choosed file path is asked
            System.out.print("Enter a file path:");
            try{
                String filePath = in.next();
                Scanner fp = new Scanner(new File(filePath));
                behavior = fp.nextLine();
                firstGenStr = fp.nextLine();
            }catch(Exception e){
                System.out.println("File doesn't work");
            }
        }else{
            // to ask the behavior
            System.out.println("Enter the behavior:");
            behavior = in.nextLine();
            System.out.println("Enter the initial state:");
            firstGenStr = in.nextLine();
        }
        // Turn the firstGen string into a list of cells
        List<ECACell> firstGen = new ArrayList<>();
        for (int i =0 ;i<firstGenStr.length(); i++ ){
            firstGen.add(ECACell.fromChar(firstGenStr.charAt(i)));
        }
        Grid1D grid = new Grid1D(root, behavior, firstGen, 50);
        ECARunner.run(grid);
    }
}
