module com.example.pharmacyapp1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.pharmacyapp1 to javafx.fxml;
    exports com.example.pharmacyapp1;
}