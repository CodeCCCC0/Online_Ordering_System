package com.takeout.service;

import com.takeout.pojo.Cart;
import com.takeout.pojo.Food;
import com.takeout.pojo.FoodCollection;
import com.takeout.pojo.Order;

import java.util.List;
import java.util.Map;

public interface FoodService {
//Select
    public Map<String, Object> searchFoods(String searchText, String classification, String order, int offset, int limit);
    public Map<String, Object> selectFoods(int offset, int limit);
    public Food selectFoodById(int foodId);
    public List<String> selectFoodClassifications();
    public int getFoodCount();
    public int resultsCount(String searchText, String classification);
    public boolean isCollect(int foodId, String uname);
    public List<Map<String, Object>> getCollections(String uname);
    public List<Map<String, Object>> selectUserOrders(String uname);
    public List<Map<String, Object>> searchUserOrder(String uname, String searchText);
    public Map<String, Object> selectOrders(int offset, int limit);
    public List<Map<String, Object>> selectUserCarts(String uname);
    public Map<String, Object> selectCartById(int cartId);
//Insert
    public int addFood(Food food);
    public int addToCollection(FoodCollection collection);
    public int createOrder(Order order);
    public int addToCart(Cart cart);
//Delete
    public int delFormCollectInDetail(int foodId, String uname);
    public int delFromCollection(int collectId);
    public int delFromOrdertab(int orderId);
    public int delFromCart(int[] carts);
//Update
    public int updateFoodInfo(Food food);
    public int updateFoodStatus(int[] foodsId);
    public int updateOrderInfo(int orderId, String specification, int amount, double totalPrice);
    public int updateCartInfo(int cartId, String specification, int amount);
    public int receivingOrder(int orderId);
    public int cancelReceivingOrder(int orderId);
}
