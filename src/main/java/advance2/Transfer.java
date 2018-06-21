package advance2;


import advance1.threads.ThreadReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

public class Transfer {

    private static LongAdder SUCCESTRANSACTION = new LongAdder();
    private static AtomicInteger atomicInteger = new AtomicInteger(0);
    private Logger logger = LoggerFactory.getLogger(Transfer.class);


    public void transfer(Account from, Account to, long amount, int transactionCounter) throws InterruptedException {

        if (from.equals(to)) {
            return;
        }
        if (from.getBalance() < amount) {
            logger.info("Insufficient funds");
            return;
        }
        Account acc1 = more(from, to);
        Account acc2 = less(from, to);

        logger.info("Try to lock account " + " id" + acc1.getId());
        if (acc1.getReentrantLock().tryLock(50, TimeUnit.MILLISECONDS)) {
            logger.info("Try to lock account " + " id" + acc2.getId() + "\n");
            if (acc2.getReentrantLock().tryLock(50, TimeUnit.MILLISECONDS)) {
                try {
                    if (getAtomicInteger().intValue() < transactionCounter) {
                        atomicInteger.incrementAndGet();
                        logger.info("Both accounts are locked " + " id" + acc1.getId() + " " + " id" + acc2.getId());
                        from.withdraw(amount);
                        to.deposit(amount);
                        logger.info("Transfer success" + "\n");
                    }
                    return;
                } finally {
                    acc1.getReentrantLock().unlock();
                    acc2.getReentrantLock().unlock();
                    logger.info("Both accounts are unlocked" + " id" + acc1.getId() + " " + " id" + acc2.getId() + "\n");
                }

            }
        }
    }

    public Account more(Account account, Account account2) {
        return account.getId() < account2.getId() ? account : account2;
    }

    public Account less(Account account, Account account2) {
        return account.getId() < account2.getId() ? account2 : account;
    }

    public static LongAdder getSUCCESSTRANSACTION() {
        return SUCCESTRANSACTION;
    }


    public void incrementSuccessTransfer() {
        SUCCESTRANSACTION.increment();
    }

    public static AtomicInteger getAtomicInteger() {
        return atomicInteger;
    }
}
