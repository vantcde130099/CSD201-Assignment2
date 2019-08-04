/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manager;

import Entity.TaxPayer;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author thinh
 */
public class Validate {
    
    private static final Scanner sc = new Scanner(System.in);
    private static final Scanner sTrsc = new Scanner(System.in);
    
    public static int checkInputLimit(int min, int max){
        while(true){
            try{
                int result = Integer.parseInt(sc.nextLine().trim());
                if (result < min && result > max){
                    throw new NumberFormatException();
                }
                return result;
            }catch(NumberFormatException ex){
                System.out.println("Please enter number in range (" + min + ", " + max+")");
                System.out.print("Enter again: ");
            }
        }
    }
    
    public static String checkInputString(){
        while(true){
            String result = sTrsc.nextLine().trim();
            if (!result.isEmpty()){
                return result;
            }
            System.out.println("String must not empty");
        }
    }
    
    public static double checkInputIncome(){
        while(true){
            try{
                double result = Double.parseDouble(sc.nextLine().trim());
                if (result <= 0){
                    throw new NumberFormatException();
                }
                return result;
            }catch(NumberFormatException ex){
                System.out.println("Please input positive number.");
                System.out.print("Enter again: ");
            }
        }
    }
    
    public static boolean checkExistByCode(ArrayList<TaxPayer> listTax, String newcode){
        for (TaxPayer tax : listTax){
            if (tax.getCode().compareTo(newcode) == 0){
                return true;
            }
        }
        return false;
    }
    
    public static double checkInputDouble(){
        while (true) {            
            try {
                double result = Double.parseDouble(sc.nextLine().trim());
                return result;
            } catch (NumberFormatException e) {
                System.err.println("Please enter number!");
                System.out.print("Enter again: ");
            }
        }
    }
}
