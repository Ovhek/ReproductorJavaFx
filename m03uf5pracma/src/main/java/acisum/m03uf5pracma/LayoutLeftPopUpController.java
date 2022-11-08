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
import javafx.stage.Stage;
import model.Fmp3;
import model.PlayList;

/**
 * FXML Controller class
 *
 * @author bhugo
 */
public class LayoutLeftPopUpController implements Initializable {

    //Creacion de varaibles y declaramos los elementos de el .fxml
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

    //cuando cliquemos en guardar recogeremos el nombre lo añadiemos a una varible de clase 
    //playlist que pasaremos a la pantalla leftcontroller
    //saltaran errores en case de poner nombres errononeos
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

               /* Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle("Informacion");
                alert.setContentText("Se ha añadido correctamente");
                alert.showAndWait();*/

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
    
    //cuando pulsamos en cancelar setearemos nombre a null y cerraremos la ventana
    @FXML
    private void onActionCancelar(ActionEvent event) {
        //ponemos el nombre a null para que nos añada nada
        this.nombre = null;
        // Cerrar ventana
        Stage stage = (Stage) this.btn_cancelar.getScene().getWindow();
        stage.close();
    }

    //getter y setter de nombre(finalmente en nuestro caso setter se podria eliminar ya que no lo usariamos)
    public PlayList getNombre() {
        return nombre;
    }

    public void setNombre(PlayList nombre) {
        this.nombre = nombre;
    }

}
