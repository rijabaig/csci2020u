import javafx.event.EventHandler;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
//import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.*;

public class lab4 extends Application {
    @Override
    public void start(Stage mystage) throws Exception {

        mystage.setWidth(600);
        mystage.setHeight(400);
        GridPane p = new GridPane();
        p.setPadding(new Insets(40, 10, 10, 10));
        p.setVgap(30);
        p.setHgap(30);
        DatePicker datePick = new DatePicker();
        TextField username = new TextField();
        PasswordField password = new PasswordField();
        TextField fullname = new TextField();
        TextField email = new TextField();
        TextField phone = new TextField();
        TextField postalcode = new TextField();
        TextField city = new TextField();
        p.add(new Label("UserName: "), 0, 0);
        p.add(username, 1, 0);
        p.add(new Label("Postal Code: "), 0, 4);
        p.add(postalcode, 1, 4);
        p.add(new Label("Password: "), 2, 0);
        p.add(password, 3, 0);
        p.add(new Label("City: "), 2, 4);
        p.add(city, 3, 4);
        p.add(new Label("Name: "), 0, 1);
        p.add(fullname, 1, 1);
        p.add(new Label("E-Mail: "), 2, 1);
        p.add(email, 3, 1);
        p.add(new Label("Phone: "), 0, 3);
        p.add(phone, 1, 3);
        p.add(new Label("Birthday: "), 2, 3);
        p.add(datePick, 3, 3);

        Button btAdd = new Button();
        btAdd.setText("Register Here");
        btAdd.setWrapText(true);
        btAdd.setStyle("-fx-background-color: #ff0000; ");
        btAdd.setPrefSize(100, 50);
        btAdd.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                System.out.println("First Name = " + username.getText() +
                        "\nPassword =" + password.getText() +
                        "\nFull Name =" + fullname.getText() +
                        "\nEmail =" + email.getText() +
                        "\nPhone Number = (" + phone.getText().charAt(0) +
                        phone.getText().charAt(1) + phone.getText().charAt(2) +
                        +phone.getText().charAt(3) + phone.getText().charAt(4) +
                        phone.getText().charAt(5) + +phone.getText().charAt(6) +
                        phone.getText().charAt(7) + phone.getText().charAt(8) +
                        phone.getText().charAt(9) +
                        "\nDate Of Birth = " + datePick.getValue() +
                        "\nCity = " + city.getText() +
                        "\nPostal Code = " + postalcode.getText());
                username.setText("");
                password.setText("");
                postalcode.setText("");
                fullname.setText("");
                email.setText("");
                phone.setText("");
                city.setText("");
                datePick.setValue(null);

            }
        });
        p.add(btAdd, 2, 5);
        Scene scene = new Scene(p, 600, 400);
        mystage.setScene(scene);
        mystage.setTitle("Lab 04 Solution Rija");
        mystage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
