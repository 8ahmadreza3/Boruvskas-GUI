module com.example.boruvska {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.boruvska to javafx.fxml;
    exports com.example.boruvska;
}