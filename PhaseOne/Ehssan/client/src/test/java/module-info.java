module com.ehssan {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;


    opens com.ehssan to javafx.fxml;
    exports com.ehssan;
}
