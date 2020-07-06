package sg.edu.rp.c346.id18054367.mybmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.preference.PreferenceManager;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity {


    EditText etWeight;
    EditText etHeight;
    Button btnCalculate;
    Button btnReset;
    TextView tvLastDate;
    TextView tvLastBMI;
    TextView tvRate;

    @Override
    protected void onPause() {
        super.onPause();
        String strDate =  tvLastDate.getText().toString();
        String strBMI = tvLastBMI.getText().toString();
        String strRate = tvRate.getText().toString();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor prefEdit = prefs.edit();
        prefEdit.putString("date", strDate);
        prefEdit.putString("bmi", strBMI);
        prefEdit.putString("rate", strRate);

        prefEdit.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String strDate = prefs.getString("date", "Last Calculated Date:");
        tvLastDate.setText(strDate);

        String strBMI = prefs.getString("bmi", "Last Calculated BMI:0.0");
        tvLastBMI.setText(strBMI);

        String strRate = prefs.getString("rate", "");
        tvLastBMI.setText(strRate);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etWeight = findViewById(R.id.editTextWeight);
        etHeight = findViewById(R.id.editTextHeight);
        btnCalculate = findViewById(R.id.buttonCalculate);
        btnReset = findViewById(R.id.buttonReset);
        tvLastDate = findViewById(R.id.textViewDate);
        tvLastBMI = findViewById(R.id.textViewBMI);
        tvRate = findViewById(R.id.textViewRate);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code for the action

                float weight = Float.parseFloat(etWeight.getText().toString());
                float height = Float.parseFloat(etHeight.getText().toString());
                float bmi = weight / (height * height);
                Calendar now = Calendar.getInstance();  //Create a Calendar object with current date and time
                String datetime = now.get(Calendar.DAY_OF_MONTH) + "/" +
                        (now.get(Calendar.MONTH)+1) + "/" +
                        now.get(Calendar.YEAR) + " " +
                        now.get(Calendar.HOUR_OF_DAY) + ":" +
                        now.get(Calendar.MINUTE);


                tvLastDate.setText("Last Calculated Date:" + datetime);
                tvLastBMI.setText("Last Calculated BMI:" + bmi);

                if (bmi < 18.5){
                    tvRate.setText("You are underweight");
                } else if (bmi >= 18.5 && bmi <= 24.9) {
                    tvRate.setText("Your BMI is normal");
                } else if (bmi >= 25 && bmi <= 29.9){
                    tvRate.setText("You are overweight");
                } else if (bmi >= 30){
                    tvRate.setText("You are obese");
                }

                etWeight.setText("");
                etHeight.setText("");

            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code for the action
                tvLastDate.setText("Last Calculated Date:");
                tvLastBMI.setText("Last Calculated BMI:0.0");
                etWeight.setText("");
                etHeight.setText("");
                tvRate.setText("");


            }
        });

    }

}
