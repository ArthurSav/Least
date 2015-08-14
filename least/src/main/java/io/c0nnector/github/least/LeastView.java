package io.c0nnector.github.least;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

/**
 * An extension of the recyclerview. Adds some useful functionality
 */
public class LeastView extends RecyclerView {

    /**
     * Default layout manager
     */
    LinearLayoutManager manager;


    public LeastView(Context context) {
        super(context);
    }

    public LeastView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LeastView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        init(getContext());
    }

    protected void init(Context context) {

        manager = new LinearLayoutManager(context);
        manager.setOrientation(OrientationHelper.VERTICAL);

        setLayoutManager(manager);
    }

    /*****************************************************
     * ---------------- * Divider * --------------------
     ****************************************************/

    public void setDivider(boolean set) {
        if (set)
            addItemDecoration(new HorizontalDividerItemDecoration.Builder(getContext()).build());
    }

    public void setDivider(HorizontalDividerItemDecoration horizontalDividerItemDecoration) {
        addItemDecoration(horizontalDividerItemDecoration);
    }

    /*****************************************************
     * ---------------- * Orientation * --------------------
     *
     *
     *
     ****************************************************/

    public void horizontal(boolean setHorizontalOrientation){

        if (manager !=null) {

            if (setHorizontalOrientation) manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        }
    }

    public void vertical(boolean setVerticalOrientation){

        if (manager !=null) {

            if (setVerticalOrientation) manager.setOrientation(LinearLayoutManager.VERTICAL);
        }
    }
}
