package com.example.vannga.coundowntimer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SettingActivity extends AppCompatActivity {

    private EditText edSetting;
    private Button btnOK;

    private Long minutes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        edSetting = (EditText) findViewById(R.id.edSetting);

        btnOK = (Button) findViewById(R.id.btnOK);

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingActivity.this, MainActivity.class);
            try {
                minutes = Long.parseLong(edSetting.getText().toString());
                intent.putExtra("minutes", minutes);
                startActivity(intent);
            }catch (Exception ex){
                Toast.makeText(SettingActivity.this, "Please input here a minutes", Toast.LENGTH_SHORT).show();
            }

            }
        });
    }

}
