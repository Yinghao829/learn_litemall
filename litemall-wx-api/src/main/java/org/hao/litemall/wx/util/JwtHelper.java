package org.hao.litemall.wx.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.checkerframework.checker.units.qual.A;

import java.util.*;

/**
 * JWT工具类，用于生成和验证JWT（JSON Web Token）
 * 用于用户认证和授权，生成包含用户信息的Token，并在验证Token时提取用户信息
 */

public class JwtHelper {
    // JWT 签名使用的密钥，用于加密和解密Token
    static final String SECRET = "X-Litemall-Token";
    // JWT的签发者，签名是谁由谁生成
    static final String ISSUER = "LITEMALL";
    // 签名的主题
    static final String SUBJECT = "this is litemall token";
    // 签名的观众，表示Token是由谁使用的
    static final String AUDIENCE = "MINIAPP";

    /**
     * 根据用户ID生成JWT Token
     * Token包含用户ID、签发者、主题、受众、签发时间或过期时间等信息
     * 使用HS256算法进行签名
     * @param userId 用户ID，将作为自定义声明存储在Token中
     * @return 生成的JWT Token，如果失败则返回null
     */

    public String createToken(Integer userId) {
        try {
            // 使用HMAC256算法和密钥创建Algorithm对象
            Algorithm algorithm = Algorithm.HMAC256(SECRET);

            Map<String, Object> map = new HashMap<String, Object>();
            Date nowDate = new Date();
            // 过期时间：2h
            Date expireDate = getAfterDate(nowDate, 0, 0, 0, 2, 0, 0);
            // 设置JWT头部信息，指定算法和类型
            map.put("alg", "HS256");
            map.put("typ", "JWT");
            // 使用JWT.create构建Token
            String token = JWT.create()
                    .withHeader(map)
                    .withClaim("userId", userId)
                    .withIssuer(ISSUER)
                    .withSubject(SUBJECT)
                    .withAudience(AUDIENCE)
                    .withIssuedAt(nowDate)
                    .withExpiresAt(expireDate)
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 验证JWT Token并提取其中的用户ID
     * 验证内容包括签名、签发者、过期时间等
     *
     * @param token 待验证的JWT Token
     * @return Token中包含的用户ID，如果失败则返回0
     */

    public Integer verifyTokenAndGetUserId(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            // 创建JWTVerifier，设置验证规则（必须是指定的签发者）
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build();
            // 验证Token，如果验证失败则会抛出异常
            DecodedJWT jwt = verifier.verify(token);
            // 获取Token中的所有声明
            Map<String, Claim> claims = jwt.getClaims();
            // 提取用户ID声明
            Claim claim = claims.get("userId");
            return claim.asInt();
        } catch (JWTVerificationException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 计算指定日期后的日期
     * 按年月日时分秒为单位
     * @param date 基准日期
     * @param year 年
     * @param month 月
     * @param day 日
     * @param hour 时
     * @param minute 分
     * @param second 秒
     * @return 计算后的日期
     */

    public Date getAfterDate(Date date, int year, int month, int day, int hour, int minute, int second) {
        if (date == null) {
            date = new Date();
        }

        Calendar cal = new GregorianCalendar();

        cal.setTime(date);
        if (year != 0) {
            cal.add(Calendar.YEAR, year);
        }
        if (month != 0) {
            cal.add(Calendar.MONTH, month);
        }
        if (day != 0) {
            cal.add(Calendar.DATE, day);
        }
        if (hour != 0) {
            cal.add(Calendar.HOUR, hour);
        }
        if (minute != 0) {
            cal.add(Calendar.MINUTE, minute);
        }
        if (second != 0) {
            cal.add(Calendar.SECOND, second);
        }
        return cal.getTime();
    }
}
