package com.learn.notes.redis.lock;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
public class GoodController{

    public static final String REDIS_LOCK = "REDIS_LOCK";
/*

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private Redisson redisson;

    @GetMapping("/buy_goods")
    public String buy_Goods() throws Exception{
        */
/* 采用redis事务、lur脚本解决分布式锁问题
        String value = UUID.randomUUID().toString() + Thread.currentThread().getName();
        Boolean flag = stringRedisTemplate.opsForValue()//使用另一个带有设置超时操作的方法
                .setIfAbsent(REDIS_LOCK, value, 10L, TimeUnit.SECONDS);
        *//*

        RLock redissonLock = redisson.getLock(REDIS_LOCK);
        redissonLock.lock();
        try {
            */
/* 采用redis事务、lur脚本解决分布式锁问题
            if(!flag) {
                return "抢锁失败";
            }
            *//*

            String result = stringRedisTemplate.opsForValue().get("goods:001");// get key ====看看库存的数量够不够
            int goodsNumber = result == null ? 0 : Integer.parseInt(result);
            if(goodsNumber > 0){
                int realNumber = goodsNumber - 1;
                stringRedisTemplate.opsForValue().set("goods:001", String.valueOf(realNumber));
                System.out.println("成功买到商品，库存还剩下: "+ realNumber + " 件" + "\t服务提供端口" + serverPort);
                return "成功买到商品，库存还剩下:" + realNumber + " 件" + "\t服务提供端口" + serverPort;
            }else{
                System.out.println("商品已经售完/活动结束/调用超时,欢迎下次光临" + "\t服务提供端口" + serverPort);
            }

            return "商品已经售完/活动结束/调用超时,欢迎下次光临" + "\t服务提供端口" + serverPort;
        }finally {
            */
/* 采用redis事务解决分布式锁清除问题
            while(true){
                stringRedisTemplate.watch(REDIS_LOCK);
                if(stringRedisTemplate.opsForValue().get(REDIS_LOCK).equalsIgnoreCase(value)){
                    stringRedisTemplate.setEnableTransactionSupport(true);
                    stringRedisTemplate.multi();
                    stringRedisTemplate.delete(REDIS_LOCK);
                    List<Object> list = stringRedisTemplate.exec();
                    if (list == null) {
                        continue;
                    }
                }
                stringRedisTemplate.unwatch();
                break;
            }
            *//*


            */
/* 采用lur脚本解决分布式锁清除问题
            Jedis jedis = RedisUtils.getJedis();
            String script = "if redis.call('get', KEYS[1]) == ARGV[1] "
                    + "then "
                    + "    return redis.call('del', KEYS[1]) "
                    + "else "
                    + "    return 0 "
                    + "end";

            try {
                Object o = jedis.eval(script, Collections.singletonList(REDIS_LOCK),//
                        Collections.singletonList(value));
                if("1".equals(o.toString())) {
                    System.out.println("---del redis lock ok.");
                }else {
                    System.out.println("---del redis lock error.");
                }
            }finally {
                if(jedis != null)
                    jedis.close();
            }
            *//*

            if(redissonLock.isLocked() && redissonLock.isHeldByCurrentThread()) {
                redissonLock.unlock();
            }
        }
    }
*/

}

