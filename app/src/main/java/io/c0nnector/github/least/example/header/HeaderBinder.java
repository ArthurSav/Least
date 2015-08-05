package io.c0nnector.github.least.example.header;

import android.support.annotation.LayoutRes;

import io.c0nnector.github.least.Binder;


/**
 * Created by arthur on 8/5/15.
 */
public class HeaderBinder extends Binder<HeaderViewHolder, String> {

    public HeaderBinder(Class<String> stringClass, Class<HeaderViewHolder> cls, @LayoutRes int layoutId) {
        super(stringClass, cls, layoutId);
    }

    @Override
    public void onBindViewHolder(HeaderViewHolder holder, String s, int position) {
        holder.txtTitle.setText(s);
    }
}
