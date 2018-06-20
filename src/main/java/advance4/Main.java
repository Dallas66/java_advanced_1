package advance4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String example = "";
        String filepath = "./src/main/resources/file.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            example = reader.readLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        RegexpFormatter.formatMobileNumber(example);

    }
}
