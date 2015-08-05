package io.c0nnector.github.least;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

/**
 * View type
 */
public class BaseViewHolder extends RecyclerView.ViewHolder {

    /**
     * Constructor
     *
     * @param itemView
     */
    public BaseViewHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);
    }

}
