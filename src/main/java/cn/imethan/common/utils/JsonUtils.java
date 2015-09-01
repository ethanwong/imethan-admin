package cn.imethan.common.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * JsonUtils.java
 *
 * @author Ethan Wong
 * @time 2014年10月31日下午8:18:39
 */
public class JsonUtils {
	
	private static ObjectMapper objectMapper = new ObjectMapper();
	
	static{
//		objectMapper.setSerializationInclusion(Inclusion.NON_NULL);  
//		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		objectMapper.setDateFormat(sdf);
	}
	
	public static String writeValueAsString(Object object) {
		String result = "";
		try {
			result = objectMapper.writeValueAsString(object);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}


