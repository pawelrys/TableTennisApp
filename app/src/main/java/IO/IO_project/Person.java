package IO.IO_project;

abstract class Person {  // factory method - metoda wytwórcza
    private final int ID;
    private final String name;
    private final String surname;
    private final String sex ;
    private final String birthdayDate;
    private static int counter = 0;

    Person(String name, String surname, String sex, String birthdayDate){
        this.ID = counter;
        counter++;
        this.name = name;
        this.surname = surname;
        this.sex = sex;
        this.birthdayDate = birthdayDate;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getSex() {
        return sex;
    }

    public String getBirthdayDate() {
        return birthdayDate;
    }

    public int getID() {
        return ID;
    }
}
