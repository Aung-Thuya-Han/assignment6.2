package view;

import controller.CurrencyConverterController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Currency;
import model.CurrencyModel;

public class CurrencyConverterView extends Application {
    private final TextField amountField = new TextField();
    private final TextField resultField = new TextField();
    private final ChoiceBox<Currency> sourceChoice = new ChoiceBox<>();
    private final ChoiceBox<Currency> targetChoice = new ChoiceBox<>();
    private final Label errorLabel = new Label();

    @Override
    public void start(Stage stage) {
        CurrencyModel model = new CurrencyModel();
        CurrencyConverterController controller = new CurrencyConverterController(this);

        sourceChoice.getItems().addAll(model.getCurrencies());
        targetChoice.getItems().addAll(model.getCurrencies());
        sourceChoice.getSelectionModel().select(0);
        targetChoice.getSelectionModel().select(1);

        Label instructions = new Label("Enter an amount, choose the currencies, and click Convert.");
        instructions.setWrapText(true);

        Label amountLabel = new Label("Amount");
        amountLabel.setLabelFor(amountField);
        amountField.setPromptText("For example, 12.50");
        VBox amountBox = new VBox(4, amountLabel, amountField);

        Label sourceLabel = new Label("From currency");
        sourceLabel.setLabelFor(sourceChoice);
        sourceChoice.setMaxWidth(Double.MAX_VALUE);
        VBox sourceBox = new VBox(4, sourceLabel, sourceChoice);

        Label targetLabel = new Label("To currency");
        targetLabel.setLabelFor(targetChoice);
        targetChoice.setMaxWidth(Double.MAX_VALUE);
        VBox targetBox = new VBox(4, targetLabel, targetChoice);

        HBox currencyRow = new HBox(12, sourceBox, targetBox);
        HBox.setHgrow(sourceBox, Priority.ALWAYS);
        HBox.setHgrow(targetBox, Priority.ALWAYS);

        Button convertButton = new Button("Convert");
        convertButton.setOnAction(event -> controller.convert());

        Label resultLabel = new Label("Converted amount");
        resultLabel.setLabelFor(resultField);
        resultField.setEditable(false);
        VBox resultBox = new VBox(4, resultLabel, resultField);

        errorLabel.getStyleClass().add("error");
        errorLabel.setWrapText(true);
        Label rateNote = new Label("Fixed ECB rates from 20 November 2026.");

        VBox layout = new VBox(12, instructions, amountBox, currencyRow,
                convertButton, resultBox, errorLabel, rateNote);
        layout.setPadding(new Insets(20));

        Scene scene = new Scene(layout, 460, 320);
        scene.getStylesheets().add("style.css");

        stage.setTitle("Currency Converter");
        stage.setScene(scene);
        stage.show();
    }

    public String getAmount() {
        return amountField.getText();
    }

    public Currency getSourceCurrency() {
        return sourceChoice.getValue();
    }

    public Currency getTargetCurrency() {
        return targetChoice.getValue();
    }

    public void showResult(String result) {
        resultField.setText(result);
        errorLabel.setText("");
    }

    public void showError(String message) {
        resultField.clear();
        errorLabel.setText(message);
    }
}
