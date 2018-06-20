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
        if (from.getBalance() < amount){
            return;
        }
        Account acc1 = more(from, to);
        Account acc2 = less(from, to);

        if (acc1.getReentrantLock().tryLock(50, TimeUnit.MILLISECONDS)) {
            if (acc2.getReentrantLock().tryLock(50, TimeUnit.MILLISECONDS)) {
                try {
                    if (getAtomicInteger().intValue() < transactionCounter) {
                        atomicInteger.incrementAndGet();
                        System.out.println(amount + " amount");
                        from.withdraw(amount);
                        System.out.println(from.getId() + " from");
                        to.deposit(amount);
                        System.out.println(to.getId() + " get");
                        System.out.println(getAtomicInteger() + " successTransfers");
                    }
                    return;
                } finally {
                    acc1.getReentrantLock().unlock();
                    acc2.getReentrantLock().unlock();
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
