package nju.software.convoy.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * token工具
 * @Author: tommy_z
 * @Date: 2020/1/16
 */
public class JwtUtil {
    // 设置token过期时间为10天
    private static final long EXPIRE_TIME = 10 * 24 * 60 * 60 * 1000;
    // token私钥
    private static final String TOKEN_SECRET = "f5fsd88a2fv38v4ds8bnn33zg8gj8lyb1";

    /**
     * 签名方法
     * @param phone
     * @return
     */
    public static String sign(String phone) {
        try {
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);

            Map<String, Object> header = new HashMap<>(2);
            header.put("typ", "jwt");
            header.put("alg", "HS256");
            return JWT.create()
                    .withHeader(header)
                    .withClaim("phone", phone)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * 验证token
     * @param token
     * @return
     */
    public static boolean verify(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    /**
     * 通过token获取用户信息
     * @param token
     * @return
     */
    public static String getPhone(String token){
        try{
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("phone").asString();
        } catch (Exception e) {
            return null;
        }
    }
}
