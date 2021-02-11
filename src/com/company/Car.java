package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

//Відомості про машини включають наступні дані: марка, потужність, країна виготівник, дата випуску, первинна ціна.
// Розділити машини на дві групи - випущені до 2000 року і після. Розподілити їх по країнах.
public class Car implements java.io.Serializable {
    private String brand;
    private int power;
    private String country;
    private int date;
    private double price;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public void input(BufferedReader reader) throws IOException {
        System.out.println("Введіть марку авомобіля");
        this.brand = reader.readLine();
        System.out.println("Введіть потужність");
        this.power = Main.input_int(reader);
        System.out.println("Введіть країну виробника");
        this.country = reader.readLine();
        System.out.println("Введіть дату випуску");
        this.date = Main.input_year(reader);
        System.out.println("Введіть ціну");
        this.price = Main.input_double(reader);
    }
    public static void print(ArrayList<Car> arr) throws IOException {
        if (arr.size()>0)
        for (int i = 0; i < arr.size(); i++) {
            System.out.println("№ " + (i+1));
            System.out.println("Марка авомобіля: " + arr.get(i).brand);
            System.out.println("Потужність: " + arr.get(i).power);
            System.out.println("Країна виробник: " + arr.get(i).country);
            System.out.println("Дата випуску: " + arr.get(i).date);
            System.out.println("Ціна: " + arr.get(i).price);
        }
        else System.out.println("Масив пустий");
    }
    public void print() throws IOException {
                System.out.println("Марка авомобіля: " + this.brand);
                System.out.println("Потужність: " + this.power);
                System.out.println("Країна виробник: " + this.country);
                System.out.println("Дата випуску: " + this.date);
                System.out.println("Ціна: " + this.price);
    }
    public static void serialization(ArrayList<Car> arr) {
        try {
            FileOutputStream fos = new FileOutputStream("cars.ser");
            ObjectOutputStream out = new ObjectOutputStream(fos);
            for (int i = 0; i < arr.size(); i++) {
                out.writeObject(arr.get(i));
            }
                out.close();
                fos.close();
        }catch (IOException i){
            System.out.println("Помилка серіалізації");
        }
    }
    public static ArrayList<Car> searchByBrand(ArrayList<Car> arr, String brand) {
        ArrayList<Car> search = new ArrayList<>();
            for (int i = 0; i < arr.size(); i++) {
                if(arr.get(i).brand.equals(brand))search.add(arr.get(i));
            }
            return search;
    }
    public static ArrayList<Car> searchByPower(ArrayList<Car> arr, int power) {
        ArrayList<Car> search = new ArrayList<>();
        for (int i = 0; i < arr.size(); i++) {
            if(arr.get(i).power == power)search.add(arr.get(i));
        }
        return search;
    }
    public static ArrayList<Car> searchByCountry(ArrayList<Car> arr, String country) {
        ArrayList<Car> search = new ArrayList<>();
        for (int i = 0; i < arr.size(); i++) {
            if(arr.get(i).country.equals(country))search.add(arr.get(i));
        }
        return search;
    }
    public static ArrayList<Car> searchByDate(ArrayList<Car> arr, int date) {
        ArrayList<Car> search = new ArrayList<>();
        for (int i = 0; i < arr.size(); i++) {
            if(arr.get(i).date == date)search.add(arr.get(i));
        }
        return search;
    }
    public static ArrayList<Car> searchByPrice(ArrayList<Car> arr, double price) {
        ArrayList<Car> search = new ArrayList<>();
        for (int i = 0; i < arr.size(); i++) {
            if(arr.get(i).price == price)search.add(arr.get(i));
        }
        return search;
    }
    public static ArrayList<Car> deserialization() {
        ArrayList<Car> arr = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream("cars.ser");
            ObjectInputStream in = new ObjectInputStream(fis);
            Car car = (Car)in.readObject();
             do{
                arr.add(car);
                 try {
                     System.out.println("+"+car.getBrand());
                     car = (Car)in.readObject();
                 }catch (IOException i){
                     return arr;
                 }
            }while (car != null);
            in.close();
            fis.close();
        }catch (IOException | ClassNotFoundException i){
            System.out.println("Помилка десеріалізації");
        }
        return arr;
    }
    public static Comparator<Car> brand_comparator = new Comparator<Car>() {

        public int compare(Car s1, Car s2) {
            String brand1 = s1.getBrand().toUpperCase();
            String brand2 = s2.getBrand().toUpperCase();
            return brand1.compareTo(brand2);
        }
    };
    public static Comparator<Car> power_comparator = new Comparator<Car>() {

        public int compare(Car s1, Car s2) {
            int power1 = s1.getPower();
            int power2 = s2.getPower();
            return (power1 - power2);
        }
    };
    public static Comparator<Car> country_comparator = new Comparator<Car>() {

        public int compare(Car s1, Car s2) {
            String country1 = s1.getCountry().toUpperCase();
            String country2 = s2.getCountry().toUpperCase();
            return country1.compareTo(country2);
        }
    };
    public static Comparator<Car> date_comparator = new Comparator<Car>() {

        public int compare(Car s1, Car s2) {
            int date1 = s1.getDate();
            int date2 = s2.getDate();
            return (date1 - date2);
        }
    };
    public static Comparator<Car> price_comparator = new Comparator<Car>() {

        public int compare(Car s1, Car s2) {
            double price1 = s1.getPrice()*100;
            double price2 = s2.getPrice()*100;
            return (int) (price1 - price2);
        }
    };

    public static ArrayList<Car> menu_sort(BufferedReader reader, ArrayList<Car> arr) throws IOException {
        System.out.println("Сортувати за");
        System.out.println("1 : Марка авомобіля");
        System.out.println("2 : Потужність");
        System.out.println("3 : Країна виробник");
        System.out.println("4 : Дата випуску");
        System.out.println("5 : Ціна");
        int index  = Main.input_int(reader);
        switch (index){
            case 1:
                Collections.sort(arr, brand_comparator);
                break;
            case 2:
                Collections.sort(arr, power_comparator);
                break;
            case 3:
                Collections.sort(arr, country_comparator);
                break;
            case 4:
                Collections.sort(arr, date_comparator);
                break;
            case 5:
                Collections.sort(arr, price_comparator);
                break;
            default: arr = menu_sort(reader, arr);
        }
        return arr;
    }
    public static ArrayList<Car> menu_search(BufferedReader reader, ArrayList<Car> arr) throws IOException {
        ArrayList<Car> search = new ArrayList<>();
        System.out.println("Шукати за");
        System.out.println("1 : Марка авомобіля");
        System.out.println("2 : Потужність");
        System.out.println("3 : Країна виробник");
        System.out.println("4 : Дата випуску");
        System.out.println("5 : Ціна");
        int index  = Main.input_int(reader);
        switch (index){
            case 1:
                System.out.println("Введіть марку авомобіля");
                String brand = reader.readLine();
                search = searchByBrand(arr, brand);
                break;
            case 2:
                System.out.println("Введіть потужність");
                int power = Main.input_int(reader);
                search = searchByPower(arr, power);
                break;
            case 3:
                System.out.println("Введіть країну виробника");
                String country = reader.readLine();
                search = searchByCountry(arr, country);
                break;
            case 4:
                System.out.println("Введіть дату випуску");
                int date = Main.input_int(reader);
                search = searchByDate(arr, date);
                break;
            case 5:
                System.out.println("Введіть ціну");
                double price = Main.input_double(reader);
                search = searchByPrice(arr, price);
                break;
            default: search = menu_search(reader, arr);
        }
        return search;
    }
    public static ArrayList<Car> untill2000(BufferedReader reader, ArrayList<Car> arr) throws IOException {
        ArrayList<Car> search = new ArrayList<>();
        for (int i = 0; i < arr.size(); i++) {
            if(arr.get(i).date <= 2000)search.add(arr.get(i));
        }
        return search;
    }
    public static ArrayList<Car> after2000(BufferedReader reader, ArrayList<Car> arr) throws IOException {
        ArrayList<Car> search = new ArrayList<>();
        for (int i = 0; i < arr.size(); i++) {
            if(arr.get(i).date > 2000)search.add(arr.get(i));
        }
        return search;
    }
    public static void dividedByCountry(BufferedReader reader, ArrayList<Car> arr) throws IOException {
        Collections.sort(arr, country_comparator);
        int i = 0;
        do {
            String temp = arr.get(i).country;
            System.out.println("~~~~~~" + temp + "~~~~~~");
             do{
                arr.get(i).print();
                 System.out.println();
                i++;if(i==arr.size())return;
            }while (arr.get(i).country.equals(temp));
             if(i < arr.size())temp = arr.get(i).country;else return;
        }while(i < arr.size());
    }

    public static void menu(BufferedReader reader, ArrayList<Car> arr) throws IOException {
        System.out.println("Меню");
        System.out.println("1 : Додати новий об'єкт");
        System.out.println("2 : Вивести список");
        System.out.println("3 : Серіалізувати список");
        System.out.println("4 : Десеріалізувати список");
        System.out.println("5 : Видалити об'єкт по індексу");
        System.out.println("6 : Пошук");
        System.out.println("7 : Сортування");
        System.out.println("8 : Розділити машини на випущені до 2000 і після");
        System.out.println("9 : Розділити машини по країнах");
        System.out.println("0 : Вихід");
        int index  = Main.input_int(reader);
        switch (index){
            case 1:Car c = new Car();
                c.input(reader);
                arr.add(c);
                break;
            case 2: print(arr);
                break;
            case 3: serialization(arr);
                break;
            case 4: arr = deserialization(); print(arr);
                break;
            case 5: System.out.println("Введіть індекс елемента, який хочете видалити");
                int id = Main.input_int(reader);
                if(id > 0 && id <= arr.size())
                arr.remove(id-1);
                else {
                    System.out.println("Не існує елемента з таки індексом");
                    menu(reader, arr);
                }
                break;
            case 6: print(menu_search(reader, arr));
                break;
            case 7: arr = menu_sort(reader, arr); print(arr);
                break;
            case 8: System.out.println("Автомобілі випущені до 2000 включно");
                print(untill2000(reader, arr));
                System.out.println("\nАвтомобілі після до 2000");
                print(after2000(reader, arr));
                break;
            case 9: dividedByCountry(reader, arr);
                break;
            case 0: return;
            default: System.out.println("Не існує пункта меню з таки індексом");
            menu(reader, arr);
            break;
        }
        menu(reader, arr);
    }
}
