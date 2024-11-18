package org.chobit.core.model;

import lombok.Data;

import java.time.LocalDate;

/**
 * 产品
 *
 * @author robin
 */
@Data
public class ProductEntity {


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
}
