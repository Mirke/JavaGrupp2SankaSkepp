module com.grupp2.sankaskepp {
    requires javafx.controls;
    requires javafx.fxml;

    //opens com.grupp2.sankaskepp to javafx.fxml;
    exports com.grupp2.sankaskepp.players;
    opens com.grupp2.sankaskepp.players to javafx.fxml;
    exports com.grupp2.sankaskepp.Bastian_Tobias_Anna;
    opens com.grupp2.sankaskepp.Bastian_Tobias_Anna to javafx.fxml;

    opens com.grupp2.sankaskepp to javafx.fxml;
    //exports com.grupp2.sankaskepp.AnnaGUI;
    opens com.grupp2.sankaskepp.AnnaGUI to javafx.fxml;
    exports com.grupp2.sankaskepp.CreateAndSetBoats;
    opens com.grupp2.sankaskepp.CreateAndSetBoats to javafx.fxml;
}