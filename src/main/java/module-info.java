module gui.a7 {
    requires javafx.controls;
    requires javafx.fxml;


    opens gui.a7 to javafx.fxml;
    exports gui.a7;
}