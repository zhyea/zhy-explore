package org.chobit.core.convert;

import org.chobit.core.enums.ProductStatusEnum;
import org.chobit.core.model.ProductEntity;
import org.chobit.core.model.ProductItem;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.time.LocalDate;
import java.util.List;

/**
 * 产品对象转换
 *
 * @author robin
 */
@Mapper
public interface ProductConverter {

	ProductItem entity2ItemSimply(ProductEntity entity);

	@Named("entity2Item")
	default ProductItem entity2Item(ProductEntity entity) {
		if (null == entity) {
			return null;
		}

		ProductItem result = entity2ItemSimply(entity);
		result.setStatus(ProductStatusEnum.analyzeStatus(entity.getManufactureDate(), entity.getQualityGranteeMonths()));

		return result;
	}

	@IterableMapping(qualifiedByName = "entity2Item")
	List<ProductItem> entityList2ItemList(List<ProductEntity> entityList);
}
