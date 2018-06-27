package workWithGoods;

import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class DataValidation {

    public static boolean textFieldNotEmpty(TextField textField){
        return (textField.getText() != null && !textField.getText().isEmpty());
    }

    public static boolean checkSmalllName(TextField textField){
        return textField.getLength() > 3;
    }

    public static boolean checkPrices(TextField wholeSale, TextField retail){
        return Double.parseDouble(wholeSale.getText()) < Double.parseDouble(retail.getText());
    }

    public static boolean textFieldPriceValidation(TextField textField) {
        return textField.getText().matches("(\\d{1,4}.\\d{1,3})|(\\d{1,4})");
    }

    public static boolean textFieldQuantityValidation (TextField textField) {
        return textField.getText().matches("\\d{1,5}");
    }


    public static boolean datePickerDateValidation(DatePicker datePicker){
        return datePicker.getValue() != null && !datePicker.getValue().toString().isEmpty();
    }

    public static boolean textFieldIsSmall(TextField textField, Label label, String validationText){
        boolean result = true;
        String message = null;
        if(!checkSmalllName(textField)){
            result = false;
            message = validationText;
        }
        label.setText(message);
        return result;
    }


    public static boolean textFieldNotEmpty(TextField textField, Label label, String validationText){
        boolean result = true;
        String message = null;
        if(!textFieldNotEmpty(textField)){
            result = false;
            message = validationText;
        }
        label.setText(message);
        return result;
    }


    public static boolean checkPricesCorrect(TextField wholPrice, TextField retPrice, Label label, String validationText){
        boolean result = true;
        String message = null;
        if(!checkPrices(wholPrice, retPrice)){
            result = false;
            message = validationText;
        }
        label.setText(message);
        return result;
    }


    public static boolean textFieldQuantityValidation(TextField textField, Label label, String validationText) {
        boolean result = true;
        String message = null;
        if(!textFieldQuantityValidation(textField)){
            result = false;
            message = validationText;
        }
        label.setText(message);
        return result;
    }


    public static boolean textFieldPriceValidation(TextField textField, Label label, String validationText){
        boolean result = true;
        String message = null;
        if(!textFieldPriceValidation(textField)){
            result = false;
            message = validationText;
        }
        label.setText(message);
        return result;

    }


    public static boolean textFieldDateValidation(DatePicker datePicker, Label label, String validationText){
        boolean result = true;
        String message = null;
        if(!datePickerDateValidation(datePicker)){
            result = false;
            message = validationText;
        }
        label.setText(message);
        return result;

    }











}
