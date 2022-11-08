/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package acisum.m03uf5pracma;

import java.util.HashMap;

/**
 *
 * @author Alexandru
 */
//Singleton
public class Controller {
    //private static Controller instance = null;
   
    /*public static synchronized Controller getInstance(){
        return (instance == null) ? new Controller() : instance;
    }*/
    HashMap<String, Object> controllers;

    public HashMap<String, Object> getControllers() {
        return controllers;
    }

    public void setControllers(HashMap<String, Object> controllers) {
        this.controllers = controllers;
    }
}
