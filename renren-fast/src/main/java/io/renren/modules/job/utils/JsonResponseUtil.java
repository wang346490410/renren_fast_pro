package io.renren.modules.job.utils;


import io.renren.common.utils.GsonUtil;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @Discription
 * @Date Create in 2017/12/9 17:05
 * @Author wdl
 * @Modified by:
 */
public class JsonResponseUtil {

    public static void writeJsonToResponse(Object obj, HttpServletResponse response, PrintWriter pw){
        response.setHeader("Access-Control-Allow-Origin","*");
        response.setContentType("application/json;charset=utf-8");
        String json= GsonUtil.toJson(obj);
        pw.write(json);
    }

}
