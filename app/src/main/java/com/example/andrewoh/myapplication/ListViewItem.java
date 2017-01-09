package com.example.andrewoh.myapplication;


public class ListViewItem {
    private String name;
    private String score;

    public ListViewItem(String name, String score){
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return this.name;
    }
    public String getScore() {
        return this.score;
    }
}
