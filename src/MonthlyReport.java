import java.time.Month;
import java.time.format.TextStyle;
import java.util.*;

public class MonthlyReport {  // Помесячный отчет
    static HashMap<Month, ArrayList<MonthData>> data = new LinkedHashMap<>();

    public MonthlyReport() {   // заполнение данных по каждому из имеющихся месяцев
        for(int i = 1; i<4; i++){
            String file = "m.20210" + i + ".csv";
            ArrayList<MonthData> monthData = DataReading.parseFileContentsForMonth(file);
            data.put(Month.of(i), monthData);
            String month = Month.of(i).getDisplayName(TextStyle.FULL_STANDALONE, new Locale("ru"));
            if (!monthData.isEmpty()) System.out.println("Отчет за " + month + " успешно считан");
            else System.out.println("Произошла ошибка при считывании отчета за " + month +
                    ", проверьте нахождение файла " + file + " и повторите попытку.");
        }
    }

    public static HashMap<Month, ArrayList<MonthData>> getMonthlyData (){ // вернуть хэш-карту с данными по каждому месяцу
        return data;
    }

    public static double getTotalExpenseForMonth (Month month) { // получить сумму расходов за один месяц
        ArrayList<MonthData> listOfDataForOneMonth = data.get(month);
        double totalExpenseForMonth = 0.0;
        for (int i = 0; i < listOfDataForOneMonth.size(); i++) {
            MonthData monthData = listOfDataForOneMonth.get(i);
            if (monthData.is_expense) {
                totalExpenseForMonth += monthData.quantity * monthData.sum_of_one;
            } else continue;
        }
        return totalExpenseForMonth;
    }

    public static double getTotalInputForMonth (Month month) { // получить сумму доходов за один месяц
        ArrayList<MonthData> listOfDataForOneMonth = data.get(month);
        double totalExpenseForMonth = 0.0;
        for (int i = 0; i < listOfDataForOneMonth.size(); i++) {
            MonthData monthData = listOfDataForOneMonth.get(i);
            if (!monthData.is_expense) {
                totalExpenseForMonth += monthData.quantity * monthData.sum_of_one;
            } else continue;
        }
        return totalExpenseForMonth;
    }

    public static void printMonthReport(){     // вывести информацию о всех месячных отчетах
        if (checkHasMonthlyReport()==false) return;
        for(Map.Entry<Month, ArrayList<MonthData>> pair : data.entrySet()) {
            ArrayList<MonthData> dataListOfOfMonth = data.get(pair.getKey());
            double maxExpense = 0.0;
            double maxIncome = 0.0;
            String incomeItemName = "";
            String expenseItemName = "";
            for (int i = 0; i < dataListOfOfMonth.size(); i++) {
                MonthData monthData = dataListOfOfMonth.get(i);
                double totalSumOfItem = monthData.quantity * monthData.sum_of_one;
                if (monthData.is_expense) {
                    if (totalSumOfItem > maxExpense) {
                        maxExpense = totalSumOfItem;
                        expenseItemName = monthData.item_name;
                    } else continue;
                } else {
                    if (totalSumOfItem > maxIncome) {
                        maxIncome = totalSumOfItem;
                        incomeItemName = monthData.item_name;
                    } else continue;
                }
            }
            String month = "";
            switch (pair.getKey()){
                case JANUARY:
                    month = "в январе: ";
                    break;
                case FEBRUARY:
                    month = "в феврале: ";
                    break;
                case MARCH:
                    month = "в марте: ";
            }
            System.out.println("Самый прибыльный товар "+ month);
            System.out.println(incomeItemName + ", cумма " + maxIncome);
            System.out.println("Самая большая трата " + month);
            System.out.println(expenseItemName + ", cумма " + maxExpense);
            System.out.println("____________________________________________");
        }
    }

    public static boolean checkHasMonthlyReport () {  // проверка, были ли считаны месячные отчеты
        if (data.isEmpty()) {
            System.out.println("Для начала работы требуется считать месячные отчеты");
            System.out.println("Пожалуйста, введите в консоли \"1\"");
        }
        return !data.isEmpty();
    }
}
