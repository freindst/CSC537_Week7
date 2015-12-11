package com.example.frein.lzw_compression;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class encodingResult extends AppCompatActivity {
    private TextView inputTF;
    private TextView outputTF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encoding_result);

        this.inputTF = (TextView)this.findViewById(R.id.inputTF);
        this.inputTF.setText(compressionCore.input);
        this.outputTF = (TextView)this.findViewById(R.id.outputTF);
        this.outputTF.setText(getEncodeOutput(compressionCore.input));
    }

    private String getEncodeOutput(String input)
    {
        encoding e = new encoding();
        String result = e.output(input + "#");
        return result;
    }

    public void decodingButtonPressed(View v)
    {
        compressionCore.bits = getEncodeOutput(compressionCore.input);
        Intent i = new Intent(this, decoding_compare.class);
        startActivity(i);
    }
}
