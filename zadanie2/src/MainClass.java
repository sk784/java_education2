import java.sql.*;
import java.util.Scanner;

public class MainClass {
    private static Connection connection;
    private static Statement stmt;
    private static PreparedStatement prstmt;

    public static void main(String[] args) {

        try {
            connect();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            stmt.execute("CREATE TABLE IF NOT EXISTS products (" +
                    "id INTEGER," +
                    "prodid INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "title TEXT," +
                    "cost INTEGER)");
            stmt.execute("DELETE FROM products");

            prstmt = connection.prepareStatement("INSERT INTO products (id, title, cost)" +
                    " VALUES (?,?,?)");
           connection.setAutoCommit(false);
            for (int i = 1; i < 10000; i++) {
                prstmt.setInt(1, i );
                prstmt.setString(2, "product" + i);
                prstmt.setInt(3, i*10 );
                prstmt.addBatch();
            }
            prstmt.executeBatch();
            connection.setAutoCommit(false);
            connection.commit();

            System.out.println("Введите(например)цена product545, чтобы узнать стоимость товара по его названию;\n" +
                    "Введите (например)сменитьцену product10 10000, чтобы изменить цену товара по его названию;\n" +
                    "Введите (например)товарыпоцене 100 600, чтобы узнать, какие товары находятся в заданном вами диапазоне;\n" +
                    "Введите выход, чтобы выйти;\n");
            Scanner sc = new Scanner(System.in);
            while (true) {
                String str = sc.nextLine();
                System.out.println("Вы ввели: "+str);
                String arr[] = str.split(" ");
                if(str.startsWith("цена")){
                    getCostByTitle(arr[1]);
                }if(str.startsWith("сменитьцену")){
                    setCostByTitle(arr[1], Integer.parseInt(arr[2]));
                }if(str.startsWith("товарыпоцене")){
                   rangeProducts(Integer.parseInt(arr[1]), Integer.parseInt(arr[2]));
                }if(str.equals("выход")){
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        disconnect();
    }

    public static void getCostByTitle(String title) {
        try {
            prstmt = connection.prepareStatement("SELECT cost FROM products WHERE title = ?");
            prstmt.setString(1, title);
            ResultSet rs = prstmt.executeQuery();
            boolean f = false;
            while (rs.next()){
                f = true;
                System.out.println(rs.getInt("cost"));
            }
            rs.close();
            prstmt.close();

            if(!f){
                System.out.println("Нет товара с таким именем!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void setCostByTitle(String title, int cost) {
        try {
            prstmt = connection.prepareStatement("UPDATE products SET cost=? WHERE title = ?");
            prstmt.setInt(1, cost);
            prstmt.setString(2, title);
            prstmt.executeUpdate();
            getCostByTitle(title);
            prstmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void rangeProducts(int min, int max){
        try {
            prstmt = connection.prepareStatement("SELECT * FROM Products WHERE Cost BETWEEN ? AND ?");
            prstmt.setInt(1, min);
            prstmt.setInt(2, max);
            ResultSet rs = prstmt.executeQuery();
            boolean f = false;
            while(rs.next()){
                f = true;
                System.out.println(rs.getInt("ID")+" "+rs.getInt("Prodid")+" "+rs.getString("Title")+" "+rs.getInt("Cost"));
            }
            rs.close();
            prstmt.close();
            if(!f){
                System.out.println("Товары в заданном диапазоне не найдены.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void connect() throws ClassNotFoundException, SQLException {

        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:mainDB.db");
        stmt = connection.createStatement();

    }

    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

