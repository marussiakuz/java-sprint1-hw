public class MonthData { // информация по одной единице расхода/дохода
    String item_name;
    boolean is_expense;
    int quantity;
    double sum_of_one;

    public MonthData(String item_name, boolean is_expense, int quantity, double sum_of_one){
        this.item_name = item_name;
        this.is_expense = is_expense;
        this.quantity = quantity;
        this.sum_of_one = sum_of_one;
    }
}
