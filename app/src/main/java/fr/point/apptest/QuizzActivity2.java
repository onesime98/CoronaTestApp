package fr.point.apptest;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.shreyaspatil.MaterialDialog.MaterialDialog;
import com.shreyaspatil.MaterialDialog.interfaces.DialogInterface;

import java.util.ArrayList;

public class QuizzActivity2 extends AppCompatActivity {
    TextView question1, question2, question3;
    RadioGroup radioGroup1, radioGroup2, radioGroup3;
    RadioButton rButton1, rButton2, rButton3;
    Button validate;

    ArrayList<String> CoronaQuizz = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz2);
        Typeface type = Typeface.createFromAsset(getAssets(), "fonts/rodona.ttf");

        radioGroup1 = findViewById(R.id.rgroup1);
        radioGroup2 = findViewById(R.id.rgroup2);
        radioGroup3 = findViewById(R.id.rgroup3);
        validate = findViewById(R.id.btnValidate);
        validate.setTypeface(type);

        question1 = findViewById(R.id.textview_question1);
        question2 = findViewById(R.id.textview_question2);
        question3 = findViewById(R.id.textview_question3);
        question1.setTypeface(type);
        question2.setTypeface(type);
        question3.setTypeface(type);
        try {
            Bundle bundle = getIntent().getExtras();
            if (bundle.containsKey("question1"))
                CoronaQuizz.add(bundle.getString("question1"));
            if (bundle.containsKey("question2"))
                CoronaQuizz.add(bundle.getString("question2"));
            if (bundle.containsKey("question3"))
                CoronaQuizz.add(bundle.getString("question3"));

        } catch (NullPointerException e) {
        }

        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                analyseAnswer();
            }
        });


        //Toast.makeText(getApplicationContext(), "Taille " + CoronaQuizz.size(), Toast.LENGTH_LONG).show();
    }


    public void analyseAnswer() {
        Long nbreOui, nbreNon,pourcentage;
        int radiobutton1Id = radioGroup1.getCheckedRadioButtonId();
        int radiobutton2Id = radioGroup2.getCheckedRadioButtonId();
        int radiobutton3Id = radioGroup3.getCheckedRadioButtonId();
        rButton1 = findViewById(radiobutton1Id);
        rButton2 = findViewById(radiobutton2Id);
        rButton3 = findViewById(radiobutton3Id);

        if ((radioGroup1.getCheckedRadioButtonId() == -1) || (radioGroup2.getCheckedRadioButtonId() == -1) || (radioGroup3.getCheckedRadioButtonId() == -1)) {
            Toast.makeText(this, " Vous devez nécessairemment choisir une option pour chaque question", Toast.LENGTH_LONG).show();
        } else {

            CoronaQuizz.add(rButton1.getText().toString());
            CoronaQuizz.add(rButton2.getText().toString());
            CoronaQuizz.add(rButton3.getText().toString());

            nbreOui = CoronaQuizz.stream().filter(reponse -> reponse.equals("Oui")).count();
            nbreNon = CoronaQuizz.stream().filter(reponse -> reponse.equals("Non")).count();
            if (nbreOui > nbreNon) {
                pourcentage = (nbreOui *100)/(nbreOui+nbreNon);

                MaterialDialog mDialog = new MaterialDialog.Builder(this)
                        .setTitle("Résultat du Test")
                        .setAnimation(R.raw.corona_loader)
                        .setMessage(" Vous avez " + pourcentage + " % de risques d'avoir le coronavirus .\n" +
                                "Vous présentez les symptômes du CoronaVirus. Restez chez vous et contacter le 15 pour être pris en charge")
                        .setCancelable(false)
                        .setNegativeButton("Fermer",R.drawable.ic_close_black_24dp, new MaterialDialog.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                dialogInterface.dismiss();
                                CoronaQuizz.clear();
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                finish();
                            }
                        })
                        .build();
                mDialog.show();

            } else {
                pourcentage =0L;
                pourcentage = (nbreNon *100)/(nbreOui+nbreNon);

                MaterialDialog mDialog = new MaterialDialog.Builder(this)
                        .setTitle("Résultat du Test")
                        .setAnimation(R.raw.coronavirus)
                        .setMessage(" Vous avez " + pourcentage + " % de risques de ne pas avoir le coronavirus .\n"+
                                "Vous n'avez pas le coronavirus. N'oubliez pas d'adopter les gestes barrières")
                        .setCancelable(false)
                        .setNegativeButton("Fermer",R.drawable.ic_close_black_24dp, new MaterialDialog.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                dialogInterface.dismiss();
                                CoronaQuizz.clear();
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                finish();
                            }
                        })
                        .build();
                mDialog.show();

            }

        }

    }
}
