package org.chobit.spring.model.request;

import lombok.Data;

import java.util.List;

/**
 * 批量查询请求
 *
 * @author robin
 */
@Data
public class BatchQueryRequest {


    private List<String> idNoList;


}
