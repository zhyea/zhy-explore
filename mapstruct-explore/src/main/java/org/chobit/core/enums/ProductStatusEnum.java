package org.chobit.core.enums;

import java.time.LocalDate;

/**
 * 产品状态
 *
 * @author robin
 */
public enum ProductStatusEnum {

	/**
	 * 有效
	 */
	EFFECTIVE,

	/**
	 * 过期
	 */
	EXPIRED,;


	/**
	 * 根据生产日期和保质期判断产品状态
	 *
	 * @param manufactureDate      生产日期
	 * @param qualityGranteeMonths 保质期
	 * @return 产品状态
	 */
	public static ProductStatusEnum analyzeStatus(LocalDate manufactureDate, Integer qualityGranteeMonths) {
		if (null == manufactureDate || null == qualityGranteeMonths) {
			return null;
		}

		ProductStatusEnum status = ProductStatusEnum.EFFECTIVE;

		if (manufactureDate.plusMonths(qualityGranteeMonths)
				.isBefore(LocalDate.now())) {
			status = ProductStatusEnum.EXPIRED;
		}

		return status;
	}
}
