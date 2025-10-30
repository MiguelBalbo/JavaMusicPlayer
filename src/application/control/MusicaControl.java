package application.control;

 
import com.goxr3plus.streamplayer.stream.StreamPlayer;
import application.gui.IconsRender;
import application.model.Musica;
import application.model.UIButtons;
import application.model.UIElements;
import application.model.UIFiles;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MusicaControl {
	
	private StreamPlayer mp;
	private TagInfoControl tic = new TagInfoControl();
	private final Timeline[] tl = new Timeline[1];
	//private KeyFrameControl kfc = new KeyFrameControl();
	private IconsRender ir = new IconsRender();
	private final double[] progressoTempo = new double[1];
	
	
	public void openMusic(UIFiles uf, Runnable onFinish) throws Exception{
		
		mp = new StreamPlayer();
		mp.open(uf.getMusicF());
		mp.play();
		
	}
	
	public void openMusic(Stage primaryStage, UIFiles uf) throws Exception{
		
		mp = new StreamPlayer();
		mp.open(uf.getMusicF());
		mp.play();
		
		
	}
	
	
	
	public void mscPlayerControles(UIElements ue, UIButtons ub, Musica m1) throws Exception {
		
		final double duracaoTotal = m1.getDuracaoSegundos();
		final double[] progress = new double[1];
		progress[0] = 1;

		
		ub.setListenerVolumeSldr((obs, old, newVal) -> {
			mp.setGain(newVal.doubleValue());
		});
		
		
		KeyFrame k = new KeyFrame (Duration.millis(100), e2 -> {
			if (mp == null) {
		        tl[0].stop();
		        progressoTempo[0] = 0.0;
		        return;
		    }
			
			//if(progress[0] == 1) {
				//closePlayer(root);
			//}
			
			
			if (mp.isPaused()) {
				ub.setPausedPlay(true, ir);
				EventHandler<ActionEvent> event = e1 -> mp.resume();
				ub.setActionPauseBtn(event);
			} else {
				ub.setPausedPlay(false, ir);
				EventHandler<ActionEvent> event = e1 -> mp.pause();
				ub.setActionPauseBtn(event);
				
				
				progress[0] = (double)(progressoTempo[0]/ duracaoTotal);
				ue.setPgBar(progress[0]);
				ue.setTempoAtualLbl(progressoTempo[0]);
				progressoTempo[0] += 0.1;

			}
		});
		
		
		tl[0] = new Timeline(k);
		tl[0].setCycleCount(Timeline.INDEFINITE);
		tl[0].play();
		
	}
	

	public void closePlayer() {
		if (mp != null) {
			tl[0].stop();
	        progressoTempo[0] = 0;
			
			mp.stop();
            try { Thread.sleep(50); } catch (InterruptedException e) { e.printStackTrace(); }
			mp = null;
			tl[0].stop();
		}
	}	
	
	

}
