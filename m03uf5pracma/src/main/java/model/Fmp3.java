/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author joseb
 */
public class Fmp3 {
    
    
    
    //Atributos
    private String title;
    private String root;
    
    //Constructores
    public Fmp3(String title, String root) {
        this.title = title;
        this.root = root;
    }
    //Metodos

    @Override
    public String toString() {
        return "Fmp3{" + "title=" + title + ", root=" + root + '}';
    }
    
    
    
    //Gets and Sets
    public String getTitle() {
        return title;
    }

    public String getRoot() {
        return root;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRoot(String root) {
        this.root = root;
    }
    
    
    
}
