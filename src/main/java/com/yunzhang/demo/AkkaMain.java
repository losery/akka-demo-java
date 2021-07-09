package com.yunzhang.demo;

import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.dispatch.Dispatchers;
import akka.pattern.Patterns;
import akka.util.Timeout;
import scala.concurrent.Await;
import scala.concurrent.Future;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class AkkaMain {

    public static void main(String[] args) throws Exception {
        ActorSystem actorSystem = ActorSystem.create("flow");//.getDispatcher();
        actorSystem.dispatchers().lookup(Dispatchers.DefaultDispatcherId());
        actorSystem.actorOf(Props.create(GreetActor.class), GreetActor.class.getSimpleName());
        actorSystem.actorOf(Props.create(Greeted2Actor.class), Greeted2Actor.class.getSimpleName());
//        actorSystem.actorOf(Props.create(GreetedHandler.class), GreetedHandler.class.getSimpleName());

//        actorSystem.actorSelection("/user/GreetActor").tell("msg1", ActorRef.noSender());
//        System.out.println();

//        ExecutionContext executionContext = actorSystem.dispatcher();
//
//        Future<String> f1 = Futures.future(new Callable<String>() {
//            public String call() {
//                return "Hello" + "World";
//            }
//        }, executionContext);
//
//        Future<Integer> f2 = f1.map(new Mapper<String, Integer>() {
//            public Integer apply(String s) {
//                return s.length();
//            }
//        }, executionContext);
//
//        f2.onSuccess(new PrintResult<Integer>(), actorSystem.dispatcher());

//        ActorRef greetActor = actorSystem.actorOf(Props.create(GreetHandler.class), "greet");
////        greetActor.tell("", ActorRef.noSender());
//
////        actorSystem.eventStream().subscribe(greetedActor, String.class);
//
////        greetActor.tell("hello", null);
//
        Timeout timeout = new Timeout(5, TimeUnit.SECONDS);
        ActorSelection actorSelection = actorSystem.actorSelection("/user/GreetActor");

        Future<Object> future = Patterns.ask(actorSelection, new HashMap<>(), timeout);
//        future.onComplete(new Function1<Try<Object>, Object>() {
//            public Object apply(final Try<Object> v1) {
//                return null;
//            }
//        }, executionContext);
//
        Object result = Await.result(future, timeout.duration());
        System.out.println(result);
//
//        try {
//            System.out.println(">>> Press ENTER to exit <<<");
//            System.in.read();
//        } catch (IOException ignored) {
//        } finally {
//            actorSystem.terminate();
//        }
    }

}
