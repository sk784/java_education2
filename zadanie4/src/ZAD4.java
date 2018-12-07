public class ZAD4 {
    static final Object obj = new Object();
    static char ch = 'A';

    public static void main(String[] args) {
        new Thread(new A()).start();
        new Thread(new B()).start();
        new Thread(new C()).start();
    }


    static class A implements Runnable {
        @Override
        public void run() {
            synchronized (obj) {
                try {
                    for (int i = 0; i < 5; i++) {
                        while (ch != 'A')
                            obj.wait();
                        System.out.print("A");
                        ch = 'B';
                        obj.notify();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class B implements Runnable {
        @Override
        public void run() {
            synchronized (obj) {
                try {
                    for (int i = 0; i < 5; i++) {
                        while (ch != 'B')
                            obj.wait();
                        System.out.print("B");
                        ch = 'C';
                        obj.notify();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class C implements Runnable {
        @Override
        public void run() {
            synchronized (obj) {
                try {
                    for (int i = 0; i < 5; i++) {
                        while (ch != 'C')
                            obj.wait();
                        System.out.print("C");
                        ch = 'A';
                        obj.notify();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}


