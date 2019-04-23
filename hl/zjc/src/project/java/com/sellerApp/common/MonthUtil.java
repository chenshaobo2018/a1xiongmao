/**
 * 
 */
package com.sellerApp.common;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author wgm
 *
 */
public class MonthUtil {

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * 获取当前月份的日期
	 * 
	 * @param year
	 * @param month
	 * @param maxday
	 * @return
	 */
	public static List<String> getMonthFullDay(int year, int month,int maxday){
	    int day=1;
        List<String> fullDayList = new ArrayList<String>();
        if(day <= 0 ) day = 1;
        Calendar cal = Calendar.getInstance();// 获得当前日期对象
        cal.clear();// 清除信息
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);// 1月从0开始
        cal.set(Calendar.DAY_OF_MONTH, day);// 设置为1号,当前日期既为本月第一天
        int count = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        for (int j = 0; j <= (count-1);) {
            if(sdf.format(cal.getTime()).equals(getLastDay(year, month)))
                break;
            cal.add(Calendar.DAY_OF_MONTH, j == 0 ? +0 : +1);
            j++;
            fullDayList.add(sdf.format(cal.getTime()));
        }
        List<String> List = new ArrayList<String>();
        for (int i = 0; i < maxday; i++) {
        	List.add(fullDayList.get(i).toString());
		}
        return List;
    }
    
    public static String getLastDay(int year,int month){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, 0);
        return sdf.format(cal.getTime());
    }
}
