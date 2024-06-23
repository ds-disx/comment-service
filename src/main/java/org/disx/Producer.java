package org.disx;

import io.vertx.core.json.JsonObject;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

public class Producer {

    @Inject
    @Channel("comment-queue")
    Emitter<JsonObject> emitter;

    public void send(JsonObject message) {
        emitter.send(message);
    }
}
