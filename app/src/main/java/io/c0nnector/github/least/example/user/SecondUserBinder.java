package io.c0nnector.github.least.example.user;


import io.c0nnector.github.least.Binder;
import io.c0nnector.github.least.example.R;

public class SecondUserBinder extends Binder<UserSecondViewHolder, User>{


    @Override
    public void onBindViewHolder(UserSecondViewHolder holder, User user, int position) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_user_second;
    }

    @Override
    public Class<UserSecondViewHolder> getViewHolderClass() {
        return UserSecondViewHolder.class;
    }

    @Override
    public Class<User> getItemClass() {
        return User.class;
    }

    @Override
    public int getViewType() {
        return 2;
    }

}
