package io.c0nnector.github.least.example.user;

import io.c0nnector.github.least.ItemViewType;

/**
 * Simple user object
 */
public class User implements ItemViewType {

    int id;

    String name;

    public User(int id, String name) {

        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {

        return name;
    }

    @Override
    public int getViewType() {
        return id == 1 || id == 2? 0 : 1;
    }
}
