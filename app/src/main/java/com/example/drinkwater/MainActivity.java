package com.example.drinkwater;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.SharedPreferences;
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

    private boolean activated;

    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonNotify = findViewById(R.id.button_notify);
        editTextMinutes = findViewById(R.id.edit_text_interval);
        timePicker = findViewById(R.id.time_picker);

        timePicker.setIs24HourView(true);

        buttonNotify.setOnClickListener(notifyClick);

        preferences = getSharedPreferences("database", Context.MODE_PRIVATE);
        activated = preferences.getBoolean("activated", false);

        if (activated) {
            returnSavedState();
        }
    }

    private void returnSavedState() {
        buttonNotify.setText(R.string.pause);

        int color = ContextCompat.getColor(this, android.R.color.black);
        buttonNotify.setBackgroundColor(color);

        int interval = preferences.getInt("interval", 0);
        int hour = preferences.getInt("hour", timePicker.getCurrentHour());
        int minute = preferences.getInt("minutes", timePicker.getCurrentMinute());

        editTextMinutes.setText(String.valueOf(interval));
        timePicker.setCurrentHour(hour);
        timePicker.setCurrentMinute(minute);
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

            if (!activated) {
                buttonNotify.setText(R.string.pause);

                int color = ContextCompat.getColor(v.getContext(), android.R.color.black);
                buttonNotify.setBackgroundColor(color);

                activated = true;

                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("activated", true);
                editor.putInt("interval", interval);
                editor.putInt("hour", hour);
                editor.putInt("minutes", minute);
                editor.apply();

            } else {
                buttonNotify.setText(R.string.notify);

                int color = ContextCompat.getColor(v.getContext(), R.color.colorAccent);
                buttonNotify.setBackgroundColor(color);

                activated = false;

                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("activated", false);
                editor.remove("interval");
                editor.remove("hour");
                editor.remove("minutes");
                editor.apply();
            }

            Log.d("Testing", "hour: " + hour + "minute: " +
                minute + "interval: " + interval);
        }
    };

}