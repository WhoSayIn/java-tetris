/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

/**
 * Şekillerin base class'ı
 * @author Hüseyin KELEŞ
 */
public class Sekil {

    public int[][] govde; // = new int[4][4];
    public int[][][] govdeler; // Şeklin döndürülmüş halleri
    public int aci; //Şeklin açısı, 0-3

    public void cevir() {
        govde = govdeler[(aci++) % 4];
    }

}
