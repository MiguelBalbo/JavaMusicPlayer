package application.control;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class FileControl {
	
	//tipos = 0 é musica e playlists, 1 é apenas playlist, 2 é arquivos de letras de música
	public FileChooser selecionaArquivo(int tipo) {
		FileChooser fc = new FileChooser();
		
		if (tipo == 0) {
			ExtensionFilter exMscPlay = new ExtensionFilter("Music and Playlist Files","*.mp3","*.flac","*.wav","*.aac","*.m4a","*.ogg","*.m3u8","*.m3u");
			ExtensionFilter exMsc = new ExtensionFilter("Music Files","*.mp3","*.flac","*.wav","*.aac","*.m4a","*.ogg");
			fc.getExtensionFilters().add(exMscPlay);
			fc.getExtensionFilters().add(exMsc);
		}
		
		if (tipo == 0 || tipo == 1) {
			ExtensionFilter exPlay = new ExtensionFilter("Playlist Files","*.m3u8","*.m3u");
			fc.getExtensionFilters().add(exPlay);
		}
		
		if (tipo == 2) {
			ExtensionFilter exLyrics = new ExtensionFilter("Lyric Files","*.lrc");
			fc.getExtensionFilters().add(exLyrics);
		}
		
		fc.setTitle("Escolha Arquivo");
		ExtensionFilter exAll = new ExtensionFilter("All Files","*.*");
		fc.getExtensionFilters().add(exAll);
		
		
		return fc;
	}
	
	
	
}
