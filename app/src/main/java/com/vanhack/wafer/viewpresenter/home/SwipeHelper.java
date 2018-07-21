package com.vanhack.wafer.viewpresenter.home;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

/**
 * Helper class to Handle swipe events.
 * We could override this inline, but the code would get ugly
 */
public class SwipeHelper extends ItemTouchHelper.Callback {

    /**
     * While we are creating this new class, the Delete should not be handled here.
     * So we make the Activity to implement this interface to handle the Swipe event
     */
    public interface SwipeListener {
        void onSwiped(RecyclerView.ViewHolder viewHolder);
    }

    //Someone who cares about swipe event
    private SwipeListener mListener;

    public SwipeHelper(SwipeListener listener) {
        mListener = listener;
    }

    /**
     * For now we only care about Swipe events
     * I'll limit the Swipe to the Left only
     * @param recyclerView
     * @param viewHolder
     * @return
     */
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        return makeMovementFlags(0, ItemTouchHelper.LEFT);
    }

    /**
     * We'll not move anything
     * @return
     */
    @Override
    public boolean isLongPressDragEnabled() {
        return false;
    }

    /**
     * Sure, we want the Swipe
     * @return
     */
    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    /**
     * No Move allowed
     * @param recyclerView
     * @param viewHolder
     * @param target
     * @return
     */
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    /**
     * This callback is called when the Swipe is done and the ViewHolder goes away
     * By the rules, we need to delete the row, so lets call the listener to get the job done
     * @param viewHolder
     * @param direction
     */
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        mListener.onSwiped(viewHolder);
    }

    /**
     * The default behavior for the ItemTouchHelper.Callback is to apply all changes
     * to the whole ViewHolder, but here we want to move only the foreground layer,
     * so we have to get only the foreground layer and pass it to the UIUtil
     * This will be done in all methods implemented by the ItemTouchUIUtil
     */

    /**
     * Callback called when the ViewHolder is disturbed
     * @param viewHolder
     * @param actionState could be IDLE, DRAG or SWIPE
     */
    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        //do it for the foreground
        if (viewHolder != null) {
            final View foregroundView = ((CountryAdapter.CountryViewHolder) viewHolder).getForegroundView();
            getDefaultUIUtil().onSelected(foregroundView);
        }
    }

    /**
     * Callback called when the view is being drawed
     * @param c
     * @param recyclerView
     * @param viewHolder
     * @param dX
     * @param dY
     * @param actionState
     * @param isCurrentlyActive
     */
    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView,
                            RecyclerView.ViewHolder viewHolder, float dX, float dY,
                            int actionState, boolean isCurrentlyActive) {
        //do it for the foreground
        if (viewHolder != null) {
            final View foregroundView = ((CountryAdapter.CountryViewHolder) viewHolder).getForegroundView();
            getDefaultUIUtil().onDraw(c, recyclerView, foregroundView, dX, dY, actionState, isCurrentlyActive);
        }
    }

    /**
     * Callback called when the view is being drawed
     * Same as before, but its needed for Honeycomb or before
     * @param c
     * @param recyclerView
     * @param viewHolder
     * @param dX
     * @param dY
     * @param actionState
     * @param isCurrentlyActive
     */
    @Override
    public void onChildDrawOver(Canvas c, RecyclerView recyclerView,
                                RecyclerView.ViewHolder viewHolder, float dX, float dY,
                                int actionState, boolean isCurrentlyActive) {
        //do it for the foreground
        if (viewHolder != null) {
            final View foregroundView = ((CountryAdapter.CountryViewHolder) viewHolder).getForegroundView();
            getDefaultUIUtil().onDrawOver(c, recyclerView, foregroundView, dX, dY, actionState, isCurrentlyActive);
        }
    }

    /**
     * Callback called when the view is reseted
     * @param recyclerView
     * @param viewHolder
     */
    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        if (viewHolder != null) {
            final View foregroundView = ((CountryAdapter.CountryViewHolder) viewHolder).getForegroundView();
            getDefaultUIUtil().clearView(foregroundView);
        }
    }
}