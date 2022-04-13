package tests;

import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class DemoWebShopTest {

    @Test
    void addToCart() {
        given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .body("product_attribute_16_5_4=14&product_attribute_16_6_5=17&" +
                        "product_attribute_16_3_6=18&product_attribute_16_4_7=20&addtocart_16.EnteredQuantity=1")
                .when()
                .post("http://demowebshop.tricentis.com/addproducttocart/details/16/1")
                .then()
                .log().all()
                .statusCode(200)
                .body("success", is(true))
                .body("message", is("The product has been added to your " +
                        "<a href=\"/cart\">shopping cart</a>"))
                .body("updatetopcartsectionhtml", is("(1)"));
    }

    @Test
    void subscribeNewsLetter() {
        given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .body("email=test@test.com")
                .when()
                .post("http://demowebshop.tricentis.com/subscribenewsletter")
                .then()
                .log().all()
                .statusCode(200)
                .body("Success", is(true))
                .body("Result", is("Thank you for signing up!" +
                        " A verification email has been sent. We appreciate your interest."));
    }
}
