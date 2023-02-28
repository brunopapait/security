package br.com.papait.bruno.security.mapper.product;

import br.com.papait.bruno.security.commons.Mapper;
import br.com.papait.bruno.security.dto.ProductDTO;
import br.com.papait.bruno.security.entity.ProductEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper implements Mapper<ProductEntity, ProductDTO> {
	@Override
	public ProductEntity toPersistence(ProductDTO objectDTO) {
		return new ProductEntity(objectDTO.id(), objectDTO.name(), objectDTO.price(), objectDTO.description());
	}

	@Override
	public ProductDTO toDomain(ProductEntity objectPersistence) {
		return new ProductDTO(objectPersistence.getId(), objectPersistence.getName(), objectPersistence.getPrice(), objectPersistence.getDescription());
	}
}
