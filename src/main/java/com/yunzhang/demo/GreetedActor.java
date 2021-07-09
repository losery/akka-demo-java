package com.yunzhang.demo;

import akka.actor.AbstractActor;
import akka.actor.Props;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GreetedActor extends AbstractActor {
    private final int max;
    private int count;

    public GreetedActor(int max) {
        this.max = max;
        getContext().actorOf(Props.create(Greeted2Actor.class), "greeted2");
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder().matchAny(greeted -> {
            log.info("greeted:" + greeted);
            getContext().getChild("greeted2").forward("greeted2", getContext());
//                getSender().tell("hhhhh boss", getSelf());
//                count++;
//                log.info("greeted:"+greeted+ count);
//                if (count < max) {
//                    getContext().getParent().tell("hello", null);
//                }
        }).build();
    }

    @Override
    public void preStart() throws Exception {
        super.preStart();
        log.info("preStart");
    }

    @Override
    public void postStop() throws Exception {
        super.postStop();
        log.info("postStop");
    }
}
