package l16.messagesystem;

public class WorkerProcessesRunner {
    private final String  DBSERVICE_JAR = "/home/user/projects/otus-java-2017-11-anton-okolelov/L16/l16dbservice/target/l16-0.0.1-SNAPSHOT.jar";
    private final String  FRONTEND_JAR = "/home/user/projects/otus-java-2017-11-anton-okolelov/L16/l16frontend/target/l16-frontend-0.0.1-SNAPSHOT.jar";
    private final ProcessRunner processRunner = new ProcessRunner();

    public void runDbService() {
        processRunner.run("java -jar " + DBSERVICE_JAR);
    }

    public void runFrontend() {
        processRunner.run("java -jar " + FRONTEND_JAR);
    }
}
