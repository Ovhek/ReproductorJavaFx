package acisum.m03uf5pracma;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
import com.sun.media.jfxmedia.track.Track;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.PlayList;
import model.PlayListComparator;

/**
 * FXML Controller class
 *
 * @author Cole
 */
public class LayoutLeftController extends Controller implements Initializable {

    ObservableList<PlayList> elements = FXCollections.observableArrayList();
    ObservableList<PlayList> filtroListas = FXCollections.observableArrayList();
    //ObservableList<String> elements = FXCollections.observableArrayList();

    @FXML
    public Label txt_iv_playlist;

    @FXML
    private ListView<PlayList> lv_playlists;

    @FXML
    private Button btn_add_playlist;

    @FXML
    private Button btn_delete_playlist;

    @FXML
    private Button btn_filter_playlist;

    @FXML
    private TextField txt_buscar;

    private Label getTxtIvPlaylist() {
        return txt_iv_playlist;
    }

    private Languages lang = Languages.ENGLISH;
    
    /**
     * Función que se ejecuta al inicializar la escena. Añade los elementos al ListView.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        /*Con el setItems lo que hacemos es que los elementos que aÃ±adamos
        se pondran directamente en nuestro listView*/
        lv_playlists.setItems(elements);
       
    }
    

    /**
     * Función encargada de seleccionar la playlist y añadirla como lista actual.
     */
    @FXML
    private void onActionBtnSelect(ActionEvent event){
        int posicioElementSeleccionat = lv_playlists.getSelectionModel().getSelectedIndex();
        
        if (posicioElementSeleccionat > -1){
            PlayList p = elements.get(posicioElementSeleccionat);
            
            ((LayoutCenterController)controllers.get(LayoutCenterController.class.getSimpleName())).setPlayList(p);
        }
    }

    /**
     * Función que se encarga de añadir una nueva Playlist.
     */
    @FXML
    private void onActionBtnAdd(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/acisum/m03uf5pracma/layoutLeftPopUp.fxml"));

            Parent root = loader.load();

            LayoutLeftPopUpController controlador = loader.getController();
            controlador.initAttributes(elements);

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            LayoutTopController layout_top = (LayoutTopController)(controllers.get("LayoutTopController"));
            stage.setTitle(layout_top.appBundle.getString("add_playlist_title"));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();

            PlayList n = controlador.getNombre();
            if (n != null) {
                this.elements.add(n);
                if (n.getNombreLista().toLowerCase().contains(this.txt_buscar.getText().toLowerCase())) {
                    this.filtroListas.add(n);
                }
                this.lv_playlists.refresh();

            }

        } catch (IOException ex) {
            Logger.getLogger(LayoutLeftController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Función encargada de eliminar una playlist.
     */
    @FXML
    private void onActionBtnEliminar(ActionEvent event) {

        // Recogemos en una variable la posicion del elemento seleccionado
        int posicioElementSeleccionat = lv_playlists.getSelectionModel().getSelectedIndex();

        // si la posicion es correcta eliminamos el elemento
        //que corresponde a la posicion mencionada, 
        // Puede que sea -1 en caso de no seleccionar posicion
        if (posicioElementSeleccionat > -1) {
            PlayList p = elements.get(posicioElementSeleccionat);
            String nombre = p.getNombreLista();
            String nombreCL =  ((LayoutCenterController)controllers.get(LayoutCenterController.class.getSimpleName())).getLabListTitle().getText();
            if(nombre.equals(nombreCL)){
                 ((LayoutCenterController)controllers.get(LayoutCenterController.class.getSimpleName())).borrarLista();
            }
            elements.remove(posicioElementSeleccionat);
            filtroListas.remove(posicioElementSeleccionat);
        }
    }
    

    /**
     * Función encargada de filtrar las playlist mediante un searc text.
     */
    @FXML
    private void filtrarNombre(KeyEvent event) {

        String filtroNombre = this.txt_buscar.getText();

        // Si el texto del nombre esta vacio, seteamos la tabla de personas con el original
        if (filtroNombre.isEmpty()) {
            this.lv_playlists.setItems(elements);
        } else {

            // Limpio la lista
            this.filtroListas.clear();

            for (PlayList n : this.elements) {
                if (n.getNombreLista().toLowerCase().contains(filtroNombre.toLowerCase())) {
                    this.filtroListas.add(n);
                }
            }
            this.lv_playlists.setItems(filtroListas);
        }
    }
    
    
    private boolean reverseSort = false;
    
    /*
    * Función encargada de ordenar la lista alfanuemricamente.
    **/
    @FXML
    private void onActionOrdenar(ActionEvent event){

        if (reverseSort){
            Collections.sort(elements,Collections.reverseOrder(new PlayListComparator()));
        }
        else{
            Collections.sort(elements,new PlayListComparator());
        }
        this.lv_playlists.setItems(elements);
        
        reverseSort = !reverseSort;
    }

    public ListView<PlayList> getLv_playlists() {
        return lv_playlists;
    }
}