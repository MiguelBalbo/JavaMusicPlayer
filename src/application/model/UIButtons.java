package application.model;

import application.gui.IconsRender;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;

public class UIButtons {
	
	private Slider volumeSldr = new Slider(0,1,1);
	private Button addPlaylistBtn = new Button();
	private Button lyricsBtn = new Button();
	private Button pauseBtn = new Button("Sem música tocando");
	private Button backBtn = new Button();
	private Button proxBtn = new Button();
	private MenuItem pauseMscItem = new MenuItem("Pausar");
	
	public void ResetElements(){
		pauseBtn.setText("Sem música tocando");
		pauseBtn.setGraphic(null);
		setNextBackDisabled();
	}
	
	public MenuItem getPauseMscItem() {
		return pauseMscItem;
	}
	
	public void setActionPauseMscItem(EventHandler<ActionEvent> event) {
		pauseMscItem.setOnAction(event);
	}
	
	
	
	public Slider getVolumeSldr() {
		return volumeSldr;
	}
	public void setVolumeSldr(Slider volumeSldr) {
		this.volumeSldr = volumeSldr;
	}
	public void setListenerVolumeSldr(ChangeListener<Number> volumeSliderListener) {
		volumeSldr.valueProperty().addListener(volumeSliderListener);
	}
	
	
	public Button getAddPlaylistBtn() {
		return addPlaylistBtn;
	}
	public void setAddPlaylistBtn(Button addPlaylistBtn) {
		this.addPlaylistBtn = addPlaylistBtn;
	}
	public void setTextAddPlaylistBtn(String text) {
		this.addPlaylistBtn.setText(text);;
	}
	public void setActionAddPlaylistBtn(EventHandler<ActionEvent> event) {
		this.addPlaylistBtn.setOnAction(event);;
	}
	
	
	
	//botão de letras
	public Button getLyricsBtn() {
		return lyricsBtn;
	}
	public void setLyricsBtn(Button btn) {
		this.lyricsBtn = btn;
	}
	public void setTextLyricsBtn(String text) {
		this.lyricsBtn.setText(text);
	}
	public void setActionLyricsBtn(EventHandler<ActionEvent> event) {
		this.lyricsBtn.setOnAction(event);
	}
	
	
	
	
	
	public Button getPauseBtn() {
		return pauseBtn;
	}
	public void setPauseBtn(Button pauseBtn) {
		this.pauseBtn = pauseBtn;
	}
	public void setTextPauseBtn(String text, ImageView iv) {
		pauseBtn.setText(text);
	}
	public void setActionPauseBtn(EventHandler<ActionEvent> event) {
		this.pauseBtn.setOnAction(event);;
	}
	public void setPausedPlay(Boolean isPaused, IconsRender ir) {
		if (!isPaused) {
			pauseBtn.setGraphic(ir.ivPauseIcn());
			pauseBtn.setText("Pause");
		} else if (isPaused) {
			pauseBtn.setGraphic(ir.ivPlayIcn());
			pauseBtn.setText("Play");
		}
	}
	
	
	
	//botão de voltar música
	public Button getBackBtn() {
		return backBtn;
	}
	public void setBackBtn(Button backBtn) {
		this.backBtn = backBtn;
	}
	public void setTextBackBtn(String text) {
		this.backBtn.setText(text);;
	}
	public void setActionBackBtn(EventHandler<ActionEvent> event) {
		this.backBtn.setOnAction(event);;
	}
	
	
	
	public Button getProxBtn() {
		return proxBtn;
	}
	public void setProxBtn(Button proxBtn) {
		this.proxBtn = proxBtn;
	}
	public void setTextProxBtn(String text) {
		this.proxBtn.setText(text);;
	}
	public void setActionProxBtn(EventHandler<ActionEvent> event) {
		this.proxBtn.setOnAction(event);;
	}
	
	public void setNextBackDisabled() {
		backBtn.setDisable(true);
		proxBtn.setDisable(true);
	}
	
	public void setNextBackEnabled() {
		backBtn.setDisable(false);
		proxBtn.setDisable(false);
	}
}
