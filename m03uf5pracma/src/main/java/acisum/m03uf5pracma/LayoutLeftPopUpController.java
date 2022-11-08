package acisum.m03uf5pracma;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Fmp3;
import model.PlayList;

/**
 * FXML Controller class
 *
 * @author bhugo
 */
public class LayoutLeftPopUpController implements Initializable {

    @FXML
    private AnchorPane newPlaylistPopUp;
    @FXML
    private TextField txt_nombre_lista;

    @FXML
    private Button btn_guardar;

    @FXML
    private Button btn_cancelar;

    private PlayList nombre;
    private ObservableList<PlayList> listas;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void initAttributes(ObservableList<PlayList> listas) {

        this.listas = listas;

    }

    /**
     Función encargada de guardar la playlist.
     */
    @FXML
    private void onActionGuardar(ActionEvent event) {

        String nombreLista = txt_nombre_lista.getText();
        ObservableList<Fmp3> tl = FXCollections.observableArrayList();
        PlayList n = new PlayList(nombreLista,tl);

        if (txt_nombre_lista.getText().isBlank()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("No has introducido un nombre");
            alert.showAndWait();
        }

        if (!txt_nombre_lista.getText().isBlank()) {
            if (!containsName(listas,n.getNombreLista())) {

                this.nombre = n;

                // Cerrar ventana
                Stage stage = (Stage) this.btn_guardar.getScene().getWindow();
                stage.close();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("El nombre introducido ya existe");
                alert.showAndWait();
            }
        }

    }

    public boolean containsName(final ObservableList<PlayList> list, final String name){
        return list.stream().map(PlayList::getNombreLista).filter(name::equals).findFirst().isPresent();
    }
    
    /*
    * Función encargada de cerrar la ventana al cancelar el PopUp
    **/
    @FXML
    private void onActionCancelar(ActionEvent event) {
        //ponemos el nombre a null para que nos añada nada
        this.nombre = null;
        // Cerrar ventana
        Stage stage = (Stage) this.btn_cancelar.getScene().getWindow();
        stage.close();
    }

    public PlayList getNombre() {
        return nombre;
    }

    public void setNombre(PlayList nombre) {
        this.nombre = nombre;
    }

}
