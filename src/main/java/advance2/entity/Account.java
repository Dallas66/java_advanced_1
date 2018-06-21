package advance2.entity;

import java.io.Serializable;
import java.util.Objects;
import java.util.concurrent.locks.ReentrantLock;

public class Account implements Serializable {
    private long balance;
    private int id;

     private  ReentrantLock reentrantLock;

    public Account(long balance,int id) {
        this.id = id;
        this.reentrantLock = new ReentrantLock();
        this.balance = balance;
    }

    public void withdraw(long amount) {
        balance -= amount;
    }

    public void deposit(long amount) {
        balance += amount;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
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
                "balance=" + balance +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id == account.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
