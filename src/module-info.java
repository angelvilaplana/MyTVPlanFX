module MyTVPlanFX {
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    requires com.google.gson;

    opens mytvplan to javafx.graphics;
    opens mytvplan.fx to javafx.fxml;
}