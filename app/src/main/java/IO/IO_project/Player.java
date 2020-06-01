package IO.IO_project;

public class Player extends Person {
    private String club;
    private String ageCategory;
    private int points;

    Player(String name, String surname, String sex, String birthdayDate, String club, String ageCategory, int points) {
        super(name, surname, sex, birthdayDate);
        this.club = club;
        this.ageCategory = ageCategory;
        this.points = points;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public String getAgeCategory() {
        return ageCategory;
    }

    public void setAgeCategory(String ageCategory) {
        this.ageCategory = ageCategory;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
