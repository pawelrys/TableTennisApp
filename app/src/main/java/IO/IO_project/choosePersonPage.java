package IO.IO_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import java.util.Objects;

public class choosePersonPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_person_page);
        Objects.requireNonNull(getSupportActionBar()).setTitle("choosePersonPage"); //ustawia nazwe nagłówka
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true); //cofa

        Button button1 = (Button) findViewById(R.id.refereeButton1);
        Button button2 = (Button) findViewById(R.id.guestButton);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(choosePersonPage.this, refereeLoginPage.class);
                startActivity(intent);
            }
        });


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(choosePersonPage.this, guestLoginPage.class);
                startActivity(intent);
            }
        });
    }
}
