/**
 * 
 */
package com.wxactivity.share.util;

import java.util.List;

import com.wxactivity.share.dao.po.ZjcSharePrizePO;

/**
 * 抽奖随机率计算
 * 
 * @author zhangchao
 *
 */
public class SharePrizeUtil {

	public static ZjcSharePrizePO getPrizeIndex(List<ZjcSharePrizePO> prizes) {
        int random = -1;
        try{
            //计算总权重
            double sumWeight = 0;
            for(ZjcSharePrizePO p : prizes){
                sumWeight += p.getShare_prize_weight();
            }

            //产生随机数
            double randomNumber;
            randomNumber = Math.random();

            //根据随机数在所有奖品分布的区域并确定所抽奖品
            double d1 = 0;
            double d2 = 0;          
            for(int i=0;i<prizes.size();i++){
                d2 += Double.parseDouble(String.valueOf(prizes.get(i).getShare_prize_weight()))/sumWeight;
                if(i==0){
                    d1 = 0;
                }else{
                    d1 +=Double.parseDouble(String.valueOf(prizes.get(i-1).getShare_prize_weight()))/sumWeight;
                }
                if(randomNumber >= d1 && randomNumber <= d2){
                    random = i;
                    break;
                }
            }
        }catch(Exception e){
            System.out.println("生成抽奖随机数出错，出错原因：" +e.getMessage());
        }
        return prizes.get(random);
    }
	
}
