package org.demo.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.demo.common.entity.Member;
import org.springframework.stereotype.Service;

/**
 * @author zacconding
 * @Date 2018-05-26
 * @GitHub : https://github.com/zacscoding
 */
@Service
public class MemberService {

    public List<Member> findAll() {
        return createDummy();
    }

    private List<Member> createDummy() {
        List<Member> ret = new ArrayList<>();

        Random random = new Random();

        for (int i = 1; i < 10; i++) {
            Member member = new Member();
            member.setId((long) i);
            member.setName("name" + i);
            member.setAge(random.nextInt(50));

            ret.add(member);
        }

        return ret;
    }
}
