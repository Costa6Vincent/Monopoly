/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Bank;

/**
 *
 * @author 373000781
 */
public class Bank {
    public static int totalMoney=15140;
    public static int currentMoney;
    Bank()
    {
        
    }

    public static int getTotalMoney() {
        return totalMoney;
    }

    public static int getCurrentMoney() {
        return currentMoney;
    }

    public static void setTotalMoney(int totalMoney) {
        Bank.totalMoney = totalMoney;
    }

    public static void setCurrentMoney(int currentMoney) {
        Bank.currentMoney = currentMoney;
    }
    
}
