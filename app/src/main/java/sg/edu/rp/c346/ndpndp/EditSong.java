package sg.edu.rp.c346.ndpndp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class EditSong extends AppCompatActivity {
    TextView tvID;
    EditText etTitleEdit, etYearEdit, etSingerEdit;
    RadioGroup rgStarsEdit;
    RadioButton rb;
    Button btnUpdate, btnDelete, btnCancel;
    Song data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_song);

        tvID = findViewById(R.id.textViewID);
        etTitleEdit = findViewById(R.id.editTextTitleEdit);
        etSingerEdit = findViewById(R.id.editTextSingerEdit);
        etYearEdit = findViewById(R.id.editTextYearEdit);
        btnUpdate = findViewById(R.id.buttonUpdate);
        btnDelete = findViewById(R.id.buttonDelete);
        rgStarsEdit = findViewById(R.id.rgStarsEdit);
        btnCancel = findViewById(R.id.buttonCancel);

        Intent i = getIntent();
        data = (Song) i.getSerializableExtra("data");

        tvID.setText("" + data.getId());
        etTitleEdit.setText(data.getTitle());
        etSingerEdit.setText(data.getSinger());
        etYearEdit.setText("" + data.getYear());

        if(data.getStars() == 1){
            rgStarsEdit.check(R.id.RadioButton1Edit);
        }else if(data.getStars() == 2){
            rgStarsEdit.check(R.id.RadioButton2Edit);
        }else if(data.getStars() == 3){
            rgStarsEdit.check(R.id.RadioButton3Edit);
        }else if(data.getStars() == 4){
            rgStarsEdit.check(R.id.RadioButton4Edit);
        }else if(data.getStars() == 5){
            rgStarsEdit.check(R.id.RadioButton5Edit);
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditSong.this);
                data.setTitle(etTitleEdit.getText().toString());
                data.setSinger(etSingerEdit.getText().toString());
                data.setYear(Integer.parseInt(etYearEdit.getText().toString()));
                int starsID = rgStarsEdit.getCheckedRadioButtonId();
                rb = findViewById(starsID);
                int stars = Integer.parseInt(rb.getText().toString());
                data.setStars(stars);
                dbh.updateSong(data);
                dbh.close();
                setResult(RESULT_OK);
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbh = new DBHelper(EditSong.this);
                dbh.deleteSong(data.getId());
                dbh.close();
                setResult(RESULT_OK);
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK);
                finish();
            }
        });
    }
}
