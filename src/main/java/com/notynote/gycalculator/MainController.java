package com.notynote.gycalculator;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    Text sumText;
    @FXML
    Text currentSum;
    @FXML
    Button zero;
    @FXML
    Button one;
    @FXML
    Button two;
    @FXML
    Button three;
    @FXML
    Button four;
    @FXML
    Button five;
    @FXML
    Button six;
    @FXML
    Button seven;
    @FXML
    Button eight;
    @FXML
    Button nine;
    @FXML
    Button clear;
    @FXML
    Button plus;
    @FXML
    Button minus;
    @FXML
    Button equal;
    @FXML
    Button plusOne;
    @FXML
    Button minusOne;
    @FXML
    Button plusTwo;
    @FXML
    Button minusTwo;

    int sum;
    boolean firstRound;

    ArrayList<String> calculatingText = new ArrayList<>();
    ArrayList<Integer> calculatingNumber = new ArrayList<>();
    ArrayList<String> buttonType = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        sum = 2;
        this.firstRound = true;

        currentSum.setText("");
        sumText.setText(String.valueOf(sum));

        plusOne.setOnAction(actionEvent -> AmountButton(plusOne));
        minusOne.setOnAction(actionEvent -> AmountButton(minusOne));
        plusTwo.setOnAction(actionEvent -> AmountButton(plusTwo));
        minusTwo.setOnAction(actionEvent -> AmountButton(minusTwo));

        one.setOnAction(actionEvent -> NumberPress(one));
        two.setOnAction(actionEvent -> NumberPress(two));
        three.setOnAction(actionEvent -> NumberPress(three));
        four.setOnAction(actionEvent -> NumberPress(four));
        five.setOnAction(actionEvent -> NumberPress(five));
        six.setOnAction(actionEvent -> NumberPress(six));
        seven.setOnAction(actionEvent -> NumberPress(seven));
        eight.setOnAction(actionEvent -> NumberPress(eight));
        nine.setOnAction(actionEvent -> NumberPress(nine));
        zero.setOnAction(actionEvent -> NumberPress(zero));

        plus.setOnAction(actionEvent -> ManipulatorPress(plus));
        minus.setOnAction(actionEvent -> ManipulatorPress(minus));

        equal.setOnAction(actionEvent -> EqualPress(equal));

    }

    public void EqualPress(Button button) {

        boolean manipulate = false;
        boolean plus = false;
        boolean minus = false;
        Integer sum = 0;

        for (String s: calculatingText) {

            if (manipulate) {
                if(plus) {
                    sum = calculatingNumber.get(0) + Integer.parseInt(s);
                    calculatingNumber.clear();
                } else if (minus) {

                    sum = calculatingNumber.get(0) - Integer.parseInt(s);
                    calculatingNumber.clear();
                }


                plus = false;
                manipulate = false;
                minus = false;
            }

            if (s.equalsIgnoreCase("+")) {
                manipulate = true;
                plus = true;
            } else if (s.equalsIgnoreCase("-")) {
                manipulate = true;
                minus = true;
            } else {
                calculatingNumber.add(Integer.valueOf(s));
            }

        }

        if (sum > 10) {
            sum = 10;
            currentSum.setText("จี้เต็ม 10 นะจ๊ะ");
        } else if (sum < 0) {
            sum = 0;
            currentSum.setText("ตำสุด 0 นะจ๊ะ");
        } else {
            currentSum.setText("");
        }

        calculatingNumber.clear();
        calculatingText.clear();
        buttonType.clear();
        firstRound = false;
        sumText.setText(sum.toString());

    }

    public void ManipulatorPress(Button button) {

        if (buttonType.isEmpty()) {
            calculatingText.add(sumText.getText());
            if (button.getText().equalsIgnoreCase("+")) {
                buttonType.add("Plus");
                calculatingText.add(button.getText());
            } else {
                buttonType.add("Minus");
                calculatingText.add(button.getText());
            }

        } else {
            if (buttonType.get(buttonType.size()-1).equalsIgnoreCase("Plus")) {
                if (button.getText().equalsIgnoreCase("-")) {
                    buttonType.set(buttonType.size()-1,"Minus");
                    calculatingText.set(calculatingText.size()-1,button.getText());
                }
            } else if (buttonType.get(buttonType.size()-1).equalsIgnoreCase("Minus")) {
                if (button.getText().equalsIgnoreCase("+")) {
                    buttonType.set(buttonType.size()-1,"Plus");
                    calculatingText.set(calculatingText.size()-1,button.getText());
                }
            } else {
                //number
                //calculatingNumber.add(Integer.valueOf(button.getText()));
            }
        }


        StringBuilder sb = new StringBuilder();
        for (String s : calculatingText) {
            sb.append(s);
        }

        currentSum.setText(sb.toString());



    }

    public void NumberPress(Button button) {

        if (firstRound && buttonType.isEmpty()) {
            currentSum.setText("ตาแรกนะใช้ + กับ - เอาสิจ๊ะ");
            return;
        }

        if (buttonType.isEmpty()) {
            currentSum.setText("ยังไม่ได้กด + หรือลบ - เลยนะเรา");
            return;
        }

        if (buttonType.get(buttonType.size()-1).equalsIgnoreCase("Number")) {
            String newNumber = calculatingText.get(calculatingText.size()-1)+button.getText();
            calculatingText.remove(calculatingText.size()-1);
            calculatingText.add(newNumber);
            //calculatingNumber.set(calculatingNumber.size()-1, Integer.valueOf(calculatingText.get(calculatingText.size()-1)));
        } else {
            buttonType.add("Number");
            //calculatingNumber.add(Integer.valueOf(button.getText()));
            calculatingText.add(button.getText());
        }

        StringBuilder sb = new StringBuilder();
        for (String s : calculatingText) {
            sb.append(s);
        }

        currentSum.setText(sb.toString());

    }

    public void ClearButton() {
        sum = 2;
        this.firstRound = true;
        this.calculatingNumber.clear();
        this.calculatingText.clear();
        this.buttonType.clear();

        currentSum.setText("");
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
        currentSum.setText("");
        sumText.setText(String.valueOf(sum));
    }



}
