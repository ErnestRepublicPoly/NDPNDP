package sg.edu.rp.c346.ndpndp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnAdd, btnShowList;
    EditText etTitle, etSinger, etYear;
    RadioGroup rgStars;
    RadioButton rb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAdd = findViewById(R.id.buttonAdd);
        btnShowList = findViewById(R.id.buttonList);
        etTitle = findViewById(R.id.editTextTitle);
        etSinger = findViewById(R.id.editTextSinger);
        etYear = findViewById(R.id.editTextYear);
        rgStars = findViewById(R.id.RadioGroupStars);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etTitle.getText().toString();
                String singer = etSinger.getText().toString();
                int year = Integer.parseInt(etYear.getText().toString());
                int starsID = rgStars.getCheckedRadioButtonId();
                rb = findViewById(starsID);
                int stars = Integer.parseInt(rb.getText().toString());

                DBHelper dbh = new DBHelper(MainActivity.this);
                long inserted_id = dbh.insertSong(title, singer, year, stars);
                dbh.close();

                if (inserted_id != -1){
                    Toast.makeText(MainActivity.this, "Insert successful",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ShowSong.class);
                startActivityForResult(intent, 1);
            }
        });
    }
}
