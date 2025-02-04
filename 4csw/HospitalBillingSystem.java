abstract class HospitalBilling {
    public abstract double calculateBill(double consultationFee);
    public abstract double calculateBill(double consultationFee, int days);
    public abstract double calculateBill(double consultationFee, int days, double surgeryCharges);
    public abstract double calculateBill(double consultationFee, int days, double surgeryCharges, boolean hasInsurance);
}

class ConcreteHospitalBilling extends HospitalBilling {
    @Override
    public double calculateBill(double consultationFee) {
        return consultationFee;
    }

    @Override
    public double calculateBill(double consultationFee, int days) {
        return consultationFee + (days * 2000);
    }

    @Override
    public double calculateBill(double consultationFee, int days, double surgeryCharges) {
        return consultationFee + (days * 2000) + surgeryCharges;
    }

    @Override
    public double calculateBill(double consultationFee, int days, double surgeryCharges, boolean hasInsurance) {
        double total = consultationFee + (days * 2000) + surgeryCharges;
        return hasInsurance ? total * 0.8 : total;
    }
}

public class HospitalBillingSystem {
    public static void main(String[] args) {
        HospitalBilling billing = new ConcreteHospitalBilling();
        System.out.println("Bill: " + billing.calculateBill(500));
        System.out.println("Bill with Room Charges: " + billing.calculateBill(500, 3));
        System.out.println("Bill with Surgery Charges: " + billing.calculateBill(500, 3, 10000));
        System.out.println("Bill with Insurance Discount: " + billing.calculateBill(500, 3, 10000, true));
    }
}