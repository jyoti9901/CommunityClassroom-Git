package com.example.mdb.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("groceryItems")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@Builder
public class GroceryItem {

    @Id
    private String id;

    private String name;
    private int quantity;
    private String category;


}
