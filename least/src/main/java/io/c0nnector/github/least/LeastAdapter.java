package io.c0nnector.github.least;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import io.c0nnector.github.least.util.UtilList;


/**
 * A recyclerview adapter that makes it very easy to add multiple view types inside a list.
 * Use it with a binder
 * @see Binder
 */
public class LeastAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    Context context;

    /**
     * List binders. They define the viewtypes & object relations to viewholders
     */
    List<Binder> viewTypes;

    /**
     * List objects
     */
    List<Object> items;

    /**
     * Constructor
     *
     * @param context
     * @param builder
     */
    private LeastAdapter(Context context, Builder builder) {
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

            if (baseBinderType == viewType) return  binder.getViewHolder(parent);
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
     * Types are created from the list object class name.
     * We use it to tie binders & list objects
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        return UtilList.getObjectId(getItem(position));
    }


    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }


    public Object getItem(int position){
        return items.get(position);
    }

    public void addItem(Object object){
        this.items.add(object);
    }

    public List<Object> getList(){
        return items;
    }

    /**
     * Positions occupied by the list
     * @return
     */
    public int getItemPositions() {
        return getItemCount() > 0? getItemCount() - 1 : 0;
    }

    /*****************************************************
     * ---------------- * Builder * --------------------
     *
     *
     *
     ****************************************************/

    public static class Builder {

        private List<Object> items = new ArrayList<>();

        List<Binder> viewTypes = new ArrayList<>();

        public Builder item(Object item){
            this.items.add(item);
            return this;
        }

        public Builder items(List items){

            //noinspection unchecked
            this.items.addAll(items);
            return this;
        }

        public Builder binder(Binder binder){
            this.viewTypes.add(binder);
            return this;
        }

        public Builder binders(List<Binder> viewTypes){
            this.viewTypes.addAll(viewTypes);
            return this;
        }

        public LeastAdapter build(Context context){
            return new LeastAdapter(context, this);
        }
    }
}
