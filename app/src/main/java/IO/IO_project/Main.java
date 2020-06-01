package IO.IO_project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Objects;

public class Main extends AppCompatActivity {
    public static String globalPreferenceName = "preference";
    private static int player = 0;
    private static int player_in_tournament = 0;
    private static int space_player = 0;
    private static int random_player = 0;
    private static boolean ladder_tournament;
    private static final int all_match = 31;
    private static int end_match = 0;
    static final String tournament_date = "31-06-2020r.";
    static boolean create = false;
    static int sure = 0;


    public static final String name_player = "player";         //ile zawodników
    public static final String name_playerInTournament = "playerInTournament";  //ile zawodników zgłoszonych do turnieju
    public static final String name_spacePlayer = "spcePlayer"; //ile zawodników rozstawionych w turnieju
    public static final String name_randomPlayer = "randomPlayer";  //ile zawodników rozlosowanych
    public static final String name_ladderTournament = "ladder";    //czy stworzona drabinka turniejowa
    public static final String name_endMatch = "endMatch";         //ile meczy zakończonych


    public static int getPlayer() {
        return player;
    }

    public static void setPlayer(int players) {
        player = players;
    }

    public static void addPlayer() { player++; }

    public static int getPlayer_in_tournament() {
        return player_in_tournament;
    }

    public static void setPlayer_in_tournament(int player) {
        player_in_tournament = player;
    }

    public static void addPlayer_in_tournament() { player_in_tournament++; }

    public static int getSpace_player() {
        return space_player;
    }

    public static void setSpace_player(int space_player) {
        Main.space_player = space_player;
    }

    public static int getRandom_player() {
        return random_player;
    }

    public static void setRandom_player(int random_player) {
        Main.random_player = random_player;
    }

    public static boolean isLadder_tournament() {
        return ladder_tournament;
    }

    public static void setLadder_tournament(boolean ladder_tournament) { Main.ladder_tournament = ladder_tournament; }

    public static int getEnd_match() {
        return end_match;
    }

    public static void setEnd_match(int end_match) {
        Main.end_match = end_match;
    }

    public static void addEnd_match() { end_match++; }

    public static int getAll_match() {
        return all_match;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.start_page);
        databaseAdmin db = databaseAdmin.getInstance(Main.this);

        TextView v = (TextView) findViewById(R.id.textView1);

        Button button = (Button) findViewById(R.id.start);

        SharedPreferences sharedPreferences = getSharedPreferences(globalPreferenceName, MODE_PRIVATE);
        player = sharedPreferences.getInt(name_player, 0);
        player_in_tournament = sharedPreferences.getInt(name_playerInTournament, 0);
        space_player = sharedPreferences.getInt(name_spacePlayer, 0);
        random_player = sharedPreferences.getInt(name_randomPlayer, 0);
        ladder_tournament = sharedPreferences.getBoolean(name_ladderTournament, false);
        end_match = sharedPreferences.getInt(name_endMatch, 0);
//        startAdd();

        v.setText("");

        Main.setPlayer(32);
        Main.setPlayer_in_tournament(32);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main.this, choosePersonPage.class);
                startActivity(intent);
            }
        });
    }

    void startAdd(){
        databaseAdmin db = databaseAdmin.getInstance(Main.this);

        Player p1 = new Player("Jacek", "Kruk1", "M", "01-01-1999", "ULKS Moszczenica", "Senior", 1);
        Player p2 = new Player("Tomasz", "Kruk2", "M", "01-01-1999", "ULKS Moszczenica", "Senior", 2);
        Player p3 = new Player("Marcin", "Kruk3", "M", "01-01-1999", "ULKS Moszczenica", "Senior", 3);
        Player p4 = new Player("Dawid", "Kruk4", "M", "01-01-1999", "ULKS Moszczenica", "Senior", 4);
        Player p5 = new Player("Paweł", "Kruk5", "M", "01-01-1999", "ULKS Moszczenica", "Senior", 5);
        Player p6 = new Player("Damian", "Kruk6", "M", "01-01-1999", "ULKS Moszczenica", "Senior", 6);
        Player p7 = new Player("Eryk", "Kruk7", "M", "01-01-1999", "ULKS Moszczenica", "Senior", 7);
        Player p8 = new Player("Jakub", "Kruk8", "M", "01-01-1999", "ULKS Moszczenica", "Senior", 8);
        Player p9 = new Player("Jarosław", "Kruk9", "M", "01-01-1999", "ULKS Moszczenica", "Senior", 9);
        Player p10 = new Player("Patryk", "Kruk10", "M", "01-01-1999", "ULKS Moszczenica", "Senior", 10);
        Player p11 = new Player("Aleksander", "Kruk11", "M", "01-01-1999", "ULKS Moszczenica", "Senior", 32);
        Player p12 = new Player("Igor", "Kruk12", "M", "01-01-1999", "ULKS Moszczenica", "Senior", 31);
        Player p13 = new Player("Hubert", "Kruk13", "M", "01-01-1999", "ULKS Moszczenica", "Senior", 30);
        Player p14 = new Player("Arkadiusz", "Kruk14", "M", "01-01-1999", "ULKS Moszczenica", "Senior", 29);
        Player p15 = new Player("Łukasz", "Kruk15", "M", "01-01-1999", "ULKS Moszczenica", "Senior", 28);
        Player p16 = new Player("Antoni", "Kruk16", "M", "01-01-1999", "ULKS Moszczenica", "Senior", 27);
        Player p17 = new Player("Jan", "Kruk17", "M", "01-01-1999", "ULKS Moszczenica", "Senior", 26);
        Player p18 = new Player("Szymon", "Kruk18", "M", "01-01-1999", "ULKS Moszczenica", "Senior", 11);
        Player p19 = new Player("Franciszek", "Kruk19", "M", "01-01-1999", "ULKS Moszczenica", "Senior", 12);
        Player p20 = new Player("Filip", "Kruk20", "M", "01-01-1999", "ULKS Moszczenica", "Senior", 13);
        Player p21 = new Player("Mikołaj", "Kruk21", "M", "01-01-1999", "ULKS Moszczenica", "Senior", 14);
        Player p22 = new Player("Mateusz", "Kruk22", "M", "01-01-1999", "ULKS Moszczenica", "Senior", 15);
        Player p23 = new Player("Bartek", "Kruk23", "M", "01-01-1999", "ULKS Moszczenica", "Senior", 16);
        Player p24 = new Player("Kacper", "Kruk24", "M", "01-01-1999", "ULKS Moszczenica", "Senior", 17);
        Player p25 = new Player("Stanisław", "Kruk25", "M", "01-01-1999", "ULKS Moszczenica", "Senior", 25);
        Player p26 = new Player("Piotrek", "Kruk26", "M", "01-01-1999", "ULKS Moszczenica", "Senior", 24);
        Player p27 = new Player("Leon", "Kruk27", "M", "01-01-1999", "ULKS Moszczenica", "Senior", 23);
        Player p28 = new Player("Marcel", "Kruk28", "M", "01-01-1999", "ULKS Moszczenica", "Senior", 22);
        Player p29 = new Player("Michał", "Kruk29", "M", "01-01-1999", "ULKS Moszczenica", "Senior", 18);
        Player p30 = new Player("Nikodem", "Kruk30", "M", "01-01-1999", "ULKS Moszczenica", "Senior", 19);
        Player p31 = new Player("Tymon", "Kruk31", "M", "01-01-1999", "ULKS Moszczenica", "Senior", 20);
        Player p32 = new Player("Ignacy", "Kruk32", "M", "01-01-1999", "ULKS Moszczenica", "Senior", 21);

        db.addPlayer(p1);
        db.addPlayer(p2);
        db.addPlayer(p3);
        db.addPlayer(p4);
        db.addPlayer(p5);
        db.addPlayer(p6);
        db.addPlayer(p7);
        db.addPlayer(p8);
        db.addPlayer(p9);
        db.addPlayer(p10);
        db.addPlayer(p11);
        db.addPlayer(p12);
        db.addPlayer(p13);
        db.addPlayer(p14);
        db.addPlayer(p15);
        db.addPlayer(p16);
        db.addPlayer(p17);
        db.addPlayer(p18);
        db.addPlayer(p19);
        db.addPlayer(p20);
        db.addPlayer(p21);
        db.addPlayer(p22);
        db.addPlayer(p23);
        db.addPlayer(p24);
        db.addPlayer(p25);
        db.addPlayer(p26);
        db.addPlayer(p27);
        db.addPlayer(p28);
        db.addPlayer(p29);
        db.addPlayer(p30);
        db.addPlayer(p31);
        db.addPlayer(p32);

        db.addPlayerToTournament(p1);
        db.addPlayerToTournament(p2);
        db.addPlayerToTournament(p3);
        db.addPlayerToTournament(p4);
        db.addPlayerToTournament(p5);
        db.addPlayerToTournament(p6);
        db.addPlayerToTournament(p7);
        db.addPlayerToTournament(p8);
        db.addPlayerToTournament(p9);
        db.addPlayerToTournament(p10);
        db.addPlayerToTournament(p11);
        db.addPlayerToTournament(p12);
        db.addPlayerToTournament(p13);
        db.addPlayerToTournament(p14);
        db.addPlayerToTournament(p15);
        db.addPlayerToTournament(p16);
        db.addPlayerToTournament(p17);
        db.addPlayerToTournament(p18);
        db.addPlayerToTournament(p19);
        db.addPlayerToTournament(p20);
        db.addPlayerToTournament(p21);
        db.addPlayerToTournament(p22);
        db.addPlayerToTournament(p23);
        db.addPlayerToTournament(p24);
        db.addPlayerToTournament(p25);
        db.addPlayerToTournament(p26);
        db.addPlayerToTournament(p27);
        db.addPlayerToTournament(p28);
        db.addPlayerToTournament(p29);
        db.addPlayerToTournament(p30);
        db.addPlayerToTournament(p31);
        db.addPlayerToTournament(p32);

        db.addMainReferee(new MainReferee("Jacek", "Kruk", "M", "01-01-1999", "admin", "fajny", "admin"));
        db.addCountingReferee(new CountingReferee("Tomasz", "KrukCountingReferee", "M", "01-01-1998", "ab", 4, "admin"));
    }
}



