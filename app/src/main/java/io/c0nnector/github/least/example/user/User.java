package io.c0nnector.github.least.example.user;

/**
 * Simple user object
 */
public class User implements UserType {

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
    public int getUserType() {
        return id == 1 || id == 2? 0: 1;
    }
}
