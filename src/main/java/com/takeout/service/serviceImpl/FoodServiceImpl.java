package com.takeout.service.serviceImpl;

import com.takeout.dao.FoodMapper;
import com.takeout.dao.UserMapper;
import com.takeout.pojo.*;
import com.takeout.service.FoodService;
import com.takeout.util.NewDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FoodServiceImpl implements FoodService {
    @Autowired
    private FoodMapper foodMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public Map<String, Object> searchFoods(String searchText, String classification, String order, int offset, int limit) {
        String textReg = "[" + searchText + "]";
        Map<String, Object> map = new HashMap<>();
        List<Food> foodList = foodMapper.searchFoods(textReg, classification, order, offset, limit);
        int resultsCount = foodMapper.getResultsCount(textReg, classification);
        map.put("resultsCount", resultsCount);
        map.put("foodList", foodList);
        return map;
    }

    @Override
    public Map<String, Object> selectFoods(int offset, int limit) {
        Map<String, Object> map = new HashMap<>();
        List<Food> foodList = foodMapper.selectFoods(offset, limit);
        int foodCount = foodMapper.getFoodCount();
        map.put("foodList", foodList);
        map.put("foodCount", foodCount);
        return map;
    }

    @Override
    public Food selectFoodById(int foodId) {
        try {
            return foodMapper.selectFoodById(foodId);
        } catch (NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<String> selectFoodClassifications() {
        return foodMapper.selectFoodClassifications();
    }

    @Override
    public int getFoodCount() {
        return foodMapper.getFoodCount();
    }

    @Override
    public int resultsCount(String searchText, String classification) {
        String textReg = "[" + searchText + "]";
        return foodMapper.getResultsCount(textReg, classification);
    }

    @Override
    public boolean isCollect(int foodId, String uname) {
        FoodCollection collection = foodMapper.selectFoodFromCollection(foodId, uname);
        if(collection!=null){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public List<Map<String, Object>> getCollections(String uname) {
        List<Map<String, Object>> collections = foodMapper.selectUserCollections(uname);
        return collections;
    }

    @Override
    public List<Map<String, Object>> selectUserOrders(String uname) {
        List<Map<String, Object>> orderList = foodMapper.selectUserOrders(uname);
        for(Map<String, Object> order : orderList){
            if("已接单".equals(order.get("orderStatus"))){
                order.put("isRec", true);
            }else{
                order.put("isRec", false);
            }
        }
        return orderList;
    }

    @Override
    public List<Map<String, Object>> searchUserOrder(String uname, String searchText) {
        String textReg = "[" + searchText + "]";
        return foodMapper.searchUserOrder(uname, textReg);
    }

    @Override
    public Map<String, Object> selectOrders(int offset, int limit) {
        Map<String, Object> map = new HashMap<>();
        List<Map<String, Object>> mapList = foodMapper.selectOrders(offset, limit);
        int orderCount = foodMapper.getOrdersCount();
        int untreatedOrderCount = foodMapper.getUntreatedOrderCount();
        for(Map<String, Object> listItem : mapList){
            if("待接单".equals(listItem.get("orderStatus").toString())){
                listItem.put("orderStatus", "接单");
            }
        }
        map.put("untreatedOrderCount", untreatedOrderCount);
        map.put("orderCount", orderCount);
        map.put("orderList", mapList);
        return map;
    }

    @Override
    public List<Map<String, Object>> selectUserCarts(String uname) {
        return foodMapper.selectUserCarts(uname);
    }

    @Override
    public Map<String, Object> selectCartById(int cartId) {
        return foodMapper.selectCartById(cartId);
    }

    @Override
    public int addFood(Food food) {
        food.setSalesVolume(0);
        food.setStatus(0);
        foodMapper.addFood(food);
        return food.getFoodId();
    }

    @Override
    public int addToCollection(FoodCollection collection) {
        if(!isCollect(collection.getFoodId(), collection.getUname())){
            return foodMapper.addToCollection(collection);
        }else{
            return 0;
        }

    }

    @Override
    public int createOrder(Order order) {
        Food food = foodMapper.selectFoodById(order.getFoodId());
        double price = food.getPrice();
        int amount = order.getAmount();

        order.setTotalPrice(price*amount);
        order.setDate(NewDate.getDate());
        order.setOrderStatus("待接单");

        int status = foodMapper.addOrder(order);
        if(status!=0){
            foodMapper.updateFoodSales(order.getFoodId(), order.getAmount());
        }
        return status;
    }

    @Override
    public int addToCart(Cart cart) {
        return foodMapper.addToCart(cart);
    }

    @Override
    public int delFormCollectInDetail(int foodId, String uname) {
        return foodMapper.delFromCollectInDetail(foodId, uname);
    }

    @Override
    public int delFromCollection(int collectId) {
        return foodMapper.delFromCollection(collectId);
    }

    @Override
    public int delFromOrdertab(int orderId) {
        return foodMapper.delFromOrdertab(orderId);
    }

    @Override
    public int delFromCart(int[] carts) {
        return foodMapper.delFromCart(carts);
    }

    @Override
    public int updateFoodInfo(Food food) {
        return foodMapper.updateFoodInfo(food);
    }

    @Override
    public int updateFoodStatus(int[] foodsId) {
        return foodMapper.updateFoodStatus(foodsId);
    }

    @Override
    public int updateOrderInfo(int orderId, String specification, int amount, double totalPrice) {
        return foodMapper.updateOrderInfo(orderId, specification, amount, totalPrice);
    }

    @Override
    public int updateCartInfo(int cartId, String specification, int amount) {
        return foodMapper.updateCartInfo(cartId, specification, amount);
    }

    @Override
    public int receivingOrder(int orderId) {
        return foodMapper.receivingOrder(orderId);
    }

    @Override
    public int cancelReceivingOrder(int orderId) {
        return foodMapper.cancelReceivingOrder(orderId);
    }
}
