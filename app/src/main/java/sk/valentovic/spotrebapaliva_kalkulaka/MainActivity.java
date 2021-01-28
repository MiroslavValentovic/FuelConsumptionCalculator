package sk.valentovic.spotrebapaliva_kalkulaka;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText consumption;
    private EditText distance;
    private EditText fuel_price;
    private ImageButton recalculate;
    private Button reset;
    private TextView fuel_consumption;
    private TextView supposed_distance;
    private TextView enter_fuel_price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        consumption = (EditText) findViewById(R.id.consumption);
        distance = (EditText) findViewById(R.id.distance);
        fuel_price = (EditText) findViewById(R.id.fuel_price);
        recalculate = (ImageButton) findViewById(R.id.imageButton);
        fuel_consumption = (TextView) findViewById(R.id.id_consumption);
        supposed_distance = (TextView) findViewById(R.id.id_distance);
        enter_fuel_price = (TextView) findViewById(R.id.enter_price);
        reset = (Button) findViewById(R.id.button_reset);

        reset.setOnClickListener(v -> reset());


        recalculate.setOnClickListener(v -> {
            if (consumption.length() == 0) {
                fuel_consumption.setTextColor(getResources().getColor(R.color.red));
                Toast.makeText(getApplicationContext(), "Zadaj hodnotu", Toast.LENGTH_SHORT).show();
            } else if (distance.length() == 0) {
                supposed_distance.setTextColor(getResources().getColor(R.color.red));
                Toast.makeText(getApplicationContext(), "Zadaj hodnotu", Toast.LENGTH_SHORT).show();
            } else if (fuel_price.length() == 0) {
                enter_fuel_price.setTextColor(getResources().getColor(R.color.red));
                Toast.makeText(getApplicationContext(), "Zadaj hodnotu", Toast.LENGTH_SHORT).show();
            } else {
                closeKeyboard();
                Calculate();
            }
        });

    }

    private void Calculate() {

        double result;
        double result2;
        double num1 = Double.parseDouble("0" + consumption.getText().toString());
        double num2 = Double.parseDouble("0" + fuel_price.getText().toString());
        double num3 = Double.parseDouble("0" + distance.getText().toString());

        result = ((num1 * num2) / 100) * num3;
        result2 = (num1 * num2) / 100;

        String the_result = String.format("%.2f", result);

        if (result > 0 && result2 > 0.) {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Výsledok");
            alert.setCancelable(false);
            alert.setMessage("Vaša cesta vás bude stáť " + the_result + "€");
            alert.setPositiveButton("Späť", (dialogInterface, i) -> {
            });
            alert.show();

            fuel_consumption.setTextColor(getResources().getColor(R.color.black));
            supposed_distance.setTextColor(getResources().getColor(R.color.black));
            enter_fuel_price.setTextColor(getResources().getColor(R.color.black));
        } else if (result == 0 && result2 == 0) {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Chyba!");
            alert.setCancelable(false);
            alert.setMessage("Žiaľ z týchto údajov sa nedá nič vypočítať, zadajte iné hodnoty");
            alert.setPositiveButton("Späť", (dialogInterface, i) -> {
                reset();
            });
            alert.show();
        }

    }

        private void closeKeyboard () {

            View view = this.getCurrentFocus();

            if (view != null) {

                InputMethodManager manager
                        = (InputMethodManager)
                        getSystemService(
                                Context.INPUT_METHOD_SERVICE);
                manager
                        .hideSoftInputFromWindow(
                                view.getWindowToken(), 0);
            }
        }

        private void reset () {
            consumption.setText("");
            fuel_price.setText("");
            distance.setText("");
        }
    }
