package co.quarkworks.headerrecyclerviewadapter_demo.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import co.quarkworks.headerrecyclerviewadapter_demo.R;

/**
 * @author jacobmuchow@quarkworks.co (Jacob Muchow)
 */

public class HeaderView extends RelativeLayout {
    private static final String TAG = HeaderView.class.getSimpleName();

    public HeaderView(Context context) {
        super(context);
        initialize();
    }

    public HeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public HeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }

    private void initialize() {
        LayoutInflater.from(getContext()).inflate(R.layout.header_view, this);
    }
}
