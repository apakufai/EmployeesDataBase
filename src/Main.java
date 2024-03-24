/*
  TODO: Создать приложение «Система учета сотрудников».
        Основная задача проекта: хранить информацию о сотрудниках организации и
        структуре организации.
        Необходимо сохранять следующую информацию: ФИО сотрудника, дату рож-
        дения, пол, контактный телефон, должность, название отдела в котором работает
        сотрудник, информацию о непосредственном начальнике (если такой есть), дату
        приема на работу, зарплату.
        Приложение должно позволять: принимать на работу новых сотрудников,
        увольнять сотрудников, изменять информацию о сотрудниках. Приложение долж-
        но предоставить функциональность по поиску сотрудников внутри организации
        по таким параметрам: ФИО, должности, названию отдела, ФИО начальника. При-
        ложение должно иметь возможность создавать следующие отчеты: структура ор-
        ганизации (информация об отделах, ФИО начальников отделов), средняя зарплата
        по организации и по отделам, ТОП-10 самых дорогих сотрудников по зарплате,
        ТОП-10 самых преданных сотрудников по количеству лет работы в организации.
        Приложение должно сохранять и загружать информацию об организации из
        файлов.
        Интерфейс приложения должен позволять выводить результат работы отчета
        в консоль или файл. Необходимо предусмотреть возможность входа по логину и
        паролю.
    */ // ЗАДАНИЕ

import java.util.Scanner;

public class Main {

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Для выхода из программы введите \"Выход\"");

        String login;

        // Блок проверки наличия аккаунта в базе
        do {
            System.out.print("\nВведите логин: ");
            login = sc.nextLine();

            if (login.equalsIgnoreCase("выход")) {
                System.out.println("\nВыход из программы");
                System.exit(0);
            }

            if (login.equals("")) {
                System.out.println("Строка ввода пустая!");
                continue;
            }

            Account.checkAccounts(login);

        } while (Account.getCurrentAccount().equals("null"));

        System.out.println("\nВыбрана учётная запись с логином " + login);

        String password;
        boolean correctPassword = false;

        // Блок проверки пароля к выбранному аккаунту
        int numberOfPasswordAttempts = 3; // Счётчик попыток ввода пароля
        do {
            System.out.print("\nВведите пароль: ");
            password = sc.nextLine();

            if (password.equalsIgnoreCase("выход")) {
                System.out.println("\nВыход из программы");
                System.exit(0);
            }

            if (password.equals("")) {
                System.out.println("Строка ввода пустая!");
                continue;
            }

            if (!(password.hashCode() == Account.getCurrentPasswordHash())) {
                System.out.print("Неверный пароль!");
                numberOfPasswordAttempts--;

                if (numberOfPasswordAttempts > 0) {
                    System.out.print(" Осталось попыток: " + numberOfPasswordAttempts + "\n");
                    continue;
                }
                System.out.println(" Выход из программы");
                System.exit(0);

            }

            System.out.println("Пароль верный. Добро пожаловать, " + login + "!");
            correctPassword = true;

        } while (!correctPassword);

        // Блок работы с программой
        Employee.addEmployees(); // Добавление сотрудников из файла "Employees (reserve).txt"
        System.out.println("\nДля добавления нового сотрудника введите \"Новый\"");
        System.out.println("Для поиска сотрудника введите \"Найти\"");
        System.out.println("Для изменения данных сотрудника введите \"Изменить\"");
        System.out.println("Для удаления сотрудника из базы данных введите \"Удалить\"");
        System.out.println("Для сохранения всех изменённых данных введите \"Сохранить\"");
        System.out.println("Для вывода информации о компании введите \"Фирма\"");
        System.out.println("Для вывода основных данных всех сотрудников введите \"Вывод простой\"");
        System.out.println("Для вывода полных данных всех сотрудников введите \"Вывод полный\"");
        System.out.println("Для выхода из программы введите \"Выход\"");

        String operation;
        do {
            System.out.print("\nВведите операцию: ");
            operation = sc.nextLine().toLowerCase(); // Ввод нужной операции

            switch (operation) {

                case ("новый") -> Employee.newEmployee(); // Добавление нового сотрудника

                case ("найти") -> Employee.findAndPrintEmployee(); // Поиск сотрудника по определённым данным

                case ("изменить") -> Employee.changeEmployee(); // Изменение данных сотрудника по id

                case ("удалить") -> Employee.removeEmployee(); // Удаление сотрудника по id

                case ("сохранить") -> Employee.saveChangesInFile(); // Сохранение изменений и перезапись файла

                case ("фирма") -> Employee.printCompanyReport(); // Вывод определённых данных о компании

                case ("вывод простой") -> {
                    System.out.println();
                    for (Employee e : Employee.employees) {
                        e.printInfoSimple();
                    }
                } // Вывод поисковой информации о сотрудниках

                case ("вывод полный") -> {
                    for (Employee e : Employee.employees) {
                        System.out.println();
                        e.printInfoAll();
                    }
                } // Вывод полной информации о сотрудниках

                case ("выход") -> {
                    System.out.println("\nВыход из программы");
                } // Выход из программы

                default -> System.out.println("\nНекорректная операция! Попробуйте снова");
            }

        } while (!operation.equals("выход"));

        sc.close(); // Закрытие сканера

    }
}