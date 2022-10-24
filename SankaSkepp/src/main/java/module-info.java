module com.grupp2.sankaskepp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.grupp2.sankaskepp to javafx.fxml;
    exports com.grupp2.sankaskepp;
}