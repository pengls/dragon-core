package com.dragon.core.jwt;

import com.dragon.core.Stu;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @ClassName: JwtTest
 * @Description: TODO
 * @Author: pengl
 * @Date: 2020/4/1 21:45
 * @Version V1.0
 */
public class JwtTest {
    long bg;
    long end;

    @Before
    public void befor() {
        bg = System.currentTimeMillis();
    }

    @After
    public void after() {
        end = System.currentTimeMillis();
        System.out.println("----------all times:>>>" + (end - bg) + " ms");
    }

    @Test
    public void tt1(){
        /*Stu stu = new Stu("Z-01202323","hh",11);
        String token = JwtToken.builder()
                .data(stu)
                .algorithm(JwtAlgorithm.PBEWithSHA1AndDESede)
                .key("abc12123112313213456778843213431")
                .expire(3000)
                .build().create();
        System.out.println(token);*/
        String token = "BUifwjH0KfSIKiO_lHTjtU8MoOWkISjcgVYyzo6Aez5t6xkH12elut4_cDcg6WXZ0Iap72Ts7qkYFy9oElL-lNdRrM2nYF2F73XxSKnkj5aKcHTDfkn9fin5vhbdEh11WVNhh4Sj9gJzOoeokxTaij8AI_NollLoFO1oXuXFqllSvCkbIRwVqKnQYGxALNqSPODtlY2wn1bsQsGc8ii-s_4PS6_rssYf_CzKBpUXJ_qqNVgaFywpz0tbNRUvyCIBGybJ8a0WeMqIrIODp1g04rMUb1eAuFpimqkSv1PpJpu5DoQTNldkEQxaBey04z0KQBcD7mt2mD1uePTXsG9kHvFV9pLS9a9awEKJGvqq54zNZGHnHmNy7SwyOfCbt0TlrLuhMAn7b6bTOLeazML8EllMBPYOETaicDYFxvIOuvH95RNd7gIWff982HdWEFVl4b-zZh_6_GHw4LR0iZjQfWBeWCP12BHB-v1nCvaZLVFOLM9QND69oIgQHJETED305ZAyl-zuHzucLEccZ-7QnbjNABoNRpIWwe9sXRdJMXxDhIQWcfQZReepaJZEDR7CwqeOhGxES0q4ql4I955OIEeQ3Px3HM_2vnryxBzQ3kQ";
        System.out.println(JwtToken.builder().algorithm(JwtAlgorithm.PBEWithSHA1AndDESede).key("abc12123112313213456778843213431").build().verify(token));
        //Stu stut = (Stu)JwtToken.builder().key("abc12123112313213456778843213431").build().parse(token);
        //System.out.println(JSON.toJSONString(stut));
    }
}
