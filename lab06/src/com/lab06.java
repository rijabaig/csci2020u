package com.lab06;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.IOException;

public class lab06 extends Application {
    private static double[] avgHousingPricesByYear = {
            247381.0, 264171.4, 287715.3, 294736.1,
            308431.4, 322635.9, 340253.0, 363153.7
    };
    private static double[] avgCommercialPricesByYear = {
            1121585.3, 1219479.5, 1246354.2, 1295364.8,
            1335932.6, 1472362.0, 1583521.9, 1613246.3
    };
    private static String[] ageGroups = {
            "18-25", "26-35", "36-45", "46-55", "56-65", "65+"
    };
    private static int[] purchasesByAgeGroup = {
            648, 1021, 2453, 3173, 1868, 2247
    };
    private static Color[] pieColours = {
            Color.AQUA, Color.GOLD, Color.DARKORANGE,
            Color.DARKSALMON, Color.LAWNGREEN, Color.PLUM
    };

    @Override
    public void start(Stage primaryStage) throws IOException {
        // title and labeling axises
        primaryStage.setTitle("lab06");
        CategoryAxis x_Axis = new CategoryAxis();
        x_Axis.setLabel("Years");
        NumberAxis y_Axis = new NumberAxis();
        y_Axis.setLabel("Prices");
        // creating bar chart
        BarChart<String, Number> barChart = new BarChart<>(x_Axis, y_Axis);

        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.getData().add(new XYChart.Data<>("2015", avgHousingPricesByYear[0]));
        series1.getData().add(new XYChart.Data<>("2016", avgHousingPricesByYear[1]));
        series1.getData().add(new XYChart.Data<>("2017", avgHousingPricesByYear[2]));
        series1.getData().add(new XYChart.Data<>("2018", avgHousingPricesByYear[3]));
        series1.getData().add(new XYChart.Data<>("2019", avgHousingPricesByYear[4]));
        series1.getData().add(new XYChart.Data<>("2020", avgHousingPricesByYear[5]));
        series1.getData().add(new XYChart.Data<>("2021", avgHousingPricesByYear[6]));
        series1.getData().add(new XYChart.Data<>("2022", avgHousingPricesByYear[7]));
        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        series2.getData().add(new XYChart.Data<>("2015", avgCommercialPricesByYear[0]));
        series2.getData().add(new XYChart.Data<>("2016", avgCommercialPricesByYear[1]));
        series2.getData().add(new XYChart.Data<>("2017", avgCommercialPricesByYear[2]));
        series2.getData().add(new XYChart.Data<>("2018", avgCommercialPricesByYear[3]));
        series2.getData().add(new XYChart.Data<>("2019", avgCommercialPricesByYear[4]));
        series2.getData().add(new XYChart.Data<>("2020", avgCommercialPricesByYear[5]));
        series2.getData().add(new XYChart.Data<>("2021", avgCommercialPricesByYear[6]));
        series2.getData().add(new XYChart.Data<>("2022", avgCommercialPricesByYear[7]));
        // adding data
        barChart.getData().addAll(series1, series2);
        barChart.setTitle("Housing/Commercial Prices Yearly");

        // pie chart

        primaryStage.setWidth(1500);
        primaryStage.setHeight(600);
        ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList(
                new PieChart.Data(ageGroups[0], purchasesByAgeGroup[0]),
                new PieChart.Data(ageGroups[1], purchasesByAgeGroup[1]),
                new PieChart.Data(ageGroups[2], purchasesByAgeGroup[2]),
                new PieChart.Data(ageGroups[3], purchasesByAgeGroup[3]),
                new PieChart.Data(ageGroups[4], purchasesByAgeGroup[4]),
                new PieChart.Data(ageGroups[5], purchasesByAgeGroup[5]));
        final PieChart chart = new PieChart(pieData);
        chart.setLegendVisible(false);
        barChart.setLegendVisible(false);
        int j = 0;
        for (PieChart.Data data : pieData) {
            data.getNode().setStyle(
                    "-fx-pie-color: " + pieColours[j % pieColours.length] + ";");
            j++;
        }
        Label lb = new Label("Here is my Graph and Pie chart "); // label
        Label lb2 = new Label("Rija Baig: 100746674");
        lb.setTranslateX(10);
        lb.setTranslateY(200);
        lb2.setFont(Font.font("Cambria", 32));
        lb2.setTranslateX(10);
        HBox hbox = new HBox(barChart, chart, lb, lb2);

        Scene scene = new Scene(hbox);
        hbox.setBackground(new Background(new BackgroundFill(Color.PINK, null, null)));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
