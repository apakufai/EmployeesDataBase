import java.util.Comparator;

public class Comparators {

    // Компаратор для основной сортировки сотрудников
    public static Comparator<Employee> mainComparator = Comparator
            .comparing(Employee::getFullName)
            .thenComparing(Employee::getYearOfBirth)
            .thenComparing(Employee::getMonthOfBirth)
            .thenComparing(Employee::getDayOfBirth)
            .thenComparing(Employee::getId);


    // Компаратор для сортировки сотрудников по зарплате
    public static Comparator<Employee> highestSalaryEmployeeComparator = Comparator
            .comparing(Employee::getSalary);


    // Компаратор для сортировки сотрудников по сроку работы в компании
    public static Comparator<Employee> longestServingEmployeeComparator = Comparator
            .comparing(Employee::getYearOfEmploymentDate)
            .thenComparing(Employee::getMonthOfEmploymentDate)
            .thenComparing(Employee::getDayOfEmploymentDate);

}