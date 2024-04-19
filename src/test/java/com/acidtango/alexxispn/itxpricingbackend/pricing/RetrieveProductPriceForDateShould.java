package com.acidtango.alexxispn.itxpricingbackend.pricing;

import com.acidtango.alexxispn.itxpricingbackend.pricing.products.infrastructure.controllers.dtos.GetProductPriceResponseDto;
import com.acidtango.alexxispn.itxpricingbackend.pricing.products.infrastructure.controllers.dtos.ProductPriceReadModelResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;


import java.time.LocalDateTime;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RetrieveProductPriceForDateShould {
    @LocalServerPort
    private int port;

    @Test
    public void retrieve_the_price_for_a_product_and_brand_for_a_given_date() {
        String productCode = "35455";
        String brandCode = "1";
        String requestDateTime = LocalDateTime.of(2020, 6, 14, 10, 0, 0).toString();

        ProductPriceReadModelResponseDto productPrice = given()
                .port(port)
                .when()
                .queryParam("dateTime", requestDateTime)
                .get("/brand/{brandCode}/product/{productCode}/price", brandCode, productCode)
                .getBody()
                .as(GetProductPriceResponseDto.class)
                .productPrice();

        assertEquals(productPrice.productCode(), productCode);
        assertEquals(productPrice.brandCode(), brandCode);
        assertEquals(productPrice.fromDateTime(), LocalDateTime.of(2020, 6, 14, 0, 0, 0));
        assertEquals(productPrice.toDateTime(), LocalDateTime.of(2020, 12, 31, 23, 59, 59));
        assertEquals(productPrice.amount(), 35.5);
        assertEquals(productPrice.currencyCode(), "EUR");
    }
}
