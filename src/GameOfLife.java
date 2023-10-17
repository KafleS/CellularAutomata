import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameOfLife extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    // to create the grid for game of life
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Game of Life");

        GridPane root = new GridPane();
        double width = 1200;
        double height = 1000;
        Scene scene = new Scene(root, width, height);

        primaryStage.setScene(scene);
        primaryStage.show();

        List<String> firstGenStr = new ArrayList<>();
        int row = 0, column = 0;
        List<String> behavior = new ArrayList<>();
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
            // to ask to enter the inital state file path
            System.out.print("Enter the initial state file path:");
            String filePath2 = in.next();
            Scanner fp2 = new Scanner(new File(filePath2));
            String[] dimensions = fp2.nextLine().split(" ");
            row = Integer.parseInt(dimensions[0]);
            column = Integer.parseInt(dimensions[1]);
            while(fp2.hasNext()) {
                firstGenStr.add(fp2.nextLine());
            }
        }else{
            System.out.println("Enter the behavior:");
            System.out.println("Enter the initial state:");
        }


        //Turn the firstGen string into a list of cells
        List<List<ECACell>> firstGen = new ArrayList<>();
        for (int i =0 ;i<firstGenStr.size(); i++ ){
            List<ECACell> firstGenRow = new ArrayList<>();
            for (int j =0 ;j<firstGenStr.get(0).length(); j++ ){
                firstGenRow.add(ECACell.fromChar(firstGenStr.get(i).charAt(j)));
            }
            firstGen.add(firstGenRow);
        }
        GridGame grid = new GridGame(root, row, column, firstGen, 50);
        ECARunner.run(grid);
    }
}
