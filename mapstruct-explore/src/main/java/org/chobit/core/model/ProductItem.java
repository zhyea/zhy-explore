package org.chobit.core.model;

import lombok.Data;
import org.chobit.core.enums.ProductStatusEnum;

import java.time.LocalDate;

/**
 * 商品项
 *
 * @author robin
 */
@Data
public class ProductItem {

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 保质期
	 */
	private Integer qualityGranteeMonths;

	/**
	 * 生产日期
	 */
	private LocalDate manufactureDate;

	/**
	 * 状态
	 */
	private ProductStatusEnum status;

}
