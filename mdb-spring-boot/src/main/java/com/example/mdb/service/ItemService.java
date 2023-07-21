package com.example.mdb.service;

import com.example.mdb.model.GroceryItem;
import com.example.mdb.repository.ItemRepository;
import com.netflix.discovery.DiscoveryClient;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ItemService {
    private final WebClient.Builder webClientBuilder;
    private final ItemRepository itemRepository;

    public void createItem(GroceryItem groceryItem) {
       ArrayList result =  webClientBuilder.build().get()
               .uri("http://product-service/api/product")
                .retrieve()
                .bodyToMono(ArrayList.class)
                .block();
       if(result.isEmpty()==false) {
           itemRepository.save(groceryItem);
           log.info("Item {} is saved ", groceryItem.getId());
       } else{
           throw new IllegalArgumentException("Product is not in th stock");
       }
    }

    public List<GroceryItem> getAllItems() {
        return itemRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<GroceryItem> getItemsByName(String name) {
        return itemRepository.findItemByName(name);
    }

    public List<GroceryItem> searchProducts(String query) {
        return itemRepository.searchProducts(query);
    }
}
