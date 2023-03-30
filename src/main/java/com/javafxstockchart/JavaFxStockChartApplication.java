package com.javafxstockchart;

import com.javafxstockchart.service.DatabaseConnection;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Application that will show stock charts and randomly dictate whether you should buy or sell
 * Frameworks used: JavaFX, SpringBoot,SQL
 * FXML, Lombok, Jackson
 */

//todo Controller Skeleton - check how to open in Scene Builder via IntelliJ - Done

/**
 * Service - Application's logic, all changes, database connecting
 * Controller - View/UI
 * Models
 * Repository - strictly connected to models
 */

@SpringBootApplication
public class JavaFxStockChartApplication extends Application {
	/** ConfigurableApplicationContext confAppContext:
	 *  Spring context - it will be used to add all Beans (modules) to an Application
	 */
	public static ConfigurableApplicationContext confAppContext; //spring context, SpringBoot application
	private FXMLLoader fxmlLoader; //UI of JavaFX application

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		fxmlLoader.setLocation(getClass().getResource("/ui.fxml")); //Set location of FXML file
		Parent root = fxmlLoader.load();
		stage.setTitle("Market Chart App");
		Scene scene = new Scene(root, 1000, 800);
		stage.setScene(scene);
		stage.show();
	}

	@Override
	public void init() throws Exception {
		confAppContext = SpringApplication.run(JavaFxStockChartApplication.class);
		fxmlLoader = new FXMLLoader();
		fxmlLoader.setControllerFactory(confAppContext::getBean);
	}

	@Override
	public void stop() throws Exception {
		confAppContext.stop();
		Platform.exit();
	}
}
