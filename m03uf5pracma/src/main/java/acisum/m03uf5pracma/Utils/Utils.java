/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package acisum.m03uf5pracma.Utils;

import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author Cole
 */
public abstract class Utils {
    
    
    public static MediaPlayer getMediaPlayer(String mp3Path) throws MediaException
    {
        return new MediaPlayer(new Media(mp3Path));
    }
    
    public static String getMp3Path(Object instance, String songName)
    {

        var test = instance.getClass().getClassLoader().getResource("data/");
        
        return instance.getClass().getClassLoader().getResource("data/"+songName+".mp3").toString();
    }
}
