package domain;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Keyboard {

    public static String getString(String message){
        Scanner sc = new Scanner(System.in);
        String input = "";
        boolean correct = false;

        do {
            System.out.println(message);
            try {
                input = sc.nextLine();
                correct = true;
            } catch (InputMismatchException ex){
                System.out.println("Invalid input. Insert caracters.");
            }
        } while(!correct);
        return input;
    }

    public static int getInt(String message){
        Scanner sc = new Scanner(System.in);
        int input = 0;
        boolean correct = false;

        do {
            System.out.println(message);
            try {
                input = sc.nextInt();
                correct = true;
            } catch (InputMismatchException ex){
                System.out.println("Invalid input. Insert integers.");
            }
            sc.nextLine();
        } while(!correct);
        return input;
    }

    public static double getDouble(String missatge){
        Scanner sc = new Scanner(System.in);
        double input = 0;
        boolean correct = false;

        do {
            System.out.println(missatge);
            try {
                input = sc.nextDouble();
                correct = true;
            } catch (InputMismatchException ex){
                System.out.println("Invalid input. Insert double.");
            }
            sc.nextLine();
        } while(!correct);
        return input;
    }
    
    
    public static long getLong(String message){
        Scanner sc = new Scanner(System.in);
        long input = 0;
        boolean correct = false;

        do {
            System.out.println(message);
            try {
                input = sc.nextLong();
                correct = true;
            } catch (InputMismatchException ex){
                System.out.println("Invalid input. Insert long.");
            }
            sc.nextLine();
        } while(!correct);
        return input;
    }

}