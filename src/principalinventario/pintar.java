/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package principalinventario;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author LeonarPC
 */
public class pintar extends Canvas {
int MAXIMOPRODUCTOS=1000;
    public pintar() {
        setBounds(0, 0, 600, 400);

    }

    public void paint(Graphics g) {
        int ancho = getWidth(), alto = getHeight();
        int cantPro = cantidadProductos();
        int tamAncBarras, tamAltBarras;
        float mayCant = 0;
        int[][] indCantProd = new int[2][cantPro];
        cargarVectProd(indCantProd);
        //el canpro*3 = la distancias entre barras
        tamAncBarras = (ancho - (cantPro * 3)) / cantPro;
///////////////////////////////////////////////
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, ancho, alto);
        for (int i = 0; i < cantPro; i++) {
            g.setColor(Color.GREEN);
            //g.setStroke(s);
            tamAltBarras = (int) (((float) indCantProd[1][i] / (float) MAXIMOPRODUCTOS * (float) alto) - 20);

            if (indCantProd[1][i] < 900) {//solo las barras rojas
                g.setColor(Color.red);
            }
            g.fillRect(i * tamAncBarras + i * 3, alto - tamAltBarras, tamAncBarras, tamAltBarras);
            g.setColor(Color.BLACK);

            g.drawRect(i * tamAncBarras + i * 3, alto - tamAltBarras, tamAncBarras, tamAltBarras);
            g.drawString(toString().valueOf(indCantProd[1][i]), i * tamAncBarras + i * 3, alto - tamAltBarras);
            /////////////////////////////////////////////
        }
    }
    

    public int cantidadProductos() {
        int total = 0;
        try {
            File f = new File("productos\\indices.txt");
            if (!f.exists()) { //preguntamos si no existe el archivo
                f.createNewFile();//si no existe creamos uno vacio
            }
            // lee el archivo caracter a caracter
            FileReader capturarBuffer = new FileReader(f);
            //el buffered reader se usa para leer linea por linea un archivo
            BufferedReader bufLinea = new BufferedReader(capturarBuffer);
            String cadenaLeida = "";//solo leemos la cantidad de registros
            while ((cadenaLeida = bufLinea.readLine()) != null) {
                total++;
            }
        } catch (IOException ex) {
        }
        return total;
    }

    public void cargarVectProd(int[][] indCantProd) {

        File f = new File("productos");
        BufferedReader buf;
        f.mkdirs();
        //se pregunta que ese directorio exista
        try {
            if (f.exists()) { // Directorio existe
                //creamos un vector de objetos de tipo archivos, en donde cada indice del vector es un archivo
                File[] ficheros = f.listFiles();
                int counter = 0;
                //recorremos con un ciclo for todos los archivos
                for (int x = 0; x < ficheros.length; x++) {
                    //agregar si no es el nombre del archivo llamado indice
                    if (!ficheros[x].getName().contains("indices")) {
                        FileReader leer = null;
                        leer = new FileReader(ficheros[x]);
                        buf = new BufferedReader(leer);
                        //como la cantidad esta en la 5 pósicion hago una lectura de 5 veces
                        buf.readLine();//esta linea es el nombre
                        buf.readLine();//esta linea es tipo
                        indCantProd[0][counter] = Integer.parseInt(buf.readLine());  //esta linea es el id   
                        buf.readLine();//esta linea es el precio
                        indCantProd[1][counter] = Integer.parseInt(buf.readLine());  //esta linea a es el restante en el almacen   
                        buf.close();
                        leer.close();
                        counter++;
                    }
                }
            }
        } catch (IOException ex) {
        }

    }
}
