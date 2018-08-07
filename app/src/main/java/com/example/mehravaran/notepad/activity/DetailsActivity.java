package com.example.mehravaran.notepad.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;

import com.example.mehravaran.notepad.CalendarTool;
import com.example.mehravaran.notepad.ItemData.ItemDataNotes;
import com.example.mehravaran.notepad.R;
import com.example.mehravaran.notepad.database.DataBase;

import java.util.ArrayList;
import java.util.Date;

public class DetailsActivity extends AppCompatActivity {
    private Button B_save;
    private ImageButton I_save;
    private EditText Edit_title;
    private EditText Edit_text;
    private TextView Datte;

    Context context = this;
    private DataBase dataBase = new DataBase(context);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        initActionListener();
        initFonts();
    }

    private void initFonts() {
        Typeface iranSansfont = Typeface.createFromAsset(getAssets(), "fonts/IRANSansMobile.ttf");
        TextView Edit = findViewById(R.id.Edit);
        Edit.setTypeface(iranSansfont);
        EditText enter_title = findViewById(R.id.enter_title);
        enter_title.setTypeface(iranSansfont);
        EditText enter_text = findViewById(R.id.enter_text);
        enter_text.setTypeface(iranSansfont);
        TextView last_modified_date = findViewById(R.id.last_modified_date);
        last_modified_date.setTypeface(iranSansfont);
        Button B_save_text = findViewById(R.id.B_save_text);
        B_save_text.setTypeface(iranSansfont);

    }

    private void initActionListener() {
        B_save = findViewById(R.id.B_save_text);
        I_save = findViewById(R.id.I_save_text);
        Edit_text = findViewById(R.id.enter_text);
        Edit_title = findViewById(R.id.enter_title);
        Datte = findViewById(R.id.last_modified_date);

        CalendarTool calendarTool = new CalendarTool();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String currentDateandTime = sdf.format(new Date());
        String detail_date = calendarTool.getIranianYear() + "/"
                + calendarTool.getIranianMonth() + "/"
                + calendarTool.getIranianDay() + "  "
                + currentDateandTime;
        Datte.setText(detail_date);

        final ArrayList<ItemDataNotes> noteitems = new ArrayList<>();

        B_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Edit_title.getText().toString().equals("") || Edit_text.getText().toString().equals(""))
                    Toast.makeText(getApplicationContext(), "همه ی فیلد ها الزامی هستند", Toast.LENGTH_SHORT).show();
                else {
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                    String currentDateandTime = sdf.format(new Date());
                    CalendarTool calendarTool = new CalendarTool();
                    String date = currentDateandTime + "\n" + calendarTool.getIranianYear() + "/" + calendarTool.getIranianMonth() + "/" + calendarTool.getIranianDay();
                    dataBase.InsertData(new ItemDataNotes(Edit_title.getText().toString(), Edit_text.getText().toString(), date));
                    Toast.makeText(getApplicationContext(), "یادداشت ذخیره شد", Toast.LENGTH_SHORT).show();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(DetailsActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }, 500);
                }
            }
        });
        I_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Edit_title.getText().toString().equals("") || Edit_text.getText().toString().equals(""))
                    Toast.makeText(getApplicationContext(), "همه ی فیلد ها الزامی هستند", Toast.LENGTH_SHORT).show();
                else {
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                    String currentDateandTime = sdf.format(new Date());
                    CalendarTool calendarTool = new CalendarTool();
                    String date = currentDateandTime + "\n" + calendarTool.getIranianYear() + "/" + calendarTool.getIranianMonth() + "/" + calendarTool.getIranianDay();
                    dataBase.InsertData(new ItemDataNotes(Edit_title.getText().toString(), Edit_text.getText().toString(), date));
                    Toast.makeText(getApplicationContext(), "یادداشت ذخیره شد", Toast.LENGTH_SHORT).show();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(DetailsActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }, 500);
                }
            }
        });


    }
}
