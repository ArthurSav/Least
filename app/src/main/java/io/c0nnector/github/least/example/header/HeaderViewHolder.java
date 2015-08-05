package io.c0nnector.github.least.example.header;

import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import io.c0nnector.github.least.example.R;
import io.c0nnector.github.leastview.BaseViewHolder;

/**
 * Created by arthur on 8/5/15.
 */
public class HeaderViewHolder extends BaseViewHolder{

    @Bind(R.id.txtTitle)
    public TextView txtTitle;

    /**
     * Constructor
     *
     * @param itemView
     */
    public HeaderViewHolder(View itemView) {

        super(itemView);
    }
}
