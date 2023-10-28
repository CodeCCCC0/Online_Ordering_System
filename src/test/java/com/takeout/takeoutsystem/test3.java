package com.takeout.takeoutsystem;

import com.alibaba.fastjson.JSONObject;
import com.takeout.TakeoutSystemApplication;
import com.takeout.dao.FoodMapper;
import com.takeout.pojo.Cart;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TakeoutSystemApplication.class)
public class test3 {
    @Autowired
    private FoodMapper foodMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    private String redisKey = "verify:identiCode";

    @Test
    public void testKey(){
        Set list = redisTemplate.opsForHash().keys("cart:uname");
        Map map = redisTemplate.opsForHash().entries("cart:uname");
        System.out.println("map.toString() = " + map.toString());
//        Object[] list1 = list.toArray();
//        System.out.println(list1[1]);
        System.out.println("map.get(\"food2/specification\") = " + map.get("food2/specification"));
    }

    @Test
    public void testString(){
        System.out.println("redisTemplate.opsForValue().get(redisKey) = " + redisTemplate.opsForValue().get(redisKey));
    }

    @Test
    public void testHash(){
        redisTemplate.opsForHash().put("HashTest", "id", 8);
        System.out.println("redisTemplate.opsForHash().get(\"HashTest\", \"id\") = " + redisTemplate.opsForHash().get("HashTest", "id"));
    }

    @Test
    public void testStringSet(){
        redisTemplate.opsForValue().set(redisKey, "aa42");
        System.out.println("redisTemplate.opsForValue().get(redisKey) = " + redisTemplate.opsForValue().get(redisKey));
    }

    @Test
    public void testSet(){
        redisTemplate.opsForSet().add("setTest", "4a5b", "f564", "ui31", "lk99");
        System.out.println("redisTemplate.opsForSet().pop(\"setTest\") = " + redisTemplate.opsForSet().pop("setTest"));
    }

    @Test
    public void jsonTest() {
        String jsonStr = "{'id':'4','name':'asd'}";
        Map<String, Object> map = (Map<String, Object>)JSONObject.parse(jsonStr);
        System.out.println("map.get(\"name\") = " + map.get("name"));
    }

    @Test
    public void selectTest(){
//        String uname = "'uun'; drop table cart";
//        Cart cart = foodMapper.testSelect(uname);
//        System.out.println(cart.toString());
    }

    @Test
    public void intTest(){
        int[] arr = new int[10];
        arr[0] = 5;
        arr[3] = 8;
        for(int i : arr){
            System.out.println(i);
        }
    }

    @Test
    public void testtt(){
        CQueue cQueue = new CQueue();
        cQueue.appendTail(53);
        cQueue.appendTail(66);
        cQueue.appendTail(32);
        cQueue.output();
        System.out.println("cQueue.deleteHead() = " + cQueue.deleteHead());
        System.out.println("cQueue.deleteHead() = " + cQueue.deleteHead());
        System.out.println("cQueue.deleteHead() = " + cQueue.deleteHead());
        System.out.println("cQueue.deleteHead() = " + cQueue.deleteHead());
    }

    @Test
    public void entryTest(){
        Map<String, String> map = new HashMap<>();
        map.put("a", "hello");
        map.put("b", "world");
        map.put("c", "yes");
        for(String key : map.keySet()){
            System.out.println(key + " - " + map.get(key));
        }
//        Set<Map.Entry<String, String>> entrySets = map.entrySet();
//        for(Map.Entry<String, String> entry : entrySets){
//            System.out.println(entry.getKey() + " - " + entry.getValue());
//        }
//        String[] key = map.keySet().toArray(new String[0]);
//        System.out.println(key[1]);
    }
}

class CQueue {
    private Stack stackIn;
    private Stack stackOut;

    public CQueue() {
        stackIn  = new Stack(10);
        stackOut = new Stack(10);
    }

    public void appendTail(int value) {
        while(stackOut.hand>0){
            stackIn.put(stackOut.pop());
        }
        stackIn.put(value);
    }

    public int deleteHead() {
        while(stackIn.hand>0){
            stackOut.put(stackIn.pop());
        }
        if(stackOut.hand==0){
            return -1;
        }else{
            return stackOut.pop();
        }
    }

    public void output(){
        if(stackIn.hand==0){
            stackOut.print();
        }else{
            stackIn.print();
        }
    }
}

class Stack {
    private int[] stack;
    public int hand = 0;

    public Stack(int length){
        stack = new int[length];
    }

    public void put(int num){
        stack[hand] = num;
        hand++;
    }
    public int pop(){
        hand--;
        return stack[hand];
    }
    public void print(){
        for(int i : stack){
            System.out.print(i + ", ");
        }
    }
}
