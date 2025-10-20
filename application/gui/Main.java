package application.gui;
	
import java.io.File;
import application.control.MusicaControl;
import application.control.TagInfoControl;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;


public class Main extends Application {
	
	private	MusicaControl mc = new MusicaControl();
	private TagInfoControl tic = new TagInfoControl();
	private GridRender gr = new GridRender();
	private ButtonIconsRender bir = new ButtonIconsRender();
	
	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setMinWidth(400);
			primaryStage.setMinHeight(680);
			primaryStage.setTitle("Player de Musica");
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,650);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			root.setStyle("-fx-background-color:#202022;");
			
			ImageView ivFolderIcn = bir.ivFolderIcn();
			Button btnEscolheArquivo = new Button("Escolher arquivo");
			btnEscolheArquivo.setGraphic(ivFolderIcn);
			btnEscolheArquivo.setContentDisplay(ContentDisplay.LEFT);
			btnEscolheArquivo.getStyleClass().add("botao-arquivo");
			
			//renderiza capa
			ImageView capa = tic.mscCapa();
			GridPane capaAlbumGrd = gr.capaAlbumGrd(capa);
			root.setTop(capaAlbumGrd);
			
			//renderiza tags da música
			Label musicaTagLbl = new Label("");
			Label albumTagLbl = new Label("");
			Label artistaTagLbl = new Label("");
			GridPane tagMusicaGrd = gr.tagMusicaGrd(musicaTagLbl, albumTagLbl, artistaTagLbl);
			
			//renderiza tempo
			Label tempoAtualLbl = new Label("00:00");
			Label tempoFinalLbl = new Label("00:00");
			ProgressBar pgBar = new ProgressBar();
			GridPane tempoMusicaGrd = gr.tempoMusicaGrd(tempoAtualLbl, tempoFinalLbl, pgBar);
			
			
			
			//renderiza volume e botões extras
			Slider volumeSldr = new Slider(0,1,1);
			GridPane volumePauseGrd = gr.volumePauseGrd(volumeSldr);
			
			
			
			//renderiza botões do player
			Button pauseBtn = new Button("Sem música tocando");
			GridPane playerCtrlGrd = gr.playerCtrlGrd(pauseBtn);
			
			
			
			//agrupa tag, volume, tempo e botão de abrir arquivo
			GridPane agrupaTagsGrd = new GridPane();
			agrupaTagsGrd.add(tempoMusicaGrd,0,0);
			agrupaTagsGrd.add(tagMusicaGrd,0,1);
			agrupaTagsGrd.add(playerCtrlGrd, 0, 2);
			agrupaTagsGrd.add(volumePauseGrd, 0, 3);
			agrupaTagsGrd.add(btnEscolheArquivo, 0, 4);
			
			
			root.setCenter(agrupaTagsGrd);
			
			btnEscolheArquivo.setOnAction(
				e -> {
					try {
						//para musica atual (se houver)
						mc.closePlayer(root);
						
						//abre seletor de arquivo
						File f = mc.selecionaArquivo(primaryStage);
						
						//corrige player
						if (f != null) {
							gr.refreshPlayer(root, tic, mc, capa, f, capaAlbumGrd, musicaTagLbl, albumTagLbl, artistaTagLbl, tempoFinalLbl, primaryStage, pauseBtn, tempoAtualLbl, pgBar, volumeSldr);
						}
					
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}	
				});
				
				btnEscolheArquivo.setOnDragOver(e -> {
					if (e.getGestureSource() != btnEscolheArquivo && e.getDragboard().hasFiles()) {
		                e.acceptTransferModes(TransferMode.COPY);
		            }
		            e.consume();
				});
				
				btnEscolheArquivo.setOnDragDropped(e -> {
					Dragboard db = e.getDragboard();
					mc.closePlayer(root);
					
					
		            if (db.hasFiles()) {
		                File file = db.getFiles().get(0); 
		                try {
							gr.refreshPlayer(root, tic, mc, capa, file, capaAlbumGrd, musicaTagLbl, albumTagLbl, artistaTagLbl, tempoFinalLbl, primaryStage, pauseBtn, tempoAtualLbl, pgBar, volumeSldr);
						} catch (Exception e1) {
		                	e1.printStackTrace();
		                }
		            }
				});
			
			
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	


	public static void main(String[] args) {
		launch(args);
	}
}
