package advance2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class PojoReadAndWriter {

    private Logger logger = LoggerFactory.getLogger(PojoReadAndWriter.class);
    File folder = new File("./src/main/resources/files");
    private File[] fileMassive;

    public void writer(Account account) {

        for (File file : fileMassive) {
            try (ObjectOutputStream oos =
                         new ObjectOutputStream(new FileOutputStream(file))) {
                oos.writeObject(account);
            } catch (IOException e) {
                logger.error("WoW wOw we caught a bug " + e + " possibly file not found");
            }
        }
    }


    public CopyOnWriteArrayList<Account> reader() {
        CopyOnWriteArrayList<Account> accounts = new CopyOnWriteArrayList<>();
        fileMassive = folder.listFiles();
        for (File file : fileMassive) {
            try (ObjectInputStream ois
                         = new ObjectInputStream(new FileInputStream(file))) {

                Account account = (Account) ois.readObject();
                accounts.add(account);

            } catch (Exception ex) {
                logger.error("WoW wOw we caught a bug " + ex + " possibly file not found");
            }

        }
        logger.info("View all files" + "\n");
        return accounts;
    }
}

