package advance2;

import java.util.concurrent.TimeUnit;

public class Transfer extends Thread {
    private  Account from;
    private  Account to;
    private Long amount;

    public Transfer(Account from, Account to, Long amount) {
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    @Override
    public void run() {

    }

     void transfer() throws InterruptedException {
        if (from.getBalance() < amount)
            throw new IllegalArgumentException("Insufficient founds");
        if (qwemore(from, to).getReentrantLock().tryLock(1, TimeUnit.SECONDS)) {
            System.out.println(qwemore(from, to));
            try {
                if (qwe(from, to).getReentrantLock().tryLock(1, TimeUnit.SECONDS)) {
                    System.out.println(qwe(from, to));
                    try {
                        from.withdraw(amount);
                        to.deposit(amount);
                    } finally {
                        to.getReentrantLock().unlock();
                    }
                }
            } finally {
                from.getReentrantLock().unlock();
            }
        }

    }

    public static Account qwemore(Account account, Account account2) {
        return account.getId() < account2.getId() ? account : account2;
    }

    public static Account qwe(Account account, Account account2) {
        return account.getId() < account2.getId() ? account2 : account;
    }

    public Account getFrom() {
        return from;
    }

    public void setFrom(Account from) {
        this.from = from;
    }

    public Account getTo() {
        return to;
    }

    public void setTo(Account to) {
        this.to = to;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

}
