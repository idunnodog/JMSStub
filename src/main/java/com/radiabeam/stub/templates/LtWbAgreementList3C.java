package com.radiabeam.stub.templates;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LtWbAgreementList3C {
    private String response;
    private final Pattern refIdPattern = Pattern.compile("(?<=refId>)(.*)(?=<\\/ns0:refId>)");
    private final Pattern jwtPattern = Pattern.compile("(?<=ns0:jwt>)(.*)(?=<\\/ns0:jwt>)");
    private Matcher refIdMatcher;
    private Matcher jwtMatcher;



    //upload response from file
    public LtWbAgreementList3C(){
        try {
            this.response = IOUtils.toString(Objects.requireNonNull(getClass()
                    .getClassLoader().getResourceAsStream("responses/LtWbAgreementList3C_rs.xml")), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //get response by jwt and docId
    public String getResponse(String refId,String jwt) {
        return response.replaceFirst("(?<=docId>)(.*)(?=<\\/ns0:docId>)", refId + "-1")
                .replaceFirst("(?<=ns0:jwt>)(.*)(?=<\\/ns0:jwt>)", jwt)
                .replaceFirst("(?<=refId>)(.*)(?=<\\/ns0:refId>)", refId);
    }

    //get correlation values from request
    public HashMap<String,String> parseRequest(String request){
        HashMap<String,String> values = new HashMap<>();
        refIdMatcher = refIdPattern.matcher(request);
        if (refIdMatcher.find()) {
            values.put("refId",refIdMatcher.group(0));
        }
        jwtMatcher = jwtPattern.matcher(request);
        if (jwtMatcher.find()) {
            values.put("jwt",jwtMatcher.group(0));
        }
        return values;
    }




}
