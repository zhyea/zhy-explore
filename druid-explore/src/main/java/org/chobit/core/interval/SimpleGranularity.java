package org.chobit.core.interval;

/**
 * @author robin
 */
public enum SimpleGranularity {

    /**
     * 会将起始和结束时间内所有数据聚合到一起返回一个结果集
     */
    ALL,
    /**
     * 按照创建索引时的最小粒度做聚合计算，最小粒度是毫秒为单位，不推荐使用性能较差
     */
    NONE,
    /**
     * 以分钟作为聚合的最小粒度
     */
    MINUTE,
    /**
     * 15分钟聚合
     */
    FIFTEEN_MINUTE,
    /**
     * 30分钟聚合
     */
    THIRTY_MINUTE,
    /**
     * 一小时聚合
     */
    HOUR,
    /**
     * 天聚合
     */
    DAY,
    /**
     * 月聚合
     */
    MONTH,
    /**
     * 季度聚合
     */
    QUARTER,
    /**
     * 年聚合
     */
    YEAR,
    ;


}
