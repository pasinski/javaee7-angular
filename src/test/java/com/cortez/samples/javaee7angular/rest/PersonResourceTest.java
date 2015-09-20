package com.cortez.samples.javaee7angular.rest;


import com.cortez.samples.javaee7angular.data.Person;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.specification.RequestSpecification;
import org.apache.http.client.methods.RequestBuilder;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.*;

import com.jayway.restassured.response.Response;

import static org.hamcrest.Matchers.equalTo;

/**
 * Created by michal on 9/20/15.
 */
public class PersonResourceTest {

    @Test
    public void shouldSaveAPerson() {

        //String myJSON = "{\"description\":\"Konoha\",\"imageUrl\":\"http://img1.wikia.nocookie.net/__cb20140616090940/naruto/images/thumb/b/b3/KakashiHatake.png/300px-KakashiHatake.png\",\"name\":\"Hatake Kakashi\"}";
        Person person = new Person();
        person.setName("Hatake Kakashi");
        person.setImageUrl("http://img1.wikia.nocookie.net/__cb20140616090940/naruto/images/thumb/b/b3/KakashiHatake.png/300px-KakashiHatake.png");
        person.setDescription("Konoha");
        //make get request to fetch capital of norway
        Person savedPerson =
                given().
                        baseUri("http://michal:57802/javaee7-angular-3.5").
                        basePath("/resources").
                        contentType(ContentType.JSON).body(person)
                .when().
                        post("/persons").then().statusCode(200)
                .extract().as(Person.class);

        Long id = savedPerson.getId();

        given().
                baseUri("http://michal:57802/javaee7-angular-3.5").
                basePath("/resources").pathParam("id", id)
        .when()
                .get("/persons/{id}")
        .then()
                .statusCode(200)
                .body("name", equalTo("Hatake Kakashi"));

        given().baseUri("http://michal:57802/javaee7-angular-3.5").
                basePath("/resources").pathParam("id", id)
        .when()
                .delete("/persons/{id}")
                .then().statusCode(204);

    }
}
