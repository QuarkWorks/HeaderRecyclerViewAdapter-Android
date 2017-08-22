package co.quarkworks.headerrecyclerviewadapter_demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;

import co.quarkworks.headerrecyclerviewadapter.HeaderRecyclerViewAdapter;
import co.quarkworks.headerrecyclerviewadapter_demo.views.FooterView;
import co.quarkworks.headerrecyclerviewadapter_demo.views.HeaderView;
import co.quarkworks.headerrecyclerviewadapter_demo.views.NumberCell;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.main_recycler_view);

        recyclerView.setAdapter(adapter);

        adapter.addHeaderView(new HeaderView(this));
        adapter.addFooterView(new FooterView(this));
    }

    private final HeaderRecyclerViewAdapter adapter = new HeaderRecyclerViewAdapter() {
        @Override
        public RecyclerView.ViewHolder onCreateRowViewHolder(ViewGroup parent, int viewType) {
            Log.d(TAG, "onCreateRowViewHolder");
            return new RecyclerView.ViewHolder(new NumberCell(parent.getContext())) {};
        }

        @Override
        public void onBindRowViewHolder(RecyclerView.ViewHolder holder, int position) {
            Log.d(TAG, "onBindRowViewHolder: " + position);

            if (holder.itemView instanceof NumberCell) {
                Log.d(TAG, "isNumberCell");
                ((NumberCell) holder.itemView).setViewData(position);
            }
        }

        @Override
        public int getRowCount() {
            return 5;
        }
    };
}
