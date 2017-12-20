package l04;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class App {
    public static void main(String[] args) {
        Random random = new Random();
        List<Integer[]> list = new ArrayList<>();
        while (true) {
            for (int i = 0; i < 10; i++) {
                list.add(new Integer[500]);
                if (random.nextBoolean()) {
                    list.remove(random.nextInt(list.size()));
                }
            }
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
        }
    }
}

/*
java -verbose:gc -XX:+UseSerialGC    -XX:+PrintGCTimeStamps -XX:+PrintGCDateStamps -Xloggc:SerialGC.log -Xms1500m -Xmx1500m  -jar target/L04-1.0-SNAPSHOT.jar

java -verbose:gc -XX:+UseParallelGC  -XX:+PrintGCTimeStamps -XX:+PrintGCDateStamps -Xloggc:ParallelGC.log -Xms1500m -Xmx1500m  -jar target/L04-1.0-SNAPSHOT.jar

java -verbose:gc -XX:+UseParNewGC    -XX:+PrintGCTimeStamps -XX:+PrintGCDateStamps -Xloggc:ParNewGC.log -Xms1500m -Xmx1500m  -jar target/L04-1.0-SNAPSHOT.jar

java -verbose:gc -XX:+UseG1GC        -XX:+PrintGCTimeStamps -XX:+PrintGCDateStamps -Xloggc:G1GC.log -Xms1500m -Xmx1500m  -jar target/L04-1.0-SNAPSHOT.jar


Результат обработки логов:
---------------------------------

G1GC
[16:16]: 0.4059362
[16:17]: 0.9766377
[16:18]: 0.3349364
[16:19]: 1.2107481
[16:20]: 3.2138809
[16:21]: 1.620251
[16:22]: 6.7817123
[16:23]: 12.7618063
Total: 27.3059089

ParallelGC
[16:01]: 0.2345259
[16:02]: 0.4438332
[16:03]: 0.5259364
[16:04]: 0.5292354
[16:05]: 0.6187218
[16:06]: 4.4280492
[16:07]: 20.9968867
Total: 27.7771886

ParNewGC
[16:08]: 0.1257852
[16:09]: 0.1887278
[16:10]: 0.1974144
[16:11]: 0.5709091
[16:12]: 0.7190586
[16:13]: 1.7040784
[16:14]: 11.4238248
[16:15]: 25.4836721
Total: 40.4134704

SerialGC
[15:52]: 0.197957
[15:53]: 0.2915809
[15:54]: 0.30458
[15:55]: 0.5708216
[15:56]: 0.6423111
[15:57]: 0.7908907
[15:58]: 4.0285227
[15:59]: 34.9548546
Total: 41.7815186


Выводы:
----------------------------------
Все типы GC в последнюю минуту проявляют максимум активности, что логично. Для данной программы выгоднее всего оказался G1,
так как меньше всего потрачено на сборку мусора суммарно.

*/

