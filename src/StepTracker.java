import java.util.ArrayList;
import java.util.HashMap;

public class StepTracker {
    HashMap<String, int[]> stepInfo = new HashMap<>();
    int stepsGoal = 10000;

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


    void saveStepsPerDay(String month, int day, int stepsCompleted) {
        int[] days = stepInfo.get(month.toLowerCase());
        days[day - 1] += stepsCompleted;
        stepInfo.put(month.toLowerCase(), days);
        if (month.equalsIgnoreCase("март") || month.equalsIgnoreCase("август")) {
            System.out.println("Сохранено. " + day + " " + month.toLowerCase() + "а Вы прошли " + days[day - 1] + " шагов");
        } else if (month.equalsIgnoreCase("май")) {
            System.out.println("Сохранено. " + day + " " + "мая" +
                    " Вы прошли " + days[day - 1] + " шагов");
        } else {
            month = month.toLowerCase();
            System.out.println("Сохранено. " + day + " " + month.replace('ь', 'я') +
                    " Вы прошли " + days[day - 1] + " шагов");
        }
    }

    void getStatistic(String month) {
        System.out.println("Количество пройденных шагов по дням: ");
        int[] days = stepInfo.get(month.toLowerCase());
        for (int i = 0; i < days.length; i++) {
            if (i == 0) {
                System.out.print((i+1) + " день: " + days[i]);
            } else System.out.print(", " + (i+1) + " день: " + days[i]);
        }
        System.out.println();
    }

    int completedStepsPerMonth(String month) {
        int[] days = stepInfo.get(month.toLowerCase());
        int sum = 0;
        for (int i = 0; i < days.length; i++) {
            sum += days[i];
        }
        return sum;
    }

    int getMaxSteps(String month) {
        int[] days = stepInfo.get(month.toLowerCase());
        int maxStepsPerDays = 0;
        for (int i = 0; i < days.length; i++) {
            if (days[i] > maxStepsPerDays) {
                maxStepsPerDays = days[i];
            }
        }
        return maxStepsPerDays;
    }

    double getAveragePerMonth(String month) {
        int[] days = stepInfo.get(month.toLowerCase());
        int sum = 0;
        for (int i = 0; i < days.length; i++) {
            sum += days[i];
        }
        return (double) sum / days.length;
    }

    int getTheBestSeries(String month) {
        int[] days = stepInfo.get(month.toLowerCase());
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

    void changeStepsGoal(int newStepsGoal) {
        stepsGoal = newStepsGoal;
        System.out.println("Теперь Ваша цель " + stepsGoal + " шагов в день");
    }

    boolean checkedMonth (String month) {
        if (stepInfo.containsKey(month.toLowerCase())) {
            return true;
        }
        else return false;
    }
}
