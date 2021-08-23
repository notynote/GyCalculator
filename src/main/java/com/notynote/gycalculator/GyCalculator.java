package com.notynote.gycalculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GyCalculator extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GyCalculator.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 405, 920);
        stage.setTitle("GyCalculator by NotyNote");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}