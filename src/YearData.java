import java.time.Month;

public class YearData {   // информация по расходу/доходу за один месяц в году
    Month month;
    double amount;
    boolean is_expense;

    public YearData (Month month, double amount, boolean is_expense){
        this.month = month;
        this.amount = amount;
        this.is_expense = is_expense;
    }
}
