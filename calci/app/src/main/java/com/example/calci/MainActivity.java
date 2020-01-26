package com.example.calci;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button button0, button1, button2, button3, button4, button5, button6,
            button7, button8, button9, buttonAdd, buttonSub, buttonDiv,
            buttonMul, button10, buttonC, buttonEqual;
    EditText expresssion;
    static char[] express;
    static int len,addn=0,multn=0,divn=0,subn=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button0     =  (Button) findViewById(R.id.button0);
        button1     = (Button) findViewById(R.id.button1);
        button2     = (Button) findViewById(R.id.button2);
        button3     = (Button) findViewById(R.id.button3);
        button4     = (Button) findViewById(R.id.button4);
        button5     = (Button) findViewById(R.id.button5);
        button6     = (Button) findViewById(R.id.button6);
        button7     = (Button) findViewById(R.id.button7);
        button8     = (Button) findViewById(R.id.button8);
        button9     = (Button) findViewById(R.id.button9);
        button10    = (Button) findViewById(R.id.button10);
        buttonAdd   = (Button) findViewById(R.id.buttonadd);
        buttonSub   = (Button) findViewById(R.id.buttonsub);
        buttonMul   = (Button) findViewById(R.id.buttonmul);
        buttonDiv   = (Button) findViewById(R.id.buttondiv);
        buttonEqual = (Button) findViewById(R.id.buttonequ);
        expresssion = (EditText) findViewById(R.id.etext);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expresssion.setText(expresssion.getText() + "1");
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expresssion.setText(expresssion.getText() + "2");
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expresssion.setText(expresssion.getText() + "3");
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expresssion.setText(expresssion.getText() + "4");
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expresssion.setText(expresssion.getText() + "5");
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expresssion.setText(expresssion.getText() + "6");
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expresssion.setText(expresssion.getText() + "7");
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expresssion.setText(expresssion.getText() + "8");
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expresssion.setText(expresssion.getText() + "9");
            }
        });

        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expresssion.setText(expresssion.getText() + "0");
            }
        });

        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expresssion.setText("");
            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (expresssion == null) {
                    expresssion.setText("");
                } else {
                    expresssion.setText(expresssion.getText() + "+");
                    addn++;
                }
            }
        });

        buttonSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (expresssion == null) {
                    expresssion.setText("");
                } else {
                    expresssion.setText(expresssion.getText() + "-");
                    subn++;
                }
            }
        });

        buttonDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (expresssion == null) {
                    expresssion.setText("");
                } else {
                    expresssion.setText(expresssion.getText() + "/");
                    divn++;
                }
            }
        });

        buttonMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (expresssion == null) {
                    expresssion.setText("");
                } else {
                    expresssion.setText(expresssion.getText() + "*");
                    multn++;
                }
            }
        });

        buttonEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String copy = expresssion.getText().toString();
                express = copy.toCharArray();
                len = express.length;
                int i=0,div,mult,add,sub;
                i=0;
                while(len!=1)
                {
                    while(divn!=0)
                    {
                        i++;
                        if(express[i]=='/')
                        {
                            divn--;
                            divide(i);
                            i=0;
                        }
                    }
                    while(multn!=0)
                    {
                        i++;
                        if(express[i]=='*')
                        {
                            multn--;
                            mult(i);
                            i=0;
                        }
                    }
                    while(addn!=0)
                    {
                        i++;
                        if(express[i]=='+')
                        {
                            addn--;
                            sum(i);
                            i=0;
                        }
                    }
                    while(subn!=0)
                    {
                        i++;
                        if(express[i]=='-')
                        {
                            subn--;
                            sub(i);
                            i=0;
                        }
                    }
                }
                System.out.println((int)(express[0]-48));
                //express[0]=(int)express[0]-48;
            expresssion.setText(""+ (int)(express[0]-48));
            }
        });
    }

    public static void divide(int i)
    {
        int result,j;
        result=((int)(express[i-1])-48)/((int)(express[i+1])-48);
        result=result+48;
        express[i-1]=(char)result;
        for(j=i;j<len-2;j++)
        {
            express[j]=express[j+2];
        }
        len=len-2;
    }

    public static void sum(int i)
    {
        int result,j;
        result=((int)express[i-1]-48)+(int)express[i+1]-48;
        result=result+48;
        express[i-1]=(char)result;
        for(j=i;j<len-2;j++)
        {
            express[j]=express[j+2];
        }
        len=len-2;
    }

    public static void sub(int i)
    {
        int result,j;
        result=(int)express[i-1]-(int)express[i+1];
        result=result+48;
        express[i-1]=(char)result;
        for(j=i;j<len-2;j++)
        {
            express[j]=express[j+2];
        }
        len=len-2;
    }

    public static void mult(int i)
    {
        int result,j;
        result=(((int)express[i-1]) -48)*(((int)express[i+1])-48);
        result=result+48;
        express[i-1]=(char)result;
        for(j=i;j<len-2;j++)
        {
            express[j]=express[j+2];
        }
        len=len-2;
    }
}