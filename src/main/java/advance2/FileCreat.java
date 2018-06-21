package advance2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class FileCreat {

    private Random random = new Random();

    private Logger logger = LoggerFactory.getLogger(FileCreat.class);

    public void createFiles() {
        for (int i = 1; i <= 10; i++) {
            Account account = new Account(random.nextInt(7000) + 1000, i);
            File file = new File("./src/main/resources/files" + "/user" + i + ".bin");
            try {
                file.createNewFile();
            } catch (IOException e) {
                logger.error("WoW wOw we caught a bug Invalid file path " + e);
            }
            try (ObjectOutputStream oos =
                         new ObjectOutputStream(new FileOutputStream("./src/main/resources/files/" + "user" + i + ".bin"))) {

                oos.writeObject(account);
            } catch (Exception ex) {
                logger.error("WoW wOw we caught a bug " + ex + " possibly file not found");
            }
        }
    }
}
