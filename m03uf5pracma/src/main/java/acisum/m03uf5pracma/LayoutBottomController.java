package acisum.m03uf5pracma;

import acisum.m03uf5pracma.Utils.Utils;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
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
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;


public class LayoutBottomController extends Controller implements Initializable {

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
    private Label playerTime;

    @FXML
    private ProgressBar playerVolumeProgress;

    @FXML
    private Slider playerVolumeSlider;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        int sliderMax = 100;
        int sliderMin = 0;
        Double sliderMinWidth = Slider.USE_COMPUTED_SIZE;
        Double sliderMaxWidh = Double.MAX_VALUE;

        asignProgressTosSlider(playerVolumeSlider, playerVolumeProgress, sliderMin, sliderMax, sliderMinWidth, sliderMaxWidh);
        asignProgressTosSlider(playerSliderSlider, playerSliderProgress, sliderMin, sliderMax, sliderMinWidth, sliderMaxWidh);
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
    @FXML
    void actionPlayerBackward(ActionEvent event) {

    }

    @FXML
    void actionPlayerForward(ActionEvent event) {

    }

    @FXML
    void actionPlayerMuteUnMute(ActionEvent event) {

    }

    @FXML
    void actionPlayerPlayPause(ActionEvent event) {

    }

    @FXML
    void actionPlayerShuffleMode(ActionEvent event) {

    }

    @FXML
    void actionPlayerStop(ActionEvent event) {

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


