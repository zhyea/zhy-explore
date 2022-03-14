package org.chobit.core;

import com.ke.bigdata.growth.model.Pair;
import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;
import org.chobit.core.req.ByteDanceRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;

import static com.ke.bigdata.growth.utils.Strings.isNotBlank;
import static com.ke.bigdata.growth.utils.Strings.toLong;

public class RtaReqClient extends AbstractJavaSamplerClient {


    private static final Logger log = LoggerFactory.getLogger(RtaReqClient.class);
    /**
     * RtaID集合
     */
    private final List<Long> rtaIds = new LinkedList<>();
    /**
     * RTA请求路径
     */
    private String url;


    private final ByteDanceRequest req = new ByteDanceRequest();

    private static final String NAME_RTA_IDS = "Rta IdS";

    private static final String NAME_URL = "Rta URL";

    /**
     * 用于设置传入的参数，可以设置多个，已设置的参数会显示到Jmeter参数列表中
     * Arguments类表示一组参数对象
     *
     * @return 参数对象
     */
    @Override
    public Arguments getDefaultParameters() {
        // 声明定义Arguments类
        Arguments params = new Arguments();
        // 添加一个新参数
        params.addArgument(NAME_URL, "");
        // 添加一个新参数
        params.addArgument(NAME_RTA_IDS, "");
        return params;
    }


    /**
     * 初始化方法，用于初始化性能测试时的每个线程，实际运行时每个线程仅执行一次
     * <p>
     * JavaSamplerContext类用于向JavaSamplerClient实现提供上下文信息
     * 这当前由在GUI中指定的初始化参数组成，其他数据将来可以通过JavaSamplerContext访问
     *
     * @param jsc 上下文
     */
    @Override
    public void setupTest(JavaSamplerContext jsc) {
        // 以String形式获取指定参数的值，或者如果未指定该值，则返回指定的默认值
        String str = jsc.getParameter(NAME_RTA_IDS, "10000");
        url = jsc.getParameter(NAME_URL);

        log.info("Url:{}, RtaIds: {}", url, str);

        if (isNotBlank(str)) {
            String[] arr = str.split(",");
            for (String s : arr) {
                rtaIds.add(toLong(s));
            }
        }
    }


    /**
     * 性能测试时的线程运行体，即测试执行的循环体，根据线程数和循环次数的不同可执行多次
     *
     * @param ctx 上下文
     * @return 运行结果
     */
    @Override
    public SampleResult runTest(JavaSamplerContext ctx) {

        SampleResult results = new SampleResult();
        results.sampleStart();
        try {
            Pair<Integer, Long> pair = req.send(url, rtaIds);
            results.setResponseData(pair.toString(), StandardCharsets.UTF_8.name());
            if (200 == pair.getKey()) {
                results.setSuccessful(true);
            } else {
                results.setSuccessful(false);
            }
        } catch (IOException e) {
            log.error("rta 请求出现异常", e);
            results.setResponseData("执行过程中出现异常", StandardCharsets.UTF_8.name());
            results.setSuccessful(false);
        } finally {
            results.sampleEnd();
        }
        return results;
    }

    /**
     * 测试结束方法，用于结束性能测试中的每个线程，实际运行时，每个线程仅执行一次，在测试方法运行结束后执行
     *
     * @param ctx 请求上下文
     */
    public void teardownTest(JavaSamplerContext ctx) {
        //
    }
}
