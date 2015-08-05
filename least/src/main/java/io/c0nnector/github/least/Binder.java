package io.c0nnector.github.least;


import android.support.annotation.LayoutRes;
import android.view.ViewGroup;

import io.c0nnector.github.least.util.UtilList;


/**
 * Binds list objects to viewholders
 *
 * @param <Viewholder>
 * @param <Item>
 */
public abstract class Binder<Viewholder extends BaseViewHolder, Item> {

    ListCallbacks<Viewholder, Item> callback;


    @LayoutRes
    int layoutId;

    Class<Item> itemClass;

    Class<Viewholder> cls;

    /**
     * Constructor
     *
     * @param itemClass An object to be tied with the viewholder. e.g User.class is tied to UserViewholder.class
     * @param cls viewholder class to be tied to a list object e.g UserViewholder.class is tied to User.class
     * @param layoutId layout id for the viewholder view
     */
    public Binder(Class<Item> itemClass, Class<Viewholder> cls, @LayoutRes int layoutId) {
        this.itemClass = itemClass;
        this.cls = cls;
        this.layoutId = layoutId;
    }

    /**
     * Option to add a onBind callback
     *
     * @param itemClass
     * @param cls
     * @param layoutId
     * @param callback
     */
    public Binder(Class<Item> itemClass, Class<Viewholder> cls, @LayoutRes int layoutId, ListCallbacks<Viewholder, Item> callback) {
        this.itemClass = itemClass;
        this.cls = cls;
        this.layoutId = layoutId;
        this.callback = callback;
    }


    /**
     * Creates a viewholder given the class and layout
     * @param parent
     * @return
     */
    public Viewholder getViewHolder(ViewGroup parent) {
        return UtilList.getViewHolder(parent, layoutId, cls);
    }

    /**
     * Called when the adapter's onBind matches a viewholder to a list item
     * A callback will be invoked too, if defined
     *
     * @param holder viewholder
     * @param item list item tied to the viewholder
     * @param position list position
     */
    public void onBindCallback(Viewholder holder, Item item, int position){

        onBindViewHolder(holder, item, position);

        if (callback !=null) callback.onBindViewHolder(holder, item, position);
    }

    /**
     * Returns a unique identifier for this view type
     * @return
     */
    public int getViewType(){
        return UtilList.getClassId(itemClass);
    }


    public abstract void onBindViewHolder(Viewholder holder, Item item, int position);

}
