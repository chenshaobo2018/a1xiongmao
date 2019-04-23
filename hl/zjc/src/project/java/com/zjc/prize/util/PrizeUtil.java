/**
 * 
 */
package com.zjc.prize.util;

import java.util.List;

import com.zjc.prize.dao.po.ZjcPrizePO;

/**
 * 抽奖随机率计算
 * 
 * @author zhangchao
 *
 */
public class PrizeUtil {

	public static ZjcPrizePO getPrizeIndex(List<ZjcPrizePO> prizes) {
        int random = -1;
        try{
            //计算总权重
            double sumWeight = 0;
            for(ZjcPrizePO p : prizes){
                sumWeight += p.getPrize_weight();
            }

            //产生随机数
            double randomNumber;
            randomNumber = Math.random();

            //根据随机数在所有奖品分布的区域并确定所抽奖品
            double d1 = 0;
            double d2 = 0;          
            for(int i=0;i<prizes.size();i++){
                d2 += Double.parseDouble(String.valueOf(prizes.get(i).getPrize_weight()))/sumWeight;
                if(i==0){
                    d1 = 0;
                }else{
                    d1 +=Double.parseDouble(String.valueOf(prizes.get(i-1).getPrize_weight()))/sumWeight;
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
