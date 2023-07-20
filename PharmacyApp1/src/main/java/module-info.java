module com.example.pharmacyapp1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.pharmacyapp1 to javafx.fxml;
    exports com.example.pharmacyapp1;
}