import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Locale;

public class DataComparison {   // сверка данных между годовым отчетом и месячными отчетами
    static double expenseOfMonth;
    static double inputOfMonth;
    static double expenseOfYear;
    static double inputOfYear;
    static ArrayList<YearData> dataByYear = YearlyReport.getDataByYear();

    public static void ComparisonByMonth () {   // сравнение данных по каждому месяцу
        for (YearData yearData : dataByYear) {
            String month = yearData.month.getDisplayName(TextStyle.FULL_STANDALONE, new Locale("ru"));
            month = month.replace('ь','е');
            expenseOfMonth = MonthlyReport.getTotalExpenseForMonth(yearData.month);
            inputOfMonth = MonthlyReport.getTotalInputForMonth(yearData.month);
            if (yearData.is_expense) {
                if (yearData.amount == expenseOfMonth) continue;
                else System.out.println("Сумма расходов в " + month.replace('т','е') + " не совпадает");
            }
            else {
                if (yearData.amount == inputOfMonth) continue;
                else System.out.println("Сумма доходов в " + month + " не совпадает");
            }
        }
    }

    public static void totalComparison () {    // Сравнение по месяцам и по совокупным расходам/доходам за все месяцы
        if (checkIsNotNull () == false) return;
        ComparisonByMonth ();
        expenseOfYear = YearlyReport.getTotalExpenseOfYear();
        inputOfYear = YearlyReport.getTotalInputOfYear();
        double totalExpenseByMonth = 0.0;
        double totalInputByMonth = 0.0;
        for (int i = 1; i<4; i++){
            totalExpenseByMonth += MonthlyReport.getTotalExpenseForMonth(Month.of(i));
            totalInputByMonth += MonthlyReport.getTotalInputForMonth(Month.of(i));
        }
        if ((totalExpenseByMonth == expenseOfYear) && (totalInputByMonth == inputOfYear))
            System.out.println("Сверка данных за год и по месяцам прошла успешно, расхождений не выявлено");
        else System.out.println("В результате сверки данных выявлены расхождения");
    }

    public static boolean checkIsNotNull () {  // проверка, были ли считаны месячные и годовой отчеты
        return (MonthlyReport.checkHasMonthlyReport() && YearlyReport.checkHasYearlyReport());
    }
}
