package com.yaer.remittance.ui.user_modular.user_seller;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.yaer.remittance.ui.user_modular.user_seller.adapter.ImagePickerAdapter;

public class ItemMoveCallBackImpl extends ItemTouchHelper.Callback {

    private ItemMoveHelperApi mHelperApi;

    public ItemMoveCallBackImpl(ItemMoveHelperApi helperApi) {
        this.mHelperApi = helperApi;
    }
    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }
    @Override
    public boolean isItemViewSwipeEnabled() {
        return false;
    }
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN|ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT;
        int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
        return makeMovementFlags(dragFlags, swipeFlags);
    }
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if(adapter == null || mHelperApi == null)
            return false;
        if(!(adapter instanceof ImagePickerAdapter)){
            return false;
        }
        ImagePickerAdapter imagePickerAdapter = (ImagePickerAdapter) adapter;
        if (((viewHolder.getAdapterPosition()!= adapter.getItemCount()-1 && target.getAdapterPosition() != adapter.getItemCount()-1)
                || (adapter.getItemCount()-1 == viewHolder.getAdapterPosition())) || imagePickerAdapter.maxlastNotAdd()) {
            mHelperApi.onItemMoved(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        }
        return true;
    }
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
    }
}