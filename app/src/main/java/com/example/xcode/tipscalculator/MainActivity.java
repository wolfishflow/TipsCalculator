package com.example.xcode.tipscalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button clear;
    private Button calc;
    private EditText billAmount;
    private Spinner tipPercent;
    private EditText tipAmount;
    private EditText grandTotal;
    private Spinner splitAmong;
    private EditText perPerson;
    private TextView amountPerPerson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        clear = (Button) findViewById(R.id.btnClear);
        calc = (Button) findViewById(R.id.btnCalculate);

        billAmount = (EditText) findViewById(R.id.etBillAmount);
        tipPercent = (Spinner) findViewById(R.id.spTipPercent);
        tipAmount = (EditText) findViewById(R.id.etTipAmount);
        grandTotal = (EditText) findViewById(R.id.etGrandTotal);
        splitAmong = (Spinner) findViewById(R.id.spSplit);
        perPerson = (EditText) findViewById(R.id.etAmountPer);
        amountPerPerson = (TextView) findViewById(R.id.tvAmountPer);

    }

    public void onCalculate(View view) {

        //checks if amount is blank
        if (billAmount.getText().length()>0)
        {
            double billOrg = Double.parseDouble(billAmount.getText().toString());
            String tpString = tipPercent.getSelectedItem().toString();
            double tpDouble = Double.parseDouble(tpString.substring(0, tpString.length() - 1));
            int splitAmongInt = Integer.parseInt(splitAmong.getSelectedItem().toString());
            double gtDouble;
            tpDouble = tpDouble * 0.01;
            tpDouble = Math.ceil(tpDouble * 100)/100;
            tipAmount.setText(String.valueOf(Math.floor((billOrg * ( tpDouble)) * 100)/100));
            billOrg = Math.floor((billOrg * (1 + tpDouble)) * 100)/100;

            //if there is a split of more then 1 person, enable split tv and et
            if (splitAmongInt > 1)
            {
                gtDouble = billOrg / splitAmongInt;
                perPerson.setVisibility(View.VISIBLE);
                amountPerPerson.setVisibility(View.VISIBLE);

                perPerson.setText(String.valueOf(gtDouble));
            }
            grandTotal.setText(String.valueOf(billOrg));

        }
        else
        {
            //Warn user of their lack of input.
            Toast.makeText(this, "No Amount is entered for the Bill.", Toast.LENGTH_SHORT)
                    .show();
        }

    }
//Method clears all values and resets spinners!
    public void onClear(View view) {
        tipAmount.setText("");
        grandTotal.setText("");
        perPerson.setVisibility(View.INVISIBLE);
        amountPerPerson.setVisibility(View.INVISIBLE);
        billAmount.setText("");
        splitAmong.setSelection(0);
        tipPercent.setSelection(0);

    }
}
