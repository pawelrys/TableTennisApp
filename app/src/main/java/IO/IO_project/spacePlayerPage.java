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

public class spacePlayerPage extends AppCompatActivity {
    EditText spacingText;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spacing_player_page);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true); //cofa
        databaseAdmin db = databaseAdmin.getInstance(spacePlayerPage.this);

        spacingText = (EditText)findViewById(R.id.spacingText);
        button = (Button) findViewById(R.id.spacingButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String help = spacingText.getText().toString();
                int liczba = 0;
                if (!help.equals("")){
                    liczba=Integer.parseInt(help);
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
                int nr_startowy = Main.getSpace_player();

                while(k.moveToNext() && liczba > 0){
                    if(k.getString(6) == null){
                        db.updateTodo(k.getInt(5),"Nr_Startowy", staticFunction.returnStartNumber(nr_startowy));
                        liczba--;
                        nr_startowy++;
                    }
                }
                Main.setSpace_player(nr_startowy);

                spacingText.setText("");
                Main.setRandom_player(Main.getRandom_player() + liczba);
                SharedPreferences.Editor editor = getSharedPreferences(Main.globalPreferenceName, MODE_PRIVATE).edit();
                editor.putInt(Main.name_spacePlayer, Main.getSpace_player());
                editor.apply();


                if( liczba > 0) {
                    Toast.makeText(getApplicationContext(), "Błąd wewnętrzny!", Toast.LENGTH_LONG).show();
                }
                k.close();
            }
        });
    }


    public static boolean spacingPlayers(int liczba, int nr_startowy, Cursor k, databaseAdmin db) {
        while(k.moveToNext()){
            if(k.getString(6) == null){
                db.updateTodo(k.getInt(5),"Nr_Startowy", staticFunction.returnStartNumber(nr_startowy));
                liczba--;
                nr_startowy++;
            }
        }

        Main.setSpace_player(nr_startowy);
        Main.setRandom_player(Main.getRandom_player() + liczba);

        if( liczba > 0) {
            return false;
        }
        k.close();
        return true;
    }
}
