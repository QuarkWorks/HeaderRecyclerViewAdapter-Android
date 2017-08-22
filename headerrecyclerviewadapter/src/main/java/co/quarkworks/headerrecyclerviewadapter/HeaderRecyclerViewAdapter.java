package co.quarkworks.headerrecyclerviewadapter;

import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * The MIT License (MIT)
 * Copyright (c) 2016
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 * @author berbschloe@quarkworks.co
 */
@SuppressWarnings({"UnusedParameters", "unused"})
public abstract class HeaderRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = HeaderRecyclerViewAdapter.class.getSimpleName();

    @NonNull
    private final List<View> headerViews = new ArrayList<>();

    @NonNull
    private final List<View> footerViews = new ArrayList<>();

    public HeaderRecyclerViewAdapter() {
        this(null, null);
    }

    public HeaderRecyclerViewAdapter(@Nullable List<View> headerViews, @Nullable List<View> footerViews) {
        if (headerViews != null && !headerViews.isEmpty()) {
            this.headerViews.addAll(headerViews);
        }

        if (footerViews != null && !footerViews.isEmpty()) {
            this.footerViews.addAll(footerViews);
        }
    }

    public final void addHeaderView(@NonNull View view) {
        addHeaderView(getHeaderCount(), view);
    }

    public final void addHeaderView(int position, @NonNull View view) {
        if (headerViews.contains(view)) {
            return;
        }

        removeFooterView(view);

        headerViews.add(position, view);
        //notifyItemInserted(position);
        notifyDataSetChanged();
    }

    @NonNull
    public final View getHeaderView(int position) {
        return headerViews.get(position);
    }

    @NonNull
    public final List<View> getHeaderViews() {
        return new ArrayList<>(headerViews);
    }

    public final void removeHeaderView(View view) {
        int position = headerViews.indexOf(view);
        if (position == -1) {
            return;
        }

        removeHeaderView(position);
    }

    public final void removeHeaderView(int position) {
        headerViews.remove(position);
        notifyDataSetChanged();
        //notifyItemRemoved(position);
    }

    public final void removeAllHeaders() {
        headerViews.clear();
        notifyDataSetChanged();
    }

    public final void addFooterView(@NonNull View view) {
        addFooterView(getFooterCount(), view);
    }

    public final void addFooterView(int position, @NonNull View view) {
        if (footerViews.contains(view)) {
            return;
        }

        removeHeaderView(view);

        footerViews.add(position, view);
        notifyDataSetChanged();
        //notifyItemInserted(headerViews.size() + getRowCount() + position);
    }

    @NonNull
    public final View getFooterView(int position) {
        return footerViews.get(position);
    }

    public final List<View> getFooterViews() {
        return new ArrayList<>(footerViews);
    }

    public final void removeFooterView(@NonNull View view){
        int position = footerViews.indexOf(view);
        if (position == -1) {
            return;
        }

        removeFooterView(position);
    }

    public final void removeFooterView(int position) {
        footerViews.remove(position);
        notifyDataSetChanged();
        //notifyItemRemoved(headerViews.size() + getRowCount() + position);
    }

    public final void removeAllFooters() {
        footerViews.clear();
        notifyDataSetChanged();
    }

    public final void removeAllHeadersAndFooters() {
        headerViews.clear();
        footerViews.clear();
        notifyDataSetChanged();
    }

    public final boolean isHeaderView(int position) {
        return position < getHeaderCount();
    }

    public final boolean isHeaderView(View view) {
        return getHeaderViewForType(getViewTypeForHeaderOrFooter(view)) != null;
    }

    public final int getViewTypeForHeaderOrFooter(@NonNull View view) {
        return view.hashCode();
    }

    @Nullable
    public final View getHeaderViewForType(int viewType) {
        for (View headerView : headerViews) {
            if (getViewTypeForHeaderOrFooter(headerView) == viewType) {
                return headerView;
            }
        }
        return null;
    }

    public final boolean isFooterView(int position) {
        return position >= getHeaderCount() + getRowCount();
    }

    public final boolean isFooterView(View view) {
        return getFooterViewForType(getViewTypeForHeaderOrFooter(view)) != null;
    }

    @Nullable
    public final View getFooterViewForType(int viewType) {
        for (View footerView : footerViews) {
            if (getViewTypeForHeaderOrFooter(footerView) == viewType) {
                return footerView;
            }
        }
        return null;
    }

    public final boolean isRowView(int position) {
        return !isHeaderView(position) && !isFooterView(position);
    }

    public final int getHeaderCount() {
        return headerViews.size();
    }

    public final int getFooterCount() {
        return footerViews.size();
    }

    /*
        Abstract Methods
     */

    public abstract RecyclerView.ViewHolder onCreateRowViewHolder(ViewGroup parent, int viewType);

    public abstract void onBindRowViewHolder(RecyclerView.ViewHolder holder, int position);

    public abstract int getRowCount();

    /*
        Optional
     */

    public void onBindRowViewHolder(RecyclerView.ViewHolder holder, int position, List<Object> payloads) {
        onBindRowViewHolder(holder, position);
    }

    public int getRowViewType(int position) {
        return 0;
    }

    public long getRowId(int position) {
        return RecyclerView.NO_ID;
    }

    public void onRowViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        //Stub
    }

    public void onRowViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
        //Stub
    }

    public void onRowViewRecycled(RecyclerView.ViewHolder holder) {
        //Stub
    }

    public boolean onFailedToRecycleRowView(RecyclerView.ViewHolder holder) {
        return false;
    }

    /*
        Overridden Methods
     */

    @Override
    public final RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View headerView = getHeaderViewForType(viewType);
        if (headerView != null) {
            return new HeaderVH(headerView) {};
        }

        View footerView = getFooterViewForType(viewType);
        if (footerView != null) {
            return new FooterVH(footerView) {};
        }

        return onCreateRowViewHolder(parent, viewType);
    }

    @Override
    public final void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (!isRowView(position)) {
            return;
        }

        onBindRowViewHolder(holder, position - getHeaderCount());
    }

    @Override
    public final void onBindViewHolder(RecyclerView.ViewHolder convertView, int position, List<Object> payloads) {
        if (!isRowView(position)) {
            return;
        }

        onBindRowViewHolder(convertView, position - getHeaderCount(), payloads);
    }

    @Override
    public final int getItemCount() {
        return getHeaderCount() + getRowCount() + getFooterCount();
    }

    @Override
    public final long getItemId(int position) {
        if (isHeaderView(position)) {
            return getViewTypeForHeaderOrFooter(headerViews.get(position));

        } else if (isFooterView(position)) {
            return getViewTypeForHeaderOrFooter(footerViews.get(position - getHeaderCount() - getRowCount()));
        }

        return getRowId(position - getHeaderCount());
    }

    @Override
    public final int getItemViewType(int position) {
        if (isHeaderView(position)) {
            return getViewTypeForHeaderOrFooter(headerViews.get(position));

        } else if (isFooterView(position)) {
            return getViewTypeForHeaderOrFooter(footerViews.get(position - getHeaderCount() - getRowCount()));
        }

        return getRowViewType(position - getHeaderCount());
    }

    @Override @CallSuper
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        if (holder instanceof HeaderVH || holder instanceof FooterVH) {
            return;
        }

        onRowViewAttachedToWindow(holder);
    }

    @Override @CallSuper
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
        if (holder instanceof HeaderVH || holder instanceof FooterVH) {
            return;
        }

        onRowViewDetachedFromWindow(holder);
    }

    @Override @CallSuper
    public void onViewRecycled(RecyclerView.ViewHolder holder) {
        if (holder instanceof HeaderVH || holder instanceof FooterVH) {
            return;
        }

        onRowViewRecycled(holder);
    }

    @Override @CallSuper
    public boolean onFailedToRecycleView(RecyclerView.ViewHolder holder) {
        if (holder instanceof HeaderVH || holder instanceof FooterVH) {
            return super.onFailedToRecycleView(holder);
        }

        return onFailedToRecycleRowView(holder);
    }

    private static class HeaderVH extends RecyclerView.ViewHolder {
        public HeaderVH(View itemView) {
            super(itemView);
        }
    }

    private static class FooterVH extends RecyclerView.ViewHolder {
        public FooterVH(View itemView) {
            super(itemView);
        }
    }
}
