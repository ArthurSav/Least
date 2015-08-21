package io.c0nnector.github.least.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.c0nnector.github.least.example.banner.Banner;
import io.c0nnector.github.least.example.banner.BannerBinder;
import io.c0nnector.github.least.example.banner.BannerViewHolderHolder;
import io.c0nnector.github.least.example.header.HeaderBinder;
import io.c0nnector.github.least.example.header.HeaderViewHolder;
import io.c0nnector.github.least.example.user.User;
import io.c0nnector.github.least.example.user.UserBinder;
import io.c0nnector.github.least.example.user.UserViewHolderHolder;
import io.c0nnector.github.least.LeastAdapter;
import io.c0nnector.github.least.LeastView;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.recyclerview)
    LeastView leastView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setupRecyclerView();
    }

    public void setupRecyclerView(){



        //adapter builder
        LeastAdapter adapter = new LeastAdapter.Builder()

                // 1) bind the view types you want to display
                .binder(new UserBinder(User.class, UserViewHolderHolder.class, R.layout.layout_user))
                .binder(new BannerBinder(Banner.class, BannerViewHolderHolder.class, R.layout.layout_banner))
                .binder(new HeaderBinder(String.class, HeaderViewHolder.class, R.layout.layout_header))

                // 2) add your list items
                .item("Section 1")
                .items(getUsers())

                .item("Section 2")
                .items(getBanners())


                .item("Section 3")
                .items(getUsers())

                // 3) done
                .build(this);


        leastView.setAdapter(adapter);

        //bonus divider if you use the LeastView
        leastView.setDivider(true);
    }

    /**
     * Creates a dummy user list
     * @return
     */
    private List<User> getUsers(){

        List<User> users = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            users.add(new User(i, "user_"+i));
        }

        return users;
    }

    /**
     * Creates a dummy banner list
     * @return
     */
    private List<Banner> getBanners(){

        List<Banner> banners = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            banners.add(new Banner("banner " + i));
        }

        return banners;
    }

}
