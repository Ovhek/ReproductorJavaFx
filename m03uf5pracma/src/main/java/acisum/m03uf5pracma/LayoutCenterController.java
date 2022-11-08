package acisum.m03uf5pracma;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
import acisum.m03uf5pracma.Utils.Utils;
import java.net.URL;
import java.nio.file.Path;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import model.FileUtils;
import model.Fmp3;
import model.PlayList;

/**
 * FXML Controller class
 *
 * @author Cole
 */
public class LayoutCenterController extends Controller implements Initializable {

    PlayList playList;
    ObservableList<Fmp3> elements = FXCollections.observableArrayList();
    ObservableList<Fmp3> filtroListas = FXCollections.observableArrayList();

    @FXML
    public Label labListTitle;
    @FXML
    public ListView<Fmp3> lvMP3;
    @FXML
    public TextField textSearh;
    @FXML
    public Button btnSortMP3;
    @FXML
    public Button btnDeleteMP3;
    @FXML
    public Button btnAddMP3;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lvMP3.setItems(elements);
    }

    @FXML
    public void onActionBtnAdd(ActionEvent event) {

        if (playList != null) {
            Path path = FileUtils.getMP3Fromfile();
            
                String root = Utils.normalizeURLFormat(path.toString());
                String fileName = Utils.getFileName(root);
                Fmp3 fmp3 = new Fmp3(fileName, "", "", root);

                elements.add(fmp3);
                lvMP3.refresh();
            
        }
        

    }

    public void setPlayList(PlayList playList) {
        this.playList = playList;
        labListTitle.setText(this.playList.getNombreLista());
        elements = FXCollections.observableArrayList(this.playList.getPlayList());
        lvMP3.setItems(elements);
    }

    public void borrarLista() {

        labListTitle.setText("List Title");
        this.playList = null;
        elements.clear();
        lvMP3.setItems(elements);
    }

    public PlayList getPlayList() {
        return playList;
    }

    public ObservableList<Fmp3> getElements() {
        return elements;
    }

    public ObservableList<Fmp3> getFiltroListas() {
        return filtroListas;
    }

    public Label getLabListTitle() {
        return labListTitle;
    }

    public ListView<Fmp3> getLvMP3() {
        return lvMP3;
    }

    public TextField getTextSearh() {
        return textSearh;
    }

    public Button getBtnSortMP3() {
        return btnSortMP3;
    }

    public Button getBtnDeleteMP3() {
        return btnDeleteMP3;
    }

    public Button getBtnAddMP3() {
        return btnAddMP3;
    }

}
