package application.gui;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.Base64;

import application.control.MusicaControl;
import application.control.TagInfoControl;
import application.model.Musica;
import application.model.UIButtons;
import application.model.UIElements;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class GridRender {
	
	
	//controles do player
	public GridPane playerCtrlGrd(Button pauseBtn, Button backBtn, Button proxBtn) {
		IconsRender ir = new IconsRender();
		GridPane playerCtrlGrd = new GridPane();

		//anterior
		backBtn.getStyleClass().add("btn-ant-prox");
		ImageView antIv = ir.backIcon();
		backBtn.setGraphic(antIv);
		
		//proximo
		proxBtn.getStyleClass().add("btn-ant-prox");
		ImageView ivProx = ir.nextIcon();
		proxBtn.setGraphic(ivProx);
		
		playerCtrlGrd.add(backBtn, 0, 0);
		playerCtrlGrd.add(proxBtn, 2, 0);
		
		//pause
		playerCtrlGrd.getStyleClass().add("player-controls-grd");
		pauseBtn.getStyleClass().add("pause-btn");
		playerCtrlGrd.add(pauseBtn, 1, 0);
		
		return playerCtrlGrd;
	}


	//botões de playlist, letras e volume
	public GridPane volumePauseGrd(Slider volumeSldr, Button addPlaylistBtn, Button lyricsBtn) {
		IconsRender ir = new IconsRender();
		
		GridPane volumePauseGrd = new GridPane();
		volumePauseGrd.getStyleClass().add("volume-pause");
		ImageView iv = ir.addPlaylistIcon();
				
		addPlaylistBtn.setGraphic(iv);
		addPlaylistBtn.getStyleClass().add("botoes-extras");
		
		ImageView iv2 = ir.lyricsIcon();
		lyricsBtn.setGraphic(iv2);
		lyricsBtn.getStyleClass().add("botoes-extras");
		volumePauseGrd.add(addPlaylistBtn, 0, 0);
		volumePauseGrd.add(lyricsBtn, 1, 0);
		
		//parte do volume
		Label volumeLbl = new Label("Volume: ");			
		volumeLbl.getStyleClass().add("volume-controles-texto");
		volumeSldr.getStyleClass().add("volume-slider");
		HBox volumeBox = new HBox(volumeLbl, volumeSldr);
		volumeBox.getStyleClass().add("volume-controles");
		volumePauseGrd.add(volumeBox, 2, 0);
		return volumePauseGrd;
	}
	
	
	//texto padrão das tags
	private GridPane tagGrdTemplate() {
		IconsRender ir = new IconsRender();
		
		GridPane tagMusicaGrd = new GridPane();
				
		//titulo da música
		ImageView iv1 = ir.musicIcon();
		Label musicaLbl = new Label("Música: ");
		musicaLbl.getStyleClass().add("titulo-musica");
		musicaLbl.setGraphic(iv1);
		tagMusicaGrd.add(musicaLbl, 0, 0);
		
				
				
		//album
		ImageView iv2 = ir.albumIcon();
		Label albumLbl = new Label("Álbum: ");
		albumLbl.getStyleClass().add("outras-infos-musica");
		albumLbl.setGraphic(iv2);
		tagMusicaGrd.add(albumLbl, 0, 1);
		
		
		//artista
		ImageView iv3 = ir.artistIcon();
		Label artistaLbl = new Label("Artista: ");
		artistaLbl.getStyleClass().add("outras-infos-musica");
		artistaLbl.setGraphic(iv3);
		tagMusicaGrd.add(artistaLbl, 0, 2);
		
		return tagMusicaGrd;
	}

	

	
	//gera textos da música
	public GridPane tagMusicaGrd(UIElements ue) {
		
		GridPane tagMusicaGrd = tagGrdTemplate();
		tagMusicaGrd.add(ue.getMusicaTagLbl(), 1, 0);
		tagMusicaGrd.add(ue.getAlbumTagLbl(), 1, 1);
		tagMusicaGrd.add(ue.getArtistaTagLbl(), 1, 2);
		tagMusicaGrd.getStyleClass().add("grid-tags-musica");
		
		//tags CSS nas tags
		ue.setCssMusicaTagLbl("titulo-musica");
		ue.setCssArtistaTagLbl("outras-infos-musica");
		ue.setCssAlbumTagLbl("outras-infos-musica");
		return tagMusicaGrd;
	}

	//tempo da música
	public GridPane tempoMusicaGrd(Label tempoAtualLbl, Label tempoFinalLbl, ProgressBar pgBar) {
		GridPane tempoMusicaGrd = new GridPane();
		tempoMusicaGrd.getStyleClass().add("tempo-musica-grid");
		pgBar.getStyleClass().add("barra-progresso");
		tempoAtualLbl.getStyleClass().add("tempo");
		tempoFinalLbl.getStyleClass().add("tempo");
		tempoMusicaGrd.add(tempoAtualLbl, 0, 0);
		tempoMusicaGrd.add(tempoFinalLbl, 2, 0);
		tempoMusicaGrd.add(pgBar, 1, 0);
		return tempoMusicaGrd;
	}
	
	
	
	
	//atualiza o player
	
	

}
