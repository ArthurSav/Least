package io.c0nnector.github.least.example.banner;

import android.view.View;
import android.widget.TextView;

import io.c0nnector.github.least.BaseViewHolder;
import io.c0nnector.github.least.example.R;


public class BannerViewHolderHolder extends BaseViewHolder {


    public TextView textView;

    /**
     * Constructor
     *
     * @param itemView
     */
    public BannerViewHolderHolder(View itemView) {
        super(itemView);

        textView = (TextView) itemView.findViewById(R.id.textView2);
    }
}
