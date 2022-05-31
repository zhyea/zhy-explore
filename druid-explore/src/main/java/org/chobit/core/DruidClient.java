package org.chobit.core;


import com.fasterxml.jackson.core.type.TypeReference;
import org.chobit.core.http.HttpResult;
import org.chobit.core.interval.DruidResponse;

import java.util.List;

import static org.chobit.core.http.HttpClient.postBody;
import static org.chobit.core.utils.JsonKit.fromJson;
import static org.chobit.core.utils.StrKit.isBlank;


/**
 * @author robin
 */
public final class DruidClient {

    private static final String URL_DRUID_QUERY = "http://druid-query-host/druidServing/query/queryDruid";


    public static <T> List<T> query(DruidRequest request, TypeReference<DruidResponse<T>> typeReference) {
        String resp = query(request);
        if (isBlank(resp)) {
            return null;
        }

        DruidResponse<T> response = fromJson(resp, typeReference);
        if (null == response || null == response.getData()) {
            return null;
        }

        return response.getData();
    }


    public static String query(DruidRequest request) {
        HttpResult result = postBody(URL_DRUID_QUERY, request.toJson());
        if (result.isSuccess()) {
            return result.getContent();
        }
        return "";
    }

    private DruidClient() {
    }
}
