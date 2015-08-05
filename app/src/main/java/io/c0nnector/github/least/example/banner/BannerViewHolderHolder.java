package io.c0nnector.github.least.example.banner;

import android.view.View;
import android.widget.TextView;

import butterknife.Bind;

import io.c0nnector.github.least.example.R;
import io.c0nnector.github.least.BaseViewHolder;


public class BannerViewHolderHolder extends BaseViewHolder{

    @Bind(R.id.textView2)
    public TextView textView;

    /**
     * Constructor
     *
     * @param itemView
     */
    public BannerViewHolderHolder(View itemView) {
        super(itemView);
    }
}
