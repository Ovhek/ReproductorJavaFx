/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.Objects;
import javafx.collections.ObservableList;

/**
 *
 * @author joseb
 */
public class PlayList {
    private ObservableList<Fmp3>  playList;
    private String nombreLista;
    
    //Constructores

    public PlayList(ObservableList<Fmp3> playList) {
        this.playList = playList;
    }
    
    public PlayList(String nombreLista, ObservableList<Fmp3> playList){
        this.nombreLista = nombreLista;
        this.playList = playList;
    }

    public PlayList(String nombreLista) {
        this.nombreLista = nombreLista;
    }
    
    //Metodos
    @Override    
    public String toString() {
        return nombreLista;
    }

    public String getNombreLista() {
        return nombreLista;
    }

    public void setNombreLista(String nombreLista) {
        this.nombreLista = nombreLista;
    }

    //Gets and Setters
    public ObservableList<Fmp3> getPlayList() {
        return playList;
    }

    public void setPlayList(ObservableList<Fmp3> playList) {
        this.playList = playList;
    }
    
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PlayList other = (PlayList) obj;
        return Objects.equals(this.playList, other.playList);
    }
    
    
}
