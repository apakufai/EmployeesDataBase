public enum Department {

    SALES("Отдел продаж", "Панкратов Павел Петрович"),
    DEVELOPMENT("Отдел разработок", "Орлов Олег Остапович"),
    DIRECTORATE("Совет директоров", "Митрофанов Егор Андреевич"),
    OUTSOURCING("Вольный найм", "-"),
    EMPTY("null", "null");

    private String department;
    private String deptDirector;

    Department(String department, String deptDirector) {
        this.department = department;
        this.deptDirector = deptDirector;
    }

    public String getDepartment() {
        return department;
    }

    public String getDeptDirector() {
        return deptDirector;
    }


}