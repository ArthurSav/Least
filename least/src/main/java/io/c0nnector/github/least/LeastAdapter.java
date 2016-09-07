
package io.c0nnector.github.least;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import io.c0nnector.github.least.util.UtilList;


/**
 * This recyclerview adapter makes it very easy to create an adapter with multiple view types. <p/>
 * Use binders to create different list view types. Each binder has a unique view & object (e.g
 * UserViewHolder.class & User.class) The adapter will 'glue' together objects to their correlating
 * view holder. <p/> Check the example for usage
 *
 * @see Binder
 */
public class LeastAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {


    Context context;

    /**
     * List binders. They define the viewtypes & object relations to viewholders
     */
    List<Binder> viewTypes;

    /**
     * List objects
     */
    List<T> items;

    /**
     * Constructor
     *
     * @param context
     * @param builder
     */
    private LeastAdapter(Context context, Builder<T> builder) {
        this.context = context;
        this.viewTypes = builder.viewTypes;
        this.items = builder.items;
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

        for (Binder binder : viewTypes) {

            int baseBinderType = binder.getViewType();

            if (baseBinderType == viewType) {

                BaseViewHolder holder = binder.getViewHolder(parent);
                binder.onCreateViewHolder(holder);

                return holder;
            }
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

        for (Binder binder : viewTypes) {

            if (binder.getViewType() == getItemViewType(position)) {

                //noinspection unchecked
                binder.onBindCallback(holder, getItem(position), position);

                break;
            }
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

        T listItem = getItem(position);

        //handles objects that have more than one view in the list
        if (listItem instanceof ItemViewType) {
            return ((ItemViewType) listItem).getViewType();
        }

        return UtilList.getObjectId(getItem(position));
    }


    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    /**
     * @param position objection position in the list
     *
     * @return list object from certain position
     */
    public T getItem(int position) {
        return items.get(position);
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
        return viewTypes;
    }

    /**
     * Positions occupied by the list
     *
     * @return
     */
    public int getItemPositions() {
        return getItemCount() > 0 ? getItemCount() - 1 : 0;
    }

    /*****************************************************
     * ---------------- * Builder * --------------------
     ****************************************************/

    public static class Builder<T> {


        private List<T> items = new ArrayList<>();

        List<Binder> viewTypes = new ArrayList<>();


        public Builder item(T item) {
            this.items.add(item);
            return this;
        }

        public Builder items(List<T> items) {
            this.items.addAll(items);
            return this;
        }

        public Builder binder(Binder binder) {
            this.viewTypes.add(binder);
            return this;
        }

        public Builder binders(List<Binder> viewTypes) {
            this.viewTypes.addAll(viewTypes);
            return this;
        }

        public LeastAdapter<T> build(Context context) {
            return new LeastAdapter<>(context, this);
        }
    }

    /*****************************************************
     * ---------------- * Helpers * --------------------
     *
     *
     *
     ****************************************************/

    /**
     * Adds items to the list
     *
     * @param items
     */
    public void add(@NonNull List<T> items) {

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
    public void replace(@NonNull List<T> items) {

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

}
