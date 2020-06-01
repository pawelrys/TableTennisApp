package IO.IO_project;

public class Guest {
    private int ID;
    private String imie;

    Guest(int ID, String imie){
        this.ID = ID;
        this.imie = imie;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public int getID() { return ID; }

    public void setID(int ID) { this.ID = ID; }
}
