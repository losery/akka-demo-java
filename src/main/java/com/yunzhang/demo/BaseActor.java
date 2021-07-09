package com.yunzhang.demo;

import akka.actor.AbstractActor;

import java.util.HashMap;
import java.util.Map;

/**
 * User: yunzhang
 * Date: 2020/7/15
 */
public abstract class BaseActor extends AbstractActor {
    protected Map<String, Object> message = new HashMap<>();

    @Override
    public Receive createReceive() {
        return receiveBuilder().match(Map.class, s -> {
            process(s);
            if (getNext() != null) {
                getContext().getSystem().actorSelection("/user/" + getNext().getSimpleName()).tell(
                    message,
                    getSender()
                );
            } else {
                getContext().dispatcher();
                getSender().tell(message, getSender());
            }
        }).build();
    }

    public abstract void process(Map<String, Object> s);

    public abstract Class<? extends BaseActor> getNext();

}
