package com.takeout.dao;

import com.takeout.pojo.Cart;
import com.takeout.pojo.FoodCollection;
import com.takeout.pojo.Food;
import com.takeout.pojo.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface FoodMapper {
//Select
    public List<Food> searchFoods(String searchText, String classification, String order, int offset, int limit);
    public List<Food> selectFoods(int offset, int limit);
    public List<String> selectFoodClassifications();
    public int getFoodCount();
    public int getResultsCount(String searchText, String classification);
    public Food selectFoodById(int id);
    public List<Food> selectFoodsByName(String foodName);
    public List<Food> selectFoodsByTag(String target);
    public FoodCollection selectFoodFromCollection(int foodId, String uname);
    public List<Map<String, Object>> selectUserCollections(String uname);
    public List<Map<String, Object>> selectUserOrders(String uname);
    public List<Map<String, Object>> searchUserOrder(String uname, String searchText);
    public List<Map<String, Object>> selectOrders(int offset, int limit);
    public int getOrdersCount();
    public int getUntreatedOrderCount();
    public List<Map<String, Object>> selectUserCarts(String uname);
    public Map<String, Object> selectCartById(int cartId);
//add
    public int addToCollection(FoodCollection collection);
    public int addFood(Food food);
    public int addOrder(Order order);
    public int addToCart(Cart cart);
//delete
    public int delFoodById(int id);
    public int delFromCollectInDetail(int foodId, String uname);
    public int delFromCollection(int collectId);
    public int delFromOrdertab(int orderId);
    public int delFromCart(int[] carts);
//update
    public int updateFoodInfo(Food food);
    public int updateFoodStatus(int[] foodsId);
    public int updateFoodName(int id, String newFoodName);
    public int updateFoodIngredients(int id, String ingredients);
    public int updateFoodSpecification(int id, String specification);
    public int updateFoodTarget(int id, String target);
    public int updateFoodPrice(int id, double price);
    public int updateFoodSales(int id, int amount);
    public int updateOrderInfo(int orderId, String specification, int amount, double totalPrice);
    public int updateCartInfo(int cartId, String specification, int amount);
    public int receivingOrder(int orderId);
    public int cancelReceivingOrder(int orderId);
//test
    public List<Map<String, Object>> testMapSel(String uname);
}
