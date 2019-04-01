package com.slack.resgenerator;

import io.restassured.response.Response;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class ResponseGenerator {
    public Response createChannel(String AuthToken,String  channelName) throws IOException {
        Response createChanelResponse = given().log().all()
                .header("Content-Type","application/x-www-form-urlencoded")
                .header("Content-Type","application/json")
                .header("Authorization", AuthToken)
                .queryParams("token",AuthToken)
                .queryParam("name",channelName)
                .when()
                .post(endpointsLocator("CreateChannels"));
        return createChanelResponse;
    }
    public Response joinChannel(String AuthToken, String channelName) throws IOException {
        Response joinChanelResponse = given().log().all()
                .header("Content-Type","application/x-www-form-urlencoded")
                .header("Content-Type","application/json")
                .header("Authorization", AuthToken)
                .queryParams("token",AuthToken)
                .queryParam("name",channelName)
                .when()
                .post(endpointsLocator("JoinChannels"));
        return joinChanelResponse;
    }
    public Response renameChannel(String AuthToken, String channelName, Response createChanelResponse ) throws IOException {
        Response renameChanelResponse = given().log().all()
                .header("Content-Type","application/x-www-form-urlencoded")
                .header("Content-Type","application/json")
                .header("Authorization", AuthToken)
                .queryParams("token",AuthToken)
                .queryParam("channel",createChanelResponse.jsonPath().getString("channel.id"))
                .queryParam("name",channelName+"_renamed")
                .when()
                .post(endpointsLocator("RenameChannels"));
        return renameChanelResponse;
    }
    public Response listChannel(String AuthToken) throws IOException {
        Response listChanelResponse = given().log().all()
                .header("Content-Type","application/x-www-form-urlencoded")
                .header("Content-Type","application/json")
                .header("Authorization", AuthToken)
                .queryParams("token",AuthToken)
                .when()
                .get(endpointsLocator("ListChannels"));
        return listChanelResponse;
    }
    public Response archiveChannel(String AuthToken, Response createChanelResponse ) throws IOException {
        Response archiveChannelResponse = given().log().all()
                .header("Content-Type","application/x-www-form-urlencoded")
                .header("Content-Type","application/json")
                .header("Authorization", AuthToken)
                .queryParams("token",AuthToken)
                .queryParam("channel",createChanelResponse.jsonPath().getString("channel.id"))
                .when()
                .post(endpointsLocator("ArchiveChannel"));
        return archiveChannelResponse;
    }
    public static String configLocator(String key) throws IOException {
        Properties prop = new Properties();
        prop.load(new FileInputStream("config.properties"));
        return prop.getProperty(key);
    }
    public static String endpointsLocator(String key) throws IOException {
        Properties prop = new Properties();
        prop.load(new FileInputStream("EndpointsRepo/endpoints.properties"));
        return prop.getProperty(key);
    }

}
