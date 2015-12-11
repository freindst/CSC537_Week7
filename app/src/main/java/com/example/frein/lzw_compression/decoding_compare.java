package com.example.frein.lzw_compression;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class decoding_compare extends AppCompatActivity {
    private TextView originalTF;
    private TextView outputTF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decoding_compare);

        this.originalTF = (TextView)this.findViewById(R.id.originalTF);
        this.originalTF.setText(compressionCore.input);

        this.outputTF = (TextView)this.findViewById((R.id.outputTF));
        this.outputTF.setText(getDecodeOuput(compressionCore.bits));
    }

    private String getDecodeOuput(String bits)
    {
        decoding d = new decoding();
        String result = d.output(bits);
        return result;
    }
}
