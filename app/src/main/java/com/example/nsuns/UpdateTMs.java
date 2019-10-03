package com.example.nsuns;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class UpdateTMs extends AppCompatActivity {

    private Double TM_OHP = 0.0;
    private Double TM_SQUAT = 0.0;
    private Double TM_BENCH = 0.0;
    private Double TM_DL = 0.0;

    private Double OLD_TM_OHP = 0.0;
    private Double OLD_TM_SQUAT = 0.0;
    private Double OLD_TM_BENCH = 0.0;
    private Double OLD_TM_DL = 0.0;

    private static boolean refresh = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_tms);

        // if a TMs file exists, back it up
        // if it doesn't exist it will be generated once TMs are set

        if (Arrays.asList(fileList()).contains("TMs.txt")) {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(openFileInput("TMs.txt"), "UTF-8"))){
                for (String line = br.readLine(); line != null; line = br.readLine()) {
                    String[] parts = line.split(",");
                    if (parts[0].equals("ohp")) {
                        OLD_TM_OHP = Double.parseDouble(parts[1]);
                    } else if (parts[0].equals("squat")) {
                        OLD_TM_SQUAT = Double.parseDouble(parts[1]);
                    } else if (parts[0].equals("bench")) {
                        OLD_TM_BENCH = Double.parseDouble(parts[1]);
                    } else if (parts[0].equals("dl")) {
                        OLD_TM_DL = Double.parseDouble(parts[1]);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // update textviews
        updateTextFields(OLD_TM_OHP, OLD_TM_BENCH, OLD_TM_SQUAT, OLD_TM_DL);
    }

    public void updateTMs(View view) {

        EditText editOHP = (EditText) findViewById(R.id.editOHP);
        EditText editSquat = (EditText) findViewById(R.id.editSquat);
        EditText editBench = (EditText) findViewById(R.id.editBench);
        EditText editDL = (EditText) findViewById(R.id.editDL);

        StringBuilder failures = new StringBuilder();
        boolean failure = false;

        try {
            TM_OHP = editOHP.getText().toString().equals("") ? OLD_TM_OHP : Double.parseDouble(editOHP.getText().toString());
        } catch (Exception e) { // catch nullpointer and numberformat exceptions
            failures.append("\t\tOHP\n");
            TM_OHP = OLD_TM_OHP;
            failure = true;
        }
        try {
            TM_SQUAT = editSquat.getText().toString().equals("") ? OLD_TM_SQUAT : Double.parseDouble(editSquat.getText().toString());
        } catch (Exception e) {
            failures.append("\t\tSquat\n");
            TM_SQUAT = OLD_TM_SQUAT;
            failure = true;
        }
        try {
            TM_BENCH = editBench.getText().toString().equals("") ? OLD_TM_BENCH : Double.parseDouble(editBench.getText().toString());
        } catch (Exception e) {
            failures.append("\t\tBench\n");
            TM_BENCH = OLD_TM_BENCH;
            failure = true;
        }
        try {
            TM_DL = editDL.getText().toString().equals("") ? OLD_TM_DL : Double.parseDouble(editDL.getText().toString());
        } catch (Exception e) {
            failures.append("\t\tDeadlift\n");
            TM_DL = OLD_TM_DL;
            failure = true;
        }

        updateTextFields(TM_OHP, TM_SQUAT, TM_BENCH, TM_DL);
        updateTMlocal(TM_OHP, TM_SQUAT, TM_BENCH, TM_DL);
        TextView updateView = findViewById(R.id.updatedTMs);

        if (!failure) {
            Toast.makeText(this, "Updated TMs successfully.", Toast.LENGTH_LONG).show();
            updateView.setText(String.format(Locale.getDefault(), "%s", "Updated TMs successfully."));
            refresh = true;
        } else {
            updateView.setText(String.format(Locale.getDefault(), "%s%s%s", "Detected incorrect format in:", failures, "Please try again."));
            refresh = false;
        }
        /*if (!failure) {
            updateView.setText(String.format(Locale.getDefault(), "%s", "Updated TMs successfully."));
            return true;
        } else {
            updateView.setText(String.format(Locale.getDefault(), "%s", "Detected incorrect format of TMs.\nPlease try again."));
            return false;
        }*/
    }

    public void updateReloadTMs(View view) {
        updateTMs(view);
        if (refresh) {
            MainActivity.getInstance().finish();
            MainActivity.mainActivity = null;
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }
    }

    public void revertTMs(View view) {
        updateTextFields(OLD_TM_OHP, OLD_TM_SQUAT, OLD_TM_BENCH, OLD_TM_DL);

        TextView update = findViewById(R.id.updatedTMs);
        update.setText(String.format(Locale.getDefault(), "%s", "Reverted changes to TMs."));
    }

    private void updateTextFields(Double newOHP, Double newSquat, Double newBench, Double newDL) {
        // update textfields
        TextView textOHP = findViewById(R.id.currentOHP);
        TextView textBench = findViewById(R.id.currentBench);
        TextView textSquat = findViewById(R.id.currentSquat);
        TextView textDL = findViewById(R.id.currentDL);

        textOHP.setText(String.format(Locale.getDefault(), "%s:\n %.2f", "current OHP", newOHP));
        textBench.setText(String.format(Locale.getDefault(), "%s:\n %.2f", "current Bench", newBench));
        textSquat.setText(String.format(Locale.getDefault(), "%s:\n %.2f", "current Squat", newSquat));
        textDL.setText(String.format(Locale.getDefault(), "%s:\n %.2f", "current DL", newDL));
    }

    private void updateTMlocal(Double newOHP, Double newSquat, Double newBench, Double newDL) {
        // update internal storage
        String updatedTMs = String.format(Locale.getDefault(), "ohp,%f\nsquat,%f\nbench,%f\ndl,%f", newOHP, newSquat, newBench, newDL);
        try {
            FileOutputStream fOut = openFileOutput("TMs.txt", Context.MODE_PRIVATE);
            fOut.write(updatedTMs.getBytes());
            fOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
