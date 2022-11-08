/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Comparator;

/**
 *
 * @author Cole
 */
public class PlayListComparator implements Comparator<PlayList> {

    @Override
    public int compare(PlayList o1, PlayList o2) {
        return o1.getNombreLista().compareTo(o2.getNombreLista());
    }
    
}
