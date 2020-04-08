import java.util.*;

class MyException extends Exception {

}

abstract class Account {

    // variable declaration
    float account_balance;
    String cust_name;

    // public var
    public int account_num;
    // private var
    private String cust_password;

    // default constructor
    public Account() {
        account_num = 0;
        account_balance = 0;
        cust_name = "null";
        cust_password = "null";
    }

    // parameterized constructor
    public Account(int account_num, String cust_name, int account_balance, String cust_password) {
        this.account_num = account_num;
        this.cust_name = cust_name;
        this.account_balance = account_balance;
        this.cust_password = cust_password;
    }

    // abstract method
    public abstract String showAccount();

    // withdraw method
    public void withdrawMoney(int amount) {
        try {
            if (account_balance >= amount) {
                account_balance -= amount;
            } else {
                // throws custom exception
                throw new MyException();
            }
        } catch (MyException e) {
            System.out.println("insufficient balance");
        }
    }
}

class SavingsAccount extends Account {
    float intrest_rate;

    // default constructor
    public SavingsAccount() {
        intrest_rate = 0.003f;
    }

    // parameterized constructor
    public SavingsAccount(int account_num, String cust_name, int account_balance, String cust_password, float rate) {
        super(account_num, cust_name, account_balance, cust_password);
        if (rate > 0.015 || rate < 0.003) {
            intrest_rate = 0.003f;
        } else {
            intrest_rate = rate;
        }
    }

    public void applyIntrest() {
        if (account_balance >= 500) {
            account_balance += (account_balance * intrest_rate);
        }
    }

    // abstrct method from parent class
    public String showAccount() {
        String temp = "acc_num: " + account_num + ", name: " + cust_name + ", balance: " + account_balance;
        return temp;
    }
}

public class MyBank {
    public static void main(String st[]) {
        Scanner scan = new Scanner(System.in);
        int a, b;
        float c;
        String d, e;

        System.out.println("enter acc_num, name, balance, password, rate");
        a = scan.nextInt();
        d = scan.next();
        b = scan.nextInt();
        e = scan.next();
        c = scan.nextFloat();
        // read values, use Scanner class
        SavingsAccount s1 = new SavingsAccount(a, d, b, e, c);

        // details of account
        System.out.println(s1.showAccount());

        // withdraw 100 from account
        s1.withdrawMoney(100);

        // call applyintrest on account
        s1.applyIntrest();

        // display the balance
        System.out.println(s1.showAccount());

        // to show exception works
        s1.withdrawMoney(800);
        System.out.println(s1.showAccount());

        System.out.println("program terminated successfully");
    }
}
