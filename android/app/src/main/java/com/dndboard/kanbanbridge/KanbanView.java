package com.dndboard.kanbanbridge;

import android.app.FragmentManager;
import android.os.Build;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.app.FragmentTransaction;
import android.support.v4.util.Pair;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.dndboard.R;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.woxthebox.draglistview.BoardView;

import java.util.ArrayList;


public class KanbanView extends FrameLayout {

    private static int sCreatedItems = 0;
    private BoardView mBoardView;
    private int mColumns;

    public KanbanView(ReactContext context) {
        super(context);
        init();
    }

    public ReactContext getReactContext() {
        return (ReactContext) getContext();
    }

    public void init() {
        View view = inflate(getReactContext(), R.layout.board_layout, this);
        mBoardView = view.findViewById(R.id.board_view);
    }

    public void addColumnList(String name) {
        final ArrayList<Pair<Long, String>> mItemArray = new ArrayList<>();
        int addItems = 3;
        for (int i = 0; i < addItems; i++) {
            long id = sCreatedItems++;
            mItemArray.add(new Pair<>(id, "Item " + id));
        }

        final int column = mColumns;
        final ItemAdapter listAdapter = new ItemAdapter(mItemArray, R.layout.column_item, R.id.item_layout, true);
        final View header = View.inflate(getReactContext(), R.layout.column_header, null);
        ((TextView) header.findViewById(R.id.text)).setText(name);

        mBoardView.addColumnList(listAdapter, header, false);
        mColumns++;
    }

    public void setSnapToColumnsWhenScrolling(boolean snapToColumns) {
        mBoardView.setSnapToColumnsWhenScrolling(snapToColumns);
    }

    public void setSnapToColumnsWhenDragging(boolean snapToColumns) {
        mBoardView.setSnapToColumnWhenDragging(snapToColumns);
    }

    public void setSnapDragItemToTouch(boolean snapToTouch) {
        mBoardView.setSnapDragItemToTouch(snapToTouch);
    }

    public void setSnapToColumnInLandscape(boolean snapToColumn) {
        mBoardView.setSnapToColumnInLandscape(snapToColumn);
    }


    @Override
    public void requestLayout() {
        super.requestLayout();
        post(measureAndLayout);
    }

    private final Runnable measureAndLayout = new Runnable() {
        @Override
        public void run() {
            measure(
                    MeasureSpec.makeMeasureSpec(getWidth(), MeasureSpec.EXACTLY),
                    MeasureSpec.makeMeasureSpec(getHeight(), MeasureSpec.EXACTLY));
            layout(getLeft(), getTop(), getRight(), getBottom());
        }
    };

}
