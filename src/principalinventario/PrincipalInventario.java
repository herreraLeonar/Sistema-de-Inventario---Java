/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package principalinventario;

/**
 *
 * @author Ingenieria Digital
 */
public class PrincipalInventario {

    static gestionInventario objInv;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        objInv=new gestionInventario();
        objInv.setBounds(50, 50,640, 480);
        objInv.setLocationRelativeTo(null);
        objInv.setVisible(true);
    }
}
