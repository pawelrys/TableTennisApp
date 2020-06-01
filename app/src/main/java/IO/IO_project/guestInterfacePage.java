package IO.IO_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Objects;

public class guestInterfacePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guest_interface_page);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true); //cofa


        Button button = (Button)findViewById(R.id.classificationButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Main.getAll_match() != Main.getEnd_match()){
                    Toast.makeText(getApplicationContext(), "Nie wszystkie mecze zostały zakończone!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(guestInterfacePage.this, classificationPage.class);
                startActivity(intent);
            }
        });
    }

    public void ladderButton(View view) {
        if(!Main.isLadder_tournament()){
            Toast.makeText(this, "Nie stworzono jeszcze drabinki!", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(this, tournamentLadderPage.class);
        startActivity(intent);
    }
}
