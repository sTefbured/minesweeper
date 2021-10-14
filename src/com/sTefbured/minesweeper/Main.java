package com.sTefbured.minesweeper;

import com.sTefbured.minesweeper.ui.playfield.PlayField;
import javafx.application.Application;
import javafx.embed.swing.SwingNode;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main extends Application {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        SwingNode node = new SwingNode();
        node.setContent(new PlayField(16, 30, 99));
        Scene scene = new Scene(new BorderPane(node));
        primaryStage.setScene(scene);
        primaryStage.setTitle(Constants.APPLICATION_TITLE);
        primaryStage.sizeToScene();
        primaryStage.show();
    }
}
