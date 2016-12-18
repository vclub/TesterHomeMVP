package com.testerhome.nativeandroid;

/**
 * Created by Bin Li on 2016/12/9.
 */

public class Config {

    public static final String APP_ID = "wxfa200479f6cbbc58";

    public static final String TOPICS_TYPE_RECENT = "recent";       // 最新
    public static final String TOPICS_TYPE_POPULAR = "popular";     // 热门的话题
    public static final String TOPICS_TYPE_NO_REPLY = "no_reply";   // 还没有任何回帖的
    public static final String TOPICS_TYPE_EXCELLENT = "excellent"; // 精华帖

    public static final String WIKI_URL = "https://testerhome.com/wiki";

    public static String getImageUrl(String imagePath) {
        if (imagePath.startsWith("//testerhome.com")) {
            return "https:".concat(imagePath);
        } else if (!imagePath.contains("https://testerhome.com")) {
            return "https://testerhome.com".concat(imagePath);
        } else {
            return imagePath;
        }

    }
}
