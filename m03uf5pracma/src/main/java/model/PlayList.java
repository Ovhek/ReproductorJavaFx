/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author joseb
 */
public class PlayList {
    private ArrayList<ListMP3> playList;
    
    //Constructores

    public PlayList(ArrayList<ListMP3> playList) {
        this.playList = playList;
    }
    
    //Metodos
    @Override    
    public String toString() {
        return "PlayList{" + "playList=" + playList + '}';
    }

    //Gets and Setters
    public ArrayList<ListMP3> getPlayList() {
        return playList;
    }
    
}
