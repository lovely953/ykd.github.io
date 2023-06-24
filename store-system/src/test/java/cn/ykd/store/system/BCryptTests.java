package cn.ykd.store.system;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptTests {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    //密码加密
    @Test
    void encode(){
        String rawPassword = "123456";
        System.out.println("密码原文:"+rawPassword);
        for (int i = 0; i <15 ; i++) {
            String encode = passwordEncoder.encode(rawPassword);
           // System.out.println("密文:"+encode);
        }
        long start = System.currentTimeMillis();
        System.out.println(start);
        /**
         * 密码原文:123456
         * 密文:$2a$10$cf3MkUCIJp7UmzwdSRWh.OIO.1wHwTZxCpMKmgbbqEvwRB8mlSBqK
         * 密文:$2a$10$0V9Jrc7s4Q5EfdwosLj9X.tjwIZcIlXco51mus/P/ExX9DJzlr2iW
         * 密文:$2a$10$QRJSV.jhsjVTEocjEk/FFeE.Aed1npUj9SfBPLy4AcF7Ed6F5bsse
         * 密文:$2a$10$QOGow1ll4dpEhpxJEH8i0ur48qsdXB2Y8xrntWnKTf26orAJChi4O
         * 密文:$2a$10$4sl50K1PoIMveJ/rpkyAhurFYiqQaeXuJHCukxNShDu6eo4PK0QkO
         * 密文:$2a$10$/JTNiL4Fhw4fGzVOOfxdZ.rUfRh15HFviC7V5kJyNXbAnrCkA2sM.
         * 密文:$2a$10$YkzCvXXFY9s/a3RtRlhwXOG4QaJFU55igCamQ.mMe6wR/sXWtGYQe
         * 密文:$2a$10$7PKtk61g9zJo1E089s2eFOz6Z/8bTrHWv2MDaGCJmPHwa/N0/HL0m
         * 密文:$2a$10$ngQswM91BYSHie0quKuh8ujV2tVVi2xhQLKYKHoWw5AEIIstpQSy.
         * 密文:$2a$10$j2Zy.CJvX6fhiLyrxxreVuBwLMPxHxLNRftlMqzhyB32rpfakVgAu
         * 密文:$2a$10$PaotNhHHm7v9hxuS1Uempe1g3H10EDrTUdg2LqP8iswAhavvvMUw.
         */
    }

    //密码匹配
    @Test
    void matches(){
        String rawPassword = "123456";
        String encode = "$2a$10$PaotNhHHm7v9hxuS1Uempe1g3H10EDrTUdg2LqP8iswAhavvvMUw.";
        boolean matches = passwordEncoder.matches(rawPassword, encode);
        System.out.println("匹配结果"+matches);
    }
}
