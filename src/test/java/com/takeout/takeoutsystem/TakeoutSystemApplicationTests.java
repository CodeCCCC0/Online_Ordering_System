package com.takeout.takeoutsystem;

import com.google.code.kaptcha.Producer;
import com.takeout.TakeoutSystemApplication;
import com.takeout.controller.Controller;
import com.takeout.dao.FoodMapper;
import com.takeout.pojo.FoodCollection;
import com.takeout.pojo.Food;
import com.takeout.pojo.Order;
import com.takeout.service.FoodService;
import com.takeout.util.MD5Util;
import com.takeout.util.MailSend;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TakeoutSystemApplication.class)
class TakeoutSystemApplicationTests {
    @Value("${spring.kafka.bootstrap-servers}")
    private String logggg;
    @Autowired
    HttpServletRequest request;
    @Autowired
    FoodService foodService;
    @Autowired
    FoodMapper foodMapper;
    @Autowired
    Controller controller;
    @Autowired
    MailSend mailSend;
    @Autowired
    @Qualifier("emailCodeProducer")
    private Producer kaptcharProducer;
    @Test
    public void getLoggg(){
        System.out.println(logggg);
    }
    @Test
    public void getFoodTest(){
        System.out.println(controller.getFood(4).toString());
    }
    @Test
    public void getUid(){
        String str = UUID.randomUUID().toString();
        System.out.println(str);
    }
    @Test
    public void selectFoods(){

        List<Food> foodList = foodMapper.selectFoods(0, 10);
        for(Food food : foodList){
            System.out.println(food.toString());
        }
    }
    @Test
    public void getfoodscount(){
//        System.out.println(controller.foodsCount("鸡肉"));
    }
    @Test
    public void selectFoodTargets(){
        List<String> list = foodMapper.selectFoodClassifications();
        for (String str : list){
            System.out.println(str);
        }
    }
    @Test
    public void controllerTest(){
//        List<Food> foodList = controller.searchFoods("牛", null, null, 0, 20);
//        for(Food food : foodList){
//            System.out.println(food.toString());
//        }
    }
    @Test
    public void mailSend(){
        String content = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">" +
                    "<head>" +
                        "<meta charset=\"UTF-8\">" +
                        "<title>测试邮件</test>" +
                    "</head>" +
                    "<body>" +
                        "<h1 style=\"color:deepskyblue\"><strong>你好你好！</strong></h1>" +
                    "</body>" +
                "</html>";
        mailSend.sendMail("cyk9263@qq.com", "testMail", content);
    }
    @Test
    public void emailIdenti(){
        String str = kaptcharProducer.createText();
        System.out.println(str);
    }
    @Test
    void testsearch(){
        List<Food> l2 = foodMapper.searchFoods("[肉饼]", null, null, 0, 20);
        for(Food food : l2){
            System.out.println(food.toString());
        }
    }
    @Test
    void testAddCollect(){
        FoodCollection coll = new FoodCollection();
        coll.setFoodId(4);
        coll.setFoodName("蔬菜");
        coll.setUname("asd");
        System.out.println(foodMapper.addToCollection(coll));
    }
    @Test
    void testCollectSel(){
        System.out.println(foodService.isCollect(92, "Chalotte"));
    }
    @Test
    void testDelFormCollect(){
        System.out.println(foodService.delFromCollection(85));
    }
    @Test
    void testAddOrder(){
        Order order = new Order();
        order.setUname("Chalotte");
        order.setFoodId(85);
        order.setSpecification("不辣");
        order.setAmount(3);
        order.setAddress("address");
        foodService.createOrder(order);
    }
    @Test
    void testMapSel(){
        List<Map<String, Object>> mapList = foodMapper.testMapSel("Chalotte");
        System.out.println(mapList.toString());
    }
    @Test
    void testCollectionSel(){
        List<Map<String, Object>> mapList = foodMapper.selectUserCollections("Chalotte");
        System.out.println(mapList.toString());
    }
    @Test
    void testGetOrders(){
        System.out.println(foodService.selectUserOrders("Chalotte").toString());
    }
    @Test
    void encodeToMd5() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String pwd = "123456";
        String md5Pwd = MD5Util.EncoderPwdByMD5(pwd);
        System.out.println(pwd + ", " + md5Pwd);
    }
}
