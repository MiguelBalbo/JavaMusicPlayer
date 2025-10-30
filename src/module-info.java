module GIT_JAVAFX_PlayerMsc {
	requires javafx.controls;
	requires javafx.graphics;
	requires jaudiotagger;
	requires com.goxr3plus.streamplayer;
	
	opens application.gui to javafx.graphics, javafx.fxml;
}
