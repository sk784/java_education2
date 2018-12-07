import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Car implements Runnable {
    private static int CARS_COUNT;

    static {
        CARS_COUNT = 0;
    }

    private Race race;
    private int speed;
    private String name;
    private CyclicBarrier cb;

    private static AtomicInteger ai = new AtomicInteger(0);

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed, CyclicBarrier cb) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
        this.cb = cb;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int) (Math.random() * 800));
            System.out.println(this.name + " готов");
            cb.await();
            cb.await();
            for (int i = 0; i < race.getStages().size(); i++) {
                race.getStages().get(i).go(this);
            }
            switch (ai.incrementAndGet()) {
                case 1:
                   System.out.println(name + " - занял ПЕРВОЕ место!");
                   break;
                case 2:
                    System.out.println(name + " - занял ВТОРОЕ место!");
                    break;
                case 3:
                    System.out.println(name + " - занял ТРЕТЬЕ место!");
                    break;
                case 4:
                    System.out.println(name + " - проиграл гонку!");
                    break;
            }
            cb.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}




