package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MatchActivity extends AppCompatActivity {
    private TextView homeText;
    private TextView awayText;
    private TextView homeScore;
    private TextView awayScore;
    private ImageView homelogo;
    private ImageView awaylogo;
    int home;
    int away;

    public static final String RESULT_KEY="result";

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
        homelogo = findViewById(R.id.home_logo);
        awaylogo = findViewById(R.id.away_logo);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            // TODO: display value here
            Bundle extra = getIntent().getExtras();
            Bitmap bmp = extra.getParcelable("homeImg");
            Bitmap bmp2 = extra.getParcelable("awayImg");

            homelogo.setImageBitmap(bmp);
            awaylogo.setImageBitmap(bmp2);
            homeText.setText(extras.getString(MainActivity.HOMETEAM_KEY));
            awayText.setText(extras.getString(MainActivity.AWAYTEAM_KEY));
            //receive img
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
    public void handleResult(View view) {
        String result = null;

        if(away > home){
            result = awayText.getText().toString();

        }else if(away < home){
            result = homeText.getText().toString();
        }else{
            result = "DRAW";
        }
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra(RESULT_KEY, result);
        startActivity(intent);
    }
}
