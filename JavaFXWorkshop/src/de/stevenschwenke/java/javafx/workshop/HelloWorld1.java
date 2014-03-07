package de.stevenschwenke.java.javafx.workshop;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class HelloWorld1 extends Application implements Initializable {

	/** UI components */
	@FXML
	private PieChart pieChart;

	@FXML
	private Label potatoesLabel;

	@FXML
	private Label applesLabel;

	@FXML
	private Label chipsLabel;

	/** Properties to hold the amount of stuff we are going to buy */
	private IntegerProperty amountOfPotatoes = new SimpleIntegerProperty();
	private IntegerProperty amountOfApples = new SimpleIntegerProperty();
	private IntegerProperty amountOfChips = new SimpleIntegerProperty();

	private Data junk;

	private Data fruits;

	private Data vegetables;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("helloWorld1.fxml"));
		// TODO Was ist eine Stage, was eine Scene? - Abbildungen!
		// TODO Sources einbinden!
		// TODO Workspace als zip auf 2 USB-Sticks rumgehen lassen - gleich mit
		// fxml startet Scene Builder und allen Beispielen. Sollte aber nicht
		// dieser Workspace sein, da hier Zugangsdaten für Github stehen!
		Scene scene = new Scene(root, 800, 600);

		stage.setTitle("Hello World 1");
		stage.setScene(scene);
		stage.show();

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		fruits = new PieChart.Data("Fruits", 0);
		vegetables = new PieChart.Data("Vegetables", 0);
		junk = new PieChart.Data("Junkfood", 0);

		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(fruits, vegetables, junk);
		pieChart.setData(pieChartData);

		// Listener for the properties to react on changes
		amountOfPotatoes.addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				potatoesLabel.textProperty().set(newValue.toString());
				vegetables.setPieValue(vegetables.getPieValue() + (newValue.intValue() - oldValue.intValue()));
			}
		});

		amountOfApples.addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				applesLabel.textProperty().set(newValue.toString());
				fruits.setPieValue(fruits.getPieValue() + (newValue.intValue() - oldValue.intValue()));
			}
		});

		amountOfChips.addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				chipsLabel.textProperty().set(newValue.toString());
				junk.setPieValue(junk.getPieValue() + (newValue.intValue() - oldValue.intValue()));
			}
		});

	}

	@FXML
	private void addOneToApples() {
		// Update not possible on this observable list - only with implementing
		// a callback while instantiating the list!
		// fruits.pieValueProperty().add(1L);

		amountOfApples.set(amountOfApples.get() + 1);
	}

	@FXML
	private void subtractOneFromApples() {
		if (amountOfApples.greaterThan(0).get())
			amountOfApples.set(amountOfApples.get() - 1);
	}

	@FXML
	private void addOneToPotatoes() {
		amountOfPotatoes.set(amountOfPotatoes.get() + 1);
	}

	@FXML
	private void subtractOneFromPotatoes() {
		// TODO Diese Logik mit den Properties abbilden!
		if (amountOfPotatoes.greaterThan(0).get()) {
			amountOfPotatoes.set(amountOfPotatoes.get() - 1);
		}
	}

	@FXML
	private void addOneToChips() {
		amountOfChips.set(amountOfChips.get() + 1);
	}

	@FXML
	private void subtractOneFromChips() {
		if (amountOfChips.greaterThan(0).get()) {
			amountOfChips.set(amountOfChips.get() - 1);
		}
	}
}
