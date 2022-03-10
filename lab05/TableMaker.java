package com.Lab05;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.Group;
import javafx.scene.text.Font;
import java.io.IOException;

public class TableMaker extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(new Group());

        stage.setTitle("Rija's Lab 05");
        stage.setWidth(1250);
        stage.setHeight(500);
        final Label lb = new Label("Marks Table");
        lb.setFont(new Font("Arial", 20));
        TableColumn<StudentRecord, Float> assignmentCol = new TableColumn<>("Assignments");
        assignmentCol.setMinWidth(200);

        assignmentCol.setCellValueFactory(new PropertyValueFactory<StudentRecord, Float>("assignment"));

        TableColumn<StudentRecord, String> SIDCol = new TableColumn<>("SID");
        SIDCol.setMinWidth(200);

        SIDCol.setCellValueFactory(new PropertyValueFactory<StudentRecord, String>("id"));

        TableColumn<StudentRecord, Float> midtermCol = new TableColumn<>("Midterm");
        midtermCol.setMinWidth(200);

        midtermCol.setCellValueFactory(new PropertyValueFactory<StudentRecord, Float>("midterm"));
        TableColumn<StudentRecord, Character> letterCol = new TableColumn<>("Letter Grade");
        letterCol.setMinWidth(200);

        letterCol.setCellValueFactory(new PropertyValueFactory<StudentRecord, Character>("letterGrade"));

        TableView<StudentRecord> table = new TableView<>();
        TableColumn<StudentRecord, Float> finalCol = new TableColumn<>("Final Exam");
        finalCol.setMinWidth(200);

        finalCol.setCellValueFactory(new PropertyValueFactory<StudentRecord, Float>("exam"));

        TableColumn<StudentRecord, Float> totalCol = new TableColumn<>("Final Grade");
        totalCol.setMinWidth(200);
        totalCol.setCellValueFactory(new PropertyValueFactory<StudentRecord, Float>("total"));

        table.setItems(DataSource.getAllMarks());
        table.getColumns().addAll(SIDCol, assignmentCol, midtermCol,
                finalCol, totalCol, letterCol);

        VBox vbox = new VBox();
        vbox.setSpacing(5);

        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.getChildren().addAll(lb, table);
        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
