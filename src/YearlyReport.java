import java.time.Month;
import java.util.ArrayList;

public class YearlyReport {  // Годовой отчет
    static ArrayList<YearData> dataByYear = new ArrayList<>();

    public YearlyReport() { // заполнение списка расходами/доходами за год
        dataByYear = DataReading.parseFileContentsForYear("y.2021.csv");
        System.out.println("Отчет за 2021 год успешно считан");
    }

    public static ArrayList<YearData> getDataByYear (){  // вернуть список с данными за год
        return dataByYear;
    }

    public static double getTotalExpenseOfYear (){  // получить сумму расходов за год
        double totalExpense = 0.0;
        for(YearData yearData : dataByYear) {
            if (yearData.is_expense) totalExpense += yearData.amount;
            else continue;
        }
        return totalExpense;
    }

    public static double getTotalInputOfYear (){ // получить сумму доходов за год
        double totalInput = 0.0;
        for(YearData yearData : dataByYear) {
            if (!yearData.is_expense) totalInput += yearData.amount;
            else continue;
        }
        return totalInput;
    }

    public static void printYearReport(){  // вывести информацию о годовом отчете
        if (checkHasYearlyReport ()==false) return;
        System.out.println("Отчет за 2021 год:");
        printAverageYearReport();
        double expenseOfJanuary = 0.0;
        double inputOfJanuary = 0.0;
        double expenseOfFebruary = 0.0;
        double inputOfFebruary = 0.0;
        double expenseOfMarch = 0.0;
        double inputOfMarch = 0.0;
        for(YearData yearData : dataByYear) {
            if (yearData.month.equals(Month.JANUARY)){
                if (yearData.is_expense) expenseOfJanuary = yearData.amount;
                else inputOfJanuary = yearData.amount;
            }
            else if (yearData.month.equals(Month.FEBRUARY)){
                if (yearData.is_expense) expenseOfFebruary = yearData.amount;
                else inputOfFebruary = yearData.amount;
            } else {
                if (yearData.is_expense) expenseOfMarch = yearData.amount;
                else inputOfMarch = yearData.amount;
            }
        }
        System.out.println("Прибыль в январе составила: " + (inputOfJanuary-expenseOfJanuary));
        System.out.println("Прибыль в феврале составила: " + (inputOfFebruary-expenseOfFebruary));
        System.out.println("Прибыль в марте составила: " + (inputOfMarch-expenseOfMarch));
    }

    public static void printAverageYearReport(){ // получить средний расход/расход за все месяцы в году
        int expenseQuantity = 0;
        int inputQuantity = 0;
        double sumOfExpense = 0;
        double sumOfInput = 0;
        for(YearData yearData : dataByYear) {
            if (yearData.is_expense){
                sumOfExpense += yearData.amount;
                ++expenseQuantity;
            }
            else {
                sumOfInput += yearData.amount;
                ++inputQuantity;
            }
        }
        System.out.println("Средний расход в течение года составил: " + sumOfExpense/expenseQuantity);
        System.out.println("Средний доход в течение года составил: " + sumOfInput/inputQuantity);
    }

    public static boolean checkHasYearlyReport () {   // проверка, был ли считан годовой отчет
        if (dataByYear.isEmpty()) {
            System.out.println("Для начала работы требуется считать годовой отчет");
            System.out.println("Пожалуйста, введите в консоли \"2\"");
        }
        return !dataByYear.isEmpty();
    }
}
