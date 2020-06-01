package IO.IO_project;

abstract class Referee extends Person{
    private String login;
    private String password;

    Referee(String name, String surname, String sex, String birthdayDate, String password, String login) {
        super(name, surname, sex, birthdayDate);
        this.password = password;
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() { return login; }

    public void setLogin(String login) { this.login = login; }

}

class MainReferee extends Referee{
    private String position;
    MainReferee(String name, String surname, String sex, String birthdayDate, String password, String position, String login) {
        super(name, surname, sex, birthdayDate, password, login);
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}

class CountingReferee extends Referee{
    private int tableNumber;

    CountingReferee(String name, String surname, String sex, String birthdayDate, String password, int tableNumber, String login) {
        super(name, surname, sex, birthdayDate, password, login);
        this.tableNumber = tableNumber;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) { this.tableNumber = tableNumber; }
}