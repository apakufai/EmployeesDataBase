import java.io.*;

// Здесь хранятся логины и пароли людей, имеющих доступ к управлению приложением.
public class Account {

    private static String currentAccount = "null";
    private static int currentPasswordHash = 0;

    public static String getCurrentAccount() {
        return currentAccount;
    }

    public static int getCurrentPasswordHash() {
        return currentPasswordHash;
    }

    public static void checkAccounts(String inputAccount) {

        try {

            BufferedReader reader = new BufferedReader(new FileReader("Accounts.txt")); // Объявление буферизированного считывания
            String line = reader.readLine();

            while (line != null) {

                if (line.startsWith("А") && line.substring(3).equals(inputAccount)) {
                    currentAccount = line.substring(3);
                    currentPasswordHash = reader.readLine().substring(3).hashCode();
                }
                line = reader.readLine();
            }
            reader.close(); // Закрытие потока чтения
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (currentAccount.equals("null")) {
            System.out.println("Такого логина нет в базе!");
        }

    } // Проверка наличия аккаунта в файле "Accounts.txt"

}