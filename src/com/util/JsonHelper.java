package com.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class JsonHelper {
	
	//�Զ����ѭ�������ж�
	public static String toJsonString(Object obj) {
		return JSON.toJSONString(obj,SerializerFeature.DisableCircularReferenceDetect);
	}
}
