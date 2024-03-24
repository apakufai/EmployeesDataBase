public enum Sex {

    MALE("Мужской"),
    FEMALE("Женский"),
    EMPTY("null");

    String sex;

    Sex(String sex) {
        this.sex = sex;
    }

    public String getSex() {
        return sex;
    }

}