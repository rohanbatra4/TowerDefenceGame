package org.game.event;

import com.almasb.fxgl.entity.Entity;
import javafx.event.Event;
import javafx.event.EventType;

public class KilledEvent extends Event {

    public static final EventType<KilledEvent> ANY
            = new EventType<>(Event.ANY, "ENEMY_KILLED");

    private Entity enemy;

    public Entity getEnemy() {
        return enemy;
    }

    public KilledEvent(Entity enemy) {
        super(ANY);
        this.enemy = enemy;

    }
}
