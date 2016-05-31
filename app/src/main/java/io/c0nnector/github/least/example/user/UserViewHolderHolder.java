package io.c0nnector.github.least.example.user;


import android.view.View;
import android.widget.TextView;

import io.c0nnector.github.least.BaseViewHolder;
import io.c0nnector.github.least.example.R;

public class UserViewHolderHolder extends BaseViewHolder {


    public TextView textView;

    /**
     * Constructor
     *
     * @param itemView
     */
    public UserViewHolderHolder(View itemView) {
        super(itemView);
        textView = (TextView) itemView.findViewById(R.id.textView);
    }


}
