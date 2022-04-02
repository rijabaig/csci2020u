package com.lab08;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javafx.scene.control.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import java.nio.file.StandardOpenOption;

public class TableMaker extends Application {

    public String currentIDnum;
    public Float currentAssignmentGrade;
    public String currentName = null;
    public String currentPath = null;
    public Float currentMidtermGrade;
    public Float currentFinalGrade;
    public DataSource source = new DataSource();

    public void SaveAs(FileChooser myfile, Stage stage) throws IOException {

        myfile.setInitialFileName("myfile.csv");
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(stage);
        currentName = file.getName();
        Save(fileChooser, stage);
        if (file != null) {
            try {
                Files.write(file.toPath(), currentName.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void Save(FileChooser filechooser, Stage stage) throws IOException {
        if (currentName == null) {
            SaveAs(filechooser, stage);
        }
        try {

            File file = new File(currentPath);
            PrintWriter writer = new PrintWriter(file);
            ObservableList<StudentRecord> record = source.getAllMarks();
            writer.println("SID,Assignments,Midterm,FinalExam");

            for (int i = 0; i < record.size(); i++) {
                StudentRecord student = (StudentRecord) record.get(i);
                writer.println(student.getId());
                writer.println(",");
                writer.println(Float.toString(student.getassignment()));
                writer.println(",");
                writer.println(Float.toString(student.getmidterm()));
                writer.println(",");
                writer.println(Float.toString(student.getFinalExam()));

            }
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void Load(FileChooser filee, Stage stage) throws IOException {
        BufferedReader fileReader = null;
        filee.setInitialFileName("myfile.csv");
        File file = filee.showOpenDialog(stage);
        try {

            fileReader = new BufferedReader(new FileReader(file));
            String line = "";
            fileReader.readLine();

            while ((line = fileReader.readLine()) != null) {
                String[] temp = line.split(",");
                if (temp.length > 0) {
                    source.addData(temp[0], Float.parseFloat(temp[1]),
                            Float.parseFloat(temp[2]), Float.parseFloat(temp[3]));
                }
            }
        } catch (IOException e) {

            e.printStackTrace();
        }

    }

    @Override
    public void start(Stage stage) throws IOException {

        Scene scene = new Scene(new Group());
        stage.setTitle("Rija's Lab 05");
        stage.setWidth(1250);
        stage.setHeight(800);
        stage.setTitle("CSCI2020U - Lab 8");
        final Label lb = new Label("Marks Table");
        lb.setFont(new Font("Arial", 22));
        FileChooser filepick = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("CSV Files (*.csv)", "*.csv");
        filepick.getExtensionFilters().add(extensionFilter);

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10, 10, 10, 10));

        TableColumn<StudentRecord, Float> assignmentCol = new TableColumn<>("Assignments");
        assignmentCol.setMinWidth(200);
        assignmentCol.setCellValueFactory(new PropertyValueFactory<StudentRecord, Float>("assignmentGrade"));
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
        totalCol.setCellValueFactory(new PropertyValueFactory<StudentRecord, Float>("finalMark"));
        table.setItems(DataSource.getAllMarks());
        table.getColumns().addAll(SIDCol, assignmentCol, midtermCol,
                finalCol, totalCol, letterCol);

        // Creating the menu and its event handling
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10, 10, 10, 10));
        MenuButton menu = new MenuButton("Menu");
        MenuItem newf = new MenuItem("New");
        MenuItem exit = new MenuItem("Exit");
        MenuItem open = new MenuItem("Open");
        MenuItem save = new MenuItem("Save");
        MenuItem saveAs = new MenuItem("Save As");
        newf.setOnAction(event -> table.getItems().clear());
        open.setOnAction(event -> {
            File file = filepick.showOpenDialog(stage);
            if (file != null) {
                try {
                    Load(filepick, stage);
                } catch (IOException e1) {

                    e1.printStackTrace();
                }
                table.getItems().addAll(source.getAllMarks());
            }
        });
        save.setOnAction(e -> {
            try {
                Save(filepick, stage);
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {

                e1.printStackTrace();
            }
        });
        saveAs.setOnAction(e -> {
            try {
                SaveAs(filepick, stage);
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        });
        exit.setOnAction(e -> {
            stage.close();
        });
        menu.getItems().addAll(newf, open, save, saveAs, exit);
        hBox.getChildren().addAll(menu);
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(4);
        gridPane.setHgap(4);
        gridPane.setAlignment(Pos.CENTER_LEFT);
        Button button = new Button("Add");
        Label F = new Label("Final Mark: ");
        Label ID = new Label("ID: ");
        Label M = new Label("Midterm Mark: ");
        Label A = new Label("Assignments Mark: ");
        TextField AInput = new TextField();
        TextField FInput = new TextField();
        TextField IDInput = new TextField();
        TextField MInput = new TextField();
        IDInput.setOnKeyTyped(e -> {
            currentIDnum = IDInput.getText();
        });

        FInput.setOnKeyTyped(e -> {
            currentFinalGrade = Float.parseFloat(FInput.getText());
        });

        MInput.setOnKeyTyped(e -> {
            currentMidtermGrade = Float.parseFloat(MInput.getText());
        });
        AInput.setOnKeyTyped(e -> {
            currentAssignmentGrade = Float.parseFloat(AInput.getText());
        });
        button.setOnAction(e -> {
            source.addData(currentIDnum, currentFinalGrade, currentAssignmentGrade, currentMidtermGrade);
            table.setItems(source.getAllMarks());
            IDInput.clear();
            FInput.clear();
            AInput.clear();
            MInput.clear();

        });

        vbox.getChildren().addAll(hBox, lb, table, ID, IDInput, A, AInput, M, MInput, F, FInput, button);
        ((Group) scene.getRoot()).getChildren().addAll(vbox);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
