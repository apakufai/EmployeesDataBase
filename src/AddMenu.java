import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class AddMenu {

    public static String enterFullName() {

        String nameInput;
        String surnameInput;
        String patronymicInput;
        String fullName;

        System.out.println(); // Косметический отступ

        nameInput = inputNameSurnamePatronymic("имя");

        if (nameInput.equals("cancel")) {
            return "cancel";
        } // Строка для отмены операции

        System.out.println(); // Косметический отступ

        surnameInput = inputNameSurnamePatronymic("фамилию");

        if (surnameInput.equals("cancel")) {
            return "cancel";
        } // Строка для отмены операции

        System.out.println(); // Косметический отступ

        patronymicInput = inputNameSurnamePatronymic("отчество");

        if (patronymicInput.equals("cancel")) {
            return "cancel";
        } // Строка для отмены операции

        fullName = Checks.correctInputCase(surnameInput) + " " + Checks.correctInputCase(nameInput)
                + patronymicInput; // ПОЛНОЕ ИМЯ СОТРУДНИКА
        /* Здесь пробел между фамилией и отчеством не ставится, т.к. не у всех людей оно есть.
        При наличии отчества пробел сразу вписывается в строку отчества, после чего происходит общая конкатенация
        */ // Комментарий к коду

        System.out.println("\nФИО СОТРУДНИКА: " + fullName); // Проверка, можно удалить

        return fullName;

    } // Ввод имени

    public static Sex enterSex() {

        System.out.println(); // Косметический отступ

        Sex sex = Sex.EMPTY;

        do {
            System.out.print("Введите пол сотрудника - \"М\" или \"Ж\": ");
            String sexInput = Main.sc.nextLine().toLowerCase();
            switch (sexInput) {
                case ("") -> System.out.println("Строка ввода пустая!");
                case ("отмена") -> {
                    return Sex.EMPTY;
                }
                case ("м") -> sex = Sex.MALE;
                case ("ж") -> sex = Sex.FEMALE;
                default -> System.out.println("Некорректный ввод!");
            }

        } while (sex == Sex.EMPTY);

        System.out.println("\nПОЛ СОТРУДНИКА: " + sex.getSex()); // Проверка, можно удалить

        return sex;

    } // Ввод пола

    public static String enterBirthday() {

        System.out.println(); // Косметический отступ

        String birthday = null;

        boolean isDateCorrect = false;

        do {

            LocalDate dateOfBirthday = inputDate("день рождения");

            if (dateOfBirthday.isEqual(LocalDate.of(1, 1, 1))) {
                return "cancel";
            } // Сопровождающая строка для отмены операции

            // Границы года вводятся вручную! Раньше 16-ти на работу не берут по закону, после 100 обычно не работают
            if (dateOfBirthday.plusYears(16).isAfter(LocalDate.now()) ||
                    dateOfBirthday.isBefore(LocalDate.now().minusYears(100))) {
                System.out.println("\nНекорректный для найма возраст! Попробуйте снова\n");
                continue;
            }

            birthday = dateOfBirthday.getDayOfMonth() + "."
                    + dateOfBirthday.getMonthValue() + "."
                    + dateOfBirthday.getYear();

            isDateCorrect = true;

        } while (!isDateCorrect);

        System.out.println("\nДАТА РОЖДЕНИЯ СОТРУДНИКА: " + birthday); // Проверка, можно удалить

        return birthday;

    } // Ввод даты рождения

    public static Position enterPosition() {

        System.out.println(); // Косметический отступ

        Position position = Position.EMPTY; // Должность, значение по умолчанию - строка "null"

        // Проверка на корректность ввода строки должности

        do {
            System.out.print("Введите должность сотрудника: ");
            String positionInput = Main.sc.nextLine().toLowerCase(); // Ввод должности

            switch (positionInput) {
                case ("") -> System.out.println("Строка ввода пустая!");
                case ("отмена") -> {
                    return Position.EMPTY;
                }
                case ("программист") -> position = Position.PROGRAMMER;
                case ("менеджер") -> position = Position.MANAGER;
                case ("дизайнер") -> position = Position.DESIGNER;
                default -> System.out.println("Некорректный ввод!");
            }

        } while (position == Position.EMPTY);

        System.out.println("\nДОЛЖНОСТЬ СОТРУДНИКА: " + position.getPosition()); // Проверка, можно удалить

        return position;

    } // Ввод должности

    public static Department enterDepartment() {

        System.out.println(); // Косметический отступ

        Department department = Department.EMPTY; // Отдел

        do {
            System.out.print("Введите отдел (если его нет, введите \"-\"): ");
            String departmentInput = Main.sc.nextLine().toLowerCase(); // Ввод должности
            switch (departmentInput) {
                case ("") -> System.out.println("Строка ввода пустая!");
                case ("отмена") -> {
                    return Department.EMPTY;
                }
                case ("-") -> department = Department.OUTSOURCING;
                case ("отдел продаж") -> department = Department.SALES;
                case ("отдел разработок") -> department = Department.DEVELOPMENT;
                case ("совет директоров") -> department = Department.DIRECTORATE;
                default -> System.out.println("Некорректный ввод!");
            }

        } while (department == Department.EMPTY);

        System.out.println("\nОТДЕЛ СОТРУДНИКА: " + department.getDepartment()); // Проверка, можно удалить

        return department;

    } // Ввод отдела

    public static String enterBoss() {

        System.out.println(); // Косметический отступ

        String boss = ""; // Начальник

        boolean isBossCorrect; // Проверка на правильность строки ввода босса
        boolean isBossChanged = false; // Выбран ли начальник?

        do {
            System.out.print("Введите имя и фамилию начальника сотрудника (если его нет, введите \"-\"): ");
            String bossInput = Main.sc.nextLine().toLowerCase(); // Ввод кода города

            if (bossInput.equals("")) {
                System.out.println("Строка ввода пустая!");
                continue;
            }

            if (bossInput.equals("отмена")) {
                return "cancel";
            }

            isBossCorrect = (bossInput.equals(bossInput.replaceAll("[^- А-ЯЁа-яё]", ""))); // проверка

            if (!isBossCorrect) {
                System.out.println("Некорректный ввод!");
                continue;
            }

            switch (bossInput) {
                case ("-") -> {
                    System.out.println("\nПОЛЕ НАЧАЛЬНИКА ОСТАЛОСЬ ПУСТЫМ");
                    return "-";
                }
                case ("павел панкратов") -> {
                    boss = "Панкратов Павел Петрович";
                    isBossChanged = true;
                }
                case ("олег орлов") -> {
                    boss = "Орлов Олег Остапович";
                    isBossChanged = true;
                }
                case ("егор митрофанов") -> {
                    boss = "Митрофанов Егор Андреевич";
                    isBossChanged = true;
                }
                default -> System.out.println("Введённый человек отсутствует в базе данных!");
            }

        } while (!isBossChanged);

        System.out.println("\nНАЧАЛЬНИК СОТРУДНИКА: " + boss); // Проверка, можно удалить

        return boss;

    } // Ввод начальника
        /* Здесь начальник для каждого человека вводится вручную. В классе "Department" за каждым ENUM
        закреплён департамент и начальник этого департамента. Не факт, что начальник отдела, в котором
        работает человек, будет непосредственным начальником этого человека.
        */ // Комментарий к блоку

    public static String enterPhone() {

        String inputPhone;
        String phone = null;

        System.out.println(); // Косметический отступ

        boolean isPhoneCorrect = false;

        do {
            System.out.print("Введите телефон сотрудника: ");

            inputPhone = Main.sc.nextLine().toLowerCase();

            if (inputPhone.equals("")) {
                System.out.println("Строка ввода пустая!");
                continue;
            }

            if (inputPhone.equals("отмена")) {
                return "cancel";
            } // Для выхода из меню

            if (!Checks.correctPhone(inputPhone)) {
                System.out.println("Некорректный ввод!");
                continue;
            }

            if (inputPhone.replaceAll("[^0-9]", "").length() != 11) {
                System.out.println("Некорректная длина номера!");
                continue;
            } // Проверка на длину номера

            phone = "+7" + inputPhone.replaceAll("[^0-9]", "").substring(1);

            isPhoneCorrect = true;

        } while (!isPhoneCorrect);

        System.out.println("\nТЕЛЕФОН СОТРУДНИКА: " + phone); // Проверка, можно удалить

        return phone;

    } // Полный ввод телефона

    public static String enterEmploymentDate() {

        System.out.println(); // Косметический отступ

        String employment = null;

        boolean isEmploymentDateCorrect = false;

        do {

            LocalDate employmentDate = inputDate("дату найма");

            if (employmentDate.isEqual(LocalDate.of(1, 1, 1))) {
                return "cancel";
            } // Сопровождающая строка для отмены операции

            // Границы года вводятся вручную! После 100 обычно не работают
            if (employmentDate.isBefore(LocalDate.now().minusYears(100))) {
                System.out.println("\nНекорректный для найма возраст! Попробуйте снова\n");
                continue;
            }

            employment = employmentDate.getDayOfMonth() + "."
                    + employmentDate.getMonthValue() + "."
                    + employmentDate.getYear();

            isEmploymentDateCorrect = true;

        } while (!isEmploymentDateCorrect);

        System.out.println("\nДАТА НАЙМА СОТРУДНИКА: " + employment); // Проверка, можно удалить

        return employment;

    } // Ввод даты найма

    public static int enterSalary() {

        System.out.println(); // Косметический отступ

        boolean isSalaryCorrect = false; // Проверка на правильность введённой зарплаты

        int salary = 0; // Зарплата

        do {
            System.out.print("Введите зарплату сотрудника: ");
            String salaryInput = Main.sc.nextLine().toLowerCase();

            if (salaryInput.equals("")) {
                System.out.println("Строка ввода пустая!");
                continue;
            } // При пустой строке

            if (salaryInput.length() > 9) {
                System.out.println("Некорректный ввод!");
                continue;
            } // Чтобы обойти NumberFormatException

            if (salaryInput.equals("отмена")) {
                return -1;
            }

            isSalaryCorrect = Checks.correctNumber(salaryInput); // Проверка на правильность ввода

            if (!isSalaryCorrect) {
                continue;
            }

            salary = Integer.parseInt(salaryInput);


        } while (!isSalaryCorrect);

        System.out.println("\nЗАРПЛАТА СОТРУДНИКА: " + salary + " РУБЛЕЙ."); // Проверка, можно удалить

        return salary;

    } // Ввод зарплаты

    public static LocalDate inputDate(String type) {

        LocalDate date = null;
        boolean isDateCorrect = false;
        String[] dateByParts;

        do {
            System.out.print("Введите " + type + " сотрудника в формате ДД.ММ.ГГГГ: ");

            String input = Main.sc.nextLine().toLowerCase();

            if (input.equals("")) {
                System.out.println("Строка ввода пустая!");
                continue;
            }

            if (input.equals("отмена")) {
                isDateCorrect = true;
                return LocalDate.of(1, 1, 1);
            } // Сопровождающая строка для отмены операции

            if (!Checks.correctDate(input)) {
                continue;
            }

            dateByParts = input.split("\\.");

            if (dateByParts.length < 3) {
                System.out.println("Некорректный ввод!");
                continue;
            }

            int year = Integer.parseInt(dateByParts[2]);
            int month = Integer.parseInt(dateByParts[1]);
            int day = Integer.parseInt(dateByParts[0]);

            try {
                date = LocalDate.of(year, month, day);
            } catch (DateTimeParseException e) {
                System.out.println("\nНекорректная дата! Попробуйте снова\n");
                continue;
            }

            if (date.isBefore(LocalDate.parse("1900-01-01"))) {
                System.out.println("\nНекорректная дата! Попробуйте снова\n");
                continue;
            }

            isDateCorrect = true;

        } while (!isDateCorrect);

        return date;

    }

    public static String inputNameSurnamePatronymic(String type) {

        String input; // Строка ввода
        boolean isInputCorrect = false;

        do {

            if (type.equals("отчество")) {
                System.out.print("Введите " + type + " сотрудника (если его нет, введите \"отс\"): ");
            } else {
                System.out.print("Введите " + type + " сотрудника: ");
            }

            input = Main.sc.nextLine().toLowerCase(); // Ввод имени

            if (input.equals("")) {
                System.out.println("Строка ввода пустая!");
                continue;
            } // Для повторного запуска цикла

            if (input.equals("отмена")) {
                return "cancel";
            } // Для отмены операции


            isInputCorrect = Checks.correctName(input);
            if (!isInputCorrect) {
                continue;
            }

            if (type.equals("отчество")) {
                if (input.equals("отс")) {
                    return ""; // Пустой ввод, чтобы избежать NullPointerException
                } else {
                    input = " " + Checks.correctInputCase(input);
                }
            }

        } while (!isInputCorrect); // Ввод имени сотрудника
        return input;
    } // Общий метод для ввода ФИО

}