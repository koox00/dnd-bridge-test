//  Created by react-native-create-bridge

package com.dndboard.kanbanbridge;


import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;

import com.facebook.react.uimanager.annotations.ReactProp;


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

}
