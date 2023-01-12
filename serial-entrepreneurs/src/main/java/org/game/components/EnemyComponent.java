package org.game.components;

import com.almasb.fxgl.core.collection.PropertyMap;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import org.game.TowerDefenseGameApp;

import javafx.geometry.Point2D;
import java.util.List;


import org.game.event.EnemyReachedMonumentEvent;

import static org.game.TowerDefenseGameApp.finalboss;
import static org.game.TowerDefenseGameApp.score;

public class EnemyComponent extends Component {
    private List<Point2D> waypoints;
    private Point2D nextWaypoint;
    private double speed;
    private static int numCollisions = 1;
    private int health = 3;
    public static int gone = 0;

    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public void onAdded() {
        waypoints = ((TowerDefenseGameApp) FXGL.getApp()).getWaypoints();
        nextWaypoint = waypoints.remove(0);
    }

    @Override
    public void onUpdate(double tpf) {
        numCollisions++;

        speed = tpf * 80 * 2;
        if (score == 100) {
            speed = tpf * 60 *2;
        }

        Point2D velocity = nextWaypoint.subtract(entity.getPosition())
                .normalize()
                .multiply(speed);

        entity.translate(velocity);

        if (nextWaypoint.distance(entity.getPosition()) < speed) {
            entity.setPosition(nextWaypoint);

            if (!waypoints.isEmpty()) {
                nextWaypoint = waypoints.remove(0);
            } else {
                entity.removeFromWorld();
                gone++;
                PropertyMap state = FXGL.getWorldProperties();
                if (finalboss) {
                    state.setValue("health", state.getInt("health") - 200);
                }
                else if (numCollisions % 2 == 0) {
                    state.setValue("health", state.getInt("health") - 15);
                } else if (numCollisions % 3 == 0) {
                    state.setValue("health", state.getInt("health") - 10);
                } else {
                    state.setValue("health", state.getInt("health") - 20);
                }
                if (state.getInt("health") <= 0) {
                    TowerDefenseGameApp opApp = (TowerDefenseGameApp) FXGL.getApp();
                    opApp.gameOver();
                }
                FXGL.getEventBus().fireEvent(new EnemyReachedMonumentEvent());
            }
        }
    }
}
