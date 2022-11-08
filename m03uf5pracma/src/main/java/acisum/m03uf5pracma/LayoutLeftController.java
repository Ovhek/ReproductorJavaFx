package acisum.m03uf5pracma;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
import java.io.IOException;
import java.net.URL;
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

/**
 * FXML Controller class
 *
 * @author Cole
 */
public class LayoutLeftController extends Controller implements Initializable {

    // Creacion de observable list(Actuan a modo de arrayList pero en javaFx)
    // ArrayList elements para todo tipo de gestion con los parametros introducidos y listas
    // filtroListas servira para la barra de busqueda
    //Se asignan de clase playlist para trabajar con la clase y su atributos
    ObservableList<PlayList> elements = FXCollections.observableArrayList();
    ObservableList<PlayList> filtroListas = FXCollections.observableArrayList();

    //Creacion de varaibles y declaramos los elementos de el .fxml
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

    //En el initialize se ejecutara lo que le digamos al ejecutarse la pantalla
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        /*Con el setItems lo que hacemos es que los elementos que añadamos
        se pondran directamente en nuestro listView*/
        lv_playlists.setItems(elements);

    }

    //Cuando cliquemos en el boton de la flecha y tengamos una lista seleccionada podremos empezar
    //a trabajar metiendole cancones a la lista en concreto, 
    //sabremos que estamos dentro porque se cambiara el label de la derecha
    @FXML
    private void onActionBtnSelect(ActionEvent event) {
        //Sacamos posicion 
        int posicioElementSeleccionat = lv_playlists.getSelectionModel().getSelectedIndex();

        // si la posicion es correcta eliminamos el elemento
        //que corresponde a la posicion mencionada, 
        // Puede que sea -1 en caso de no seleccionar posicion
        if (posicioElementSeleccionat > -1) {
            //sacamos el objeto de una posicion determinada de elements
            PlayList p = elements.get(posicioElementSeleccionat);

            //Trabajamos con el objeto que hemos sacado
            ((LayoutCenterController) controllers.get(LayoutCenterController.class.getSimpleName())).setPlayList(p);
        }
    }

    //Cuando cliquemos en button add se nos redigira a otra pantalla encargada de 
    //permitirnos poner un nombre de lista, guardarlo o salir
    @FXML
    private void onActionBtnAdd(ActionEvent event) {

        //Hacemos tryCatch para sacar excepcions
        try {
            //Creamos el loader y el controlador para trabajar con la pantalla que abriremos 
            //y traernos parametros
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/acisum/m03uf5pracma/layoutLeftPopUp.fxml"));

            Parent root = loader.load();

            LayoutLeftPopUpController controlador = loader.getController();
            controlador.initAttributes(elements);

            //Creams scene y stage para trabajar con otra pantalla
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();

            //añadimos el nombre que hemos traido de leftpopupcontroller
            PlayList n = controlador.getNombre();
            //si n es null no haremos nada por el contrario lo añadiremos a elements y filtrolistas
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

    //cuando cliquemos en eliminar si tenemos un elemento seleccionado lo eliminaremos
    @FXML
    private void onActionBtnEliminar(ActionEvent event) {

        // Recogemos en una variable la posicion del elemento seleccionado
        int posicioElementSeleccionat = lv_playlists.getSelectionModel().getSelectedIndex();

        if (posicioElementSeleccionat > -1) {
            PlayList p = elements.get(posicioElementSeleccionat);
            String nombre = p.getNombreLista();
            String nombreCL = ((LayoutCenterController) controllers.get(LayoutCenterController.class.getSimpleName())).getLabListTitle().getText();
            // si los nombres coinciden llamamos a borrar lista y reiniciamos la lista, la seteamos en null y la limpiamos
            if (nombre.equals(nombreCL)) {
                ((LayoutCenterController) controllers.get(LayoutCenterController.class.getSimpleName())).borrarLista();
            }
            //eliminamos elemento de las listas
            elements.remove(posicioElementSeleccionat);
            filtroListas.remove(posicioElementSeleccionat);
        }
    }

    //cuando escribimos en la barra de busqueda se nos actualizara automaticamente la lista mientras escribimos
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
    private void onActionOrdenar(ActionEvent event) {

    }

    public ListView<PlayList> getLv_playlists() {
        return lv_playlists;
    }

}
