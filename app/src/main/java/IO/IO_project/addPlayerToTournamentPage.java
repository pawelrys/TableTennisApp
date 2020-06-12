package IO.IO_project;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Objects;

public class addPlayerToTournamentPage extends AppCompatActivity {
    EditText name;
    EditText surname;
    EditText date;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_player_to_tournament_page);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true); //cofa

        name = findViewById(R.id.addPlayerToTournamentNameText);
        surname = findViewById(R.id.addPlayerToTournamentSurnameText);
        date = findViewById(R.id.addPlayerToTournamentDate);
        button = findViewById(R.id.addPlayerToTournamentPlayerButton);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                name.onEditorAction(EditorInfo.IME_ACTION_DONE);
                surname.onEditorAction(EditorInfo.IME_ACTION_DONE);
                date.onEditorAction(EditorInfo.IME_ACTION_DONE);
                button.onEditorAction(EditorInfo.IME_ACTION_DONE);

                databaseAdmin db = databaseAdmin.getInstance(addPlayerToTournamentPage.this);


                name = findViewById(R.id.addPlayerToTournamentNameText);
                surname = findViewById(R.id.addPlayerToTournamentSurnameText);
                date = findViewById(R.id.addPlayerToTournamentDate);

                String nameAddPlayerToTournament = name.getText().toString();
                String surnameAddPlayerToTournament = surname.getText().toString();
                String dateAddPlayerToTournament = date.getText().toString();

                SharedPreferences sharedPreferences = getSharedPreferences(Main.globalPreferenceName, MODE_PRIVATE);
                Main.setPlayer(sharedPreferences.getInt(Main.name_player, 0));
                Main.setPlayer_in_tournament(sharedPreferences.getInt(Main.name_playerInTournament, 0));
                Main.setLadder_tournament(sharedPreferences.getBoolean(Main.name_ladderTournament, false));

                if(Main.getPlayer_in_tournament() > 31){
                    Toast.makeText(getApplicationContext(), "Jest maksymalna liczba zawodników zgłoszonych do turnieju!", Toast.LENGTH_LONG).show();
                    return;
                }

                if(Main.isLadder_tournament()){
                    Toast.makeText(getApplicationContext(), "Turniej już się zaczął!", Toast.LENGTH_LONG).show();
                    return;
                }

                if(nameAddPlayerToTournament.isEmpty() || surnameAddPlayerToTournament.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Błędne dane!", Toast.LENGTH_LONG).show();
                    return;
                }

                if(dateAddPlayerToTournament.length() < 10) {
                    int idx = 0;
                    int i = -1;
                    Cursor k = db.showPlayer();
                    while (k.moveToNext()) {
                        if (k.getString(1).equals(nameAddPlayerToTournament) && k.getString(2).equals(surnameAddPlayerToTournament)) {
                            idx++;
                            i = k.getInt(0);
                        }
                    }
                    if (idx == 0) {
                        Toast.makeText(getApplicationContext(), "Nie ma takiego zawodnika!", Toast.LENGTH_LONG).show();
                        return;
                    }

                    if (idx > 1) {
                        Toast.makeText(getApplicationContext(), "Jest więcej zawodników o takim imieniu i nazwisku, podaj datę urodzenia!", Toast.LENGTH_LONG).show();
                        return;
                    }

                    if (idx == 1) {
                        Cursor l = db.showPlayerInTournament();
                        while (l.moveToNext()) {
                            int a = l.getInt(1);
                            if (i == a) {
                                Toast.makeText(getApplicationContext(), "Ten zawodnik jest już dodany do turnieju!", Toast.LENGTH_LONG).show();
                                return;
                            }
                        }
                        Main.addPlayer_in_tournament();
                        db.addPlayerToTournament(i);
                        Toast.makeText(getApplicationContext(), "Zawodnik został prawidłowo dodany do turnieju!", Toast.LENGTH_LONG).show();
                        name.setText("");
                        surname.setText("");
                        date.setText("");

                        SharedPreferences.Editor editor = getSharedPreferences(Main.globalPreferenceName, MODE_PRIVATE).edit();
                        editor.putInt(Main.name_playerInTournament, Main.getPlayer_in_tournament());
                        editor.apply();
                    }
                }
                else if (dateAddPlayerToTournament.length() == 10){
                    String data = dateAddPlayerToTournament.substring(0, 2) + "-" + dateAddPlayerToTournament.substring(3, 5) + "-" + dateAddPlayerToTournament.substring(6, 10);

                    int i = -1;
                    Cursor k = db.showPlayer();
                    while (k.moveToNext()) {
                        if (k.getString(1).equals(nameAddPlayerToTournament) && k.getString(2).equals(surnameAddPlayerToTournament) && k.getString(3).equals(data)) {
                            i = k.getInt(0);
                            Cursor l = db.showPlayerInTournament();
                            while (l.moveToNext()) {
                                int a = l.getInt(1);
                                if (i == a) {
                                    Toast.makeText(getApplicationContext(), "Ten zawodnik jest już dodany do turnieju!", Toast.LENGTH_LONG).show();
                                    return;
                                }
                            }
                            Main.addPlayer_in_tournament();
                            db.addPlayerToTournament(i);
                            Toast.makeText(getApplicationContext(), "Zawodnik został prawidłowo dodany do turnieju!", Toast.LENGTH_LONG).show();
                            name.setText("");
                            surname.setText("");
                            date.setText("");

                            Main.addPlayer_in_tournament();
                            SharedPreferences.Editor editor = getSharedPreferences(Main.globalPreferenceName, MODE_PRIVATE).edit();
                            editor.putInt(Main.name_playerInTournament, Main.getPlayer_in_tournament());
                            editor.apply();
                        }
                    }
                    Toast.makeText(getApplicationContext(), "Nie ma takiego zawodnika!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
