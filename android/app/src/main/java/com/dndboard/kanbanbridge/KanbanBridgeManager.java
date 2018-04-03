//  Created by react-native-create-bridge

package com.dndboard.kanbanbridge;


import android.support.annotation.Nullable;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;

import com.facebook.react.uimanager.annotations.ReactProp;

import java.util.Map;


public class KanbanBridgeManager extends SimpleViewManager<KanbanView> {
    public static final String REACT_CLASS = "KanbanBridge";


    @Override
    public String getName() {
        // Tell React the name of the module
        // https://facebook.github.io/react-native/docs/native-components-android.html#1-create-the-viewmanager-subclass
        return REACT_CLASS;
    }

    @Override
    public KanbanView createViewInstance(ThemedReactContext context){
        // Create a view here
        // https://facebook.github.io/react-native/docs/native-components-android.html#2-implement-method-createviewinstance

        return new KanbanView(context);
    }

    @ReactProp(name="dataSource")
    public void setDataSource(KanbanView view, ReadableArray dataSource){
        for(int i=0; i< dataSource.size(); i++){
            view.addColumnList(dataSource.getMap(i));
        }
    }

    @ReactProp(name = "snapToColumnsWhenScrolling", defaultBoolean = true)
    public void setSnapToColumnsWhenScrolling(KanbanView view, boolean snapToColumns ) {
        view.setSnapToColumnsWhenScrolling(snapToColumns);
    }

    @ReactProp(name = "snapToColumnsWhenDragging", defaultBoolean = true)
    public void setSnapToColumnsWhenDragging(KanbanView view, boolean snapToColumns) {
        view.setSnapToColumnsWhenDragging(snapToColumns);
    }

    @ReactProp(name = "snapDragItemToTouch", defaultBoolean = true)
    public void setSnapDragItemToTouch(KanbanView view, boolean snapToTouch) {
        view.setSnapDragItemToTouch(snapToTouch);
    }

    @ReactProp(name = "snapToColumnInLandscape")
    public void setSnapToColumnInLandscape(KanbanView view, boolean snapToColumn) {
        view.setSnapToColumnInLandscape(snapToColumn);
    }

    @Override
    public @Nullable Map getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.of(
                "onEndDragging",
                MapBuilder.of("registrationName", "onEndDragging"),
                "onStartDragging",
                MapBuilder.of("registrationName", "onStartDragging")
        );
    }

}
