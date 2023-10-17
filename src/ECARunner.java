import javafx.animation.AnimationTimer;

import java.util.concurrent.TimeUnit;

public class ECARunner {

    /**
     * Starts the animation timer that controls the visualization.
     * @param grid of cells to be shown to the screen. At each time step
     *             it is evolved using its given ruleset.
     */
    public static void run(Grid2D grid) {
        AnimationTimer timer = new AnimationTimer() {
            private long prevUpdate = 0;
            @Override
            public void handle(long now) {
                if (now - prevUpdate >= TimeUnit.SECONDS.toNanos(1)) {
                    grid.nextGeneration();
                    prevUpdate = now;
                }
            }
        };
        timer.start();
    }
}
