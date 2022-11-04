package acisum.m03uf5pracma;

import acisum.m03uf5pracma.Utils.Utils;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    private Slider playerSlider;

    @FXML
    private Button playerStop;

    @FXML
    private Label playerTime;

    @FXML
    private Slider playerVolume;

    MediaPlayer mediaPlayer = null;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
        try {
            String path = Utils.getMp3Path(this, "A");
            mediaPlayer = Utils.getMediaPlayer(path);
            //String path = Utils.getMp3Path(this, "A");
            //mediaPlayer = Utils.getMediaPlayer(path);
        } catch (MediaException e) {
            System.out.println("AAAA");
        }
        
        
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
    void mouseDragDetectedPlayerVolume(MouseEvent event) {

    }

    @FXML
    void mouseDragDonePlayerSlider(DragEvent event) {

    }

    @FXML
    void mouseDragDonePlayerVolume(DragEvent event) {

    }

    @FXML
    void mousePressedPlayerSlider(MouseEvent event) {

    }

    @FXML
    void mousePressedPlayerVolume(MouseEvent event) {

    }
    
}


