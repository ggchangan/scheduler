package cn.deepclue.scheduler.domain;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class Callback {
    private String url;
    private String requestBody;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }

    public MultiValueMap getRequest() {
        MultiValueMap<String, String> hashMap = new LinkedMultiValueMap();

        return null;
    }
}
