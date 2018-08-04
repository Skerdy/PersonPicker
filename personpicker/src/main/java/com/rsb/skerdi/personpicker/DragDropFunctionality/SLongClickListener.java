package com.rsb.skerdi.personpicker.DragDropFunctionality;

import android.content.ClipData;
import android.view.View;

public class SLongClickListener implements View.OnLongClickListener {
    @Override
    public boolean onLongClick(View v) {
        ClipData data = ClipData.newPlainText("", "");
        View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
        v.startDrag(data, shadowBuilder, v, 0);
        v.setVisibility(View.INVISIBLE);
        return true;
    }
}
