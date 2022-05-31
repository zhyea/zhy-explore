package org.chobit.core.http;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.DefaultServiceUnavailableRetryStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.StandardHttpRequestRetryHandler;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.chobit.core.utils.StrKit.isNotBlank;
import static org.chobit.core.utils.UrlHelper.buildQueryStr;


/**
 * Http请求处理工具类
 *
 * @author robin
 */
public final class HttpClient {


    private static final Logger logger = LoggerFactory.getLogger(HttpClient.class);


    /**
     * Get请求
     *
     * @param url 请求URL
     * @return 请求结果
     */
    public static HttpResult get(String url) {
        return get(url, null, null);
    }

    /**
     * Get请求
     *
     * @param url    请求url
     * @param header 请求header
     * @param params 请求参数
     * @return 请求结果
     */
    public static HttpResult get(String url, Map<String, ?> header, Map<String, Object> params) {
        if (null != params) {
            url = buildQueryStr(url, params);
        }
        Request request = Request.Get(url);
        return execute(request, header, null);
    }

    /**
     * post body请求
     *
     * @param url  请求url
     * @param body 请求体
     * @return 请求结果
     */
    public static HttpResult postBody(String url, String body) {
        return postBody(url, null, body);
    }

    /**
     * post body请求
     *
     * @param url    请求url
     * @param header 请求header
     * @param body   请求体
     * @return 请求结果
     */
    public static HttpResult postBody(String url, Map<String, String> header, String body) {
        return postBody(url, header, body, null);
    }

    /**
     * post body请求
     *
     * @param url    请求url
     * @param header 请求header
     * @param body   请求体
     * @param proxy  请求代理
     * @return 请求结果
     */
    public static HttpResult postBody(String url, Map<String, String> header, String body, HttpHost proxy) {
        Request request = Request.Post(url);
        if (isNotBlank(body)) {
            request.bodyString(body, ContentType.APPLICATION_JSON);
        }
        return execute(request, header, proxy);
    }


    /**
     * post body请求
     *
     * @param url    请求url
     * @param header 请求header
     * @param params 请求参数
     * @return 请求结果
     */
    public static HttpResult post(String url, Map<String, String> header, Map<String, Object> params) {
        if (null == params || params.isEmpty()) {
            throw new IllegalArgumentException("Params of POST request cannot be empty.");
        }
        List<NameValuePair> pairs = params.entrySet()
                .stream()
                .map(e -> new BasicNameValuePair(e.getKey(), e.getValue().toString()))
                .collect(Collectors.toList());
        Request request = Request.Post(url).bodyForm(pairs, StandardCharsets.UTF_8);
        return execute(request, header, null);
    }


    /**
     * 获取GET请求返回的信息流
     *
     * @param url 请求路径
     * @return 数据流
     */
    public static InputStream getForStream(String url) {
        return executeForStream(Request.Get(url), null, null);
    }


    /**
     * 获取POST请求返回的信息流
     *
     * @param url    请求url
     * @param header header
     * @param body   请求体
     * @return 数据流
     */
    public static InputStream postForStream(String url, Map<String, String> header, String body) {
        Request request = Request.Post(url);
        if (isNotBlank(body)) {
            request.bodyString(body, ContentType.APPLICATION_JSON);
        }
        return executeForStream(request, header, null);
    }


    /**
     * 执行http请求，并返回HttpResult实例
     *
     * @param request http请求实例
     * @return 请求结果
     */
    public static HttpResult execute(HttpRequest request) {
        Request req;
        switch (request.getMethod()) {
            case GET:
                req = Request.Get(buildQueryStr(request.getUrl(), request.getParams()));
                break;
            case POST:
                req = Request.Post(request.getUrl()).bodyForm(request.params(), StandardCharsets.UTF_8);
                break;
            case POST_BODY:
                req = Request.Post(request.getUrl()).bodyString(request.getBody(), ContentType.APPLICATION_JSON);
                break;
            default:
                throw new IllegalArgumentException("Http Method is invalid.");
        }
        HttpHost proxy = null;
        if (isNotBlank(request.getHost()) && request.getPort() > 0) {
            proxy = new HttpHost(request.getHost(), request.getPort());
        }

        return execute(req, request.getHeaders(), proxy);
    }


    /**
     * 发送byte[]形式的post请求，并接收byte[]形式的请求结果
     * 主要用来处理protobuf之类的请求
     *
     * @param url    请求地址
     * @param header header信息
     * @param body   请求体
     * @return 响应信息
     * @throws IOException 异常信息
     */
    public static byte[] postWithByte(String url, Map<String, ?> header, byte[] body) throws IOException {
        Request req = Request.Post(url).bodyByteArray(body);
        Response response = execute0(req, header, null);
        return response.returnContent().asBytes();
    }


    /**
     * 执行http请求，并返回HttpResult实例
     *
     * @param request http请求实例
     * @param header  请求header
     * @param proxy   代理路由
     * @return 请求结果
     */
    private static HttpResult execute(Request request, Map<String, ?> header, HttpHost proxy) {
        try {
            Response response = execute0(request, header, proxy);
            return resultOf(response);
        } catch (IOException e) {
            logger.error("Execute common http request error. detail:[{}]", request, e);
            HttpResult result = new HttpResult();
            result.setErrMsg(e.getMessage());
            return result;
        }
    }

    /**
     * 执行http请求，并返回HttpResult实例
     *
     * @param request http请求实例
     * @param header  请求header
     * @param proxy   请求代理
     * @return 请求结果
     */
    private static InputStream executeForStream(Request request, Map<String, String> header, HttpHost proxy) {
        try {
            Response response = execute0(request, header, proxy);
            return streamOf(response);
        } catch (IOException e) {
            logger.error("Execute http request for InputStream error. detail:[{}]", request, e);
            return null;
        }
    }


    /**
     * 执行http请求
     *
     * @param request http请求
     * @param header  请求header
     * @param proxy   请求代理
     * @return http响应信息
     * @throws IOException 异常信息
     */
    private static Response execute0(Request request, Map<String, ?> header, HttpHost proxy) throws IOException {
        try {
            if (null != header) {
                for (Map.Entry<String, ?> e : header.entrySet()) {
                    request.addHeader(e.getKey(), String.valueOf(e.getValue()));
                }
            }
            if (null != proxy) {
                request.viaProxy(proxy);
            }
            request.connectTimeout(120000)
                    .socketTimeout(120000);

            return EXECUTOR.execute(request);
        } catch (IOException e) {
            logger.error("Execute http request error. detail:[{}]", request, e);
            throw e;
        }
    }


    private static final Executor EXECUTOR = Executor.newInstance(customHttpClient());


    /**
     * 调整Http请求连接池，设置请求重试策略
     */
    private static org.apache.http.client.HttpClient customHttpClient() {
        return HttpClientBuilder.create()
                .setMaxConnTotal(512)
                .setMaxConnPerRoute(128)
                .setRetryHandler(new StandardHttpRequestRetryHandler())
                .setServiceUnavailableRetryStrategy(new DefaultServiceUnavailableRetryStrategy(6, 3))
                .build();
    }


    /**
     * 解析响应信息为HttpResult实例
     *
     * @param response http请求响应
     * @return HttpResult实例
     */
    private static HttpResult resultOf(Response response) {
        int status = -1;
        String content = "";
        try {
            HttpResponse res = response.returnResponse();
            status = res.getStatusLine().getStatusCode();
            content = EntityUtils.toString(res.getEntity());
            return new HttpResult(status, content);
        } catch (Exception e) {
            logger.error("Read response error.", e);
            HttpResult result = new HttpResult(status, content);
            result.setErrMsg(e.getMessage());
            return result;
        }
    }


    /**
     * 从http响应信息中读取数据流
     */
    private static InputStream streamOf(Response response) {
        try {
            HttpResponse r = response.returnResponse();
            HttpEntity entity = r.getEntity();
            return entity.getContent();
        } catch (IOException e) {
            logger.error("Read response error", e);
            return null;
        }
    }


    private HttpClient() {
        throw new UnsupportedOperationException("Private constructor, cannot be accessed.");
    }
}
