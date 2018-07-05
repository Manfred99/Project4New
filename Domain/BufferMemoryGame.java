/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import java.util.ArrayList;
import javafx.scene.image.Image;

/**
 *
 * @author manfr
 */
public class BufferMemoryGame {
    private static int i;
    private static int j;
    private static boolean shoot;
    public static String clientName;
    private  static boolean haveImageToPaint;
    private  static Image imageToPaint;
    private static int matrixNaval[][];
    private static int ship;
    private static ArrayList<Image> sprite;
    public BufferMemoryGame() {
        i=0;
        j=0;
        shoot = false;
        clientName = "NOName";
        haveImageToPaint = false;
    }

    public  ArrayList<Image> getSprite() {
        return sprite;
    }

    public  void setSprite(ArrayList<Image> sprite) {
        BufferMemoryGame.sprite = sprite;
    }
    
    public  int getShip() {
        return ship;
    }

    public  void setShip(int ship) {
        BufferMemoryGame.ship = ship;
    }
    
    public  int[][] getMatrixNaval() {
        return matrixNaval;
    }

    public  void setMatrixNaval(int[][] matrixNaval) {
        BufferMemoryGame.matrixNaval = matrixNaval;
    }
    
    public  Image getImageToPaint() {
        return imageToPaint;
    }

    public  void setImageToPaint(Image imageToPaint) {
        BufferMemoryGame.imageToPaint = imageToPaint;
    }

    public  boolean isHaveImageToPaint() {
        return haveImageToPaint;
    }

    public  void setHaveImageToPaint(boolean haveImageToPaint) {
        BufferMemoryGame.haveImageToPaint = haveImageToPaint;
    }

//    public String getClientName() {
//        return clientName;
//    }
//
//    public void setClientName(String clientName) {
//        BufferMemoryGame.clientName = clientName;
//    }
    
    public  int getI() {
        return i;
    }

    public  void setI(int i) {
        BufferMemoryGame.i = i;
    }

    public  int getJ() {
        return j;
    }

    public  void setJ(int j) {
        BufferMemoryGame.j = j;
    }

    public  boolean isShoot() {
        return shoot;
    }

    public  void setShoot(boolean shoot) {
        BufferMemoryGame.shoot = shoot;
    }
    
    
}
