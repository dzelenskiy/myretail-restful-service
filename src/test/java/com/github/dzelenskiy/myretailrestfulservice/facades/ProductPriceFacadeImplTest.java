package com.github.dzelenskiy.myretailrestfulservice.facades;

import com.github.dzelenskiy.myretailrestfulservice.MyretailRestfulServiceApplication;
import com.github.dzelenskiy.myretailrestfulservice.dtos.*;
import com.github.dzelenskiy.myretailrestfulservice.repos.dynamodb.CurrentPriceRepo;
import com.github.dzelenskiy.myretailrestfulservice.services.CurrentPriceService;
import com.github.dzelenskiy.myretailrestfulservice.services.CurrentPriceServiceImpl;
import com.github.dzelenskiy.myretailrestfulservice.services.ProductDetailsService;
import com.github.dzelenskiy.myretailrestfulservice.services.ProductDetailsServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@TestPropertySource(locations="classpath:application.properties")
@SpringBootTest(
        //webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = { MyretailRestfulServiceApplication.class }
)
//@AutoConfigureMockMvc(addFilters = false)
public class ProductPriceFacadeImplTest {

    @Mock
    private ProductDetailsService productDetailsService;

    @Mock
    private CurrentPriceService currentPriceService;

    @InjectMocks
    private ProductPriceFacade productPriceFacade = new ProductPriceFacadeImpl();

    @Test
    public void getProductWithCurrentPriceById() {

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
        currentPrice.setCurrencyCode("USD");

        product.setCurrentPrice(currentPrice);


        when(productDetailsService.getProductDetailsById(13860428)).thenReturn(productDetails);
        when(currentPriceService.getCurrentPriceByProductId(13860428)).thenReturn(currentPrice);

        Product productFromFacade = productPriceFacade.getProductWithCurrentPriceById(13860428);

        assertThat(productFromFacade.getId()).isEqualTo(13860428);
        assertThat(productFromFacade.getName()).isEqualTo("The Big Lebowski (Blu-ray)");
        assertThat(productFromFacade.getCurrentPrice().getProductId()).isEqualTo(13860428);
        assertThat(productFromFacade.getCurrentPrice().getValue()).isEqualTo(new BigDecimal("7.99"));
        assertThat(productFromFacade.getCurrentPrice().getCurrencyCode()).isEqualTo("USD");
    }

}
