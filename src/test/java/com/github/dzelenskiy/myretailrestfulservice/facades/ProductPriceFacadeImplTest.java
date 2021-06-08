package com.github.dzelenskiy.myretailrestfulservice.facades;

import com.github.dzelenskiy.myretailrestfulservice.MyretailRestfulServiceApplication;
import com.github.dzelenskiy.myretailrestfulservice.dtos.*;
import com.github.dzelenskiy.myretailrestfulservice.enums.CurrencyCode;
import com.github.dzelenskiy.myretailrestfulservice.exceptions.ProductDetailsNotFoundException;
import com.github.dzelenskiy.myretailrestfulservice.services.CurrentPriceService;
import com.github.dzelenskiy.myretailrestfulservice.services.ProductDetailsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = { MyretailRestfulServiceApplication.class })
@TestPropertySource(locations="classpath:application.properties")
public class ProductPriceFacadeImplTest {

    private static final String PRODUCT_DETAILS_NOT_FOUND_MESSAGE =
            "Unable to retrieve product details.";

    @Mock
    private ProductDetailsService productDetailsService;

    @Mock
    private CurrentPriceService currentPriceService;

    @InjectMocks
    private ProductPriceFacade productPriceFacade = new ProductPriceFacadeImpl();

    @Test
    public void getProductWithCurrentPriceById() throws ProductDetailsNotFoundException {

        ProductDetails productDetails = new ProductDetails();

        Item item = new Item();
        item.setTcin(13860428);

        ProductDescription productDescription = new ProductDescription();
        productDescription.setTitle("The Big Lebowski (Blu-ray)");

        item.setProductDescription(productDescription);

        productDetails.setItem(item);


        Product product = new Product();
        product.setId(13860428);
        product.setName("The Big Lebowski (Blu-ray)");

        CurrentPrice currentPrice = new CurrentPrice();
        currentPrice.setProductId(13860428);
        currentPrice.setValue(new BigDecimal("7.99"));
        currentPrice.setCurrencyCode(CurrencyCode.USD.toString());

        product.setCurrentPrice(currentPrice);


        when(productDetailsService.getProductDetailsById(13860428)).thenReturn(productDetails);
        when(currentPriceService.getCurrentPriceByProductId(13860428)).thenReturn(currentPrice);

        Product productFromFacade = productPriceFacade.getProductWithCurrentPriceById(13860428);

        assertThat(productFromFacade.getId()).isEqualTo(13860428);
        assertThat(productFromFacade.getName()).isEqualTo("The Big Lebowski (Blu-ray)");
        assertThat(productFromFacade.getCurrentPrice().getProductId()).isEqualTo(13860428);
        assertThat(productFromFacade.getCurrentPrice().getValue()).isEqualTo(new BigDecimal("7.99"));
        assertThat(productFromFacade.getCurrentPrice().getCurrencyCode()).isEqualTo(CurrencyCode.USD.toString());
    }


    @Test
    public void updateProductCurrentPrice_withExistingProduct() throws ProductDetailsNotFoundException {

        ProductDetails productDetails = new ProductDetails();

        Item item = new Item();
        item.setTcin(13860428);

        ProductDescription productDescription = new ProductDescription();
        productDescription.setTitle("The Big Lebowski (Blu-ray)");

        item.setProductDescription(productDescription);

        productDetails.setItem(item);


        Product product = new Product();
        product.setId(13860428);
        product.setName("The Big Lebowski (Blu-ray)");

        CurrentPrice currentPrice = new CurrentPrice();
        currentPrice.setProductId(13860428);
        currentPrice.setValue(new BigDecimal("7.99"));
        currentPrice.setCurrencyCode(CurrencyCode.USD.toString());

        product.setCurrentPrice(currentPrice);

        when(productDetailsService.getProductDetailsById(13860428)).thenReturn(productDetails);

        productPriceFacade.updateProductCurrentPrice(product);

        verify(currentPriceService, times(1)).updateCurrentPrice(currentPrice);

    }

    @Test
    public void updateProductCurrentPrice_withNonExistentProduct() throws ProductDetailsNotFoundException {

        Product product = new Product();
        product.setId(0);
        product.setName("NonExistent Product");

        CurrentPrice currentPrice = new CurrentPrice();
        currentPrice.setProductId(0);
        currentPrice.setValue(new BigDecimal("9.99"));
        currentPrice.setCurrencyCode(CurrencyCode.USD.toString());

        product.setCurrentPrice(currentPrice);

        when(productDetailsService.getProductDetailsById(0))
                .thenThrow(new ProductDetailsNotFoundException(PRODUCT_DETAILS_NOT_FOUND_MESSAGE));

        ProductDetailsNotFoundException productDetailsNotFoundException =
                assertThrows(ProductDetailsNotFoundException.class, () -> {
                    productPriceFacade.updateProductCurrentPrice(product);
                });

        assertThat(productDetailsNotFoundException.getMessage())
            .isEqualTo(PRODUCT_DETAILS_NOT_FOUND_MESSAGE);

        verify(currentPriceService, times(0)).updateCurrentPrice(currentPrice);

    }

}
