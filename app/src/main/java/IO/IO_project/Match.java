package IO.IO_project;

public class Match {
    private final int ID;
    private final int IDPlayer1;
    private final int IDPlayer2;
    private String set1 = "";
    private String set2 = "";
    private String set3 = "";
    private String set4 = "";
    private String set5 = "";
    private int IDWinPlayer;

    Match(int id, int ID1, int ID2){
        this.ID = id;
        this.IDPlayer1 = ID1;
        this.IDPlayer2 = ID2;
    }

    public int getIDWinPlayer() {
        return IDWinPlayer;
    }

    public void setIDWinPlayer(int IDWinPlayer) {
        this.IDWinPlayer = IDWinPlayer;
    }

    public String getSet5() {
        return set5;
    }

    public void setSet5(String set5) {
        this.set5 = set5;
    }

    public String getSet4() {
        return set4;
    }

    public void setSet4(String set4) {
        this.set4 = set4;
    }

    public String getSet3() {
        return set3;
    }

    public void setSet3(String set3) {
        this.set3 = set3;
    }

    public String getSet2() {
        return set2;
    }

    public void setSet2(String set2) {
        this.set2 = set2;
    }

    public String getSet1() {
        return set1;
    }

    public void setSet1(String set1) {
        this.set1 = set1;
    }

    public int getIDPlayer1() {
        return IDPlayer1;
    }

    public int getIDPlayer2() {
        return IDPlayer2;
    }

    public int getID() { return ID; }
}
