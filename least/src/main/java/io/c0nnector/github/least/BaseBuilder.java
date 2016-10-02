package io.c0nnector.github.least;

import android.content.Context;

import java.util.List;

public abstract class BaseBuilder<CustomBuilder, CustomAdapter extends LeastAdapter> {

    public abstract CustomBuilder item(Object item);

    public abstract CustomBuilder items(List items);

    public abstract CustomBuilder binder(Binder binder);

    public abstract CustomBuilder binders(List<Binder> viewTypes);

    public abstract CustomAdapter build(Context context);

    abstract List getItems();
    abstract List<Binder> getBinders();
}
