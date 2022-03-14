package org.chobit.core;

import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.chobit.core.constant.DeviceIdType;
import org.chobit.core.req.RtaRequest;

import java.util.List;

public abstract class AbstractRtaTest {


    protected <T> T execute(RtaRequest<T> req,
                            String host,
                            List<Long> rtaIds,
                            DeviceIdType didType,
                            String did) {
        try {
            String path = host + req.path();
            byte[] body = req.body(rtaIds, didType, did);
            Response response = Request.Post(path).bodyByteArray(body).execute();
            return req.parseResponse(response.returnContent().asBytes());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
