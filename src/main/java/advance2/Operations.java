package advance2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Operations extends Thread {

    private Logger logger = LoggerFactory.getLogger(Operations.class);

    private final CopyOnWriteArrayList<Account> list;
    private Random random = new Random();

    public Operations(CopyOnWriteArrayList<Account> list) {
        this.list = list;
    }


    @Override
    public void run() {
        while (Transfer.getAtomicInteger().intValue() < 1000) {
            try {
                new Transfer().transfer(list.get(random.nextInt(list.size())),
                        list.get(random.nextInt(list.size())),
                        random.nextInt(100) + 50, 1000);
            } catch (InterruptedException e) {
                logger.error("Your thread suddenly is interrupted, either before or during the activity " + e);
            }
        }
    }

}
