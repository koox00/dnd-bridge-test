package com.dndboard.kanbanbridge;

import android.app.FragmentManager;
import android.os.Build;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.dndboard.R;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;


public class KanbanView extends FrameLayout {

    private BoardFragment mBoardFragment;
    private boolean mHasSavedInstance = false;

    public KanbanView(ReactContext context) {
        super(context);
        init();
    }

    public ReactContext getReactContext() {
        return (ReactContext) getContext();
    }

    public void init() {
        inflate(getReactContext(), R.layout.container, this);
        mBoardFragment = BoardFragment.newInstance(getReactContext());
    }

    @Nullable
    @Override
    protected Parcelable onSaveInstanceState() {
        mHasSavedInstance = true;
        return super.onSaveInstanceState();
    }

    @Override
    protected void onAttachedToWindow() {
        if (!mHasSavedInstance) {
            FragmentManager fragmentManager = getReactContext().getCurrentActivity().getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.container_id, mBoardFragment, "fragment").commit();
        }
        super.onAttachedToWindow();
    }

    @Override
    protected void onDetachedFromWindow() {
        if (getReactContext().getCurrentActivity() != null) {
            FragmentManager fragmentManager = getReactContext().getCurrentActivity().getFragmentManager();

            // Code crashes with java.lang.IllegalStateException: Activity has been destroyed
            // if our activity has been destroyed when this runs
            if (mBoardFragment != null) {
                boolean isDestroyed = false;

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    isDestroyed = getReactContext().getCurrentActivity().isDestroyed();
                }

                if (!isDestroyed) {
                    // https://stackoverflow.com/a/34508430/61072
                    fragmentManager.beginTransaction().remove(mBoardFragment).commitAllowingStateLoss();
                }
            }
        }
        super.onDetachedFromWindow();
    }


}
