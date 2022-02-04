public class App {

    public static void main(String[] args) {
        Countdown countdown = new Countdown();

        CountdownThread t1 = new CountdownThread(countdown);
        t1.setName("Thread 1");
        CountdownThread t2 = new CountdownThread(countdown);
        t2.setName("Thread 2");
        CountdownThread t3 = new CountdownThread(countdown);
        t3.setName("Thread 3");

        t1.start();
        t2.start();
        t3.start();
    }
}

class Countdown {
    private int i;
    public void doCountdown() {
        String color;

        switch(Thread.currentThread().getName()) {
            case "Thread 1":
                color = Color.ANSI_CYAN;
                break;
            case "Thread 2":
                color = Color.ANSI_PURPLE;
                break;
            case "Thread 3":
                color = Color.ANSI_BLUE;
            default:
                color = Color.ANSI_GREEN;
        }

        synchronized (this) {
            for(i=10; i > 0; i--) {
                System.out.println(color + Thread.currentThread().getName() + ": i = " + i);
            }
        }
    }
}

class CountdownThread extends Thread {
    private Countdown threadCountdown;

    public CountdownThread(Countdown countdown) {
        threadCountdown = countdown;
    }

    @Override
    public void run() {
        threadCountdown.doCountdown();
    }
}

