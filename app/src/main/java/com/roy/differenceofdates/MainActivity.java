package com.roy.differenceofdates;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private static final String TAG1 = "MainActivity";
    private static final String TAG2 = "MainActivity";
    private TextView mDisplayDate1;
    private DatePickerDialog.OnDateSetListener mDataSetListener1;
    private TextView mDisplayDate2;
    private DatePickerDialog.OnDateSetListener mDataSetListener2;
    int yy1, mm1, dd1, yy2, mm2, dd2, s = 0;
    int am[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //taking input for 1st date
        mDisplayDate1 = (TextView) findViewById(R.id.tvdate1);
        mDisplayDate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        MainActivity.this,
                        android.R.style.Theme_DeviceDefault_Light_Dialog_MinWidth,
                        mDataSetListener1,
                        year, month, day);

                dialog.show();


            }
        });

        mDataSetListener1 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int y1, int m1, int d1) {
                m1 += 1;
                Log.d(TAG1, "emDataSet: dd/mm/yyyy" + d1 + "/" + m1 + "/" + y1);
                yy1 = y1;
                mm1 = m1;
                dd1 = d1;

                String date1 = d1 + "/" + m1 + "/" + y1;
                mDisplayDate1.setText(date1);
            }
        };

        //taking input for 2nd date
        mDisplayDate2 = (TextView) findViewById(R.id.tvdate2);
        mDisplayDate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        MainActivity.this,
                        android.R.style.Theme_DeviceDefault_Light_Dialog_MinWidth,
                        mDataSetListener2,
                        year, month, day);

                dialog.show();


            }
        });

        mDataSetListener2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int y2, int m2, int d2) {
                m2 += 1;
                Log.d(TAG1, "emDataSet: dd/mm/yyyy" + d2 + "/" + m2 + "/" + y2);
                yy2 = y2;
                mm2 = m2;
                dd2 = d2;

                String date2 = d2 + "/" + m2 + "/" + y2;
                mDisplayDate2.setText(date2);
            }
        };

        final Button calday = (Button) findViewById(R.id.calday);   //for cal days

        calday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //display object

                int  i, c = 0,s=0, y1=yy1;

                if (y1 % 4 == 0 || y1 % 100 == 0 || y1 % 400 == 0)
                    am[1] = 29;

                if (yy1 == yy2)//summation of days
                {
                    for (i = mm1-1; i < mm2 - 1; i++)
                        s += am[i];

                }
                else {
                    for (i = mm1 - 1; i < 12; i++)//1st inpt year
                        s += am[i];

                    y1+=1;
                    while(y1<yy2) {             //middle years
                        if (y1 % 4 == 0 || y1 % 100 == 0 || y1 % 400 == 0)
                            am[1] = 29;
                        else
                            am[1]=28;

                        for (i = 0; i < 12; i++)
                        {
                            s += am[i];
                        }

                        y1++;
                    }
                    for (i = 0; i < mm2 - 1; i++)//2nd inpt year
                    {
                        s += am[i];
                    }
                }
                s -= dd1;
                s += dd2;

                calday.setText(s + " days");

            }
        });

        final Button calmon = (Button) findViewById(R.id.calmon); //for cal mnth & days

        calmon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i, c = 0, s = 0,y1=yy1;
                String st = null, mn, da;

                if (y1 % 4 == 0 || y1 % 100 == 0 || y1 % 400 == 0)
                    am[1] = 29;

                if (yy1 == yy2)//sumation of days
                {
                    for (i = mm1 - 1; i < mm2 - 1; i++) {
                        s += am[i];
                    }
                }
                else {
                    for (i = mm1 - 1; i < 12; i++)//1st year
                        s += am[i];

                    y1+=1;
                    while(y1<yy2) {             //middle years
                        if (y1 % 4 == 0 || y1 % 100 == 0 || y1 % 400 == 0)
                            am[1] = 29;
                        else
                            am[1]=28;

                        for (i = 0; i < 12; i++)
                        {
                            s += am[i];
                        }

                        y1++;
                    }
                    for (i = 0; i < mm2 - 1; i++)//2nd year
                    {
                        s += am[i];
                    }
                }
                s -= dd1;
                s += dd2;

                int ct=0;
                if (s >= 365)
                {
                    c=12;
                    while(s>=365)
                    {
                       ct++;
                       s -= 365;
                    }

                    c*=ct;
                }
                i = mm1 - 1;

                while (s > am[i])                   //Calculating no. of months & remaining day
                {
                    s = s - am[i];
                    i++;
                    c++;

                    if (i == 11)
                        i = 0;
                }

                if (c > 0) {
                    mn = Integer.toString(c);
                    da = Integer.toString(s);
                    if(s==0)
                        st=mn+" months";
                    if(s==1)
                        st = mn + " months & " + da + " day";
                    if(s>1)
                    st = mn + " months & " + da + " days";

                }
                else
                    st = "Less than a month";

                calmon.setText(st);

        }
        });
    }
}














