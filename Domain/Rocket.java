/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;

/**
 *
 * @author Oscar Luis
 */
public class Rocket extends Character {
    private int instruction;
    private int x;
    private int y;
    public Rocket(int x, int y, int imgNum) {
        super(x, y, imgNum);
        this.instruction=0;
        this.x=0;
        this.y=0;
    }

    public int getInstruction() {
        return instruction;
    }

    public void setInstruction(int instruction) {
        this.instruction = instruction;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.setX(x);
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.setY(y);
        this.y = y;
    }
    
    public Rocket() {
    }

    public void setSprite() throws FileNotFoundException {
        ArrayList<Image> sprite = super.getSprite();
        for (int i = 0; i < 3; i++) {
            sprite.add(new Image(new FileInputStream("src/Assets/Rocket" + i + ".png")));
        }
    }

    public void verticalRightLeft1() {
        for (int j = 270, i = super.getX(); j < 365; j++, i--) {
            try {
                ArrayList<Image> sprite = super.getSprite();
                super.setImage(sprite.get(1));
                Thread.sleep(10);
                this.setY(j);
                this.setX(i);
            } catch (InterruptedException ex) {
                Logger.getLogger(Rocket.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void verticalLeftRight1() {
        for (int j = 365, i = super.getX(); j > 270; j--, i++) {
            try {
                ArrayList<Image> sprite1 = super.getSprite();
                super.setImage(sprite1.get(0));
                Thread.sleep(10);
                this.setY(j);
                this.setX(i);
            } catch (InterruptedException ex) {
                Logger.getLogger(Rocket.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void verticalLeftRight2() {
        for (int j = 270, i = super.getX(); j < 365; j++, i++) {
            try {
                ArrayList<Image> sprite = super.getSprite();
                super.setImage(sprite.get(1));
                Thread.sleep(10);
                this.setY(j);
                this.setX(i);
            } catch (InterruptedException ex) {
                Logger.getLogger(Rocket.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void verticalRightLeft2() {
        for (int j = 365, i = super.getX(); j > 270; j--, i--) {
            try {
                ArrayList<Image> sprite1 = super.getSprite();
                super.setImage(sprite1.get(0));
                Thread.sleep(10);
                this.setY(j);
                this.setX(i);
            } catch (InterruptedException ex) {
                Logger.getLogger(Rocket.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void verticalDown() {
        for (int j = 270; j < 365; j++) {
            try {
                ArrayList<Image> sprite = super.getSprite();
                super.setImage(sprite.get(1));
                Thread.sleep(10);
                this.setY(j);
            } catch (InterruptedException ex) {
                Logger.getLogger(Rocket.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public void verticalUp() {
        for (int j = 365; j > 270; j--) {
            try {
                ArrayList<Image> sprite1 = super.getSprite();
                super.setImage(sprite1.get(0));
                Thread.sleep(10);
                this.setY(j);
            } catch (InterruptedException ex) {
                Logger.getLogger(Rocket.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    @Override
    public void run() {
        if(this.instruction==1){
            verticalDown();
        }else if (this.instruction==2){
            verticalUp();
        }
            
    }
}
