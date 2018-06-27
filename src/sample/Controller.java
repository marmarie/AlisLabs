package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {


    @FXML
    private Button button;

    @FXML
    private TextField firstString;

    @FXML
    private TextField secondString;

    @FXML
    private Label result;

    @FXML
   public void getSymbols(){
        StringBuilder symbols = new StringBuilder();
            for( String symbol : firstString.getText().split("")){
            if(!secondString.getText().contains(symbol))
                symbols.append(symbol);
            }
            if (symbols.toString().equals("")) {
                symbols.append( "There are no symbols that aren't repeated ");}
      result.setText(symbols.toString());
    }
}
