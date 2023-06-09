package acisum.m03uf5pracma;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
import acisum.m03uf5pracma.Utils.Utils;
import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
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

    //Playlist
    PlayList playList;
    //Elementos MP3
    ObservableList<Fmp3> elements = FXCollections.observableArrayList();
    //Filtros de la lista MP3
    ObservableList<Fmp3> filtroListas = FXCollections.observableArrayList();

    @FXML
    public Label labListTitle;
    @FXML
    public ListView<Fmp3> lvMP3;
    @FXML
    public TextField textSearch;
    @FXML
    public Button btnSortMP3;
    @FXML
    public Button btnDeleteMP3;
    @FXML
    public Button btnAddMP3;

    /**
     * Función que inicializa la escena, añade los elementos al listview de mp3. 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lvMP3.setItems(elements);
        
        //Al seleccionar un mp3 del listado llama a la función encargada de instanciar el mediaplayer
        lvMP3.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                LayoutBottomController layout_bottom = (LayoutBottomController)(controllers.get("LayoutBottomController"));
                layout_bottom.nextSong = false;
                layout_bottom.initMediaPlayer();
            }
        });
    }

    /**
     * Función encargada de añadir archivos MP3 a la Playlist.
     */
    @FXML
    public void onActionBtnAdd(ActionEvent event) {

        if (playList != null) {
            Path path = FileUtils.getMP3Fromfile();
            File file = path.toFile();
            
            if(file !=null){
                String root = file.toURI().toString();
                String fileName = file.getName();
                Fmp3 fmp3 = new Fmp3(fileName, "", "", root);

                elements.add(fmp3);
                if (playList.getNombreLista().toLowerCase().contains(this.textSearch.getText().toLowerCase())) {
                    this.filtroListas.add(fmp3);
                }
                lvMP3.refresh();
            }
        }
        

    }
    
    /**
     * Función encargada de eliminar un MP3 de la playlist.
     */
    @FXML
    public void onActionBtnDelete(ActionEvent event){
        if(playList != null){
            int posicioElementSeleccionat = lvMP3.getSelectionModel().getSelectedIndex();
            if (posicioElementSeleccionat > -1){
                elements.remove(posicioElementSeleccionat);
                filtroListas.remove(posicioElementSeleccionat);
                lvMP3.refresh();
               
            }
        }
    }
    
    /**
     * Función encargada de filtrar la playlist mediante un string.
     */
    @FXML
    private void filtrarNombre(KeyEvent event) {

        String filtroNombre = this.textSearch.getText();

        // Si el texto del nombre esta vacio, seteamos la tabla de personas con el original
        if (filtroNombre.isEmpty()) {
            this.lvMP3.setItems(elements);
        } else {

            // Limpio la lista
            this.filtroListas.clear();

            for (Fmp3 n : this.elements) {
                if (n.getTitle().toLowerCase().contains(filtroNombre.toLowerCase())) {
                    this.filtroListas.add(n);
                }
            }
            this.lvMP3.setItems(filtroListas);
        }
    }

    public void setPlayList(PlayList playList) {
        this.playList = playList;
        labListTitle.setText(this.playList.getNombreLista());
        elements = this.playList.getPlayList();
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

    public TextField getTextSearch() {
        return textSearch;
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
