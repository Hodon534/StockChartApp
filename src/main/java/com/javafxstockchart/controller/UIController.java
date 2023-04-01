package com.javafxstockchart.controller;

import com.javafxstockchart.model.Pojo.TimeSeries.PojoTimeSeries;
import com.javafxstockchart.model.Pojo.TimeSeries.Value;
import com.javafxstockchart.model.tickers.Company;
import com.javafxstockchart.service.DatabaseConnection;
import com.javafxstockchart.service.GetStockQuery;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
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
    private PojoTimeSeries chartData;
    private final String[] spanBetweenRecords = {"1day", "1week", "1month"};
    private final String[] periodOfQueries = {"Max", "10 years", "5 years", "3 years", "1 year", "YTD"};

    public UIController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        intervalChoiceBox.getItems().addAll(spanBetweenRecords);
        intervalChoiceBox.setValue("1week");
        periodChoiceBox.getItems().addAll(periodOfQueries);
        periodChoiceBox.setValue("Max");
        lineChart.setCreateSymbols(false);
        lineChart.setLegendVisible(false);

        String companyQuery = "SELECT symbol, name FROM tickers";
        try {
            Connection conn = DatabaseConnection.getConnection();
            Statement statement = conn.createStatement();;
            ResultSet queryOutput = statement.executeQuery(companyQuery);


        } catch(SQLException e) {
            e.printStackTrace();
        }


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
    }

    public void setChartData() throws IOException {
        chartData = GetStockQuery.requestData(getIntervalValue(), getTextFieldValue());
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
        String startDate = chartData.getValues()[0].getDateTime();
        String endDate = chartData.getValues()[chartData.getValues().length-1].getDateTime();
        Double minValue = 0.0;
        HashMap<LocalDate, Double> closingMap = new HashMap<>();
        Value biggestValue = Arrays.stream(chartData.getValues()).max(Comparator.comparingDouble(Value::getClose)).orElseThrow(NoSuchElementException::new);
        Double maxValue = biggestValue.getClose() + (biggestValue.getClose()*0.15);
        int ySeparator = (int) (maxValue/7);

        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis(minValue, maxValue, ySeparator);
        xAxis.setTickLabelsVisible(false);
        lineChart.getXAxis().setTickLabelsVisible(false);
        lineChart.getXAxis().setOpacity(0);
        lineChart.getData().add(setXYChart());
    }

    public void resetChartDataAndLineChart(){
        chartData = new PojoTimeSeries();
        lineChart.getData().clear();
    }

}
