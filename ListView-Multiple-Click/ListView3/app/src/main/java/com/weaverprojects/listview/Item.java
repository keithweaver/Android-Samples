package com.weaverprojects.listview;

/**
 * Created by Weaver on 15-05-07.
 */
public class Item {
    private String mText;
    public Item(String text){
        super();
        mText = text;
    }
    public String getText(){
        return mText;
    }
    public void setText(String text){
        mText = text;
    }
}
