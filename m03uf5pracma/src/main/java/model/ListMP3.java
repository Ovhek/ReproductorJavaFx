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
public class ListMP3 {
    
    //Atributos
    private String title;
    private ArrayList<Fmp3> list;
    private String icon;
    
    //Constructores

    public ListMP3(String title, ArrayList<Fmp3> list, String icon) {
        this.title = title;
        this.list = list;
        this.icon = icon;
    }
    
    public ListMP3(String title, ArrayList<Fmp3> list) {
        this.title = title;
        this.list = list;
 
    }
    
     public ListMP3(String title, String icon) {
        this.title = title;
        this.icon = icon;
    }
    
     public ListMP3(String title) {
        this.title = title;
    }
     
    //Metodos
    @Override
    public String toString() {
        return "ListMP3{" + "title=" + title + ", list=" + list + ", icon=" + icon + '}';
    }

    //Get and Sets
    public String getTitle() {
        return title;
    }

    public ArrayList<Fmp3> getList() {
        return list;
    }

    public String getIcon() {
        return icon;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setList(ArrayList<Fmp3> list) {
        this.list = list;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
    
    
    
    
}
