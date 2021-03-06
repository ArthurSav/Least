package io.c0nnector.github.least.example;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;
import java.util.ArrayList;
import java.util.List;

import io.c0nnector.github.least.LeastAdapter;
import io.c0nnector.github.least.example.banner.Banner;
import io.c0nnector.github.least.example.banner.BannerBinder;
import io.c0nnector.github.least.example.header.HeaderBinder;
import io.c0nnector.github.least.example.user.SecondUserBinder;
import io.c0nnector.github.least.example.user.User;
import io.c0nnector.github.least.example.user.UserBinder;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        setupRecyclerView();
    }

    public void setupRecyclerView() {

        //adapter builder
        LeastAdapter adapter = new LeastAdapter.Builder()

                // 1) bind the view types you want to display
                .binder(new UserBinder())
                .binder(new SecondUserBinder())
                .binder(new BannerBinder())
                .binder(new HeaderBinder())

                // 2) add your list items
                .item("Section 1")
                .items(getUsers())

                .item("Section 2")
                .items(getBanners())

                .item("Section 3")
                .items(getUsers())

                // 3) done
                .build(this);

        HorizontalDividerItemDecoration dividerItemDecoration = new HorizontalDividerItemDecoration.Builder(this)
            .color(Color.GRAY)
            .build();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(adapter);
    }

    /**
     * Creates a dummy user list
     *
     * @return
     */
    private List<User> getUsers() {

        List<User> users = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            users.add(new User(i, "user_" + i));
        }

        return users;
    }

    /**
     * Creates a dummy banner list
     *
     * @return
     */
    private List<Banner> getBanners() {

        List<Banner> banners = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            banners.add(new Banner("banner " + i));
        }

        return banners;
    }

}
