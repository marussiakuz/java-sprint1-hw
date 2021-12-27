import java.util.Scanner;

public class Main {

        public static void main (String[] args) {
            Scanner scanner = new Scanner(System.in);

            Converter converter = new Converter(75, 50);
            StepTracker stepTracker = new StepTracker();

            while (true) {
                printMenu();
                int command = scanner.nextInt();

                if (command == 1) {
                    System.out.println("Укажите название месяца");
                    String month = scanner.next();
                    if (stepTracker.checkedMonth(month)) {
                        System.out.println("Укажите день от 1 до 30");
                        int day = scanner.nextInt();
                        if (day < 1 || day > 30) {
                            System.out.println("Можно указать только от 1 до 30, попробуйте снова");
                            continue;
                        }
                        System.out.println("Сколько Вы прошли в указанный день?");
                        int stepsCompleted = scanner.nextInt();
                        if (stepsCompleted < 0) {
                            System.out.println("Значение не может быть отрицательным");
                            continue;
                        }
                        stepTracker.saveStepsPerDay(month, day, stepsCompleted);
                    } else System.out.println("Неправильно указан месяц, попробуйте снова");

                } else if (command == 2) {
                    System.out.println("Укажите месяц, за который Вы хотите получить статистику?");
                    String month = scanner.next();
                    if (stepTracker.checkedMonth(month)) {
                        stepTracker.getStatistic(month);
                        int completedSteps = stepTracker.completedStepsPerMonth(month);
                        System.out.println("За месяц Вы прошли " + completedSteps + " шагов");
                        System.out.println("Максимальное количество шагов за день составило: " + stepTracker.getMaxSteps(month));
                        System.out.println("Среднее количество шагов за день составило: " + stepTracker.getAveragePerMonth(month));
                        System.out.println("Пройденная дистанция: " + converter.convertToDistanceKm(completedSteps) + " км");
                        System.out.println("Количество сожжённых килокалорий: " + converter.convertToKkal(completedSteps));
                        System.out.println("Лучшая серия: " + stepTracker.getTheBestSeries(month) + " дней подряд");
                    } else System.out.println("Неправильно указан месяц, попробуйте снова");

                } else if (command == 3) {
                    System.out.println("Введите количество шагов:");
                    int newStepsGoal = scanner.nextInt();
                    if (newStepsGoal<0) {
                        System.out.println("Значение не может быть отрицательным, попробуйте снова");
                        continue;
                    }
                    else if (newStepsGoal==0) {
                        System.out.println("Цель не может быть 0 шагов в день, попробуйте снова");
                        continue;
                    }
                    else stepTracker.changeStepsGoal(newStepsGoal);

                } else if (command == 0) {
                    System.out.println("Выход");
                    break;
                } else {
                    System.out.println("Извините, такой команды пока нет.");
                    break;
                }
            }
        }

        public static void printMenu() {
            System.out.println("Что вы хотите сделать? ");
            System.out.println("1 - Ввести количество шагов за определённый день");
            System.out.println("2 - Напечатать статистику за определённый месяц");
            System.out.println("3 - Изменить цель по количеству шагов в день");
            System.out.println("0 - Выход");
        }
    }

