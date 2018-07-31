package com.rsb.skerdi.personpicker;

import android.graphics.Bitmap;
import android.os.AsyncTask;

/**
 * Created by user on 7/31/2018.
 */

public class GetBitmapsAsync extends AsyncTask<String, Void, Bitmap> {

    private BitmapDownloadAssistant bitmapDownloadAssistant;

    public GetBitmapsAsync(BitmapDownloadAssistant bitmapDownloadAssistant){
        this.bitmapDownloadAssistant = bitmapDownloadAssistant;
    }


@Override
protected Bitmap doInBackground(String... params) {

        System.out.println("doInBackground");
        Bitmap bitmap = null;
        bitmap = Utils.downloadImage(params[0]);
        return bitmap;

        }

@Override
protected void onPostExecute(Bitmap bitmap) {
    System.out.println("bitmap" + bitmap);
    bitmapDownloadAssistant.onBitmapDownloaded(bitmap);

}
        }