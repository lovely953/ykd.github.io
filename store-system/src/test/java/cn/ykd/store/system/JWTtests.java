package cn.ykd.store.system;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTtests {
    String secretKey = "asdghftrhr,ertrtrgfsfgf.";
     Date date = new Date(System.currentTimeMillis()+20*60*60*1000);



     //生成jwt
    @Test
    void generate(){
        Map<String, Object> claims = new HashMap<>();
        claims.put("id",9527);
        claims.put("username","ykd");
        String jwt = Jwts.builder()
                //header
                .setHeaderParam("alg","HS256")
                .setHeaderParam("typ","JWT")
                //playload
                .setClaims(claims)
                //Signature
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS256,secretKey)
                //完成
                .compact();
        System.out.println(jwt);
    }

    //eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6OTUyNywiZXhwIjoxNjgwNTc5NTc0LCJ1c2
    // VybmFtZSI6InlrZCJ9.l6qfwLVTI0J6kg5gXsjPnYla5_gypH9s0yZi6onIq5M
    //解析Jwt
    @Test
    void pares(){
        String jwt = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6OTUyNywiZXhwIjoxNjgwNjU3NDkyLCJ1c2VybmFtZSI6InlrZCJ9.tZrdI4XjBvmS8rTEzBWj80CtvLt3OJU_MQZwqLiZf_I";
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
        Object id = claims.get("id");
        Object username = claims.get("username");
        System.out.println("id:"+id);
        System.out.println("usernam:"+username);
    }
}
