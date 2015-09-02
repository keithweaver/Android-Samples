package com.weaverprojects.customadapter;

/**
 * Created by Keith on 2015-09-02.
 */
public class SingleRow {
    String mainText;
    String subText;

    public SingleRow(String m, String s){
        this.mainText = m;
        this.subText = s;
    }
    public String getMainText(){
        return this.mainText;
    }
    public String getSubText(){
        return this.subText;
    }
}
