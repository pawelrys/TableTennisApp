package IO.IO_project;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class classificationPage extends AppCompatActivity{
    TextView t1, t2, t3, t4, t5;
    TableLayout table;
    TableRow tr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classification_page);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true); //cofa
        t1 = (TextView)findViewById(R.id.miejsce);
        t2 = (TextView)findViewById(R.id.imie);
        t3 = (TextView)findViewById(R.id.nazwisko);
        t4 = (TextView)findViewById(R.id.nr);
        t5 = (TextView)findViewById(R.id.punkty);
        table = (TableLayout)findViewById(R.id.table);
        databaseAdmin db = databaseAdmin.getInstance(classificationPage.this);

        Cursor k = db.showPersonPlayerTournamentByParameterDESC("Miejsce");
        while(k.moveToNext()){
            tr = new TableRow(this);
            int a = 25;
            tr.setId(a);
            tr.setBackgroundColor(Color.BLACK);
            TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            layoutParams.setMargins(2,2, 2, 2);
            tr.setLayoutParams(layoutParams);

            int z1 = 20;

            TextView te1 = new TextView(this);

            te1.setId(z1++);
            te1.setText(String.valueOf(k.getInt(12)));
            te1.setTextColor(Color.BLACK);          // part2
            te1.setBackgroundColor(Color.WHITE);
            te1.setGravity(Gravity.CENTER);
            te1.setHeight(100);
            tr.addView(te1, layoutParams);

            TextView te2 = new TextView(this);
            te2.setId(z1++);
            te2.setText(k.getString(1));
            te2.setTextColor(Color.BLACK);
            te2.setBackgroundColor(Color.WHITE);
            te2.setGravity(Gravity.CENTER);
            te2.setHeight(100);
            tr.addView(te2, layoutParams);

            TextView te3 = new TextView(this);
            te3.setId(z1++);
            te3.setText(k.getString(2));
            te3.setTextColor(Color.BLACK);
            te3.setBackgroundColor(Color.WHITE);
            te3.setGravity(Gravity.CENTER);
            te3.setHeight(100);
            tr.addView(te3, layoutParams);

            TextView te4 = new TextView(this);
            te4.setId(z1++);
            te4.setText(String.valueOf(k.getInt(11)));
            te4.setTextColor(Color.BLACK);
            te4.setBackgroundColor(Color.WHITE);
            te4.setGravity(Gravity.CENTER);
            te4.setHeight(100);
            tr.addView(te4, layoutParams);

            TextView te5 = new TextView(this);
            te5.setId(z1++);
            te5.setText(String.valueOf(staticFunction.getPoints(k.getInt(12))));
            te5.setTextColor(Color.BLACK);
            te5.setBackgroundColor(Color.WHITE);
            te5.setGravity(Gravity.CENTER);
            te5.setHeight(100);
            tr.addView(te5, layoutParams);
            table.addView(tr, new TableLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }
    }
}
