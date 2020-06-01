package IO.IO_project;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class searchPlayerPage extends AppCompatActivity {
    EditText editText1;
    EditText editText2;
    EditText editText3;
    Button hide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_player_page);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true); //cofa

        editText1 = findViewById(R.id.searchNameText);
        editText2 = findViewById(R.id.searchSurnameText);
        editText3 = findViewById(R.id.searchDate);
        hide = findViewById(R.id.searchPlayerButton);

        hide.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                editText1.onEditorAction(EditorInfo.IME_ACTION_DONE);
                editText2.onEditorAction(EditorInfo.IME_ACTION_DONE);
                editText3.onEditorAction(EditorInfo.IME_ACTION_DONE);

                databaseAdmin db = databaseAdmin.getInstance(searchPlayerPage.this);

                EditText addNameText = findViewById(R.id.searchNameText);
                EditText addSurnameText = findViewById(R.id.searchSurnameText);
                EditText addDate = findViewById(R.id.searchDate);

                String name = addNameText.getText().toString();
                String surname = addSurnameText.getText().toString();
                String date = addDate.getText().toString();

                if(name.isEmpty() || surname.isEmpty() || date.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Błędne dane!", Toast.LENGTH_LONG).show();
                    return;
                }

                if(date.length() < 10){
                    Toast.makeText(getApplicationContext(), "Zły format daty!", Toast.LENGTH_LONG).show();
                    return;
                }

                String data = date.substring(0, 2) + "-" + date.substring(3, 5) + "-" + date.substring(6, 10);
                TextView v = (TextView)findViewById(R.id.returnPlayer);

                Cursor k = db.showPlayer();
                while(k.moveToNext()) {
                    if(k.getString(1).equals(name) && k.getString(2).equals(surname) && k.getString(3).equals(data)){
                        Toast.makeText(getApplicationContext(), "Znaleziono zawodnika!", Toast.LENGTH_LONG).show();
                        String imie = k.getString(1);
                        String nazwisko = k.getString(2);
                        String dataur = k.getString(3);
                        String plec = k.getString(4);
                        String klub = k.getString(5);
                        String kategoria = k.getString(6);
                        String punkty = k.getString(7);
                        v.setText(imie + " " + nazwisko + " " + dataur + " " + plec + " " + klub + " " + kategoria + " " + punkty);
                        return;
                    }
                }

                Toast.makeText(getApplicationContext(), "Nie ma takiego zawodnika w bazie!", Toast.LENGTH_LONG).show();

                v.setText("");
            }
        });
    }
}
