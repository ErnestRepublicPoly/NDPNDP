package sg.edu.rp.c346.ndpndp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ShowSong extends AppCompatActivity {
    Button btnFilter;
    ListView lv;
    ArrayAdapter aa;
    ArrayList<Song> Songs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_song);
        lv = findViewById(R.id.lv);
        btnFilter = findViewById(R.id.buttonFilter);
        Songs = new ArrayList<>();
        DBHelper db = new DBHelper(ShowSong.this);
        ArrayList<Song> data =  db.getAllSongs();
        for (int i = 0; i < data.size(); i++){
            Songs.add(data.get(i));
        }

        aa = new SongAdapter(this, R.layout.row, Songs);
        lv.setAdapter(aa);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long identity) {
                Song data = Songs.get(position);
                Intent i = new Intent(ShowSong.this,
                        EditSong.class);
                i.putExtra("data", data);
                startActivityForResult(i, 9);
            }
        });

        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Songs.clear();
                DBHelper db = new DBHelper(ShowSong.this);
                ArrayList<Song> data =  db.getAllSongs5Star();
                for (int i = 0; i < data.size(); i++){
                    Songs.add(data.get(i));
                }
                aa.notifyDataSetChanged();
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 9) {
                Songs.clear();
                DBHelper dbh = new DBHelper(ShowSong.this);
                Songs.addAll(dbh.getAllSongs());
                dbh.close();
                aa.notifyDataSetChanged();
            }
        }
    }
}
