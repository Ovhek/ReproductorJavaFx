package acisum.m03uf5pracma;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

/**
 * FXML Controller class
 *
 * @author Cole
 */
public class LayoutLeftController extends Controller implements Initializable {

    //ObservableList<String> elements = FXCollections.observableArrayList();

    @FXML
    public Label txt_iv_playlist;

    @FXML
    private ListView<String> lv_playlists;

    @FXML
    private Button btn_add_playlist;

    @FXML
    private Button btn_delete_playlist;

    @FXML
    private Button btn_filter_playlist;

    private Label getTxtIvPlaylist (){
        return txt_iv_playlist;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        /*Con el setItems lo que hacemos es que los elementos que añadamos
        se pondran directamente en nuestro listView*/
        //lv_playlists.setItems(elements);

        /*Declaramos un listener que sera el que detectara cuando seleccionemos en alguna playlist*/
        /*lv_playlists.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {

            //activar el botó "eliminar" per si l'usuari el vol eliminar
            btn_delete_playlist.setDisable(false);

        });*/
    }

    private void onActionBtnAdd(ActionEvent event) {

        //desactivem el botó eliminar
        btn_delete_playlist.setDisable(true);
        
    }

    private void onActionBtnEliminar(ActionEvent event) {

    }

}
