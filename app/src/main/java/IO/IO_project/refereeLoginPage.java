package IO.IO_project;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Objects;

public class refereeLoginPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.referee_login_page);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true); //cofa
    }

    public void loginReferee(View view) {
        databaseAdmin db = databaseAdmin.getInstance(refereeLoginPage.this);

        EditText login = findViewById(R.id.refereeLoginText);
        EditText password = findViewById(R.id.refereePasswordText);

        String loginText = login.getText().toString();
        String passwordText = password.getText().toString();

        Cursor k = db.showParametReferee();
        int nr_stolu = 0;
        int id = -1;
        while(k.moveToNext()) {
            if(k.getString(1).equals(loginText) && k.getString(2).equals(passwordText)){
                id = k.getInt(0);
                nr_stolu = k.getInt(4);
                break;
            }
        }
        if(id == -1){
            Toast.makeText(getApplicationContext(), "Błędne dane logowania!", Toast.LENGTH_LONG).show();
        }
        else{
            if(nr_stolu == 0){
                Intent intent = new Intent(this, mainRefereeInterfacePage.class);
                startActivity(intent);
            }
            else{
                Intent intent = new Intent(this, countingRefereeInterfacePage.class);
                startActivity(intent);
            }
        }
    }
}
