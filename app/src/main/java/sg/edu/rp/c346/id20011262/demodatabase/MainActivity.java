package sg.edu.rp.c346.id20011262.demodatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    Button btn, btnGet;
    TextView tvResults;
    EditText etTask, etDate;
    ListView lv;
    ArrayList<task> al;
    ArrayAdapter<String> aa;

    boolean change = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btnInsert);
        btnGet = findViewById(R.id.btnGetTasks);
        tvResults = findViewById(R.id.tvResults);
        etTask = findViewById(R.id.editTextTask);
        etDate = findViewById(R.id.editTextDate);
        lv = findViewById(R.id.lv);

        DBHelper db = new DBHelper(MainActivity.this);
        al = db.getTasks(change);
        db.close();

        aa = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, al);
        lv.setAdapter(aa);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(MainActivity.this);
                dbh.insertTask(etTask.getText().toString(),etDate.getText().toString());
            }
        });

        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DBHelper dbh = new DBHelper(MainActivity.this);
                ArrayList<String> data = dbh.getTaskContent();

                String txt = "";
                for (int i=0; i<data.size(); i++) {
                    //Log.d("Database Content", i+". " + data.get(i));
                    txt += i + ". " + data.get(i) + "\n";
                }
                tvResults.setText(txt);

                DBHelper db2 = new DBHelper(MainActivity.this);
                al = db2.getTasks(change);
                db2.close();
                change = !change;
                aa = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, al);
                lv.setAdapter(aa);

                al.clear();
                al.addAll(dbh.getTasks(change));
                aa.notifyDataSetChanged();
            }
        });

    }
}