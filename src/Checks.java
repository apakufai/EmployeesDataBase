public class Checks {

    public static boolean correctName(String input) {
        if (input.equals(input.replaceAll("[^-А-ЯЁа-яё]", ""))) {
            return true;
        } else {
            System.out.println("Некорректный ввод!");
            return false;
        }
    } // Метод для проверки правильного ввода строк

    public static boolean correctNumber(String input) {
        if (input.equals(input.replaceAll("[^0-9]", ""))) {
            return true;
        } else {
            System.out.println("Некорректный ввод!");
            return false;
        }
    } // Метод для проверки правильного ввода чисел

    public static boolean correctDate(String input) {
        if (input.equals(input.replaceAll("[^0-9.]", ""))) {
            return true;
        } else {
            System.out.println("Некорректный ввод!");
            return false;
        }
    } // Метод для проверки правильного ввода даты

    public static boolean correctPhone(String input) {
        if (input.equals(input.replaceAll("[^0-9-+() ]", ""))) {
            return true;
        } else {
            System.out.println("Некорректный ввод!");
            return false;
        }
    } // Метод для проверки правильного ввода номера телефона

    public static String correctInputCase(String str) {

        String firstLetter = String.valueOf(new StringBuffer(str)
                .delete(1, str.length())).toUpperCase(); // Первая буква

        str = String.valueOf(new StringBuffer(str.toLowerCase())
                .replace(0, 1, firstLetter)); // Слияние первой буквы и конца строки

        return str;
    } // Метод, делающий первую букву заглавной, а остальные - строчными

}