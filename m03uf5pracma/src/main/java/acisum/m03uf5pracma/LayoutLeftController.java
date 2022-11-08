package acisum.m03uf5pracma;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
import java.io.IOException;
import java.net.URL;
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        /*Con el setItems lo que hacemos es que los elementos que añadamos
        se pondran directamente en nuestro listView*/
        lv_playlists.setItems(elements);
    }

    @FXML
    private void onActionBtnAdd(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/acisum/m03uf5pracma/layoutLeftPopUp.fxml"));

            Parent root = loader.load();

            LayoutLeftPopUpController controlador = loader.getController();
            controlador.initAttributes(elements);

            Scene scene = new Scene(root);
            Stage stage = new Stage();
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

    @FXML
    private void onActionBtnEliminar(ActionEvent event) {

        // Recogemos en una variable la posicion del elemento seleccionado
        int posicioElementSeleccionat = lv_playlists.getSelectionModel().getSelectedIndex();

        // si la posicion es correcta eliminamos el elemento
        //que corresponde a la posicion mencionada, 
        // Puede que sea -1 en caso de no seleccionar posicion
        if (posicioElementSeleccionat > -1) {
            elements.remove(posicioElementSeleccionat);
            filtroListas.remove(posicioElementSeleccionat);
        }
    }

    
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
    
    @FXML
    private void onActionOrdenar(ActionEvent event){
        
    }
}
