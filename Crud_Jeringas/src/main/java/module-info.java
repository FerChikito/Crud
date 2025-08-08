module org.example.crud_jeringas {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens org.example.crud_jeringas to javafx.fxml;
    exports org.example.crud_jeringas;
}