import java.io.*;
import java.util.List;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.UUID;


public class Employee {

    public static TreeSet<Employee> employees = new TreeSet<>(Comparators.mainComparator); // Сотрудники

    private String fullName;
    private String birthday;
    private Sex sex;
    private String phone;
    private Position position;
    private Department department;
    private String boss;
    private String employmentDate;
    private int salary;
    private final String id;

    // ГЕТТЕРЫ
    public String getFullName() {
        return fullName;
    }

    public String getBirthday() {
        return birthday;
    }

    public int getYearOfBirth() {
        String[] fullDate = birthday.split("\\.");
        return Integer.parseInt(fullDate[2]);
    } // Возвращение года рождения сотрудника в Integer для компаратора

    public int getMonthOfBirth() {
        String[] fullDate = birthday.split("\\.");
        return Integer.parseInt(fullDate[1]);
    } // Возвращение месяца рождения сотрудника в Integer для компаратора

    public int getDayOfBirth() {
        String[] fullDate = birthday.split("\\.");
        return Integer.parseInt(fullDate[0]);
    } // Возвращение дня рождения сотрудника в Integer для компаратора

    public String getSex() {
        return sex.getSex();
    }

    public Sex getSexENUM() {
        return sex;
    }

    public String getPhone() {
        return phone;
    }

    public String getPosition() {
        return position.getPosition();
    }

    public Position getPositionENUM() {
        return position;
    }

    public String getDepartment() {
        return department.getDepartment();
    }

    public Department getDepartmentENUM() {
        return department;
    }

    public String getBoss() {
        return boss;
    }

    public String getEmploymentDate() {
        return employmentDate;
    }

    public int getYearOfEmploymentDate() {
        String[] fullDate = employmentDate.split("\\.");
        return Integer.parseInt(fullDate[2]);
    }

    public int getMonthOfEmploymentDate() {
        String[] fullDate = employmentDate.split("\\.");
        return Integer.parseInt(fullDate[1]);
    }

    public int getDayOfEmploymentDate() {
        String[] fullDate = employmentDate.split("\\.");
        return Integer.parseInt(fullDate[0]);
    }

    public int getSalary() {
        return salary;
    }

    public String getId() {
        return id;
    }


    // Конструктор
    public Employee(String fullName, String birthday, Sex sex, String phone, Position position, Department department,
                    String boss, String employmentDate, int salary, String id) {
        this.fullName = fullName;
        this.birthday = birthday;
        this.sex = sex;
        this.phone = phone;
        this.position = position;
        this.department = department;
        this.boss = boss;
        this.employmentDate = employmentDate;
        this.salary = salary;
        this.id = id;
    }


    // Методы
    public void printInfoSimple() {
        System.out.println(fullName + "; " + position.getPosition() + "; " + department.getDepartment() + "; " +
                boss + "; " + id);
    } // Печать поисковой информации о сотрудниках

    public void printInfoAll() {
        System.out.println("ФИО: " + fullName + "\nДата рождения: " + birthday + "\nПол: " + sex.getSex() +
                "\nТелефон: " + phone + "\nДолжность: " + position.getPosition() +
                "\nОтдел: " + department.getDepartment() + "\nНачальник: " + boss + "\nДата найма: " + employmentDate +
                "\nЗарплата: " + salary + "\nID: " + id);
    } // Печать полной информации о сотрудниках

    public static void printCompanyReport() {

        String report;
        boolean isReportCorrect = false; // Проверка на корректность запроса для выхода из цикла
        System.out.println("\nЧтобы выйти из меню отчётов, введите \"Отмена\"");
        System.out.println("Чтобы вывести информацию о руководителях, введите \"Нач\"");
        System.out.println("Чтобы вывести среднюю зарплату по всей организации и по отделам, введите \"Зп\"");
        System.out.println("Чтобы вывести 10 самых дорогостоящих сотрудников, введите \"Плата\"");
        System.out.println("Чтобы вывести 10 самых долго работающих сотрудников, введите \"Стаж\"");

        do {
            System.out.print("\nВведите запрос: ");

            report = Main.sc.nextLine().toLowerCase();

            switch (report) {

                case ("отмена") -> {
                    return;
                }

                case ("нач") -> {

                    List<Employee> salesDept = new ArrayList<>(); // Сотрудники отдела продаж
                    List<Employee> developmentDept = new ArrayList<>(); // Сотрудники отдела разработок
                    List<Employee> directorateDept = new ArrayList<>(); // Сотрудники совета директоров
                    List<Employee> outsource = new ArrayList<>(); // Вольнонаёмные сотрудники

                    for (Employee emp : employees) {
                        Department dept = emp.getDepartmentENUM();
                        switch (dept) {
                            case SALES -> salesDept.add(emp);
                            case DEVELOPMENT -> developmentDept.add(emp);
                            case DIRECTORATE -> directorateDept.add(emp);
                            case OUTSOURCING -> outsource.add(emp);
                        }
                    } // Добавление сотрудников в списки

                    System.out.println(); // Косметический отступ

                    System.out.println(Department.DIRECTORATE.getDepartment() + "; Глава: " +
                            Department.DIRECTORATE.getDeptDirector() + "; " +
                            "Кол-во сотрудников: " + directorateDept.size());

                    System.out.println(Department.SALES.getDepartment() + "; Глава: " +
                            Department.SALES.getDeptDirector() + "; " +
                            "Кол-во сотрудников: " + salesDept.size());

                    System.out.println(Department.DEVELOPMENT.getDepartment() + "; Глава: " +
                            Department.DEVELOPMENT.getDeptDirector() + "; " +
                            "Кол-во сотрудников: " + developmentDept.size());

                    System.out.println("Кол-во вольнонаёмных сотрудников: " + outsource.size());

                    isReportCorrect = true;

                }

                case ("зп") -> {

                    List<Employee> salesDept = new ArrayList<>(); // Сотрудники отдела продаж
                    List<Employee> developmentDept = new ArrayList<>(); // Сотрудники отдела разработок
                    List<Employee> directorateDept = new ArrayList<>(); // Сотрудники совета директоров
                    List<Employee> outsource = new ArrayList<>(); // Вольнонаёмные сотрудники

                    double salesSalary = 0; // Счётчик зарплаты отдела продаж
                    double developmentSalary = 0; // Счётчик зарплаты отдела разработок
                    double directorateSalary = 0; // Счётчик зарплаты совета директоров
                    double outsourceSalary = 0; // Счётчик зарплаты вольнонаёмных сотрудников
                    double totalSalary = 0; // Счётчик общей зарплаты

                    int salersCount = 0; // Счётчик сотрудников отдела продаж
                    int developersCount = 0; // Счётчик сотрудников отдела разработок
                    int directorsCount = 0; // Счётчик сотрудников совета директоров
                    int outsourcesCount = 0; // Счётчик вольнонаёмных сотрудников
                    int allEmployeesCount = 0; // Счётчик сотрудников по всей компании

                    for (Employee emp : employees) {

                        totalSalary += emp.getSalary(); // Счётчик общей зарплаты
                        allEmployeesCount++; // Счётчик сотрудников

                        Department dept = emp.getDepartmentENUM();

                        switch (dept) {

                            case SALES -> {
                                salesDept.add(emp);
                                salesSalary += emp.getSalary();
                                salersCount++;
                            }

                            case DEVELOPMENT -> {
                                developmentDept.add(emp);
                                developmentSalary += emp.getSalary();
                                developersCount++;
                            }

                            case DIRECTORATE -> {
                                directorateDept.add(emp);
                                directorateSalary += emp.getSalary();
                                directorsCount++;
                            }

                            case OUTSOURCING -> {
                                outsource.add(emp);
                                outsourceSalary += emp.getSalary();
                                outsourcesCount++;
                            }

                        }

                    } // Добавление сотрудников в списки и заполнение счётчиков

                    double averageFullSalary = totalSalary / allEmployeesCount; // Средняя зарплата по компании
                    double averageSalesSalary = salesSalary / salersCount; // Средняя зарплата по компании
                    double averageDevelopmentSalary = developmentSalary / developersCount; // Средняя зарплата по компании
                    double averageDirectorateSalary = directorateSalary / directorsCount; // Средняя зарплата по компании
                    double averageOutsourceSalary = outsourceSalary / outsourcesCount; // Средняя зарплата по компании

                    System.out.println(); // Косметический отступ

                    System.out.println("Средняя зарплата по компании: " +
                            (Math.round(averageFullSalary * 100.0) / 100.0));
                    System.out.println("Средняя зарплата по отделу продаж: " +
                            (Math.round(averageSalesSalary * 100.0) / 100.0));
                    System.out.println("Средняя зарплата по отделу разработок: " +
                            (Math.round(averageDevelopmentSalary * 100.0) / 100.0));
                    System.out.println("Средняя зарплата совета директоров: " +
                            (Math.round(averageDirectorateSalary * 100.0) / 100.0));
                    System.out.println("Средняя зарплата вольнонаёмных сотрудников: " +
                            (Math.round(averageOutsourceSalary * 100.0) / 100.0));

                    isReportCorrect = true;

                }

                case ("плата") -> {

                    List<Employee> maxSalary = new ArrayList<>(employees);
                    maxSalary.sort(Comparators.highestSalaryEmployeeComparator.reversed());

                    try {

                        System.out.println(); // Косметический отступ

                        for (int i = 0; i < 10; i++) {
                            System.out.println(maxSalary.get(i).getFullName() + "; " +
                                    maxSalary.get(i).getSalary() + "; " +
                                    maxSalary.get(i).getId());
                        }

                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("\n* Количество сотрудников меньше десяти!");
                    }

                    isReportCorrect = true;

                }

                case ("стаж") -> {

                    List<Employee> maxWorkPeriod = new ArrayList<>(employees);
                    maxWorkPeriod.sort(Comparators.longestServingEmployeeComparator);

                    try {

                        System.out.println(); // Косметический отступ

                        for (int i = 0; i < 10; i++) {
                            System.out.println(maxWorkPeriod.get(i).getFullName() + "; " +
                                    maxWorkPeriod.get(i).getEmploymentDate() + "; " +
                                    maxWorkPeriod.get(i).getId());
                        }

                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("\n* Количество сотрудников меньше десяти!");
                    }

                    isReportCorrect = true;

                }

                default -> System.out.println("Некорректный ввод!");

            }

        } while (!isReportCorrect);

    } // Запрос на вывод информации о компании

    public static void addEmployees() {

        try {

            BufferedReader reader = new BufferedReader(new FileReader("Employees (reserve).txt"));
            String line = reader.readLine();

            String fullName = null;
            String birthday = null;
            Sex sex = null;
            String phone = null;
            Position position = null;
            Department department = null;
            String boss = null;
            String employmentDate = null;
            int salary = 0;
            String id = null;

            while (line != null) { // Считывание строки

                if (line.startsWith("ФИО")) {
                    fullName = line.split(": ")[1];
                }
                if (line.startsWith("Дата рождения")) {
                    birthday = line.split(": ")[1];
                }
                if (line.startsWith("Пол")) {
                    if (line.contains("Женский")) {
                        sex = Sex.FEMALE;
                    }
                    if (line.contains("Мужской")) {
                        sex = Sex.MALE;
                    }
                }
                if (line.startsWith("Телефон")) {
                    phone = line.split(": ")[1];
                }
                if (line.startsWith("Должность")) {
                    if (line.contains("Программист")) {
                        position = Position.PROGRAMMER;
                    }
                    if (line.contains("Менеджер")) {
                        position = Position.MANAGER;
                    }
                    if (line.contains("Дизайнер")) {
                        position = Position.DESIGNER;
                    }
                    if (line.contains("Начальник отдела")) {
                        position = Position.DIRECTOR;
                    }
                }
                if (line.startsWith("Отдел")) {
                    if (line.contains("Отдел продаж")) {
                        department = Department.SALES;
                    }
                    if (line.contains("Отдел разработок")) {
                        department = Department.DEVELOPMENT;
                    }
                    if (line.contains("Совет директоров")) {
                        department = Department.DIRECTORATE;
                    }
                    if (line.contains("Вольнонаёмный")) {
                        department = Department.OUTSOURCING;
                    }
                }
                if (line.startsWith("Начальник")) {
                    boss = line.split(": ")[1];
                }
                if (line.startsWith("Дата найма")) {
                    employmentDate = line.split(": ")[1];
                }
                if (line.startsWith("Зарплата")) {
                    salary = Integer.parseInt(line.substring(10));
                }
                if (line.startsWith("ID")) {
                    id = line.split(": ")[1];
                }

                // Возвращение значений по умолчанию
                if (line.contains("_")) {
                    employees.add(new Employee(fullName, birthday, sex, phone, position, department, boss, employmentDate,
                            salary, id));
                    fullName = null;
                    birthday = null;
                    sex = Sex.EMPTY;
                    phone = null;
                    position = Position.EMPTY;
                    department = Department.EMPTY;
                    boss = null;
                    employmentDate = null;
                    salary = 0;
                    id = null;
                }

                line = reader.readLine();

            }
            reader.close(); // Закрытие потока чтения
        } catch (IOException e) {
            e.printStackTrace();
        }
    } // Загрузка сотрудников из файла в TreeSet программы

    public static void newEmployee() {

        System.out.println("\nЧтобы выйти из меню добавления, введите \"Отмена\"");

        boolean isBackStringActive = false;

        String fullName; // Она же ФИО
        String birthday; // Дата рождения
        Sex sex; // Пол, сделан через ENUM
        String phone; // Телефон
        Position position; // Должность, сделана через ENUM
        Department department; // Отдел, сделан через ENUM
        String boss; // Начальник (ЕСЛИ ЕСТЬ!!!)
        String employmentDate; // Дата приёма на работу
        int salary; // Зарплата
        String id; // Айдишник

        while (!isBackStringActive) {

            fullName = AddMenu.enterFullName(); // Добавление ФИО

            if (fullName.equals("cancel")) {
                isBackStringActive = true;
                continue;
            }

            sex = AddMenu.enterSex(); // Добавление пола

            if (sex == Sex.EMPTY) {
                isBackStringActive = true;
                continue;
            }

            birthday = AddMenu.enterBirthday(); // Добавление даты рождения

            if (birthday.equals("cancel")) {
                isBackStringActive = true;
                continue;
            }

            position = AddMenu.enterPosition(); // Добавление должности

            if (position == Position.EMPTY) {
                isBackStringActive = true;
                continue;
            }

            department = AddMenu.enterDepartment(); // Добавление отдела

            if (department == Department.EMPTY) {
                isBackStringActive = true;
                continue;
            }

            boss = AddMenu.enterBoss(); // Добавление начальника

            if (boss.equals("cancel")) {
                isBackStringActive = true;
                continue;
            }

            phone = AddMenu.enterPhone(); // Добавление телефона

            if (phone.equals("cancel")) {
                isBackStringActive = true;
                continue;
            }

            employmentDate = AddMenu.enterEmploymentDate(); // Добавление даты найма

            if (employmentDate.equals("cancel")) {
                isBackStringActive = true;
                continue;
            }

            salary = AddMenu.enterSalary(); // Добавление зарплаты

            if (salary == -1) {
                isBackStringActive = true;
                continue;
            }

            id = UUID.randomUUID().toString().substring(0, 8); // Добавление рандомного айдишника (16-теричная система, 8 символов)

            System.out.println("\nID СОТРУДНИКА: " + id); // Проверка, можно удалить

            employees.add(new Employee(fullName, birthday, sex, phone, position, department, boss, employmentDate, salary, id));
            System.out.println("\nСотрудник " + fullName + " успешно добавлен");
            isBackStringActive = true;

        }

    } // Добавление нового сотрудника

    public static void saveChangesInFile() {

        try {
            PrintWriter writer = new PrintWriter(new FileWriter("Employees.txt")); // Объявление буферизированной перезаписи

            for (Employee emp : employees) {
                writer.write("ФИО: " + emp.getFullName() + "\r");
                writer.write("Дата рождения: " + emp.getBirthday() + "\r");
                writer.write("Пол: " + emp.getSex() + "\r");
                writer.write("Телефон: " + emp.getPhone() + "\r");
                writer.write("Должность: " + emp.getPosition() + "\r");
                writer.write("Отдел: " + emp.getDepartment() + "\r");
                writer.write("Начальник: " + emp.getBoss() + "\r");
                writer.write("Дата найма: " + emp.getEmploymentDate() + "\r");
                writer.write("Зарплата: " + emp.getSalary() + "\r");
                writer.write("ID: " + emp.getId() + "\r");
                writer.write("__________\r\r");
            }
            writer.close(); // Закрытие потока записи
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("\nИзменения сохранены");
    } // Перезапись данных их программы в файл "Employees.txt"

    public static void findAndPrintEmployee() {

        boolean correctInput = false; // Проверка на корректный ввод запроса

        System.out.println("\nЧтобы выйти из меню поиска, введите \"Отмена\"");
        System.out.println("Для поиска по Ф/И/О введите \"Имя\"");
        System.out.println("Для поиска по специальности введите \"Долж\"");
        System.out.println("Для поиска по подразделению введите \"Отд\"");
        System.out.println("Для поиска по руководителю введите \"Нач\"");

        do {
            System.out.print("\nВведите критерий поиска: ");
            String searchTypeInput = Main.sc.nextLine().toLowerCase(); // Ввод критерия

            switch (searchTypeInput) {

                case ("отмена") -> {
                    return;
                } // Для выхода из меню

                case ("имя") -> {

                    System.out.print("Введите Ф/И/О для поиска: ");
                    String searchFullName = Main.sc.nextLine().toLowerCase();

                    if (searchFullName.equals("")) {
                        System.out.println("Строка ввода пустая!");
                        continue;
                    }

                    System.out.println(); // Косметический отступ

                    List<Employee> foundEmployees = employees.stream()
                            .filter(employee -> employee.getFullName()
                                    .toLowerCase()
                                    .contains(searchFullName))
                            .toList();

                    foundEmployees.forEach(Employee::printInfoSimple);

                    if (foundEmployees.size() == 0) {
                        System.out.println("Ф/И/О не найдено!");
                        continue;
                    }

                    correctInput = true;

                }

                case ("долж") -> {
                    System.out.print("Введите должность для поиска: ");
                    String searchPosition = Main.sc.nextLine().toLowerCase();

                    if (searchPosition.equals("")) {
                        System.out.println("Строка ввода пустая!");
                        continue;
                    }

                    System.out.println(); // Косметический отступ

                    List<Employee> foundEmployees = employees.stream()
                            .filter(employee -> employee.getPosition()
                                    .toLowerCase()
                                    .contains(searchPosition))
                            .toList();

                    foundEmployees.forEach(Employee::printInfoSimple);

                    if (foundEmployees.size() == 0) {
                        System.out.println("Должность не найдена!");
                        continue;
                    }

                    correctInput = true;

                }

                case ("отд") -> {
                    System.out.print("Введите отдел для поиска: ");
                    String searchDepartment = Main.sc.nextLine().toLowerCase();

                    if (searchDepartment.equals("")) {
                        System.out.println("Строка ввода пустая!");
                        continue;
                    }

                    System.out.println(); // Косметический отступ

                    List<Employee> foundEmployees = employees.stream()
                            .filter(employee -> employee.getDepartment()
                                    .toLowerCase()
                                    .contains(searchDepartment))
                            .toList();

                    foundEmployees.forEach(Employee::printInfoSimple);

                    if (foundEmployees.size() == 0) {
                        System.out.println("Отдел не найден!");
                        continue;
                    }

                    correctInput = true;
                }

                case ("нач") -> {
                    System.out.print("Введите начальника для поиска: ");
                    String searchBoss = Main.sc.nextLine().toLowerCase();

                    if (searchBoss.equals("")) {
                        System.out.println("Строка ввода пустая!");
                        continue;
                    }

                    System.out.println(); // Косметический отступ

                    List<Employee> foundEmployees = employees.stream()
                            .filter(employee -> employee.getBoss()
                                    .toLowerCase()
                                    .contains(searchBoss))
                            .toList();

                    foundEmployees.forEach(Employee::printInfoSimple);

                    if (foundEmployees.size() == 0) {
                        System.out.println("Начальник не найден!");
                        continue;
                    }

                    correctInput = true;
                }

                default -> System.out.println("\nНекорректный ввод!");
            }
        } while (!correctInput);
    } // Поиск сотрудника по определённому параметру

    public static void changeEmployee() {

        boolean isChangingCompleted = false;

        System.out.println("\nЧтобы выйти из меню изменения, введите \"Отмена\"");

        do {
            System.out.print("\nВведите ID сотрудника для корректировки: ");
            String idInput = Main.sc.nextLine().toLowerCase();

            if (idInput.equals("отмена")) {
                return;
            }

            boolean isIdInBase = false;
            Employee employeeToCorrect = null;
            String currentId = null;

            for (Employee emp : employees) {
                if (emp.getId().equals(idInput)) {
                    isIdInBase = true;
                    employeeToCorrect = emp;
                    currentId = emp.getId();
                    System.out.println("\nВыбран сотрудник " + emp.getFullName() + ", " + emp.getPosition() + ", "
                            + emp.getBirthday() + " г.р.");
                }
            } // Цикл для проверки наличия ID и активации меню изменения, если айдишник есть в базе

            if (!isIdInBase) {
                System.out.println("ID не найдено!");
                continue;
            }

            String operation;

            System.out.println("\nДля изменения Ф/И/О сотрудника введите \"Имя\"");
            System.out.println("Для изменения пола сотрудника введите \"Пол\"");
            System.out.println("Для изменения даты рождения сотрудника введите \"Рожд\"");
            System.out.println("Для изменения специальности сотрудника введите \"Долж\"");
            System.out.println("Для изменения подразделения сотрудника введите \"Отд\"");
            System.out.println("Для изменения руководителя сотрудника введите \"Нач\"");
            System.out.println("Для изменения телефона сотрудника введите \"Тел\"");
            System.out.println("Для изменения даты найма сотрудника введите \"Найм\"");
            System.out.println("Для изменения зарплаты сотрудника введите \"Зп\"");
            System.out.println("Для отмены изменений и выхода из меню введите \"Отмена\"");
            System.out.println("Для сохранения изменений и выхода из меню введите \"Сохр\"");

            // Приведение исходных значений
            String fullNameToChange = employeeToCorrect.getFullName();
            Sex sexToChange = employeeToCorrect.getSexENUM();
            String birthdayToChange = employeeToCorrect.getBirthday();
            Position positionToChange = employeeToCorrect.getPositionENUM();
            Department departmentToChange = employeeToCorrect.getDepartmentENUM();
            String bossToChange = employeeToCorrect.getBoss();
            String phoneToChange = employeeToCorrect.getPhone();
            String employmentDateToChange = employeeToCorrect.getEmploymentDate();
            int salaryToChange = employeeToCorrect.getSalary();

            do {
                System.out.print("\nВведите параметр для изменения: ");
                operation = Main.sc.nextLine().toLowerCase();

                switch (operation) {
                    case ("отмена") -> {
                        System.out.println("\nОперация отменена, изменения не сохранены");
                        return;
                    }
                    case ("имя") -> fullNameToChange = AddMenu.enterFullName();
                    case ("пол") -> sexToChange = AddMenu.enterSex();
                    case ("рожд") -> birthdayToChange = AddMenu.enterBirthday();
                    case ("долж") -> positionToChange = AddMenu.enterPosition();
                    case ("отд") -> departmentToChange = AddMenu.enterDepartment();
                    case ("нач") -> bossToChange = AddMenu.enterBoss();
                    case ("тел") -> phoneToChange = AddMenu.enterPhone();
                    case ("найм") -> employmentDateToChange = AddMenu.enterEmploymentDate();
                    case ("зп") -> salaryToChange = AddMenu.enterSalary();
                    case ("сохр") -> {
                        employees.remove(employeeToCorrect); // Удаление старой записи о сотруднике
                        employees.add(new Employee(fullNameToChange, birthdayToChange, sexToChange, phoneToChange, positionToChange,
                                departmentToChange, bossToChange, employmentDateToChange,
                                salaryToChange, currentId)); // Добавление изменённой записи со старым айдишником
                        isChangingCompleted = true;
                        System.out.println("\nИзменение данных сотрудника успешно завершено");
                    }
                }
            } while (!operation.equals("сохр"));
        } while (!isChangingCompleted);
    } // Изменение сотрудника по id

    public static void removeEmployee() {

        boolean isDeletingCompleted = false;
        Employee employeeToDelete = null;

        System.out.println("\nЧтобы выйти из меню изменения, введите \"Отмена\"");
        do {

            System.out.print("\nВведите ID сотрудника для удаления: ");

            String idInput = Main.sc.nextLine().toLowerCase();

            if (idInput.equals("отмена")) {
                return;
            }

            boolean isIdInBase = false;
            for (Employee emp : employees) {
                if (emp.getId().equals(idInput)) {
                    isIdInBase = true;
                    employeeToDelete = emp;
                    break;
                }
            }

            if (!isIdInBase) {
                System.out.println("id не найдено!");
                continue;
            }

            System.out.println("\nВыбран сотрудник для удаления:");
            System.out.println(employeeToDelete.getFullName() + "; " + employeeToDelete.getBirthday() + "; " +
                    employeeToDelete.getPosition() + "; " + employeeToDelete.getId());
            System.out.println("Для подтверждения удаления введите \"+\", для отмены удаления введите \"-\"");
            System.out.print("Удалить сотрудника? ");

            String confirmation = Main.sc.nextLine();

            switch (confirmation) {
                case ("отмена") -> {
                    return;
                }
                case ("+") -> {
                    employees.remove(employeeToDelete);
                    isDeletingCompleted = true;
                    System.out.println("\nУдаление сотрудника успешно завершено");
                }
                case ("-") -> {
                    // Пропуск цикла, удаление сотрудника не производится
                }
                default -> System.out.println("Некорректный ввод!");
            }

        } while (!isDeletingCompleted);
    } // Удаление сотрудника по id

}