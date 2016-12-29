package at.ac.tuwien.policenauts.l4.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.SurfaceHolder;

import java.util.ArrayList;
import java.util.List;

import at.ac.tuwien.policenauts.l4.android.GameSurfaceView;

/**
 * The main game object, used for loading resources and rendering.
 * The GameLoop calls and updates this class.
 *
 * @author Wassily Bartuska
 */
public class Game {
    private final Context context;
    private TextureManager textureManager = null;
    private LevelLoader levelLoader = null;
    private float fps;

    /**
     * Initialize game object with application context.
     *
     * @param context Application context
     */
    public Game(Context context) {
        this.context = context;
    }

    /**
     * Initialize all important resource managers.
     */
    public void initialize() {
        // Initialize resource managers
        textureManager = new TextureManager(context);
        levelLoader = new LevelLoader(context, textureManager);
    }

    /**
     * Update the game logic.
     *
     * @param tpf Time per frame in milliseconds
     */
    void updateLogic(float tpf) {
        fps = 1 / tpf * 1000;
    }

    /**
     * Rendering all objects to the canvas here.
     *
     * @param canvas Drawing canvas
     */
    public void render(Canvas canvas) {
        textureManager.setCanvas(canvas);
        canvas.drawColor(Color.BLACK);

        Paint paint = new Paint();
        paint.setColor(Color.RED);

        canvas.drawRect(canvas.getWidth()/2-40, canvas.getHeight()/2-40,
                canvas.getWidth()/2+40, canvas.getHeight()/2+40, paint);

        Paint textP = new Paint();
        textP.setColor(Color.GREEN);
        textP.setTextAlign(Paint.Align.RIGHT);

        String fpsText = "frames per second: " + fps;
        canvas.drawText(fpsText, 0, fpsText.length()-1, (canvas.getWidth()/10) * 9, canvas.getHeight()/10, textP);
    }
}
