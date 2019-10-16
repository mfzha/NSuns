package com.mzha.nsuns;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mzha.nsuns.R;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Locale;

public class UpdatePBs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_tms);

    }

    public void updatePBs(View view) {


    }

    public void revertPBs(View view) {

    }

    private void updateTextFields(Double newOHP, Double newSquat, Double newBench, Double newDL) {

    }

    private void updatePBlocal(Double newOHP, Double newSquat, Double newBench, Double newDL) {

    }
}
