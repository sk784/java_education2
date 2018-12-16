import java.util.*;

public class Massive {
        public static void main(String[] args) {

            //С помощью переменной i задаем числа внутри массива
            int i = 1;

            int[][] arr = new int[7][7];

            //Заполняем периметр массива
            for (int y = 0; y < 7; y++) {
                arr[0][y] = i;
                i++;
            }
            for (int x = 1; x < 7; x++) {
                arr[x][6] = i;
                i++;
            }
            for (int y = 5; y >= 0; y--) {
                arr[6][y] = i;
                i++;
            }
            for (int x = 5; x > 0; x--) {
                arr[x][0] = i;
                i++;
            }

           //координаты следующей ячейки
            int c = 1;
            int d = 1;

            while (i < 49) {

                //Движемся вправо
                while (arr[c][d + 1] == 0) {
                    arr[c][d] = i;
                    i++;
                    d++;
                }

                //Движемся вниз
                while (arr[c + 1][d] == 0) {
                    arr[c][d] = i;
                    i++;
                    c++;
                }

                //Движемся влево
                while (arr[c][d - 1] == 0) {
                    arr[c][d] = i;
                    i++;
                    d--;
                }

                //Движемся вверх
                while (arr[c - 1][d] == 0) {
                    arr[c][d] = i;
                    i++;
                    c--;
                }
            }

         // Заполняем центральную ячейку
            for (int x = 0; x < 7; x++) {
                for (int y = 0; y < 7; y++) {
                    if (arr[x][y] == 0) {
                        arr[x][y] = i;
                    }
                }
            }

            //выводим массив на экран
            for(int x = 0; x < 7; x++){
                for(int y = 0; y < 7; y++){
                    System.out.print(arr[x][y]+"\t");
                }
                System.out.println();
            }

        }
}
