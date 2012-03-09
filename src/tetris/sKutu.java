/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import java.awt.Color;

/**
 *
 * @author WhoSayIn
 */
public class sKutu extends Kutu {
    public sKutu(int X, int Y) {
        super(X, Y);
        super.mRenk = Color.BLUE;
    }

    public void sil()
    {
        remove(this);
        validate();
    }
}
