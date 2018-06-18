package advance1;

import advance1.customToolse.CustomQueue;
import advance1.threads.ThreadReader;
import advance1.threads.ThreadWriter;

public class Main {

    public static void main(String[] args) {

        CustomQueue customQueue = new CustomQueue();


        ThreadWriter threadWriter = new ThreadWriter(customQueue);
        ThreadWriter threadWriter1 = new ThreadWriter(customQueue);

        ThreadReader threadReader = new ThreadReader(customQueue);
        ThreadReader threadReader1 = new ThreadReader(customQueue);
        ThreadReader threadReader2 = new ThreadReader(customQueue);

        threadWriter.start();
        threadWriter1.start();

        threadReader.start();
        threadReader1.start();
        threadReader2.start();


    }
}

