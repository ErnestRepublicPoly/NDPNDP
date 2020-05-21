package sg.edu.rp.c346.ndpndp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class SongAdapter extends ArrayAdapter<Song> {
    Context context;
    ArrayList<Song> songs;
    int resource;
    ImageView iv1, iv2, iv3, iv4, iv5;
    TextView songTitle, songYear, songSinger;

    public SongAdapter(Context context, int resource, ArrayList<Song> songs) {
        super(context, resource, songs);
        this.context = context;
        this.songs = songs;
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.row, parent, false);
        songTitle = rowView.findViewById(R.id.textViewTitle);
        songSinger = rowView.findViewById(R.id.textViewSinger);
        songYear = rowView.findViewById(R.id.textViewYear);
        iv1 = rowView.findViewById(R.id.imageView1Star);
        iv2 = rowView.findViewById(R.id.imageView2Star);
        iv3 = rowView.findViewById(R.id.imageView3Star);
        iv4 = rowView.findViewById(R.id.imageView4Star);
        iv5 = rowView.findViewById(R.id.imageView5Star);

        Song currSong = songs.get(position);

        songTitle.setText(currSong.getTitle());
        songSinger.setText(currSong.getSinger());
        songYear.setText(""+currSong.getYear());
        int stars = currSong.getStars();

        if (stars >= 1) {
            iv1.setImageResource(android.R.drawable.btn_star_big_on);
        }
        if (stars >= 2) {
            iv2.setImageResource(android.R.drawable.btn_star_big_on);
        }
        if (stars >= 3) {
            iv3.setImageResource(android.R.drawable.btn_star_big_on);
        }
        if (stars >= 4) {
            iv4.setImageResource(android.R.drawable.btn_star_big_on);
        }
        if (stars >= 5) {
            iv5.setImageResource(android.R.drawable.btn_star_big_on);
        }

        return rowView;
    }
}
