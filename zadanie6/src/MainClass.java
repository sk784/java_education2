import java.util.Arrays;

public class MainClass {
    public static void main(String[] args) {

        System.out.println(Arrays.toString(splitArrayAfter4(new int[]{1,2,4,2,4,1,7})));
        System.out.println(checkArrayFor1and4(new int[]{1,4,1,4}));
    }



    public static int[] splitArrayAfter4(int[] arr) throws RuntimeException {
        if (arr.length == 0) {
            throw new NullPointerException();
        }

        int newIndex = -1;
        for (int i = 0; i < arr.length; i++)
            if (arr[i] == 4)
                newIndex = i + 1;

        if (newIndex != -1)
            return Arrays.copyOfRange(arr, newIndex, arr.length);
        else
            throw new RuntimeException();
    }

    public static boolean checkArrayFor1and4(int[] arr) {
        int c1 = 0, c4 = 0;
        for (int x : arr) {
            if (x == 1)
                c1++;
            else if (x == 4)
                c4++;
            else return false;
        }

        return (c1 > 0 && c4 > 0);
    }

}
