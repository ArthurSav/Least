package io.c0nnector.github.least.example.user;


import android.support.annotation.LayoutRes;

import io.c0nnector.github.least.Binder;
import io.c0nnector.github.least.util.UtilList;

public class SecondUserBinder extends Binder<UserSecondViewHolder, User>{


    public SecondUserBinder(Class<User> userClass, Class<UserSecondViewHolder> cls, @LayoutRes int layoutId) {
        super(userClass, cls, layoutId);
    }

    @Override
    public void onBindViewHolder(UserSecondViewHolder holder, User user, int position) {

    }

    @Override
    public int getViewType() {
        return 1;
    }
}
