/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

/**
 *
 * @author WhoSayIn
 */
public class Skl_Kare extends Sekil {

    public Skl_Kare() {
        aci = 0;
        govdeler = new int[][][]{
                    {
                        {1, 1, 0, 0},
                        {1, 1, 0, 0},
                        {0, 0, 0, 0},
                        {0, 0, 0, 0}},
                    {
                        {1, 1, 0, 0},
                        {1, 1, 0, 0},
                        {0, 0, 0, 0},
                        {0, 0, 0, 0}},
                    {
                        {1, 1, 0, 0},
                        {1, 1, 0, 0},
                        {0, 0, 0, 0},
                        {0, 0, 0, 0}},
                    {
                        {1, 1, 0, 0},
                        {1, 1, 0, 0},
                        {0, 0, 0, 0},
                        {0, 0, 0, 0}}
                };
        govde = govdeler[aci];
    }
}
