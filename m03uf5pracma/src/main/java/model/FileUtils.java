/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author joseb
 */
import acisum.m03uf5pracma.Utils.IdentificaOS;
import java.nio.file.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author manel
 */
public class FileUtils {

    /**
     * *
     * Retorna una icona
     *
     * @param instance
     * @param nom
     * @return
     */
    public static String getIcona(Object instance, String nom) {
        return instance.getClass().getClassLoader().getResource("icons/" + nom).toString();
    }

    /**
     * *
     * Permet seleccionar un fitxer MP3 d'una unitat de disc
     *
     * @return
     */
    public static Path getMP3Fromfile() {
        Path ret = null;

        Stage stage1 = new Stage();

        FileChooser filechooser1 = new FileChooser();

        filechooser1.setTitle("Seleccionar fixter MP3");

        Path fitxerMP3 = filechooser1.showOpenDialog(stage1).toPath();
        if (fitxerMP3 != null) {
            ret = fitxerMP3;
        }

        return ret;
    }

}
