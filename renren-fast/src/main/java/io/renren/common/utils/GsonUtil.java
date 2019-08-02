package io.renren.common.utils;

import cn.hutool.core.util.ReflectUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.bind.DateTypeAdapter;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * GsonUtil 工具类
 * Created by keyu.wang on 2017/12/13.
 */
public class GsonUtil {
    private static Logger logger = LoggerFactory.getLogger(GsonUtil.class);
    private static final String FACTORIES_NAME = "factories";

    /**
     * 将字json字符串转换为制定的java对象
     *
     * @param tClass
     * @param jsonParam
     * @author keyu.wang
     * @date 2017/12/13 15:20
     */
    public static <T> T fromJson(Class<T> tClass, String jsonParam) {
        T object = null;

        if (StringUtils.isEmpty(jsonParam)) {//转换参数为空
            logger.info("json字符串转换为实体发生异常，jsonParam为空");
            return null;
        }

        try {
            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(Date.class, new DateTypeAdapter());
            Gson gson = builder.create();
            object = gson.fromJson(jsonParam, tClass);
        } catch (JsonSyntaxException e) {
            logger.error("json字符串转换为实体发生未知异常，jsonParam：" + jsonParam);
            logger.error(e.getMessage());
        }
        return object;
    }

    /**
     * 将java对象转换为字符串
     *
     * @param objParam java对象
     * @author keyu.wang
     * @date 2017/12/13 15:18
     */
    public static String toJson(Object objParam) {

        String json = null;

        if (objParam == null) {
            logger.info("java实体转换为字符串发生异常，objParam 为 null");
            return null;
        }

        try {
            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(Date.class, new DateTypeAdapter());
            Gson gson = builder.create();
            json = gson.toJson(objParam);
        } catch (Exception e) {
            logger.error("java实体转换为字符串发生未知异常",e);
            logger.error(e.getMessage());
        }
        return json;
    }

    /**
     * 把json数据转换为map String 优化Map中的类型
     * @param json
     * @return
     */
    public static Map<String,String> fromJsonMapStr(String json){
        return fromJsonMap(json, new TypeToken<Map<String, String>>() {
        });
    }

    /**
     * 把json数据转换为map Object 优化Map中的类型
     * @param json
     * @return
     */
    public static Map<String,Object> fromJsonMapObj(String json){
        return fromJsonMap(json, new TypeToken<Map<String, Object>>() {
        });
    }

    public static <T> T fromJsonMap(String json, TypeToken<T> typeToken) {
//        Gson gson = new GsonBuilder()
//                /**
//                 * 重写map的反序列化
//                 */
//                .registerTypeAdapter(new TypeToken<Map<String, Object>>() {
//                }.getType(), new MapTypeAdapter()).create();
//
//        return gson.fromJson(json, typeToken.getType());
        return null;
    }


    public static void main(String[] args) {
//        DemoDto demoDto = new DemoDto();
//        demoDto.setName("张三");
//        demoDto.setSex(1);
//        demoDto.setSalary(1.11);
//        demoDto.setBirthday(new Date());
//        String s = GsonUtil.toJson(demoDto);
//        DemoDto demoDto1 = GsonUtil.fromJson(DemoDto.class, s);

        System.out.println("asdfa");
    }

    /**
     * 将字json字符串转换为制定的java对象
     *
     * @param tClass
     * @param jsonParam
     * @author keyu.wang
     * @date 2017/12/13 15:20
     */
    @SuppressWarnings("unchecked")
	public static <T> T fromJsonResolveInt(Class<T> tClass, String jsonParam) {
        T object = null;

        if (StringUtils.isEmpty(jsonParam)) {//转换参数为空
            logger.info("json字符串转换为实体发生异常，jsonParam为空");
            return null;
        }

        try {
            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(Date.class, new DateTypeAdapter());
            Gson gson = builder.create();
            List<TypeAdapterFactory> factories = (List<TypeAdapterFactory>) ReflectUtil.getFieldValue(gson, FACTORIES_NAME);
    		TypeAdapterFactory factory = factories.get(1);
//    		TypeAdapterFactory proxy = new ResolveIntegerTypeAdapterFactory(factory);
    		List<TypeAdapterFactory> newFactories = new ArrayList<TypeAdapterFactory>();
    		newFactories.addAll(factories);
//    		newFactories.set(1, proxy);
    		ReflectUtil.setFieldValue(gson, FACTORIES_NAME, newFactories);
            object = gson.fromJson(jsonParam, tClass);
        } catch (Exception e) {
            logger.error("json字符串转换为实体发生未知异常，jsonParam：" + jsonParam);
            logger.error(e.getMessage());
        }
        return object;
    }

}
