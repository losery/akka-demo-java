package com.yunzhang.demo;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class GreetActor extends BaseActor {

    @Override
    public void process(Map<String, Object> s) {
        message.put("aa", "aa");
    }

    @Override
    public Class<? extends BaseActor> getNext() {
        return Greeted2Actor.class;
    }
}
