package io.renren.common.utils;


import cn.hutool.core.date.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 工具类-字符串处理
 */
public final class StringUtil {
    private static Logger logger = LoggerFactory.getLogger(StringUtil.class);


    // 每位加权因子
    private static int power[] = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};

    // 手机号前缀
    private static final String PHONE_SEGMENT = "134,135,136,137,138,139,147,150,151,152,157,158,159,178,182,183,184,187,188,130,131,132,145,155,156,171,175,176,185,186,133,149,153,173,177,180,181,189,170";

    /**
     * 构造函数
     */
    private StringUtil() {

    }

    /**
     * 判断输入的手机号码是否有效
     *
     * @param str 手机号码
     * @return 检验结果（true：有效 false：无效）
     */
    public static boolean isValidPhone(String str) {
        String phone = isNull(str);
        if (phone.length() != 11) {
            return false;
        }
        Pattern regex = Pattern.compile("^\\d{11}$");
        Matcher matcher = regex.matcher(phone);
        boolean isMatched = matcher.matches();
        if (!isMatched) {
            return false;
        }
//        String segment = phone.substring(0, 3);
//        String segments = PHONE_SEGMENT;
//        if (segments.contains(segment)) {
//            return true;
//        }
        Pattern regex1 = Pattern.compile("^1[03456789]\\d{9}$");
//        Pattern regex1 = Pattern.compile("^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\d{8}$");
        Matcher matcher1 = regex1.matcher(phone);
        boolean isMatched1 = matcher1.matches();
        if (isMatched1) {
            return true;
        }
        return false;
    }

    /**
     * 判断输入的手机号码是否有效
     *
     * @param str 手机号码
     * @return 检验结果（true：有效 false：无效）
     */
    public static boolean isValidPhone(String str,List<String> starts) {
        String phone = isNull(str);
        if (phone.length() != 11) {
            return false;
        }
        Pattern regex = Pattern.compile("^\\d{11}$");
        Matcher matcher = regex.matcher(phone);
        boolean isMatched = matcher.matches();
        if (!isMatched) {
            return false;
        }

        for (String start:starts) {
        	if(str.startsWith(start)){
        		return true;
        	}
		}

        return false;
    }

    /**
     * 判断邮箱是否有效
     *
     * @param str 邮箱
     * @return 检验结果（true：有效 false：无效）
     */
    public static boolean isMail(String str) {
        String mail = isNull(str);
        Pattern regex = Pattern.compile("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");
        Matcher matcher = regex.matcher(mail);
        boolean isMatched = matcher.matches();
        return isMatched;
    }

    /**
     * 判断输入的身份证号码是否有效
     *
     * @param str 身份证号码
     * @return 检验结果（true：有效 false：无效）
     */
    public static boolean isCard(String str) {
        String cardId = isNull(str);
        // 身份证正则表达式(15位)
        Pattern isIDCard1 = Pattern.compile("^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$");
        // 身份证正则表达式(18位)
        Pattern isIDCard2 = Pattern.compile("^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X|x)$");
        Matcher matcher1 = isIDCard1.matcher(cardId);
        Matcher matcher2 = isIDCard2.matcher(cardId);
        boolean isMatched = matcher1.matches() || matcher2.matches();
        return isMatched;
    }

    public static boolean isNotBlank(Object o) {
        return !isNull(o).equals("");
    }

    /**
     * 根据身份证获取年龄
     *
     * @param idNo
     * @return
     * @throws ParseException
     */
//    public static Integer getAge(String idNo) throws ParseException {
//        String dates;
//        if (idNo != null && idNo.length() == 15) {
//            idNo = convertIdcarBy15bit(idNo);
//        }
//        if (idNo != null && idNo.length() == 18) {
//            dates = idNo.substring(6, 14);
//            int year = DateUtil.daysBetween(DateUtil.valueOf(dates, DateUtil.DATE_FORMAT_SHORT), DateUtil.getNow()) / 365;
//            return year;
//        }
//        return 0;
//    }

    /**
     * 根据身份证获取出生日期
     *
     * @param idNo
     * @return
     * @throws ParseException
     */
    public static Date getBirthday(String idNo) throws ParseException {
        String dates;
        Date date = null;
        if (idNo != null && idNo.length() == 15) {
            idNo = convertIdcarBy15bit(idNo);
        }
        if (idNo != null && idNo.length() == 18) {
            dates = idNo.substring(6, 14);
            date = DateUtil.parseDate(dates);
            return date;
        }
        return date;
    }

    /**
     * 根据身份证Id获取性别
     *
     * @param cardId
     * @return 性别: '男' / '女'
     */
    public static String getSex(String cardId) {
        int sexNum;
        // 15位的最后一位代表性别，18位的第17位代表性别，奇数为男，偶数为女
        if (cardId.length() == 15) {
            sexNum = cardId.charAt(cardId.length() - 1);
        } else {
            sexNum = cardId.charAt(cardId.length() - 2);
        }

        if (sexNum % 2 == 1) {
            return "男";
        } else {
            return "女";
        }
    }

    public static String toString(String separate, int... objs) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < objs.length; i++) {
            if (i > 0)
                sb.append(separate);
            sb.append(objs[i]);
        }
        return sb.toString();
    }

    public static String replaceNotChsEngNum(String srcStr) {
        if(isEmpty(srcStr))
            return "";
        String regChinese = "[^\\u4e00-\\u9fa5a-zA-Z0-9]";//非中文和非英文字符正则匹配
        String filterName = srcStr.replaceAll(regChinese, "");//过滤掉非中文和非英文字符
        return filterName;
    }

    public static String toStringArray(Object... list) {
        StringBuilder sb = new StringBuilder();
        int index = 0;

        for (Object o : list) {
            if (index > 0) sb.append(",");
            sb.append(o.toString());
            index++;
        }
        return sb.toString();
    }

    @SuppressWarnings("rawtypes")
    public static String toString(Collection list) {
        return toString(list, ",");
    }

    @SuppressWarnings("rawtypes")
    public static String toString(Collection list, String delim) {
        StringBuilder sb = new StringBuilder();
        int index = 0;

        for (Object o : list) {
            if (index > 0) sb.append(delim);
            sb.append(o.toString());
            index++;
        }
        return sb.toString();
    }

    public static String getRelativePath(File file, File parentFile) {
        return file.getAbsolutePath().replaceFirst("^\\Q" + parentFile.getAbsolutePath() + "\\E", "").replace("\\", "/");
    }


    public static String getRepairedFileUri(String fullpath) {
        return fullpath.replaceFirst("[\\\\/]$", "").replace("\\", "/").replace("//", "/");
    }


    /**
     * 格式化金额数字为千分位显示；
     *
     * @param str
     * @return
     */
    public static String fmtMicrometer(String str) {
        DecimalFormat df;
        if (str.indexOf(".") > -1) {
            if (str.length() - str.indexOf(".") - 1 == 0) {
                df = new DecimalFormat("###,##0.");
            } else if (str.length() - str.indexOf(".") - 1 == 1) {
                df = new DecimalFormat("###,##0.0");
            } else {
                df = new DecimalFormat("###,##0.00");
            }
        } else {
            df = new DecimalFormat("###,##0");
        }
        double number = Double.parseDouble(str);
        return df.format(number);
    }

    /**
     * 根据身份证获取年龄
     *
     * @param idNo
     * @return
     * @throws ParseException
     */
//    public static Integer getAge(String idNo) throws ParseException {
//        String dates;
//        if (idNo != null && idNo.length() == 15) {
//            idNo = convertIdcarBy15bit(idNo);
//        }
//        if (idNo != null && idNo.length() == 18) {
//            dates = idNo.substring(6, 14);
//            int year = DateUtil.daysBetween(DateUtil.valueOf(dates, DateUtil.DATEFORMAT_STR_012), DateUtil.getNow()) / 365;
//            return year;
//        }
//        return 0;
//    }

    /**
     * 将15位的身份证转成18位身份证
     *
     * @param idcard
     * @return
     * @throws ParseException
     */
    public static String convertIdcarBy15bit(String idcard) throws ParseException {
        String idcard17;
        // 非15位身份证
        if (idcard.length() != 15) {
            return null;
        }

        if (isDigital(idcard)) {
            // 获取出生年月日
            String birthday = idcard.substring(6, 12);
            Date birthdate = new SimpleDateFormat("yyMMdd").parse(birthday);
            Calendar cday = Calendar.getInstance();
            cday.setTime(birthdate);
            String year = String.valueOf(cday.get(Calendar.YEAR));

            idcard17 = idcard.substring(0, 6) + year + idcard.substring(8);

            char c[] = idcard17.toCharArray();
            String checkCode;

            if (null != c) {
//                int bit[] = new int[idcard17.length()];
                int bit[];
                // 将字符数组转为整型数组
                bit = converCharToInt(c);
                int sum17 = getPowerSum(bit);

                // 获取和值与11取模得到余数进行校验码
                checkCode = getCheckCodeBySum(sum17);
                // 获取不到校验位
                if (null == checkCode) {
                    return null;
                }

                // 将前17位与第18位校验码拼接
                idcard17 += checkCode;
            }
        } else { // 身份证包含数字
            return null;
        }
        return idcard17;
    }

    /**
     * 将和值与11取模得到余数进行校验码判断
     *
     * @param sum17
     * @return 校验位
     */
    private static String getCheckCodeBySum(int sum17) {
        String checkCode = null;
        switch (sum17 % 11) {
            case 10:
                checkCode = "2";
                break;
            case 9:
                checkCode = "3";
                break;
            case 8:
                checkCode = "4";
                break;
            case 7:
                checkCode = "5";
                break;
            case 6:
                checkCode = "6";
                break;
            case 5:
                checkCode = "7";
                break;
            case 4:
                checkCode = "8";
                break;
            case 3:
                checkCode = "9";
                break;
            case 2:
                checkCode = "x";
                break;
            case 1:
                checkCode = "0";
                break;
            case 0:
                checkCode = "1";
                break;
        }
        return checkCode;
    }

    /**
     * 将身份证的每位和对应位的加权因子相乘之后，再得到和值
     *
     * @param bit
     * @return
     */
    public static int getPowerSum(int[] bit) {

        int sum = 0;

        if (power.length != bit.length) {
            return sum;
        }

        for (int i = 0; i < bit.length; i++) {
            for (int j = 0; j < power.length; j++) {
                if (i == j) {
                    sum = sum + bit[i] * power[j];
                }
            }
        }
        return sum;
    }

    /**
     * 数字验证
     *
     * @param str
     * @return
     */
    public static boolean isDigital(String str) {
        return str == null || "".equals(str) ? false : str.matches("^[0-9]*$");
    }

    /**
     * 将字符数组转为整型数组
     *
     * @param c
     * @return
     * @throws NumberFormatException
     */
    public static int[] converCharToInt(char[] c) throws NumberFormatException {
        int[] a = new int[c.length];
        int k = 0;
        for (char temp : c) {
            a[k++] = Integer.parseInt(String.valueOf(temp));
        }
        return a;
    }

    /**
     * 字符转成map类型的
     * 字符串："current=1&mobileType=1&pageSize=10"
     */
    public static Map<String, Object> convertStringToMap(String s) {
        Map<String, Object> m = new HashMap<String, Object>();
        String[] couple = s.split("&");
        for (int i = 0; i < couple.length; i++) {
            String[] single = couple[i].split("=");
            if (single.length < 2) {
                m.put(single[0], "");
                continue;
            }
            m.put(single[0], single[1]);
        }
        return m;
    }

    /**
     * 比较版本号的大小,前者大则返回一个正数,后者大返回一个负数,相等则返回0
     *
     * @param version1
     * @param version2
     * @return
     */
    public static int compareVersion(String version1, String version2) throws Exception {
        if (version1 == null || version2 == null) {
            throw new Exception("compareVersion error:illegal params.");
        }
        String[] versionArray1 = version1.split("\\.");//注意此处为正则匹配，不能用"."；
        String[] versionArray2 = version2.split("\\.");
        int idx = 0;
        int minLength = Math.min(versionArray1.length, versionArray2.length);//取最小长度值
        int diff = 0;
        while (idx < minLength
                && (diff = versionArray1[idx].length() - versionArray2[idx].length()) == 0//先比较长度
                && (diff = versionArray1[idx].compareTo(versionArray2[idx])) == 0) {//再比较字符
            ++idx;
        }
        //如果已经分出大小，则直接返回，如果未分出大小，则再比较位数，有子版本的为大；
        diff = (diff != 0) ? diff : versionArray1.length - versionArray2.length;
        return diff;
    }

    static int[] DAYS = {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    /**
     * @param date yyyy-MM-dd HH:mm:ss  / yyyy-MM-dd
     * @return
     */
    public static boolean isValidDate(String date) {
        try {
            int year = 0;
            int month = 0;
            int day = 0;
            if (date.length() > 5) {
                year = Integer.parseInt(date.substring(0, 4));
                if (year <= 0) return false;
            } else {
                return false;
            }
            if (date.length() > 8) {
                month = Integer.parseInt(date.substring(5, 7));
                if (month <= 0 || month > 12)
                    return false;
            } else {
                return false;
            }
            if (date.length() > 11) {
                day = Integer.parseInt(date.substring(8, 10));
                if (day <= 0 || day > DAYS[month])
                    return false;
            } else {
                return false;
            }
            if (month == 2 && day == 29 && !isGregorianLeapYear(year)) {
                return false;
            }
            if (date.length() > 20) {
                int hour = Integer.parseInt(date.substring(11, 13));
                if (hour < 0 || hour > 23)
                    return false;
                int minute = Integer.parseInt(date.substring(14, 16));
                if (minute < 0 || minute > 59)
                    return false;
                int second = Integer.parseInt(date.substring(17, 19));
                if (second < 0 || second > 59)
                    return false;
            }
        } catch (Exception e) {
           logger.error("发生未知异常",e);
            return false;
        }
        return true;
    }

    public static final boolean isGregorianLeapYear(int year) {
        return year % 4 == 0 && (year % 100 != 0 || year % 400 == 0);
    }

    /**
     * 8 位 UCS 转换格式
     */
    public static final String UTF_8 = "UTF-8";

    /**
     * 字符串编码转换的实现方法
     *
     * @param str        待转换编码的字符串
     * @param newCharset 目标编码
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String changeCharset(String str, String newCharset)
            throws UnsupportedEncodingException {
        if (str != null) {
            //用默认字符编码解码字符串。
            byte[] bs = str.getBytes();
            //用新的字符编码生成字符串
            return new String(bs, newCharset);
        }
        return null;
    }

    /**
     * 字符串编码转换的实现方法
     *
     * @param str        待转换编码的字符串
     * @param oldCharset 原编码
     * @param newCharset 目标编码
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String changeCharset(String str, String oldCharset, String newCharset)
            throws UnsupportedEncodingException {
        if (str != null) {
            //用旧的字符编码解码字符串。解码可能会出现异常。
            byte[] bs = str.getBytes(oldCharset);
            //用新的字符编码生成字符串
            return new String(bs, newCharset);
        }
        return null;
    }

    /**
     * 把十六进制Unicode编码字符串转换为中文字符串, 将\u848B\u4ECB\u77F3转化成蒋介石，注意格式
     *
     * @param str eg:\u848B\u4ECB\u77F3
     * @return 蒋介石
     */
    public static String unicodeToString(String str) {

        Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");

        Matcher matcher = pattern.matcher(str);

        char ch;

        while (matcher.find()) {

            ch = (char) Integer.parseInt(matcher.group(2), 16);

            str = str.replace(matcher.group(1), ch + "");

        }
        return str;
    }

    /**
     * 获取uuid
     *
     * @return
     */
    public static String generationUuid() {
        return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
    }

    public static String isNull(String str) {
        return str == null || "".equals(str) ? "" : str;
    }

    public static boolean isNotBlank(CharSequence cs) {
        return !isBlank(cs);
    }

    public static boolean isBlank(CharSequence cs) {
        int strLen;
        if (cs != null && (strLen = cs.length()) != 0) {
            for (int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(cs.charAt(i))) {
                    return false;
                }
            }

            return true;
        } else {
            return true;
        }
    }

    public static boolean isNumber(String str) {
        if (isBlank(str)) {
            return false;
        } else {
            Pattern regex = Pattern.compile("(-)?\\d*(.\\d*)?");
            Matcher matcher = regex.matcher(str);
            boolean isMatched = matcher.matches();
            return isMatched;
        }
    }

    public static boolean isBlank(Object o) {
        return isNull(o).equals("");
    }

    public static String isNull(Object o) {
        if (o == null) {
            return "";
        } else {
            String str = "";
            if (o instanceof String) {
                str = (String) o;
            } else {
                str = o.toString();
            }

            return str.trim();
        }
    }

    public static boolean isEmpty(String cs) {
        return cs == null || cs.length() == 0;
    }

    public static String doubleToString(Double d) {
        if (d == null) {
            return "0";
        }
        return String.format("%.2f", d);
    }

    public static String clearUnderline(String str) {
        char[] charArr = isNull(str).toLowerCase().toCharArray();
        StringBuffer sb = new StringBuffer();
        sb.append(charArr[0]);
        boolean isClear = false;

        for (int i = 1; i < charArr.length; ++i) {
            if (charArr[i] == 95) {
                isClear = true;
            } else if (isClear && charArr[i] >= 97 && charArr[i] <= 122) {
                char c = (char) (charArr[i] - 32);
                sb.append(c);
                isClear = false;
            } else {
                sb.append(charArr[i]);
            }
        }

        return sb.toString();
    }

    public static String trim(final String str) {
        return str == null ? null : str.trim();
    }

    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * @Author: wl
     * @Description: 是否是中文姓名
     * @Date: 2017/12/25  10:12
     * @Version: 2.0
     */
    public static boolean isChineseName(String str) {
        String realName = isNull(str);
        String pattern = "^[\\u4e00-\\u9fa5]+(·[\\u4e00-\\u9fa5]+)*$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(realName);
        boolean isMatched = m.matches();
        return isMatched;
    }

    /**
     * 手机号模糊匹配
     *
     * @param sourcePhone
     * @param targetPhone
     * @return
     */
    public static boolean comparePattern(String sourcePhone, String targetPhone, char pattern, int threshold) {
        if (StringUtil.isBlank(sourcePhone) || StringUtil.isBlank(targetPhone)) {
            return false;
        }

        char[] source = sourcePhone.toCharArray();
        char[] target = targetPhone.toCharArray();

        // 长度不相等，不匹配
        if (source.length != target.length) {
            return false;
        }

        int equalc = 0;
        int patternc = 0;
        int notequalc = 0;
        for (int i = 0; i < target.length; i++) {
            if (source[i] == target[i] && source[i] != pattern) {
                equalc++;
            } else if (pattern == source[i] || pattern == target[i]) {
                patternc++;
            } else {
                notequalc++;
            }
        }

        // 有完全相同的字符，并且匹配的字符数 > 阈值
        if (equalc > 0 && (equalc + patternc) >= threshold) {
            return true;
        }

        return false;
    }

    /**
    * @description: 首字母大写
    * @param:
    * @author: xiang.li
    * @date: 16:40 2018/1/12 0012
    */
    public static String firstCharUpperCase(String s) {
        StringBuffer sb = new StringBuffer(s.substring(0, 1).toUpperCase());
        sb.append(s.substring(1, s.length()));
        return sb.toString();
    }

    /**
     * 下划线转驼峰法
     * @param line 源字符串
     * @param smallCamel 大小驼峰,是否为小驼峰
     * @return 转换后的字符串
     */
    public static String underline2Camel(String line,boolean smallCamel){
        if(line==null||"".equals(line)){
            return "";
        }
        StringBuffer sb=new StringBuffer();
        Pattern pattern=Pattern.compile("([A-Za-z\\d]+)(_)?");
        Matcher matcher=pattern.matcher(line);
        while(matcher.find()){
            String word=matcher.group();
            sb.append(smallCamel&&matcher.start()==0?Character.toLowerCase(word.charAt(0)):Character.toUpperCase(word.charAt(0)));
            int index=word.lastIndexOf('_');
            if(index>0){
                sb.append(word.substring(1, index).toLowerCase());
            }else{
                sb.append(word.substring(1).toLowerCase());
            }
        }
        return sb.toString();
    }

    public static String toString(Object obj) {
        return (obj == null) ? null : obj.toString();
    }

}
