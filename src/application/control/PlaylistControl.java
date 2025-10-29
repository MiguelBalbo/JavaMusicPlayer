package application.control;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import application.model.UIFiles;

public class PlaylistControl {

	public List<File> openPlaylist(UIFiles uf) throws Exception{
		List<File> playlist = new ArrayList<>();
		BufferedReader br = new BufferedReader(new FileReader(uf.getPlaylistF()));
		String linha = br.readLine();
		
		while(linha != null) {
			File fileLinha = new File(linha);
			playlist.add(fileLinha);
			linha = br.readLine();
		}
		
		return playlist;
	}
	
	public void saveToPlaylist(UIFiles uf) throws Exception{
		String fMscPath = uf.getMusicF().getAbsolutePath();
		
		System.out.println(fMscPath);
		BufferedReader br = new BufferedReader(new FileReader(uf.getPlaylistF()));
		String linha = br.readLine();
		Boolean isOnFile = false;
		while(linha != null) {
			if (linha.equalsIgnoreCase(fMscPath)) {
				isOnFile = true;
			}
			linha = br.readLine();
		}
		
		if (!isOnFile) {
			BufferedWriter bw = new BufferedWriter(new FileWriter(uf.getPlaylistF(), true));
			bw.write(fMscPath);
			bw.newLine();
			bw.flush();
			bw.close();
			System.out.println("Música adicionada à playlist: " + uf.getMusicF().getName() + "\n Caminho = " + uf.getPlaylistF().getAbsolutePath());
		} else {
			System.out.println("Música já na playlist");
		}
		br.close();
	}
	
	
	
	
}
