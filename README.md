# Least
Least amount of effort to add multiple views in a recyclerview.


####LeastAdapter

```java

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

```
![Alt Text](http://fat.gfycat.com/AbsoluteMediocreBaiji.gif)

####Details
The idea is that you bind an object class to a viewholder. The object class is your typical list item e.g User.class

Let's say you want to display 3 different kind of views. A **user view**, a **banner view** and a **header view**.

#####First step
Create a binder for each view type by extending the **Binder** class, like so:

```java

//Extend the binder by adding the viewholder and list item class of your view
public class UserBinder extends Binder<UserViewHolderHolder, User> {

    @Override
    public void onBindViewHolder(UserViewHolderHolder holder, User user, int position) {
        holder.textView.setText(user.getName());
    }
}
```
Do the same for your other views

#####Last step
That's pretty much it. Just use the binder to your adapter like so:

```java

//set the list item class, viewholder class and the view layout id
UserBinder userBinder = new UserBinder(User.class, UserViewHolderHolder.class, R.layout.layout_user)

new LeastAdapter.Builder()
                .binder(userBinder)
                ...
                //add more views
```
Check the example for more

##Bonus

####LeastView

```java
<io.c0nnector.github.least.LeastView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:id="@+id/leastView"/>
```

Add a divider
```java
leastView.setDivider(true);
```

**NO MORE lines like this**, unless you want to change it. (Default layout manager is set upon inflation)
```java
recyclerview.setLayoutManager(new LinearLayoutManager(this));
```

#Install
**1. Add it in your build.gradle at the end of repositories:**

```
repositories {
	    // ...
	    maven { url "https://jitpack.io" }
	}
```
	
**2. Add the dependency in the form**

```
dependencies {
	        compile 'com.github.c0nnector:Least:1.0'
	}
```
