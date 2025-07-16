module com.example.busmanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens com.example.busmanagementsystem to javafx.fxml;
    exports com.example.busmanagementsystem;
}