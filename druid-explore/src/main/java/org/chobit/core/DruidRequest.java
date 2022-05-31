package org.chobit.core;


import org.chobit.core.interval.*;
import org.chobit.core.utils.JsonKit;
import org.chobit.core.utils.LocalDateKit;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.chobit.core.utils.StrKit.isBlank;


/**
 * @author robin
 */
public class DruidRequest {

    private final Map<String, Object> params = new HashMap<>(4);

    private final List<Dimension> dimensions = new LinkedList<>();

    private final List<Aggregate> aggregations = new LinkedList<>();

    private String apiKey;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private String timeZone = "Asia/Shanghai";

    private QueryType queryType = QueryType.GROUP_BY;

    private SimpleGranularity granularity = SimpleGranularity.ALL;

    private String dataSource;

    private String queryId;


    private void addDim(String dimName, String outName, OutputType outputType, DimensionType dimType) {
        outName = (isBlank(outName) ? dimName : outName);
        outputType = (null == outputType ? OutputType.STRING : outputType);
        dimType = (null == dimType ? DimensionType.DEFAULT : dimType);

        Dimension dim = new Dimension();
        dim.setDimension(dimName);
        dim.setOutputName(outName);
        dim.setOutputType(outputType);
        dim.setType(dimType);
        this.dimensions.add(dim);
    }

    private void addAggregator(String fieldName, String outputName, AggregateType type) {
        Aggregate agg = new Aggregate();
        agg.setFieldName(fieldName);
        agg.setName(outputName);
        agg.setType(type);

        this.aggregations.add(agg);
    }


    private void clear() {
        this.params.clear();
        this.dimensions.clear();
        this.aggregations.clear();
        this.startTime = null;
        this.endTime = null;
        this.queryType = null;
        this.granularity = null;
        this.apiKey = null;
        this.queryId = null;
    }


    private static final String DRUID_TIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    public Map<String, Object> toMap() {
        if (null == this.startTime) {
            throw new IllegalArgumentException("起始时间不可以为空");
        }
        if (null == this.endTime) {
            this.endTime = LocalDateTime.now();
        }
        this.params.put("granularity", this.granularity.name().toLowerCase());

        String interval = LocalDateKit.formatTime(DRUID_TIME_PATTERN, startTime)
                + "/" + LocalDateKit.formatTime(DRUID_TIME_PATTERN, endTime);
        this.params.put("intervals", new String[]{interval});
        this.params.put("queryType", this.queryType);
        this.params.put("dataSource", this.dataSource);

        Map<String, String> context = new HashMap<>(2);
        context.put("sqlTimeZone", timeZone);
        context.put("queryId", queryId);
        this.params.put("context", context);
        this.params.put("dataSource", this.dataSource);
        this.params.put("aggregations", this.aggregations);
        this.params.put("dimensions", this.dimensions);

        Map<String, Object> request = new HashMap<>(8);
        request.put("apiKey", apiKey);
        request.put("json", this.params);

        return request;
    }


    public String toJson() {
        Map<String, Object> map = toMap();
        return JsonKit.toJson(map);
    }

    public static final class Builder {

        private final DruidRequest instance;

        private Builder() {
            this.instance = new DruidRequest();
        }

        public static Builder newBuilder() {
            return new Builder();
        }


        public Builder apiKey(String apiKey) {
            instance.apiKey = apiKey;
            return this;
        }

        public Builder queryId(String queryId) {
            instance.queryId = queryId;
            return this;
        }

        public Builder dataSource(String dataSource) {
            instance.dataSource = dataSource;
            return this;
        }

        public Builder startTime(LocalDateTime startTime) {
            instance.startTime = startTime;
            return this;
        }

        public Builder endTime(LocalDateTime endTime) {
            instance.endTime = endTime;
            return this;
        }

        public Builder timeZone(String timeZone) {
            instance.timeZone = timeZone;
            return this;
        }

        public Builder queryType(QueryType queryType) {
            instance.queryType = queryType;
            return this;
        }

        public Builder granularity(SimpleGranularity granularity) {
            instance.granularity = granularity;
            return this;
        }

        public Builder addDim(String name) {
            instance.addDim(name, name, null, null);
            return this;
        }

        public Builder addDim(String name, OutputType outputType) {
            instance.addDim(name, name, outputType, null);
            return this;
        }

        public Builder addDim(String name, String outputName) {
            instance.addDim(name, outputName, null, null);
            return this;
        }

        public Builder addDim(String name, String outputName, OutputType outputType) {
            instance.addDim(name, outputName, outputType, null);
            return this;
        }

        public Builder addDim(String name, String outputName, OutputType outputType, DimensionType dimType) {
            instance.addDim(name, outputName, outputType, dimType);
            return this;
        }

        public Builder addAggregator(String fieldName, String outputName, AggregateType type) {
            instance.addAggregator(fieldName, outputName, type);
            return this;
        }


        public DruidRequest build() {
            return instance;
        }

        public void clear() {
            this.instance.clear();
        }
    }


    private DruidRequest() {
    }
}
