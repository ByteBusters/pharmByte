package com.example.pharmacyapp1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HelloController {
    @FXML
    private TextField email_id;

    @FXML
    private PasswordField password;

    @FXML
    private Label allField;

    public void onButtonCLicked(ActionEvent event) throws IOException {
        String enteredUsername = email_id.getText();
        String enteredPassword = password.getText();

        try (Connection connection = DatabaseHelper.getConnection()) {
            String sql = "SELECT COUNT(*) FROM users WHERE username = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, enteredUsername);
            statement.setString(2, enteredPassword);

            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);

            if (count == 1) {
                // Username and password are correct, navigate to the homepage
                Parent root = FXMLLoader.load(getClass().getResource("Homepage.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } else {
                // Invalid username or password, display an error message
                allField.setText("Invalid username or password");
                allField.setTextFill(Color.RED);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database connection error
            allField.setText("Error connecting to the database");
            allField.setTextFill(Color.RED);
        }
    }

    private boolean checkLoginCredentials(String username, String password) {
        try (Connection connection = DatabaseHelper.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM login WHERE username=? AND password=?")) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @FXML
    private void onDetailsClicked() {
        try {
            // Load the FXML file for the "Stocks" page
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Stock.fxml"));
            Parent root = loader.load();

            // Create a new stage for the "Stocks" page
            Stage stage = new Stage();
            stage.setTitle("Stocks");
            stage.setScene(new Scene(root));
            stage.show();

            // You can optionally close the current stage if needed
            // ((Stage) details.getScene().getWindow()).close();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }
    }

    @FXML
    private void onTransactionClicked() {
        try {
            // Load the FXML file for the "Transaction History" page
            FXMLLoader loader = new FXMLLoader(getClass().getResource("RecordHistory.fxml"));
            Parent root = loader.load();

            // Create a new stage for the "Transaction History" page
            Stage stage = new Stage();
            stage.setTitle("Transaction History");
            stage.setScene(new Scene(root));
            stage.show();

            // You can optionally close the current stage if needed
            // ((Stage) transactionDetails.getScene().getWindow()).close();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }
    }

    @FXML
    private void onAddMedicineClicked() {
        try {
            // Load the FXML file for the "PharmacistAddMedicine" page
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PharmacistAddMedicine.fxml"));
            Parent root = loader.load();

            // Create a new stage for the "PharmacistAddMedicine" page
            Stage stage = new Stage();
            stage.setTitle("Add Medicine");
            stage.setScene(new Scene(root));
            stage.show();

            // You can optionally close the current stage if needed
            // ((Stage) AddMed.getScene().getWindow()).close();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }
    }

}
