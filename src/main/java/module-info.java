module MVC {

    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    exports MVC.controller;
    opens MVC.controller;
    exports MVC.model;
    opens MVC.model;


}
