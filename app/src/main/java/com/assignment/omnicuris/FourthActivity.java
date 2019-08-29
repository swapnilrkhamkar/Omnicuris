package com.assignment.omnicuris;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.radiobutton.MaterialRadioButton;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;

public class FourthActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private TextView txtQuestion;
    private MaterialButton btnNext, btnPrevious;
    private ParentsModel parentsModel;
    private RadioGroup radioAnswer;
    private MaterialRadioButton radioA, radioB, radioC, radioD, radioAnswerButton;
    private String answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        txtQuestion = findViewById(R.id.txtQuestion);
        btnNext = findViewById(R.id.btnNext);
        btnPrevious = findViewById(R.id.btnPrevious);
        radioAnswer = findViewById(R.id.radioAnswer);
        radioA = findViewById(R.id.radioA);
        radioB = findViewById(R.id.radioB);
        radioC = findViewById(R.id.radioC);
        radioD = findViewById(R.id.radioD);

        String json = inputStreamToString(FourthActivity.this.getResources().openRawResource(R.raw.questions));

        Log.e("json", " " + json);
        parentsModel = new Gson().fromJson(json, ParentsModel.class);

        btnPrevious.setVisibility(View.VISIBLE);
        txtQuestion.setText(parentsModel.getQuestions().get(3).getQuestion());
        radioA.setText(parentsModel.getQuestions().get(3).getAnswers().get(0).getA());
        radioB.setText(parentsModel.getQuestions().get(3).getAnswers().get(0).getB());
        radioC.setText(parentsModel.getQuestions().get(3).getAnswers().get(0).getC());
        radioD.setText(parentsModel.getQuestions().get(3).getAnswers().get(0).getD());

        radioAnswer.setOnCheckedChangeListener(this);
        btnNext.setOnClickListener(this);
        btnPrevious.setOnClickListener(this);
    }

    public String inputStreamToString(InputStream inputStream) {
        try {
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes, 0, bytes.length);
            return new String(bytes);
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.btnNext :


                if (answer != null) {
                    SQLite sqLite = new SQLite(FourthActivity.this);
                    sqLite.addAnswer("4", answer);
                    startActivity(new Intent(FourthActivity.this, FifthActivity.class));
                    finish();
                }
                else {
                    new MaterialAlertDialogBuilder(FourthActivity.this, R.style.ThemeOverlay_MaterialComponents_MaterialAlertDialog_Centered)
                            .setTitle("Please select an answer")
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            //.setNegativeButton("Cancel", null)
                            .show();
                }


                break;

            case R.id.btnPrevious :

                startActivity(new Intent(FourthActivity.this, ThirdActivity.class));
                finish();

                break;
        }

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {

        radioAnswerButton = findViewById(i);
        Log.e("ANSWER", " " + radioAnswerButton.getText());
        answer = radioAnswerButton.getText().toString();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}
