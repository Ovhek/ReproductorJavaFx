package acisum.m03uf5pracma;

import acisum.m03uf5pracma.Utils.Utils;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import model.Fmp3;
import org.controlsfx.glyphfont.Glyph;

/**
 Clase encargada del reproductor MP3
 */
public class LayoutBottomController extends Controller implements Initializable {

    //Sonido seleccionado actualmente
    private Fmp3 sonido_seleccionado;

    @FXML
    Glyph muteUnMuteGlyph;
    @FXML
    Glyph playPauseGlyph;
    @FXML
    private Button playerBackward;

    @FXML
    private Button playerForward;

    @FXML
    private Button playerLoop;

    @FXML
    private Button playerMuteUnMute;

    @FXML
    private Button playerPlayPause;

    @FXML
    private Button playerShuffleMode;

    @FXML
    private ProgressBar playerSliderProgress;

    @FXML
    private Slider playerSliderSlider;

    @FXML
    private Button playerStop;

    @FXML
    private Label playerTimeCurrent;

    @FXML
    private Label playerTimeLeft;

    @FXML
    private ProgressBar playerVolumeProgress;

    @FXML
    private Slider playerVolumeSlider;

    //Valor máximo del Slider.
    int sliderMax = 100;
    //Valor Minimo del slider.
    int sliderMin = 0;
    Double sliderMinWidth = Slider.USE_COMPUTED_SIZE;
    Double sliderMaxWidh = Double.MAX_VALUE;

    Media media = null;
    MediaPlayer mediaPlayer = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
            
    }
    
   public boolean nextSong = false;
   
   /**
    * Función que inicializa el media player. 
    */
    public void initMediaPlayer(){
        if(mediaPlayer != null)
            reset();
        
        if(!nextSong)
            getSoundOfControllerCenter();
        else
            mediaPlayer.play();
        
        mediaPlayer = Utils.getMediaPlayer(sonido_seleccionado.getPath());
        init();
    }
    /**
     * Función que reinicia el mediaPlayer, mueve la reproducción al inicio, cambia el icono del play y cambia el slider del volumen
     */
    private void reset(){
        if(!nextSong)
            mediaPlayer.stop();
        
        mediaPlayer.seek(Duration.ZERO);
        currentVol = mediaPlayer.getVolume() * 100;
        playPauseGlyph.setIcon("PLAY");
        playerVolumeSlider.setValue(mediaPlayer.getVolume() * 100);
        playing = false;
    }
    /**
     * Función que inicializa el reproductor, Añade un listener que se ejecuta cuando el media player está listo.
     * Cuando el mediaplayer está listo asigna los sliders y progress bar así como el volumen.
     */
    public void init(){
         if (mediaPlayer != null) {

            mediaPlayer.currentTimeProperty().addListener((ov, oldVal, newVal) -> {
                playerTimeChanged(ov, oldVal, newVal);
                if (mediaPlayer.getTotalDuration().toSeconds() - mediaPlayer.getCurrentTime().toSeconds() <= 1) {
                    nextSong();
                }
            });

            mediaPlayer.setOnReady(new Runnable() {
                @Override
                public void run() {
                    sliderMax = (int) mediaPlayer.getTotalDuration().toSeconds();
                    asignProgressTosSlider(playerSliderSlider, playerSliderProgress, sliderMin, sliderMax, sliderMinWidth, sliderMaxWidh);
                    asignProgressTosSlider(playerVolumeSlider, playerVolumeProgress, sliderMin, sliderMax, sliderMinWidth, sliderMaxWidh);
                    playerSliderSlider.setOnMousePressed((event) -> {
                        playerTimePressed(event);
                    });
                    playerSliderSlider.setOnMouseDragged((event) -> {
                        playerTimeDragged(event);
                    });

                    playerVolumeSlider.setValue(mediaPlayer.getVolume() * 100);

                    playerVolumeSlider.setOnMousePressed((event) -> {
                        setVolume();
                    });
                    playerVolumeSlider.setOnMouseDragged((event) -> {
                        setVolume();
                    });
                }
            });
        }
    }

    /**
     * Función que pasa a la siguiente canción. Toma la posición del list view central donde se encuentran los temas.
     * */
    private void nextSong(){
        reset();
        mediaPlayer.stop();
        int nextPosition = ((LayoutCenterController) controllers.get(LayoutCenterController.class.getSimpleName())).getLvMP3().getSelectionModel().getSelectedIndex();
        if(nextPosition + 1 >=((LayoutCenterController) controllers.get(LayoutCenterController.class.getSimpleName())).getElements().size()){
                ((LayoutCenterController) controllers.get(LayoutCenterController.class.getSimpleName())).getLvMP3().getSelectionModel().select(0);
        }else{
            ((LayoutCenterController) controllers.get(LayoutCenterController.class.getSimpleName())).getLvMP3().getSelectionModel().select(nextPosition+1);
        }
        
        if(nextPosition <= ((LayoutCenterController) controllers.get(LayoutCenterController.class.getSimpleName())).getElements().size())
            sonido_seleccionado = ((LayoutCenterController) controllers.get(LayoutCenterController.class.getSimpleName())).getElements().get(nextPosition);
        initMediaPlayer();
        
        
    }
    /**
     * Función la cual cambia el volumen.
     */
    private void setVolume() {
        if (mediaPlayer != null) {
            mediaPlayer.setVolume(playerVolumeSlider.getValue() / 100);
        }
    }

    /**
     Función que se ejecuta al pressionar el slider con el botón, cambia la reproducción del media player a la posición clicada.
     */
    private void playerTimePressed(MouseEvent event) {
        mediaPlayer.seek(Duration.seconds(playerSliderSlider.getValue()));
    }

    /**
     * Función que se ejecuta al arrastrar el slider. Cambia el seek del mediaplayer a la posición arrastrada.
     */
    private void playerTimeDragged(MouseEvent event) {
        mediaPlayer.seek(Duration.seconds(playerSliderSlider.getValue()));
    }

    /**
     * Función que se ejecuta al cambiar el tiempo del reproductor. Cambia los textos del tiempo actual de la cancion y el restante.
     */
    private void playerTimeChanged(ObservableValue<? extends Duration> ov, Duration oldVal, Duration newVal) {
        playerSliderSlider.setValue(newVal.toSeconds());

        int minutes = (int) mediaPlayer.getCurrentTime().toMinutes();
        int seconds = (int) mediaPlayer.getCurrentTime().toSeconds();

        playerTimeCurrent.setText(String.format("%02d:%02d", timeManager(minutes), timeManager(seconds)));

        int minutesLeft = (int) mediaPlayer.getTotalDuration().toMinutes() - minutes;
        int secondsLeft = (int) mediaPlayer.getTotalDuration().toSeconds() - seconds;
        playerTimeLeft.setText(String.format("%02d:%02d", timeManager(minutesLeft), timeManager(secondsLeft)));
    }

    /**
     * Función encargada de manejar el tiempo. Cuando la variable es mayor a 60 devuelve 0.
     */
    private int timeManager(int time) {
        int mTime = time;
        int timeManager = (mTime / 60);

        return timeManager > 0 ? (mTime - 60 * timeManager) : mTime;
    }

    /**
     * Función que asigna un progress bar a un slider.
     */
    private void asignProgressTosSlider(Slider sl, ProgressBar pb, int slMinValue, int slMaxValue, Double slMinWidth, Double slMaxWidth) {

        sl.setMin(slMinValue);
        sl.setMax(slMaxValue);
        sl.setMinWidth(slMinWidth);
        sl.setMaxWidth(slMaxWidth);

        pb.setMinWidth(sl.getWidth());
        pb.setMaxWidth(slMaxWidth);

        sl.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
            pb.setProgress(new_val.doubleValue() / slMaxValue);
        });
    }
    
    // Parametro que indica el tiempo por defecto hacia atras y hacia delante.
    private int FORWARD_BACKWARD_SECONDS = 10;

    /**
     * Función que se ejecuta al presionar el botón de ir hacia detrás en la canción. Resta 10 segundos al seek.
     */
    @FXML
    void actionPlayerBackward(ActionEvent event) {
        if(mediaPlayer != null){
        mediaPlayer.seek(mediaPlayer.getCurrentTime().subtract(Duration.seconds(FORWARD_BACKWARD_SECONDS)));}
    }

     /**
     * Función que se ejecuta al presionar el botón de ir hacia delante en la canción. Aumenta 10 segundos al seek.
     */
    @FXML
    void actionPlayerForward(ActionEvent event) {
        if(mediaPlayer != null){
        mediaPlayer.seek(mediaPlayer.getCurrentTime().add(Duration.seconds(FORWARD_BACKWARD_SECONDS)));}
    }

    private boolean mute = false;
    private Double currentVol = 0.0;

    /**
     * Función encargada de silenciar o desilenciar el volumen del mediaplayer.
     */
    @FXML
    void actionPlayerMuteUnMute(ActionEvent event) {
        
        if(mediaPlayer != null){
        if (mute) {
            muteUnMuteGlyph.setIcon("");
            mediaPlayer.setVolume(currentVol / 100);
            playerVolumeSlider.setValue(currentVol);
        } else {
            currentVol = mediaPlayer.getVolume() * 100;
            mediaPlayer.setVolume(0);
            playerVolumeSlider.setValue(0);
            muteUnMuteGlyph.setIcon("");
        }
        mute = !mute;}
    }

    private Timer timer;
    private TimerTask rask;
    private boolean playing = false;
    
    /**
     * Función encargada de reproducir o pausar el mediaplayer.
     */
    @FXML
    void actionPlayerPlayPause(ActionEvent event) {
            if (playing) {
                mediaPlayer.pause();
                playPauseGlyph.setIcon("PLAY");
            } else {
                mediaPlayer.play();
                playPauseGlyph.setIcon("PAUSE");
            }
            playing = !playing;
    }

    /**
     * Función encargada de obtener el controlador de sonido.
     */
    private void getSoundOfControllerCenter() {

        int position = ((LayoutCenterController) controllers.get(LayoutCenterController.class.getSimpleName())).getLvMP3().getSelectionModel().getSelectedIndex();
        if (position > -1) {
            sonido_seleccionado = ((LayoutCenterController) controllers.get(LayoutCenterController.class.getSimpleName())).getElements().get(position);
        }
    }

    /**
     Función que obtiene el sonido seleccionada.
     */
    public Fmp3 getSonido_seleccionado() {
        return sonido_seleccionado;
    }

    /**
     * Función que settea el sonido seleccionado
     */
    public void setSonido_seleccionado(Fmp3 sonido_seleccionado) {
        this.sonido_seleccionado = sonido_seleccionado;
    }

    /**
     * Función del modo aleatorio. Al final no se ha podido implementar.
     */
    @FXML
    void actionPlayerShuffleMode(ActionEvent event) {

    }

    /**
     * Función encargada de parar el mediaplayer.
     */
    
    @FXML
    void actionPlayerStop(ActionEvent event) {

        if (mediaPlayer != null) {
            mediaPlayer.stop();
            playing = false;
            playPauseGlyph.setIcon("PLAY");
            mediaPlayer.seek(Duration.ZERO);
        }
    }

    
    @FXML
    void mouseDragDetectedPlayerSlider(MouseEvent event) {

    }

    @FXML
    void mouseDragDonePlayerSlider(DragEvent event) {

    }

    @FXML
    void mousePressedPlayerSlider(MouseEvent event) {

    }

}