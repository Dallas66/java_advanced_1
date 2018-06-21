package advance2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class Main {

    private static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {


        FileCreat fileCreat = new FileCreat();
        fileCreat.createFiles();

        PojoReadAndWriter pojoReadAndWriter = new PojoReadAndWriter();
        CopyOnWriteArrayList<Account> arrayList = pojoReadAndWriter.reader();

        long before = 0;
        long after = 0;
        for (Account account : arrayList) {
//            System.out.println(account.getBalance() + " balance " + account.getId() + " id");
            before += account.getBalance();
        }
        logger.info(before + " the sum of all balances before" + "\n");
//        System.out.println(before + " sum all acc before");


        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            executorService.execute(new Operations(arrayList));
        }

        try {
            executorService.awaitTermination(5, TimeUnit.SECONDS);
            executorService.shutdown();
        } catch (InterruptedException e) {
            logger.error("WoW wOw we caught a bug " + e);
        }


        for (Account account : arrayList) {
//            System.out.println(account.getBalance() + " balance " + account.getId() + " id");
            after += account.getBalance();
        }
        logger.info(after + " the sum of all balances after" + "\n");
//        System.out.println(after + " sum all account after");


        for (Account account : arrayList) {
            pojoReadAndWriter.writer(account);
        }
        logger.info("All file overwritten" + "\n");


    }

}

