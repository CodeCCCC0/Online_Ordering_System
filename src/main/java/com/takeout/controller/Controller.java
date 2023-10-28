package com.takeout.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.code.kaptcha.Producer;
import com.takeout.pojo.*;
import com.takeout.service.FoodService;
import com.takeout.service.UserService;
import com.takeout.util.MailSend;
import com.takeout.util.NewDate;
import com.takeout.util.RedisUtil;
import com.takeout.util.UploadUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.concurrent.TimeUnit;

@RestController
public class Controller {
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    private UserService userService;
    @Autowired
    private FoodService foodService;
    @Autowired
    private MailSend mailSend;
    @Autowired
    @Qualifier("emailCodeProducer")
    private Producer emailCodeProducer;
    @Autowired
    @Qualifier("VerificCodeProducer")
    private Producer kaptcharProducer;

    /*
     *---------------food---------------
     */
    @RequestMapping("/getfoods")
    public Map<String, Object> getFoods(@RequestParam("offset") int offset, @RequestParam("limit") int limit){//
        return foodService.selectFoods(offset, limit);
    }

    @RequestMapping("/getfood")
    public Food getFood(@RequestBody int foodId){
        return foodService.selectFoodById(foodId);
    }

    @RequestMapping("/getfoodcount")
    public int getFoodCount(){
        return foodService.getFoodCount();
    }

    @RequestMapping("/addfood")
    public String addFood(MultipartFile foodImg,
                       String foodName,
                       String classification,
                       String ingredients,
                       String specification,
                       double price) throws IOException {
        Food food = new Food();
        if(foodImg==null){
            return "图片为空！";
        }else{
            String imgPath = UploadUtil.uploadImage(foodImg);
            System.out.println("imgPath = " + imgPath);
            food.setImage(imgPath);
            food.setFoodName(foodName);
            food.setClassification(classification);
            food.setIngredients(ingredients);
            food.setSpecification(specification);
            food.setPrice(price);

            int rs = foodService.addFood(food);
            if(rs!=0){
                return "添加菜品成功！id: " + rs;
            }else{
                return "添加菜品失败！";
            }
        }
    }

    @RequestMapping("/delfoodsbyid")
    public int delFoodsById(@RequestBody int[] foodsId, HttpServletRequest request){
//        String uname = request.getSession().getAttribute("user").toString();
//        if(userService.getUserInfo(uname).getUserType()==1){
//            return foodService.updateFoodStatus(foodsId);
//        }else{
//            return 503;
//        }
        return foodService.updateFoodStatus(foodsId);
    }

    @RequestMapping("/updatefoodinfo")
    public String updateFoodInfo(MultipartFile foodImg,
                              int foodId,
                              String foodName,
                              String classification,
                              String ingredients,
                              String specification,
                              double price) throws IOException {
        Food food = new Food();
        String imgPath = null;
        if(foodImg!=null){
            imgPath = UploadUtil.uploadImage(foodImg);
        }
        food.setFoodId(foodId);
        food.setImage(imgPath);
        food.setFoodName(foodName);
        food.setClassification(classification);
        food.setIngredients(ingredients);
        food.setSpecification(specification);
        food.setPrice(price);

        int rs = foodService.updateFoodInfo(food);
        if(rs!=0){
            return "菜品信息编辑成功！";
        }else{
            return "菜品信息编辑失败！";
        }
    }

    @RequestMapping("/getfoodclassifications")
    public List<String> getFoodClassifications(){
        return foodService.selectFoodClassifications();
    }

    @RequestMapping("/searchfoods")
    public Map<String, Object> searchFoods(@RequestParam("search") String searchText,
                                           @RequestParam("classification") String classification,
                                           @RequestParam("order") String order,
                                           @RequestParam("offset") int offset,
                                           @RequestParam("limit") int limit){
        if("null".equals(classification)){
            classification = null;
        }
        if("null".equals(order)){
            order = null;
        }
        return foodService.searchFoods(searchText, classification, order, offset, limit);
    }

    @RequestMapping("/searchallfoods")
    public Map<String, Object> searchAllFoods(@RequestParam("search") String searchText,
                                              @RequestParam("offset") int offset,
                                              @RequestParam("limit") int limit){
        return foodService.searchFoods(searchText, null, null, offset, limit);
    }

    @RequestMapping("/getresultscount")
    public int foodsCount(@RequestParam("search") String searchText,
                          @RequestParam("classification") String classification){
        if("null".equals(classification)){
            classification = null;
        }
        return foodService.resultsCount(searchText, classification);
    }

    @RequestMapping("/createorder")
    public int createOrder(@RequestBody Map<String, Object> map, HttpServletRequest request){
        int foodId = Integer.parseInt((String)map.get("foodId"));
        int amount = (int) map.get("amount");
        Order order = new Order();
        String[] addressStr = map.get("address").toString().split("/");
        String specification = map.get("specification").toString();
        String address = map.get("address").toString().split("/")[0];
        String uname = request.getSession().getAttribute("user").toString();
        if(addressStr.length==1||addressStr.length==2&&"".equals(addressStr[1])){
            String tel = userService.getUserInfo(uname).getTel();
        }else{
            String tel = addressStr[1];
        }

        order.setUname(uname);
        order.setFoodId(foodId);
        order.setAmount(amount);
        order.setSpecification(specification);
        order.setAddress(address);

        return foodService.createOrder(order);
    }

    @RequestMapping("/getuserorders")
    public List<Map<String, Object>> getUserOrders(HttpServletRequest request){
        String uname = request.getSession().getAttribute("user").toString();
        return foodService.selectUserOrders(uname);
    }

    @RequestMapping("/searchorder")
    public List<Map<String, Object>> searchUserOrder(@RequestBody String searchText, HttpServletRequest request){
        String uname = request.getSession().getAttribute("user").toString();
        return foodService.searchUserOrder(uname, searchText);
    }

    @RequestMapping("/cancelorder")
    public int cancelOrder(@RequestBody int orderId){
        return foodService.delFromOrdertab(orderId);
    }

    @RequestMapping("/editorder")
    public int editOrder(@RequestBody Map<String, Object> map){
        int orderId = (int) map.get("orderId");
        int amount = (int) map.get("amount");
        double totalPrice = (double) map.get("totalPrice");
        String specification = (String) map.get("specification");

        return foodService.updateOrderInfo(orderId, specification, amount, totalPrice);
    }

    @RequestMapping("/getorders")
    public Map<String, Object> getOrders(@RequestParam("offset") int offset, @RequestParam("limit") int limit){
        return foodService.selectOrders(offset, limit);
    }

    @RequestMapping("/receivingorder")
    public int receivingOrder(@RequestBody int orderId){
        return foodService.receivingOrder(orderId);
    }

    @RequestMapping("/cancelreceivingorder")
    public int cancelReceivingOrder(@RequestBody int orderId){
        return foodService.cancelReceivingOrder(orderId);
    }

    @RequestMapping("/addtocollection")
    public int addToCollection(@RequestBody Map<String, Object> map, HttpServletRequest request){
        int foodId = Integer.parseInt((String)map.get("foodId"));
        String foodName = (String)map.get("foodName");
        String loginUser = request.getSession().getAttribute("user").toString();
        FoodCollection collection = new FoodCollection(foodId, foodName, loginUser);
        return foodService.addToCollection(collection);
    }

    @RequestMapping("/delfromcollection")
    public int delFromCollection(@RequestBody Map<String, Object> map, HttpServletRequest request){
        int id = Integer.parseInt(map.get("id").toString());
        if(Boolean.parseBoolean(map.get("isDetail").toString())){
            String uname = request.getSession().getAttribute("user").toString();
            return foodService.delFormCollectInDetail(id, uname);
        }else{
            return foodService.delFromCollection(id);
        }
    }

    @RequestMapping("/iscollect")
    public boolean isCollect(@RequestBody int foodId, HttpServletRequest request){
        String uname = request.getSession().getAttribute("user").toString();
        return foodService.isCollect(foodId, uname);
    }

    @RequestMapping("/getcollections")
    public List<Map<String, Object>> getCollections(HttpServletRequest request){
        String uname = request.getSession().getAttribute("user").toString();
        return foodService.getCollections(uname);
    }

    @RequestMapping("/addtocart")
    public int addToCart(@RequestBody Map<String, Object> map, HttpServletRequest request){
        int foodId = Integer.parseInt((String)map.get("foodId"));
        int amount = (int) map.get("amount");
        String specification = map.get("specification").toString();
        String uname = request.getSession().getAttribute("user").toString();

        Cart cart = new Cart();
        cart.setUname(uname);
        cart.setFoodId(foodId);
        cart.setAmount(amount);
        cart.setSpecification(specification);

        return foodService.addToCart(cart);
    }

    @RequestMapping("/getcarts")
    public List<Map<String, Object>> getCarts(HttpServletRequest request){
        String uname = (String) request.getSession().getAttribute("user");
        return foodService.selectUserCarts(uname);
    }

    @RequestMapping("/settlement")
    public int settlement(@RequestBody Map<String, Object> map, HttpServletRequest request){
        String uname = request.getSession().getAttribute("user").toString();
        List<Order> orderList = new ArrayList<>();
        String tel;
        ArrayList arr = (ArrayList) map.get("carts");
        String[] addressTel = map.get("address").toString().split("/");
        String address = addressTel[0];
        if(addressTel.length==1||addressTel.length==2&&"".equals(addressTel[1])){
            tel = userService.getUserInfo(uname).getTel().toString();
        }else{
            tel = addressTel[1];
        }

        for (Object id : arr) {
            int cartId = (int) id;
            Map<String, Object> cart = foodService.selectCartById(cartId);
            Order order = new Order();
            order.setUname(uname);
            order.setFoodId(Integer.parseInt(cart.get("food_id").toString()));
            order.setSpecification(cart.get("specification").toString());
            order.setAmount((int) cart.get("amount"));
            order.setAddress(address);
            order.setTel(tel);

            foodService.createOrder(order);
        }
        return 1;
    }

    @RequestMapping("/delfromcart")
    public int delFromCart(@RequestBody int[] carts){
        return foodService.delFromCart(carts);
    }

    @RequestMapping("/updateCartInfo")
    public int updateCartInfo(@RequestBody Map<String, Object> map){
        int cardId = (int) map.get("cartId");
        int amount = (int) map.get("amount");
        String specification = map.get("specification").toString();

        return foodService.updateCartInfo(cardId, specification, amount);
    }
    /*
     *---------------user---------------
     */
    @RequestMapping("/emailidenticode")
    public Map<String, Object> emailIdentiCode(@RequestBody Map<String, String> map){
        String uname = map.get("uname");
        String email = map.get("email");
        Map<String, Object> resMap = new HashMap<>();
        if(uname!=null){
            if(!userService.isExistUser(uname)){
                resMap.put("position", 0);
                resMap.put("message", "用户不存在");
            }else if(!userService.isItsEmail(uname, email)){
                resMap.put("position", 3);
                resMap.put("message", "邮箱不是所注册邮箱");
            }else{
                sendEmailIdentiCode(email);
                resMap.put("position", -1);
                resMap.put("message", "验证码已发送，请注意查收");
            }
        }else{
            if(userService.isExistEmail(email)){
                resMap.put("position", 3);
                resMap.put("message", "邮箱已被注册");
            }else{
                sendEmailIdentiCode(email);
                resMap.put("position", -1);
                resMap.put("message", "验证码已发送，请注意查收");
            }
        }
        return resMap;
    }

    @RequestMapping("/changepwdemailcode")
    public Map<String, Object> changepwdemailcode(@RequestBody String email, HttpServletRequest request){
        String uname = request.getSession().getAttribute("user").toString();
        String userEmail = JSONObject.parse(email).toString();
        Map<String, Object> resMap = new HashMap<>();
        if(!userService.isItsEmail(uname, userEmail)){
            resMap.put("position", 3);
            resMap.put("message", "邮箱不是所注册邮箱");
        }else{
            sendEmailIdentiCode(userEmail);
            resMap.put("position", -1);
            resMap.put("message", "验证码已发送，请注意查收");
        }
        return resMap;
    }

    @RequestMapping("/changepwd")
    public Map<String, Object> changePassword(@RequestBody String emailCode, HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        String emailCodeString = JSONObject.parse(emailCode).toString();
        String emailIdentiCode = (String) redisUtil.valueGet("emailIdentiCode");
        String uname = request.getSession().getAttribute("user").toString();
        if(emailIdentiCode==null){
            map.put("position", -2);
            map.put("message", "邮箱验证码已过期，请重新发送");
        }else if(!emailCodeString.equals(emailIdentiCode)){
            map.put("position", 2);
            map.put("message", "邮箱验证码错误");
        }else{
            map.put("position", -1);
            map.put("message", "验证通过");
            redisUtil.delKey("emailIdentiCode");
        }
        return map;
    }

    @RequestMapping("/changepwdnext")
    public Map<String, Object> changePasswordNext(@RequestBody String newPassword, HttpServletRequest request) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Map<String, Object> map = new HashMap<>();
        String newPwd = JSONObject.parse(newPassword).toString();
        map = userService.updatePassword(newPwd, request.getSession().getAttribute("user").toString());
        return map;
    }

    @RequestMapping("/getidenticode")
    public void getKaptcha(HttpServletResponse response){
        String text = kaptcharProducer.createText();
        BufferedImage image = kaptcharProducer.createImage(text);

        redisUtil.valueSet("identiCode", text);

        response.setContentType("image/png");
        try {
            OutputStream os = response.getOutputStream();
            ImageIO.write(image, "png", os);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(redisUtil.valueGet("identiCode"));
    }

    @RequestMapping("/register")
    public Map<String, Object> register(@RequestBody Map<String, String> userInfo, HttpSession session) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Map<String, Object> map = new HashMap<>();
        if(!userInfo.get("emailIdentiCode").equals(redisUtil.valueGet("emailIdentiCode"))){
            map.put("position", 4);
            map.put("message", "邮箱验证码错误");
        }else if(!redisUtil.valueGet("identiCode").equals(userInfo.get("identiCode"))){
            map.put("position", 5);
            map.put("message", "验证码错误");
        }else{
            User user = new User();
            user.setUname(userInfo.get("uname"));
            user.setPwd(userInfo.get("password"));
            user.setEmail(userInfo.get("email"));
            user.setUserType(0);
            map = userService.register(user);
            session.removeAttribute("emailIdentiCode");
        }
        return map;
    }

    @RequestMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> userInfo,
                                     HttpServletResponse response,
                                     HttpSession session) throws IOException, NoSuchAlgorithmException {
        Map<String, Object> map = new HashMap<>();
        if(!userInfo.get("identiCode").equals(redisUtil.valueGet("identiCode"))){
            map.put("position", 2);
            map.put("message", "验证码错误");
        }else{
            User user = new User();
            user.setUname(userInfo.get("uname"));
            user.setPwd(userInfo.get("password"));
            map = userService.login(user);
            redisUtil.delKey("identiCode");
            if(Integer.parseInt(map.get("position").toString())==-1){
                session.setAttribute("user", user.getUname());
                session.setMaxInactiveInterval(60*60);
                Cookie cookie = new Cookie("sessionid", session.getId());
                cookie.setMaxAge(60*60);
                response.addCookie(cookie);
            }
        }
        return map;
    }

    @RequestMapping("/getsignstatus")
    public String getSignStatus(HttpServletRequest request){
        return request.getSession().getAttribute("user").toString();
    }

    @RequestMapping("/signout")
    public void signout(HttpServletRequest request){
        request.getSession().invalidate();
    }

    @RequestMapping("/retrievepwd")
    public Map<String, Object> retrievePassword(@RequestBody Map<String, String> identiInfo,
                                                HttpSession session){
        Map<String, Object> map = new HashMap<>();
        String emailIdentiCode = (String) redisUtil.valueGet("emailIdentiCode");
        String uname = identiInfo.get("uname");
        if(emailIdentiCode==null){
            map.put("position", -2);
            map.put("message", "邮箱验证码已过期，请重新发送");
        }else if(!identiInfo.get("emailIdentiCode").equals(emailIdentiCode)){
            map.put("position", 2);
            map.put("message", "邮箱验证码错误");
        }else{
            map.put("position", -1);
            map.put("message", "验证通过");
            redisUtil.delKey("emailIdentiCode");
            session.setAttribute("uname", uname);
            session.setMaxInactiveInterval(60*5);
        }
        return map;
    }

    @RequestMapping("/getuserinfo")
    public User getUserInfo(HttpServletRequest request){
        String username = request.getSession().getAttribute("user").toString();
        System.out.println("user: " + username);
        return userService.getUserInfo(username);
    }

    @RequestMapping("/updatepassword")
    public Map<String, Object> updatePassword(@RequestBody Map<String, String> passwordInfo, HttpSession session) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Map<String, Object> map = new HashMap<>();
        map = userService.updatePassword(passwordInfo.get("newPassword"), session.getAttribute("uname").toString());
        session.removeAttribute("uname");
        return map;
    }

    @RequestMapping("/updateuserinfo")
    public String updateUserInfo(MultipartFile userImage, String tel, String email, HttpServletRequest request) throws IOException {
        User user = new User();
        String uname = request.getSession().getAttribute("user").toString();
        String imgPath = null;
        if(userImage!=null){
            imgPath = UploadUtil.uploadImage(userImage);
        }
        user.setUname(uname);
        user.setUserImage(imgPath);
        user.setTel(tel);
        user.setEmail(email);

        int rs = userService.updateUserInfo(user);
        if(rs!=0){
            return "个人信息编辑成功！";
        }else{
            return "个人信息编辑失败！";
        }
    }
    /*
    邮件验证码发送，通过kaptchar验证码插件生成六位数字验证码，随后通过JavaMail编辑并发送邮件
     */
    public void sendEmailIdentiCode(String email){
        //调用kaptchar中Producer对象的createText()方法生成验证码
        String text = emailCodeProducer.createText();
        //存储到session中，以便进行验证
        redisUtil.valueSet("emailIdentiCode", text);
        //设置session生命周期，2分钟后过期，即验证码失效
        redisUtil.expire("emailIdentiCode", 60*2);
        //编辑邮件内容，将验证码发出
        String content = "<!DOCTYPE html>" +
                "<html lang='en'>" +
                    "<head>" +
                        "<meta charset='UTF-8'>" +
                        "<title>网上订餐系统_邮箱验证</title>" +
                    "</head>" +
                    "<body>" +
                        "<p>本次注册邮箱验证的验证码为</p>" +
                        "<h2 style='color: orangered'>" + text + "</h2>" +
                        "<p>有效期2分钟，请尽快使用</p>" +
                    "</body>" +
                "</html>";
        mailSend.sendMail(email, "网上订餐系统_邮箱验证", content);
    }
}
