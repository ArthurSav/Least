
package io.c0nnector.github.least;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * This recyclerview adapter makes it very easy to create an adapter with multiple view types. <p/>
 * Use binders to create different list view types. Each binder has a unique view & object (e.g
 * UserViewHolder.class & User.class) The adapter will 'glue' together objects to their correlating
 * view holder. <p/> Check the example for usage
 *
 * @see Binder
 */
@SuppressWarnings("unchecked")
public class LeastAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {

    private static final int VIEWTYPE_NONE = -1;

    Context context;

    /**
     * List binders. They define the viewtypes & object relations to viewholders
     */
    List<Binder> binders;

    /**
     * List objects
     */
    List items;

    /**
     * Constructor
     *
     * @param context
     * @param builder
     */
    protected LeastAdapter(Context context, Builder builder) {
        this.context = context;
        this.binders = builder.getBinders();
        this.items = builder.getItems();
    }

    /**
     * Matches a binderType to a viewType
     *
     * @param parent
     * @param viewType see getItemViewType()
     *
     * @return viewholder to show
     */
    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType != VIEWTYPE_NONE) {

            Binder binder = binders.get(viewType);

            BaseViewHolder holder = binder.getViewHolder(parent);
            binder.onCreateViewHolder(holder);

            return holder;
        }

        //no binder found, return empty view so it doesn't crash
        return new BaseViewHolder(new View(context));
    }

    /**
     * Matches a binderType to a viewType, handles list item binding inside the appropriate binder
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {

        int itemViewType = getItemViewType(position);

        if (itemViewType != VIEWTYPE_NONE) {

            Binder binder = binders.get(itemViewType);

            //noinspection unchecked
            binder.onBindCallback(holder, getItem(position), position);
        }
    }

    /**
     * Types are created from the list object class name. We use it to tie binders & list objects
     *
     * @param position
     *
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        Object listItem = getItem(position);
        return getItemViewType(listItem);
    }

    /**
     * Identifies a list as a view type
     * @param item list item
     * @return existing viewtype from the binder list
     */
    private int getItemViewType(Object item){

        for (int i = 0; i < binders.size(); i++) {

            Binder binder = binders.get(i);

            if (item instanceof ItemViewType && binder.isViewTypeCustom() ) {
                int itemType = ((ItemViewType) item).getViewType();
                int binderType = binder.getViewType();
                if (itemType == binderType) return i;
            }

            else if (!binder.isViewTypeCustom() && binder.getItemClass().isInstance(item)) return i;
        }

        return VIEWTYPE_NONE;
    }



    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    /**
     * @param position item position in the list
     *
     * @return item from position, if any
     */
    public T getItem(int position) {
        return (T) items.get(position);
    }

    /**
     * @return list of registered adapter items
     */
    public List<T> getList() {
        return items;
    }

    /**
     * @return list of defined binders
     */
    public List<Binder> getBinders() {
        return binders == null? binders = new ArrayList<>(): binders;
    }

    /**
     * Positions occupied by the list
     *
     * @return
     */
    public int getItemPositions() {
        return getItemCount() > 0 ? getItemCount() - 1 : 0;
    }

    ///////////////////////////////////////////////////////////////////////////
    // Add / remove
    ///////////////////////////////////////////////////////////////////////////

    /**
     * Adds items to the list
     *
     * @param items
     */
    public void add(@NonNull List items) {

        int previousCount = getItemPositions();

        getList().addAll(items);

        notifyItemRangeInserted(previousCount + 1, items.size());
    }

    /**
     * Adds an item at the end of the list
     *
     * @param item
     */
    public void add(T item) {

        getList().add(item);

        int size = getItemPositions();

        notifyItemInserted(size);

        notifyItemRangeChanged(size, 1);
    }

    /**
     * Adds a new item at a specified position
     *
     * @param item
     * @param position list position to insert
     */
    public void add(T item, int position) {

        getList().add(position, item);

        notifyItemInserted(position);

        notifyItemRangeChanged(position, getItemCount());
    }

    /**
     * Replace a list item at a given position
     *
     * @param item     item to replace with
     * @param position position to replace
     */
    public void replace(T item, int position) {

        getList().set(position, item);

        notifyItemChanged(position);
    }

    /**
     * Replaces current list of items
     *
     * @param items
     */
    public void replace(@NonNull List items) {

        getList().clear();

        getList().addAll(items);

        notifyDataSetChanged();
    }

    /**
     * Remove an item
     *
     * @param position item position in the list
     */
    public void remove(int position) {

        getList().remove(position);

        notifyItemRemoved(position);

        notifyItemRangeChanged(position, getItemCount());
    }

    /**
     * Removes all items from the list
     */
    public void removeAll() {

        int size = getItemCount();

        getList().clear();

        notifyItemRangeRemoved(0, size);
    }

    /**
     * @param binders a list of binders to add
     */
    public void addBinder(Binder... binders) {
        for (Binder binder : binders) {
            getBinders().add(binder);
        }
        notifyDataSetChanged();
    }

    /**
     * removes current binders
     */
    public void removeBinders(){
        getBinders().clear();
        notifyDataSetChanged();
    }

    /**
     * replaces current binders
     * @param binders binders to replace with
     */
    public void replaceBinders(Binder... binders){
        getBinders().clear();
        addBinder(binders);
    }

    /**
     * Checks if a position is valid in the list
     *
     * @param position
     *
     * @return
     */
    public boolean positionExists(int position) {

        int size = getItemCount() - 1;

        return position <= size;
    }

    ///////////////////////////////////////////////////////////////////////////
    // Builder
    ///////////////////////////////////////////////////////////////////////////

    public static class Builder extends BaseBuilder<Builder, LeastAdapter>{

        private List items = new ArrayList<>();
        private List<Binder> binders = new ArrayList<>();

        @Override
        public Builder item(Object item) {
            this.items.add(item);
            return this;
        }

        @Override
        public Builder items(List items) {
            this.items.addAll(items);
            return this;
        }

        @Override
        public Builder binder(Binder binder) {
            this.binders.add(binder);
            return this;
        }

        @Override
        public Builder binders(List<Binder> viewTypes) {
            this.binders.addAll(viewTypes);
            return this;
        }

        @Override
        public LeastAdapter build(Context context) {
            return new LeastAdapter<>(context, this);
        }

        @Override
        public List getItems() {
            return items;
        }

        @Override
        List<Binder> getBinders() {
            return binders;
        }
    }


}
