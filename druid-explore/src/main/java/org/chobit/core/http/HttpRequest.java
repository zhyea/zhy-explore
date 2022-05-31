package org.chobit.core.http;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.chobit.core.http.HttpMethod.GET;
import static org.chobit.core.utils.JsonKit.toJson;
import static org.chobit.core.utils.UrlHelper.buildQueryStr;


/**
 * Http请求包装类
 *
 * @author robin
 */
public class HttpRequest {


    private HttpMethod method = GET;

    private String url;

    private final Map<String, String> headers = new LinkedHashMap<>(4);

    private final Map<String, Object> params = new LinkedHashMap<>(4);

    private String body;

    private String host;

    private int port;


    public HttpRequest() {
    }


    public HttpRequest(HttpMethod method, String url) {
        this.method = method;
        this.url = url;
    }


    public List<NameValuePair> params() {
        List<NameValuePair> result = new LinkedList<>();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            BasicNameValuePair pair = new BasicNameValuePair(entry.getKey(), String.valueOf(entry.getValue()));
            result.add(pair);
        }
        return result;
    }


    public void addHeaders(Map<String, String> headers) {
        this.headers.putAll(headers);
    }


    public void addHeader(String name, String value) {
        this.headers.put(name, value);
    }


    public void addParams(Map<String, Object> params) {
        this.params.putAll(params);
    }


    public void addParam(String name, Object value) {
        this.params.put(name, value);
    }


    public HttpMethod getMethod() {
        return method;
    }

    public void setMethod(HttpMethod method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }


    public Map<String, Object> getParams() {
        return params;
    }


    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public String toString() {
        if (method == GET) {
            return buildQueryStr(this.url, this.params);
        } else {
            return toJson(this);
        }
    }
}
