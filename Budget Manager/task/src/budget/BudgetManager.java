package budget;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BudgetManager {
    double balance;
    Map<String, Double> purchases;
    Map<String, Double> food;
    Map<String, Double> clothes;
    Map<String, Double> entertainment;
    Map<String, Double> others;

    BudgetManager() {
        this.balance = 0;
        this.purchases = new HashMap<>();
        this.food = new HashMap<>();
        this.clothes = new HashMap<>();
        this.entertainment = new HashMap<>();
        this.others = new HashMap<>();
    }

    public void addIncome() {
        System.out.println("\nEnter income:");
        Scanner scanner = new Scanner(System.in);
        balance += Double.parseDouble(scanner.nextLine());
        System.out.println("Income was added!");
    }

    public void addPurchase(Map<String, Double> map) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEnter purchase name:");
        String s = scanner.nextLine();
        System.out.println("Enter its price:");
        try {
            double price = Double.parseDouble(scanner.nextLine());
            map.put(s, price);
            purchases.put(s, price);
            balance -= price;
            System.out.println("Purchase was added!");
        } catch (NumberFormatException e){
            System.out.println("Wrong input, purchase was not added");
        }
    }

    public void showPurchaseList() {
        if (purchases.size() == 0) {
            System.out.println("\nThe purchase list is empty");
        } else {
            double total = 0;
            System.out.println();
            for (String key : purchases.keySet()) {
                System.out.println(key + " $" + String.format("%,.2f", purchases.get(key)));
                total += purchases.get(key);
            }
            System.out.println("Total sum: $" + String.format("%,.2f", total));
        }
    }

    public void showPurchaseList(Map<String, Double> map) {
        if (map.size() == 0) {
            System.out.println("\nThe purchase list is empty");
        } else {
            double total = 0;
            System.out.println();
            for (String key : map.keySet()) {
                System.out.println(key + " $" + String.format("%,.2f", map.get(key)));
                total += map.get(key);
            }
            System.out.println("Total sum: $" + String.format("%,.2f", total));
        }
    }

    public void showBalance() {
        System.out.println("\nBalance: $" + String.format("%,.2f", this.balance));
    }

    public void save() throws IOException {
        File file = new File("purchases.txt");
        FileWriter fw = new FileWriter(file);

        LocalDateTime ldt = LocalDateTime.now();
        fw.write("Purchases; saved " + ldt + "\n\n");

        fw.write("--- BALANCE ---\n");
        fw.write(balance + "\n\n");

        fw.write("--- FOOD ---\n");
        for (var entry : food.entrySet()) {
            fw.write(entry.getKey() + " || $" + entry.getValue() + "\n");
        }
        fw.write("---------\n\n");

        fw.write("--- CLOTHES ---\n");
        for (var entry : clothes.entrySet()) {
            fw.write(entry.getKey() + " || $" + entry.getValue() + "\n");
        }
        fw.write("---------\n\n");

        fw.write("--- ENTERTAINMENT ---\n");
        for (var entry : entertainment.entrySet()) {
            fw.write(entry.getKey() + " || $" + entry.getValue() + "\n");
        }
        fw.write("---------\n\n");

        fw.write("--- OTHERS ---\n");
        for (var entry : others.entrySet()) {
            fw.write(entry.getKey() + " || $" + entry.getValue() + "\n");
        }
        fw.write("---------\n\n");

        fw.close();

        System.out.println("Purchases were saved!");
    }

    public void load() throws FileNotFoundException {
        File file = new File("purchases.txt");
        Scanner scanner = new Scanner(file);

        while(scanner.hasNextLine()) {
            String s = scanner.nextLine();
            switch (s) {
                case "--- BALANCE ---":
                    s = scanner.nextLine();
                    balance = Double.parseDouble(s);
                    break;
                case "--- FOOD ---":
                     while(true) {
                        s = scanner.nextLine();
                        if (!s.equals("---------")) {
                            String[] data = s.split(" \\|\\| \\$");
                            food.put(data[0], Double.parseDouble(data[1]));
                            purchases.put(data[0], Double.parseDouble(data[1]));
                        } else {
                            break;
                        }
                    }
                    break;
                case "--- CLOTHES ---":
                    while (true) {
                        s = scanner.nextLine();
                        if (!s.equals("---------")) {
                            String[] data = s.split(" \\|\\| \\$");
                            clothes.put(data[0], Double.parseDouble(data[1]));
                            purchases.put(data[0], Double.parseDouble(data[1]));
                        } else {
                            break;
                        }
                    }
                    break;
                case "--- ENTERTAINMENT ---":
                    while (true) {
                        s = scanner.nextLine();
                        if (!s.equals("---------")) {
                            String[] data = s.split(" \\|\\| \\$");
                            entertainment.put(data[0], Double.parseDouble(data[1]));
                            purchases.put(data[0], Double.parseDouble(data[1]));
                        } else {
                            break;
                        }
                    }
                    break;
                case "--- OTHERS ---":
                    while (true) {
                        s = scanner.nextLine();
                        if (!s.equals("---------")) {
                            String[] data = s.split(" \\|\\| \\$");
                            others.put(data[0], Double.parseDouble(data[1]));
                            purchases.put(data[0], Double.parseDouble(data[1]));
                        } else {
                            break;
                        }
                    }
                    break;
            }
        }
        scanner.close();

        System.out.println("\nPurchases were loaded!");
    }

    public void analyzeAllPurchases() {
        if(purchases.size() > 0) {
            QuickSort sort = new QuickSort(purchases);
            sort.quicksort(0, sort.prices.size() - 1);
            double total = 0;
            for (double d : sort.prices) {
                total += d;
            }
            System.out.println("\nAll:");
            for (int i = sort.prices.size() - 1; i >= 0; i--) {
                System.out.println(sort.names.get(i) + " $" + String.format("%,.2f", sort.prices.get(i)));
            }
            System.out.println("Total: $" + String.format("%,.2f", total));
        } else {
            System.out.println("\nThe purchase list is empty");
        }
    }

    public void analyzeCertain(Map<String, Double> map, String type) {
        if(map.size() > 0) {
            QuickSort sort = new QuickSort(map);
            sort.quicksort(0, sort.prices.size() - 1);
            double total = 0;
            for (double d : sort.prices) {
                total += d;
            }
            System.out.println("\n" + type + ":");
            for (int i = sort.prices.size() - 1; i >= 0; i--) {
                System.out.println(sort.names.get(i) + " $" + String.format("%,.2f", sort.prices.get(i)));
            }
            System.out.println("Total: $" + String.format("%,.2f", total));
        } else {
            System.out.println("\nThe purchase list is empty");
        }
    }

    public void analyzeAllTypes() {
        Map<String, Double> map = new HashMap<>();
        double total = 0;
        for (String s : food.keySet()) {
            total += food.get(s);
        }
        map.put("Food", total);

        total = 0;
        for (String s : clothes.keySet()) {
            total += clothes.get(s);
        }
        map.put("Clothes", total);

        total = 0;
        for (String s : entertainment.keySet()) {
            total += entertainment.get(s);
        }
        map.put("Entertainment", total);

        total = 0;
        for (String s : others.keySet()) {
            total += others.get(s);
        }
        map.put("Other", total);

        QuickSort sort = new QuickSort(map);
        sort.quicksort(0, sort.prices.size() - 1);

        total = 0;
        for (double d : sort.prices) {
            total += d;
        }
        System.out.println("\nTypes:");
        for (int i = sort.prices.size() - 1; i >= 0; i--) {
            System.out.println(sort.names.get(i) + " - $" + String.format("%,.2f", sort.prices.get(i)));
        }
        System.out.println("Total sum: $" + String.format("%,.2f", total));
    }
 }
