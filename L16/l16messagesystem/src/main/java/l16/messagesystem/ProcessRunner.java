package l16.messagesystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;

public class ProcessRunner {
    static Logger logger = Logger.getLogger(ProcessRunner.class.getName());

    public void run(String command) {
        logger.info(command);
        ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
        processBuilder.redirectErrorStream(true);
        try {
            Process process = processBuilder.start();
            String firstLine = new BufferedReader(new InputStreamReader(process.getInputStream())).readLine();
            logger.info(firstLine);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
