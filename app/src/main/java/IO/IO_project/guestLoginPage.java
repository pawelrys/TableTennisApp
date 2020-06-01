package IO.IO_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class guestLoginPage extends AppCompatActivity {
    TextView textView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guest_login_page);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true); //cofa

        databaseAdmin db = databaseAdmin.getInstance(guestLoginPage.this);

        textView = (TextView)findViewById(R.id.guestLoginText);
        button = (Button)findViewById(R.id.loginButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String tmp = textView.getText().toString();
                Guest guest = new Guest(1, tmp);
                db.addGuest(guest);
                Intent intent = new Intent(guestLoginPage.this, guestInterfacePage.class);
                startActivity(intent);
            }
        });
    }
}
