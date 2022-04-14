package com.example.clientserver;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class server extends Application {
    private static ServerSocket ss;

    static TextArea outputtext = new TextArea();




    public static void servernew() {
        try {
            ss = new ServerSocket(8888); //port number
        } catch (IOException ex) {

            ex.printStackTrace();
        }
    }


    public static void acceptConnections(ServerSocket serverSocket) {
        try {
            System.out.println("Waiting for connections...");

            while (true) {
                Socket s = serverSocket.accept();

                System.out.println("Client connected!");


                ClientHandler clientHandler = new ClientHandler(s);
                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class ClientHandler implements Runnable {
        // input and output streams
        private DataInputStream input = null;
        private DataOutputStream output = null;


        public ClientHandler(Socket clientSocket) {


            try {
                input = new DataInputStream(clientSocket.getInputStream());
                output = new DataOutputStream(clientSocket.getOutputStream());
            } catch (IOException e) {

                e.printStackTrace();
            }
        }
        boolean value = true;
        @Override
        public void run() {
            try {
                // receive the message from client
                while (true) {
                    String message = input.readUTF();
                    if (value == true) {
                        outputtext.appendText(message);
                        value = false;
                    } if(value==false) {
                        outputtext.appendText("\n\n" + message);
                    }
                    System.out.println(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static class Connect extends Thread {
        @Override
        public void run() {
            servernew();
            acceptConnections(ss);
        }
    }

    @Override
    public void start(Stage stage) {
        // create a GridPane layout
        GridPane g = new GridPane();
        g.setAlignment(Pos.CENTER); // align its center
        g.setHgap(5); // set horizontal and vertical gap between rows and columns
        g.setVgap(5);
        g.setStyle("-fx-background-color: #C0C0C0;");
        g.setPadding(new Insets(10, 10, 10, 10)); // add padding

        // exit button
        Button exitbut = new Button("Exit");
        exitbut.setOnAction((ActionEvent event) -> {
            Platform.exit();
        });
        // add the components to the grid
        g.add(outputtext, 0, 0);
        g.add(exitbut, 0, 2);

        // create a Scene node and add the GridPane grid node to the scene
        Scene scene = new Scene(g, 400, 300);
        stage.setTitle("Server");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Connect newcon = new Connect();
        newcon.start();

        launch();
    }
}
