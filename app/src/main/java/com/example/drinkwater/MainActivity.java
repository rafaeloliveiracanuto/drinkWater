package com.example.drinkwater;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {

    private Button buttonNotify;
    private EditText editTextMinutes;
    private TimePicker timePicker;

    private int hour, minute, interval;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonNotify = findViewById(R.id.button_notify);
        editTextMinutes = findViewById(R.id.edit_text_interval);
        timePicker = findViewById(R.id.time_picker);

        timePicker.setIs24HourView(true);

        buttonNotify.setOnClickListener(notifyClick);
    }

    public View.OnClickListener notifyClick = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            String sInterval = editTextMinutes.getText().toString();

            hour = timePicker.getCurrentHour();
            minute = timePicker.getCurrentMinute();
            interval = Integer.parseInt(sInterval);

            Log.d("Testing", "hour: " + hour + "minute: " +
                minute + "interval: " + interval);
        }
    };

}