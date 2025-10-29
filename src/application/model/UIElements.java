package application.model;

import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class UIElements {
	
	private Label tempoAtualLbl = new Label();
	private double tempoAtualSegundos = 0.0;
	private Label tempoFinalLbl = new Label();
	private int tempoFinalSegundos = 0;
	private ProgressBar pgBar = new ProgressBar();
	private Label musicaTagLbl = new Label();
	private Label albumTagLbl = new Label();
	private Label artistaTagLbl = new Label();
	private GridPane capaAlbumGrd = new GridPane();
	
	public void SetUIElements(Musica m1, ImageView imagem){
		musicaTagLbl.setText(m1.musica);
		albumTagLbl.setText(m1.album);
		artistaTagLbl.setText(m1.artista);
		tempoFinalLbl.setText((String.format("%02d",(int)m1.getDuracaoSegundos()/60)) + ":" + String.format("%02d",((int)m1.getDuracaoSegundos()%60)));
		setImageCapaAlbumGrd(imagem);
	}
	
	
	
	
	//capa
	public GridPane getCapaAlbumGrd() {
		return capaAlbumGrd;
	}
	
	public void setCapaAlbumGrd(GridPane capaAlbumGrd) {
		this.capaAlbumGrd = capaAlbumGrd;
	}
	
	public void setImageCapaAlbumGrd(ImageView imagem) {
		capaAlbumGrd.getChildren().removeAll();
		imagem.getStyleClass().add("capa");
		capaAlbumGrd.add(imagem, 0, 0);
		
	}
	
	public void setCssCapaAlbumGrd(String css) {
		capaAlbumGrd.getStyleClass().add(css);
	}
	
	
	
	
	
	
	//tempo atual
	public Label getTempoAtualLbl() {
		return tempoAtualLbl;
	}
	
	public double getTempoAtualSegundos() {
		return tempoAtualSegundos;
	}
	
	public String getTempoAtualPreciso() {
		int minutos = (int) (tempoAtualSegundos / 60);
		int segundos = (int) (tempoAtualSegundos % 60);
		int milesimos = (int) ((tempoAtualSegundos - (int) tempoAtualSegundos) * 100);

		return String.format("%02d:%02d.%01d", minutos, segundos, milesimos);

	}
	
	public void setTempoAtualLbl(double segundos) {
		tempoAtualLbl.setText(String.format("%02d",(int)segundos/60) + ":" + String.format("%02d",(int)segundos%60));
		tempoAtualSegundos = segundos;
	}
	
	public void setCssTempoAtualLbl(String css) {
		tempoAtualLbl.getStyleClass().add(css);
	}
	
	
	
	
	
	//tempo final
	public Label getTempoFinalLbl() {
		return tempoFinalLbl;
	}
	
	public int getTempoFinalSegundos() {
		return tempoFinalSegundos;
	}
	
	public void setTempoFinalLbl(int segundos) {
		this.tempoFinalLbl.setText(String.format("%02d",(int)segundos/60) + ":" + String.format("%02d",(int)segundos%60));
		tempoFinalSegundos = segundos;
	}
	
	public void setCssTempoFinalLbl(String css) {
		this.tempoFinalLbl.getStyleClass().add(css);
	}
	
	
	
	
	
	//barra de progresso
	public ProgressBar getPgBar() {
		return pgBar;
	}
	
	public void setPgBar(Double progresso) {
		pgBar.setProgress(progresso);
	}
	
	
	
	
	//titulo faixa
	public Label getMusicaTagLbl() {
		return musicaTagLbl;
	}
	
	public void setMusicaTagLbl(String musicaTagLblTxt) {
		musicaTagLbl.setText(musicaTagLblTxt);;
	}
	
	public void setCssMusicaTagLbl(String css) {
		musicaTagLbl.getStyleClass().add(css);
	}
	
	
	
	
	//album
	public Label getAlbumTagLbl() {
		return albumTagLbl;
	}
	
	public void setAlbumTagLbl(String text) {
		this.albumTagLbl.setText(text);
	}
	
	public void setCssAlbumTagLbl(String css) {
		this.albumTagLbl.getStyleClass().add(css);
	}
	
	
	//artista
	public Label getArtistaTagLbl() {
		return artistaTagLbl;
	}
	
	public void setArtistaTagLbl(String text) {
		this.artistaTagLbl.setText(text);
	}
	
	public void setCssArtistaTagLbl(String css) {
		this.artistaTagLbl.getStyleClass().add(css);
	}
	
}
