import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
      // byteArray();
      // unionFiles();
        readFile();
    }

    static void byteArray() {
        try (FileInputStream in = new FileInputStream("1.txt"); ByteArrayOutputStream b = new ByteArrayOutputStream()) {
            byte[] arr = new byte[512];

            int x;

            while ((x = in.read(arr)) != -1) {
                b.write(arr, 0, x);
            }
            System.out.println(b);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void unionFiles(){
        ArrayList<InputStream> a = new ArrayList<>();

        try {
            a.add(new FileInputStream("2-1.txt"));
            a.add(new FileInputStream("2-2.txt"));
            a.add(new FileInputStream("2-3.txt"));
            a.add(new FileInputStream("2-4.txt"));
            a.add(new FileInputStream("2-5.txt"));

            FileOutputStream o = new FileOutputStream("2-6.txt");

            SequenceInputStream in = new SequenceInputStream(Collections.enumeration(a));

        int x;
        try {
        while ((x = in.read()) != -1) {
            o.write(x);
            System.out.print((char)x);
        }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    static void readFile(){
        try (RandomAccessFile raf = new RandomAccessFile("3.txt", "r");
             BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            long fileLength = raf.length();
            long pageLength = 1800;
            long pagesCount = fileLength/pageLength;
            byte[] b = new byte[1800];
            System.out.println("Объем файла: " + fileLength + "\nДлина страницы: " + pageLength + " \nКоличество страниц: " + pagesCount);
            while (true) {
                System.out.println("\nВведите номер страницы между 0 и " + pagesCount);
                long p = Long.parseLong(br.readLine());
                if (p <= pagesCount && p >= 0) {
                    raf.seek(p * pageLength);
                    raf.read(b, 0, b.length);
                    for (byte bytes : b)
                        System.out.print((char) bytes);
                } else {
                    System.out.println("Нет такой страницы! " + p);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    }






