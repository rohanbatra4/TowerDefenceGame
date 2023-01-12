package org.game;

import com.almasb.fxgl.dsl.components.OffscreenCleanComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.game.components.*;
import org.game.tower.TowerDataComponent;

import static com.almasb.fxgl.dsl.FXGLForKtKt.entityBuilder;
import static org.game.TowerDefenseGameApp.finalboss;
import static org.game.TowerDefenseGameApp.score;

public class TowerDefenseFactory implements EntityFactory {
    private static int enemyID = 1;
    @Spawns("Enemy")
    public Entity spawnEnemy(SpawnData data) {
        enemyID++;
        if (finalboss == true) {
            return entityBuilder(data)
                    .type(TowerDefenseType.ENEMY)
                    .viewWithBBox("megaenemy_adobespark.png")
                    .with(new CollidableComponent(true))
                    .with(new EnemyComponent())
                    .build();
        }
        else if (enemyID % 2 == 0) {
            return entityBuilder(data)
                    .type(TowerDefenseType.ENEMY)
                    .viewWithBBox("enemypng.png")
                    .with(new CollidableComponent(true))
                    .with(new EnemyComponent())
                    .build();
        } else if (enemyID % 3 == 0) {
            return entityBuilder(data)
                    .type(TowerDefenseType.ENEMY)
                    .viewWithBBox("enemy2.png")
                    .with(new CollidableComponent(true))
                    .with(new EnemyComponent())
                    .build();
        } else {
            return entityBuilder(data)
                    .type(TowerDefenseType.ENEMY)
                    .viewWithBBox("enemy3.png")
                    .with(new CollidableComponent(true))
                    .with(new EnemyComponent())
                    .build();
        }
    }
    @Spawns("WaterTower")
    public Entity spawnWaterTower(SpawnData data) {
        return entityBuilder(data)
                .type(TowerDefenseType.TOWER)
                .view("Water Tower.png")
                .with(new CollidableComponent(true))
                .with(new TowerDataComponent())
                .with(new WaterTowerComponent())
                .build();
    }
    @Spawns("AirTower")
    public Entity spawnAirTower(SpawnData data) {
        return entityBuilder(data)
                .type(TowerDefenseType.TOWER)
                .view("air tower.png")
                .with(new CollidableComponent(true))
                .with(new TowerDataComponent())
                .with(new AirTowerComponent())
                .build();
    }
    @Spawns("FireTower")
    public Entity spawnFireTower(SpawnData data) {
        return entityBuilder(data)
                .type(TowerDefenseType.TOWER)
                .view("tower.png")
                .with(new CollidableComponent(true))
                .with(new TowerDataComponent())
                .with(new FireTowerComponent())
                .build();
    }

    @Spawns("Bullet")
    public Entity spawnBullet(SpawnData data) {
        return entityBuilder(data)
                .type(TowerDefenseType.BULLET)
                .viewWithBBox(new Rectangle(15, 5, Color.DARKGREY))
                .with(new CollidableComponent(true))
                .with(new OffscreenCleanComponent())
                .build();
    }

    public static int getEnemyID() {
        return enemyID;
    }

    public static void setEnemyID(int enemyID) {
        TowerDefenseFactory.enemyID = enemyID;
    }
}
