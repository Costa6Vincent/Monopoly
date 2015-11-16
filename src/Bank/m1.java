/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Bank;

import java.util.ArrayList;

/**
 *
 * @author 373000781
 */
public class m1 
{
    public static int num1Bills=40;
    public static int value=1;
    public static ArrayList<m1> bill1;
    m1()
    {
        for(int index=0;index<num1Bills;index++)
        {
            bill1.add(index, this);
        }
    }
}
