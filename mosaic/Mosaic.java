/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mosaic;

import Domain.BlockNum;
import Domain.BufferMemoryGame;
import Utility.ImportantMethods;
import Sockets.ClientSocketsChat;
import java.awt.Color;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;

/**
 *
 * @author Oscar Luis
 */
public class Mosaic extends Canvas {

    private int dimentions;

    private static BlockNum[][] coordenates;
    private BufferMemoryGame sharedMemory;
    private static int matrixShips[][];
    
    public Mosaic(String dimensions) {
    
        if (dimensions.equalsIgnoreCase("3x3")) {
            super.setWidth(300);
            super.setHeight(300);
            this.dimentions = 300;
            fillMatrixShips(3);
            
        } else if (dimensions.equalsIgnoreCase("5x5")) {
            super.setWidth(500);
            super.setHeight(500);
            this.dimentions = 500;
            fillMatrixShips(5);
            
        }
        this.sharedMemory = new BufferMemoryGame();
    }
    public void getMatrixToOtherClass(){
        sharedMemory.setMatrixNaval(matrixShips);
    }
    public String getMatrixShips() {
        String result="";
        for (int i = 0; i < matrixShips.length; i++) {
           for (int j = 0; j < matrixShips[0].length; j++) {
              result+= matrixShips[i][j];
           }
           result+="\n";
       }
        return result;
    }

    public void setMatrixShips(int[][] matrixShips) {
        this.matrixShips = matrixShips;
    }
    public void setPositionShip(int i, int j, int ship){
        this.matrixShips[i][j]=ship;
    }
   public void fillMatrixShips(int size){
       matrixShips = new int[size][size];
       for (int i = 0; i < matrixShips.length; i++) {
           for (int j = 0; j < matrixShips[0].length; j++) {
               matrixShips[i][j]=0;
           }
       }
   }
   
    public void draw() {
//        GraphicsContext gc = super.getGraphicsContext2D();
//        gc.setFill(Paint.valueOf("BLUE"));
//        gc.fillRect(0, 0, this.getWidth(), this.getHeight());
        if (this.dimentions == 300) {
            drawTable(100, 300, 300, super.getGraphicsContext2D());
            //important.drawTable(100, 400, 400, super.getGraphicsContext2D());
        } else if (this.dimentions == 500) {
            drawTable(100, 500, 500, super.getGraphicsContext2D());
            //important.drawTable(100, 500, 500, super.getGraphicsContext2D());
        }
    
        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                double x = event.getX();
                double y = event.getY();

                if (x >= 0 && x <= dimentions && y >= 0 && y <= dimentions) {
//                    System.out.println("Equis" + x);
//                    System.out.println("Ye" + y);
                    BlockNum temp;
                    for (int i = 0; i < getCoordinates().length; i++) {
                        for (int j = 0; j < getCoordinates()[0].length; j++) {
                            temp = getCoordinates()[i][j];
                           //System.out.println(temp);
                            if ((x>=temp.getB()&&x<=temp.getD())&&(y>=temp.getA()&&y<=temp.getC())) {

//                                System.out.println(temp);
//                                System.out.println(i+","+j);
                                sharedMemory.setI(i);
                                sharedMemory.setJ(j);
                                sharedMemory.setShoot(true);
                            }

                        }
                    }
                }
            }

        });
    }

    public BlockNum[][] getCoordinates() {
        if (this.dimentions == 300) {
            this.coordenates = fillMatixNum(3, 3, 100);
        } else if (this.dimentions == 500) {
            this.coordenates = fillMatixNum(5, 5, 100);
        }
        return coordenates;
    }

    private void drawTable(int size, int d1, int d2, GraphicsContext g) {
        for (int i = 0; i <= d1; i += size) {
            g.strokeLine(0, i, d1, i);

        }
        for (int y = 0; y <= d2; y += size) {
            g.strokeLine(y, 0, y, d1);
        }
    }

    public BlockNum[][] fillMatixNum(int fila, int columna, int sizeSquare) {
        BlockNum[][] matrix = new BlockNum[fila][columna];
        BlockNum block;
        int dim1 = 0, dim2 = 0, dim3 = 0, dim4 = 0;
        for (int i = 0, x = 0; i < matrix.length; i++, x += sizeSquare) {
            for (int j = 0, y = 0; j < matrix[0].length; j++, y += sizeSquare) {
                dim1 = x;
                dim2 = y;
                dim3 = x + sizeSquare;
                dim4 = y + sizeSquare;
                block = new BlockNum(dim1, dim2, dim3, dim4);
                matrix[i][j] = block;
            }
        }
        return matrix;
    }

    public int getD1(int pixels) {
        int d1 = 0;

        int num1 = 0;
        for (int i = 0; i <= 680; i++) {
            if (pixels * i <= 680) {
                num1 = i;
            } else {
                i = 680;
            }
        }

        d1 = pixels * num1;
        return d1;
    }//drawTable
    
    
    public void drawImages(){
        GraphicsContext g = getGraphicsContext2D();
        int[][] matrixShips = sharedMemory.getMatrixNaval();
        BlockNum temp;
                    for (int i = 0; i < matrixShips.length; i++) {
                        for (int j = 0; j < matrixShips.length; j++) {
                            temp = getCoordinates()[i][j];
                            if(matrixShips[i][j]==1){
                                g.drawImage(sharedMemory.getSprite().get(1), temp.getB(), temp.getA());
                            }else if(matrixShips[i][j]==2){
                                g.drawImage(sharedMemory.getSprite().get(2), temp.getB(), temp.getA());
                            }else if(matrixShips[i][j]==3){
                                g.drawImage(sharedMemory.getSprite().get(0), temp.getB(), temp.getA());
                            }
                           //System.out.println(temp);
                            

                        }
                    }
        //g.drawImage(image, coordenate1, coordenate2);
    }
    public void mouseClickToDrawImage(){
         if (this.dimentions == 300) {
            drawTable(100, 300, 300, super.getGraphicsContext2D());
            //important.drawTable(100, 400, 400, super.getGraphicsContext2D());
        } else if (this.dimentions == 500) {
            drawTable(100, 500, 500, super.getGraphicsContext2D());
            //important.drawTable(100, 500, 500, super.getGraphicsContext2D());
        }
         
        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                double x = event.getX();
                double y = event.getY();

                if (x >= 0 && x <= dimentions && y >= 0 && y <= dimentions) {
//                    System.out.println("Equis" + x);
//                    System.out.println("Ye" + y);
                    BlockNum temp;
                    for (int i = 0; i < getCoordinates().length; i++) {
                        for (int j = 0; j < getCoordinates()[0].length; j++) {
                            temp = getCoordinates()[i][j];
                           //System.out.println(temp);
                            if ((x>=temp.getB()&&x<=temp.getD())&&(y>=temp.getA()&&y<=temp.getC())) {

                                
                                if(sharedMemory.isHaveImageToPaint()){
                                    
                                    GraphicsContext g = getGraphicsContext2D();
                                     g.drawImage(sharedMemory.getImageToPaint(), temp.getB(), temp.getA());
                                     setPositionShip(i, j, sharedMemory.getShip());
                                     
                                }else{
                                    
                                }
                                sharedMemory.setHaveImageToPaint(false);
                            }

                        }
                    }
                }
            }

        });
    }
}
