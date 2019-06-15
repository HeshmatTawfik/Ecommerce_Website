package com.idalia.cosmeticsShop.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.idalia.cosmeticsShop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JSONArrayConverter {
    @Autowired
    static ProductService productService;

    public static String mapper(List<Product> p) {
        ObjectMapper objectMapper = new ObjectMapper();
        //Set pretty printing of json
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        Product product = new Product();
        product.setId(1);
        product.setCategoryName("asasas");
        product.setName("asasasas");
        List<Product> personList = p;

        String arrayToJson = null;
        try {
            arrayToJson = objectMapper.writeValueAsString(personList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return arrayToJson;
    }

    public static List<Product> fromJSonToList(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        TypeReference<List<Product>> mapType = new TypeReference<List<Product>>() {};
        List<Product> jsonToProduct=new ArrayList<>();

            if(json!=null)
            jsonToProduct = objectMapper.readValue(json, mapType);



          //  e.printStackTrace();

        return jsonToProduct;
    }

    public static void main(String[] args) {
        ArrayList<Product> pp = new ArrayList<>();
        Product p1 = new Product();
        p1.setName("p1");
        p1.setId(1);
        Product p2 = new Product();
        p2.setName("p2");
        p1.setId(2);
        pp.add(p1);
        pp.add(p2);
        System.out.println(mapper(pp));

    }
}
