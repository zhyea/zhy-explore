package org.chobit.core;

import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.chobit.core.req.ByteDanceReq;
import org.chobit.core.req.RtaRequest;

import java.io.IOException;
import java.util.List;

import static org.chobit.core.Config.*;
import static org.chobit.core.constant.DeviceIdType.IMEI;

public class MyApp {


    private static void execute(RtaRequest req) throws IOException {

        List<Long> rtaIds = PROD_RTA_IDS;

        String path = RTA_PROD + req.path();
        byte[] body = req.body(rtaIds, IMEI, TEST_IMEI);
        Response response = Request.Post(path).bodyByteArray(body).execute();
        req.parseResponse(response.returnContent().asBytes());
    }


    public static void main(String[] args) throws IOException {
        RtaRequest req = new ByteDanceReq();
        execute(req);
    }

}
