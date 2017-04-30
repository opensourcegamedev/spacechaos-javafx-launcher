package spacechaos.launcher.gui;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import spacechaos.launcher.Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Constantin Schulte
 */
public class CreditsController {
    public static final int CREDITS_NUMBER = 6;

    private ArrayList<Canvas> creditsEntries;

    @FXML private VBox creditsPane;

    @FXML private void initialize(){
        creditsPane.setFocusTraversable(false);
        creditsEntries = new ArrayList<Canvas>();

        for(int number = 1; number <= CREDITS_NUMBER; ++number){
            Canvas creditCanvas = new Canvas(900, 100);
            try(Scanner scanner = new Scanner(new File("./data/credits/credits_" + number + ".txt"))){
                GraphicsContext gc = creditCanvas.getGraphicsContext2D();
                gc.setFill(Color.WHITE);
                gc.setStroke(Color.gray(0.5));
                gc.setLineWidth(2);
                gc.strokeRect(1, 1, 898, 98);
                gc.fillRect(1, 1, 898, 98);

                gc.setFill(Color.gray(0.5));
                gc.setFont(new Font("arial", 32));
                gc.fillText(scanner.nextLine(), 20, 40);
                gc.setFont(new Font("arial", 22));
                gc.fillText(scanner.nextLine(), 20, 70);
                if(scanner.hasNextLine()){
                    gc.setFill(Color.DARKBLUE);
                    gc.setFont(new Font("arial", 32));
                    gc.fillText(scanner.nextLine(), 500, 40 );
                    final String site = scanner.nextLine();
                    creditCanvas.setOnMouseClicked(e->{
                        Main.hostServices.showDocument(site);
                    });
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            creditsEntries.add(creditCanvas);
        }

        creditsPane.getChildren().addAll(creditsEntries);
    }
}