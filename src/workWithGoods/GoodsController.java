package workWithGoods;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.util.Callback;

import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.util.ResourceBundle;

public class GoodsController implements Initializable {

    ObservableList<Product> data = FXCollections.observableArrayList(
          new Laptop("MacBook Pro", "Apple", 1200.00, 1350.00, LocalDate.of(2030, Month.APRIL, 19), 10),
          new Laptop("MacBook Air", "Apple", 900.00, 950.00, LocalDate.of(2029, Month.AUGUST, 10), 5),
          new Laptop("Lenovo LapTop", "Lenovo", 500.00, 650.00, LocalDate.of(2030, Month.JANUARY, 1), 100),
          new Laptop("HP Pavillion", "HP", 800.00, 950.00, LocalDate.of(2040, Month.SEPTEMBER, 23), 59)
    );



    FilteredList<Product> filteredData = new FilteredList<Product>(data, product -> true);


    private IntegerProperty index = new SimpleIntegerProperty();

    @FXML
    TableView<Product> tableView;

    @FXML
    TableColumn<Product, String> iName;

    @FXML
    TableColumn<Product, Integer> iQuantity;

    @FXML
    TableColumn<Product, String> iManufacturer;

    @FXML
    TableColumn<Product, Double> iWholPrice;

    @FXML
    TableColumn<Product, Double> iRetailPrice;

    @FXML
    TableColumn<Product, LocalDate> iExpDate;


    @FXML
    TextField nameInput;

    @FXML
    TextField manufacturerInput;

    @FXML
    TextField quantityInput;

    @FXML
    TextField wholPriceInput;

    @FXML
    TextField retPriceInput;

    @FXML
    DatePicker expDateInput;

    @FXML
    Button saveButton;

    @FXML
    Button deleteButton;

    @FXML
    Label nameLabel;

    @FXML
    Label manufacturerLabel;

    @FXML
    Label wholPriceLabel;

    @FXML
    Label retPriceLabel;

    @FXML
    Label expiryDateLabel;

    @FXML
    Label quantityLabel;

    @FXML
    Label smallNameLabel;

    @FXML
    TextField filterField;

    @FXML
    Label searchMAnufacturer;





    @Override
    public void initialize(URL location, ResourceBundle resources) {

        index.set(-1);

        iName.setCellValueFactory(new PropertyValueFactory<>("name"));
        iManufacturer.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));
        iQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        iWholPrice.setCellValueFactory(new PropertyValueFactory<>("wholesalePrice"));
        iRetailPrice.setCellValueFactory(new PropertyValueFactory<>("retailPrice"));
        iExpDate.setCellValueFactory(new PropertyValueFactory<>("expiryDate"));
        tableView.setEditable(true);
        tableView.setItems(data);


        tableView.getSelectionModel().selectedItemProperty().addListener((ChangeListener<Object>) (observable, oldValue, newValue) -> index.set(data.indexOf(newValue)));
      //  setTableEditable();
        iName.setOnEditCommit(event -> ( event.getTableView().getItems().get(event.getTablePosition().getRow())).setName(event.getNewValue()));



        filterField.textProperty().addListener(((observable, oldValue, newValue) -> filteredData.setPredicate(product -> {
            if(newValue == null || newValue.isEmpty()){
                return true;
            }
            String lowerCaseFilter = newValue.toLowerCase();

            //filter by manufacturer
            if(product.getManufacturer().toLowerCase().startsWith(lowerCaseFilter)){
                return true;
            }
            return false;

        })));


        SortedList<Product> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableView.comparatorProperty());

        tableView.setItems(sortedData);


    }


    public void onAddProduct(ActionEvent event){

        boolean nameV = DataValidation.textFieldNotEmpty(nameInput, nameLabel, "Name is required");
        boolean manufacturerV = DataValidation.textFieldNotEmpty(manufacturerInput, manufacturerLabel, "Manufacturer is required");
        boolean wholPriceV = DataValidation.textFieldPriceValidation(wholPriceInput, wholPriceLabel, "Enter correct price");
        boolean retPriceV = DataValidation.textFieldPriceValidation(retPriceInput, retPriceLabel, "Enter correct price");
        boolean quantityV = DataValidation.textFieldQuantityValidation(quantityInput, quantityLabel, "Numbers only");
        boolean expDateV = DataValidation.textFieldDateValidation(expDateInput, expiryDateLabel, "Provide a date!");


        boolean smallNameV = true;
        boolean correctPrices = true;
        if(nameV)
        smallNameV = DataValidation.textFieldIsSmall(nameInput, nameLabel, "Small name!");

        if(wholPriceV && retPriceV)
            correctPrices = DataValidation.checkPricesCorrect(wholPriceInput, retPriceInput, wholPriceLabel, "Whol > Retail!");


        if(nameV && manufacturerV && wholPriceV && retPriceV && quantityV && expDateV && smallNameV && correctPrices) {

            Product product = new Laptop(nameInput.getText(), manufacturerInput.getText(), Double.parseDouble(wholPriceInput.getText())
                    , Double.parseDouble(retPriceInput.getText()), expDateInput.getValue(), Integer.parseInt(quantityInput.getText()));

            data.add(product);

            clearForm();
        }
    }


    public void onDeleteProduct(ActionEvent event){
        int i = index.get();
        if(i > -1) {
            data.remove(i);
            tableView.getSelectionModel().clearSelection();
        }
    }

    public void clearForm(){

        nameInput.clear();
        manufacturerInput.clear();
        quantityInput.clear();
        wholPriceInput.clear();
        retPriceInput.clear();

    }

    private void setTableEditable() {
        tableView.setEditable(true);
        // allows the individual cells to be selected
        tableView.getSelectionModel().cellSelectionEnabledProperty().set(true);
        // when character or numbers pressed it will start edit in editableView
        // fields
        tableView.setOnKeyPressed(event -> {
            if (event.getCode().isLetterKey() || event.getCode().isDigitKey()) {
                editFocusedCell();
            } else if (event.getCode() == KeyCode.RIGHT
                    || event.getCode() == KeyCode.TAB) {
                tableView.getSelectionModel().selectNext();
                event.consume();
            } else if (event.getCode() == KeyCode.LEFT) {
                // work around due to
                // tableViewView.getSelectionModel().selectPrevious() due to a bug
                // stopping it from working on
                // the first column in the last row of the tableView
                selectPrevious();
                event.consume();
            }
        });
    }

    @SuppressWarnings("unchecked")
    private void editFocusedCell() {
        final TablePosition<Product, ?> focusedCell = tableView
                .focusModelProperty().get().focusedCellProperty().get();
        tableView.edit(focusedCell.getRow(), focusedCell.getTableColumn());
    }

    @SuppressWarnings("unchecked")
    private void selectPrevious() {
        if (tableView.getSelectionModel().isCellSelectionEnabled()) {
            // in cell selection mode, we have to wrap around, going from
            // right-to-left, and then wrapping to the end of the previous line
            TablePosition<Product, ?> pos = tableView.getFocusModel()
                    .getFocusedCell();
            if (pos.getColumn() - 1 >= 0) {
                // go to previous row
                tableView.getSelectionModel().select(pos.getRow(),
                        getTableColumn(pos.getTableColumn(), -1));
            } else if (pos.getRow() < tableView.getItems().size()) {
                // wrap to end of previous row
                tableView.getSelectionModel().select(pos.getRow() - 1,
                        tableView.getVisibleLeafColumn(
                                tableView.getVisibleLeafColumns().size() - 1));
            }
        } else {
            int focusIndex = tableView.getFocusModel().getFocusedIndex();
            if (focusIndex == -1) {
                tableView.getSelectionModel().select(tableView.getItems().size() - 1);
            } else if (focusIndex > 0) {
                tableView.getSelectionModel().select(focusIndex - 1);
            }
        }
    }

    private TableColumn<Product, ?> getTableColumn(
            final TableColumn<Product, ?> column, int offset) {
        int columnIndex = tableView.getVisibleLeafIndex(column);
        int newColumnIndex = columnIndex + offset;
        return tableView.getVisibleLeafColumn(newColumnIndex);
    }



}
