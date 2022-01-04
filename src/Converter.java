public class Converter {
    double toDistance;
    double toCal;

    Converter(double distance, double cal) {
        toDistance = distance;
        toCal = cal;
    }

    double convertToDistanceKm (int completedSteps) {  // возвращает пройденную дистанцию (в км);
        return (double) completedSteps * toDistance/1000;
    }

    double convertToKkal (int completedSteps) {   // возвращает количество сожжённых килокалорий
        return (double) completedSteps * toCal/1000;
    }
}
