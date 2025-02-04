abstract class ECommerce {
    public abstract double calculateDiscount(double price);
    public abstract double calculateDiscount(double price, double discountPercentage);
    public abstract double calculateDiscount(double price, boolean isPremiumMember, double discountPercentage);
    public abstract double calculateDiscount(double price, int quantity, boolean isBOGO);
}

class ConcreteECommerce extends ECommerce {
    @Override
    public double calculateDiscount(double price) {
        return price;
    }

    @Override
    public double calculateDiscount(double price, double discountPercentage) {
        return price - (price * discountPercentage / 100);
    }

    @Override
    public double calculateDiscount(double price, boolean isPremiumMember, double discountPercentage) {
        if (isPremiumMember) {
            return price - (price * discountPercentage / 100);
        }
        return price;
    }

    @Override
    public double calculateDiscount(double price, int quantity, boolean isBOGO) {
        if (isBOGO && quantity > 1) {
            return price * ((quantity / 2) + (quantity % 2));
        }
        return price * quantity;
    }
}

public class ECommerceSystem {
    public static void main(String[] args) {
        ECommerce eCommerce = new ConcreteECommerce();
        System.out.println("No Discount: " + eCommerce.calculateDiscount(1000));
        System.out.println("Flat Discount: " + eCommerce.calculateDiscount(1000, 10));
        System.out.println("Premium Member Discount: " + eCommerce.calculateDiscount(1000, true, 15));
        System.out.println("BOGO Offer: " + eCommerce.calculateDiscount(100, 3, true));
    }
}