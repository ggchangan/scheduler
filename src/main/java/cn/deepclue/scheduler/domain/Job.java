package cn.deepclue.scheduler.domain;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xuzb on 17/03/2017.
 */
public class Job {
    private static final String JID_KEY = "JID";
    private static final String APPID_KEY = "APPID";
    private static final String CALLBACK_KEY = "CALLBACK";

    private int jId;
    private int appId;
    private Callback callback;

    public Job() {
    }

    public Job(int jId) {
        this.jId = jId;
    }

    public int getjId() {
        return jId;
    }

    public void setjId(int jId) {
        this.jId = jId;
    }

    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }

    public String getGroupName() {
        return "DEFAULT";
    }

    public Callback getCallback() {
        return callback;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public boolean run() {
        return true;
    }

    public String serialize() {
        Map<String, String> dataMap = new HashMap<>();
        dataMap.put(JID_KEY, String.valueOf(jId));
        dataMap.put(APPID_KEY, String.valueOf(appId));
        dataMap.put(CALLBACK_KEY, new Gson().toJson(callback));
        return new Gson().toJson(dataMap);
    }

    public void deserialize(String jsonMap) {
        Type mapType = new TypeToken<Map<String, String>>() {
        }.getType();
        Map<String, String> dataMap = new Gson().fromJson(jsonMap, mapType);

        String jidKey = dataMap.get(JID_KEY);
        this.jId = Integer.valueOf(jidKey);

        String appidKey = dataMap.get(APPID_KEY);
        this.appId = Integer.valueOf(appidKey);

        String callbackKey = dataMap.get(CALLBACK_KEY);
        this.callback = new Gson().fromJson(callbackKey, Callback.class);
    }
}
