package org.game.components;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.ProjectileComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.time.LocalTimer;
import org.game.Config;
import org.game.TowerDefenseGameApp;
import org.game.TowerDefenseType;
import javafx.geometry.Point2D;
import javafx.util.Duration;

public class WaterTowerComponent extends Component {
    private LocalTimer shootTimer;

    @Override
    public void onAdded() {
        shootTimer = FXGL.newLocalTimer();
        shootTimer.capture();
    }

    @Override
    public void onUpdate(double tpf) {

        if (shootTimer.elapsed(Duration.seconds(1.1))) {
            FXGL.getGameWorld()
                    .getClosestEntity(entity, e -> e.isType(TowerDefenseType.ENEMY))
                    .ifPresent(nearestEnemy -> {
                        shoot(nearestEnemy);
                        shootTimer.capture();
                    });
        }
    }

    private void shoot(Entity enemy) {
        Point2D position = getEntity().getPosition();

        Point2D direction = enemy.getPosition().subtract(position);
        if (position.distance(enemy.getPosition()) < 450) {
            Entity bullet = FXGL.spawn("Bullet", position);
            TowerDefenseGameApp.getAllEntities().add(bullet);
            bullet.addComponent(new ProjectileComponent(direction, Config.BULLET_SPEED));
        }
    }
}




