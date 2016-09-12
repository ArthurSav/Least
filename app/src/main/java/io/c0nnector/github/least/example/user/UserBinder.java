package io.c0nnector.github.least.example.user;

import io.c0nnector.github.least.Binder;
import io.c0nnector.github.least.example.R;


public class UserBinder extends Binder<UserViewHolderHolder, User>{

    @Override
    public void onBindViewHolder(UserViewHolderHolder holder, User user, int position) {
        holder.textView.setText(user.getName());
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_user;
    }

    @Override
    public Class<UserViewHolderHolder> getViewHolderClass() {
        return UserViewHolderHolder.class;
    }

    @Override
    public Class<User> getItemClass() {
        return User.class;
    }
}
