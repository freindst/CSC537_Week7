package com.example.frein.lzw_compression;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText inputTF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.inputTF = (EditText)this.findViewById(R.id.inputTF);
    }

    public void exampleButtonPressed(View v)
    {
        String example = "TOBEORNOTTOBEORTOBEORNOT";
        this.inputTF.setText(example);
    }

    public void encodeButtonPressed(View v)
    {
        String input = this.inputTF.getText().toString().toUpperCase();
        compressionCore.input = input;
        Intent i = new Intent(this, encodingResult.class);
        startActivity(i);
    }
}
