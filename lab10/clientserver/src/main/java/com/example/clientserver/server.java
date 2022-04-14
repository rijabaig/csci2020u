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
    private static int y;
    static TextArea outputtext = new TextArea();



    // constructor to make new serverSocket at the specified port
    public static void initServer() {
        try {
            ss = new ServerSocket(8888);
            y = 0;

            // acceptConnections();
        } catch (IOException ex) {
            System.out.println("Thrown from Server constructor");
            ex.printStackTrace();
        }
    }

    // method to encapsulate the instructions for the server waiting for connections
    public static void acceptConnections(ServerSocket serverSocket) {
        try {
            System.out.println("Waiting for connections...");

            while (true) {
                Socket socket = serverSocket.accept();
                y++;
                System.out.println("Client #" + y + " connected!");

                // use a thread to handle each connection
                ClientHandler clientHandler = new ClientHandler(socket, y);
                Thread t = new Thread(clientHandler);
                t.start();
            }
        } catch (IOException e) {
            System.out.println("Thrown from Server -> acceptConnections()");
            e.printStackTrace();
        }
    }

    // use threads to concurrently let two players play
    // use ClientHandler class to add instructions to run on each thread
    public static class ClientHandler implements Runnable {
        // input and output streams
        private DataInputStream input = null;
        private DataOutputStream output = null;
        private final int myusernumber;

        public ClientHandler(Socket clientSocket, int myusernumber) {
            this.myusernumber = myusernumber;

            try {
                input = new DataInputStream(clientSocket.getInputStream());
                output = new DataOutputStream(clientSocket.getOutputStream());
            } catch (IOException e) {
                System.out.println("Exception from ClientHandler constructor");
                e.printStackTrace();
            }
        }
        boolean value = true;
        @Override
        public void run() {
            try {
                // send the client ID
                output.writeInt(myusernumber);

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

    // that you write another thread class to handle the listening of incoming
    // connections
    public static class Connection extends Thread {
        @Override
        public void run() {
            initServer();
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
        Connection newcon = new Connection();
        newcon.start();

        launch();
    }
}