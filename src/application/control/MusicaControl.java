package application.control;

 
import java.net.URL;

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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MusicaControl {
	
	private MediaPlayer mplay;
	private StreamPlayer sp;
	private final Timeline[] tl = new Timeline[1];
	private IconsRender ir = new IconsRender();
	private boolean isFlac = false;
	
	
	public void openMusic(UIFiles uf, Runnable onFinish) throws Exception{
		
		String[] strFatia = uf.getMusicF().getAbsolutePath().split("\\.");
		String extensaoArq = strFatia[strFatia.length-1].toLowerCase();
		
		if(extensaoArq.equals("flac") || extensaoArq.equals("ogg")){
			sp = new StreamPlayer();
			sp.open(uf.getMusicF());
			sp.play();
			isFlac = true;
			
		}else {
			Media media = new Media(uf.getMusicF().toURI().toString());
			mplay = new MediaPlayer(media);
			mplay.play();
		}
		
	}
	
	public void openMusic(Stage primaryStage, UIFiles uf) throws Exception{
		
		String[] strFatia = uf.getMusicF().getAbsolutePath().split("\\.");
		String extensaoArq = strFatia[strFatia.length-1].toLowerCase();
		
		if(extensaoArq.equals("flac") || extensaoArq.equals("ogg")){
			sp = new StreamPlayer();
			sp.open(uf.getMusicF());
			sp.play();
			isFlac = true;
		} else {
			Media media = new Media(uf.getMusicF().toURI().toString());
			mplay = new MediaPlayer(media);
			mplay.play();
		}
		
		
	}
	
	public void openLink(Stage primaryStage, String url) throws Exception{
		
		String[] strFatia = url.split("\\.");
		String extensaoArq = strFatia[strFatia.length-1].toLowerCase();
		
		if(extensaoArq.equals("flac") || extensaoArq.equals("ogg")){
			URL urlF = new URL(url);
			sp = new StreamPlayer();
			sp.open(urlF);
			sp.play();
			isFlac = true;
		} else {
			Media media = new Media(url);
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
		
		final double[] progressoSegundos = new double[1];
		progressoSegundos[0] = 0;
			
			
		
		ub.setListenerVolumeSldr((obs, old, newVal) -> {
			if (isFlac) {
				sp.setGain(newVal.doubleValue());
			} else {
				mplay.setVolume(newVal.doubleValue());
			}
		});
		
		
		KeyFrame k = new KeyFrame (Duration.millis(100), e2 -> {
			if (mplay == null && sp == null) {
		        tl[0].stop();
		        return;
		    }
			
			//if(progress[0] == 1) {
				//closePlayer(root);
			//}
			
			if (mplay != null) {
				
				//ADICIONAR LISTENER NO BOTÃO!!!!!!! <-------------------------------
				//bind faz espelhamento, ele atualiza ao digitar em uma textbox
				
				if (mplay.getStatus() == MediaPlayer.Status.PAUSED) {
					ub.setPausedPlay(true, ir);
					EventHandler<ActionEvent> event = e1 -> {
						if(isFlac) {
							sp.resume();
						} else {
							mplay.play();
						}
					};
					ub.setActionPauseBtn(event);
					ub.setActionPauseMscItem(event);
				} else {
					ub.setPausedPlay(false, ir);
					EventHandler<ActionEvent> event = e1 -> {
						if(isFlac) {
							sp.pause();
						} else {
							mplay.pause();
						}
					};
					ub.setActionPauseBtn(event);
					ub.setActionPauseMscItem(event);
					
					if (isFlac) {
						progressoSegundos[0] += 0.1;
						progress[0] = (double)(progressoSegundos[0]/ duracaoTotal);						
					} else {
						progress[0] = (double)(mplay.getCurrentTime().toSeconds()/ duracaoTotal);
					}
					ue.setPgBar(progress[0]);
					ue.setTempoAtualLbl(mplay.getCurrentTime().toSeconds());

				}
			} else if (sp != null) { //logica do flac
				
				if (sp.isPaused()) {
					ub.setPausedPlay(true, ir);
					EventHandler<ActionEvent> event = e1 -> {
						sp.resume();
					};
					ub.setActionPauseBtn(event);
					ub.setActionPauseMscItem(event);
				} else {
					ub.setPausedPlay(false, ir);
					EventHandler<ActionEvent> event = e1 -> {
						sp.pause();
					};
					ub.setActionPauseBtn(event);
					ub.setActionPauseMscItem(event);
					
					progressoSegundos[0] += 0.1;
					progress[0] = (double)(progressoSegundos[0]/ duracaoTotal);						
					ue.setPgBar(progress[0]);
					ue.setTempoAtualLbl(progressoSegundos[0]);

				}
			}
			
		});
		
		
		tl[0] = new Timeline(k);
		tl[0].setCycleCount(Timeline.INDEFINITE);
		tl[0].play();
		
	}
	

	public void closePlayer() {
		if (mplay != null) {
			mplay.stop();
            try { Thread.sleep(50); } catch (InterruptedException e) { e.printStackTrace(); }
			mplay = null;
			tl[0].stop();
		} else if (sp != null) {
			sp.stop();
            try { Thread.sleep(50); } catch (InterruptedException e) { e.printStackTrace(); }
			sp = null;
			tl[0].stop();
		}
		
	}	
	
	

}
