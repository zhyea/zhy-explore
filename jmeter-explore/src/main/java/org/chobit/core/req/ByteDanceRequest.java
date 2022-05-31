package org.chobit.core.req;

import javafx.util.Pair;
import org.chobit.core.proto.ByteDanceRta;

import java.io.IOException;
import java.util.List;


public class ByteDanceRequest {

    public Pair<Integer, Long> send(String url, List<Long> rtaIds) throws IOException {
        return null;
    }


    private ByteDanceRta.RtaRequest init(List<Long> rtaIds) {
        ByteDanceRta.RtaRequest.Builder builder = ByteDanceRta.RtaRequest.newBuilder();
        builder.setPlatformValue(1);
        builder.setSlotId(123456L); //广告位ID
        builder.setAge(16);
        builder.setGenderValue(1);
        builder.setCity("北京");
        builder.setModel("RedMi 10");
        builder.addInstallList("com.ke.zhy");
        builder.setDidTypeValue(1);
        builder.setExperiment(12);
        builder.setDeviceTypeValue(2);
        builder.setSource("local");
        builder.addRtaIds(123L);
        builder.addAllRtaIds(rtaIds);
        builder.setEnableStrategyValue(9);
        builder.setTtDid(589647L);

        ByteDanceRta.Device device =
                ByteDanceRta.Device.newBuilder()
                        .setImeiMd5("123456")
                        .build();
        builder.setDevice(device);

        ByteDanceRta.Industry industry =
                ByteDanceRta.Industry.newBuilder()
                        .setFirstIndustry(16)
                        .setSecondIndustry(18)
                        .build();
        builder.setIndustry(industry);

        ByteDanceRta.Geo geo =
                ByteDanceRta.Geo.newBuilder()
                        .setCity(12345L)
                        .setLat(12345.56)
                        .setLat(56789.23)
                        .build();
        builder.setGeo(geo);

        builder.setDeviceModel("Redmi-10");

        return builder.build();
    }

}
