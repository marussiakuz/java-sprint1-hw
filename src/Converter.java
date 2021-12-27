public class Converter {

        double toDistance;
        double toCal;

        Converter(double distance, double cal) {
            toDistance = distance;
            toCal = cal;
        }

        double convertToDistanceKm (int completedSteps) {
            return (double) completedSteps * toDistance/1000;
        }

        double convertToKkal (int completedSteps) {
            return (double) completedSteps * toCal/1000;
        }
    }
