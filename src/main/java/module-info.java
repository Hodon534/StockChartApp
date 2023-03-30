module stockchart {
/**
 * File needed to run JavaFX + SpringBoot app
 */
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires spring.boot;
    requires spring.boot.starter;
    requires spring.boot.autoconfigure;
    requires spring.context;
    requires spring.beans;
    requires spring.core;
    requires java.sql;
    requires spring.data.jpa;
    requires spring.web;
    requires javafx.base;
    requires lombok;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires org.json;
    requires com.google.gson;

    exports com.javafxstockchart;
    exports com.javafxstockchart.service to spring.beans;
    opens com.javafxstockchart to spring.core, javafx.graphics;
    exports com.javafxstockchart.controller;
    opens com.javafxstockchart.controller to javafx.graphics, spring.core, javafx.fxml;
    exports com.javafxstockchart.model.api;
    opens com.javafxstockchart.model.api to javafx.graphics, spring.core;
    exports com.javafxstockchart.model.Pojo.TimeSeries;
    opens com.javafxstockchart.model.Pojo.TimeSeries to javafx.graphics, spring.core, com.fasterxml.jackson.databind;
    opens com.javafxstockchart.model.tickers;
    exports com.javafxstockchart.model.tickers to spring.beans, javafx.base, javafx.fxml, javafx.graphics;
}