package com.yunzhang.demo;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class Greeted2Actor extends BaseActor {

    @Override
    public void process(Map<String, Object> s) {
        message.put("bb", "bb");

    }

    @Override
    public Class<? extends BaseActor> getNext() {
        return null;
    }
}
