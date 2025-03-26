package com.acidtango.alexxispn.itxpricingbackend.pricing;

import com.acidtango.alexxispn.itxpricingbackend.pricing.products.infrastructure.controllers.dtos.ProductPriceResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.time.Instant;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RetrieveProductPriceForDateTest {
    @LocalServerPort
    private int port;

    @Test
    public void retrieve_the_price_for_a_product_and_brand_for_a_given_date() {
        String productCode = "35455";
        String brandCode = "1";
        String requestDateTime = Instant.parse("2020-06-14T10:00:00Z").toString();

        ProductPriceResponseDto productPrice = given()
                .port(port)
                .when()
                .queryParam("dateTime", requestDateTime)
                .get("/brand/{brandCode}/product/{productCode}/price", brandCode, productCode)
                .getBody()
                .as(ProductPriceResponseDto.class);

        assertEquals(productCode, productPrice.productCode());
        assertEquals(brandCode, productPrice.brandCode());
        assertEquals(productPrice.fromDateTime(), Instant.parse("2020-06-14T00:00:00Z"));
        assertEquals(productPrice.toDateTime(), Instant.parse("2020-12-31T23:59:59Z"));
        assertEquals(35.5, productPrice.amount());
        assertEquals("EUR", productPrice.currencyCode());
    }

    @Test
    public void retrieve_the_price_for_a_product_and_brand_for_a_given_date_2() {
        String productCode = "35455";
        String brandCode = "1";
        String requestDateTime = Instant.parse("2020-06-14T16:00:00Z").toString();

        ProductPriceResponseDto productPrice = given()
                .port(port)
                .when()
                .queryParam("dateTime", requestDateTime)
                .get("/brand/{brandCode}/product/{productCode}/price", brandCode, productCode)
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(ProductPriceResponseDto.class);

        assertEquals(productCode, productPrice.productCode());
        assertEquals(brandCode, productPrice.brandCode());
        assertEquals(productPrice.fromDateTime(), Instant.parse("2020-06-14T15:00:00Z"));
        assertEquals(productPrice.toDateTime(), Instant.parse("2020-06-14T18:30:00Z"));
        assertEquals(25.45, productPrice.amount());
        assertEquals("EUR", productPrice.currencyCode());
    }

    @Test
    public void retrieve_the_price_for_a_product_and_brand_for_a_given_date_3() {
        String productCode = "35455";
        String brandCode = "1";
        String requestDateTime = Instant.parse("2020-06-14T21:00:00Z").toString();

        ProductPriceResponseDto productPrice = given()
                .port(port)
                .when()
                .queryParam("dateTime", requestDateTime)
                .get("/brand/{brandCode}/product/{productCode}/price", brandCode, productCode)
                .getBody()
                .as(ProductPriceResponseDto.class);

        assertEquals(productCode, productPrice.productCode());
        assertEquals(brandCode, productPrice.brandCode());
        assertEquals(productPrice.fromDateTime(), Instant.parse("2020-06-14T00:00:00Z"));
        assertEquals(productPrice.toDateTime(), Instant.parse("2020-12-31T23:59:59Z"));
        assertEquals(35.5, productPrice.amount());
        assertEquals("EUR", productPrice.currencyCode());
    }

    @Test
    public void retrieve_the_price_for_a_product_and_brand_for_a_given_date_4() {
        String productCode = "35455";
        String brandCode = "1";
        String requestDateTime = Instant.parse("2020-06-15T10:00:00Z").toString();

        ProductPriceResponseDto productPrice = given()
                .port(port)
                .when()
                .queryParam("dateTime", requestDateTime)
                .get("/brand/{brandCode}/product/{productCode}/price", brandCode, productCode)
                .getBody()
                .as(ProductPriceResponseDto.class);

        assertEquals(productCode, productPrice.productCode());
        assertEquals(brandCode, productPrice.brandCode());
        assertEquals(productPrice.fromDateTime(), Instant.parse("2020-06-15T00:00:00Z"));
        assertEquals(productPrice.toDateTime(), Instant.parse("2020-06-15T11:00:00Z"));
        assertEquals(30.5, productPrice.amount());
        assertEquals("EUR", productPrice.currencyCode());
    }

    @Test
    public void retrieve_the_price_for_a_product_and_brand_for_a_given_date_5
            () {
        String productCode = "35455";
        String brandCode = "1";
        String requestDateTime = Instant.parse("2020-06-16T21:00:00Z").toString();

        ProductPriceResponseDto productPrice = given()
                .port(port)
                .when()
                .queryParam("dateTime", requestDateTime)
                .get("/brand/{brandCode}/product/{productCode}/price", brandCode, productCode)
                .getBody()
                .as(ProductPriceResponseDto.class);

        assertEquals(productCode, productPrice.productCode());
        assertEquals(brandCode, productPrice.brandCode());
        assertEquals(productPrice.fromDateTime(), Instant.parse("2020-06-15T16:00:00Z"));
        assertEquals(productPrice.toDateTime(), Instant.parse("2020-12-31T23:59:59Z"));
        assertEquals(38.95, productPrice.amount());
        assertEquals("EUR", productPrice.currencyCode());
    }

    @Test
    public void return_404_when_product_price_not_found() {
        String productCode = "nonexistent";
        String brandCode = "1";
        String requestDateTime = Instant.parse("2020-06-14T10:00:00Z").toString();

        given()
                .port(port)
                .when()
                .queryParam("dateTime", requestDateTime)
                .get("/brand/{brandCode}/product/{productCode}/price", brandCode, productCode)
                .then()
                .statusCode(404);
    }

    @Test
    public void return_400_when_date_time_is_not_provided() {
        String productCode = "35455";
        String brandCode = "1";

        given()
                .port(port)
                .when()
                .get("/brand/{brandCode}/product/{productCode}/price", brandCode, productCode)
                .then()
                .statusCode(400);
    }
}
