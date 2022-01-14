import java.util.Scanner;
import java.io.IOException;

public class AutomationOfAccounting { // Диалог с пользователем
    public static void main (String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printMenu();
            String userInput = scanner.next();

            switch (userInput) {
                case "1":
                    MonthlyReport monthlyReport = new MonthlyReport();
                    break;
                case "2":
                    YearlyReport yearlyReport = new YearlyReport();
                    break;
                case "3":
                    DataComparison.totalComparison();
                    break;
                case "4":
                    MonthlyReport.printMonthReport ();
                    break;
                case "5":
                    YearlyReport.printYearReport();
                    break;
                case "0":
                    System.out.println("Выход");
                    return;
                default:
                    System.out.println("Ошибка, такой команды нет.");
            }
        }
    }

    public static void printMenu() {
        System.out.println("Что вы хотите сделать? ");
        System.out.println("1 - Считать все месячные отчёты");
        System.out.println("2 - Считать годовой отчёт");
        System.out.println("3 - Сверить отчёты");
        System.out.println("4 - Вывести информацию о всех месячных отчётах");
        System.out.println("5 - Вывести информацию о годовом отчёте");
        System.out.println("0 - Выход");
    }
}
