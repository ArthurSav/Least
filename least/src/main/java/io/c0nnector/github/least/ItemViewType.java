package io.c0nnector.github.least;

/**
 * Helps to tie a single object type with many view holders
 */
public interface ItemViewType {


    /**
     * Used to match the object type with a viewholder type
     */
    int getViewType();
}
