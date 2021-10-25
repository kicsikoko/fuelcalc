package com.kicsikoko.fuelcalc;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import static com.kicsikoko.fuelcalc.R.string.forint;

public class MainActivity extends AppCompatActivity {

    private EditText fuel, road, avarage;
    private TextView calcresult;
    private ImageView changeImage;
    private CheckBox benzin;
    private CheckBox diesel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button calcButton = findViewById(R.id.button_calc);
        Button resetButton = findViewById(R.id.reset_button);
        fuel = findViewById(R.id.fuel_etxt);
        road = findViewById(R.id.road_etxt);
        avarage = findViewById(R.id.avarage_etxt);
        calcresult = findViewById(R.id.result_tv);
        changeImage = findViewById(R.id.image);

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fuel.setText("");
                road.setText("");
                avarage.setText("");
                calcresult.setText("");
                changeImage.setImageResource(R.drawable.blackpump);
            }
        });
        calcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fuel_price = fuel.getText().toString().trim();
                String road_plan = road.getText().toString().trim();
                String avarage_consumption = avarage.getText().toString().trim();


                if (fuel_price.isEmpty() && road_plan.isEmpty() && avarage_consumption.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Nem töltöttél ki minden mezőt!", Toast.LENGTH_LONG).show();

                } else {
                    final int fprice = Integer.parseInt(fuel_price);
                    final int rplan = Integer.parseInt(road_plan);
                    final int aconsumption = Integer.parseInt(avarage_consumption);
                    String result = "";
                    int div = 100;

                    double calc;
                    calc = (fprice * aconsumption * rplan) / div;
                    //result = String.valueOf(calc);


                    calcresult.setText(String.format(Locale.GERMANY,"%.1f", calc) + " Ft" );
                    if (calc >= 0 && calc <= 5000) {
                        changeImage.setImageResource(R.drawable.greenpump);
                    } else if (calc >= 5000 && calc <= 10000) {
                        changeImage.setImageResource(R.drawable.orangepump);
                    } else {
                        changeImage.setImageResource(R.drawable.redpump);
                    }
                }
            }
        });

    }


}
