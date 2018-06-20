package advance2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class PojoReadAndWriter {

    File folder = new File("./src/main/resources/files");

    private File[] fileMassive;

    public void writer(Account account) {

        for (File file : fileMassive) {
            try (ObjectOutputStream oos =
                         new ObjectOutputStream(new FileOutputStream(file))) {
                oos.writeObject(account);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    public CopyOnWriteArrayList<Account> reader() {
        CopyOnWriteArrayList<Account> accounts = new CopyOnWriteArrayList<>();
        fileMassive = folder.listFiles();
        for (File file : fileMassive) {
//            System.out.println(file.getName() + " filereader");
            try (ObjectInputStream ois
                         = new ObjectInputStream(new FileInputStream(file))) {

                Account account = (Account) ois.readObject();
                accounts.add(account);

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
        return accounts;
    }
}

