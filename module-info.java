module Controller {
    requires javafx.controls;
    requires javafx.fxml;
	requires org.json;

    // Open the 'application' package for access from other modules
    opens application to javafx.graphics, javafx.fxml;
    // Additional exports if needed:
    // exports application.someotherpackage;
    // exports application.yetanotherpackage;
}

