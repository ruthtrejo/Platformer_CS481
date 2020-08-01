package edu.utep.cs4381.hw3platformer.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

import edu.utep.cs4381.hw3platformer.BackgroundData;

public class Background {

    public Bitmap bitmap;
    public Bitmap bitmapReversed;

    public int width;
    public int height;

    public boolean reversedFirst;

    public int xClip;// controls where we clip the bitmaps each frame
    public float y;
    public float endY;
    public int z;

    public float speed;

    public boolean isParallax;


    public Background(Context context, int yPixelsPerMetre, int screenWidth, BackgroundData data){

        int resID = context.getResources().getIdentifier(data.bitmapName,
                "drawable", context.getPackageName());

        // For parallax
        bitmap = BitmapFactory.decodeResource(context.getResources(), resID);

        // Which version of background (reversed or regular) is currently drawn first (on left)
        reversedFirst = false;

        //Initialise animation variables.
        xClip = 0;  //always start at zero
        y = data.startY;
        endY = data.endY;
        z = data.layer;
        isParallax = data.isParallax;
        speed = data.speed; //Scrolling background speed

        bitmap = Bitmap.createScaledBitmap(bitmap, screenWidth,
                data.height * yPixelsPerMetre
                , true); //Scale background to fit the screen resolution.

        width = bitmap.getWidth();
        height = bitmap.getHeight();

        //Create a mirror image of the background (horizontal flip)
        Matrix matrix = new Matrix();
        matrix.setScale(-1, 1);
        bitmapReversed = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);

    }
}
