package co.quarkworks.headerrecyclerviewadapter_demo.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import co.quarkworks.headerrecyclerviewadapter_demo.R;

/**
 * @author jacobmuchow@quarkworks.co (Jacob Muchow)
 */

public class FooterView extends RelativeLayout {
    private static final String TAG = FooterView.class.getSimpleName();

    public FooterView(Context context) {
        super(context);
        initialize();
    }

    public FooterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public FooterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }

    private void initialize() {
        LayoutInflater.from(getContext()).inflate(R.layout.footer_view, this);
    }
}
