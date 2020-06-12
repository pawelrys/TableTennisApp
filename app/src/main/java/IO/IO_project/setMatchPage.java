package IO.IO_project;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class setMatchPage extends AppCompatActivity {
    EditText name1;
    EditText surname1;
    EditText name2;
    EditText surname2;
    EditText set1;
    EditText set2;
    EditText set3;
    EditText set4;
    EditText set5;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_match_page);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        button = (Button)findViewById(R.id.enterMatchButton1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name1 = (EditText) findViewById(R.id.matchFirstNameText);
                surname1 = (EditText) findViewById(R.id.matchFirstSurnameText);
                name2 = (EditText) findViewById(R.id.matchSecondNameText);
                surname2 = (EditText) findViewById(R.id.matchSecondSurnameText);
                set1 = (EditText) findViewById(R.id.matchSetFirstText);
                set2 = (EditText) findViewById(R.id.matchSetSecondText);
                set3 = (EditText) findViewById(R.id.matchSetThirdText);
                set4 = (EditText) findViewById(R.id.matchSetFourthText);
                set5 = (EditText) findViewById(R.id.matchSetFifthText);

                String Sname1 = name1.getText().toString();
                String Ssurname1 = surname1.getText().toString();
                String Sname2 = name2.getText().toString();
                String Ssurname2 = surname2.getText().toString();
                String Sset1 = set1.getText().toString();
                String Sset2 = set2.getText().toString();
                String Sset3 = set3.getText().toString();
                String Sset4 = set4.getText().toString();
                String Sset5 = set5.getText().toString();
                databaseAdmin db = databaseAdmin.getInstance(setMatchPage.this);

                if(Sname1.isEmpty() || Ssurname1.isEmpty() || Sname2.isEmpty() || Ssurname2.isEmpty() || Sset1.isEmpty() || Sset2.isEmpty() || Sset3.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Błędne dane!", Toast.LENGTH_LONG).show();
                    return;
                }


                if(staticFunction.isMatch(Sset1, Sset2, Sset3, Sset4, Sset5) == -1){
                    Toast.makeText(getApplicationContext(), "Błędne dane w setach meczu!", Toast.LENGTH_LONG).show();
                    return;
                }



                Cursor match = db.showMatch();
                Cursor player = db.showPersonPlayerInTournament();
                int id_player1 = -1;
                int id_player2 = -1;


                int tmp = 0;
                while(player.moveToNext()){
                    if(player.getString(1).equals(Sname1) && player.getString(2).equals(Ssurname1)){
                        id_player1 = player.getInt(9);
                        if(++tmp == 2) break;
                    }
                    if(player.getString(1).equals(Sname2) && player.getString(2).equals(Ssurname2)){
                        id_player2 = player.getInt(9);
                        if(++tmp == 2) break;
                    }
                }
                if(tmp != 2){
                    Toast.makeText(getApplicationContext(), "Nie znaleziono takich zawodników w bazie!", Toast.LENGTH_LONG).show();
                    return;
                }
                while(match.moveToNext()){

                    if((match.getInt(1) == id_player1 && match.getInt(2) == id_player2) || (match.getInt(1) == id_player2 && match.getInt(2) == id_player1)){
                        tmp = -1;
                        break;
                    }
                }

                if(tmp != -1){
                    Toast.makeText(getApplicationContext(), "Nie znaleziono meczu pomiędzy tymi zawodnikami!", Toast.LENGTH_LONG).show();
                    return;
                }

                String res1 = staticFunction.result(Sset1);
                String res2 = staticFunction.result(Sset2);
                String res3 = staticFunction.result(Sset3);
                String res4 = staticFunction.result(Sset4);
                String res5 = staticFunction.result(Sset5);

                if(res1.equals("error") || res2.equals("error") || res3.equals("error") || res4.equals("error") || res5.equals("error")){
                    Toast.makeText(getApplicationContext(), "Błąd wewnętrzny!", Toast.LENGTH_LONG).show();
                    return;
                }



                int win = staticFunction.isMatch(res1, res2, res3, res4, res5);

                db.setMatch(match.getInt(0), "Set_1", res1);
                db.setMatch(match.getInt(0), "Set_2", res2);
                db.setMatch(match.getInt(0), "Set_3", res3);
                db.setMatch(match.getInt(0), "Set_4", res4);
                db.setMatch(match.getInt(0), "Set_5", res5);
                if(win == 1) {
                    db.setWinMatch(match.getInt(0), id_player1);
                }
                if(win == 2) {
                    db.setWinMatch(match.getInt(0), id_player2);
                }
                Main.addEnd_match();
                db.loadMatch(match.getInt(0) - 1);
                Toast.makeText(getApplicationContext(), "Mecz dodany!", Toast.LENGTH_LONG).show();

                name1.setText("");
                name2.setText("");
                surname1.setText("");
                surname2.setText("");
                set1.setText("");
                set2.setText("");
                set3.setText("");
                set4.setText("");
                set5.setText("");

            }
        });
    }

    public static boolean setMatch(String Sname1, String Ssurname1, String Sname2, String Ssurname2, String Sset1, String Sset2, String Sset3, String Sset4, String Sset5, databaseAdmin db){
        if(Sname1.isEmpty() || Ssurname1.isEmpty() || Sname2.isEmpty() || Ssurname2.isEmpty() || Sset1.isEmpty() || Sset2.isEmpty() || Sset3.isEmpty()){
            return false;
        }


        if(staticFunction.isMatch(Sset1, Sset2, Sset3, Sset4, Sset5) == -1){
            return false;
        }

        Cursor match = db.showMatch();
        Cursor player = db.showPersonPlayerInTournament();
        int id_player1 = -1;
        int id_player2 = -1;


        int tmp = 0;
        while(player.moveToNext()){
            if(player.getString(1).equals(Sname1) && player.getString(2).equals(Ssurname1)){
                id_player1 = player.getInt(9);
                if(++tmp == 2) break;
            }
            if(player.getString(1).equals(Sname2) && player.getString(2).equals(Ssurname2)){
                id_player2 = player.getInt(9);
                if(++tmp == 2) break;
            }
        }
        if(tmp != 2){
            return false;
        }
        while(match.moveToNext()){

            if((match.getInt(1) == id_player1 && match.getInt(2) == id_player2) || (match.getInt(1) == id_player2 && match.getInt(2) == id_player1)){
                tmp = -1;
                break;
            }
        }

        if(tmp != -1){
            return false;
        }

        String res1 = staticFunction.result(Sset1);
        String res2 = staticFunction.result(Sset2);
        String res3 = staticFunction.result(Sset3);
        String res4 = staticFunction.result(Sset4);
        String res5 = staticFunction.result(Sset5);

        if(res1.equals("error") || res2.equals("error") || res3.equals("error") || res4.equals("error") || res5.equals("error")){
            return false;
        }

        return true;
    }
}
