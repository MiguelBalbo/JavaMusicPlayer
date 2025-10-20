package application.model;

import javafx.scene.image.Image;

public class Musica {
	String musica;		
	String album;
	String artista;
	Image c;
	double duracaoSegundos;
	
	public Musica(String musica, String album, String artista, Image capa, double duracaoSegundos) {
		this.musica = musica;
		this.album = album;
		this.artista = artista;
		c = capa;
		this.duracaoSegundos = duracaoSegundos;
	}
	
	public String getMusica() {
		return musica;
	}
	public void setMusica(String musica) {
		this.musica = musica;
	}
	public String getAlbum() {
		return album;
	}
	public void setAlbum(String album) {
		this.album = album;
	}
	public String getArtista() {
		return artista;
	}
	public void setArtista(String artista) {
		this.artista = artista;
	}
	public Image getC() {
		return c;
	}
	public void setC(Image c) {
		this.c = c;
	}
	public void setDuracaoSegundos (Double ds) {
		duracaoSegundos = ds;
	}
	public double getDuracaoSegundos() {
		return duracaoSegundos;
	}
	
	
	public boolean isEqual(String musica,String album, String artista, double duracaoSegundos) {
		if (musica.equals(this.musica) && album.equals(this.album) && artista.equals(this.artista) && duracaoSegundos == this.duracaoSegundos) {
			return true;
		} else {
			return false;
		}
	}
	
	
}
