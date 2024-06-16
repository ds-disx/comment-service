package org.disx;

import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

public class Producer {

    @Inject
    @Channel("comment-queue")
    Emitter<Comment> emitter;

    public void send(Comment comment) {
        emitter.send(comment);
    }
}
