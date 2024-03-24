public enum Position {

    PROGRAMMER("Программист"),
    MANAGER("Менеджер"),
    DESIGNER("Дизайнер"),
    DIRECTOR("Начальник отдела"),
    EMPTY("null");

    String position;

    Position(String position) {
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

}