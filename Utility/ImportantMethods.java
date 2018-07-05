package Utility;

import Domain.BlockNum;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

/**
 *
 * Clase encargada de contener varios metodos importantes para la correcta
 * funcionalidad del proyecto entre ellos la creacion de matrices para la
 * movilidad de el personaje
 */
public class ImportantMethods {

    /**
     * drawTable method to make the lines of images
     *
     * @param size Tamaño de un cuadrante
     * @param d1 Ancho de la matriz total
     * @param d2 Largo de la matriz total
     * @param g metodo de grafico para poder dibujar las lineas correspondientes
     */
    public void drawTable(int size, int d1, int d2, GraphicsContext g) {

        for (int i = 0; i <= d1; i += size) {
            g.strokeLine(0, i, d1, i);

        }
        for (int y = 0; y <= d2; y += size) {
            g.strokeLine(y, 0, y, d1);
        }

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

    /**
     * fillMatrixNum se encarga de llenar la matriz con cuadrantes y sus
     * respectivas coordenadas numericas
     *
     * @return BlockNum[][] retorna una matriz llena con cuadrantes numericos
     */
    public BlockNum[][] fillMatixNum() {
        BlockNum[][] matrix = new BlockNum[3][3];
        BlockNum block;
        int dim1 = 0, dim2 = 0, dim3 = 0, dim4 = 0;
        for (int i = 0, x = 80; i < matrix.length; i++, x += 80) {
            for (int j = 0, y = 80; j < matrix[0].length; j++, y += 80) {
                dim1 = x;
                dim2 = y;
                dim3 = x + 80;
                dim4 = y + 80;
                block = new BlockNum(dim1, dim2, dim3, dim4);
                matrix[i][j] = block;
            }
        }
        return matrix;
    }
    public BlockNum[][] fillMatixNum2() {
        BlockNum[][] matrix = new BlockNum[5][5];
        BlockNum block;
        int dim1 = 0, dim2 = 0, dim3 = 0, dim4 = 0;
        for (int i = 0, x = 80; i < matrix.length; i++, x += 80) {
            for (int j = 0, y = 80; j < matrix[0].length; j++, y += 80) {
                dim1 = x;
                dim2 = y;
                dim3 = x + 80;
                dim4 = y + 80;
                block = new BlockNum(dim1, dim2, dim3, dim4);
                matrix[i][j] = block;
            }
        }
        return matrix;
    }

    

}
