package com.example.andrewoh.myapplication;

//리스트뷰에 들어갈 정보를 저장하고 뿌리는 클래스
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
