package fr.point.apptest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Bonjour";

    TextView testPresentation;
    Button startTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Typeface type = Typeface.createFromAsset(getAssets(),"fonts/rodona.ttf");
        testPresentation = findViewById(R.id.textview_test_presentation);
        startTest =  findViewById(R.id.btnStart);
        testPresentation.setTypeface(type);
        startTest.setTypeface(type);

        startTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,QuizzActivity.class));
            }
        });
    }
}
