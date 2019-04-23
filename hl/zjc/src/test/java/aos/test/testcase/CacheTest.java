package aos.test.testcase;

import redis.clients.jedis.Jedis;
import aos.framework.core.redis.JedisUtil;

/**
 * 缓存测试
 * 
 * @author xiongchun
 *
 */
public class CacheTest {

	public static void main(String[] args) {
		Jedis jedis = JedisUtil.getJedisClient();
		for (int i = 0; i < 12000; i++) {
			jedis.lpush("L2", "哈哈" + i);
		}
	}

}
