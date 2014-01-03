package com.countriesyouvisited.activities;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.FloatMath;
import android.util.Log;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

import com.countriesyouvisited.database.DataBaseHandler;
import com.countriesyouvisited.database.objects.VisitedRegionObject;

/**
 * @author horodysk
 */
public class ScratchMapImageView extends ImageView implements OnTouchListener {

    private static final int _SCRATCH_COLOR = Color.GRAY;

    private static final int _IMAGE_MAX_SIZE = 3000;

    private DataBaseHandler _db;

    Bitmap _world;

    /***/
    public ScratchMapImageView(Context context) {
        super(context);

        ArrayList<VisitedRegionObject> visited = new ArrayList<VisitedRegionObject>();
        initialize(context, "north_america", visited);
    }

    /***/
    public ScratchMapImageView(Context context, String mapName, List<VisitedRegionObject> visited) {
        super(context);

        initialize(context, mapName, visited);
    }

    private void initialize(Context context, String mapName, List<VisitedRegionObject> visited) {
        setOnTouchListener(this);
        _world = decodeMap(mapName);
        _db = new DataBaseHandler(context);
        setImageBitmap(getOverlayedMap(visited));
    }

    /***/
    public void refreshImage(List<VisitedRegionObject> visited) {
        setImageBitmap(getOverlayedMap(visited));
        invalidate();
    }

    private Bitmap getOverlayedMap(List<VisitedRegionObject> visited) {
        Bitmap map = Bitmap.createBitmap(_world.getWidth(), _world.getHeight(), _world.getConfig());

        Canvas canvas = new Canvas(map);

        canvas.drawBitmap(_world, new Matrix(), null);

        for (VisitedRegionObject region : visited) {

            List<Pair<Integer, Integer>> points = region.getRegion(_db).getRegionPoints();

            if (!points.isEmpty()) {
                Path path = new Path();

                Pair<Integer, Integer> firstPoint = points.remove(0);
                path.moveTo(firstPoint.first, firstPoint.second);

                for (Pair<Integer, Integer> point : points) {
                    path.lineTo(point.first, point.second);
                }

                path.close();
                Paint p = new Paint();
                p.setColor(_SCRATCH_COLOR);

                canvas.drawPath(path, p);
            }
        }
        return map;
    }

    private Bitmap decodeMap(String mapName) {
        Bitmap b = null;

        // Decode image size
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;
        int drawableResourceId = getResources().getIdentifier(mapName, "drawable", getContext().getPackageName());
        BitmapFactory.decodeResource(getContext().getResources(), drawableResourceId, o);

        Log.d("CREATE MAP", mapName + " " + drawableResourceId);

        int scale = 1;
        if (o.outHeight > _IMAGE_MAX_SIZE || o.outWidth > _IMAGE_MAX_SIZE) {
            scale = (int) Math.pow(2, (int) Math.round(Math.log(_IMAGE_MAX_SIZE / (double) Math.max(o.outHeight, o.outWidth)) / Math.log(0.5)));
        }

        // Decode with inSampleSize
        BitmapFactory.Options o2 = new BitmapFactory.Options();
        o2.inSampleSize = scale;
        b = BitmapFactory.decodeResource(getContext().getResources(), drawableResourceId, o2);

        return b;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    private static final String TAG = "Touch";

    @SuppressWarnings("unused")
    private static final float MIN_ZOOM = 1f, MAX_ZOOM = 1f;

    // These matrices will be used to scale points of the image
    Matrix matrix = new Matrix();

    Matrix savedMatrix = new Matrix();

    // The 3 states (events) which the user is trying to perform
    static final int NONE = 0;

    static final int DRAG = 1;

    static final int ZOOM = 2;

    int mode = NONE;

    // these PointF objects are used to record the point(s) the user is touching
    PointF start = new PointF();

    PointF mid = new PointF();

    float oldDist = 1f;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        ScratchMapImageView view = (ScratchMapImageView) v;
        float scale;

        dumpEvent(event);
        // Handle touch events here...

        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN: // first finger down only
                savedMatrix.set(matrix);
                start.set(event.getX(), event.getY());
                Log.d(TAG, "mode=DRAG"); // write to LogCat
                mode = DRAG;
                break;

            case MotionEvent.ACTION_UP: // first finger lifted

            case MotionEvent.ACTION_POINTER_UP: // second finger lifted

                mode = NONE;
                Log.d(TAG, "mode=NONE");
                break;

            case MotionEvent.ACTION_POINTER_DOWN: // first and second finger
// down

                oldDist = spacing(event);
                Log.d(TAG, "oldDist=" + oldDist);
                if (oldDist > 5f) {
                    savedMatrix.set(matrix);
                    midPoint(mid, event);
                    mode = ZOOM;
                    Log.d(TAG, "mode=ZOOM");
                }
                break;

            case MotionEvent.ACTION_MOVE:

                if (mode == DRAG) {
                    matrix.set(savedMatrix);
                    matrix.postTranslate(event.getX() - start.x, event.getY() - start.y); // create
// the transformation in the matrix of points
                }
                else if (mode == ZOOM) {
                    // pinch zooming
                    float newDist = spacing(event);
                    Log.d(TAG, "newDist=" + newDist);
                    if (newDist > 5f) {
                        matrix.set(savedMatrix);
                        scale = newDist / oldDist; // setting the scaling of the
                                                   // matrix...if scale > 1
// means
                        // zoom in...if scale < 1
// means
                        // zoom out
                        matrix.postScale(scale, scale, mid.x, mid.y);
                    }
                }
                break;
        }

        view.setImageMatrix(matrix); // display the transformation on screen

        return true; // indicate event was handled
    }

    /*
     * --------------------------------------------------------------------------
     * Method: spacing Parameters: MotionEvent Returns: float Description:
     * checks the spacing between the two fingers on touch
     * ----------------------------------------------------
     */

    private float spacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return FloatMath.sqrt(x * x + y * y);
    }

    /*
     * --------------------------------------------------------------------------
     * Method: midPoint Parameters: PointF object, MotionEvent Returns: void
     * Description: calculates the midpoint between the two fingers
     * ------------------------------------------------------------
     */

    private void midPoint(PointF point, MotionEvent event) {
        float x = event.getX(0) + event.getX(1);
        float y = event.getY(0) + event.getY(1);
        point.set(x / 2, y / 2);
    }

    /** Show an event in the LogCat view, for debugging */
    private void dumpEvent(MotionEvent event) {
        String names[] = { "DOWN", "UP", "MOVE", "CANCEL", "OUTSIDE", "POINTER_DOWN", "POINTER_UP", "7?", "8?", "9?" };
        StringBuilder sb = new StringBuilder();
        int action = event.getAction();
        int actionCode = action & MotionEvent.ACTION_MASK;
        sb.append("event ACTION_").append(names[actionCode]);

        if (actionCode == MotionEvent.ACTION_POINTER_DOWN || actionCode == MotionEvent.ACTION_POINTER_UP) {
            sb.append("(pid ").append(action >> MotionEvent.ACTION_POINTER_ID_SHIFT);
            sb.append(")");
        }

        sb.append("[");
        for (int i = 0; i < event.getPointerCount(); i++) {
            sb.append("#").append(i);
            sb.append("(pid ").append(event.getPointerId(i));
            sb.append(")=").append((int) event.getX(i));
            sb.append(",").append((int) event.getY(i));
            if (i + 1 < event.getPointerCount())
                sb.append(";");
        }

        sb.append("]");
        Log.d("Touch Events ---------", sb.toString());
    }

}
