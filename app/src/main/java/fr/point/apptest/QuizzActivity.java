package fr.point.apptest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class QuizzActivity extends AppCompatActivity {
TextView question1,question2,question3;
RadioGroup radioGroup1,radioGroup2,radioGroup3;
RadioButton rButton1,rButton2,rButton3;
Button continueForm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz);
        Typeface type = Typeface.createFromAsset(getAssets(),"fonts/rodona.ttf");

        radioGroup1 = findViewById(R.id.rgroup1);
        radioGroup2 = findViewById(R.id.rgroup2);
        radioGroup3 = findViewById(R.id.rgroup3);
        continueForm = findViewById(R.id.btnContinue);
        continueForm.setTypeface(type);

        question1 = findViewById(R.id.textview_question1);
        question2 = findViewById(R.id.textview_question2);
        question3 = findViewById(R.id.textview_question3);
        question1.setTypeface(type);
        question2.setTypeface(type);
        question3.setTypeface(type);

        continueForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radioButtonClick();
            }
        });


    }

    public void radioButtonClick(){

    int radiobutton1Id = radioGroup1.getCheckedRadioButtonId();
    int radiobutton2Id = radioGroup2.getCheckedRadioButtonId();
    int radiobutton3Id = radioGroup3.getCheckedRadioButtonId();
    rButton1 = findViewById(radiobutton1Id);
    rButton2 = findViewById(radiobutton2Id);
    rButton3 = findViewById(radiobutton3Id);

    if(( radioGroup1.getCheckedRadioButtonId()== -1) ||( radioGroup2.getCheckedRadioButtonId()==-1) || (radioGroup3.getCheckedRadioButtonId()==-1)){
        Toast.makeText(this," Vous devez nécessairemment choisir une option pour chaque question" ,Toast.LENGTH_LONG).show();
    }
    else{

        Intent intent = new Intent(getApplicationContext(),QuizzActivity2.class);
        intent.putExtra("question1",rButton1.getText());
        intent.putExtra("question2",rButton2.getText());
        intent.putExtra("question3",rButton3.getText());
        startActivity(intent);


    }

    }
}
