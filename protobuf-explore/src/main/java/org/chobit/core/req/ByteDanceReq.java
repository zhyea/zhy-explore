package org.chobit.core.req;

import org.chobit.core.constant.DeviceIdType;
import org.chobit.core.proto.ByteDanceRta;
import org.chobit.core.tools.MD5;

import java.io.IOException;
import java.util.List;

import static org.chobit.core.constant.DeviceIdType.*;
import static org.chobit.core.tools.ToolKit.uuid;

public class ByteDanceReq implements RtaRequest<ByteDanceRta.RtaResponse> {

    @Override
    public String path() {
        return "/rta/bytedance";
    }

    @Override
    public byte[] body(List<Long> rtaIds, DeviceIdType didType, String did) {
        ByteDanceRta.RtaRequest req = init(rtaIds, didType, did);
        return req.toByteArray();
    }

    @Override
    public ByteDanceRta.RtaResponse parseResponse(byte[] content) throws IOException {
        ByteDanceRta.RtaResponse.Builder builder = ByteDanceRta.RtaResponse.newBuilder();
        builder.mergeFrom(content);

        ByteDanceRta.RtaResponse rtaResp = builder.build();

        return rtaResp;
    }


    private ByteDanceRta.RtaRequest init(List<Long> rtaIds, DeviceIdType didType, String did) {
        ByteDanceRta.RtaRequest.Builder builder = ByteDanceRta.RtaRequest.newBuilder();

        ByteDanceRta.Device device = null;
        if (didType == IMEI) {
            String imeiMd5 = MD5.encode(did);
            System.out.println(imeiMd5);
            builder.setDid(imeiMd5);
            device = ByteDanceRta.Device.newBuilder()
                    .setImeiMd5(imeiMd5)
                    .build();
        } else if (didType == IDFA) {
            builder.setDid(did);
            device = ByteDanceRta.Device.newBuilder()
                    .setIdfa(did)
                    .build();
        } else if (didType == IMEI_MD5) {
            builder.setDid(did);
            device = ByteDanceRta.Device.newBuilder()
                    .setImeiMd5(did)
                    .build();
        }

        builder.setPlatformValue(1);
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
        builder.addAllRtaIds(rtaIds);
        //builder.setEnableStrategyValue(9);
        //builder.setTtDid(589647L);


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
