package com.weaverprojects.listview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Weaver on 15-05-07.
 */
public class ItemView extends RelativeLayout {
    private TextView mTextView;
    public static ItemView inflate(ViewGroup parent) {
        ItemView itemView = (ItemView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_template, parent, false);
        return itemView;
    }
    public ItemView(Context c) {
        this(c, null);
    }

    public ItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        LayoutInflater.from(context).inflate(R.layout.row_template, this, true);
        setupChildren();
    }
    private void setupChildren() {
        mTextView = (TextView) findViewById(R.id.text1);
    }
    public void setItem(Item item) {
        mTextView.setText(item.getText());
    }
    public TextView getText(){
        return mTextView;
    }
}
