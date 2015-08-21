package io.c0nnector.github.least;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import io.c0nnector.github.least.managers.LinearLayoutManagerWrap;

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
        setDefaultLayoutManager();
    }


    private void setDefaultLayoutManager(){

        this.manager = new LinearLayoutManager(getContext(), OrientationHelper.VERTICAL, false);
        setLayoutManager(manager);
    }

    /**
     * Will set a custom linearlayout manager that enables wrap_content in the recyclerview
     * @param enable
     */
    public void enableContentWrap(boolean enable){

        if (enable) {
            this.manager = new LinearLayoutManagerWrap(getContext(), VERTICAL, false);
            setLayoutManager(manager);
        }

        else setDefaultLayoutManager();
    }


    /*****************************************************
     * ---------------- * Divider * --------------------
     ****************************************************/

    /**
     * Adds a list item divider
     * @param set
     */
    public void setDivider(boolean set) {
        if (set) addItemDecoration(new HorizontalDividerItemDecoration.Builder(getContext()).build());
        //// TODO: on false remove decoration
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

    /**
     * Horizontal orientation
     * @param setHorizontalOrientation
     */
    public void horizontal(boolean setHorizontalOrientation){

        if (manager !=null) {
            if (setHorizontalOrientation) manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        }
    }

    /**
     * Vertical orientation
     * @param setVerticalOrientation
     */
    public void vertical(boolean setVerticalOrientation){

        if (manager !=null) {
            if (setVerticalOrientation) manager.setOrientation(LinearLayoutManager.VERTICAL) ;
        }
    }
}
