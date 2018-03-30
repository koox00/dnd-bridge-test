//  Created by react-native-create-bridge

package com.dndboard.kanbanbridge;

import android.app.Activity;
import android.support.v4.util.Pair;
import android.view.View;
import android.widget.TextView;

import com.dndboard.R;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.bridge.ReadableArray;

import com.facebook.react.uimanager.annotations.ReactProp;

import javax.annotation.Nullable;

import com.woxthebox.draglistview.BoardView;

import java.util.ArrayList;



public class KanbanBridgeManager extends SimpleViewManager<BoardView> {
    public static final String REACT_CLASS = "KanbanBridge";
    private static int sCreatedItems = 0;
    private int mColumns;
    private Activity mActivity = null;


    @Override
    public String getName() {
        // Tell React the name of the module
        // https://facebook.github.io/react-native/docs/native-components-android.html#1-create-the-viewmanager-subclass
        return REACT_CLASS;
    }

    @Override
    public BoardView createViewInstance(ThemedReactContext context){
        // Create a view here
        // https://facebook.github.io/react-native/docs/native-components-android.html#2-implement-method-createviewinstance

        mActivity = context.getCurrentActivity();
        return new BoardView(context);
    }

    @ReactProp(name = "snapToColumnsWhenScrolling", defaultBoolean = true)
    public void setSnapToColumnsWhenScrolling(BoardView view, boolean value) {
      view.setSnapToColumnsWhenScrolling(value);
    }

    @ReactProp(name = "snapToColumnWhenDragging", defaultBoolean = true)
    public void setSnapToColumnWhenDragging(BoardView view, boolean value) {
      view.setSnapToColumnWhenDragging(value);
    }

    @ReactProp(name = "snapDragItemToTouch", defaultBoolean = true)
    public void setSnapDragItemToTouch(BoardView view, boolean value) {
      view.setSnapDragItemToTouch(value);
    }

    @ReactProp(name = "snapToColumnInLandscape", defaultBoolean = false)
    public void setSnapToColumnInLandscape(BoardView view, boolean value) {
      view.setSnapToColumnInLandscape(value);
    }

    @ReactProp(name = "data")
    public void createLists(BoardView view, @Nullable ReadableArray columns) {
        addColumnList(view);
    }

    private void addColumnList(BoardView view) {
        final ArrayList<Pair<Long, String>> mItemArray = new ArrayList<>();
        int addItems = 15;
        for (int i = 0; i < addItems; i++) {
            long id = sCreatedItems++;
            mItemArray.add(new Pair<>(id, "Item " + id));
        }

        final ItemAdapter listAdapter = new ItemAdapter(mItemArray, R.layout.column_item, R.id.item_layout, true);
        final View header = View.inflate(mActivity, R.layout.column_header, view);
        ((TextView) header.findViewById(R.id.text)).setText("Column " + (mColumns + 1));
        ((TextView) header.findViewById(R.id.item_count)).setText("" + addItems);

        view.addColumnList(listAdapter, header, false);
        mColumns++;
    }

    // mBoardView.setCustomDragItem(new MyDragItem(getActivity(), R.layout.column_item));

}
