package advance2;

import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        FileCreat fileCreat = new FileCreat();
        fileCreat.createFiles();

        PojoReadAndWriter pojoReadAndWriter = new PojoReadAndWriter();
        CopyOnWriteArrayList<Account> arrayList = pojoReadAndWriter.reader();

        long before = 0;
        long after = 0;
        for (Account account : arrayList) {
            System.out.println(account.getBalance() + " balance " + account.getId() + " id");
            before += account.getBalance();
        }
        System.out.println(before + " sum all acc before");


        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            executorService.execute(new Operations(arrayList));
        }

        try {
            executorService.awaitTermination(5, TimeUnit.SECONDS);
            executorService.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        for (Account account : arrayList) {
            System.out.println(account.getBalance() + " balance " + account.getId() + " id");
            after += account.getBalance();
        }
        System.out.println(after + " sum all account after");


        for (Account account : arrayList) {
            pojoReadAndWriter.writer(account);
        }


    }

}

