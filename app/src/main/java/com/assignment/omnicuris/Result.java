package com.assignment.omnicuris;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;

public class Result extends AppCompatActivity {

    private TextView txtResult;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);

        txtResult = findViewById(R.id.txtResult);

        SQLite sqLite = new SQLite(Result.this);

        ArrayList<String> answers = (ArrayList<String>) sqLite.getAllAnswers();

        ArrayList<String> expectedAnswers = new ArrayList<>();
        expectedAnswers.add("7");
        expectedAnswers.add("3");
        expectedAnswers.add("42");
        expectedAnswers.add("5");
        expectedAnswers.add("13");

        Log.e("ANSWERS", " " + answers);

        if (answers.size() != 5) {

            new MaterialAlertDialogBuilder(Result.this, R.style.ThemeOverlay_MaterialComponents_MaterialAlertDialog_Centered)
                    .setTitle("Please solve all questions")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    //.setNegativeButton("Cancel", null)
                    .show();

        } else {

            expectedAnswers.retainAll(answers);
            Log.e("COUNT", " " + expectedAnswers);
            Log.e("COUNT", " " + expectedAnswers.size());

            if (expectedAnswers.size() >= 3) {
                txtResult.setText("You are passed and your answers are " + answers);
            } else {
                txtResult.setText("You are failed and your answers are " + answers);
            }


        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
