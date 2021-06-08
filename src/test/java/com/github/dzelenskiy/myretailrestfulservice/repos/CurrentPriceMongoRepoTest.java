package com.github.dzelenskiy.myretailrestfulservice.repos;

import com.github.dzelenskiy.myretailrestfulservice.MyretailRestfulServiceApplication;
import com.github.dzelenskiy.myretailrestfulservice.dtos.CurrentPrice;
import com.github.dzelenskiy.myretailrestfulservice.enums.CurrencyCode;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = { MyretailRestfulServiceApplication.class })
@TestPropertySource(locations="classpath:application.properties")
public class CurrentPriceMongoRepoTest {

    @Autowired
    private CurrentPriceMongoRepo currentPriceMongoRepo;

    @BeforeEach
    public void setup() { currentPriceMongoRepo.deleteAll(); }

    @AfterEach
    public void cleanup() { currentPriceMongoRepo.deleteAll(); }

    @Test
    public void createRetrieveThenUpdateAndRetrieve() {

        List<CurrentPrice> currentPriceList = currentPriceMongoRepo.findAll();

        assertThat(currentPriceList).isEmpty();

        CurrentPrice currentPrice = new CurrentPrice();
        currentPrice.setProductId(13860428);
        currentPrice.setValue(new BigDecimal("7.99"));
        currentPrice.setCurrencyCode(CurrencyCode.USD.toString());

        currentPriceMongoRepo.save(currentPrice);

        currentPriceList = currentPriceMongoRepo.findAll();

        assertThat(currentPriceList).hasSize(1);
        assertThat(currentPriceList.get(0)).isEqualTo(currentPrice);
        assertThat(currentPriceList.get(0).getProductId()).isEqualTo(currentPrice.getProductId());
        assertThat(currentPriceList.get(0).getValue()).isEqualTo(currentPrice.getValue());
        assertThat(currentPriceList.get(0).getCurrencyCode()).isEqualTo(currentPrice.getCurrencyCode());

        currentPriceList = currentPriceMongoRepo.findByProductId(currentPrice.getProductId());

        assertThat(currentPriceList).hasSize(1);
        assertThat(currentPriceList.get(0)).isEqualTo(currentPrice);
        assertThat(currentPriceList.get(0).getProductId()).isEqualTo(currentPrice.getProductId());
        assertThat(currentPriceList.get(0).getValue()).isEqualTo(currentPrice.getValue());
        assertThat(currentPriceList.get(0).getCurrencyCode()).isEqualTo(currentPrice.getCurrencyCode());

        currentPrice.setValue(new BigDecimal("9.88"));
        currentPrice.setCurrencyCode(CurrencyCode.CAD.toString());

        currentPriceMongoRepo.save(currentPrice);

        currentPriceList = currentPriceMongoRepo.findAll();

        assertThat(currentPriceList).hasSize(1);
        assertThat(currentPriceList.get(0)).isEqualTo(currentPrice);
        assertThat(currentPriceList.get(0).getProductId()).isEqualTo(currentPrice.getProductId());
        assertThat(currentPriceList.get(0).getValue()).isEqualTo(currentPrice.getValue());
        assertThat(currentPriceList.get(0).getCurrencyCode()).isEqualTo(currentPrice.getCurrencyCode());

        currentPrice.setProductId(13860777);

        currentPriceMongoRepo.save(currentPrice);

        currentPriceList = currentPriceMongoRepo.findAll();

        assertThat(currentPriceList).hasSize(2);

        currentPriceList = currentPriceMongoRepo.findByProductId(currentPrice.getProductId());

        assertThat(currentPriceList.get(0)).isEqualTo(currentPrice);
        assertThat(currentPriceList.get(0).getProductId()).isEqualTo(currentPrice.getProductId());
        assertThat(currentPriceList.get(0).getValue()).isEqualTo(currentPrice.getValue());
        assertThat(currentPriceList.get(0).getCurrencyCode()).isEqualTo(currentPrice.getCurrencyCode());

    }

}
