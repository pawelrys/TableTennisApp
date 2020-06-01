package IO.IO_project;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class tournamentLadderPage extends AppCompatActivity {
    public static String [] players;
    TextView[] zaw = new TextView[64];
    private String [] text = new String[64];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tournament_ladder_page);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true); //cofa

        SharedPreferences sharedPreferences = getSharedPreferences(Main.globalPreferenceName, MODE_PRIVATE);
        Main.setLadder_tournament(sharedPreferences.getBoolean(Main.name_ladderTournament, false));

        zaw[1] = (TextView) findViewById(R.id.player1);
        zaw[2] = (TextView) findViewById(R.id.player2);
        zaw[3] = (TextView) findViewById(R.id.player3);
        zaw[4] = (TextView) findViewById(R.id.player4);
        zaw[5] = (TextView) findViewById(R.id.player5);
        zaw[6] = (TextView) findViewById(R.id.player6);
        zaw[7] = (TextView) findViewById(R.id.player7);
        zaw[8] = (TextView) findViewById(R.id.player8);
        zaw[9] = (TextView) findViewById(R.id.player9);
        zaw[10] = (TextView) findViewById(R.id.player10);
        zaw[11] = (TextView) findViewById(R.id.player11);
        zaw[12] = (TextView) findViewById(R.id.player12);
        zaw[13] = (TextView) findViewById(R.id.player13);
        zaw[14] = (TextView) findViewById(R.id.player14);
        zaw[15] = (TextView) findViewById(R.id.player15);
        zaw[16] = (TextView) findViewById(R.id.player16);
        zaw[17] = (TextView) findViewById(R.id.player17);
        zaw[18] = (TextView) findViewById(R.id.player18);
        zaw[19] = (TextView) findViewById(R.id.player19);
        zaw[20] = (TextView) findViewById(R.id.player20);
        zaw[21] = (TextView) findViewById(R.id.player21);
        zaw[22] = (TextView) findViewById(R.id.player22);
        zaw[23] = (TextView) findViewById(R.id.player23);
        zaw[24] = (TextView) findViewById(R.id.player24);
        zaw[25] = (TextView) findViewById(R.id.player25);
        zaw[26] = (TextView) findViewById(R.id.player26);
        zaw[27] = (TextView) findViewById(R.id.player27);
        zaw[28] = (TextView) findViewById(R.id.player28);
        zaw[29] = (TextView) findViewById(R.id.player29);
        zaw[30] = (TextView) findViewById(R.id.player30);
        zaw[31] = (TextView) findViewById(R.id.player31);
        zaw[32] = (TextView) findViewById(R.id.player32);
        zaw[33] = (TextView) findViewById(R.id.player1_1);
        zaw[34] = (TextView) findViewById(R.id.player2_1);
        zaw[35] = (TextView) findViewById(R.id.player3_1);
        zaw[36] = (TextView) findViewById(R.id.player4_1);
        zaw[37] = (TextView) findViewById(R.id.player5_1);
        zaw[38] = (TextView) findViewById(R.id.player6_1);
        zaw[39] = (TextView) findViewById(R.id.player7_1);
        zaw[40] = (TextView) findViewById(R.id.player8_1);
        zaw[41] = (TextView) findViewById(R.id.player9_1);
        zaw[42] = (TextView) findViewById(R.id.player10_1);
        zaw[43] = (TextView) findViewById(R.id.player11_1);
        zaw[44] = (TextView) findViewById(R.id.player12_1);
        zaw[45] = (TextView) findViewById(R.id.player13_1);
        zaw[46] = (TextView) findViewById(R.id.player14_1);
        zaw[47] = (TextView) findViewById(R.id.player15_1);
        zaw[48] = (TextView) findViewById(R.id.player16_1);
        zaw[49] = (TextView) findViewById(R.id.player1_2);
        zaw[50] = (TextView) findViewById(R.id.player2_2);
        zaw[51] = (TextView) findViewById(R.id.player3_2);
        zaw[52] = (TextView) findViewById(R.id.player4_2);
        zaw[53] = (TextView) findViewById(R.id.player5_2);
        zaw[54] = (TextView) findViewById(R.id.player6_2);
        zaw[55] = (TextView) findViewById(R.id.player7_2);
        zaw[56] = (TextView) findViewById(R.id.player8_2);
        zaw[57] = (TextView) findViewById(R.id.player1_3);
        zaw[58] = (TextView) findViewById(R.id.player2_3);
        zaw[59] = (TextView) findViewById(R.id.player3_3);
        zaw[60] = (TextView) findViewById(R.id.player4_3);
        zaw[61] = (TextView) findViewById(R.id.player1_4);
        zaw[62] = (TextView) findViewById(R.id.player1_4);
        zaw[63] = (TextView) findViewById(R.id.player1_5);

        if(Main.isLadder_tournament()) {
            loaddata();
            updateViews();
        }

        if(!Main.isLadder_tournament() && Main.create) {
            Main.setLadder_tournament(true);
            saveData();
            loaddata();
            updateViews();
        }
    }

    public boolean saveData() {
        SharedPreferences.Editor editor = getSharedPreferences(Main.globalPreferenceName, MODE_PRIVATE).edit();
        editor.putBoolean(Main.name_ladderTournament, true);
        editor.apply();
        loaddata();
        return true;
    }

    private void loaddata() {
        databaseAdmin db = databaseAdmin.getInstance(this);
        Cursor k = db.showMatch();
        int i = 0;
        Cursor cmd = db.showPersonPlayerInTournament();
        while(k.moveToNext()){
            String imie1 = "";
            String nazwisko1 = "";
            String imie2 = "";
            String nazwisko2 = "";
            int tmp1 = k.getInt(1);
            int tmp2 = k.getInt(2);
            if(tmp1 != 0){
                cmd.moveToPosition(tmp1 - 1);
                imie1 = cmd.getString(1);
                nazwisko1 = cmd.getString(2);
            }

            if(tmp2 != 0){
                cmd.moveToPosition(tmp2 - 1);
                imie2 = cmd.getString(1);
                nazwisko2 = cmd.getString(2);
            }
            text[i++] = imie1 + " " + nazwisko1;
            text[i++] = imie2 + " " + nazwisko2;
        }
        k.moveToLast();

        int tmp = k.getInt(8);
        if(tmp > 0) {
            cmd.moveToPosition(tmp - 1);
            text[62] = cmd.getString(1) + " " + cmd.getString(2);
        }
        SharedPreferences sharedPreferences = getSharedPreferences(Main.globalPreferenceName, MODE_PRIVATE);
        Main.setLadder_tournament(sharedPreferences.getBoolean(Main.name_ladderTournament, false));
    }

    private void updateViews() {
        for (int i = 0; i < 63; i++) {
            zaw[i + 1].setText(text[i]);
        }
    }
}
