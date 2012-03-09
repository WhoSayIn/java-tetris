package tetris;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import javax.swing.*;


/**
 *
 * @author Hüseyin KELEŞ
 */
public class Kutu extends JLabel {

    public Kutu(int X, int Y) {
        setBounds(X, Y, 20, 20);
        setLayout(null);
    }
    public Color mRenk = Color.RED;

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        Rectangle2D rect = new Rectangle2D.Double(0, 0, 19, 19);

        g2.setColor(mRenk);//kutu ıcın
        g2.fill(rect);

        g2.setColor(Color.black);// kenarları ıcın
        g2.draw(rect);
    }
}
