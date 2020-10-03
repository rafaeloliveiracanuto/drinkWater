package com.example.drinkwater;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {

    private Button buttonNotify;
    private EditText editTextMinutes;
    private TimePicker timePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonNotify = findViewById(R.id.button_notify);
        editTextMinutes = findViewById(R.id.edit_text_interval);
        timePicker = findViewById(R.id.time_picker);

        timePicker.setIs24HourView(true);

        
    }
}