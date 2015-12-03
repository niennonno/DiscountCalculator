package com.mapplinks.discountcalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    EditText priceView, discountView, addnView, taxView;
    TextView resultView;
    Button calculate;
    DecimalFormat f = new DecimalFormat("#.##");
    double price, discount, addn, tax, result, saving ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        priceView = (EditText)findViewById(R.id.price);
        discountView = (EditText)findViewById(R.id.discount);
        addnView = (EditText) findViewById(R.id.addn_discount);
        taxView = (EditText) findViewById(R.id.tax);

        resultView = (TextView) findViewById(R.id.result);

        calculate = (Button) findViewById(R.id.calculate_but);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate();
            }
        });

    }

    void clear(){
        priceView.setText("");
        discountView.setText("");
        addnView.setText("");
        taxView.setText("");
        resultView.setText("");
    }

    void calculate() {
        if (priceView.getText().toString().isEmpty() || discountView.getText().toString().isEmpty()) {
            Toast.makeText(MainActivity.this, "Enter both price and Discount", Toast.LENGTH_SHORT).show();
        } else {
            price = Double.parseDouble(priceView.getText().toString());
            discount = Double.parseDouble(discountView.getText().toString());
            if(addnView.getText().toString().isEmpty())
                addn=0.0;
            else
                addn = Double.parseDouble(addnView.getText().toString());

            if (taxView.getText().toString().isEmpty())
                tax=0.0;
            else
                tax = Double.parseDouble(taxView.getText().toString());

            result = price - (price * discount / 100);
            result -= (result * addn / 100);

            result += (price * tax / 100);

            saving = (price + (price * tax / 100)) - result;
            resultView.setText("The result is: " + f.format(result) + "\nYou save: " + f.format(saving) );
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.refresh) {
            clear();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
