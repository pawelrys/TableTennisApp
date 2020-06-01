package IO.IO_project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class mainRefereeInterfacePage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_referee_interface_page);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true); //cofa

        Button button = (Button)findViewById(R.id.createLadderButton);
        Button button1 = (Button) findViewById(R.id.restartTournamentButton);
        Button button2 = (Button) findViewById(R.id.classificationButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Main.getRandom_player() + Main.getSpace_player() < Main.getPlayer_in_tournament()){
                    Toast.makeText(getApplicationContext(), "Nie wszyscy zawodnicy mają nr startowe!", Toast.LENGTH_LONG).show();
                    return;
                }


                if(Main.getRandom_player() + Main.getSpace_player() > Main.getPlayer_in_tournament()){
                    Toast.makeText(getApplicationContext(), "Błąd wewnętrzny!", Toast.LENGTH_LONG).show();
                    return;
                }

                if(Main.isLadder_tournament()){
                    Toast.makeText(getApplicationContext(), "Drabinka jest już utworzona!", Toast.LENGTH_LONG).show();
                    return;
                }

                Intent intent = new Intent(mainRefereeInterfacePage.this, tournamentLadderPage.class);
                String [] zaw = new String[32];
                databaseAdmin db = databaseAdmin.getInstance(mainRefereeInterfacePage.this);

                Cursor k = db.showPersonPlayerTournamentByParameter("Nr_Startowy");
                Cursor cmd = db.showPersonPlayerTournamentByParameter("Nr_Startowy");
                cmd.moveToNext();
                int id = 0;
                while(k.moveToNext() && id < Main.getPlayer_in_tournament()){
                    if(id < 31){
                        cmd.moveToNext();
                    }
                    if(id % 2 == 0){
                        Match match = new Match(id, k.getInt(9), cmd.getInt(9));
                        db.addMatch(match);
                    }
                    String s1 = k.getString(1);
                    String s2 = k.getString(2);
                    String tmp = s1 + " " + s2;
                    zaw[id++] = tmp;
                }
                db.addRestMatch();
                tournamentLadderPage.players = zaw;
                Main.create = true;
                startActivity(intent);
            }
        });

        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(Main.sure == 0){
                    Toast.makeText(getApplicationContext(), "Jesteś pewny?", Toast.LENGTH_LONG).show();
                    Main.sure++;
                    return;
                }
                databaseAdmin db = databaseAdmin.getInstance(mainRefereeInterfacePage.this);
                db.deleteTournament();
                Main.setLadder_tournament(false);
                Main.create = false;
                SharedPreferences.Editor editor = getSharedPreferences(Main.globalPreferenceName, MODE_PRIVATE).edit();
                editor.putBoolean(Main.name_ladderTournament, false);
                editor.putInt(Main.name_playerInTournament, 0);
                editor.putInt(Main.name_endMatch, 0);
                editor.putInt(Main.name_spacePlayer, 0);
                editor.putInt(Main.name_randomPlayer, 0);
                editor.apply();
                Toast.makeText(getApplicationContext(), "Turniej został usunięty!", Toast.LENGTH_LONG).show();
                Main.sure = 0;
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences(Main.globalPreferenceName, MODE_PRIVATE);
                Main.setEnd_match(sharedPreferences.getInt(Main.name_endMatch, 0));

                if(Main.getAll_match() != Main.getEnd_match()){
                    Toast.makeText(getApplicationContext(), "Nie wszystkie mecze zostały zakończone!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(mainRefereeInterfacePage.this, classificationPage.class);
                startActivity(intent);
            }
        });

    }

    public void ladderButton(View view) {
        Intent intent = new Intent(this, tournamentLadderPage.class);
        startActivity(intent);
    }

    public void setMatchButton(View view) {
        Intent intent = new Intent(this, setMatchPage.class);
        startActivity(intent);
    }

    public void addPlayerButton(View view) {
        Intent intent = new Intent(this, addPlayerPage.class);
        startActivity(intent);
    }

    public void searchPlayerButton(View view) {
        Intent intent = new Intent(this, searchPlayerPage.class);
        startActivity(intent);
    }

    public void addPlayerToTournamentButton(View view) {
        Intent intent = new Intent(this, addPlayerToTournamentPage.class);
        startActivity(intent);
    }

    public void sortTableButton(View view) {
        Intent intent = new Intent(this, sortTablePage.class);
        startActivity(intent);
    }

    public void sortTableTournamentButton(View view) {
        Intent intent = new Intent(this, sortTableInTournamentPage.class);
        startActivity(intent);
    }

    public void spacePlayerButton(View view) {
        Intent intent = new Intent(this, spacePlayerPage.class);
        startActivity(intent);
    }

    public void randomPlayerButton(View view) {
        Intent intent = new Intent(this, randomPlayerPage.class);
        startActivity(intent);
    }
}
