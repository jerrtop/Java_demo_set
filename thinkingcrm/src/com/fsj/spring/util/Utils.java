/**
 * www.9tang.info
 */
package com.fsj.spring.util;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/** <p>
 * 		工具类
 * </p>
 * @author Jerry
 * 微博/微信：九唐时光
 * Email:jerrtop@163.com
 * wwww.9tang.info
 */
public class Utils {

	/** 
     * @param h 
     * @return 
     * 实现对map按照key asc排序 
     */  
	 public static Map mapSortedByKey(Map map) {
        Map<Object, Object> mapVK = new TreeMap<Object, Object>(
            new Comparator<Object>() {
                public int compare(Object obj1, Object obj2) {
                    String v1 = (String)obj1;
                    String v2 = (String)obj2;
                    int s = v1.compareTo(v2);
                    return s;
                }
            }
        );
        Set col = map.keySet();
        Iterator iter = col.iterator();
        while (iter.hasNext()) {
            String key = (String) iter.next();
            mapVK.put(key, map.get(key));
        }
        return mapVK;
    }

}
