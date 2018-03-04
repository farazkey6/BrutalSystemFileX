/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brutalfilesystemui;

import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;

/**
 *
 * @author ali
 */
public class Brutalfilesystemui extends Application {

    Scene scene;
    Text brt;
    Parent first;
    Parent manualr;
    Parent aboutr;
    Parent startr;
    TranslateTransition abtr;
    Processor processor;

    @Override
    public void start(Stage stage) throws Exception {
        
        
        
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        first = FXMLLoader.load(getClass().getResource("FirstPage.fxml"));
        aboutr = FXMLLoader.load(getClass().getResource("Aboutus.fxml"));
        startr = FXMLLoader.load(getClass().getResource("Statrtpage.fxml"));
        manualr = FXMLLoader.load(getClass().getResource("Manual.fxml"));

        Text text = (Text) aboutr.lookup("#abouttext");
        Rectangle rc = new Rectangle(0, 0, 300, 500);

        text.setText("Made possible by:" + "\n"
                + "Faraz Koohian 925411351" + "\n"
                + "Alireza Arabzade 911411326" + "\n"
                + "Behnoosh Tashayo " + "\n"
                + "Neda Zolaktaf" + "\n"
                + "Amir Masoud Gashmardi" + "\n"
                + "Milad Abaszade");
        text.setFont(Font.font("Borealis", 5));
        text.setFill(Color.BLACK);

        abtr = new TranslateTransition(Duration.millis(10000), text);
        abtr.setToY(-820);
        abtr.setCycleCount(abtr.INDEFINITE);
        aboutr.setClip(rc);

        Button bt = (Button) root.lookup("#bt");
        AnchorPane anc = (AnchorPane) aboutr.lookup("#anc1");
        Button start = (Button) first.lookup("#start");
        Button about = (Button) first.lookup("#about");
        Button manual = (Button) first.lookup("#manual");
        Button exit = (Button) first.lookup("#exit");
        Button importt = (Button) startr.lookup("#import");
        Button export = (Button) startr.lookup("#export");
        Button backup = (Button) startr.lookup("#backup");
        Button delete = (Button) startr.lookup("#delete");
        Button homeb = (Button) startr.lookup("#home");
        brt = (Text) root.lookup("#brt");
        bt.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                scene.setRoot(first);
            }
        });
        start.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                scene.setRoot(startr);
              processor = new Processor();
            }
        });
        anc.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                scene.setRoot(first);
                abtr.stop();
            }
        });

        about.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                scene.setRoot(aboutr);
                abtr.play();
            }
        });
        manual.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                scene.setRoot(manualr);
            }
        });
        exit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                System.exit(0);
            }
        });
        importt.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                processor.Import();
            }
        });
        delete.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                
                processor.Delete(JOptionPane.showInputDialog(null, "Enter ID to delete"));
            }
        });
        export.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                
                Record record = processor.Export(JOptionPane.showInputDialog(null, "Enter ID to export"));
                record.Export();
            }
        });

        backup.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                
                processor.Backup();
            }
        });

        homeb.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                scene.setRoot(first);
            }
        });

        scene = new Scene(root);

        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
