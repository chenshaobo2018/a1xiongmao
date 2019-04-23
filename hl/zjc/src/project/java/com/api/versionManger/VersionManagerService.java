package com.api.versionManger;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;
import aos.framework.core.dao.SqlDao;
import aos.framework.core.redis.JedisUtil;
import aos.framework.core.utils.AOSJson;
import aos.framework.core.utils.AOSUtils;

import com.api.common.util.ConstantUtil;
import com.zjc.advertising.dao.ZjcAppversonDao;
import com.zjc.advertising.dao.ZjcSystemInterfaceDao;
import com.zjc.goods.dao.po.ZjcGoodsPO;

/**
 * @author wgm
 * 
 *app接口管理server
 */
@Service(value="versionManagerService")
public class VersionManagerService {
	
	@Autowired
	private ZjcSystemInterfaceDao zjcSystemInterfaceDao;
	
	@Autowired
	private ZjcAppversonDao zjcAppversonDao;
	
	@Autowired
	private SqlDao sqlDao;
	
	 //redis ids集合的 的key名称  
    public static   String KEYIDS= "GOODSIDS";  
      
    // redis 对象的 的key名称  "SYS:BILL:"+"id"  获取单个对象  
    public static   String OBJECTKEYID= "GOODS:";  
      
    // list 的主键名称  
    public static   String MAJORKEY="goods_id";  
	
	/**
	 * app接口-获取接口列表数据
	 * 
	 * @param request
	 * @return json
	 */
	@SuppressWarnings({ "static-access"})
	public String getSystemInterfaceList(HttpServletRequest request) {
		Jedis jedis = JedisUtil.getJedisClient();
		List<ZjcGoodsPO> goodslist = sqlDao.list("com.zjc.goods.dao.ZjcGoodsDao.getNewGoodsPage1", null);
	/*	int totalPage = 0;
		if(goodslist.size()%ConstantUtil.pageSize == 0){
			totalPage = goodslist.size()/ConstantUtil.pageSize;
		} else {
			totalPage = goodslist.size()/ConstantUtil.pageSize + 1;
		}*/
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();  
		for(int j=0;j<goodslist.size();j++){
			Map<String,Object> map1 = new HashMap<String,Object>();
			map1.put(MAJORKEY, goodslist.get(j).getGoods_id());
			map1.put("goods", AOSJson.toJson(goodslist.get(j)));
			list.add(map1);
		}
		String ss= "";
		try{
			this.setCacheObjectInfo(list, KEYIDS, OBJECTKEYID, MAJORKEY, jedis);
			System.out.println("数据写入成功");
			List<Map<String, String>> findPagedQuery = findPagedQuery(KEYIDS, OBJECTKEYID, 1, ConstantUtil.pageSize, jedis);
			if (findPagedQuery!=null && findPagedQuery.size()>0) {  
	             System.out.println("查询到当前的数据记录：");
	             System.out.println("kkkjhjhj:"+findPagedQuery);
	             ss = AOSJson.toJson(findPagedQuery);
	             for (Map<String, String> map3 : findPagedQuery) {  
	                 System.out.println(map3.get(MAJORKEY));  
	             }  
	         }else{  
	             System.out.println("当前页数据为空");  
	         }  
		} catch(Exception e){
			e.printStackTrace();
		} finally{  
            jedis.close();  
        } 
		
		//return JedisUtil.getJedisClient().hmget("goodD","202").toString();
		return  ss;
	}
	
	  /** 
     * 查询缓存中的数据 
     * @param jedis 
     * @param keyIds        id集合的key值 
     * @param objectKeyId   对象key值的SYS:BILL:前缀  如："SYS:BILL:"+订单号 
     * @param num 当前页数 
     * @param pageCount  每页条数 
     * @return 
     */  
    public static  List<Map<String, String>> findPagedQuery(String keyIds, String objectKeyId, int num, int pageCount,Jedis jedis) {  
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();  
         //获取管道对象  
        Pipeline pipeline = jedis.pipelined();  
        // 开始记录数  
        int startCount = (num - 1) * pageCount;  
        List<String> lrange = jedis.lrange(keyIds, startCount, startCount + pageCount - 1);  
        if (startCount != 0 && lrange.size() != pageCount) {  
            // 当缓存中数据不够时直接返回空  
            return null;  
        }  
        // 使用pipeline 批量获取数据  
        Map<String, Response<Map<String, String>>> responses = new HashMap<String, Response<Map<String, String>>>();  
        for (String key : lrange) {  
            String objectKey = objectKeyId + key;  
            responses.put(key, pipeline.hgetAll(objectKey));  
        }  
        // 同步数据  
        pipeline.sync();  
        for (String key : responses.keySet()) {  
            Map<String, String> map = new HashMap<String, String>();  
            // 获取订单对象  
            map = responses.get(key).get();  
            list.add(map);  
        }  
        return list;  
    }
	
    /** 
     * 存入对象数据 
     * @param list 
     * @param keyIds       redis ids集合的 的key名称 
     * @param objectKeyId  redis 对象的 的key名称 
     * @param majorKey     list 的主键名称 
     */  
	public static void setCacheObjectInfo(List<Map<String, Object>> list,String keyIds,String objectKeyId,String majorKey, Jedis jedis) {  
        //获取管道对象  
       Pipeline pipeline = jedis.pipelined();  
       jedis.del(keyIds);  
       for (Map<String, Object> object : list) {  
           //存储 对象 信息  
           pipeline.rpush(keyIds, object.get(majorKey).toString());  
           //获取封装好的对象数据  
           Map<String, String> billMap = getEncapsulationObject(object);  
           String key=objectKeyId+object.get(majorKey);  
           pipeline.hmset(key, billMap);  
           pipeline.expire(key, 3*60*60*24);  
       }  
       pipeline.expire(keyIds, 3*60*60*24);  
       pipeline.sync();  
   } 
	
	  /** 
     * 将数据转换成字符串 
     * @param object 
     * @return 
     */  
    public static Map<String, String> getEncapsulationObject(Map<String, Object> object){  
        Map<String, String> billMap = new HashMap<String, String>();  
        for(String key:object.keySet()){  
            billMap.put(key,convertString(object.get(key)));  
        }  
        return billMap;  
    }
    
    /** 
     * 转换对象 
     * 当对象是一个时间类型 转成时间戳 
     * 其他类型都为字符串 
     * @param object 
     * @return 
     */  
    public static String convertString(Object object){  
        Object obj=object;  
        if(obj == null){  
            return "";  
        } else if(obj instanceof Date){  
            obj = ((Date)obj).getTime();  
            return obj.toString();  
        }  
        if(AOSUtils.isEmpty(obj.toString())){  
            return "";  
        }     
        return obj.toString();  
    }  
}
