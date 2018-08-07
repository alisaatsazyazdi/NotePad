package com.example.mehravaran.notepad.ItemData;

public class ItemDataNotes {
    private String notetitle;
    private String notedetails;
    private String notelastmodifieddate;
    private int id;
    private boolean favorite;


    public ItemDataNotes() {
    }

    public ItemDataNotes(String notetitle, String notedetails, String notelastmodifieddate, boolean favorite) {
        this.notetitle = notetitle;
        this.notedetails = notedetails;
        this.notelastmodifieddate = notelastmodifieddate;
        this.favorite = favorite;

    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNotetitle() {
        return notetitle;
    }

    public void setNotetitle(String notetitle) {
        this.notetitle = notetitle;
    }

    public String getNotedetails() {
        return notedetails;
    }

    public void setNotedetails(String notedetails) {
        this.notedetails = notedetails;
    }

    public String getNotelastmodifieddate() {
        return notelastmodifieddate;
    }

    public void setNotelastmodifieddate(String notelastmodifieddate) {
        this.notelastmodifieddate = notelastmodifieddate;
    }
}
