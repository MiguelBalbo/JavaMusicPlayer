package application.gui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javafx.util.Duration;
import java.time.LocalTime;

import application.model.UIElements;
import application.model.UIFiles;
import javafx.animation.KeyFrame;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Lyrics{
	
	private VBox lyricsBox;
	private String[] tempos;
	
	public Stage showLyrics(UIFiles uf) throws Exception {
			Stage primaryStage = new Stage();
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,650);
			ScrollPane spGeral = new ScrollPane();
			spGeral.setFitToWidth(true);
			spGeral.setVbarPolicy(ScrollBarPolicy.ALWAYS);
			root.setCenter(spGeral);
			lyricsBox = new VBox();
			
			root.setStyle("-fx-background-color:#202022;");
			lyricsBox.setStyle("-fx-background-color:#202022;");
			lyricsBox.setMinWidth(Region.USE_COMPUTED_SIZE);
			lyricsBox.setPrefWidth(Region.USE_COMPUTED_SIZE);
			lyricsBox.setMaxWidth(Double.MAX_VALUE);
			String estiloLinha = "-fx-font-size:16px; -fx-padding: 4px 0 4px 0; -fx-text-fill: #F4F4F8;";
			lyricsBox.setAlignment(Pos.TOP_CENTER);
			
			primaryStage.setScene(scene);
			primaryStage.setMinWidth(400);
			primaryStage.setMinHeight(680);
			primaryStage.setTitle("Letras");
			primaryStage.show();
			
			BufferedReader br = new BufferedReader(new FileReader(uf.getLyricsF()));
			StringBuffer sb = new StringBuffer();
			String linha = br.readLine();
			int i = 1;
			
			while(linha != null) {
				String[] strFatiado = linha.replace("[", "").split("]");
				if(strFatiado.length > 1) {
					Label lbl = new Label(strFatiado[1]);
					lbl.setStyle(estiloLinha);
					GridPane.setHalignment(lbl, HPos.CENTER);
					lyricsBox.getChildren().add(lbl);
					
					sb.append(strFatiado[0]);
					sb.append("]");
					
				}
				linha = br.readLine();
				i++;
			}
			tempos = sb.toString().split("]");

			//lyricsBox.getChildren().get(1).setStyle("-fx-font-size: 24px; -fx-text-fill: #F4F4F8;");
			
			br.close();
			
			spGeral.setContent(lyricsBox);
			
			return primaryStage;
	}
	
	
	
	
	
	public KeyFrame atualizaLrc(UIElements ue) {
		int tempoFinal = ue.getTempoFinalSegundos();
		final int[] i = new int[1];
		i[0] = 1;
		
	
		System.out.println(tempos.length);
		KeyFrame k = new KeyFrame(Duration.millis(100), e1 -> {
			
				String tempoAuxOrg = tempos[i[0]].replace("[", "").replace("]", "").replace(":", "").replace(".", "");				
				int tempoAlvo = Integer.parseInt(tempoAuxOrg);
				
				String tempoAux = ue.getTempoAtualPreciso().replace("[", "").replace("]", "").replace(":", "").replace(".", "");
				int tempoAtual = Integer.parseInt(tempoAux);
				tempoAtual -= 150;
				
				//System.out.println(tempoAux);
				//System.out.println(tempoAuxOrg);
				//System.out.println(i[0]);
				//System.out.println(tempos[0]);
				//System.out.println(tempos[1]);
				//System.out.println(tempos[2]);
				//System.out.println(tempos[3]);
				
				
				if(tempoAtual >= tempoAlvo) {
					
					i[0] += 1;
					lyricsBox.getChildren().get(i[0]).setStyle("-fx-font-size: 24px; -fx-padding: 8px 0 8px 0; -fx-text-fill: #F4F4F8;");
					lyricsBox.getChildren().get(i[0]-1).setStyle("-fx-font-size: 16px; -fx-padding: 4px 0 4px 0; -fx-text-fill: #F4F4F8;");
				}
							
		});
		return k;
	}
			
}
