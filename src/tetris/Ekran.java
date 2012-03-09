package tetris;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 * tahtayı form a eklıyor
 * @author Hüseyin KELEŞ
 */
public class Ekran extends JFrame {

    public boolean oynaniyor = false; // Oyunun oynanıp oynanmadığını kaydettiğimiz değişken. HK
    public Timer mTimer = null; // Ana timer. HK
    public int mAralik = 500, mAralikBas = 500, butonDurumu = 0, puan = 0, level = 1, st; // Timer'ın intervali. HK
    public GelenSekil mGelenSekil;
    public Tahta mTahta;
    public Sekil mSekil = new Skl_T();
    public eSekil es, es2;
    public JButton mButton;
    public JLabel lblPuan, lblLevel;

    public Ekran() {
        super("Java Tetris v0.1");
        setBounds(0, 0, 430, 440);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        mTahta = new Tahta();
        add(mTahta);
        mGelenSekil = new GelenSekil();
        add(mGelenSekil);

        setVisible(true);

        /*
         * Puan labeli
         */

        lblPuan = new JLabel("Puan : 0");
        lblPuan.setBounds(300, 200, 100, 30);
        add(lblPuan);

        /*
         * Level labeli
         */

        lblLevel = new JLabel("Level : 1");
        lblLevel.setBounds(300, 170, 100, 30);
        add(lblLevel);

        /*
         * Başlat butonu
         */
        mButton = new JButton("Başlat");
        mButton.setBounds(300, 250, 100, 30);
        add(mButton);
        mButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                switch (butonDurumu) {
                    // Başlat
                    case 0:
                        es = mTahta.rastgeleUret(); // ilk şekil
                        es2 = mTahta.rastgeleUret(); // sonraki şekil
                        mGelenSekil.setSekil(es2);
                        mTahta.ekle(es);

                        mTimer.start();
                        mButton.setText("Duraklat");
                        butonDurumu = 1;
                        break;
                    // Duraklat
                    case 1:
                        mTimer.stop();
                        mButton.setText("Devam Et");
                        butonDurumu = 2;
                        break;
                    // Devam et
                    case 2:
                        mTimer.start();
                        mButton.setText("Duraklat");
                        butonDurumu = 1;
                        break;
                }


                //mButton.setEnabled(false);
            }
        });

        mTimer = new Timer(mAralik, new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                if (mTahta.altKontrol()) {
                    mTahta.kay();
                } else {
                    es = es2;

                    st = mTahta.supur();
                    if (st > 0) {
                        puan += st * 100 + ((st - 1) * 10);
                        lblPuan.setText("Puan : " + puan);

                        level = puan / 1000;

                        lblLevel.setText("Level : " + level);
                        mAralik = mAralikBas - level * 70;
                        mTimer.setDelay(mAralik);
                    }
                    mTahta.ekle(es2);

                    es2 = mTahta.rastgeleUret();
                    mGelenSekil.setSekil(es2);
                    if (mTahta.oyunBittimi()) {
                        mTahta.aktifiSil();
                        JOptionPane.showMessageDialog(rootPane, "Oyun Bitti!");
                        mTimer.stop();
                    }
                }
            }
        });

        mButton.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    if (mTahta.solKontrol()) {
                        mTahta.solaKay();
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    if (mTahta.sagKontrol()) {
                        mTahta.sagaKay();
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    mTahta.cevir();
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    mTimer.setDelay(50);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    mTimer.setDelay(mAralik);
                }
            }
        });
    }
}
