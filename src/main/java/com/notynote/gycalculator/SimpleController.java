package com.notynote.gycalculator;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SimpleController implements Initializable {

    @FXML
    Pane pane;
    @FXML
    Text sumText;
    @FXML
    Text energyText;
    @FXML
    Button clear;
    @FXML
    Button plusOne;
    @FXML
    Button minusOne;
    @FXML
    Button plusTwo;
    @FXML
    Button minusTwo;
    @FXML
    Button fullMode;

    int sum;
    boolean firstRound;

    ArrayList<String> calculatingText = new ArrayList<>();
    ArrayList<Integer> calculatingNumber = new ArrayList<>();
    ArrayList<String> buttonType = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        sum = 3;
        this.firstRound = true;

        energyText.setText("Energies");
        sumText.setText(String.valueOf(sum));

        plusOne.setOnAction(actionEvent -> AmountButton(plusOne));
        minusOne.setOnAction(actionEvent -> AmountButton(minusOne));
        plusTwo.setOnAction(actionEvent -> AmountButton(plusTwo));
        minusTwo.setOnAction(actionEvent -> AmountButton(minusTwo));

    }

    @FXML
    public void SwitchMode() throws IOException {
        AnchorPane mainMode = FXMLLoader.load(getClass().getResource("main-view.fxml"));
        pane.getChildren().setAll(mainMode);
    }

    public void ClearButton() {
        sum = 3;
        this.firstRound = true;
        this.calculatingNumber.clear();
        this.calculatingText.clear();
        this.buttonType.clear();

        energyText.setText("Energies");
        sumText.setText(String.valueOf(sum));
    }

    public void AmountButton(Button button) {
        switch (button.getId()) {
            case "plusOne":
                this.sum++;
                break;
            case "plusTwo":
                this.sum+=2;
                break;
            case "minusOne":
                this.sum--;
                break;
            case "minusTwo":
                this.sum-=2;
                break;
        }

        if (sum > 10) {
            sum = 10;
        }

        if (sum < 0) {
            sum = 0;
        }

        firstRound = false;
        if (sum > 1) {
            energyText.setText("Energies");
        } else {
            energyText.setText("Energy");
        }
        sumText.setText(String.valueOf(sum));
    }



}
