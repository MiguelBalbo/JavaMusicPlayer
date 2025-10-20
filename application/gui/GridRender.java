package application.gui;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.Base64;

import application.control.MusicaControl;
import application.control.TagInfoControl;
import application.model.Musica;
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
	public GridPane playerCtrlGrd(Button pauseBtn) {
		GridPane playerCtrlGrd = new GridPane();

		//anterior
		Button backBtn = new Button();
		backBtn.getStyleClass().add("btn-ant-prox");
		ImageView antIv = new ImageView();
		antIv.setFitWidth(24);
		antIv.setPreserveRatio(true);
		antIv.setImage(new Image(new ByteArrayInputStream(Base64.getDecoder().decode("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAERlWElmTU0AKgAAAAgAAYdpAAQAAAABAAAAGgAAAAAAA6ABAAMAAAABAAEAAKACAAQAAAABAAAAMqADAAQAAAABAAAAMgAAAAB1y6+rAAAB+UlEQVRoBe2Xv0vDQBTHc2011STVwW6i6Ojewc2CizqLi4Pugq7+BTo4qP+AgpPgKDoodHQQZ8HNQQd1aZuaRNKe30cN9MfSJpcU9R0cuZfL3Xuf77uXEE3jxgqwAqwAK8AKsAKsACvACrAC/0UB23Zv0A9U8jqOMxN2v0zYhVi3iB5lfZtriHJVr2tLuCnaJno0lAXSo7+uxyoVOZFKee9dE33eSPX5vNLHbds5aYF4irL5QECklKM4Sg2cog30R9PM0nF6/VUgyMJerebVELQA0IJp6nNRAIK1idVIqVTKFArzBDAshPZiGNnJIAgV10RAcIy2EewhBSylWEUWLlQE37pH7CC27ZURfk5KrWxZ2fFW5yrHsRV7teqtIRPyB2IrTggSJDaQDrVj9xObA8vSz5uvVVFBcR9Xq26lA06pGRtIECUKewzjHcBYdNRQM+vBnMpr7CAULDJzdH9/N4ThF2rmDN+SN5UQtFciIOSoWCz6ANIBso9vYZ6y47r+Cs2paImBBMGa5siuYegGbOn7/iWAnoO5KNfEQShYIcQnsgPf8hTmFGUHNTRNc2HbQECCYJGdzUZDz5OND2bonypaP1AQCiCXEx/N17R2TXbYFgXkFk4fwjruXAeY5XRaznbeZ5sVYAVYAVaAFWAFWAFWgBVgBf6sAt9edon5z+uCLQAAAABJRU5ErkJggg=="))));
		backBtn.setGraphic(antIv);
		
		//proximo
		Button proxBtn = new Button();
		proxBtn.getStyleClass().add("btn-ant-prox");
		ImageView ivProx = new ImageView();
		ivProx.setFitWidth(24);
		ivProx.setPreserveRatio(true);
		ivProx.setImage(new Image(new ByteArrayInputStream(Base64.getDecoder().decode("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAERlWElmTU0AKgAAAAgAAYdpAAQAAAABAAAAGgAAAAAAA6ABAAMAAAABAAEAAKACAAQAAAABAAAAMqADAAQAAAABAAAAMgAAAAB1y6+rAAAB90lEQVRoBe2Xv07CUBjF7y0qlBZMjCRuqKujYTBxIXHQwc3EUd9AR30CHRx8A30II4MDk3Egrg4OmjjooA7SllKVXs8FmjAQIO29MPg1If3/fef8Tm9vYYwWIkAEiAARIAJEgAgQASJABP4LAd/3l1R6dd3mGX43cWsacW9stfgTGl/Hvb/Pfas4ttHn+EiHYhvpVt+CGVGvi/mRumm8KKmRR6nNMIJ31/UvNOocWjqpkVfbznAh2ANjfB/phEKI7NCuGi5IaqQtKZfLrBhGah073PMCD+mcaNA6sKQSI7JDNjt9K9PB5gv8HCGdoFqtTg3srvCkMiORJpgpcs62sT9TKq39wNBBdE7nWrkRKdayMleddMQHds9dN/jSaULW1mIkEm3bZgEt9hgTefmadpxgNzqneq3VSFfsOHowrYPRcfxPxsI5DP66badnVafQW08LLcdp7MhHiXMOE+xQtwlpSHkimEPekMACan/XandWuVz+lY10L8qMIIFNiK10BItTDPRj3eJ76ysx4nnNZ3ymLKKwsKy0jUeq0dtkHNuJxggmvqIcCx0T4hJzhzEJExJUokRgoP3nKgzThXyey8lvYkuiRKC6ImfwSZuQ9GInkkqJZdM0nxVGcI9aY3nDKdRMpYgAESACRIAIEAEiQASIABGIT+APQemIBaTqIS4AAAAASUVORK5CYII="))));
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
	public GridPane volumePauseGrd(Slider volumeSldr) {
		GridPane volumePauseGrd = new GridPane();
		volumePauseGrd.getStyleClass().add("volume-pause");
		ImageView iv = new ImageView();
		iv.setFitWidth(24);
		iv.setPreserveRatio(true);
		
		//botão adicionar a playlist
		Button addPlaylistBtn = new Button("");
		iv.setImage(new Image(new ByteArrayInputStream(Base64.getDecoder().decode("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAERlWElmTU0AKgAAAAgAAYdpAAQAAAABAAAAGgAAAAAAA6ABAAMAAAABAAEAAKACAAQAAAABAAAAMqADAAQAAAABAAAAMgAAAAB1y6+rAAABG0lEQVRoBe2VMQrCMBSG+0LRVlszOXkQL+BFXDyKm6OLl/AALs6ewtnFIQGLEmIKBkrBWNJGMvyFkjR5/0ve98p7SYIHBEAABEAABEAABEAABH4QINe+lM+1Uq+Jy6bPHufTfR99U+sMRIjHjYjmTcGQ86LInOcPeRZ8gQAIgAAIBCHgrOP/7CNSVisT4cm3t6QuPIzRUWs9c9lgr0Wgzoh5dWu58yfrbBm5IQKJLUHISGwZcZZfU0V2Wielz6XTdLzNc7parRDVwc6/jIt6vYNdUpbZpu0jWENUii05H13sgX1Kq/VhR9+mafVBR/SRD15UraD/mYdzZMQDWlAJMhIUr4fzaDJCxO7m/mePGCABARAAARAAARCwBN4T9UAyVWHbQwAAAABJRU5ErkJggg=="))));
		addPlaylistBtn.setGraphic(iv);
		addPlaylistBtn.getStyleClass().add("botoes-extras");
		
		ImageView iv2 = new ImageView();
		iv2.setFitWidth(24);
		iv2.setPreserveRatio(true);
		
		//botão de abrir letras (a ser implementado)
		Button lyricsBtn = new Button("");
		iv2.setImage(new Image(new ByteArrayInputStream(Base64.getDecoder().decode("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAERlWElmTU0AKgAAAAgAAYdpAAQAAAABAAAAGgAAAAAAA6ABAAMAAAABAAEAAKACAAQAAAABAAAAMqADAAQAAAABAAAAMgAAAAB1y6+rAAACo0lEQVRoBe1Yv08bMRSOL00DuUtQK+U/qJBYGDq3DEj9HzoiRsRUVaq6sUJnVlj5I1KpglaMCAl1qTp1qm7LLwKUu35OYnQ57Dvf86V1JJ842X5+7/P73rOfw1Uq7nERmEsE2FxQ5wDa749iGWwQLI05PJFNWi4byPxbOCLIQEAmgrR+hvGmDKAEWQfOvTHFyT0jqr1punDaXuz1tFyMhR95ekJ/poXxOw6ApzEzUeIgDMMmX6PXG+1mwXId/qp0PNUElzNWWZ20bJilZzLXbrd7E/tovBYVK5MIFZRix5h3R7ETNlZULZB46ftPL4RTlNaKjJiS4MStIELJQNqGvLVQyaqDwc2fNGDWWKd0onp9R5FZS+JEUbyRHMv6ZCIAi/AaX2RJp1Tl1fPYaVJP1icTYYzxmt6RgVJkIHFJsRM2Np2RdeEUpbWJCMX/B5tFI/LrwfNUh3xG5lW1Uv7NDHFx7s0IEgMyEWDEKJPfElhGXZTY16hOZ1kguDiPVPNkIqhavPy+UgEXlbday1+73eEHz/P2Zba+X6/K5EJm1RlptRoHcBjBjY/h4G+8V3EcveUX6TRwwu9HLTkjj5BKEsDhe0BtF4WzKiNFnU/qkzMyqVq3YRJM9IOg/lz0dVrc6iNsqRVk40ZHX6ZjmJH4Gfaz5JUtlSmr9/vXnzI1cibJGZnu5dyPFznrlzZtmJHS/DAGckSMQ1gygE0ZUX6z0uH834ng3+U97mi1WjvRcVilU6hqoUTipwPbUoFR5LiPKvj70WjUzin2wkabCD4KHMJoC794f0ZR9FEAmLf3X5rNpvRiLYKtRQQ3L7+sdjgJ3196UWSBf6Wre0be20yCB0uLiO0ktIgsAolcIqgm1p4J7rx7XARcBFwEXARcBFwEUhH4Cx2VqwJJYjaRAAAAAElFTkSuQmCC"))));
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
		GridPane tagMusicaGrd = new GridPane();
		ImageView iv1 = new ImageView();
		iv1.setPreserveRatio(true);
		iv1.setFitWidth(24);
		
		//titulo da música
		Label musicaLbl = new Label("Música: ");
		musicaLbl.getStyleClass().add("titulo-musica");
		iv1.setImage(new Image(new ByteArrayInputStream(Base64.getDecoder().decode("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAERlWElmTU0AKgAAAAgAAYdpAAQAAAABAAAAGgAAAAAAA6ABAAMAAAABAAEAAKACAAQAAAABAAAAMqADAAQAAAABAAAAMgAAAAB1y6+rAAABcElEQVRoBe1ZQU7DQAzMEiQatfkCp76CT/CPXlAf0gNv4B2c+QM3OHMp3Wq3SElwDqmWqhLGXlcJmkjROit7bM/EUdMUBQ4wAAbAABgAA3IGnDyUH9l13Yzj7ZyLHL9zPtfnNnPv7feHwMQUE3vFTDB6NzQyNokuMiND023b3A/2JFfvY9eflsVjRizZlWBDEQlrljFQxJJdCTYUkbBmGQNFLNmVYEMRCWuWMVDEkl0JdjZFYoxL+qn+TOeX94cPWjeSgqQx4pf9NOFuFz+dK+p0L7EfyX7orxeLWZZ8CfbRVAMT8w2hsZS1bIRVwLHtE8P78MRt4iQ0+6VKEVLjT6+vo1UkO60KQNWtpcibPfSSjbxmrz4B1DbSP7GYR7diOorcVMMeQrhtGvfGyNzSoJcMP7GLSpGqqt4p8/q37PP5jfk/mipFhga227gsy6KfgR949F3kpa6ru8FvUisVr1J5Us2iWDAABsAAGPg3DHwDnElML+aHmxMAAAAASUVORK5CYII="))));
		musicaLbl.setGraphic(iv1);
		tagMusicaGrd.add(musicaLbl, 0, 0);
		
		ImageView iv2 = new ImageView();
		iv2.setPreserveRatio(true);
		iv2.setFitWidth(16);
		
		//album
		Label albumLbl = new Label("Álbum: ");
		albumLbl.getStyleClass().add("outras-infos-musica");
		iv2.setImage(new Image(new ByteArrayInputStream(Base64.getDecoder().decode("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAERlWElmTU0AKgAAAAgAAYdpAAQAAAABAAAAGgAAAAAAA6ABAAMAAAABAAEAAKACAAQAAAABAAAAMqADAAQAAAABAAAAMgAAAAB1y6+rAAAEOElEQVRoBe1Z22oTQRjOxtCmTQq9axUVDy8gWp9A8c5DLaYUEfTWC0/gvYgiWA+o6K1eVMUUsQehCr5BW3yAqhcttBGhh2yS3YS46/cnO3Q7mZnsJpNSIQvLzPyH7z/McWcjkfbTzkA7A+0M/E8ZMHQ767quYdulkXLZHYxGjQG095ANw4gsO05kzjDciUQi/s5ARadtLYGQ84VCcdx1I0NhnIP85MzM1FAqlfobRk+7LAWQz9s/cjnbrb5WxjSLw/UMIegU5Fc29ezFdDq9q55eS/imad33OTLaqBFgPPThPGsUpyE9GF4l4/l8cb4hAIESsGarmLYpYOsnsezB8Dnd6NmsfYbh68begseMrK1ZB7YwNDYsy9rP7GiE3YQyTXuDDLQyCGbNF0yO0bSUCOAJBZHN6h9OMgfZMMOq+FwmE4qOJTbqdbW2iR3UAbYAwIfml2YEsUiBBDWuW85L4lI93KhKwNuk9kHmkUquxTzao/Y21SuY4BON9gb0pr1sert+Zff/SqeBsIFXcQpTKj0lqBdEJpmM71aB+Hm5nHUCR8Rvflpt3b2STHa9qaWLKfBjBZx++CH1Vzq0WOZc17khhq+l5vOlY/WDID3jNQK+XIsgpuAUfZ04zCeRlDRCGLoIg2OqLPCAXg/yZGk7keiMBj3OV7HdS+jJMRGgokeMUEeQQsG+KTKgomF5/aLi8zzDiJ7laawtDSQajQwwoSCl4xgPeDl8b7yiHqWX6jwf7VMCmook9UkxtOwiEDuCDi3RsOJ1g8jIovB0S8DsFMlIe0QkvJNp0kAwCZdDOk49uOXBPvSSEfx1Rgtb0ne/TEcaiOO4czIlER1z4DZPh+GrNCTopTrPR/uzgKYizcqY0kBiMeOTTElE7+mJvxDRVTSM99MqPs/DKJH6JA0kHu94T0C4KKh7mcAMlsuRw6xev3RP1pepSrALje7uzopPIj3pqkXC3krxG5nrFymLaOvr9qFYLPJTxGM07AcDiURH4M8CbM4ZbM59/CrI8KiU9ggxMa4/ouijetCntzf+iwxizlyDjm8BMIrYm24RL0wQVbtGH4bVeFAfauTobONN1sc1zG0iwP4o+aA6ZwVyhV3ABRJugRAFAR8Wmob29cr3psFCAuAsNh+0N5RzhOzS6RTvXVSPYAU7H9KXhsXp3gxJPArb98iHhoF4RWTmD2UHVzUHeZ7uNl05kS2cBlZ1Y1fwAO60OhgWBNlpSRAMlAXTimFG92YUQMuD2AzGqgwzGNS2AACrMrFRbjA721Li2HCHMue9De8z0K/sEx7O021xnjdCSzMm5IIvoEyQs5lpFoah4//Rs5RON3ebqDxr8Y7L2hRQoVD6gPKCTEZMd6YTia5BLK9N/3rTEgjvJHplBEENgn4cZy7vZ2jlQ20O10uT+Bn6VuvewDvQbrcz0M5AOwM7PgP/AIxWvZVUN/gjAAAAAElFTkSuQmCC"))));
		albumLbl.setGraphic(iv2);
		tagMusicaGrd.add(albumLbl, 0, 1);
		
		ImageView iv3 = new ImageView();
		iv3.setPreserveRatio(true);
		iv3.setFitWidth(16);
		
		//artista
		Label artistaLbl = new Label("Artista: ");
		artistaLbl.getStyleClass().add("outras-infos-musica");
		iv3.setImage(new Image(new ByteArrayInputStream(Base64.getDecoder().decode("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAERlWElmTU0AKgAAAAgAAYdpAAQAAAABAAAAGgAAAAAAA6ABAAMAAAABAAEAAKACAAQAAAABAAAAMqADAAQAAAABAAAAMgAAAAB1y6+rAAADDklEQVRoBe1XPYgTQRSe2ZjbzebvTg0IHv4gCGKh2FmKvY2C2B1aCIqdghZKsDjF09LC4tBOELXUVgu1sdA2hz+NjQfxkp2Y5I7s+A2XaFxnducym4DHBEJ23vvmzfu+2XlvQoj9WAWsAlYBq4BVwCpgFdj8CjDGdjQaja2TZkrTWJCx1UOEhB8ksVbzebdCKW1KfKmaHNNojHWvDkhwTm+urfE9YUgPEEIfIfZUq9VtgOhh03XGOj8IggpjHY6vUvG+n481EQQ33JHskkiwUPBKqkTxapWFj7H2QxUmDbsREUqJSPJjXCJ/zgedi8OZ+oyIrC/OXyYlATJfkzCm/hSIkN1JSXDOdyZhTP0pEKFnNJLIAlPTwI0M2TLyTEzEGbnIObmPynQFB35BFgvl9w12BFj3mMwfZ2s229dk/lIpd0tmN7KJ0itKbKvVeRINFASdWr/8vo76dMb9uaK8//WVzU2ls4PEZ4i+V7oApc9Qgk/JfEk2QUCGwe6nkrcsNlle5kX0ikWQ+oIExE7crlarRmdQEMGuPpAuGDEanZHhWJUKDTA+N2yb5LORYpNMNGktSyRJoUn7N82OjHzY0eRmUVVu4B41B/VF51Z+gK1nMnTe9717SpChY0NEgqB70HH4C/SMXejY6Nbr5RzjGp7fhmG4hITrYUjgoNvh3g8SR+HbB9vdIGhPF4u567Kcg6B1gdLMHfjywL+r178fl+FUNi0iUF5cPy4TwgmSxpc/RlPCwnRFFXgjdsTvAj81mCPIz8xUfg7GOr+xRBBwGsr/EIFAoJnNkiOe533SCayLQcN7CuxvErrzorhYIgMSvV53W7lcrkcnpzHG63cyjThKIlDqkljAcdxZvEZjIaFDwHFIqIVTgaDUWeHzffpNhZmMnb/XWSeuj7g6Acwx9J/r/3DMfD63ODxWPStfrcEEcQMdPJv+yq7fhYJ7GmfxBAqLF43vOFT7/MTtSDSu8RjX/HlZEPxfyXHeOy8qo/DjXLxibKXg++5zGd7arAJWAauAVcAqYBWwCvwnCvwCfHgoNowtiqgAAAAASUVORK5CYII="))));
		artistaLbl.setGraphic(iv3);
		tagMusicaGrd.add(artistaLbl, 0, 2);
		
		return tagMusicaGrd;
	}

	
	//renderiza capa do álbum
	public GridPane capaAlbumGrd(ImageView capa) throws Exception {
		GridPane capaAlbumGrd = new GridPane();
		capa.getStyleClass().add("capa");
		capaAlbumGrd.add(capa, 0, 0);
		capaAlbumGrd.getStyleClass().add("capa-grid");
		return capaAlbumGrd;
	}

	
	//gera textos da música
	public GridPane tagMusicaGrd(Label musicaTagLbl, Label albumTagLbl, Label artistaTagLbl) {
		GridPane tagMusicaGrd = tagGrdTemplate();
		tagMusicaGrd.add(musicaTagLbl, 1, 0);
		tagMusicaGrd.add(albumTagLbl, 1, 1);
		tagMusicaGrd.add(artistaTagLbl, 1, 2);
		tagMusicaGrd.getStyleClass().add("grid-tags-musica");
		
		//tags CSS nas tags
		musicaTagLbl.getStyleClass().add("titulo-musica");
		albumTagLbl.getStyleClass().add("outras-infos-musica");
		artistaTagLbl.getStyleClass().add("outras-infos-musica");
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
	public void refreshPlayer(BorderPane root, TagInfoControl tic, MusicaControl mc, ImageView capa, File f, GridPane capaAlbumGrd, Label musicaTagLbl, Label albumTagLbl, Label artistaTagLbl, Label tempoFinalLbl, Stage primaryStage, Button pauseBtn, Label tempoAtualLbl, ProgressBar pgBar, Slider volumeSldr) throws Exception {
		//remove capa antiga e coloca nova
		ImageView capaAux = tic.mscCapa(f);
		capaAlbumGrd.getChildren().remove(capa);
		capaAlbumGrd.getChildren().add(capaAux);
		
		//adiciona ou substitui texto do cantor
		Musica m1 = tic.musicTagInfo(f);
		musicaTagLbl.setText(m1.getMusica());
		albumTagLbl.setText(m1.getAlbum());
		artistaTagLbl.setText(m1.getArtista());
		
		//atualiza tempo final
		tempoFinalLbl.setText((String.format("%02d",(int)m1.getDuracaoSegundos()/60)) + ":" + String.format("%02d",((int)m1.getDuracaoSegundos()%60)));
		
		//toca música
		mc.openMusic(primaryStage,f);
		
		//controles do player
		mc.mscPlayerControles(root, f, pauseBtn, tempoAtualLbl, pgBar, volumeSldr);
	}
	
	

}
