package org.game.collision;

import com.almasb.fxgl.core.collection.PropertyMap;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import org.game.TowerDefenseType;
import org.game.components.EnemyComponent;
import org.game.event.EnemyReachedMonumentEvent;
import org.game.event.KilledEvent;

import static org.game.TowerDefenseGameApp.*;

public class BulletHandler extends CollisionHandler {
    public BulletHandler() {
        super(TowerDefenseType.BULLET, TowerDefenseType.ENEMY);
    }

    @Override
    protected void onCollisionBegin(Entity bullet, Entity enemy) {
        if (finalboss == true) {
            bullet.removeFromWorld();
            EnemyComponent e = enemy.getComponent(EnemyComponent.class);
            if (e.getHealth() == 3) {
                enemy.setOpacity(0.9);
                e.setHealth(10);
                return;
            }
            if (e.getHealth() == 10) {
                enemy.setOpacity(0.8);
                e.setHealth(9);
                return;
            }
            if (e.getHealth() == 9) {
                enemy.setOpacity(0.7);
                e.setHealth(8);
                return;
            }
            if (e.getHealth() == 8) {
                enemy.setOpacity(0.6);
                e.setHealth(7);
                return;
            }
            if (e.getHealth() == 7) {
                enemy.setOpacity(0.5);
                e.setHealth(6);
                return;
            }
            if (e.getHealth() == 6) {
                enemy.setOpacity(0.4);
                e.setHealth(5);
                return;
            }
            if (e.getHealth() == 5) {
                enemy.setOpacity(0.3);
                e.setHealth(4);
                return;
            }
            if (e.getHealth() == 4) {
                enemy.setOpacity(0.2);
                e.setHealth(2);
                return;
            }
            if (e.getHealth() == 2) {
                enemy.setOpacity(0.1);
                e.setHealth(1);
                return;
            }
            FXGL.getEventBus().fireEvent(new KilledEvent(enemy));
            if (e.getHealth() == 1) {
                PropertyMap state = FXGL.getWorldProperties();
                state.setValue("money", state.getInt("money"));
                enemy.removeFromWorld();
                e.setHealth(0);
                score = 0;
                winGame();
            }
        } else {
            if (measure == 0) {
                bullet.removeFromWorld();
                EnemyComponent e = enemy.getComponent(EnemyComponent.class);
                if (e.getHealth() == 3) {
                    enemy.setOpacity(0.7);
                    e.setHealth(2);
                    return;
                }
                if (e.getHealth() == 2) {
                    enemy.setOpacity(0.35);
                    e.setHealth(1);
                    return;
                }
                FXGL.getEventBus().fireEvent(new KilledEvent(enemy));
                if (e.getHealth() == 1) {
                    PropertyMap state = FXGL.getWorldProperties();
                    state.setValue("money", state.getInt("money") + 1);
                    enemy.removeFromWorld();
                    e.setHealth(0);
                }
            }
            if (measure == 1) {
                bullet.removeFromWorld();
                EnemyComponent e = enemy.getComponent(EnemyComponent.class);
                if (e.getHealth() == 3) {
                    enemy.setOpacity(0.5);
                    enemy.setRotation(180);
                    e.setHealth(1);
                    return;
                }
                FXGL.getEventBus().fireEvent(new KilledEvent(enemy));
                if (e.getHealth() == 1) {
                    PropertyMap state = FXGL.getWorldProperties();
                    state.setValue("money", state.getInt("money") + 1);
                    enemy.removeFromWorld();
                    e.setHealth(0);
                }
            }
        }

    }//enemy.getInt("Health");
}