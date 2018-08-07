package com.example.mehravaran.notepad.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mehravaran.notepad.ItemData.ItemDataNotes;
import com.example.mehravaran.notepad.R;
import com.example.mehravaran.notepad.adabter.RecyclerViewAdabterNotes;
import com.example.mehravaran.notepad.database.DataBase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerViewAdabterNotes AdabterNotes;
    ArrayList<ItemDataNotes> notedetails = new ArrayList<>();
    Context context = this;
    private RecyclerView recyclerView;
    private DataBase db = new DataBase(context);
    private ImageButton I_search_text;
    private EditText E_search_text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notedetails = db.Showdata();
        initFonts();
        initActionListener();
        initRecyclerviewUseDb();
    }

    private void initRecyclerviewUseDb() {
        recyclerView = findViewById(R.id.NoteRecyclerView);
        AdabterNotes = new RecyclerViewAdabterNotes(notedetails, MainActivity.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setAdapter(AdabterNotes);
    }

    private void initFonts() {
        Typeface iranSansfont = Typeface.createFromAsset(getAssets(), "fonts/IRANSansMobile.ttf");
        TextView head = findViewById(R.id.NotepadHead);
        head.setTypeface(iranSansfont);
        Button Buttontabnewnote = findViewById(R.id.Buttontabnewnote);
        Buttontabnewnote.setTypeface(iranSansfont);
        Button Bfavorites = findViewById(R.id.Bfavorites);
        Bfavorites.setTypeface(iranSansfont);
        EditText searchedit = findViewById(R.id.E_search_text);
        searchedit.setTypeface(iranSansfont);
    }

    private void initActionListener() {
        ImageButton Inewnote = findViewById(R.id.imagetabnewnote);
        Button Bnewnote = findViewById(R.id.Buttontabnewnote);

        ImageButton Ifavorite = findViewById(R.id.Ifavorites);
        Button Bfavorite = findViewById(R.id.Bfavorites);

        I_search_text = findViewById(R.id.I_search_text);
        E_search_text = findViewById(R.id.E_search_text);

        //ImageButton cleansearch = findViewById(R.id.cleansearch);


        Inewnote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                startActivity(intent);
            }
        });
        Bnewnote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                startActivity(intent);
            }
        });


        Ifavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BookmarksActivity.class);
                startActivity(intent);
            }
        });
        Bfavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BookmarksActivity.class);
                startActivity(intent);
            }
        });



        /*if(E_search_text.getText().toString().equals(""))
            finish();
        else
*/

        I_search_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*if (E_search_text.getText().toString().contains("d")) {
                    Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                    startActivity(intent);
                }*/
                String id = getIntent().getStringExtra("id");
                Toast.makeText(getApplicationContext(),DataBase.ID,Toast.LENGTH_SHORT).show();
                //DataBase db = new DataBase(context);


                /*if (E_search_text.getText().toString().contains(getIntent().getStringExtra("title")))
                    Toast.makeText(getApplicationContext(), "ok", Toast.LENGTH_SHORT).show();
           */ }
        });


        /*cleansearch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    if (motionEvent.getX()>(view.getWidth()-view.getPaddingRight())){
                        ((EditText)view).setText("");
                    }
                }
                return false;
            }
        });*/

    }


    private boolean liked = true;
    public void buttonPressed(View view) {

        ImageButton button = (ImageButton) view;
        int icon;

        if (liked) {
            liked = false;
            icon = R.drawable.details_activity_vector_star;
        } else {
            liked = true;
            icon = R.drawable.main_activity_vector_star_red;
            Toast.makeText(getApplicationContext(),getIntent().getStringExtra("id"),Toast.LENGTH_SHORT).show();
        }

        button.setImageDrawable(
                ContextCompat.getDrawable(getApplicationContext(), icon));


    }
}
