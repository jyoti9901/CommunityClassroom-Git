package com.example.mdb.repository;

import com.example.mdb.model.GroceryItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ItemRepository extends MongoRepository<GroceryItem, String> {

    List<GroceryItem> findItemByName(String name);

    @Query("SELECT p FROM groceryItems p WHERE " +
            "p.name LIKE CONCAT('%',:query, '%')" +
            "Or p.category LIKE CONCAT('%', :query, '%')")

    List<GroceryItem> searchProducts(String query);
}

