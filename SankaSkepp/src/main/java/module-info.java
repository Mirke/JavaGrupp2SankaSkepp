module com.grupp2.sankaskepp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.grupp2.sankaskepp to javafx.fxml;
    exports com.grupp2.sankaskepp;
    exports com.grupp2.sankaskepp.players;
    opens com.grupp2.sankaskepp.players to javafx.fxml;
}