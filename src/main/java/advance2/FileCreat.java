package advance2;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class FileCreat {

    private Random random = new Random();

    public void createFiles() {
        for (int i = 1; i <= 10; i++) {
            Account account = new Account(random.nextInt(7000)+1000,i);
            File file = new File("./src/main/resources/files"+"/user"+i+".bin");
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try (ObjectOutputStream oos =
                         new ObjectOutputStream(new FileOutputStream("./src/main/resources/files/"+"user"+i+".bin"))) {

              oos.writeObject(account);
//                System.out.println("Done fileCreate" + " " + file.getName());

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
