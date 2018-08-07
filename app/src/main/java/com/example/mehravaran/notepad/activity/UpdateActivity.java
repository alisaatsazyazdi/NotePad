package com.example.mehravaran.notepad.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
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

import com.example.mehravaran.notepad.CalendarTool;
import com.example.mehravaran.notepad.ItemData.ItemDataNotes;
import com.example.mehravaran.notepad.R;
import com.example.mehravaran.notepad.database.DataBase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UpdateActivity extends AppCompatActivity {

    private Button B_copy;
    private ImageButton I_copy;
    private Button Bshare;
    private ImageButton Ishare;
    private Button B_save;
    private ImageButton I_save;
    private EditText Edit_title;
    private EditText Edit_text;
    private TextView Date;
    private ImageButton I_favorite;
    private Button B_favorite;
    Context context = this;
    DataBase dataBase = new DataBase(context);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_update);
        initFonts();
        B_copy = findViewById(R.id.B_copy);
        I_copy = findViewById(R.id.I_copy);
        Bshare = findViewById(R.id.Bshare);
        Ishare = findViewById(R.id.Ishare);
        B_save = findViewById(R.id.B_save_text);
        I_save = findViewById(R.id.I_save_text);
        Edit_text = findViewById(R.id.enter_text);
        Edit_title = findViewById(R.id.enter_title);
        Date = findViewById(R.id.last_modified_date);
        I_favorite = findViewById(R.id.I_favorite);
        B_favorite = findViewById(R.id.B_favorite);

        final String id = getIntent().getStringExtra("id");
        Edit_title.setText(getIntent().getStringExtra("title"));
        Edit_text.setText(getIntent().getStringExtra("details"));

        CalendarTool calendarTool = new CalendarTool();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String currentDateandTime = sdf.format(new Date());
        String detail_date = calendarTool.getIranianYear() + "/"
                + calendarTool.getIranianMonth() + "/"
                + calendarTool.getIranianDay() + "  "
                + currentDateandTime;
        Date.setText(detail_date);
        final String date = currentDateandTime + "\n" + calendarTool.getIranianYear() + "/" + calendarTool.getIranianMonth() + "/" + calendarTool.getIranianDay();

        I_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Edit_title.getText().toString().equals("") || Edit_text.getText().toString().equals(""))
                    Toast.makeText(getApplicationContext(), "همه ی فیلد ها الزامی هستند", Toast.LENGTH_SHORT).show();
                else {
                    dataBase.Update(new ItemDataNotes(Edit_title.getText().toString(), Edit_text.getText().toString()
                            , date), Integer.parseInt(id));
                    Toast.makeText(getApplicationContext(), "اعمال تغییرات", Toast.LENGTH_SHORT).show();

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(UpdateActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }, 500);
                }
            }
        });
        B_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Edit_title.getText().toString().equals("") || Edit_text.getText().toString().equals(""))
                    Toast.makeText(getApplicationContext(), "همه ی فیلد ها الزامی هستند", Toast.LENGTH_SHORT).show();
                else {
                    dataBase.Update(new ItemDataNotes(Edit_title.getText().toString(), Edit_text.getText().toString()
                            , date), Integer.parseInt(id));
                    Toast.makeText(getApplicationContext(), "اعمال تغییرات", Toast.LENGTH_SHORT).show();

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(UpdateActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }, 500);
                }
            }
        });

        Bshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String share = getIntent().getStringExtra("title") + "\n"
                        + getIntent().getStringExtra("details") + "\n"
                        + getIntent().getStringExtra("date");
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, share);
                context.startActivity(intent);
            }
        });

        Ishare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String share = getIntent().getStringExtra("title") + "\n"
                        + getIntent().getStringExtra("details") + "\n"
                        + getIntent().getStringExtra("date");
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, share);
                context.startActivity(intent);
            }
        });

        I_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        B_copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "یادداشت کپی شد", Toast.LENGTH_SHORT).show();
                String copy = getIntent().getStringExtra("title") + "\n"
                        + getIntent().getStringExtra("details") + "\n"
                        + getIntent().getStringExtra("date");
                ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("label", copy);
                clipboard.setPrimaryClip(clip);
            }
        });

        I_copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "یادداشت کپی شد", Toast.LENGTH_SHORT).show();
                String copy = getIntent().getStringExtra("title") + "\n"
                        + getIntent().getStringExtra("details") + "\n"
                        + getIntent().getStringExtra("date");
                ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("label", copy);
                clipboard.setPrimaryClip(clip);
            }
        });
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
        Button B_favorite = findViewById(R.id.B_favorite);
        B_favorite.setTypeface(iranSansfont);
        Button B_copy = findViewById(R.id.B_copy);
        B_copy.setTypeface(iranSansfont);
        Button Bshare = findViewById(R.id.Bshare);
        Bshare.setTypeface(iranSansfont);
    }
}
