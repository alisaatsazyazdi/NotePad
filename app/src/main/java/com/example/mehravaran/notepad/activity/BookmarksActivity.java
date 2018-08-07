package com.example.mehravaran.notepad.activity;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.mehravaran.notepad.ItemData.ItemDataNotes;
import com.example.mehravaran.notepad.R;
import com.example.mehravaran.notepad.adabter.RecyclerViewAdabterNotes;
import com.example.mehravaran.notepad.database.DataBase;

import java.util.ArrayList;

public class BookmarksActivity extends AppCompatActivity {
    RecyclerViewAdabterNotes AdabterNotes;
    ArrayList<ItemDataNotes> notedetails = new ArrayList<>();
    Context context = this;
    private RecyclerView recyclerView;
    private DataBase db = new DataBase(context);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmarks);
        notedetails = db.Showdata();
        initRecyclerviewUseDb();
        initFonts();

    }

    private void initFonts() {
        Typeface iranSansfont = Typeface.createFromAsset(getAssets(), "fonts/IRANSansMobile.ttf");
        TextView favorites = findViewById(R.id.favorites);
        favorites.setTypeface(iranSansfont);
    }

    private void initRecyclerviewUseDb() {
        recyclerView = findViewById(R.id.favoriteNoteRecyclerView);
        AdabterNotes = new RecyclerViewAdabterNotes(notedetails, BookmarksActivity.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(BookmarksActivity.this));
        recyclerView.setAdapter(AdabterNotes);
    }

}
