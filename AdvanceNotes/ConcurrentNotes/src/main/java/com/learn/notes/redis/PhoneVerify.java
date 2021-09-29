package com.learn.notes.redis;

import redis.clients.jedis.Jedis;

import java.util.Random;

public class PhoneVerify {
    public static void main(String[] args) {
        //模拟验证码发送
        verifyCode("18855575677", "12323");
    }

    public static void getRedisCode(String phone, String code) {
        //连接redis
        Jedis jedis = new Jedis("198.168.44.168", 6379);
        //验证码key
        String codeKey = "VerifyCode" + phone + ":code";
        String redisCode = jedis.get(codeKey);
        //判断
        if (redisCode.equals(code)) {
            System.out.println("success");
        } else {
            System.out.println("lose");
        }
        jedis.close();

    }

    //每个手机每天只能发送三次，验证码放到redis中，设置过期时间为120
    public static void verifyCode(String phone, String code) {
        //连接redis
        Jedis jedis = new Jedis("198.168.44.168", 6379);

        //拼接key
        //手机发送次数key
        String countKey = "VerifyCode" + phone + ":count";
        //验证码key
        String codeKey = "VerifyCode" + phone + ":code";

        String count = jedis.get(countKey);
        if (count == null) {
            jedis.setex(countKey, 24 * 60 * 60, "1");
        } else if (Integer.parseInt(count) <= 2) {
            jedis.incr(countKey);
        } else if (Integer.parseInt(count) > 2) {
            System.out.println("超过三次");
            jedis.close();
        }
        String vscode = getcode();
        jedis.setex(codeKey, 120, vscode);
        jedis.close();

    }

    public static String getcode() {
        Random random = new Random();
        String code = "";
        for (int i = 0; i < 6; i++) {
            int rand = random.nextInt(10);
            code += rand;
        }
        return code;
    }
}
