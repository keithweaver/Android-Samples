package com.weaverprojects.listview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Weaver on 15-05-04.
 */
public class ItemView {
    private TextView mTitleTextView;
    private TextView mDescriptionTextView;
    private ImageView mImageView;

    public static ItemView inflate(ViewGroup parent) {
        ItemView itemView = (ItemView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_main, parent, false);
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
        LayoutInflater.from(context).inflate(R.layout.row, this, true);
        setupChildren();
    }

    private void setupChildren() {
        mTitleTextView = (TextView) findViewById(R.id.text1);
        mDescriptionTextView = (TextView) findViewById(R.id.text2);
        //mImageView = (ImageView) findViewById(R.id.item_imageView);
    }

    public void setItem(Item item) {
        mTitleTextView.setText(item.getTitle());
        mDescriptionTextView.setText(item.getDescription());
        // TODO: set up image URL
    }

    public ImageView getImageView () {
        return mImageView;
    }

    public TextView getTitleTextView() {
        return mTitleTextView;
    }

    public TextView getDescriptionTextView() {
        return mDescriptionTextView;
    }
}
