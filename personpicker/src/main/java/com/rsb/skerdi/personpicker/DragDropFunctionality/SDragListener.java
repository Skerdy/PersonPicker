package com.rsb.skerdi.personpicker.DragDropFunctionality;

import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class SDragListener implements View.OnDragListener  {

    private DragStopListener dragStopListener;
    private final String msg = "Drag";
    private View pickerView;
    private View toogleView;
    private boolean synchroniseToogleWithPicker;



    public SDragListener( View pickerView, View toogleView, boolean synchroniseToogleWithPicker){
        this.pickerView = pickerView;
        this.toogleView = toogleView;
        this.synchroniseToogleWithPicker = synchroniseToogleWithPicker;
    }


    @Override
    public boolean onDrag(View v, DragEvent event) {
        v.setVisibility(View.VISIBLE);
        switch (event.getAction()) {
            case DragEvent.ACTION_DRAG_STARTED:

                Log.d(msg, "Action is DragEvent.ACTION_DRAG_STARTED");

                // Do nothing
                break;

            case DragEvent.ACTION_DRAG_ENTERED:
                Log.d(msg, "Action is DragEvent.ACTION_DRAG_ENTERED");
                int x_cord = (int) event.getX();
                int y_cord = (int) event.getY();
                break;

            case DragEvent.ACTION_DRAG_EXITED:
                Log.d(msg, "Action is DragEvent.ACTION_DRAG_EXITED");
                x_cord = (int) event.getX();
                y_cord = (int) event.getY();
                v.setVisibility(View.VISIBLE);
                break;

            case DragEvent.ACTION_DRAG_LOCATION:
                Log.d(msg, "Action is DragEvent.ACTION_DRAG_LOCATION");
                x_cord = (int) event.getX();
                y_cord = (int) event.getY();
                break;

            case DragEvent.ACTION_DRAG_ENDED:
                Log.d(msg, "Action is DragEvent.ACTION_DRAG_ENDED");
                // Do nothing
                break;

            case DragEvent.ACTION_DROP:
                Log.d(msg, "ACTION_DROP event");
                Log.d("Drag", "  Y Drop = " + event.getY());
                pickerView.setY(event.getY()-(pickerView.getHeight()/2));
                pickerView.invalidate();
                pickerView.setVisibility(View.VISIBLE);
                if(synchroniseToogleWithPicker){
                    toogleView.setY(event.getY()-(toogleView.getHeight()/2));
                }
                // Do nothing
                break;

            default:
                break;
        }
        return true;
    }

    public boolean isSynchroniseToogleWithPicker() {
        return synchroniseToogleWithPicker;
    }

    public void setSynchroniseToogleWithPicker(boolean synchroniseToogleWithPicker) {
        this.synchroniseToogleWithPicker = synchroniseToogleWithPicker;
    }
}
