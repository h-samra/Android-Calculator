package com.example.herman.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static com.example.herman.calculator.R.id.result;


public class MainActivity extends AppCompatActivity {

    private EditText Ans;
    private EditText input;
    private TextView operation;

    private Double operand1 = null;
    private Double operand2 = null;
    private Double output = 0.0;
    private String pendingOperation = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Ans = (EditText) findViewById(result);
        input = (EditText) findViewById(R.id.input);
        operation = (TextView) findViewById(R.id.sign);

        Button b0 = (Button) findViewById(R.id.button0);
        Button b1 = (Button) findViewById(R.id.button1);
        Button b2 = (Button) findViewById(R.id.button2);
        Button b3 = (Button) findViewById(R.id.button3);
        Button b4 = (Button) findViewById(R.id.button4);
        Button b5 = (Button) findViewById(R.id.button5);
        Button b6 = (Button) findViewById(R.id.button6);
        Button b7 = (Button) findViewById(R.id.button7);
        Button b8 = (Button) findViewById(R.id.button8);
        Button b9 = (Button) findViewById(R.id.button9);
        Button bAC = (Button) findViewById(R.id.buttonAC);
        Button bDel = (Button) findViewById(R.id.buttonDel);
        Button bPow = (Button) findViewById(R.id.buttonPow);
        Button bDec = (Button) findViewById(R.id.buttonDec);
        Button bAns = (Button) findViewById(R.id.buttonAns);
        Button bPlus = (Button) findViewById(R.id.buttonPlus);
        Button bMinus = (Button) findViewById(R.id.buttonMinus);
        Button bMult = (Button) findViewById(R.id.buttonMult);
        Button bDivide = (Button) findViewById(R.id.buttonDivide);
        Button bEquals = (Button) findViewById(R.id.buttonEquals);


        View.OnClickListener numListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!operation.getText().toString().equals("=")) {
                    Button bNum = (Button) view;
                    input.append(bNum.getText().toString());
                }
            }
        };
        b0.setOnClickListener(numListener);
        b1.setOnClickListener(numListener);
        b2.setOnClickListener(numListener);
        b3.setOnClickListener(numListener);
        b4.setOnClickListener(numListener);
        b5.setOnClickListener(numListener);
        b6.setOnClickListener(numListener);
        b7.setOnClickListener(numListener);
        b8.setOnClickListener(numListener);
        b9.setOnClickListener(numListener);
        bDec.setOnClickListener(numListener);


        View.OnClickListener acListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ans.setText("");
                input.setText("");
                operation.setText("");
                operand1 = null;
                operand2 = null;
            }
        };
        bAC.setOnClickListener(acListener);


        View.OnClickListener delListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = input.getText().toString();
                if (text.length() > 0) {
                    input.setText(text.substring(0, text.length() - 1));
                }
            }
        };
        bDel.setOnClickListener(delListener);


        View.OnClickListener ansListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!operation.getText().toString().equals("=")) {
                    input.setText(output.toString());
                }
            }
        };
        bAns.setOnClickListener(ansListener);


        View.OnClickListener sqListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!operation.getText().toString().equals("=")) {
                    Double value = Double.valueOf(input.getText().toString());
                    value *=value;
                    input.setText(value.toString());
                }
            }
        };
        bPow.setOnClickListener(sqListener);


        View.OnClickListener oListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button b = (Button) view;
                String bOp = b.getText().toString();
                String value = input.getText().toString();
                if (value.length() !=0) {
                    performOp(value, bOp);
                }
                pendingOperation = bOp;
                operation.setText(pendingOperation);
            }
        };
        bEquals.setOnClickListener(oListener);
        bPlus.setOnClickListener(oListener);
        bMinus.setOnClickListener(oListener);
        bMult.setOnClickListener(oListener);
        bDivide.setOnClickListener(oListener);
    }


    private void performOp (String val, String op) {
        if (operand1 == null) {
           operand1 = Double.valueOf(val);
       }
        else {
            operand2 = Double.valueOf(val);

            if (pendingOperation.equals("=")) {
                pendingOperation = op;
            }
            switch (pendingOperation) {
                case "+":
                    operand1 += operand2;
                    break;
                case "-":
                    operand1 -= operand2;
                    break;
                case "x":
                    operand1 *= operand2;
                    break;
                case "/":
                    if (operand2 != 0.0) {
                        operand1 /= operand2;
                    }
                    else {
                        operand1 = 0.0;
                    }
                    break;
                case "=":
                    input.setText("");
                    //operand1 = null;
                    operand2 = null;
                    break;
            }
        }
        if (operand1 != null) {
            output = operand1;
        }
        else {
            output = 0.0;
        }
        Ans.setText(output.toString());
        input.setText("");
    }
}
