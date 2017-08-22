package co.quarkworks.headerrecyclerviewadapter_demo.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import co.quarkworks.headerrecyclerviewadapter_demo.R;

/**
 * @author jacobmuchow@quarkworks.co (Jacob Muchow)
 */

public class NumberCell extends RelativeLayout {
    private static final String TAG = NumberCell.class.getSimpleName();

    private TextView numberTextView;

    public NumberCell(Context context) {
        super(context);
        initialize();
    }

    public NumberCell(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public NumberCell(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }

    private void initialize() {
        LayoutInflater.from(getContext()).inflate(R.layout.number_cell, this);

        numberTextView = (TextView) findViewById(R.id.number_cell_text);
    }

    public void setViewData(int number) {
        numberTextView.setText(String.valueOf(number));
    }
}
