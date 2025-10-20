# MusicaControl 🎵

MusicaControl é um **player de música em JavaFX** que permite abrir, tocar, pausar e controlar músicas localmente, exibindo informações de tags (nome, artista, duração) e progresso de reprodução em tempo real. É um projeto educativo e funcional que combina **JavaFX** e a biblioteca **StreamPlayer**.

<img width="400" height="682" alt="Captura de Tela 2025-10-19 às 01 18 12" src="https://github.com/user-attachments/assets/9b3b0b22-570c-4477-a0e8-989f6fa403f9" />



---

## Funcionalidades

- Abrir arquivos de música (`.mp3`, `.flac`, `.wav`) usando um **FileChooser**.
- Tocar, pausar e retomar a música.
- Controle de volume com **Slider**.
- Exibição de **tempo decorrido** e barra de progresso.
- Mostra informações de tags da música (nome, artista, duração).
- Ícones dinâmicos de **Play/Pause** usando imagens codificadas em Base64.
- Limpeza e encerramento correto do player ao fechar a aplicação.

---

## Tecnologias

- Java 21+
- JavaFX
- CSS
- java-stream-player-10.0.2
- jaudiotagger-3.0.1
- jflac-codec-1.5.2
- jlayer-1.0.1
- jse-spi-aac-1.0.0
- mp3spi-1.9.5-1
- tritonus-share-0.3.7-2
- vorbisspi-1.0.3-1
- base64

  
---

## Estrutura do Projeto
application/
│
├─ MusicaControl.java # Controlador principal do player
├─ TagInfoControl.java # Extrai informações das tags de música
├─ Musica.java # Classe de modelo para armazenar dados da música
└─ img/ # Imagens utilizadas no projeto (opcional)

## Observações
- O player ainda não suporta streaming online; apenas arquivos locais.
- Recomendado usar arquivos com tags corretamente preenchidas para exibição completa das informações.

## Próximos Passos / Melhorias
- Suporte a playlists.
- Reproduzir múltiplos formatos de áudio com melhor compatibilidade.
- Barra de progresso clicável para pular para diferentes partes da música.
- Capa com bordas arredondadas
- Importação de playlists
- Botão de adicionar à playlist atual ou abrir uma para colocar
- Letras da música em nova janela
- Menus superiores no mac
- Integração com notificações de música do Sistema Operacional nativo
- Exportação para plataformas
- Design de ícone para identidade visual
- Abrir links estáticos
- Abrir transmissões
- Abrir arquivos, links e transmissões AAC
- Discord Rich Presence

