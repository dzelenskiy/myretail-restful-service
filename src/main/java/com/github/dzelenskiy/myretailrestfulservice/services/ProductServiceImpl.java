package com.github.dzelenskiy.myretailrestfulservice.services;

import com.github.dzelenskiy.myretailrestfulservice.dtos.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@PropertySource("classpath:target-redsky-api.properties")
@Service
public class ProductServiceImpl implements ProductService {

    @Value("${url}")
    private String apiURL;

    @Value("${excludes}")
    private String excludes;

    @Value("${key}")
    private String key;

    @Override
    public Product getProductById(int id) {

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(apiURL + Integer.toString(id))
                .queryParam("excludes", excludes)
                .queryParam("key", key);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();

        Product product = restTemplate.getForObject(builder.toUriString(), Product.class, entity);

        return product;
    }

}
