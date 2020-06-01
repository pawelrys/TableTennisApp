package IO.IO_project;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class sortTablePage extends AppCompatActivity {
    Button name;
    Button surname;
    Button sex;
    Button date;
    Button club;
    Button category;
    Button points;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sort_table_page);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true); //cofa

        name = findViewById(R.id.sortNameText);
        surname = findViewById(R.id.sortSurnameText);
        sex = findViewById(R.id.sortSex);
        date = findViewById(R.id.sortDate);
        club = findViewById(R.id.sortClub);
        category = findViewById(R.id.sortCategory);
        points = findViewById(R.id.sortPoints);

        TextView text = (TextView)findViewById(R.id.sortTextInfo);
        databaseAdmin db = databaseAdmin.getInstance(sortTablePage.this);


        text.setText("");
        Cursor k = db.showPlayer();
        while(k.moveToNext()){
            String imie = k.getString(1);
            String nazwisko = k.getString(2);
            String dataur = k.getString(3);
            String plec = k.getString(4);
            String klub = k.getString(6);
            String kategoria = k.getString(7);
            String punkty = k.getString(8);
            text.setText(text.getText() + "\n \n" + imie + " " + nazwisko + " " + dataur + " " + plec + " " + klub + " " + kategoria + " " + punkty);
        }
        k.close();


        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String parameter = "Imię";
                text.setText("");
                Cursor k = db.showPlayerByParameter(parameter);
                while(k.moveToNext()){
                    String imie = k.getString(1);
                    String nazwisko = k.getString(2);
                    String dataur = k.getString(3);
                    String plec = k.getString(4);
                    String klub = k.getString(6);
                    String kategoria = k.getString(7);
                    String punkty = k.getString(8);
                    text.setText(text.getText() + "\n \n" + imie + " " + nazwisko + " " + dataur + " " + plec + " " + klub + " " + kategoria + " " + punkty);
                }
                k.close();
            }
        });


        surname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String parameter = "Nazwisko";
                text.setText("");
                Cursor k = db.showPlayerByParameter(parameter);
                while(k.moveToNext()){
                    String imie = k.getString(1);
                    String nazwisko = k.getString(2);
                    String dataur = k.getString(3);
                    String plec = k.getString(4);
                    String klub = k.getString(6);
                    String kategoria = k.getString(7);
                    String punkty = k.getString(8);
                    text.setText(text.getText() + "\n \n" + imie + " " + nazwisko + " " + dataur + " " + plec + " " + klub + " " + kategoria + " " + punkty);
                }
                k.close();
            }
        });

        sex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String parameter = "Płeć";
                text.setText("");
                Cursor k = db.showPlayerByParameter(parameter);
                while(k.moveToNext()){
                    String imie = k.getString(1);
                    String nazwisko = k.getString(2);
                    String dataur = k.getString(3);
                    String plec = k.getString(4);
                    String klub = k.getString(6);
                    String kategoria = k.getString(7);
                    String punkty = k.getString(8);
                    text.setText(text.getText() + "\n \n" + imie + " " + nazwisko + " " + dataur + " " + plec + " " + klub + " " + kategoria + " " + punkty);
                }
                k.close();
            }
        });

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String parameter = "Data_Urodzenia";
                text.setText("");
                Cursor k = db.showPlayerByParameter(parameter);
                while(k.moveToNext()){
                    String imie = k.getString(1);
                    String nazwisko = k.getString(2);
                    String dataur = k.getString(3);
                    String plec = k.getString(4);
                    String klub = k.getString(6);
                    String kategoria = k.getString(7);
                    String punkty = k.getString(8);
                    text.setText(text.getText() + "\n \n" + imie + " " + nazwisko + " " + dataur + " " + plec + " " + klub + " " + kategoria + " " + punkty);
                }
                k.close();
            }
        });

        club.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String parameter = "Klub";
                text.setText("");
                Cursor k = db.showPlayerByParameter(parameter);
                while(k.moveToNext()){
                    String imie = k.getString(1);
                    String nazwisko = k.getString(2);
                    String dataur = k.getString(3);
                    String plec = k.getString(4);
                    String klub = k.getString(6);
                    String kategoria = k.getString(7);
                    String punkty = k.getString(8);
                    text.setText(text.getText() + "\n \n" + imie + " " + nazwisko + " " + dataur + " " + plec + " " + klub + " " + kategoria + " " + punkty);
                }
                k.close();
            }
        });

        category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String parameter = "Kategoria";
                text.setText("");
                Cursor k = db.showPlayerByParameter(parameter);
                while(k.moveToNext()){
                    String imie = k.getString(1);
                    String nazwisko = k.getString(2);
                    String dataur = k.getString(3);
                    String plec = k.getString(4);
                    String klub = k.getString(6);
                    String kategoria = k.getString(7);
                    String punkty = k.getString(8);
                    text.setText(text.getText() + "\n \n" + imie + " " + nazwisko + " " + dataur + " " + plec + " " + klub + " " + kategoria + " " + punkty);
                }
                k.close();
            }
        });

        points.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                text.setText("");
                Cursor k = db.showPlayerByParameterDESC();
                while(k.moveToNext()){
                    String imie = k.getString(1);
                    String nazwisko = k.getString(2);
                    String dataur = k.getString(3);
                    String plec = k.getString(4);
                    String klub = k.getString(6);
                    String kategoria = k.getString(7);
                    String punkty = k.getString(8);
                    text.setText(text.getText() + "\n \n" + imie + " " + nazwisko + " " + dataur + " " + plec + " " + klub + " " + kategoria + " " + punkty);
                }
                k.close();
            }
        });


    }
}
