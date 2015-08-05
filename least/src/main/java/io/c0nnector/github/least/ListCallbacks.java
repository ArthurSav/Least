package io.c0nnector.github.least;

public interface ListCallbacks<Viewholder extends BaseViewHolder, Item>{

    /**
     * Called when the list adapter's onBind is called, for a specific type of view
     * @param holder
     * @param item
     * @param position
     */
    void onBindViewHolder(Viewholder holder, Item item, int position);
}
