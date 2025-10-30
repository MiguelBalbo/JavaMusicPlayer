package application.control;

 
import application.gui.IconsRender;
import application.model.Musica;
import application.model.UIButtons;
import application.model.UIElements;
import application.model.UIFiles;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MusicaControl {
	
	private MediaPlayer mplay;
	private final Timeline[] tl = new Timeline[1];
	//private KeyFrameControl kfc = new KeyFrameControl();
	private IconsRender ir = new IconsRender();
	
	
	public void openMusic(UIFiles uf, Runnable onFinish) throws Exception{
		
		String[] strFatia = uf.getMusicF().getAbsolutePath().split("\\.");
		
		if(strFatia[strFatia.length-1].toLowerCase().equals("flac")){
			//player de flac aqui 
			
		}else {
			Media media = new Media(uf.getMusicF().toURI().toString());
			mplay = new MediaPlayer(media);
			mplay.play();
		}
		
	}
	
	public void openMusic(Stage primaryStage, UIFiles uf) throws Exception{
		
		String[] strFatia = uf.getMusicF().getAbsolutePath().split("\\.");
		if(strFatia[strFatia.length-1].toLowerCase().equals("flac")) {
			//colocar player de flac aqui
			
		} else {
			Media media = new Media(uf.getMusicF().toURI().toString());
			mplay = new MediaPlayer(media);
			mplay.play();
		}
		
		
	}
	
	
	
	public void mscPlayerControles(UIElements ue, UIButtons ub, Musica m1) throws Exception {
		
		final double duracaoTotal = m1.getDuracaoSegundos();
		final double[] progress = new double[1];
		final boolean[] aacPaused = new boolean[1];
		progress[0] = 1;
		aacPaused[0] = false;
		
		ub.setListenerVolumeSldr((obs, old, newVal) -> {
			mplay.setVolume(newVal.doubleValue());
		});
		
		
		KeyFrame k = new KeyFrame (Duration.millis(100), e2 -> {
			if (mplay == null) {
		        tl[0].stop();
		        return;
		    }
			
			//if(progress[0] == 1) {
				//closePlayer(root);
			//}
			
			if (mplay != null) {
				if (mplay.getStatus() == MediaPlayer.Status.PAUSED) {
					ub.setPausedPlay(true, ir);
					EventHandler<ActionEvent> event = e1 -> {
					mplay.play();
					};
					ub.setActionPauseBtn(event);
				} else {
					ub.setPausedPlay(false, ir);
					EventHandler<ActionEvent> event = e1 -> {
					mplay.pause();
					};
					ub.setActionPauseBtn(event);
					
					
					progress[0] = (double)(mplay.getCurrentTime().toSeconds()/ duracaoTotal);
					ue.setPgBar(progress[0]);
					ue.setTempoAtualLbl(mplay.getCurrentTime().toSeconds());

				}
			} 
			
		});
		
		
		tl[0] = new Timeline(k);
		tl[0].setCycleCount(Timeline.INDEFINITE);
		tl[0].play();
		
	}
	

	public void closePlayer() {
		if (mplay != null) {
			tl[0].stop();
			
			mplay.stop();
            try { Thread.sleep(50); } catch (InterruptedException e) { e.printStackTrace(); }
			mplay = null;
			tl[0].stop();
		}
	}	
	
	

}
