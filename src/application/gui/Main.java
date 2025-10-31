package application.gui;
	
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import application.control.FileControl;
import application.control.MusicaControl;
import application.control.PlaylistControl;
import application.control.TagInfoControl;
import application.model.Musica;
import application.model.UIButtons;
import application.model.UIElements;
import application.model.UIFiles;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.ImageView;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;


public class Main extends Application {
	
	//QUERO QUE ESSES ABAIXO SEJAM INTERFACES -- CORRIGIR ISSO
	private	MusicaControl mc = new MusicaControl();
	private GridRender gr = new GridRender();
	private IconsRender ir = new IconsRender();
	private TagInfoControl tic = new TagInfoControl();
	private PlaylistControl pc = new PlaylistControl();
	private FileControl fc = new FileControl();
	private UIElements ue = new UIElements();
	private UIButtons ub = new UIButtons();
	private UIFiles uf = new UIFiles();
	
	private Lyrics ls = new Lyrics();
	
	private Timeline t;
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setMinWidth(400);
			primaryStage.setMinHeight(680);
			primaryStage.setTitle("Player de Musica");
			primaryStage.setOnCloseRequest(e -> {
				mc.closePlayer();
				if (t != null) {
					t.stop();
				}
				System.exit(0);
			});
			
			
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,675);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			root.setStyle("-fx-background-color:#202022;");
			
			Button btnEscolheArquivo = new Button("Escolher arquivo");
			btnEscolheArquivo.setGraphic(ir.ivFolderIcn());
			btnEscolheArquivo.setContentDisplay(ContentDisplay.LEFT);
			btnEscolheArquivo.getStyleClass().add("botao-arquivo");
			
			// Create a MenuBar
	        MenuBar menuBar = new MenuBar();

	        // Menu arquivo
	        Menu fileMenu = new Menu("Arquivo");
	        fileMenu.getStyleClass().add("submenu-superior");
	        
	        MenuItem abrirTItem = new MenuItem("Abrir link");
	        MenuItem abrirAItem = new MenuItem("Abrir arquivo");
	        MenuItem abrirLItem = new MenuItem("Abrir letras");
	        MenuItem addPlayItem = new MenuItem("Adicionar à playlist");
	        MenuItem fMusicaItem = new MenuItem("Fechar música");
	        
	        //menu controles
	        Menu controleMenu = new Menu("Controles");
	        fileMenu.getStyleClass().add("submenu-superior");

	        MenuItem proxMscItem = new MenuItem("Próxima Musica");
	        MenuItem antMscItem = new MenuItem("Música anterior");
	        
	        fileMenu.getItems().addAll(abrirTItem, abrirAItem, abrirLItem, addPlayItem, fMusicaItem);
	        controleMenu.getItems().addAll(antMscItem,ub.getPauseMscItem(),proxMscItem);

	        menuBar.getMenus().addAll(fileMenu,controleMenu);
	        menuBar.getStyleClass().add("menu-superior");
			
	        if (System.getProperty("os.name") != null && System.getProperty("os.name").startsWith("Mac")) {
	        	menuBar.useSystemMenuBarProperty().set(true);
	        }
	        
			root.setTop(menuBar);
			
			
			
			
			//renderiza capa
			ue.setCssCapaAlbumGrd("capa-grid");
			ue.setImageCapaAlbumGrd(ir.mscCapa());
			
			//renderiza tags da música
			GridPane tagMusicaGrd = gr.tagMusicaGrd(ue);
			
			//renderiza tempo
			ue.setTempoAtualLbl(0);
			ue.setTempoFinalLbl(0);
			GridPane tempoMusicaGrd = gr.tempoMusicaGrd(ue.getTempoAtualLbl(), ue.getTempoFinalLbl(), ue.getPgBar());
			
			//renderiza volume e botões extras
			GridPane volumePauseGrd = gr.volumePauseGrd(ub.getVolumeSldr(), ub.getAddPlaylistBtn(),ub.getLyricsBtn());
			
			
			//renderiza botões do player
			GridPane playerCtrlGrd = gr.playerCtrlGrd(ub.getPauseBtn(), ub.getBackBtn(), ub.getProxBtn());
			
			
			
			//agrupa tag, volume, tempo e botão de abrir arquivo
			GridPane agrupaTagsGrd = new GridPane();
			agrupaTagsGrd.add(ue.getCapaAlbumGrd(), 0, 0);
			agrupaTagsGrd.add(tempoMusicaGrd,0,1);
			agrupaTagsGrd.add(tagMusicaGrd,0,2);
			agrupaTagsGrd.add(playerCtrlGrd, 0, 3);
			agrupaTagsGrd.add(volumePauseGrd, 0, 4);
			agrupaTagsGrd.add(btnEscolheArquivo, 0, 5);
			
			
			
			root.setCenter(agrupaTagsGrd);
			
			ub.setNextBackDisabled();
			proxMscItem.setDisable(true);
			antMscItem.setDisable(true);
			
			
			EventHandler<ActionEvent> abrirLetras = (e -> {
				uf.setLyricsF(fc.selecionaArquivo(2).showOpenDialog(primaryStage));
				if (uf.getLyricsF() != null) {
					Stage lyricsStage;
					try {
						lyricsStage = ls.showLyrics(uf);
						t = new Timeline(ls.atualizaLrc(ue));
						t.setCycleCount(Timeline.INDEFINITE);
						t.play();
						
						primaryStage.setOnCloseRequest(e1 -> {
							lyricsStage.close();
						});
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			});
			
			abrirLItem.setOnAction(abrirLetras);
			ub.setActionLyricsBtn(abrirLetras);
			
			
			
			EventHandler<ActionEvent> abreLink = ( e -> {
				TextInputDialog dialog = new TextInputDialog("Default Text");
				dialog.setTitle("Abrir Link");
	            dialog.setHeaderText("Insira o link abaixo:");
	            dialog.setContentText("Link:");
	            
	            Optional<String> result = dialog.showAndWait();
	            if(result.isPresent()) {
	            	try {
						mc.openLink(primaryStage, result.get());
					} catch (Exception e1) {
						e1.printStackTrace();
					}
	            }
	            
			});
			abrirTItem.setOnAction(abreLink);
			
			
			
			
			
			EventHandler<ActionEvent> addPlaylist = (e -> {
				if (uf.getPlaylistF() == null) {
					uf.setPlaylistF(fc.selecionaArquivo(1).showOpenDialog(primaryStage));
				}
				if (uf.getMusicF() != null) {
					try {
						pc.saveToPlaylist(uf);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			});
			
			ub.setActionAddPlaylistBtn(addPlaylist);
			addPlayItem.setOnAction(addPlaylist);
			
			
		
			EventHandler<ActionEvent> abrirMusica = (
				e -> {
					try {
						//para musica atual (se houver)
						mc.closePlayer();
						ue.ResetElements(ir.mscCapa());
						ub.ResetElements();
						
						//abre seletor de arquivo
						uf.setMusicF(fc.selecionaArquivo(0).showOpenDialog(primaryStage));
						
						if (uf.getMusicF() != null) {
							String fPath = uf.getMusicF().getPath();
							String[] strFatia = fPath.split("\\.");
							
							if (strFatia[strFatia.length-1].toLowerCase().equals("m3u8") || strFatia[strFatia.length-1].toLowerCase().equals("m3u") ){
								
								uf.mscAsPlaylist();
								ub.setNextBackEnabled();
								proxMscItem.setDisable(false);
								antMscItem.setDisable(false);
								uf.setPlaylistL(pc.openPlaylist(uf));
								
								//CORRIGIR ISSO, COLOCAR SEPARADO
								Thread t = abrePlaylist();
								
								t.start();
								
								EventHandler<ActionEvent> proxMsc = e1 -> {
									mc.closePlayer();
									t.interrupt();
								};
								ub.setActionProxBtn(proxMsc);
								
								
								
								EventHandler<ActionEvent> anterMsc = (e1 -> {
									mc.closePlayer();
									t.interrupt();
									uf.setPlaylistPos(uf.getPlaylistPos() - 2);
								});
								ub.setActionBackBtn(anterMsc);
								
								
							} else {
								//abre player
								if (uf.getMusicF() != null) {
									mc.openMusic(primaryStage, uf);
									//corrige player
									AtualizaPlayer();
								}
								ub.setNextBackDisabled();
							}
							
						
						}} catch (Exception e1) {
							// Auto-generated catch block
							e1.printStackTrace();
						}
					}
				);
			
			
				btnEscolheArquivo.setOnAction(abrirMusica);
				abrirAItem.setOnAction(abrirMusica);
				fMusicaItem.setOnAction(e -> {
					try {
						mc.closePlayer();
						ue.ResetElements(ir.mscCapa());
						ub.ResetElements();
					} catch (Exception e1) {
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
					mc.closePlayer();
					
					
		            if (db.hasFiles()) {
		            	uf.setMusicF(db.getFiles().get(0)); 
		                try {
		                if (uf.getMusicF() != null) {
		                	String fPath = uf.getMusicF().getPath();
							String[] strFatia = fPath.split("\\.");
							
							if (strFatia[strFatia.length-1].toLowerCase().equals("m3u8") || strFatia[strFatia.length-1].toLowerCase().equals("m3u") ){
								
								uf.mscAsPlaylist();
								uf.setPlaylistL(pc.openPlaylist(uf));
								ub.setNextBackEnabled();
								proxMscItem.setDisable(false);
								antMscItem.setDisable(false);
								
								//CORRIGIR ISSO, COLOCAR SEPARADO
								Thread t = abrePlaylist();
								
								t.start();
								
								EventHandler<ActionEvent> proxMsc = e1 -> {
									mc.closePlayer();
									t.interrupt();
								};
								ub.setActionProxBtn(proxMsc);
								proxMscItem.setOnAction(proxMsc);
								
								
								EventHandler<ActionEvent> anterMsc = (e1 -> {
									mc.closePlayer();
									t.interrupt();
									uf.setPlaylistPos(uf.getPlaylistPos() - 2);
								});
								ub.setActionBackBtn(anterMsc);
								antMscItem.setOnAction(anterMsc);
								
							} else {
								//abre player
								mc.openMusic(primaryStage, uf);
								//corrige player -----> MELHORAR ISSO, MUITA DEPENDÊNCIA EM UM MÉTODO!!!!
								AtualizaPlayer();
								ub.setNextBackDisabled();
							}
		                	}
		                
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
	
	
	
	
	
	private Thread abrePlaylist() {
		int tamanhoPl = uf.getPlaylistL().size();
		
		Thread t = new Thread (() -> {
			for (uf.setPlaylistPos(0); uf.getPlaylistPos()<=tamanhoPl; uf.setPlaylistPos(uf.getPlaylistPos() + 1)) {
				if (Thread.currentThread().isInterrupted()) break;
				
				uf.setMusicF(uf.getPlaylistL().get(uf.getPlaylistPos()));
				try {
					
					Platform.runLater(() -> {
						try {
							AtualizaPlayer();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					});
					
					CountDownLatch latch = new CountDownLatch(1);
						mc.openMusic(uf, () -> latch.countDown());
					latch.await();
					
				} catch (Exception e1) {
					// Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		return t;
	}

	public void AtualizaPlayer() throws Exception {
		Musica m1 = tic.musicTagInfo(uf);
		ue.SetUIElements(tic.musicTagInfo(uf),ir.mscCapa(m1));
		mc.mscPlayerControles(ue, ub, m1);
	}


	public static void main(String[] args) {
		launch(args);
	}
}
