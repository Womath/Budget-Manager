package budget;

import java.io.IOException;
import java.util.Scanner;

public class Menu {

    BudgetManager budgetManager;
    Options op;
    PurchaseOptions pOp;
    AnalyzeOptions aOp;

    enum Options {
        INCOME(1),
        PURCHASE(2),
        LIST(3),
        BALANCE(4),
        SAVE(5),
        LOAD(6),
        ANALYZE(7),
        EXIT(0);

        Options(int i){
        }
    }

    enum PurchaseOptions {
        FOOD,
        CLOTHES,
        ENTERTAINMENT,
        OTHER,
        ALL,
        BACK
    }

    enum AnalyzeOptions {
        ALL,
        TYPE,
        CERTAIN,
        BACK
    }

    Menu(BudgetManager budgetManager) {
        this.budgetManager = budgetManager;
    }

    public void menu() throws IOException {
        System.out.println("\nChoose your action\n" +
                "1) Add income\n" +
                "2) Add purchase\n" +
                "3) Show list of purchases\n" +
                "4) Balance\n" +
                "5) Save\n" +
                "6) Load\n" +
                "7) Analyze (Sort)\n" +
                "0) Exit");

        Scanner scanner = new Scanner(System.in);
        int i = Integer.parseInt(scanner.nextLine());
        switch (i) {
            case 0:
                this.op = Options.EXIT;
                break;
            case 1:
                this.op = Options.INCOME;
                break;
            case 2:
                this.op = Options.PURCHASE;
                break;
            case 3:
                this.op = Options.LIST;
                break;
            case 4:
                this.op = Options.BALANCE;
                break;
            case 5:
                this.op = Options.SAVE;
                break;
            case 6:
                this.op = Options.LOAD;
                break;
            case 7:
                this.op = Options.ANALYZE;
                break;
            default:
                System.out.println("Wrong input");
                return;

        }

        boolean menuLoop = true;

        switch (op) {
            case INCOME:
                budgetManager.addIncome();
                break;
            case PURCHASE:
                while(menuLoop) {
                    menuLoop = purchaseMenu();
                }
                break;
            case LIST:
                while(menuLoop) {
                    menuLoop = listMenu();
                }
                break;
            case BALANCE:
                budgetManager.showBalance();
                break;
            case SAVE:
                budgetManager.save();
                break;
            case LOAD:
                budgetManager.load();
                break;
            case ANALYZE:
                while(menuLoop) {
                    menuLoop = analyzeMenu();
                }
                break;
            case EXIT:
                System.out.println("\nBye!");
                System.exit(0);
                break;
        }


    }

    boolean analyzeMenu() {
        System.out.println("\nHow do you want to sort?\n" +
                "1) Sort all purchases\n" +
                "2) Sort by type\n" +
                "3) Sort certain type\n" +
                "4) Back");

        Scanner scanner = new Scanner(System.in);
        int i = Integer.parseInt(scanner.nextLine());

        switch (i) {
            case 1:
                aOp = AnalyzeOptions.ALL;
                break;
            case 2:
                aOp = AnalyzeOptions.TYPE;
                break;
            case 3:
                aOp = AnalyzeOptions.CERTAIN;
                break;
            case 4:
                aOp = AnalyzeOptions.BACK;
                break;
            default:
                System.out.println("Wrong input");
                break;
        }

        switch (aOp) {
            case ALL:
                budgetManager.analyzeAllPurchases();
                break;
            case TYPE:
                budgetManager.analyzeAllTypes();
                break;
            case CERTAIN:
                analyzeCertainMenu();
                break;
            case BACK:
                return false;
        }
        return true;
    }

    void analyzeCertainMenu() {
        System.out.println("\nChoose the type of purchase\n" +
                "1) Food\n" +
                "2) Clothes\n" +
                "3) Entertainment\n" +
                "4) Other\n");

        Scanner scanner = new Scanner(System.in);
        int i = Integer.parseInt(scanner.nextLine());

        switch (i) {
            case 1:
                this.pOp = PurchaseOptions.FOOD;
                break;
            case 2:
                this.pOp = PurchaseOptions.CLOTHES;
                break;
            case 3:
                this.pOp = PurchaseOptions.ENTERTAINMENT;
                break;
            case 4:
                this.pOp = PurchaseOptions.OTHER;
                break;
            default:
                System.out.println("Wrong input");
                break;

        }

        switch (pOp) {
            case FOOD:
                budgetManager.analyzeCertain(budgetManager.food, "Food");
                break;
            case CLOTHES:
                budgetManager.analyzeCertain(budgetManager.clothes, "Clothes");
                break;
            case ENTERTAINMENT:
                budgetManager.analyzeCertain(budgetManager.entertainment, "Enertainment");
                break;
            case OTHER:
                budgetManager.analyzeCertain(budgetManager.others, "Other");
                break;
            default:
                System.out.println("Wrong input");
        }

    }

    boolean purchaseMenu() {
        System.out.println("\nChoose the type of purchase\n" +
                "1) Food\n" +
                "2) Clothes\n" +
                "3) Entertainment\n" +
                "4) Other\n" +
                "5) Back");

        Scanner scanner = new Scanner(System.in);
        int i = Integer.parseInt(scanner.nextLine());

        switch (i) {
            case 1:
                this.pOp = PurchaseOptions.FOOD;
                break;
            case 2:
                this.pOp = PurchaseOptions.CLOTHES;
                break;
            case 3:
                this.pOp = PurchaseOptions.ENTERTAINMENT;
                break;
            case 4:
                this.pOp = PurchaseOptions.OTHER;
                break;
            case 5:
                this.pOp = PurchaseOptions.BACK;
                break;
            default:
                System.out.println("Wrong input");
                break;

        }

        switch (pOp) {
            case FOOD:
                budgetManager.addPurchase(budgetManager.food);
                break;
            case CLOTHES:
                budgetManager.addPurchase(budgetManager.clothes);
                break;
            case ENTERTAINMENT:
                budgetManager.addPurchase(budgetManager.entertainment);
                break;
            case OTHER:
                budgetManager.addPurchase(budgetManager.others);
                break;
            case BACK:
                return false;
        }
        return true;
    }

    boolean listMenu() {
        System.out.println("\nChoose the type of purchase\n" +
                "1) Food\n" +
                "2) Clothes\n" +
                "3) Entertainment\n" +
                "4) Other\n" +
                "5) All\n" +
                "6) Back");

        Scanner scanner = new Scanner(System.in);
        int i = Integer.parseInt(scanner.nextLine());

        switch (i) {
            case 1:
                this.pOp = PurchaseOptions.FOOD;
                break;
            case 2:
                this.pOp = PurchaseOptions.CLOTHES;
                break;
            case 3:
                this.pOp = PurchaseOptions.ENTERTAINMENT;
                break;
            case 4:
                this.pOp = PurchaseOptions.OTHER;
                break;
            case 5:
                this.pOp = PurchaseOptions.ALL;
                break;
            case 6:
                this.pOp = PurchaseOptions.BACK;
                break;
            default:
                System.out.println("Wrong input");
                break;

        }

        switch (pOp) {
            case FOOD:
                budgetManager.showPurchaseList(budgetManager.food);
                break;
            case CLOTHES:
                budgetManager.showPurchaseList(budgetManager.clothes);
                break;
            case ENTERTAINMENT:
                budgetManager.showPurchaseList(budgetManager.entertainment);
                break;
            case OTHER:
                budgetManager.showPurchaseList(budgetManager.others);
                break;
            case ALL:
                budgetManager.showPurchaseList();
                break;
            case BACK:
                return false;
        }
        return true;
    }
}
