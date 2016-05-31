package io.c0nnector.github.least.example.header;

import android.view.View;
import android.widget.TextView;

import io.c0nnector.github.least.BaseViewHolder;
import io.c0nnector.github.least.example.R;


public class HeaderViewHolder extends BaseViewHolder {


    public TextView txtTitle;

    /**
     * Constructor
     *
     * @param itemView
     */
    public HeaderViewHolder(View itemView) {
        super(itemView);

        txtTitle = (TextView) itemView.findViewById(R.id.txtTitle);
    }
}
