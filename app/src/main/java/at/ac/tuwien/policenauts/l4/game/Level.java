package at.ac.tuwien.policenauts.l4.game;

import java.util.ArrayList;
import java.util.List;

/**
 * Container for all level related information.
 *
 * @author Michael Pucher
 */
class Level {
    private final List<Segment> segments;
    private List<NonPlayerObject> currentObjects = new ArrayList<>(10);
    private List<NonPlayerObject> nextObjects = new ArrayList<>(10);
    private int currentSegment = 0;

    /**
     * Initialize a level.
     *
     * @param segments The segments of a level
     */
    Level(List<Segment> segments) {
        this.segments = segments;
    }

    /**
     * Start the level, initialize starting conditions.
     */
    void startLevel() {
        currentSegment = 0;

        // Initialize objects for rendering/collision detection
        currentObjects.addAll(segments.get(currentSegment).getObjects());
        nextObjects.addAll(segments.get(currentSegment + 1).getObjects());
    }

    /**
     * Update all (visible) level objects.
     *
     * @param tpf Time per frame in millseconds
     */
    void updateLevel(float tpf) {
        for (NonPlayerObject obj : currentObjects)
            obj.update(tpf);
        for (NonPlayerObject obj : nextObjects)
            obj.update(tpf);
    }

    /**
     * Render a level, using the texture manager.
     *
     * @param textureManager Texture manager, needs to have the canvas aquired already
     */
    void renderLevel(TextureManager textureManager) {
        for (NonPlayerObject obj : currentObjects)
            textureManager.drawSprite(obj.currentSprite(), obj.currentPosition());
        for (NonPlayerObject obj : nextObjects)
            textureManager.drawSprite(obj.currentSprite(), obj.currentPosition());
    }
}
