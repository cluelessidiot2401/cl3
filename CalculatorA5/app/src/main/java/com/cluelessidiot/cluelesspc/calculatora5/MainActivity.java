package com.cluelessidiot.cluelesspc.calculatora5;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import net.objecthunter.exp4j.*;

public class MainActivity extends AppCompatActivity {

    private Button button[] = new Button[10] ;
    private TextView input ;
    private TextView output ;
    private Button clear,add,sub,div,mul,equal,sin,cos,tan,dot,pi,sqrt;
    private Button brOpen,brClose;
    private Button mem[] = new Button[3];
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private static final String MyPREFERENCES="MyPrefs";
    private static final String Key="Key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        setButtons();
        setOnClickListeners();
    }

    private void setButtons()
    {
        button[0] = findViewById(R.id.button0);
        button[1] = findViewById(R.id.button1);
        button[2] = findViewById(R.id.button2);
        button[3] = findViewById(R.id.button3);
        button[4] = findViewById(R.id.button4);
        button[5] = findViewById(R.id.button5);
        button[6] = findViewById(R.id.button6);
        button[7] = findViewById(R.id.button7);
        button[8] = findViewById(R.id.button8);
        button[9] = findViewById(R.id.button9);

        mem[0] = findViewById(R.id.memSave);
        mem[1] = findViewById(R.id.memRecall);
        mem[2] = findViewById(R.id.memClear);

        input = findViewById(R.id.exp);
        output = findViewById(R.id.res);

        clear = findViewById(R.id.clr);

        add = findViewById(R.id.add);
        sub = findViewById(R.id.sub);
        mul= findViewById(R.id.mul);
        div= findViewById(R.id.div);

        equal = findViewById(R.id.equal);

        sin = findViewById(R.id.sin);
        cos = findViewById(R.id.cos);
        tan = findViewById(R.id.tan);

        dot = findViewById(R.id.dot);
        brOpen = findViewById(R.id.brOpen);
        brClose = findViewById(R.id.brClose);

        pi = findViewById(R.id.pi);
        sqrt = findViewById(R.id.sqroot);

    }

    private void setOnClickListeners()
    {
        View.OnClickListener temp = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = ((Button) view).getText().toString();
                input.append(text);
            }
        };

        for(int i=0;i<10;++i)
        {
            button[i].setOnClickListener(temp);
        }
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input.setText("");
                output.setText("");
            }
        });

        add.setOnClickListener(temp);
        mul.setOnClickListener(temp);
        div.setOnClickListener(temp);
        sub.setOnClickListener(temp);

        sin.setOnClickListener(temp);
        cos.setOnClickListener(temp);
        tan.setOnClickListener(temp);
        dot.setOnClickListener(temp);
        brOpen.setOnClickListener(temp);
        brClose.setOnClickListener(temp);
        sqrt.setOnClickListener(temp);


        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onEqual();
            }
        });
        pi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input.append(Double.toString(Math.PI));
            }
        });

        mem[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = output.getText().toString();
                if(text!=""){
                    Toast.makeText(getApplicationContext(), text +" is stored in memory",Toast.LENGTH_SHORT).show();
                    editor.putString(Key,text);
                    editor.commit();
                }else   Toast.makeText(getApplicationContext(), "Output is empty. Nothing to Store",Toast.LENGTH_SHORT).show();
            }
        });

        mem[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text="";
                if(sharedPreferences.getAll().containsKey(Key)) input.append(sharedPreferences.getString(Key,""));
                else Toast.makeText(getApplicationContext(),"There is no data",Toast.LENGTH_SHORT).show();
            }
        });

        mem[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sharedPreferences.getAll().containsKey(Key)){
                    Toast.makeText(getApplicationContext(),"Memory Cleared",Toast.LENGTH_SHORT).show();
                    editor.remove(Key);
                    editor.commit();
                }else   Toast.makeText(getApplicationContext(),"Memory is Already Cleared",Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void onEqual()
    {
        try {
            String txt = input.getText().toString();
            Expression expression = new ExpressionBuilder(txt).build();
            // Calculate the result and display
            double result = expression.evaluate();
            output.setText(String.format("%.12f", result));
//                    lastDot = true; // Result contains a dot
        } catch (Exception ex) {
            // Display an error message
            output.setText("Error");
//                    stateError = true;
//                    lastNumeric = false;
        }
    }

}
