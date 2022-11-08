package acisum.m03uf5pracma;

import acisum.m03uf5pracma.Utils.Utils;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import org.controlsfx.glyphfont.FontAwesome;
import org.controlsfx.glyphfont.Glyph;


public class LayoutBottomController extends Controller implements Initializable {

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

    int sliderMax = 100;
    int sliderMin = 0;
    Double sliderMinWidth = Slider.USE_COMPUTED_SIZE;
    Double sliderMaxWidh = Double.MAX_VALUE;
    
    Media media = null;
    MediaPlayer mediaPlayer = null;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
        media = new Media(Utils.getMp3Path(this, "01"));
        mediaPlayer = new MediaPlayer(media);
        
        mediaPlayer.currentTimeProperty().addListener((ov, oldVal, newVal) -> {
            playerTimeChanged(ov,oldVal,newVal);
            if (mediaPlayer.getTotalDuration().toSeconds() - mediaPlayer.getCurrentTime().toSeconds() >= 0){
                //nextSong();
            }
        });
        
        mediaPlayer.setOnReady(new Runnable() {
            @Override
            public void run() {
                sliderMax =(int) mediaPlayer.getTotalDuration().toSeconds();
                asignProgressTosSlider(playerSliderSlider, playerSliderProgress, sliderMin, sliderMax, sliderMinWidth, sliderMaxWidh);
            }
        });
        asignProgressTosSlider(playerVolumeSlider, playerVolumeProgress, sliderMin, sliderMax, sliderMinWidth, sliderMaxWidh);
        playerSliderSlider.setOnMousePressed((event) ->{
            playerTimePressed(event);
        });
        playerSliderSlider.setOnMouseDragged((event) ->{
            playerTimeDragged(event);
        });
        
        playerVolumeSlider.setValue(mediaPlayer.getVolume()*100);
        
        playerVolumeSlider.setOnMousePressed((event) ->{
            setVolume();
        });
        playerVolumeSlider.setOnMouseDragged((event) ->{
            setVolume();
        });
    }
    private void setVolume(){
        if(mediaPlayer != null)
            mediaPlayer.setVolume(playerVolumeSlider.getValue()/100);
    }
    private void playerTimePressed(MouseEvent event) {
        mediaPlayer.seek(Duration.seconds(playerSliderSlider.getValue()));
    }
    private void playerTimeDragged(MouseEvent event) {
        mediaPlayer.seek(Duration.seconds(playerSliderSlider.getValue()));
    }
    private void playerTimeChanged(ObservableValue<? extends Duration> ov, Duration oldVal, Duration newVal) {
        playerSliderSlider.setValue(newVal.toSeconds());
        
        int minutes = (int)mediaPlayer.getCurrentTime().toMinutes();
        int seconds = (int)mediaPlayer.getCurrentTime().toSeconds();

        playerTimeCurrent.setText(String.format("%02d:%02d", timeManager(minutes),timeManager(seconds)));
        
        int minutesLeft = (int)mediaPlayer.getTotalDuration().toMinutes() - minutes;
        int secondsLeft = (int)mediaPlayer.getTotalDuration().toSeconds()- seconds;
        playerTimeLeft.setText(String.format("%02d:%02d", timeManager(minutesLeft),timeManager(secondsLeft)));
    }
    private int timeManager (int time){
        int mTime = time;
        int timeManager  = (mTime/60);
        
        return timeManager > 0 ? (mTime - 60*timeManager) : mTime;
    }
    private void asignProgressTosSlider(Slider sl, ProgressBar pb, int slMinValue, int slMaxValue,Double slMinWidth, Double slMaxWidth){

        sl.setMin(slMinValue);
        sl.setMax(slMaxValue);
        sl.setMinWidth(slMinWidth);
        sl.setMaxWidth(slMaxWidth);
        
        pb.setMinWidth(sl.getWidth());
        pb.setMaxWidth(slMaxWidth);
        
        sl.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
            pb.setProgress(new_val.doubleValue() /slMaxValue);
        });
    }
    private int FORWARD_BACKWARD_SECONDS = 10;
    @FXML
    void actionPlayerBackward(ActionEvent event) {
        mediaPlayer.seek(mediaPlayer.getCurrentTime().subtract(Duration.seconds(FORWARD_BACKWARD_SECONDS)));
    }

    @FXML
    void actionPlayerForward(ActionEvent event) {
        mediaPlayer.seek(mediaPlayer.getCurrentTime().add(Duration.seconds(FORWARD_BACKWARD_SECONDS)));
    }

    private boolean mute = false;
    private Double currentVol = 0.0;
    
    @FXML
    void actionPlayerMuteUnMute(ActionEvent event) {
        if(mute){
            muteUnMuteGlyph.setIcon("");
            mediaPlayer.setVolume(currentVol/100);
            playerVolumeSlider.setValue(currentVol);
        }
        else{
            currentVol = mediaPlayer.getVolume()*100;
            mediaPlayer.setVolume(0);
            playerVolumeSlider.setValue(0);
            muteUnMuteGlyph.setIcon("");
        }
        mute = !mute;
    }

    private Timer timer;
    private TimerTask rask;
    private boolean playing = false;
    @FXML
    void actionPlayerPlayPause(ActionEvent event) {
        if (playing){
            mediaPlayer.pause();
            playPauseGlyph.setIcon("PLAY");
        }
        else{
            mediaPlayer.play();
            playPauseGlyph.setIcon("PAUSE");
        }
        playing = !playing;
    }

    private void nextMedia(){
        
    }
    @FXML
    void actionPlayerShuffleMode(ActionEvent event) {

    }

    @FXML
    void actionPlayerStop(ActionEvent event) {
        mediaPlayer.stop();
        playing = false;
        playPauseGlyph.setIcon("PLAY");
        mediaPlayer.seek(Duration.ZERO);
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


