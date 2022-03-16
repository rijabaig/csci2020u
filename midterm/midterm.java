package com.midterm;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.effect.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.Group;

public class midterm extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setWidth(330);
        primaryStage.setHeight(300);
        GridPane p = new GridPane();
        Scene sc1 = new Scene(new Group(), 400, 300);
        p.setPadding(new Insets(40, 10, 10, 10));
        p.setVgap(10);
        p.setHgap(10);
        primaryStage.setTitle("CSCI2020U - Midterm");
        primaryStage.setScene(sc1);

        // create animation button
        Button animation = new Button("Animation");
        animation.setPrefWidth(100);
        // create 2d graphic button
        Button graphics = new Button("2D Graphics");

        Circle circle = new Circle();
        // position
        circle.setCenterX(20.0f);
        circle.setCenterY(200.0f);
        // color
        circle.setFill(Color.PINK);
        circle.setRadius(25.0f);
        TranslateTransition translateTransition = new TranslateTransition();
        // duration of 2 seconds
        translateTransition.setDuration(Duration.millis(2000));
        // transition
        translateTransition.setByX(275);
        translateTransition.setNode(circle);
        // allows it to reverse back
        translateTransition.setAutoReverse(true);
        // cycle count
        translateTransition.setCycleCount(10);
        // Playing
        translateTransition.play();
        // Creating a Group object
        Group root = new Group(circle);
        // Creating a scene object
        Scene scene2 = new Scene(root, 50, 50);
        // label called lb1
        Label lb1 = new Label("Clicked on Animation Button ");
        // places label on y naxis
        lb1.setTranslateY(25);
        Button returnbt = new Button("Back To Main"); // button
        root.getChildren().addAll(lb1, returnbt);
        primaryStage.setScene(scene2);
        // sets scene
        primaryStage.show();
        // when you clcik animation button it creates scene 2
        animation.setOnAction((e) -> {
            primaryStage.setScene(scene2);
            primaryStage.show();
        });
        // when you click returnbt it returns to scene1
        returnbt.setOnAction(e -> primaryStage.setScene(sc1));

        // button for about section
        Button about = new Button("About");
        about.setPrefWidth(100);
        // adding buttons to console
        p.add(animation, 10, 3);
        p.add(graphics, 10, 4);
        p.add(about, 10, 5);

        graphics.setPrefWidth(100);
        Group graphicsroot = new Group();
        // creating scene for 2d graphic
        Scene sc3 = new Scene(graphicsroot, 600, 400);
        // generating a canvas for my drawing
        Canvas canvas = new Canvas(1000, 1000);
        graphicsroot.getChildren().add(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        // background color
        gc.setFill(Color.BLUEVIOLET);
        gc.fillRect(0, 0, 500, 400);
        gc.setLineWidth(3);
        gc.beginPath();
        // making lines for my initial R
        gc.moveTo(100, 100);
        gc.lineTo(100, 150);
        gc.strokeArc(100, 100, 30, 30, 310, 240, ArcType.OPEN);
        gc.strokeLine(100, 120, 140, 150);
        gc.strokeLine(100, 125, 125, 125);
        // create B
        gc.moveTo(175, 100);
        gc.lineTo(175, 150);
        gc.strokeArc(175, 100, 20, 25, 250, 270, ArcType.OPEN);
        gc.strokeArc(175, 125, 20, 25, 250, 270, ArcType.OPEN);
        gc.stroke();
        // new label for graphics section
        Label lb2 = new Label("Clicked on Graphics Button "); // label
        Label name = new Label("my initials are: RB");
        name.setTranslateY(200);
        name.setTranslateX(100);
        lb2.setTranslateY(25);
        Button returnbt2 = new Button("Back To Main"); // button
        graphicsroot.getChildren().addAll(lb2, name, returnbt2);

        graphics.setOnAction((e) -> {
            primaryStage.setScene(sc3);
            primaryStage.show();
        });
        returnbt2.setOnAction(e -> primaryStage.setScene(sc1));

        // about section
        Group aboutroot = new Group();
        Scene sc4 = new Scene(aboutroot, 600, 400);
        Label lb4 = new Label("Clicked on About Button "); // label
        lb4.setTranslateY(25);
        Label lb5 = new Label("Name: Rija Baig");
        lb5.setTranslateY(60);
        Label lb6 = new Label("ID: 100746674");
        lb6.setTranslateY(75);
        Label lb7 = new Label("This is my work for CSCI2020U midterm.");
        lb7.setTranslateY(90);
        Label lb8 = new Label("There are 3 sections. Animation, 2D graphics and this page.");
        lb8.setTranslateY(105);
        Label lb9 = new Label("This was my first time using shapes and animation. Enjoy!");
        lb9.setTranslateY(120);
        Button returnbt3 = new Button("Back To Main"); // button

        aboutroot.getChildren().addAll(lb4, lb5, lb6, lb7, lb8, lb9, returnbt3);
        about.setOnAction((e) -> {
            primaryStage.setScene(sc4);
            primaryStage.show();
        });
        returnbt3.setOnAction(e -> primaryStage.setScene(sc1));
        // main scene
        Group r = (Group) sc1.getRoot();
        r.getChildren().add(p);
        primaryStage.setScene(sc1);
        primaryStage.show();

        // colors for aesthetic affect
        sc4.setFill(Color.PINK);
        sc1.setFill(Color.YELLOW);
        scene2.setFill(Color.AQUA);
        // shadows for affect
        animation.setEffect(new DropShadow());
        graphics.setEffect(new DropShadow());
        about.setEffect(new DropShadow());

    }

    public static void main(String[] args) {
        Application.launch(args);

    }
}
