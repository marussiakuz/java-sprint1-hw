import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class StepTracker {
    HashMap<String, int[]> stepInfo = new HashMap<>();
    int stepsGoal = 10000;
    Scanner scanner = new Scanner(System.in);

    StepTracker() {
        int[] days = new int[30];
        stepInfo.put("январь", days);
        stepInfo.put("февраль", days);
        stepInfo.put("март", days);
        stepInfo.put("апрель", days);
        stepInfo.put("май", days);
        stepInfo.put("июнь", days);
        stepInfo.put("июль", days);
        stepInfo.put("август", days);
        stepInfo.put("сентябрь", days);
        stepInfo.put("октябрь", days);
        stepInfo.put("ноябрь", days);
        stepInfo.put("декабрь", days);
    }


    void saveStepsPerDay() {  // Ввести и сохранить количествo шагов за определённый день
        System.out.println("Укажите название месяца");
        String month = checkedMonth();
        System.out.println("Укажите день от 1 до 30");
        int day = checkedDay();
        System.out.println("Сколько Вы прошли в указанный день?");
        int stepsCompleted = checkedCompletedSteps();
        int[] days = stepInfo.get(month);
        days[day - 1] += stepsCompleted;
        stepInfo.put(month, days);
        if (month.equals("март") || month.equals("август")) {
            System.out.println("Сохранено. " + day + " " + month + "а Вы прошли " + days[day - 1] + " шагов");
        } else if (month.equals("май")) {
            System.out.println("Сохранено. " + day + " " + "мая" + " Вы прошли " + days[day - 1] + " шагов");
        } else System.out.println("Сохранено. " + day + " " + month.replace('ь', 'я') +
                " Вы прошли " + days[day - 1] + " шагов");
    }

    void getStatistics(Converter converter) {    // Подсчёт и вывод статистики за указанный пользователем месяц
        System.out.println("Укажите месяц, за который Вы хотите получить статистику");
        String month = checkedMonth();
        getStatisticsByDay(month);
        int completedSteps = completedStepsPerMonth(month);
        System.out.println("За месяц Вы прошли " + completedSteps + " шагов");
        System.out.println("Максимальное количество шагов за день составило: " + getMaxSteps(month));
        System.out.println("Среднее количество шагов за день составило: " + getAveragePerMonth(month));
        System.out.println("Пройденная дистанция: " + converter.convertToDistanceKm(completedSteps) + " км");
        System.out.println("Количество сожжённых килокалорий: " + converter.convertToKkal(completedSteps));
        System.out.println("Лучшая серия: " + getTheBestSeries(month) + " дней подряд");
    }

    void getStatisticsByDay (String month) {  // печатает количество пройденных шагов по дням
        System.out.println("Количество пройденных шагов по дням: ");
        int[] days = stepInfo.get(month.toLowerCase());
        for (int i = 0; i < days.length; i++) {
            if (i == 0) {
                System.out.print((i+1) + " день: " + days[i]);
            } else System.out.print(", " + (i+1) + " день: " + days[i]);
        }
        System.out.println();
    }

    int completedStepsPerMonth(String month) {  // возвращает сумму пройденных шагов за месяц
        int[] days = stepInfo.get(month);
        int sum = 0;
        for (int i = 0; i < days.length; i++) {
            sum += days[i];
        }
        return sum;
    }

    int getMaxSteps(String month) {   // возвращает максимальное пройденное количество шагов в месяце
        int[] days = stepInfo.get(month);
        int maxStepsPerDays = 0;
        for (int i = 0; i < days.length; i++) {
            if (days[i] > maxStepsPerDays) {
                maxStepsPerDays = days[i];
            }
        }
        return maxStepsPerDays;
    }

    double getAveragePerMonth(String month) {   // возвращает среднее количество шагов за месяц
        int[] days = stepInfo.get(month);
        int sum = 0;
        for (int i = 0; i < days.length; i++) {
            sum += days[i];
        }
        return (double) sum / days.length;
    }

    int getTheBestSeries(String month) {   // возвращает лучшую серию
        int[] days = stepInfo.get(month);
        ArrayList<Integer> series = new ArrayList<Integer>();
        for (int i = 0; i < days.length; i++) {
            if (days[i] > stepsGoal) {
                series.add(i);
            }
        }
        if (series.size() > 0) {
            int theBestSeries = 1;
            int max = 1;
            for (int i = 0; i < series.size(); i++) {
                if (i==(series.size()-1)) break;
                if ((series.get(i)+1) == series.get(i + 1)) {
                    if (++theBestSeries>max) max = theBestSeries;
                } else {
                    theBestSeries = 1;
                }
            }
            return max;
        }
        else return 0;
    }

    void changeStepsGoal() {   // меняет целевое количество шагов
        System.out.println("Введите количество шагов:");
        int newStepsGoal = scanner.nextInt();
        if (newStepsGoal < 0) {
            System.out.println("Значение не может быть отрицательным, попробуйте снова");
            return;
        } else if (newStepsGoal == 0) {
            System.out.println("Цель не может быть 0 шагов в день, попробуйте снова");
            return;
        } else {
            stepsGoal = newStepsGoal;
            System.out.println("Теперь Ваша цель " + stepsGoal + " шагов в день");
        }
    }

    String checkedMonth () {   // проверить правильность ввода месяца и вернуть значение
        String month = scanner.next();
        if (stepInfo.containsKey(month.toLowerCase())) {
            return month.toLowerCase();
        }
        else {
            do {
                System.out.println("Неправильно указан месяц, попробуйте снова");
                month = scanner.next();
            }
            while (!(stepInfo.containsKey(month.toLowerCase())));
        }
        return month.toLowerCase();
    }

    int checkedDay () {   // проверить правильность ввода дня и вернуть значение
        int day = scanner.nextInt();
        if (day >= 1 && day <= 30) {
            return day;
        } else {
            do {
                System.out.println("Можно указать только от 1 до 30, попробуйте снова");
                day = scanner.nextInt();
            }
            while (day < 1 || day > 30);
            return day;
        }
    }

    int checkedCompletedSteps () {   // проверить правильность ввода количества шагов и вернуть значение
        int completedSteps = scanner.nextInt();
        if (completedSteps >= 1) {
            return completedSteps;
        } else {
            do {
                System.out.println("Значение не может быть отрицательным или равным 0.");
                System.out.println("Попробуйте снова.");
                completedSteps = scanner.nextInt();
            }
            while (completedSteps <= 0);
            return completedSteps;
        }
    }
}