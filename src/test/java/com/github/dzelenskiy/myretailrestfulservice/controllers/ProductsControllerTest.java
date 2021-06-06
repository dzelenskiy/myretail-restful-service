package com.github.dzelenskiy.myretailrestfulservice.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.dzelenskiy.myretailrestfulservice.MyretailRestfulServiceApplication;
import com.github.dzelenskiy.myretailrestfulservice.dtos.CurrentPrice;
import com.github.dzelenskiy.myretailrestfulservice.dtos.Product;
import com.github.dzelenskiy.myretailrestfulservice.exceptions.ProductDetailsNotFoundException;
import com.github.dzelenskiy.myretailrestfulservice.facades.ProductPriceFacade;
import com.github.dzelenskiy.myretailrestfulservice.facades.ProductPriceFacadeImpl;
import com.github.dzelenskiy.myretailrestfulservice.services.CurrentPriceService;
import com.github.dzelenskiy.myretailrestfulservice.services.ProductDetailsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@TestPropertySource(locations="classpath:application.properties")
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = { MyretailRestfulServiceApplication.class }
)
@AutoConfigureMockMvc(addFilters = false)
public class ProductsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductPriceFacade productPriceFacade;

    @MockBean
    private ProductDetailsService productDetailsService;

    @MockBean
    private CurrentPriceService currentPriceService;

    @Test
    public void getProductById_success() throws Exception {

        Product product = new Product();
        product.setId(13860428);
        product.setName("The Big Lebowski (Blu-ray)");
        CurrentPrice currentPrice = new CurrentPrice();
        currentPrice.setProductId(13860428);
        currentPrice.setValue(new BigDecimal("7.99"));
        currentPrice.setCurrencyCode("USD");
        product.setCurrentPrice(currentPrice);

        when(productPriceFacade.getProductWithCurrentPriceById(13860428)).thenReturn(product);

        mockMvc.perform(get("/v1/products/13860428"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(13860428))
                .andExpect(jsonPath("$.name").value("The Big Lebowski (Blu-ray)"))
                .andExpect(jsonPath("$.current_price.value").value(7.99))
                .andExpect(jsonPath("$.current_price.currency_code").value("USD"));
    }

    @Test
    public void getProductById_notFound() throws Exception {
        when(productPriceFacade.getProductWithCurrentPriceById(0))
                .thenThrow(new ProductDetailsNotFoundException("Unable to retrieve product details."));

        mockMvc.perform(get("/v1/products/0"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateProductPrice_success() throws Exception {

        Product product = new Product();
        product.setId(13860428);
        product.setName("The Big Lebowski (Blu-ray)");
        CurrentPrice currentPrice = new CurrentPrice();
        currentPrice.setProductId(13860428);
        currentPrice.setValue(new BigDecimal("7.99"));
        currentPrice.setCurrencyCode("USD");
        product.setCurrentPrice(currentPrice);

        mockMvc.perform(
                    put("/v1/products/13860428")
                    .content(new ObjectMapper().writeValueAsString(product))
                )
                .andDo(print())
                .andExpect(status().isOk());

        verify(productPriceFacade, times(1)).updateProductCurrentPrice(product);
        verify(productDetailsService, times(1)).getProductDetailsById(product.getId());
        verify(currentPriceService, times(1)).updateCurrentPrice(currentPrice);

    }

    @Test
    public void updateProductPrice_notFound() throws Exception {


    }

    @Test
    public void updateProductPrice_missMatchedProductId() throws Exception {


    }

}
