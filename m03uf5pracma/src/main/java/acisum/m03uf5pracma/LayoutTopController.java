package acisum.m03uf5pracma;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import acisum.m03uf5pracma.Utils.Utils;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Cole
 */
public class LayoutTopController extends Controller implements Initializable {

    @FXML
    private AnchorPane layout_top;

    @FXML
    private MenuBar menu_top;

    @FXML
    private Menu menu_top_export;

    @FXML
    private Menu menu_top_import;

    @FXML
    private MenuItem menu_top_language_catalan;

    @FXML
    private MenuItem menu_top_language_english;

    @FXML
    private MenuItem menu_top_language_spanish;

    @FXML
    private Menu menu_top_languages;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }
   

    public ResourceBundle appBundle = Utils.getappBundle(Languages.ENGLISH);
    /**
     * Función que aplica un lenguaje en concreto dependiendo del especificado.
     * @param lang Enum del Lenguaje.
    */
    private void applyLanguage(Languages lang){
        appBundle = Utils.getappBundle(lang);
        changeControllersText(appBundle);
    }
    
        
    /**
     * Función que cambia el texto de los controles.
     * @param appBundle Bundle de recursos con el idioma.
     */
    private void changeControllersText(ResourceBundle appBundle){
        //Center    --> labListTitle
        //Left      --> txt_iv_playlist
        
        LayoutLeftController layout_left = (LayoutLeftController)controllers.get("LayoutLeftController");
        
        layout_left.txt_iv_playlist.setText(appBundle.getString("txt_list_playlist"));
        
        menu_top_import.setText(appBundle.getString("txt_import"));
        menu_top_export.setText(appBundle.getString("txt_export"));
        menu_top_languages.setText(appBundle.getString("txt_languages"));
        menu_top_language_spanish.setText(appBundle.getString("txt_language_es"));
        menu_top_language_english.setText(appBundle.getString("txt_language_en"));
        menu_top_language_catalan.setText(appBundle.getString("txt_languages_ca"));
        var b = 1;
        
    }    
    
    @FXML
    void languageCatalan(ActionEvent event) {
        applyLanguage(Languages.CATALAN);
    }

    @FXML
    void languageEnglish(ActionEvent event) {
        applyLanguage(Languages.ENGLISH);
    }

    @FXML
    void languageSpanish(ActionEvent event) {
        applyLanguage(Languages.SPANISH);
    }
    
}
