module com.example.weatherforecastapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;


    opens com.example.weatherforecastapp to javafx.fxml;
    exports com.example.weatherforecastapp;
}