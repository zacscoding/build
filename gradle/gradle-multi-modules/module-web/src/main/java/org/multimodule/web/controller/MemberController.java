package org.multimodule.web.controller;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zacconding
 * @Date 2018-05-26
 * @GitHub : https://github.com/zacscoding
 */
@RestController
@RequestMapping("/members/")
public class MemberController {

    private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

    @GetMapping("/index")
    public String index() {
        CloseableHttpClient client = null;
        try {
            client = HttpClients.createDefault();
            CloseableHttpResponse res = client.execute(new HttpGet("http://localhost:8181/api/members"));
            return EntityUtils.toString(res.getEntity());
        } catch (Exception e) {
            logger.error("Failed to request api", e);
        } finally {
            if (client != null) {
                try {
                    client.close();
                } catch (Exception e) {
                }
            }
        }

        return "index";
    }
}
