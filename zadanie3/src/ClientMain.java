
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientMain {
    public static void main(String[] args) throws IOException{
        Socket socket = null;

        Player x = new Player(1, "Bob");
        ObjectOutputStream oos;

        try {
            socket = new Socket("localhost", 8189);

            final Scanner in = new Scanner(socket.getInputStream());
            final PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            final Scanner console = new Scanner(System.in);

            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        String str = in.nextLine();
                        if(str.equals("/end")) {
                            out.println("/end");
                            break;
                        }
                        System.out.println("Server " + str);
                    }
                }
            });
            t1.start();

            Thread t2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        System.out.println("Введите сообщение!");
                        String str = console.nextLine();
                        System.out.println("Сообщение отправлено");
                        out.println(str);
                    }

                }
            });
            t2.setDaemon(true);
            t2.start();

            try {
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            oos = new ObjectOutputStream(new FileOutputStream("player.plr"));
            oos.writeObject(x);
            oos.close();
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

class Player implements Serializable  {
    int id;
    String name;

    public Player(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void info() {
        System.out.println(id + " " + name);
    }

}











