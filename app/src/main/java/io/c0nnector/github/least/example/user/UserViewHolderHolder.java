package io.c0nnector.github.least.example.user;


import android.view.View;
import android.widget.TextView;

import butterknife.Bind;

import io.c0nnector.github.least.example.R;
import io.c0nnector.github.leastview.BaseViewHolder;

public class UserViewHolderHolder  extends BaseViewHolder{

    @Bind(R.id.textView)
    public TextView textView;

    /**
     * Constructor
     *
     * @param itemView
     */
    public UserViewHolderHolder(View itemView) {

        super(itemView);
    }
}
