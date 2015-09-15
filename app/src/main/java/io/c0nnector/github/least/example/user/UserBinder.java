package io.c0nnector.github.least.example.user;

import android.support.annotation.LayoutRes;

import io.c0nnector.github.least.Binder;


public class UserBinder extends Binder<UserViewHolderHolder, User> {

    /**
     * Constructor
     *
     * @param userClass
     * @param cls
     * @param layoutId
     */
    public UserBinder(Class<User> userClass, Class<UserViewHolderHolder> cls, @LayoutRes int layoutId) {
        super(userClass, cls, layoutId);
    }

    @Override
    public void onBindViewHolder(UserViewHolderHolder holder, User user, int position) {
        holder.textView.setText(user.getName());
    }

    @Override
    public int getViewType() {
        return 0;
    }
}
