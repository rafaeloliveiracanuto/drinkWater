package com.example.drinkwater;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button buttonNotify;
    private EditText editTextMinutes;
    private TimePicker timePicker;

    private int hour, minute, interval;

    private boolean activated = false;

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

            if (sInterval.isEmpty()) {
                Toast.makeText(v.getContext(), R.string.message_error, Toast.LENGTH_LONG).show();
                return;
            }

            hour = timePicker.getCurrentHour();
            minute = timePicker.getCurrentMinute();
            interval = Integer.parseInt(sInterval);

            buttonNotify.setText(R.string.pause);

            int color = ContextCompat.getColor(v.getContext(), android.R.color.black);

            buttonNotify.setBackgroundColor(color);

            Log.d("Testing", "hour: " + hour + "minute: " +
                minute + "interval: " + interval);
        }
    };

}