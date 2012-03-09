/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import java.util.ArrayList;
import javax.swing.JLabel;

/**
 *
 * @author Yusuf KARADEMiR
 */
public class GelenSekil extends JLabel {

    Kutu gKutu;
    sKutu mSkl;
    public ArrayList<sKutu> mListe = new ArrayList<sKutu>();

    public GelenSekil() {
        setBounds(300, 40, 100, 100);
        setLayout(null);
        setVisible(true);
        izgaralar();
    }

    private void izgaralar()//bu method da 5 tane en 5 tane boy dan olusan kutulaırmızı labelımızın ustune yerlestııyor
    // Şekiller maksimum 4 birim olduğu için 4'e indirdim. Hüseyin KELEŞ
    {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                gKutu = new Kutu(20 * i, 20 * j);
                add(gKutu);
            }
        }
    }

    /*
     * GelenSekil kutusunu temizler
     * Hüseyin KELEŞ
     */
    public void temizle() {
        for (int i = 0; i < mListe.size(); i++) {
            mListe.get(i).setVisible(false); //kutuyu silmek için başka yol ara. HK
        }
        mListe.clear();
    }

    /*
     * Gelecek şekli bu metodda gösteriyoruz.
     * Factory tasarım deseni kullanıldı.
     * Hüseyin KELEŞ
     */
    public void setSekil(eSekil s) {
        Sekil skl = null;
        switch (s) {
            case Cubuk:
                skl = new Skl_Cubuk();
                break;
            case Kare:
                skl = new Skl_Kare();
                break;
            case T:
                skl = new Skl_T();
                break;
            case Dog:
                skl = new Skl_dog();
                break;
            case L:
                skl = new Skl_L();
                break;
            case L_T:
                skl = new Skl_L_T();
                break;
            case Dog_T:
                skl = new Skl_dog_T();
                break;
        }
        removeAll();
        repaint();
        validate();
        izgaralar();
        mListe.clear();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (skl.govde[j][i] == 1) {
                    mSkl = new sKutu(i * 20, j * 20);
                    add(mSkl, new Integer(1), 0);
                    mListe.add(mSkl);
                }
            }
        }
        
    }
}
