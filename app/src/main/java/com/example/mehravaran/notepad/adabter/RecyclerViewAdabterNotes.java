package com.example.mehravaran.notepad.adabter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mehravaran.notepad.ItemData.ItemDataNotes;
import com.example.mehravaran.notepad.R;
import com.example.mehravaran.notepad.activity.UpdateActivity;
import com.example.mehravaran.notepad.database.DataBase;

import java.util.ArrayList;

public class RecyclerViewAdabterNotes extends RecyclerView.Adapter<RecyclerViewAdabterNotes.ViewHolder> {
    ArrayList<ItemDataNotes> itemDataNotes;
    Activity activity;
    DataBase dataBase;


    public RecyclerViewAdabterNotes(ArrayList<ItemDataNotes> itemDataNotes, Activity activity) {
        this.itemDataNotes = itemDataNotes;
        this.activity = activity;
        dataBase = new DataBase(activity);

    }

    @Override
    public RecyclerViewAdabterNotes.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main_recycler_view, parent, false);
        return new ViewHolder(itemLayoutView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        final ItemDataNotes items = itemDataNotes.get(position);
        viewHolder.title.setText(itemDataNotes.get(position).getNotetitle());
        viewHolder.details.setText(itemDataNotes.get(position).getNotedetails());
        viewHolder.date.setText(itemDataNotes.get(position).getNotelastmodifieddate());

        ////////////fonts///////////////
        Typeface iranSansfont = Typeface.createFromAsset(viewHolder.itemView.getContext().getAssets(), "fonts/IRANSansMobile.ttf");
        Typeface BMitra = Typeface.createFromAsset(viewHolder.itemView.getContext().getAssets(), "fonts/BMitra.ttf");
        Typeface TitrBold = Typeface.createFromAsset(viewHolder.itemView.getContext().getAssets(), "fonts/TitrBold.ttf");
        viewHolder.date.setTypeface(iranSansfont);
        viewHolder.title.setTypeface(TitrBold);
        viewHolder.details.setTypeface(BMitra);


        viewHolder.Edit_text_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecyclerViewAdabterNotes.this.activity, UpdateActivity.class);
                intent.putExtra("id", items.getId() + "");
                intent.putExtra("title", items.getNotetitle());
                intent.putExtra("details", items.getNotedetails());
                intent.putExtra("date", items.getNotelastmodifieddate());
                activity.startActivity(intent);
            }
        });

        viewHolder.Edit_text_layout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setTitle("حذف اطلاعات");
                builder.setIcon(R.drawable.main_activity_vector_delete);
                builder.setMessage("آیا مطمئن هستید ؟");
                builder.setCancelable(false);
                builder.setPositiveButton("بله", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dataBase.Delete(items.getId());
                        itemDataNotes.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeRemoved(position, itemDataNotes.size());
                        Toast.makeText(activity, "آیتم مورد نظر حذف شد", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("خیر", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(activity, "لغو", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();

                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return itemDataNotes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView details;
        TextView date;
        LinearLayout Edit_text_layout;

        ViewHolder(View itemLayoutView) {

            super(itemLayoutView);
            title = itemLayoutView.findViewById(R.id.NotePadTitle);
            details = itemLayoutView.findViewById(R.id.NoteDetails);
            date = itemLayoutView.findViewById(R.id.date);
            Edit_text_layout = itemLayoutView.findViewById(R.id.edit_text_layout);


        }
    }

}
