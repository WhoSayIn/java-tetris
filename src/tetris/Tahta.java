package tetris;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import javax.swing.JLabel;

/**
 *
 * @author Hüseyin KELEŞ
 */
public class Tahta extends JLabel {

    private int en = 12, boy = 20;
    sKutu mSkl;
    Kutu mKutu;
    public ArrayList<sKutu> mAktif = new ArrayList<sKutu>();
    public ArrayList<sKutu> mKutular = new ArrayList<sKutu>();
    public Sekil aktifSekil = new Sekil();
    public Sekil aktifSekil2 = new Sekil();

    public Tahta() {
        setBounds(0, 0, 240, 400);//labelın boyutu
        setLayout(null);
        setVisible(true);
        izgaralar();
        this.requestFocus();
    }

    private void izgaralar()//bu method da 12 tane en 20 tane boy dan olusan kutularımızı labelımızın ustune yerlestııyor
    {
        for (int i = 0; i < en; i++) {
            for (int j = 0; j < boy; j++) {
                mKutu = new Kutu(20 * i, 20 * j);
                add(mKutu);
            }
        }
    }

    /*
     * Tahtaya yeni bir şekil ekler
     * HK
     */
    public void ekle(eSekil s) {
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

        aktifSekil = skl;
        mAktif.clear();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (skl.govde[j][i] == 1) {
                    mSkl = new sKutu(i * 20 + 100, j * 20 - 20);
                    add(mSkl, new Integer(1), 0);
                    mAktif.add(mSkl);
                }
            }
        }
    }

    /*
     * Tahtanın verilen konumuna yeni bir şekil ekler
     * HK
     */
    public boolean ekle(Sekil s, int x, int y) {
        int x2, y2;
        aktifiSil();
        mAktif.clear();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (s.govde[j][i] == 1) {
                    for (sKutu kutu : mKutular) {
                        x2 = kutu.getX();
                        y2 = kutu.getY();
                        if (((i * 20 + x == x2) && (j * 20 + y == y2)) || (x >= en * 20) || (x<0) || (y>= boy * 20)) {
                            aktifiSil();
                            mAktif.clear();
                            return false;
                        }
                    }
                    mSkl = new sKutu(i * 20 + x, j * 20 + y);
                    add(mSkl, new Integer(1), 0);
                    mAktif.add(mSkl);
                }
            }
        }
        return true;
    }

    /*
     * Rastgele şekil üretir.
     * HK
     */
    public eSekil rastgeleUret() {
        eSekil e;
        Random r = new Random();
        int i = r.nextInt(7);
        switch (i) {
            case 0:
                e = eSekil.Cubuk;
                break;
            case 1:
                e = eSekil.Dog;
                break;
            case 2:
                e = eSekil.Dog_T;
                break;
            case 3:
                e = eSekil.Kare;
                break;
            case 4:
                e = eSekil.L;
                break;
            case 5:
                e = eSekil.L_T;
                break;
            case 6:
                e = eSekil.T;
                break;
            default:
                e = eSekil.Kare;
                break;
        }
        return e;
    }

    /*
     * Aktif kayan şekli sil
     * HK
     */
    public void aktifiSil() {
        for (sKutu kutu : mAktif) {
            remove(kutu);
        }

        mAktif.clear();

        repaint();
        validate();
    }

    /*
     * Şekli aşağı kaydır
     * HK
     */
    public void kay() {
        int x, y;
        for (sKutu kutu : mAktif) {
            x = kutu.getX();
            y = kutu.getY();

            kutu.setLocation(x, y + 20);
        }
    }

    /*
     * Şekli sağa kaydır
     * HK
     */
    public void sagaKay() {
        int x, y;
        for (sKutu kutu : mAktif) {
            x = kutu.getX();
            y = kutu.getY();

            kutu.setLocation(x + 20, y);
        }
    }

    /*
     * Şekli sola kaydır
     * HK
     */
    public void solaKay() {
        int x, y;
        for (sKutu kutu : mAktif) {
            x = kutu.getX();
            y = kutu.getY();

            kutu.setLocation(x - 20, y);
        }
    }

    /*
     * Sol taraf kaymaya müsait mi
     * HK
     */
    public boolean solKontrol() {
        int x, y, x2, y2;
        for (sKutu kutu : mAktif) {
            x = kutu.getX();
            y = kutu.getY();

            if (x <= 0) {
                return false;
            }

            for (sKutu kutu2 : mKutular) {
                x2 = kutu2.getX();
                y2 = kutu2.getY();

                if ((x == x2 + 20) && (y == y2)) {
                    return false;
                }
            }
        }
        return true;
    }

    /*
     * Sağ taraf kaymaya müsait mi
     * HK
     */
    public boolean sagKontrol() {
        int x, y, x2, y2;
        for (sKutu kutu : mAktif) {
            x = kutu.getX();
            y = kutu.getY();

            if (x >= (en - 1) * 20) {
                return false;
            }

            for (sKutu kutu2 : mKutular) {
                x2 = kutu2.getX();
                y2 = kutu2.getY();

                if ((x + 20 == x2) && (y == y2)) {
                    return false;
                }
            }
        }

        return true;
    }

    /*
     * Alt taraf kaymaya müsait mi
     * HK
     */
    public boolean altKontrol() {
        int x, y, x1, y1;
        for (sKutu kutu : mAktif) {
            x = kutu.getX();
            y = kutu.getY();

            if (y >= (boy - 1) * 20) {
                mKutular.addAll(mAktif);
                mAktif.clear();
                return false;
            }

            for (sKutu k2 : mKutular) {
                x1 = k2.getX();
                y1 = k2.getY();

                if ((y >= y1 - 20) && (x == x1)) {
                    mKutular.addAll(mAktif);
                    mAktif.clear();
                    return false;
                }
            }
        }
        return true;
    }

    /*
     * Şekli Çevir
     * HK
     */
    public void cevir() {
        int x, y;

        // sol üst noktasının konumu
        x = mAktif.get(0).getX();
        y = mAktif.get(0).getY();

        aktifSekil2 = aktifSekil;
        aktifSekil.cevir();
        if (!ekle(aktifSekil, x, y)) {
            ekle(aktifSekil2, x, y);
        }

        //System.out.println(x + " - " + y);
    }

    /*
     * Oyunun bitip bitmediğini kontrol eden metod
     * HK
     */
    public boolean oyunBittimi() {
        for (sKutu kutu : mKutular) {
            if (kutu.getY() == 0) {
                return true;
            }
        }
        return false;
    }

    /*
     * eSekil den Sekil'e dönüştürür.
     * HK
     */
    public Sekil eSekil2Sekil(eSekil s) {
        Sekil skl;
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
            default:
                skl = new Skl_Kare();
                break;
        }
        return skl;
    }

    /*
     * Tüm satırları kontrol eder, tam dolu olan satırları siler
     * HK
     */
    public int supur() {
        int b = 0;
        int[] satirlar = new int[20];
        for (sKutu kutu : mKutular) {
            if (kutu.getY() > 0) {
                satirlar[kutu.getY() / 20]++;
            }
        }

        for (int i = 0; i < 20; i++) {
            if (satirlar[i] == 12) {
                sil(i);
                b++;
            }
        }

        return b;
    }

    /*
     * Verilen satırı siler, üstündekileri bir aşağı kaydırır.
     * HK
     */
    public void sil(int i) {
        int x, y;

        for (sKutu kutu : mKutular) {
            if (kutu.getY() == i * 20) {
                remove(kutu);
            }
        }

        for (Iterator it = mKutular.iterator(); it.hasNext();) {
            sKutu s = (sKutu) it.next();
            if (s.getY() == i * 20) {
                it.remove();
            }
        }

        for (sKutu k : mKutular) {
            if (k.getY() < i * 20) {
                x = k.getX();
                y = k.getY();

                k.setLocation(x, y + 20);
            }
        }

        repaint();
        validate();
    }
}
