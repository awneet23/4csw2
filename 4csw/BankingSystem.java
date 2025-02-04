abstract class Bank {
  public abstract double calculateSavingsInterest(double principal, int time);
  public abstract double calculateFixedDepositInterest(double principal, int time, double rate);
  public abstract double calculateRecurringDepositInterest(double monthlyDeposit, int time, double rate);
}

class ConcreteBank extends Bank {
  @Override
  public double calculateSavingsInterest(double principal, int time) {
      double rate = 4.0;
      return (principal * rate * time) / 100;
  }

  @Override
  public double calculateFixedDepositInterest(double principal, int time, double rate) {
      return (principal * rate * time) / 100;
  }

  @Override
  public double calculateRecurringDepositInterest(double monthlyDeposit, int time, double rate) {
      double totalDeposit = monthlyDeposit * time * 12;
      return (totalDeposit * rate * time) / 100;
  }
}

public class BankingSystem {
  public static void main(String[] args) {
      Bank bank = new ConcreteBank();
      System.out.println("Savings Interest: " + bank.calculateSavingsInterest(10000, 2));
      System.out.println("Fixed Deposit Interest: " + bank.calculateFixedDepositInterest(10000, 2, 7.5));
      System.out.println("Recurring Deposit Interest: " + bank.calculateRecurringDepositInterest(1000, 2, 6.0));
  }
}