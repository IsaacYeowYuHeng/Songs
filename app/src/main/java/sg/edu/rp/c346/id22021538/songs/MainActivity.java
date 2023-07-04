package sg.edu.rp.c346.id22021538.songs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText titleEditText;
    private EditText singersEditText;
    private EditText yearEditText;
    private RadioGroup starsRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titleEditText = findViewById(R.id.titleEditText);
        singersEditText = findViewById(R.id.singersEditText);
        yearEditText = findViewById(R.id.yearEditText);
        starsRadioGroup = findViewById(R.id.starsRadioGroup);

        starsRadioGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = titleEditText.getText().toString();
                String singers = singersEditText.getText().toString();
                int year = Integer.parseInt(yearEditText.getText().toString());
                int stars = starsRadioGroup.getCheckedRadioButtonId() == R.id.one_star ? 1 :
                        starsRadioGroup.getCheckedRadioButtonId() == R.id.two_stars ? 2 :
                                starsRadioGroup.getCheckedRadioButtonId() == R.id.three_stars ? 3 :
                                        starsRadioGroup.getCheckedRadioButtonId() == R.id.four_stars ? 4 :
                                                starsRadioGroup.getCheckedRadioButtonId() == R.id.five_stars ? 5:

                Song song = new Song(title, singers, year, stars);
                DBHelper dbHelper = new DBHelper(getApplicationContext());
                dbHelper.createSong(song);

                // Go to the SongsActivity to display all the songs
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
