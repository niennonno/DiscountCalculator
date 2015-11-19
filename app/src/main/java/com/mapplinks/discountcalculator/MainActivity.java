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

public class MainActivity extends AppCompatActivity {

    EditText priceView, discountView, addnView, taxView;
    TextView resultView;
    Button calculate;
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
                resultView.setText("The result is: " + result);
            }
        });

    }

    void clear(){
        priceView.setText("0.00");
        discountView.setText("0.00");
        addnView.setText("0.00");
        taxView.setText("0.00");
        resultView.setText("0.00");
    }

    void calculate(){
        price = Double.parseDouble(priceView.getText().toString());
        discount = Double.parseDouble(discountView.getText().toString());
        addn = Double.parseDouble(addnView.getText().toString());
        tax = Double.parseDouble(taxView.getText().toString());
        result = price - (price*discount/100);
        result -= (result*addn/100);

        result += (price*tax/100);

        saving = (price*tax/100) - result;
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
