package com.example.mdb.controller;

import com.example.mdb.model.GroceryItem;
import com.example.mdb.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/grocery")
@RequiredArgsConstructor

public class GroceryController {

    private final ItemService itemService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody GroceryItem groceryItem){
        itemService.createItem(groceryItem);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GroceryItem> getAllItems(){
        return itemService.getAllItems();
    }

    @GetMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public List<GroceryItem> getItemsByName(@PathVariable("name") String name){
        return itemService.getItemsByName(name);
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<GroceryItem> searchProducts(@RequestParam("query") String query){
        return itemService.searchProducts(query);
    }

}
