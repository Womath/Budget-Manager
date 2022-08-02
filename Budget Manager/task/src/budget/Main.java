package budget;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        BudgetManager budgetManager = new BudgetManager();
        Menu menu = new Menu(budgetManager);

        while (true) {
            menu.menu();
        }
    }
}