package org.demo.api.web;

import java.util.List;
import org.demo.api.service.MemberService;
import org.demo.common.entity.Member;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zacconding
 * @Date 2018-05-26
 * @GitHub : https://github.com/zacscoding
 */
@Controller
@RestController
@RequestMapping("/api/**")
public class ApiController {

    private static final Logger logger = LoggerFactory.getLogger(ApiController.class);

    @Autowired
    private MemberService memberService;

    @GetMapping(value = "/members")
    public ResponseEntity<List<Member>> getAllMembers() {
        logger.info("## Request members");
        try {
            return ResponseEntity.ok().body(memberService.findAll());
        } catch(Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}