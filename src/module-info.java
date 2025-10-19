module TESTE_JAVAFX_PlayerMsc {
	requires javafx.controls;
	requires javafx.graphics;
	requires jaco.mp3.player;
	requires jaudiotagger;
	requires com.goxr3plus.streamplayer;
	
	opens application to javafx.graphics, javafx.fxml;
}
