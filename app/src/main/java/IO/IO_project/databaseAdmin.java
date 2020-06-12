package IO.IO_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class databaseAdmin extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String Osoby_Name = "Osoby";
    private static final String Zawodnicy_Name = "Zawodnicy";
    private static final String Sedziowie_Name = "Sędziowie";
    private static final String Turniej_Name = "Turniej";
    private static final String Mecz_Name = "Mecze";
    private static final String Guest_Name = "Goście";
    private SQLiteDatabase db = getWritableDatabase();
    public static final String name_database = "databaseBeta23.db";

    private static databaseAdmin instance;

    databaseAdmin(Context context) {
        super(context, name_database, null, 1);
    }  //tylko do testów

    public static databaseAdmin getInstance(Context context) { // singleton, odtwarzany w każdej klasie
        if (instance == null) {
            instance = new databaseAdmin(context);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + Osoby_Name +"(" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Imię TEXT NOT NULL," +
                "Nazwisko TEXT NOT NULL," +
                "Data_Urodzenia TEXT NOT NULL," +
                "Płeć TEXT NOT NULL);" +
                "");

        db.execSQL("CREATE TABLE " + Zawodnicy_Name + "(" +
                "Player_ID INTEGER," +
                "Klub TEXT," +
                "Kategoria TEXT," +
                "Punkty INTEGER," +
                "FOREIGN KEY (Player_ID) REFERENCES Osoby (ID));" +
                "");

        db.execSQL("CREATE TABLE " + Sedziowie_Name + "(" +
                "Referee_ID INTEGER," +
                "Login TEXT," +
                "Hasło TEXT DEFAULT NULL," +
                "Stanowisko TEXT DEFAULT NULL," +
                "Nr_Stołu INTEGER DEFAULT NULL," +
                "FOREIGN KEY (Referee_ID) REFERENCES Osoby (ID));");

        db.execSQL("CREATE TABLE " + Turniej_Name + "(" +
                "Player_ID_Tournament INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Player_ID INTEGER," +
                "Nr_Startowy INTEGER DEFAULT NULL," +
                "Miejsce INTEGER DEFAULT NULL," +
                "FOREIGN KEY (Player_ID) REFERENCES Zawodnicy (Player_ID));" +
                "");

        db.execSQL("CREATE TABLE " + Mecz_Name + "(" +
                "ID_Meczu INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Player_ID1 INTEGER DEFAULT 0," +
                "Player_ID2 INTEGER DEFAULT 0," +
                "Set_1 TEXT DEFAULT NULL," +
                "Set_2 TEXT DEFAULT NULL," +
                "Set_3 TEXT DEFAULT NULL," +
                "Set_4 TEXT DEFAULT NULL," +
                "Set_5 TEXT DEFAULT NULL," +
                "Player_ID_Win INTEGER DEFAULT NULL," +
                "FOREIGN KEY (Player_ID1) REFERENCES Turniej (Player_ID_Tournament)," +
                "FOREIGN KEY (Player_ID2) REFERENCES Turniej (Player_ID_Tournament)," +
                "FOREIGN KEY (Player_ID_Win) REFERENCES Turniej (Player_ID_Tournament));" +
                "");

        db.execSQL("CREATE TABLE " + Guest_Name + "(" +
                "Guest_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Imię TEXT);" +
                "");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addPlayer(Player player){
        String data = player.getBirthdayDate().substring(6,10);
        if(data.charAt(0) > '2') return false;
        if (data.charAt(0) == '2'){
            if(data.charAt(1) > '0') return false;
            if(data.charAt(2) > '2') return false;
            if(data.charAt(2) == '2' && data.charAt(3) > '0') return false;
        }
        ContentValues wartosci = new ContentValues();
        wartosci.put("Imię", player.getName());
        wartosci.put("Nazwisko", player.getSurname());
        wartosci.put("Data_Urodzenia", player.getBirthdayDate().toString());
        wartosci.put("Płeć", player.getSex());
        long i = db.insert("Osoby", null, wartosci);

        ContentValues wartosci1 = new ContentValues();
        wartosci1.put("Player_ID", i);
        wartosci1.put("Klub", player.getClub());
        wartosci1.put("Kategoria", player.getAgeCategory());
        wartosci1.put("Punkty", player.getPoints());
        db.insert("Zawodnicy", null, wartosci1);
        return true;
    }

    public void addCountingReferee(CountingReferee referee){
        ContentValues wartosci = new ContentValues();
        wartosci.put("Imię", referee.getName());
        wartosci.put("Nazwisko", referee.getSurname());
        wartosci.put("Data_Urodzenia", referee.getBirthdayDate().toString());
        wartosci.put("Płeć", referee.getSex());
        long i = db.insert("Osoby", null, wartosci);

        ContentValues wartosci1 = new ContentValues();
        wartosci1.put("Referee_ID", i);
        wartosci1.put("Login", referee.getLogin());
        wartosci1.put("Hasło", referee.getPassword());
        wartosci1.put("Nr_Stołu", referee.getTableNumber());
        db.insert("Sędziowie", null, wartosci1);
    }

    public void addMainReferee(MainReferee referee){
        ContentValues wartosci = new ContentValues();
        wartosci.put("Imię", referee.getName());
        wartosci.put("Nazwisko", referee.getSurname());
        wartosci.put("Data_Urodzenia", referee.getBirthdayDate().toString());
        wartosci.put("Płeć", referee.getSex());
        long i = db.insert("Osoby", null, wartosci);

        ContentValues wartosci1 = new ContentValues();
        wartosci1.put("Referee_ID", i);
        wartosci1.put("Login", referee.getLogin());
        wartosci1.put("Hasło", referee.getPassword());
        wartosci1.put("Stanowisko", "pozycja");
        db.insert("Sędziowie", null, wartosci1);
    }

    public boolean addPlayerToTournament(Player player){
        String data = player.getBirthdayDate().substring(6,10);
        if(data.charAt(0) > '2') return false;
        if (data.charAt(0) == '2'){
            if(data.charAt(1) > '0') return false;
            if(data.charAt(2) > '2') return false;
            if(data.charAt(2) == '2' && data.charAt(3) > '0') return false;
        }
        ContentValues wartosci = new ContentValues();
        wartosci.put("Player_ID", player.getID() + 1);
        long i = db.insert("Turniej", null, wartosci);
        return true;
    }

    public void addPlayerToTournament(int id){
        ContentValues wartosci = new ContentValues();
        wartosci.put("Player_ID", id);
        db.insert("Turniej", null, wartosci);
    }


    public boolean addMatch(Match match) {
        if(match.getIDPlayer1() == match.getIDPlayer2()) return false;
        ContentValues wartosci = new ContentValues();
        wartosci.put("Player_ID1", match.getIDPlayer1());
        wartosci.put("Player_ID2", match.getIDPlayer2());
        db.insert(Mecz_Name, null, wartosci);
        return true;
    }

    public void addRestMatch() {
        ContentValues wartosci = new ContentValues();
        wartosci.put("Player_ID1", 0);
        wartosci.put("Player_ID2", 0);
        for (int i = 0; i < 15; i++) {
            db.insert(Mecz_Name, null, wartosci);
        }
    }

    public Cursor showPerson(){
        String rawQuery = "SELECT * FROM Osoby";
        Cursor kursor = db.rawQuery(rawQuery, null);
        return kursor;
    }

    public Cursor showOnlyPlayer() {
        String rawQuery = "SELECT * FROM " + Zawodnicy_Name ;
        Cursor kursor = db.rawQuery(rawQuery, null);
        return kursor;
    }

    public Cursor showPlayer(){
        String rawQuery = "SELECT * FROM Osoby JOIN Zawodnicy ON Osoby.ID = Zawodnicy.Player_ID";
        Cursor kursor = db.rawQuery(rawQuery, null);
        return kursor;
    }

    public Cursor showPlayerByParameter(String parameter){
        String rawQuery = "SELECT * FROM Osoby JOIN Zawodnicy ON Osoby.ID = Zawodnicy.Player_ID ORDER BY " + parameter;
        Cursor kursor = db.rawQuery(rawQuery, null);
        return kursor;
    }

    public Cursor showPlayerByParameterDESC(){
        String parameter = "Punkty";
        String rawQuery = "SELECT * FROM Osoby JOIN Zawodnicy ON Osoby.ID = Zawodnicy.Player_ID ORDER BY " + parameter + " DESC ";
        Cursor kursor = db.rawQuery(rawQuery, null);
        return kursor;
    }

    public Cursor showReferee(){
        SQLiteDatabase db = getReadableDatabase();
        String rawQuery = "SELECT * FROM Osoby INNER JOIN Sędziowie ON Osoby.ID = Sędziowie.Referee_ID";
        Cursor kursor = db.rawQuery(rawQuery, null);
        return kursor;
    }

    public Cursor showPlayersTournamentOrderByPunkty(){
        SQLiteDatabase db = getReadableDatabase();
        String rawQuery = "SELECT * FROM Zawodnicy INNER JOIN Turniej ON Zawodnicy.Player_ID = Turniej.Player_ID ORDER BY Punkty DESC";
        Cursor kursor = db.rawQuery(rawQuery, null);
        return kursor;
    }

    public Cursor showParametReferee(){
        String rawQuery = "SELECT * FROM Sędziowie";
        Cursor kursor = db.rawQuery(rawQuery, null);
        return kursor;
    }

    public boolean searchPlayer(Player player){
        Cursor k = showPlayer();
        while(k.moveToNext()) {
            if(k.getString(1).equals(player.getName()) && k.getString(2).equals(player.getSurname()) && k.getString(3).equals(player.getBirthdayDate())) return true;
        }
        return false;
    }

    public boolean updatePlayer(Player player){
        long id = player.getID();
        int idx = -1;
        Cursor k = showPlayer();
        while(k.moveToNext()) {
            int nr = k.getInt(0);
            if(nr == (int)id) {
                idx = (int)id;
            }
        }
        //todo
        return true;
    }

    public boolean updateTodo(int id, String col_name, Integer new_value) {
        ContentValues updateTodoValues = new ContentValues();
        updateTodoValues.put(col_name, new_value);
        db.update(Turniej_Name, updateTodoValues, "Player_ID=?",new String[]{String.valueOf(id)});
        return true;
    }

    public boolean setMatch(int id, String col_name, String new_value){
        ContentValues updateTodoValues = new ContentValues();
        updateTodoValues.put(col_name, new_value);
        db.update(Mecz_Name, updateTodoValues, "ID_Meczu=?",new String[]{String.valueOf(id)});
        return true;
    }

    public boolean setWinMatch(int id, int new_value){
        ContentValues updateTodoValues = new ContentValues();
        updateTodoValues.put("Player_ID_Win", new_value);
        db.update(Mecz_Name, updateTodoValues, "ID_Meczu=?",new String[]{String.valueOf(id)});
        return true;
    }

    public boolean setPlayer1(int id, int new_value){
        ContentValues updateTodoValues = new ContentValues();
        updateTodoValues.put("Player_ID1", new_value);
        db.update(Mecz_Name, updateTodoValues, "ID_Meczu=?",new String[]{String.valueOf(id)});
        return true;
    }

    public boolean setPlayer2(int id, int new_value){
        ContentValues updateTodoValues = new ContentValues();
        updateTodoValues.put("Player_ID2", new_value);
        db.update(Mecz_Name, updateTodoValues, "ID_Meczu=?",new String[]{String.valueOf(id)});
        return true;
    }

    public void deletePlayer(Player player){
        int id = player.getID();
        String where = "ID=" + id;
        db.delete(Osoby_Name, where, null);
        String where1 = "Player_ID=" + id;
        db.delete(Zawodnicy_Name, where1, null);
    }

    public void deletePlayer(int id){
        String where = "ID=" + id;
        db.delete(Osoby_Name, where, null);
        String where1 = "Player_ID=" + id;
        db.delete(Zawodnicy_Name, where1, null);
    }

    public void deleteReferee(int id){
        String where = "ID=" + id;
        db.delete(Osoby_Name, where, null);
        String where1 = "Referee_ID=" + id;
        db.delete(Sedziowie_Name, where1, null);
    }

    public Cursor showPlayerInTournament(){
        String rawQuery = "SELECT * FROM Turniej";
        Cursor kursor = db.rawQuery(rawQuery, null);
        return kursor;
    }

    public Cursor showPersonPlayerInTournament(){
        String rawQuery = "SELECT * FROM Osoby JOIN Zawodnicy ON Osoby.ID = Zawodnicy.Player_ID JOIN Turniej ON Osoby.ID = Turniej.Player_ID";
        Cursor kursor = db.rawQuery(rawQuery, null);
        return kursor;
    }

    public Cursor showPersonPlayerTournamentByParameter(String parameter){
        String rawQuery = "SELECT * FROM Osoby JOIN Zawodnicy ON Osoby.ID = Zawodnicy.Player_ID JOIN Turniej ON Zawodnicy.Player_ID = Turniej.Player_ID ORDER BY " + parameter;
        Cursor kursor = db.rawQuery(rawQuery, null);
        return kursor;
    }

    public boolean booleanPersonPlayerTournamentByParameter(String parameter){
        String rawQuery = "SELECT * FROM Osoby JOIN Zawodnicy ON Osoby.ID = Zawodnicy.Player_ID JOIN Turniej ON Zawodnicy.Player_ID = Turniej.Player_ID ORDER BY " + parameter;
        Cursor kursor = db.rawQuery(rawQuery, null);
        return true;
    }

    public Cursor showPersonPlayerTournamentByParameterNullEnd(String parameter){
        String rawQuery = "SELECT * FROM Osoby JOIN Zawodnicy ON Osoby.ID = Zawodnicy.Player_ID JOIN Turniej ON Zawodnicy.Player_ID = Turniej.Player_ID ORDER BY IFNULL(" + parameter + ", 9999) ";
        Cursor kursor = db.rawQuery(rawQuery, null);
        return kursor;
    }

    public Cursor showPersonPlayerTournamentByParameterDESC(String parameter){
        String rawQuery = "SELECT * FROM Osoby JOIN Zawodnicy ON Osoby.ID = Zawodnicy.Player_ID JOIN Turniej ON Zawodnicy.Player_ID = Turniej.Player_ID ORDER BY " + parameter + " DESC ";
        Cursor kursor = db.rawQuery(rawQuery, null);
        return kursor;
    }

    public Cursor showMatch(){
        String rawQuery = "SELECT * FROM " + Mecz_Name;
        Cursor kursor = db.rawQuery(rawQuery, null);
        return kursor;
    }

    public void addGuest(Guest guest) {
        //todo
    }

    public void loadMatch(int id){
        Cursor k = showMatch();
        k.moveToPosition(id);

        switch (id + 1) {
            case 1:
                setPlayer1(17, k.getInt(8));
                return;
            case 2:
                setPlayer2(17,k.getInt(8));
                return;
            case 3:
                setPlayer1(18, k.getInt(8));
                return;
            case 4:
                setPlayer2(18,k.getInt(8));
                return;
            case 5:
                setPlayer1(19, k.getInt(8));
                return;
            case 6:
                setPlayer2(19,k.getInt(8));
                return;
            case 7:
                setPlayer1(20, k.getInt(8));
                return;
            case 8:
                setPlayer2(20,k.getInt(8));
                return;
            case 9:
                setPlayer1(21, k.getInt(8));
                return;
            case 10:
                setPlayer2(21,k.getInt(8));
                return;
            case 11:
                setPlayer1(22, k.getInt(8));
                return;
            case 12:
                setPlayer2(22,k.getInt(8));
                return;
            case 13:
                setPlayer1(23, k.getInt(8));
                return;
            case 14:
                setPlayer2(23,k.getInt(8));
                return;
            case 15:
                setPlayer1(24, k.getInt(8));
                return;
            case 16:
                setPlayer2(24,k.getInt(8));
                return;
            case 17:
                setPlayer1(25, k.getInt(8));
                return;
            case 18:
                setPlayer2(25,k.getInt(8));
                return;
            case 19:
                setPlayer1(26, k.getInt(8));
                return;
            case 20:
                setPlayer2(26,k.getInt(8));
                return;
            case 21:
                setPlayer1(27, k.getInt(8));
                return;
            case 22:
                setPlayer2(27,k.getInt(8));
                return;
            case 23:
                setPlayer1(28, k.getInt(8));
                return;
            case 24:
                setPlayer2(28,k.getInt(8));
                return;
            case 25:
                setPlayer1(29, k.getInt(8));
                return;
            case 26:
                setPlayer2(29,k.getInt(8));
                return;
            case 27:
                setPlayer1(30, k.getInt(8));
                return;
            case 28:
                setPlayer2(30,k.getInt(8));
                return;
            case 29:
                setPlayer1(31, k.getInt(8));
                return;
            case 30:
                setPlayer2(31,k.getInt(8));
                return;

            default:
                return;
        }
    }

    void firstMatches(String[] players) {
        Cursor k = showMatch();
        int tmp = 0;
        while(tmp++ < 16 && k.moveToNext()){
            if(k.getInt(1) == -1 && k.getInt(2) != -1){
                setWinMatch(k.getInt(0), k.getInt(2));
            }
            else if(k.getInt(2) == -1 && k.getInt(1) != -1) {
                setWinMatch(k.getInt(0), k.getInt(1));
            }
            else{
                return;
            }
        }
    }

    public void loadMatches(){
        Cursor next = showMatch();
        Cursor k = showMatch();
        int i = 17;
        while(i < 31 && next.moveToPosition(i++) && k.moveToNext()) {
            setPlayer1(i, k.getInt(8));
            k.moveToNext();
            setPlayer2(i, k.getInt(8));
        }
    }

    public void deleteTournament() {
        db.delete(Turniej_Name, null, null);
        db.delete(Mecz_Name, null, null);
    }
}
