package advance2;

import java.util.concurrent.locks.ReentrantLock;

public class Account {
    private Long balance;
    private int id;

    private ReentrantLock reentrantLock;

    public Account(Long balance,int id) {
        this.id = id;
        this.reentrantLock = new ReentrantLock();
        this.balance = balance;
    }

    public void withdraw(Long amount) {
        balance -= amount;
    }

    public void deposit(Long amount) {
        balance += amount;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public ReentrantLock getReentrantLock() {
        return reentrantLock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                '}';
    }
}
