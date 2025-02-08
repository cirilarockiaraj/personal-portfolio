package com.app.personal_portfolio.service;

import java.io.File;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.app.personal_portfolio.entity.AppData;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PortfolioService {
    @Value("${app.json.url}")
    private String appFileUrl;

    public AppData getJson() {
        ObjectMapper mapper = new ObjectMapper();
        AppData specificData = null;
        try {
            File file = new File(appFileUrl);
            JsonNode res = mapper.readTree(file);
            specificData = mapper.convertValue(res, new TypeReference<AppData>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return specificData;
    }
}
