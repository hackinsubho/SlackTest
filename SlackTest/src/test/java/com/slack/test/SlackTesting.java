package com.slack.test;

import com.slack.resgenerator.ResponseGenerator;
import com.slack.validator.ResponseValidator;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Random;

public class SlackTesting extends ResponseGenerator {
    public String AuthToken = configLocator("AuthToken");
    public SlackTesting() throws IOException {
    }

    @Test(testName = "Testing", groups={"BVT"})
    public void testingSlack() throws Exception {
        RestAssured.baseURI  = "https://slack.com/api/";
        Random rand = new Random();
        String channelName= "Test"+ rand.nextInt(100);
        Response crresponse = createChannel(AuthToken,channelName);
        joinChannel(AuthToken, channelName);
        renameChannel(AuthToken, channelName, crresponse);
        Response lsResponse = listChannel(AuthToken);
        Response archResp = archiveChannel(AuthToken,crresponse);
        ResponseValidator validator = new ResponseValidator();
        validator.listResponse(lsResponse, crresponse.jsonPath().getString("channel.id"));
        validator.archiveResponse(archResp);
    }
}
