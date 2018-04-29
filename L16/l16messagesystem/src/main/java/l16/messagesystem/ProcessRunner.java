package l16.messagesystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;

public class ProcessRunner {
    static Logger logger = Logger.getLogger(ProcessRunner.class.getName());

    public void run(String command) {
        new Thread(() -> {
            logger.info(command);
            ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
            processBuilder.redirectErrorStream(true);
            try {
                Process process = processBuilder.start();
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

                while (true) {
                    String line = reader.readLine();
                    if (line == null) {
                        Thread.sleep(1000);
                    }
                    logger.info(line);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
