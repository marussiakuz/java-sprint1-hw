import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.time.Month;

public class DataReading { // Чтение из файла и конвертация строк в данные по месяцам и по году
    public static Path getPath (String file) {  // поиск файла на компьютере пользователя и получение его пути
        try {
            Files.walkFileTree(Path.of("/Users"), new FileFinder(file));
        } catch (IOException e) {
            System.out.println("Файл " + file + " не найден.");
        }
        return FileFinder.getPathOfFile();
    }

    public static String readFileContentsOrNull(Path path){// чтение строк из файла
        if (path == null) {
            System.out.println("Возможно файл отсутствует на Вашем компьютере или находится в папке," +
                    " для доступа в которую требуется разрешение администратора.");
            return "";
        }
        try {
            return Files.readString(path);
        }
        catch (IOException e) {
            System.out.println("Ошибка при чтении файла.");
            return "";
        }
        catch (NullPointerException ex){
            System.out.println("Файл не найден");
            return "";
        }
    }

    public static ArrayList<MonthData> parseFileContentsForMonth(String file){ // конвертация строк в массив данных по одному месяцу
        String content = readFileContentsOrNull(getPath(file));
        if (content.isEmpty()) System.out.println("Файл " + file + " пуст");
        String[] strings = content.split("\\n");
        ArrayList<MonthData> monthData = new ArrayList<>();
        for (int i = 1; i< strings.length; i++){
            String[] line = strings[i].split(",");
            monthData.add(new MonthData(line[0],
                    Boolean.parseBoolean(line[1]),
                    Integer.parseInt(line[2]),
                    Double.parseDouble(line[3])));
        }
        return monthData;
    }

    public static ArrayList<YearData> parseFileContentsForYear(String file){ // конвертация строк в массив данных по одному году
        String content = readFileContentsOrNull(getPath(file));
        String[] strings = content.split("\\n");
        ArrayList<YearData> yearData = new ArrayList<>();
        for (int i = 1; i< strings.length; i++){
            String[] line = strings[i].split(",");
            yearData.add(new YearData(Month.of(Integer.parseInt(line[0])),
                    Double.parseDouble(line[1]),
                    Boolean.parseBoolean(line[2])));
        }
        return yearData;
    }
}
