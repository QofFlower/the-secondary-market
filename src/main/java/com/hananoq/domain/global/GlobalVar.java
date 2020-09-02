package com.hananoq.domain.global;

/**
 * @author :花のQ
 * @since 2020/7/11 11:02
 **/
public class GlobalVar {
    private static String host = "39.106.228.114";

    private static Integer port = 8086;

    public static String preURL = "http://" + host + ":" + port;

    public static String imgPath = "/server/thesecondmarket/static/img";

    public static String avatarPath = imgPath + "/avatars/";

    public static String goodsPath = imgPath + "/goods/";

    public static String avatarPreURL = preURL + "/img/avatars/";

    public static String goodsPicUrl = preURL + "/img/goods/";

    public static String defaultAvatar = "default_avatar.jpg";

    /*private static String host = "localhost";

    private static Integer port = 8086;

    public static String preURL = "http://" + host + ":" + port;

    private static String imgPath = "D:/MyProgram/test/static/img";

    public static String avatarPath = imgPath + "/avatars/";

    public static String goodsPath = imgPath + "/goods/";

    public static String avatarPreURL = preURL + "/img/avatars/";

    public static String goodsPicUrl = preURL + "/img/goods/";

    public static String defaultAvatar = "default_avatar.jpg";*/

}
