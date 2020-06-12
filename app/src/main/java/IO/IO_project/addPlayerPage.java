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

public class addPlayerPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_player_page);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true); //cofa

        Button button = (Button)findViewById(R.id.addPlayerButton1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseAdmin db = databaseAdmin.getInstance(addPlayerPage.this);


                EditText addNameText = findViewById(R.id.addNameText);
                EditText addSurnameText = findViewById(R.id.addSurnameText);
                EditText addSex = findViewById(R.id.addSex);
                EditText addDate = findViewById(R.id.addDate);
                EditText addClub = findViewById(R.id.addClub);
                EditText addCategory = findViewById(R.id.addCategory);
                EditText addPoints = findViewById(R.id.addPoints);


                String name = addNameText.getText().toString();
                String surname = addSurnameText.getText().toString();
                String sex = addSex.getText().toString();
                String date = addDate.getText().toString();
                String club = addClub.getText().toString();
                String category = addCategory.getText().toString();
                String points = addPoints.getText().toString();


                if(name.isEmpty() || surname.isEmpty() || sex.isEmpty() || date.isEmpty() || club.isEmpty() || category.isEmpty() || points.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Błędne dane!", Toast.LENGTH_LONG).show();
                    return;
                }

                if(date.length() < 10){
                    Toast.makeText(getApplicationContext(), "Zły format daty!", Toast.LENGTH_LONG).show();
                    return;
                }
                String data1 = date.substring(6,10);
                if(data1.charAt(0) > '2') return;
                if (data1.charAt(0) == '2'){
                    if(data1.charAt(1) > '0') return;
                    if(data1.charAt(2) > '2') return ;
                    if(data1.charAt(2) == '2' && data1.charAt(3) > '0') return;
                }

                String data = date.substring(0, 2) + "-" + date.substring(3, 5) + "-" + date.substring(6, 10);

                Cursor k = db.showPerson();
                while(k.moveToNext()) {
                    if(k.getString(1).equals(name) && k.getString(2).equals(surname) && k.getString(3).equals(data)){
                        Toast.makeText(getApplicationContext(), "Taki zawodnik już istnieje w bazie!", Toast.LENGTH_LONG).show();
                        return;
                    }
                }


                String m = "z";

                if (sex.charAt(0) == 'M' || sex.charAt(0) == 'm') m ="M";
                else if (sex.charAt(0) == 'K' || sex.charAt(0) == 'k') m ="K";
                else {
                    Toast.makeText(getApplicationContext(), "Taka płeć nie istnieje!", Toast.LENGTH_LONG).show();
                    return;
                }

                int point = Integer.parseInt(points);



                Player player = new Player(name, surname, m, data, club, category, point);

                db.addPlayer(player);
                Toast.makeText(getApplicationContext(), "Prawidłowo dodano zawodnika!", Toast.LENGTH_LONG).show();

                Main.addPlayer();
                SharedPreferences.Editor editor = getSharedPreferences(Main.globalPreferenceName, MODE_PRIVATE).edit();
                editor.putInt(Main.name_player, Main.getPlayer());
                editor.apply();

                addNameText.setText("");
                addSurnameText.setText("");
                addSex.setText("");
                addDate.setText("");
                addClub.setText("");
                addCategory.setText("");
                addPoints.setText("");

            }
        });
    }
}