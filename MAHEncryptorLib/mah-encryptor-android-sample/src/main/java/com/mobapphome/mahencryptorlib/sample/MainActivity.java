package com.mobapphome.mahencryptorlib.sample;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.mobapphome.mahencryptorlib.MAHEncryptor;
import com.mobapphome.mahencryptorlib.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editTextOriginal;
    EditText editTextEnchrypted;
    EditText editTextKeyPhrase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btnDechrypt).setOnClickListener(this);
        findViewById(R.id.btnEnchrypt).setOnClickListener(this);
        editTextKeyPhrase = (EditText)findViewById(R.id.etKeyPhrase);
        editTextOriginal = (EditText)findViewById(R.id.etOriginalTxt);
        editTextEnchrypted = (EditText)findViewById(R.id.etEnchryptedTxt);
    }

    @Override
    public void onClick(View view) {
        String keyPhraseTxt = editTextKeyPhrase.getText().toString().trim();
        if (keyPhraseTxt.isEmpty()) {
            new AlertDialog.Builder(this)
                    .setTitle("Caution!")
                    .setMessage("Key phrase is empty. Fill it and continue.")
                    .setPositiveButton("Ok", null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
            return;
        }

        try {

            if (view.getId() == R.id.btnEnchrypt) {
                MAHEncryptor mahEncryptor = MAHEncryptor.newInstance(keyPhraseTxt);

                String originaltxt = editTextOriginal.getText().toString().trim();
                if (originaltxt.isEmpty()){
                    new AlertDialog.Builder(this)
                            .setTitle("Caution!")
                            .setMessage("Original string is empty. Fill it and continue.")
                            .setPositiveButton("Ok", null)
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                    return;
                }
                editTextEnchrypted.setText(mahEncryptor.encode(originaltxt));

            } else if (view.getId() == R.id.btnDechrypt) {
                MAHEncryptor mahEncryptor = MAHEncryptor.newInstance(keyPhraseTxt);

                String enchryptedTxt = editTextEnchrypted.getText().toString().trim();
                if (enchryptedTxt.isEmpty()){
                    new AlertDialog.Builder(this)
                            .setTitle("Caution!")
                            .setMessage("Enchrypted string is empty. Fill it and continue.")
                            .setPositiveButton("Ok", null)
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                    return;
                }
                editTextOriginal.setText(mahEncryptor.decode(enchryptedTxt));
            }
        }catch (Exception e){
            new AlertDialog.Builder(this)
                    .setTitle("Exception")
                    .setMessage(e.getMessage())
                    .setPositiveButton("Ok", null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
            return;
        }

    }
}
