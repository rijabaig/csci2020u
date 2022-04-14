package com.example.clientserver;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class client extends Application {
    private clientaccept x;

    @Override
    public void start(Stage stage) throws IOException {
    connect();
        GridPane g = new GridPane();
        g.setAlignment(Pos.CENTER);
        g.setHgap(5);
        g.setVgap(5);
        g.setPadding(new Insets(10, 10, 10, 10));
        Label name = new Label("Username: ");
        TextField nameenter = new TextField();
        Button clear = new Button("Clear");
        Label txt = new Label("Message: ");
        TextField txtenter = new TextField();
        Button send = new Button("Send");
        Button exitbut = new Button("Exit");

        // this will send the message when send is clicked
        // it will also clear the message text field
        send.setOnAction(event -> {
            try {
                sendmessage(nameenter.getText(), txtenter.getText());
                txtenter.setText("");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        //this extra button clears the fields
        clear.setOnAction(event -> {
            txtenter.setText("");
            nameenter.setText("");
        });

        // allows for everything to close
        exitbut.setOnAction((ActionEvent event) -> {
            Platform.exit();
        });


        g.add(name, 0, 0);
        g.add(nameenter, 1, 0);
//setting where the fields will be
        g.add(txt, 0, 1);
        g.add(txtenter, 1, 1);
        g.setStyle("-fx-background-color: #C0C0C0;");
        g.add(send, 0, 2);
        g.add(clear, 1,3);
        g.add(exitbut, 0, 3);


        Scene scene = new Scene(g, 400, 300);
        stage.setTitle("Client");
        stage.setScene(scene);
        stage.show();
    }

    private void connect() {
        x = new clientaccept();
    }

    private void sendmessage(String name, String message) throws IOException {
        try { //sending message
            x.output.writeUTF(name + ": " + message);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }


    private class clientaccept {
        private Socket s = null;
        private DataInputStream input = null;
        private DataOutputStream output = null;

        // constructor
        public clientaccept() {
            System.out.println("Client");
            try {
                s = new Socket("localhost", 8888);
                input = new DataInputStream(s.getInputStream());
                output = new DataOutputStream(s.getOutputStream());

                System.out.println("Connected Successfully");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
