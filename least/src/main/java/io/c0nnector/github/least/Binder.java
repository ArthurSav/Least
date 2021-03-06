package io.c0nnector.github.least;


import android.content.Context;
import android.support.annotation.LayoutRes;
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


    public Binder(Context context) {
        this.context = context;
    }

    public Binder() {
    }

    /**
     * Creates a viewholder given the class and layout
     *
     * @param parent
     *
     * @return
     */
    public Viewholder getViewHolder(ViewGroup parent) {
        return UtilList.getViewHolder(parent, getLayoutId(), getViewHolderClass());
    }

    /**
     * Called when the viewholder for this binder is created
     * @param viewholder created viewholder
     */
    public void onCreateViewHolder(Viewholder viewholder){}

    /**
     * Called when the adapter's onBind matches a viewholder to a list item A callback will be
     * invoked too, if defined
     *
     * @param holder   viewholder
     * @param item     list item tied to the viewholder
     * @param position list position
     */
    public void onBindCallback(final Viewholder holder, final Item item, final int position) {

        onBindViewHolder(holder, item, position);

        //bind callback
        if (bindListener != null) bindListener.onBindViewHolder(holder, item, position);

        //list item click callback
        holder.itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if(listItemListener != null) listItemListener.onListItemClick(holder, item, position);
                }
            });
    }

    /**
     * Use this if you want have different binders for the same view object.
     *
     * i.e have BinderUserMe.class, BinderUserOther.class could both belong to User.class. Binders could be returned based on position or other criteria
     *
     * @return true if you want to use this view type (default), false if you want to skip.
     */
    public boolean onViewType(Item item, int position){
        return true;
    }


    public abstract void onBindViewHolder(Viewholder holder, Item item, int position);

    /**
     * Layout id used to create the viewholder
     *
     * @return
     */
    @LayoutRes
    public abstract int getLayoutId();

    /**
     * Class of the viewholder
     *
     * @return
     */
    public abstract Class<Viewholder> getViewHolderClass();

    /**
     * Class of the object that is binded to this view
     *
     * @return
     */
    public abstract Class<Item> getItemClass();

    /**
     * Override to set a custom view type. By default we use the item class to identify the type
     * @return
     */
    public int getViewType(){
        return -1;
    }

    public boolean isViewTypeCustom(){
        return getViewType() != -1;
    }

    /*****************************************************
     * ---------------- * Listeners * --------------------
     *
     *
     *
     ****************************************************/

    /**
     * OnBind listener
     *
     * @return
     */
    public Binder setBindListener(BindListener<Viewholder, Item> bindListener) {
        this.bindListener = bindListener;
        return this;
    }

    /**
     * List item click listener
     *
     * @return
     */
    public Binder setListItemClickListener(ListItemListener<Viewholder, Item> listener) {
        this.listItemListener = listener;
        return this;
    }

}
