/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package acisum.m03uf5pracma.Utils;

import acisum.m03uf5pracma.Languages;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author Cole
 */
public abstract class Utils {
    
    /***
    * Si la URL és basura de tipus Windows, elimina el nom de la unitat
    * @return 
    */
    public static String normalizeURLFormat(String url)
    {
        String ret = "";
        
        if (IdentificaOS.getOS() == IdentificaOS.TipusOS.WIN)
            ret = url.replace("[A-Z]{1}:", "");
        
        return ret;
    }
    
    public static MediaPlayer getMediaPlayer(String mp3Path) throws MediaException
    {
        return new MediaPlayer(new Media(mp3Path));
    }
    
    public static String getMp3Path(Object instance, String songName)
    {
        return instance.getClass().getClassLoader().getResource("sounds/"+songName+".mp3").toString();
    }
    
    public static ResourceBundle getappBundle (Languages lang){
        Locale locale = null;
        switch (lang) {
            case ENGLISH:
                locale = new Locale("en");
                break;
            case SPANISH:
                locale = new Locale("es");
                break;
            case CATALAN:
                locale = new Locale("ca");
                break;
            default:
                throw new AssertionError();
        }
        return ResourceBundle.getBundle("bundles.LangBundle", locale); 
        
    }
    
}
