package IO.IO_project;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class sortTableInTournamentPage extends AppCompatActivity {
    Button name;
    Button surname;
    Button club;
    Button points;
    Button nr;
    Button place;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sort_table_in_tournament_page);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        name = findViewById(R.id.sortTournamentNameText);
        surname = findViewById(R.id.sortTournamentSurnameText);
        club = findViewById(R.id.sortTournamentClub);
        points = findViewById(R.id.sortTournamentPoint);
        nr = findViewById(R.id.sortTournamentNr);
        place = findViewById(R.id.sortTournamentPlace);


        TextView text = (TextView)findViewById(R.id.sortTournamentTextInfo);
        databaseAdmin db = databaseAdmin.getInstance(sortTableInTournamentPage.this);

        int idx = 0;
        text.setText("");
        Cursor k = db.showPersonPlayerInTournament();
        while(k.moveToNext()){
            if(k.getString(1).equals("Ignacy")) idx = k.getInt(0);
            String imie = k.getString(1);
            String nazwisko = k.getString(2);
            String klub = k.getString(6);
            String punkty = k.getString(8);
            String nr_start = k.getString(11);
            String miejsce = k.getString(12);
            text.setText(text.getText() + "\n \n" + imie + " " + nazwisko + " " + klub + " " + punkty + " " + nr_start + " " + miejsce);
        }
        k.close();


        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String parameter = "Imię";
                text.setText("");
                Cursor k = db.showPersonPlayerTournamentByParameter(parameter);
                while(k.moveToNext()){
                    String imie = k.getString(1);
                    String nazwisko = k.getString(2);
                    String klub = k.getString(6);
                    String punkty = k.getString(8);
                    String nr_start = k.getString(11);
                    String miejsce = k.getString(12);
                    text.setText(text.getText() + "\n \n" + imie + " " + nazwisko + " " + klub + " " + punkty + " " + nr_start + " " + miejsce);
                }
                k.close();
            }
        });


        surname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String parameter = "Nazwisko";
                text.setText("");
                Cursor k = db.showPersonPlayerTournamentByParameter(parameter);
                while(k.moveToNext()){
                    String imie = k.getString(1);
                    String nazwisko = k.getString(2);
                    String klub = k.getString(6);
                    String punkty = k.getString(8);
                    String nr_start = k.getString(11);
                    String miejsce = k.getString(12);
                    text.setText(text.getText() + "\n \n" + imie + " " + nazwisko + " " + klub + " " + punkty + " " + nr_start + " " + miejsce);
                }
                k.close();
            }
        });

        club.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String parameter = "Klub";
                text.setText("");
                Cursor k = db.showPersonPlayerTournamentByParameter(parameter);
                while(k.moveToNext()){
                    String imie = k.getString(1);
                    String nazwisko = k.getString(2);
                    String klub = k.getString(6);
                    String punkty = k.getString(8);
                    String nr_start = k.getString(11);
                    String miejsce = k.getString(12);
                    text.setText(text.getText() + "\n \n" + imie + " " + nazwisko + " " + klub + " " + punkty + " " + nr_start + " " + miejsce);
                }
                k.close();
            }
        });

        points.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                text.setText("");
                Cursor k = db.showPersonPlayerTournamentByParameterDESC("Punkty");
                while(k.moveToNext()){
                    String imie = k.getString(1);
                    String nazwisko = k.getString(2);
                    String klub = k.getString(6);
                    String punkty = k.getString(8);
                    String nr_start = k.getString(11);
                    String miejsce = k.getString(12);
                    text.setText(text.getText() + "\n \n" + imie + " " + nazwisko + " " + klub + " " + punkty + " " + nr_start + " " + miejsce);
                }
                k.close();
            }
        });

        nr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String parameter = "Nr_Startowy";
                text.setText("");
                Cursor k = db.showPersonPlayerTournamentByParameterNullEnd(parameter);
                while(k.moveToNext()){
                    String imie = k.getString(1);
                    String nazwisko = k.getString(2);
                    String klub = k.getString(6);
                    String punkty = k.getString(8);
                    String nr_start = k.getString(11);
                    String miejsce = k.getString(12);
                    text.setText(text.getText() + "\n \n" + imie + " " + nazwisko + " " + klub + " " + punkty + " " + nr_start + " " + miejsce);
                }
                k.close();
            }
        });

        place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String parameter = "Miejsce";
                text.setText("");
                Cursor k = db.showPersonPlayerTournamentByParameterNullEnd(parameter);
                while(k.moveToNext()){
                    String imie = k.getString(1);
                    String nazwisko = k.getString(2);
                    String klub = k.getString(6);
                    String punkty = k.getString(8);
                    String nr_start = k.getString(11);
                    String miejsce = k.getString(12);
                    text.setText(text.getText() + "\n \n" + imie + " " + nazwisko + " " + klub + " " + punkty + " " + nr_start + " " + miejsce);
                }
                k.close();
            }
        });


    }
}
