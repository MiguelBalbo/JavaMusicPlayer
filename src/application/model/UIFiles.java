package application.model;

import java.io.File;
import java.util.List;

public class UIFiles {
	
	private File playlistF;
	private List<File> playlistL;
	private int playlistPos;
	private File musicF;
	private File lyricsF;
	
	public File getLyricsF() {
		return lyricsF;
	}
	
	public void setLyricsF(File file) {
		this.lyricsF = file;
	}
	
	public File getPlaylistF() {
		return playlistF;
	}
	public void setPlaylistF(File playlistF) {
		this.playlistF = playlistF;
	}
	public List<File> getPlaylistL() {
		return playlistL;
	}
	public void setPlaylistL(List<File> playlistL) {
		this.playlistL = playlistL;
	}
	public int getPlaylistPos() {
		return playlistPos;
	}
	public void setPlaylistPos(int playlistPos) {
		this.playlistPos = playlistPos;
	}
	public File getMusicF() {
		return musicF;
	}
	public void setMusicF(File musicF) {
		this.musicF = musicF;
	}
	
	public void mscAsPlaylist() {
		playlistF = musicF;
	}
	
	
}
