package org.chobit.core.req;

import com.ke.bigdata.growth.http.HttpClient;
import com.ke.bigdata.growth.model.Pair;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.chobit.core.proto.ByteDanceRta;

import java.io.IOException;
import java.util.List;

import static com.ke.bigdata.growth.utils.Strings.uuid;

public class ByteDanceRequest {

    public Pair<Integer, Long> send(String url, List<Long> rtaIds) throws IOException {
        ByteDanceRta.RtaRequest rtaReq = init(rtaIds);
        Request req = Request.Post(url).bodyByteArray(rtaReq.toByteArray());
        long start = System.currentTimeMillis();
        HttpResponse r = HttpClient.execute(req).returnResponse();
        int code = r.getStatusLine().getStatusCode();
        long duration = System.currentTimeMillis() - start;
        return new Pair<>(code, duration);
    }


    private ByteDanceRta.RtaRequest init(List<Long> rtaIds) {
        ByteDanceRta.RtaRequest.Builder builder = ByteDanceRta.RtaRequest.newBuilder();

        String imeiMd5 = uuid();

        builder.setPlatformValue(1);
        builder.setDid(imeiMd5); //设备号
        //builder.setSlotId(123456L); //广告位ID
        //builder.setAge(16);
        //builder.setGenderValue(1);
        //builder.setCity("北京");
        //builder.setModel("RedMi 10");
        //builder.addInstallList("com.ke.zhy");
        builder.setDidTypeValue(1);
        //builder.setExperiment(12);
        //builder.setDeviceTypeValue(2);
        builder.setReqId(uuid());
        builder.setSource("local");
        //builder.addRtaIds(123L);
        builder.addAllRtaIds(rtaIds);
        //builder.setEnableStrategyValue(9);
        //builder.setTtDid(589647L);

        ByteDanceRta.Device device =
                ByteDanceRta.Device.newBuilder()
                        .setImeiMd5(imeiMd5)
                        .build();
        builder.setDevice(device);

        ByteDanceRta.Industry industry =
                ByteDanceRta.Industry.newBuilder()
                        .setFirstIndustry(16)
                        .setSecondIndustry(18)
                        .build();
        //builder.setIndustry(industry);

        ByteDanceRta.Geo geo =
                ByteDanceRta.Geo.newBuilder()
                        .setCity(12345L)
                        .setLat(12345.56)
                        .setLat(56789.23)
                        .build();
        //builder.setGeo(geo);

        //builder.setDeviceModel("Redmi-10");

        return builder.build();
    }

}
