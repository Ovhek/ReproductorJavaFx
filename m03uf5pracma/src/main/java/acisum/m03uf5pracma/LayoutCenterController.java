package acisum.m03uf5pracma;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
import acisum.m03uf5pracma.Utils.Utils;
import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
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

    // Creacion de observable list(Actuan a modo de arrayList pero en javaFx)
    //Se asignan de clase playlist para trabajar con la clase y su atributos
    PlayList playList;
    ObservableList<Fmp3> elements = FXCollections.observableArrayList();
    ObservableList<Fmp3> filtroListas = FXCollections.observableArrayList();

    //Creacion de varaibles y declaramos los elementos de el .fxml
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
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lvMP3.setItems(elements);
    }

    //cuando cliquemos en add se nos abrira el explorador de archivos para poder 
    //agregar canciones
    @FXML
    public void onActionBtnAdd(ActionEvent event) {

        //sacamos el path y abrimos el explorador de archivos
        if (playList != null) {
            Path path = FileUtils.getMP3Fromfile();
            File file = path.toFile();
            //creamos iterator que comprobara si el fichero introducio existe para impedir añadirlo
            if (file != null) {
                String root = Utils.normalizeURLFormat(path.toString());
                String fileName = file.getName();
                Fmp3 fmp3 = new Fmp3(fileName, "", "", root);
                String extension = fileName.split("\\.")[1];
                boolean igual = false;
                
                Iterator<Fmp3> comprobarNombre = elements.iterator();
                
                //comprobamos si existe el archivo en nuestra lista
                while (comprobarNombre.hasNext()){
                    if(comprobarNombre.next().toString().equals(fileName)){
                        igual = true;
                        break;
                    } else {
                        igual = false;
                    }
                }
                
                //si esta repetido no lo metemos y salta un alert
                //de lo contraio lo añadimos
                if (!igual) {
                    
                    //comprobamos extension y si no es mp3 salta alert
                    //de lo contrario lo añadimos
                    if (extension.equals("mp3")) {
                        elements.add(fmp3);
                        if (playList.getNombreLista().toLowerCase().contains(this.textSearch.getText().toLowerCase())) {
                            this.filtroListas.add(fmp3);
                        }
                        lvMP3.refresh();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText(null);
                        alert.setTitle("Error");
                        alert.setContentText("La extension de el fichero es incorrecta");
                        alert.showAndWait();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("Error");
                    alert.setContentText("El fichero ya esta en esta lista, prueba con otro!");
                    alert.showAndWait();

                }
            }
        }

    }

    //cuando clicamos en btn delete cogemos la posicion y la eliminamos de la lista y actualizamos
    @FXML
    public void onActionBtnDelete(ActionEvent event) {
        if (playList != null) {
            int posicioElementSeleccionat = lvMP3.getSelectionModel().getSelectedIndex();
            if (posicioElementSeleccionat > -1) {
                elements.remove(posicioElementSeleccionat);
                filtroListas.remove(posicioElementSeleccionat);
                lvMP3.refresh();

            }
        }
    }

    //cuando escribimos en la barra de busqueda se nos actualizara automaticamente la lista mientras escribimos
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

    //declaramos setter sobre variable de tipo playlist
    public void setPlayList(PlayList playList) {
        this.playList = playList;
        labListTitle.setText(this.playList.getNombreLista());
        elements = this.playList.getPlayList();
        lvMP3.setItems(elements);
    }

    //declaramos funcion borrar lista que limpia un label, elimina una playlist, limpia elements y el listview
    public void borrarLista() {

        labListTitle.setText("List Title");
        this.playList = null;
        elements.clear();
        lvMP3.setItems(elements);
    }

    //getters
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
