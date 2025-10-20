package application.control;

import java.io.File;
import com.goxr3plus.streamplayer.stream.StreamPlayer;

import application.gui.ButtonIconsRender;
import application.model.Musica;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Duration;

public class MusicaControl {
	
	private StreamPlayer mp;
	private TagInfoControl tic = new TagInfoControl();
	private final Timeline[] tl = new Timeline[1];
	private final Timeline[] tl1 = new Timeline[1];
	//private KeyFrameControl kfc = new KeyFrameControl();
	private ButtonIconsRender bir = new ButtonIconsRender();
	private final double[] progressoTempo = new double[1];
	
	public File selecionaArquivo(Stage primaryStage) {
		FileChooser fc = new FileChooser();
		ExtensionFilter exMsc = new ExtensionFilter("Music Files","*.mp3","*.flac","*.wav");
		ExtensionFilter exAll = new ExtensionFilter("All Files","*.*");
		fc.getExtensionFilters().addAll(exMsc, exAll);
		fc.setTitle("Escolha musica");
		File f = fc.showOpenDialog(primaryStage);
		return f;
	}
	

	
	public void openMusic(Stage primaryStage, File f) throws Exception{
		mp = new StreamPlayer();
		mp.open(f);
		mp.play();
		
		primaryStage.setOnCloseRequest(e -> {
			mp.stop();
			System.exit(0);
		});
	}
	
	
	public void mscPlayerControles(BorderPane root, File f, Button b1, Label tempoAtualLbl, ProgressBar pgTempo, Slider volumeSldr) throws Exception {
		Musica m1 = tic.musicTagInfo(f);
		final double duracaoTotal = m1.getDuracaoSegundos();
		final double[] progress = new double[1];
		progress[0] = 1;
		
		volumeSldr.valueProperty().addListener((obs, old, newVal) -> {
			mp.setGain(newVal.doubleValue());
		});
		
		
		KeyFrame k = new KeyFrame (Duration.millis(100), e2 -> {
			if (mp == null) {
		        tl[0].stop();
		        progressoTempo[0] = 0.0;
		        return;
		    }
			
			if(progress[0] == 1) {
				closePlayer(root);
			}
			
			
			if (mp.isPaused()) {
				ImageView iv = bir.ivPlayIcn();
				b1.setText("Play");
				b1.setGraphic(iv);
				b1.setContentDisplay(ContentDisplay.LEFT);
				b1.setOnAction(e1 -> mp.resume());
			} else {
				ImageView iv = bir.ivPauseIcn();
				b1.setText("Pause");
				b1.setGraphic(iv);
				b1.setContentDisplay(ContentDisplay.LEFT);
				b1.setOnAction(e1 -> mp.pause());

			}
		});
		
		KeyFrame k1 = new KeyFrame (Duration.millis(1000), e3 -> {
			if(mp == null) {
				tl1[0].stop();
				return;
			}
			
			
			if (!mp.isPaused()) {
				progress[0] = (double)(progressoTempo[0]/ duracaoTotal);
				double progressoSegundos = progressoTempo[0];
				StringBuffer sb = new StringBuffer();
				sb.append(String.format("%02d",(int)progressoSegundos/60));
				sb.append(":");
				sb.append(String.format("%02d",(int)progressoSegundos%60));
				tempoAtualLbl.setText(sb.toString());
				pgTempo.setProgress(progress[0]);
				progressoTempo[0]++;
			}
		});
		
		
		tl[0] = new Timeline(k);
		tl[0].setCycleCount(Timeline.INDEFINITE);
		tl[0].play();
		
		tl1[0] = new Timeline(k1);
		tl1[0].setCycleCount(Timeline.INDEFINITE);
		tl1[0].play();
	}
	

	public void closePlayer(BorderPane root) {
		if (mp != null) {
			tl1[0].stop();
			tl[0].stop();
	        progressoTempo[0] = 0;
			
			mp.stop();
            try { Thread.sleep(50); } catch (InterruptedException e) { e.printStackTrace(); }
			mp = null;
			tl[0].stop();
		}
	}
	
	

}
