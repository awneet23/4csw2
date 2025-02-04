abstract class RideSharing {
    public abstract double calculateFare(double distance);
    public abstract double calculateFare(double distance, int time);
    public abstract double calculateFare(double distance, int time, boolean isPeakHour);
    public abstract double calculateFare(double distance, int time, boolean isPeakHour, boolean isLuxury);
}

class ConcreteRideSharing extends RideSharing {
    @Override
    public double calculateFare(double distance) {
        return distance * 10;
    }

    @Override
    public double calculateFare(double distance, int time) {
        return (distance * 10) + (time * 2);
    }

    @Override
    public double calculateFare(double distance, int time, boolean isPeakHour) {
        double fare = (distance * 10) + (time * 2);
        return isPeakHour ? fare * 1.2 : fare;
    }

    @Override
    public double calculateFare(double distance, int time, boolean isPeakHour, boolean isLuxury) {
        double fare = isLuxury ? (distance * 20) + (time * 2) : (distance * 10) + (time * 2);
        return isPeakHour ? fare * 1.2 : fare;
    }
}

public class RideSharingSystem {
    public static void main(String[] args) {
        RideSharing ride = new ConcreteRideSharing();
        System.out.println("Normal Ride Fare: " + ride.calculateFare(10));
        System.out.println("With Waiting Charge: " + ride.calculateFare(10, 30));
        System.out.println("Peak Hours Fare: " + ride.calculateFare(10, 30, true));
        System.out.println("Luxury Ride Fare: " + ride.calculateFare(10, 30, true, true));
    }
}