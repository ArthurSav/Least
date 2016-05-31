package io.c0nnector.github.least.util;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.InvocationTargetException;


public class UtilList {


    /**
     * Inflates a viewholder of any type
     *
     * @param viewGroup
     * @param viewId
     * @param viewHolderClass
     * @param <T>
     *
     * @return
     */
    public static <T extends RecyclerView.ViewHolder> T getViewHolder(ViewGroup viewGroup, @LayoutRes int viewId, Class<T> viewHolderClass) {

        //create view from layout
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(viewId, viewGroup, false);

        //View holder class
        T holder = null;

        try {

            //create new instance of the given holder class. Invoking constructor with generics
            holder = viewHolderClass.getDeclaredConstructor(View.class).newInstance(v);

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return holder;
    }

    /**
     * Creates an object id based on the object's name
     *
     * @param object
     *
     * @return
     */
    public static int getObjectId(Object object) {
        return object.getClass().getCanonicalName().hashCode();
    }

    public static <T> int getClassId(Class<T> cls) {
        return cls.getCanonicalName().hashCode();
    }
}
