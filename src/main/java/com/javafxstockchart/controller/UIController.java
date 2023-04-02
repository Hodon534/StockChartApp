package com.javafxstockchart.controller;

import com.javafxstockchart.model.OracleFromYamaha;
import com.javafxstockchart.model.Pojo.TimeSeries.PojoTimeSeries;
import com.javafxstockchart.model.Pojo.TimeSeries.Value;
import com.javafxstockchart.model.companies.Company;
import com.javafxstockchart.service.DatabaseConnection;
import com.javafxstockchart.service.GetTimeSeriesJSON;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
public class UIController implements Initializable {
    @FXML
    private LineChart<String, Double> lineChart;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private ChoiceBox<String> intervalChoiceBox;
    @FXML
    private ChoiceBox<String> periodChoiceBox;
    @FXML
    private GridPane gridPane;
    @FXML
    private Label labelRateOfReturn;
    @FXML
    private Label labelYamahaSays;
    @FXML
    private TextField textField;
    @FXML
    private Button goButton;
    @FXML
    private TableColumn<Company, String> companyNameColumn;
    @FXML
    private TableColumn<Company, String> companySymbolColumn;
    @FXML
    private TableView<Company> companyTableView;
    @FXML
    private Label yamahaDecisionLabel;
    private final ObservableList<Company> observableList = FXCollections.observableArrayList();
    private PojoTimeSeries chartData;
    private final String[] spanBetweenRecords = {"1day", "1week", "1month"};
    private final String[] periodOfQueries = {"Max", "10 years", "5 years", "3 years", "1 year", "YTD"};

    @Autowired
    OracleFromYamaha oracleFromYamaha;

    public UIController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        intervalChoiceBoxInitialization();
        /*periodChoiceBoxInitialization();*/
        lineChartInitialization();
        tableViewInitialization();
        /*companyTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Company>() {
            @Override
            public void changed(ObservableValue<? extends Company> observableValue, Company company, Company t1) {
                String clickedCompanySymbol = companyTableView.getSelectionModel().getSelectedItem().getSymbol();
                textField.setText(clickedCompanySymbol);
            }
        });*/
    }

    private void tableViewInitialization(){
        String companyQuery = "SELECT symbol, name FROM tickers";
        try {
            //make a db connection
            Connection conn = DatabaseConnection.getConnection();
            Statement statement = conn.createStatement();
            //get data from db
            ResultSet queryOutput = statement.executeQuery(companyQuery);

            while (queryOutput.next()) {
                //add values to observableList one by one -> Company values (symbol & name)
                observableList.add(new Company(queryOutput.getString("symbol"), queryOutput.getString("name")));
            }
            // initialize columns on TableView
            companySymbolColumn.setCellValueFactory(new PropertyValueFactory<>("symbol"));
            companyNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            // put Companies on TableView via observableList
            companyTableView.setItems(observableList);
            // create FilteredList that takes ObservableList as it's argument
            FilteredList<Company> filteredList = new FilteredList<>(observableList, a -> true);

            textField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredList.setPredicate(Company -> {
                    if (newValue.isEmpty() || newValue.isBlank()) {
                        return true; // if textField is empty  = nothing happens
                    }
                    // value from TextField that has just been typed
                    String searchKeyword = newValue.toLowerCase();

                    if(Company.getName().toLowerCase().contains(searchKeyword)) {
                        return true; // found matching value in "name" column
                    } else if (Company.getSymbol().toLowerCase().contains(searchKeyword)) {
                        return true; // found matching value in "symbol" column
                    } else {
                        return false; // nothing's matching
                    }
                });
            });
            SortedList<Company> sortedList = new SortedList<>(filteredList);
            sortedList.comparatorProperty().bind(companyTableView.comparatorProperty());
            companyTableView.setItems(sortedList);
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    private void intervalChoiceBoxInitialization() {
        intervalChoiceBox.getItems().addAll(spanBetweenRecords);
        intervalChoiceBox.setValue("1week");
    }

    private void periodChoiceBoxInitialization() {
        periodChoiceBox.getItems().addAll(periodOfQueries);
        periodChoiceBox.setValue("Max");
    }

    private void lineChartInitialization() {
        lineChart.setCreateSymbols(false);
        lineChart.setLegendVisible(false);
    }

    /**
     *Symbol
     */
    private String getTextFieldValue() {
        return textField.getText();
    }

    /**
     * Interval -> "Daily/Weekly"/"Monthly"
     */
    private String getIntervalValue() {
        return intervalChoiceBox.getValue();
    }

    @FXML
    public void goButtonAction() throws IOException {
        resetChartDataAndLineChart();
        setChartData();
        setLineChartTitle();
        setAxisLineChart();
        setYamahaDecisionLabel();
    }

    public void setChartData() throws IOException {
        chartData = GetTimeSeriesJSON.requestData(getIntervalValue(), getTextFieldValue());
    }

    public void setLineChartTitle() {
        lineChart.setTitle(chartData.getMetaData().getSymbol());
    }

    public XYChart.Series setXYChart(){
        XYChart.Series series = new XYChart.Series();
        for (int i = chartData.getValues().length-1; i >= 0; i--) {
            series.getData().add(new XYChart.Data(chartData.getValues()[i].getDateTime(), chartData.getValues()[i].getClose()));
            }
        return series;
        }

    public void setAxisLineChart(){

        Double minValue = 0.0;
        Value biggestValue = Arrays.stream(chartData.getValues()).max(Comparator.comparingDouble(Value::getClose)).orElseThrow(NoSuchElementException::new);
        Double maxValue = biggestValue.getClose() + (biggestValue.getClose()*0.15);
        //int ySeparator = (int) (maxValue/7);

        CategoryAxis xAxis = new CategoryAxis();
        //NumberAxis yAxis = new NumberAxis(minValue, maxValue, ySeparator);
        //xAxis.setTickLabelsVisible(false);
        lineChart.getXAxis().setTickLabelsVisible(false);
        lineChart.getXAxis().setOpacity(0);
        lineChart.getData().add(setXYChart());
    }
    public void resetChartDataAndLineChart(){
        chartData = new PojoTimeSeries();
        lineChart.getData().clear();
    }

    public void setYamahaDecisionLabel(){
        changeTextValueOfYamahaDecisionLabel();
        changeColorOfYamahaDecisionLabel();
        }
    public void changeTextValueOfYamahaDecisionLabel() {
        oracleFromYamaha.setDecisionWhetherBuyOrSell();
        if (oracleFromYamaha.toString().equals("BUY")) {
            yamahaDecisionLabel.setText(oracleFromYamaha.toString());
        } else if (oracleFromYamaha.toString().equals("SELL")) {
            yamahaDecisionLabel.setText(oracleFromYamaha.toString());
        }
    }

    public void changeColorOfYamahaDecisionLabel() {
            if (yamahaDecisionLabel.getText().equals("BUY")) {
                yamahaDecisionLabel.setTextFill(Color.GREEN);
            } else if (yamahaDecisionLabel.getText().equals("SELL")) {
                yamahaDecisionLabel.setTextFill(Color.RED);
            }
    }

    public void setLineChartDuration(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd");
        formatter = formatter.withLocale(Locale.GERMANY);  // Locale specifies human language for translating, and cultural norms for lowercase/uppercase and abbreviations and such. Example: Locale.US or Locale.CANADA_FRENCH
        LocalDate date = LocalDate.parse(chartData.getValues()[0].getDateTime(), formatter);
        //LocalDate startDate = chartData.getValues()[0].getDateTime();
    }
/*
    private double rateOfReturn(){
        double startValue = chartData.getValues()[0].getOpen();
        double endValue = chartData.getValues()[chartData.getValues().length-1].getClose();

    }*/
}
