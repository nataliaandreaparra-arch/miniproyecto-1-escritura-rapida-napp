module np.escriturarapida {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens np.escriturarapida to javafx.fxml;
    opens np.escriturarapida.controller to javafx.fxml;
    exports np.escriturarapida;
}