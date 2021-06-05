package com.github.dzelenskiy.myretailrestfulservice.services;

import com.github.dzelenskiy.myretailrestfulservice.MyretailRestfulServiceApplication;
import com.github.dzelenskiy.myretailrestfulservice.dtos.Item;
import com.github.dzelenskiy.myretailrestfulservice.dtos.Product;
import com.github.dzelenskiy.myretailrestfulservice.dtos.ProductDescription;
import com.github.dzelenskiy.myretailrestfulservice.dtos.ProductDetails;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@TestPropertySource(locations="classpath:application.properties")
@SpringBootTest(
        //webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = { MyretailRestfulServiceApplication.class }
)
//@AutoConfigureMockMvc(addFilters = false)
public class ProductDetailsServiceTest {

    @Autowired
    private ProductDetailsService productDetailsService;

    @Test
    public void getProductDetailsById_theBigLebowski() {

        ProductDetails productDetails = new ProductDetails();

        Item item = new Item();
        item.setTcin(13860428);

        ProductDescription productDescription = new ProductDescription();
        productDescription.setTitle("The Big Lebowski (Blu-ray)");

        item.setProductDescription(productDescription);

        productDetails.setItem(item);

        ProductDetails productDetailsFromService = productDetailsService.getProductDetailsById(13860428);

        assertThat(productDetailsFromService.getItem().getTcin())
                .isEqualTo(productDetails.getItem().getTcin());

        assertThat(productDetailsFromService.getItem().getProductDescription().getTitle())
                .isEqualTo(productDetails.getItem().getProductDescription().getTitle());

    }

    @Test
    public void getProductDetailsById_goodAndGatherPeanutButter() {

        ProductDetails productDetails = new ProductDetails();

        Item item = new Item();
        item.setTcin(54456119);

        ProductDescription productDescription = new ProductDescription();
        productDescription.setTitle("Creamy Peanut Butter 40oz - Good &#38; Gather&#8482;");

        item.setProductDescription(productDescription);

        productDetails.setItem(item);

        ProductDetails productDetailsFromService = productDetailsService.getProductDetailsById(54456119);

        assertThat(productDetailsFromService.getItem().getTcin())
                .isEqualTo(productDetails.getItem().getTcin());

        assertThat(productDetailsFromService.getItem().getProductDescription().getTitle())
                .isEqualTo(productDetails.getItem().getProductDescription().getTitle());

    }

    @Test
    public void getProductDetailsById_jifNaturalPeanutButter() {

        ProductDetails productDetails = new ProductDetails();

        Item item = new Item();
        item.setTcin(13264003);

        ProductDescription productDescription = new ProductDescription();
        productDescription.setTitle("Jif Natural Creamy Peanut Butter - 40oz");

        item.setProductDescription(productDescription);

        productDetails.setItem(item);

        ProductDetails productDetailsFromService = productDetailsService.getProductDetailsById(13264003);

        assertThat(productDetailsFromService.getItem().getTcin())
                .isEqualTo(productDetails.getItem().getTcin());

        assertThat(productDetailsFromService.getItem().getProductDescription().getTitle())
                .isEqualTo(productDetails.getItem().getProductDescription().getTitle());

    }

    @Test
    public void getProductDetailsById_kraftMacAndCheese() {

        ProductDetails productDetails = new ProductDetails();

        Item item = new Item();
        item.setTcin(12954218);

        ProductDescription productDescription = new ProductDescription();
        productDescription.setTitle("Kraft Macaroni &#38; Cheese Dinner Original - 7.25oz");

        item.setProductDescription(productDescription);

        productDetails.setItem(item);

        ProductDetails productDetailsFromService = productDetailsService.getProductDetailsById(12954218);

        assertThat(productDetailsFromService.getItem().getTcin())
                .isEqualTo(productDetails.getItem().getTcin());

        assertThat(productDetailsFromService.getItem().getProductDescription().getTitle())
                .isEqualTo(productDetails.getItem().getProductDescription().getTitle());

    }

}