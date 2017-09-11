package com.example.vanahel.tasksmanagerapplication.util.screenshot;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;

class ScreenshotUtil {

    static Bitmap getScreenShot( View view ) {
        View rootView = view.getRootView();
        view.setDrawingCacheEnabled(true);
        view.measure( View.MeasureSpec.makeMeasureSpec( 0, View.MeasureSpec.UNSPECIFIED ),
                View.MeasureSpec.makeMeasureSpec( 0, View.MeasureSpec.UNSPECIFIED ) );
        view.layout( 0,0,view.getMeasuredWidth(), view.getMeasuredHeight() );
        view.buildDrawingCache(true);
        Bitmap bitmap = Bitmap.createBitmap( view.getDrawingCache() );
        rootView.setDrawingCacheEnabled(false);
        return bitmap;
    }


    static File getMainDirectoryName(Context context) {
        File mainDirectory = new File(
                context.getExternalFilesDir( Environment.DIRECTORY_PICTURES), "Task" );
        if ( !mainDirectory.exists() ) {
            if ( mainDirectory.mkdir() )
                Log.e( "Create Directory", "Main Directory Created : " + mainDirectory );
        }
        return mainDirectory;
    }

    static File store( Bitmap bm, String fileName, File saveFilePath ) {
        File directory = new File( saveFilePath.getAbsolutePath() );
        if ( !directory.exists() ){
            directory.mkdirs();
        }
        File file = new File( saveFilePath.getAbsolutePath(), fileName );
        try {
            FileOutputStream fOut = new FileOutputStream(file);
            bm.compress( Bitmap.CompressFormat.JPEG, 85, fOut );
            fOut.flush();
            fOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }
}
