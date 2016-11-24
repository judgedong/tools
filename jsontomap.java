import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;



public class jsontomap {
	public static void main(String[] args) {
		String s = "{\"data\":{\"北京东方\":{\"leaf\":\"false\",\"会计档案\":{\"leaf\":\"false\",\"其它\":{\"Strid\":\"677\",\"leaf\":\"true\",\"model_id\":\"197\"},\"凭证\":{\"Strid\":\"652\",\"leaf\":\"true\",\"model_id\":\"194\"},\"报表\":{\"Strid\":\"658\",\"leaf\":\"true\",\"model_id\":\"196\"},\"账簿\":{\"Strid\":\"655\",\"leaf\":\"true\",\"model_id\":\"195\"}},\"合同档案\":{\"2009年度合同\":{\"Strid\":\"604\",\"leaf\":\"true\",\"model_id\":\"193\"},\"leaf\":\"false\"},\"四层档案\":{\"Strid\":\"682\",\"leaf\":\"true\",\"model_id\":\"206\"},\"声像档案\":{\"2008年度\":{\"Strid\":\"631\",\"leaf\":\"true\",\"model_id\":\"199\"},\"leaf\":\"false\"},\"实物档案\":{\"2008年度\":{\"Strid\":\"636\",\"leaf\":\"true\",\"model_id\":\"198\"},\"leaf\":\"false\"},\"文书档案\":{\"leaf\":\"false\",\"一事一件库\":{\"2000年度\":{\"Strid\":\"598\",\"leaf\":\"true\",\"model_id\":\"190\"},\"2009年度\":{\"Strid\":\"598\",\"leaf\":\"true\",\"model_id\":\"190\"},\"2010年度\":{\"Strid\":\"598\",\"leaf\":\"true\",\"model_id\":\"190\"},\"leaf\":\"false\"},\"传统立卷库\":{\"2005年度\":{\"Strid\":\"596\",\"leaf\":\"true\",\"model_id\":\"187\"},\"2009年度\":{\"Strid\":\"596\",\"leaf\":\"true\",\"model_id\":\"187\"},\"2016年度\":{\"Strid\":\"596\",\"leaf\":\"true\",\"model_id\":\"187\"},\"leaf\":\"false\"}},\"档案档案\":{\"Strid\":\"596\",\"leaf\":\"true\",\"model_id\":\"187\"},\"照片档案\":{\"2008年度\":{\"Strid\":\"626\",\"leaf\":\"true\",\"model_id\":\"200\"},\"leaf\":\"false\"},\"科技档案\":{\"leaf\":\"false\",\"产品\":{\"Strid\":\"661\",\"leaf\":\"true\",\"model_id\":\"201\"},\"基建\":{\"Strid\":\"668\",\"leaf\":\"true\",\"model_id\":\"203\"},\"科研\":{\"Strid\":\"664\",\"leaf\":\"true\",\"model_id\":\"202\"},\"设备\":{\"Strid\":\"672\",\"leaf\":\"true\",\"model_id\":\"204\"}}}}}";
		System.out.println(s);
		Map<String, Object> map = parseJSON2Map(s);
		System.out.println(map);
		JSONObject json = JSONObject.fromObject(map);
		System.out.println(json.toString());
	}

    public static Map<String, Object> parseJSON2Map(String jsonStr){  
        Map<String, Object> map = new HashMap<String, Object>();  
        //最外层解析  
        JSONObject json = JSONObject.fromObject(jsonStr);  
        for(Object k : json.keySet()){  
            Object v = json.get(k);   
            //如果内层还是数组的话，继续解析  
            if(v instanceof JSONArray){  
                List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();  
                Iterator<JSONObject> it = ((JSONArray)v).iterator();  
                while(it.hasNext()){  
                    JSONObject json2 = it.next();  
                    list.add(parseJSON2Map(json2.toString()));  
                }  
                map.put(k.toString(), list);
            //如果内层还是map的话，继续解析  
            }else if (v instanceof JSONObject) {
				Map<String, Object> m = parseJSON2Map(v.toString());
				map.put(k.toString(), m);
			}else {  
                map.put(k.toString(), v);  
            }  
        }  
        return map;  
    }  
}
