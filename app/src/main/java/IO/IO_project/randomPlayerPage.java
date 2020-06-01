package IO.IO_project;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class randomPlayerPage extends AppCompatActivity {
    EditText randomText;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.random_player_page);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true); //cofa

        databaseAdmin db = databaseAdmin.getInstance(randomPlayerPage.this);

        randomText = (EditText)findViewById(R.id.randomText);
        button = (Button) findViewById(R.id.randomButton);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String help = randomText.getText().toString();
                int liczba = 0;
                if (!help.equals("")){
                    liczba = Integer.parseInt(help);
                }

                if(liczba == 0){
                    Toast.makeText(getApplicationContext(), "Liczba = 0", Toast.LENGTH_LONG).show();
                    return;
                }

                if(Main.getRandom_player() + Main.getSpace_player() + liczba > Main.getPlayer_in_tournament()){
                    Toast.makeText(getApplicationContext(), "Nie ma tylu wolnych zawodników w turnieju!", Toast.LENGTH_LONG).show();
                    return;
                }

                Cursor k = db.showPlayersTournamentOrderByPunkty();
                int nr_startowy = Main.getPlayer_in_tournament() - Main.getRandom_player();
                boolean tab [] = new boolean[nr_startowy + 1];
                int idx = 0;
                while(idx < liczba){
                    k.moveToPosition(staticFunction.random(Main.getPlayer_in_tournament()));
                    if(k.getString(6) == null && !tab[k.getInt(5)]){
                        db.updateTodo(k.getInt(5),"Nr_Startowy", nr_startowy - idx);
                        tab[k.getInt(5)] = true;
                        idx++;
                    }
                }
                Main.setRandom_player(Main.getRandom_player() + liczba);
                SharedPreferences.Editor editor = getSharedPreferences(Main.globalPreferenceName, MODE_PRIVATE).edit();
                editor.putInt(Main.name_randomPlayer, Main.getRandom_player());
                editor.apply();


                if(idx != liczba) {
                    Toast.makeText(getApplicationContext(), "Błąd wewnętrzny!", Toast.LENGTH_LONG).show();
                }
                k.close();
            }
        });
    }

    public static boolean randomPlayer(int liczba, int nr_startowy, Cursor k, databaseAdmin db){
        int idx = 0;
        while(k.moveToNext()){
            if(k.getString(6) == null){
                db.updateTodo(k.getInt(5),"Nr_Startowy", nr_startowy - idx);
                idx++;
            }
        }
        if(idx != liczba) {
            return false;
        }
        k.close();
        return true;
    }
}
