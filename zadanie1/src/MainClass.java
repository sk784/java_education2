import java.util.ArrayList;
import java.util.Arrays;


public class MainClass {

    public static void main(String[] args) {
        //Задача 1
        System.out.println("Задание1");
        String arr1[] = {"A", "B", "C", "D"};
        Integer arr2[] = {32, 22, 35, 456};
        changeElementsOfMassive(arr1, 0, 3);
        changeElementsOfMassive(arr2, 1, 2);

        //Задача 2
        System.out.println("Задание2");
        String[] arrayOfStrings = {"A", "B", "C", "D"};
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(arrayOfStrings));
        System.out.println("Исходный массив строк" + Arrays.toString(arrayOfStrings));
        System.out.println("Массив строк, преобразованный в arrayList " + arrayList + "\n------------------");


        //Задача 3
        System.out.println("Задание3");
        Box<Orange> o1 = new Box<>();
        Box<Orange> o2 = new Box<>();
        Box<Apple> a3 = new Box<>();
        Box<Apple> a4 = new Box<>();

        System.out.println("Добавляем фрукты в коробки: ");
        o1.addFruit(new Orange(), 4);
        o2.addFruit(new Orange(), 2);
        a3.addFruit(new Apple(), 6);
        a4.addFruit(new Apple(), 4);
        System.out.println("Вес первой коробки (апельсины): " + o1.getWeight());
        System.out.println("Вес второй коробки (апельсины): " + o2.getWeight());
        System.out.println("Вес третьей коробки (яблоки): " + a3.getWeight());
        System.out.println("Вес четвертой коробки (яблоки): " + a4.getWeight() + "\n-------------------");

        System.out.println("Сравниваем вес коробок: ");
        System.out.println("Равен ли вес первой коробки(апельсины)весу третьей коробки (яблоки): " + o1.compare(a3));
        System.out.println("Равен ли вес второй коробки(апельсины)весу четвертой коробки (яблоки): " + o2.compare(a4) + "\n-------------------");

        System.out.println("Пересыпаем фрукты из второй коробки в первую и из четвертой в третью: ");
        o2.move(o1);
        a4.move(a3);

        System.out.println("Вес первой коробки (апельсины): " + o1.getWeight());
        System.out.println("Вес второй коробки (апельсины): " + o2.getWeight());
        System.out.println("Вес третьей коробки (яблоки): " + a3.getWeight());
        System.out.println("Вес четвертой коробки (яблоки): " + a4.getWeight());

    }

    public static void changeElementsOfMassive(Object[] arr, int x, int y) {
        System.out.println("Исходный массив: " + Arrays.toString(arr));
        Object o = arr[x];
        arr[x] = arr[y];
        arr[y] = o;
        System.out.println("Измененный массив: " + Arrays.toString(arr) + "\n-------------------");
    }
}


