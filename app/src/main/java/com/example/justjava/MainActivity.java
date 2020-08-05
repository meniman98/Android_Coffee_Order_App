package com.example.justjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    EditText name;
    TextView amount, price;
    RadioButton sugar, milk;
    Button plus, minus;
    int priceOfCoffee = 3;
    int quantity = 0;
    String subject, greeting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.edName);
        amount = findViewById(R.id.tvAmount);
        sugar = findViewById(R.id.rbSugar);
        milk = findViewById(R.id.rbMilk);
        price = findViewById(R.id.tvPrice);
        plus = findViewById(R.id.btnPlus);
        minus = findViewById(R.id.btnMinus);




    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        String greeting = getString(R.string.greeting);
        String subject = getString(R.string.subject);



//        price.setText(NumberFormat.getCurrencyInstance().format(number) + "\n"
//               + getName()
//               + IfMilkIsChecked()
//               + IfSugarIsChecked()
//               + greeting);



        sendOrder(subject,NumberFormat.getCurrencyInstance().format(number) + "\n"
        + getName()
        + IfMilkIsChecked()
        + IfSugarIsChecked()
        + greeting);



    }
    public void submitOrder(View view) {
        if (milk.isChecked() && sugar.isChecked()) {
            priceOfCoffee = 6;
        }
        else if (milk.isChecked()) {
            priceOfCoffee = 4;
        }
        else if (sugar.isChecked()) {
            priceOfCoffee = 5;
        }
        displayPrice(quantity*priceOfCoffee);



    }

    private void display(int number) {

        amount.setText("" + number);
    }
    public void increment(View view) {
        quantity++;
        display(quantity);
    }
    public void decrement(View view) {
        if(quantity > 0) {
            quantity--;
        }
        else {
            quantity = 0;
        }
        display(quantity);
    }
    private String IfMilkIsChecked() {




        if (milk.isChecked()) {
            return getString(R.string.milkYes) + "\n";

        }
        else {
            return getString(R.string.milkNo) + "\n";
        }


    }

    private String IfSugarIsChecked() {
        if (sugar.isChecked()) {
            return getString(R.string.sugarYes) + "\n";

        }
        else {
           return getString(R.string.sugarNo) + "\n";
        }
    }


    private String getName() {
        if (name.getText().toString().length() > 0) {
            String personName = name.getText().toString();
        return getString(R.string.coffeeFor) + personName + "\n";
        }
        else {
            return "";
        }
    }
    public void sendOrder(String subject, String bodyText) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra((Intent.EXTRA_TEXT), bodyText);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }

}