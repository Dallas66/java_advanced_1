package advance2;

import java.util.concurrent.TimeUnit;

public class Operations {

    public static void main(String[] args) throws InterruptedException {
        Account account = new Account(1000L,1);
        Account account2 = new Account(2000L,2);
        Account account3 = new Account(2000L,15);

        System.out.println(account.getBalance());
        System.out.println(account2.getBalance());
        System.out.println(account3.getBalance());
        new Thread(() -> {
            try {
                transfer(account3, account2, 500L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        try {
            transfer(account2, account3, 300L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(account.getBalance());
        System.out.println(account2.getBalance());
        System.out.println(account3.getBalance());
    }


    static void transfer(Account from, Account to, Long amount) throws InterruptedException {
        if (from.getBalance() < amount)
            throw new IllegalArgumentException("Insufficient founds");
        if (qwemore(from,to).getReentrantLock().tryLock(1,TimeUnit.SECONDS)){
            System.out.println(qwemore(from,to));
            try {
                if(qwe(from,to).getReentrantLock().tryLock(1,TimeUnit.SECONDS)){
                    System.out.println(qwe(from,to));
                    try {
                        from.withdraw(amount);
                        to.deposit(amount);
                    }finally {
                        to.getReentrantLock().unlock();
                    }
                }
            }finally {
                from.getReentrantLock().unlock();
            }
        }
//        synchronized (from) {
//            synchronized (to) {
//                from.withdraw(amount);
//                to.deposit(amount);
//            }
//        }
    }
    public static Account qwemore(Account account , Account account2){
        return account.getId() < account2.getId() ? account : account2;
    }
    public static Account qwe(Account account , Account account2){
        return account.getId() < account2.getId() ? account2 : account;
    }
}