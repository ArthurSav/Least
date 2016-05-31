package io.c0nnector.github.least.example.header;

import io.c0nnector.github.least.Binder;
import io.c0nnector.github.least.example.R;


public class HeaderBinder extends Binder<HeaderViewHolder, String> {


    @Override
    public void onBindViewHolder(HeaderViewHolder holder, String s, int position) {
        holder.txtTitle.setText(s);
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_header;
    }

    @Override
    public Class<HeaderViewHolder> getViewHolderClass() {
        return HeaderViewHolder.class;
    }

    @Override
    public Class<String> getItemClass() {
        return String.class;
    }
}
