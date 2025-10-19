package application;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.Base64;
import com.goxr3plus.streamplayer.stream.StreamPlayer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Duration;

public class MusicaControl {
	
	private StreamPlayer mp;
	private TagInfoControl tic = new TagInfoControl();
	private String base64Pause = "iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAERlWElmTU0AKgAAAAgAAYdpAAQAAAABAAAAGgAAAAAAA6ABAAMAAAABAAEAAKACAAQAAAABAAAAMqADAAQAAAABAAAAMgAAAAB1y6+rAAADdElEQVRoBe1Zy27TQBT1mApCoixRJQQIdiwRKp/BI1SkKnwEfAJCsKE8BP8AqK4QfSz6EQ3iA8qqlUqkSK1qJ7ZTFZtzHY/iWB7Hj7ETJEeKHN+Ze885d94TRak+VQaqDFQZ+J8ywGSTdV2X2fbZ6vm521JVtoT3q4TBmHLkOEqHMXez0ah9ZfghE1uKECJvmsMN11WW05BD/a3d3e3ldrv9N42f9LokYDCwf/f7tjv6Wl3DGK5MA4LoNur/GfvZB5qmXZjmV0i5YVhvAkTWsoIgxttAnE9Z42TyA/AxgQ8Gw5+ZAkQ4IdbeKKZtRBTLN/HsAfiR7Oi6bj/g8WXHnojHQU5OrJsTBRJfLMu6wXEkhh2HMgz7lACKFMHRAmL63CblCQEfSISuy+9OIoK8m2FW/Cyqk8qOKVb1m1rawE5KgE8A4JB/aoaIAxKSFFx2PT+Jh9PiqnEV/EXqOuq8i6sXLKPsGYZxhRbLoJ1+k80vS5NhWqOu5WoVDPDNtK2h6/pt8un1es2wELJRGdUJl8W9j1rF3I6rE9si2Og9hHM3LkBJZeCg3o/DEgrhXcN1nRdxAcoowy76OeFwTlGYQiGY9p6SQ7NZX49yLNNWr1/SCA+cnolwhUIwLqVvQUQkktoZU6mrR36EQlRVWYr0mK1RyEkoBIce72Q3W96T6BgjQk5CIZMh5v9NKARn6qN5o0/nfhEnoRDHcTsipxna90TYQiELC+yHyGlWdvQSISehkFrt4jcijIuCqZcJRQvjFxpYTzxOUXhCIfzeCV2s3AuBCJaMOVM5CIVQPAyu73gsRsQu2cQWkdiNzKC0txntPO33SYPAR+o2HvhrxIG4JOUQWY9fwEUWlmAkEeCwnxsq0Cq/cgdLGYDuzZK2RuwYIVwa9Pi+ws87mMEep+SSuTrdmyGJd4H9mjhkDhR2RGZ6lB1c1dwKl8l+pysnwsIJ9Vh2bC8egjtFi+EiCKcQETwoF1NEN6N7MxJQuIixGMvrZgCUNgEgljew8TzlOKU8sW14SZnzv4nXmTA5+HvrhB/nY7i8lHeamjEg9wOCukn2ZoZhrsAn+EfPoablu03Mt1r66SJBpnm2jueTdBl0dhqNyy1Mr7n/epMiJEwerbIKUS3Y7/EjM8jSoaiD66Ut/Bn6ReraECZQvVcZqDJQZWDuM/APG7d9yfdSNUEAAAAASUVORK5CYII=";
	private String base64Play = "iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAERlWElmTU0AKgAAAAgAAYdpAAQAAAABAAAAGgAAAAAAA6ABAAMAAAABAAEAAKACAAQAAAABAAAAMqADAAQAAAABAAAAMgAAAAB1y6+rAAADoklEQVRoBe1Zy27TQBTNmC5apf0BXip/gFCRWMEfQEtFUAUrVuzgExCCDeUh2LMEiYBEH4uuYQNSW/EBZdWINhJSq8aJ7Zbi4dzYt3WtTGLPjKsiOVI0mfG959xzZzyvVCrlp8xAmYEyA/9TBoTtYKWUIgj2Zvb35ZTjiAnUTxOHEJWNMKysCCHnqtXh9wI/bHJbEULBe97uRykr03mCg/380tLCdK1W+5vHz7otCeh0gp/tdiCjr9903d3bg4ggugb7zUO/YL1er58a5FfIc9f1nyYCmdUlAcazBM5rXRwtPxBvEXmns7uqBdDDCVjLEWbg9nhsv4mzB+JJ2+itVnCd8W1jH8Fjku1tf/zIA4sV3/fPM49F2EMo1w12iKBIEcyWENPmNislBLwkEa2W/eGkCpCHGWbFNyqbXO2YYp24q6292FkD4AkAMZhPzRCxTkKyktu2i5PYGITr9DOIF6lzsHnez67gZ7RGnTXqFbzgczq9AZ8/jYYcsSUw6hVvQRsv7tbNvACxHy2Yb/P69rIHXnc70+sZtymHFu2jyEjK8CEb5y2BcY9EeZ5Hw1P7g130A3LmmHIBtdv+HQoil1NszD2SLLE3+6aDxT4Rln+X6+myT48Iq1sQnD+uUDAQdDUdRNa6EM4Nla1SiONUJlROJu0Q9AWL3C9NDGVMSiE49HRPdpqEfd0IO+qd4H5fw9RDvCPKmJRCUhgnvjqkihBDYAMZGFc9N2mn8zvO7WfyYpCfykfZI2EoV1ROJu1IzjUdETHnsopbKWRoSHxWOem0Q8D30dFhMTY28lXHn3wwSvLHBGJBLyQuCgZeJqQDi+Z8vpAwXxDpQoMw0zyZ63FAzcwOsSELsbdF8ZtGQjDff9IBgI/1TSOSUs+b0AN7Hl4I7MVB4zH/APcsJZNiMaLmCzgjEANnEoEY1gwgItdEr/wwBssJgOG0mrU3lNMvc2LKk/g+Rv0iZrCb3F50CRGTSOIlcD+hGKzxITO/KTu4qrlgDVQBRFdOxIUT6pbCxKwZ4GHRYlgE8ZhFO8CbxRQxzOjejAQULoI14vTYHWYgtDYBAKv7YqPcYZ5jKbFteESZi7/a6wz8u+tEjPPqWIJPk9DUjBdyLSGomWVv5roe7Z2Sf/Q06nWz20Sz1TJWRoI8b+8Dyltpsf3r4WK1OjKF6dX4rzcrQtLBoldmIGoK7Zf5yIxg6VC0guuleZxH3lldG9IBlPUyA2UGygyc+Az8A0Om8ndz/4W/AAAAAElFTkSuQmCC";
	private final Timeline[] tl = new Timeline[1];
	
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
	
	
	public void mscPlayerControles(File f, Button b1, Label tempoAtualLbl, ProgressBar pgTempo, Slider volumeSldr) throws Exception {
		Musica m1 = tic.musicTagInfo(f);
		
		volumeSldr.valueProperty().addListener((obs, old, newVal) -> {
			mp.setGain(newVal.doubleValue());
		});
		
		
		
		
		KeyFrame k = new KeyFrame (Duration.millis(100), e2 -> {
			if (mp == null) {
		        tl[0].stop();
		        return;
		    }
			
			if (mp.isPaused()) {
				Image i = new Image(new ByteArrayInputStream(Base64.getDecoder().decode(base64Play)));
				ImageView iv = new ImageView();
				iv.setFitWidth(24);
				iv.setPreserveRatio(true);
				iv.setImage(i);
				b1.setText("Play");
				b1.setGraphic(iv);
				b1.setContentDisplay(ContentDisplay.LEFT);
				
				b1.setOnAction(e1 -> {
				mp.resume();
				});
			} else {
				Image i = new Image(new ByteArrayInputStream(Base64.getDecoder().decode(base64Pause)));
				ImageView iv = new ImageView();
				iv.setFitWidth(24);
				iv.setPreserveRatio(true);
				iv.setImage(i);
				b1.setText("Pause");
				b1.setGraphic(iv);
				b1.setContentDisplay(ContentDisplay.LEFT);
				
				b1.setOnAction(e1 -> mp.pause());
				long currentBytes = mp.getEncodedStreamPosition();
				long totalBytes = mp.getTotalBytes();
				double progress = (double) currentBytes / totalBytes;
				double progressoSegundos = progress * m1.duracaoSegundos;
				StringBuffer sb = new StringBuffer();
				sb.append(String.format("%02d",(int)progressoSegundos/60));
				sb.append(":");
				sb.append(String.format("%02d",(int)progressoSegundos%60));
				tempoAtualLbl.setText(sb.toString());
				pgTempo.setProgress(progress);
			}
		});
		
		tl[0] = new Timeline(k);
		tl[0].setCycleCount(Timeline.INDEFINITE);
		tl[0].play();
	}
	

	public void closePlayer(BorderPane root) {
		if (mp != null) {
			mp.stop();
            try { Thread.sleep(50); } catch (InterruptedException e) { e.printStackTrace(); }
			mp = null;
			tl[0].stop();
		}
	}
	
	

}
