package IO.IO_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Objects;

public class choosePersonPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_person_page);
        Objects.requireNonNull(getSupportActionBar()).setTitle("choosePersonPage"); //ustawia nazwe nagłówka
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true); //cofa
    }

    public void refereeButton(View view) {
        Intent intent = new Intent(this, refereeLoginPage.class);
        startActivity(intent);
    }

    public void guestButton(View view) {
        Intent intent = new Intent(this, guestLoginPage.class);
        startActivity(intent);
    }
}
