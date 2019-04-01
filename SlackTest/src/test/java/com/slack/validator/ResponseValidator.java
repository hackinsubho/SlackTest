package com.slack.validator;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.List;
import java.util.Map;

public class ResponseValidator {

    public void archiveResponse(Response archResponse){
        if(archResponse.jsonPath().getString("ok").equalsIgnoreCase("true"))
            System.out.println("Channel is Archived");
        else
            Assert.assertTrue(false,"Channel is not Archived");
    }
    public void listResponse(Response listResponse, String id){

        List<Map<String, String>> list = listResponse.jsonPath().getList("channels");
        ObjectMapper oMapper = new ObjectMapper();
        for(int i =0;i<list.size();i++){
        Map<String, String> map = oMapper.convertValue(list.get(i), Map.class);
        if(id.equalsIgnoreCase(map.get("id")))
            System.out.println("Renamed Channel is Present");
        return;
        }
        Assert.assertTrue(false,"Renamed Channel is not present");
    }
}
