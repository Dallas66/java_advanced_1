package advance2;

import java.util.concurrent.TimeUnit;

public class Operations {

    public static void main(String[] args) throws InterruptedException {
        Account account = new Account(1000L,1);
        Account account2 = new Account(2000L,2);
        Account account3 = new Account(2000L,15);

        Transfer transfer = new Transfer(account3, account2, 500L);
        Transfer transfer1 = new Transfer(account2, account3, 300L);

        System.out.println(account.getBalance());
        System.out.println(account2.getBalance());
        System.out.println(account3.getBalance());
        new Thread(() -> {
            try {
                transfer.transfer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        Thread.sleep(1);
        try {
            transfer1.transfer();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(account.getBalance());
        System.out.println(account2.getBalance());
        System.out.println(account3.getBalance());
    }

}