package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        boolean t;
        String text;
        ArrayList<Car> arr = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            Car.menu(reader, arr);
        } catch (IOException e) {
            System.out.println("QoQ: Проблема з BufferedReader");
        }
    }
    public static int input_int(BufferedReader reader) throws IOException {
        String t = reader.readLine();
        int m;
        try {
            m = Integer.parseInt(t);
        }catch (NumberFormatException e) {
            System.out.println("Невдача, введіть число ще раз");
            m = input_int(reader);
        }
        return m;
    }
    public static int input_year(BufferedReader reader) throws IOException {
        String t = reader.readLine();
        int m;
        try {
            m = Integer.parseInt(t);
            if(m < 1885 || m > 2021){
                System.out.println("Невдача, перший автомобіль був побудований в 1885, а останній в 2021");
                m = input_int(reader);
            }
        }catch (NumberFormatException e) {
            System.out.println("Невдача, введіть число ще раз");
            m = input_int(reader);
        }
        return m;
    }
    public static double input_double(BufferedReader reader) throws IOException {
        String t = reader.readLine();
        double m;
        try {
            m = Double.parseDouble(t);
        }catch (NumberFormatException e) {
            System.out.println("Невдача, введіть число ще раз");
            m = input_double(reader);
        }
        return m;
    }

}
