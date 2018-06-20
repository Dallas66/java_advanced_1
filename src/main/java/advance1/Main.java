package advance1;

import advance1.customToolse.CustomQueue;
import advance1.threads.ThreadReader;
import advance1.threads.ThreadWriter;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {

        CustomQueue customQueue = new CustomQueue();

        ExecutorService executorWriter = Executors.newCachedThreadPool();
        ExecutorService executorReader = Executors.newCachedThreadPool();

        for (int i = 0; i < 3; i++) {
            executorWriter.execute(new ThreadWriter(customQueue));
        }
        for (int i = 0; i < 6; i++) {
            executorReader.execute(new ThreadReader(customQueue));
        }

        executorWriter.shutdown();
        executorReader.shutdown();



    }
}

