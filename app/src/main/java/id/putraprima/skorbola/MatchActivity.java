package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MatchActivity extends AppCompatActivity {
    private TextView homeText;
    private TextView awayText;
    private TextView homeScore;
    private TextView awayScore;
    int home;
    int away;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);
        //TODO
        //1.Menampilkan detail match sesuai data dari main activity
        homeText = findViewById(R.id.txt_home);
        awayText = findViewById(R.id.txt_away);
        homeScore = findViewById(R.id.score_home);
        awayScore = findViewById(R.id.score_away);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            // TODO: display value here
            String hometeam = extras.getString(MainActivity.HOMETEAM_KEY);
            homeText.setText(hometeam);

            String awayteam = extras.getString(MainActivity.AWAYTEAM_KEY);
            awayText.setText(awayteam);
        }
    }

    //2.Tombol add score menambahkan satu angka dari angka 0, setiap kali di tekan
    public void handleAddHome(View view) {
        home = Integer.parseInt(homeScore.getText().toString());
        home++;
        homeScore.setText(""+home);
    }

    public void handleAddAway(View view) {
        away = Integer.parseInt(awayScore.getText().toString());
        away++;
        awayScore.setText(""+away);
    }

    //3.Tombol Cek Result menghitung pemenang dari kedua tim dan mengirim nama pemenang ke ResultActivity, jika seri di kirim text "Draw"
}
