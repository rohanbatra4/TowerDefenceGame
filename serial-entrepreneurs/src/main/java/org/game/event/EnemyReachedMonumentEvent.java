package org.game.event;

import javafx.event.Event;
import javafx.event.EventType;
public class EnemyReachedMonumentEvent extends Event {
    public static final EventType<EnemyReachedMonumentEvent> ANY
            = new EventType<>(Event.ANY, "EnemyReachedMonumentEvent");

    public EnemyReachedMonumentEvent() {
        super(ANY);
    }
}
