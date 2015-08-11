package io.c0nnector.github.least;


import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import io.c0nnector.github.least.util.UtilList;


/**
 * Binds list objects to viewholders
 *
 * @param <Viewholder>
 * @param <Item>
 */
public abstract class Binder<Viewholder extends BaseViewHolder, Item> {

    @Nullable
    protected Context context;

    @Nullable
    BindListener<Viewholder, Item> bindListener;

    @Nullable
    ListItemListener<Viewholder, Item> listItemListener;

    @LayoutRes
    int layoutId;

    @NonNull
    Class<Item> itemClass;

    @NonNull
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
     * Constructor
     *
     * @param context
     * @param itemClass An object to be tied with the viewholder. e.g User.class is tied to UserViewholder.class
     * @param cls viewholder class to be tied to a list object e.g UserViewholder.class is tied to User.class
     * @param layoutId layout id for the viewholder view
     */
    public Binder(Context context, Class<Item> itemClass, Class<Viewholder> cls, @LayoutRes int layoutId) {
        this.itemClass = itemClass;
        this.cls = cls;
        this.layoutId = layoutId;
        this.context = context;
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
    public void onBindCallback(final Viewholder holder, final Item item, final int position){

        onBindViewHolder(holder, item, position);

        //bind callback
        if (bindListener !=null) bindListener.onBindViewHolder(holder, item, position);

        //list item click callback
        if (listItemListener !=null) {

            holder.itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    listItemListener.onListItemClick(holder, item, position);
                }
            });
        }
    }

    /**
     * Returns a unique identifier for this view type
     * @return
     */
    public int getViewType(){
        return UtilList.getClassId(itemClass);
    }


    public abstract void onBindViewHolder(Viewholder holder, Item item, int position);



    /*****************************************************
     * ---------------- * Listeners * --------------------
     *
     *
     *
     ****************************************************/

    /**
     * OnBind listener
     * @return
     */
    public Binder setBindListener(BindListener<Viewholder, Item> bindListener){
        this.bindListener = bindListener;

        return this;
    }

    /**
     * List item click listener
     * @return
     */
    public Binder setListItemClickListener(ListItemListener<Viewholder, Item> listener){
        this.listItemListener = listener;

        return this;
    }

}
