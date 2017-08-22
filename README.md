## HeaderRecyclerViewAdapter

The HeaderRecyclerViewAdapter extends Android's RecyclerView.Adapter, allowing you to easily add header and footer views without having to handle any of the position offset logic that can cause complications with binding your adapter data to your item views.

#### MyAdapter.java

```java
public class MyAdapter extends HeaderRecyclerViewAdapater {

    @Override
    public RecyclerView.ViewHolder onCreateRowViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerView.ViewHolder(new NumberCell(parent.getContext())) {};
    }

    @Override
    public void onBindRowViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder.itemView instanceof NumberCell) {
            ((NumberCell) holder.itemView).setViewData(position);
        }
    }

    @Override
    public int getRowCount() {
        return 5;
    }
}
```



#### MyActivity.java

```java
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_activity);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        MyAdapter adapter = new MyAdapter();
        adapter.addHeaderView(new HeaderView(this));
        adapter.addFooterView(new FooterView(this));

        recyclerView.setAdapter(adapter);
    }
```


# Including with Gradle

1. Add the JitPack repository center to your project build.gradle:

```
allprojects {
        repositories {
            maven { url "https://jitpack.io" }
        }
    }
}
```

2. Add the dependency to your app build.gradle:

```
dependencies {
    compile 'com.github.QuarkWorks:HeaderRecyclerView-Android:0.9.0'
}
```
