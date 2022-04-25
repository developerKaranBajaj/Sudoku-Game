module com.example.sudokogame {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.sudokogame to javafx.fxml;
    exports com.example.sudokogame;
}