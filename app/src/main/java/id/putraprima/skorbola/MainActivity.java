package id.putraprima.skorbola;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    public static final String HOMETEAM_KEY = "hometeam";
    public static final String AWAYTEAM_KEY = "awayteam";

    private EditText hometeamInput;
    private EditText awayteamInput;

    private static final String TAG = MainActivity.class.getCanonicalName();
    private static final int GALLERY_REQUEST_CODEHOME = 1;
    private static final int GALLERY_REQUEST_CODEAWAY = 1;

    private ImageView homelogo;
    private ImageView awaylogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //TODO
        //Fitur Main Activity
        //1. Validasi Input Home Team
        hometeamInput = findViewById(R.id.home_team);

        //2. Validasi Input Away Team
        awayteamInput = findViewById(R.id.away_team);

    }

    //3. Ganti Logo Home Team
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_CANCELED) {
            return;
        }

        if (requestCode == GALLERY_REQUEST_CODEHOME) {
            if (data != null) {
                try {
                    Uri imageUri = data.getData();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                    homelogo.setImageBitmap(bitmap);
                } catch (IOException e) {
                    Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, e.getMessage());
                }
            }
        }

        if (requestCode == GALLERY_REQUEST_CODEAWAY) {
            if (data != null) {
                try {
                    Uri imageUri = data.getData();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                    awaylogo.setImageBitmap(bitmap);
                } catch (IOException e) {
                    Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, e.getMessage());
                }
            }
        }
    }
    public void handleHomeLogo(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, GALLERY_REQUEST_CODEHOME);
    }

    //4. Ganti Logo Away Team
    public void handleAwayLogo(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, GALLERY_REQUEST_CODEAWAY);
    }

    //5. Next Button Pindah Ke MatchActivity
    public void handleNext(View view) {

        String hometeam = hometeamInput.getText().toString();
        String awayteam = awayteamInput.getText().toString();

        Intent intent = new Intent(this, MatchActivity.class);

        intent.putExtra(HOMETEAM_KEY, hometeam);
        intent.putExtra(AWAYTEAM_KEY, awayteam);

        startActivity(intent);
    }



}
