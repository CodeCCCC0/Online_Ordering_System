package com.takeout.takeoutsystem;

import com.takeout.TakeoutSystemApplication;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.Stack;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TakeoutSystemApplication.class)
public class Tests2 {
    @Autowired
    private KafkaProducer kafkaProducer;

    @Test
    public void kafkaTest() {
        kafkaProducer.sendMessage("test", "你好");
        kafkaProducer.sendMessage("test", "在吗");
        kafkaProducer.sendMessage("test", "这里是");
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void SystemGetProperty(){
        String str = System.getProperty("user.dir");
        System.out.println("User.dir: " + str);
        String str2 = str.substring(str.lastIndexOf("\\") + 1, str.length());
        System.out.println("last: " + str2);
    }

    @Test
    public void mathhh(){
        int a = -1;
        System.out.println(Integer.toBinaryString(a));
        System.out.println(Integer.toBinaryString(a >>> 1) + " : " + (a >>> 1));
    }

    @Test
    public void linkedListTest(){
        List<Integer> l1 = new ArrayList<>();
        LinkedList<Integer> llist = new LinkedList<>();
        llist.push(5);
        llist.push(22);
        llist.push(84);
        System.out.println("llist.size() = " + llist.size());
        System.out.println("llist.getFirst() = " + llist.getFirst());
        System.out.println("llist.getLast() = " + llist.getLast());
//        System.out.println("llist.toString() = " + llist.toString());
//        System.out.println("llist.remove(0) = " + llist.remove(0));
//        llist.push(68);
//        System.out.println("llist.toString() = " + llist.toString());
//        System.out.println("llist.remove(0) = " + llist.remove(0));
//        System.out.println("llist.remove(0) = " + llist.remove(0));
//        System.out.println("llist.remove(0) = " + llist.remove(0));
//        System.out.println("llist.isEmpty() = " + llist.isEmpty());
    }

    @Test
    public void stackTest(){
        Stack<Integer> stack = new Stack<>();
        stack.add(6);
        stack.add(21);
        stack.add(5);
        System.out.println("stack.toString() = " + stack.toString());
        System.out.println("stack.pop() = " + stack.pop());
        System.out.println("stack.pop() = " + stack.pop());
        System.out.println("stack.pop() = " + stack.pop());
        System.out.println("stack.empty() = " + stack.empty());
    }
}
@Component
class KafkaProducer {
    @Autowired
    private KafkaTemplate kafkaTemplate;
    public void sendMessage(String topic, String content){
        kafkaTemplate.send(topic, content);
    }
}

@Component
class KafkaConsumer {
    @KafkaListener(topics = {"test"})
    public void receiveMessage(ConsumerRecord record){
        System.out.println(record.value());
    }
}