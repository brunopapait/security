package br.com.papait.bruno.security.service;

import br.com.papait.bruno.security.dto.ProductDTO;
import br.com.papait.bruno.security.mapper.product.ProductMapper;
import br.com.papait.bruno.security.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

	private final ProductMapper productMapper;
	private final ProductRepository productRepository;

	public ProductService(ProductMapper productMapper, ProductRepository productRepository) {
		this.productMapper = productMapper;
		this.productRepository = productRepository;
	}

	public ProductDTO createProduct(ProductDTO productDTO) {
		var productEntity = this.productMapper.toPersistence(productDTO);
		productEntity = this.productRepository.save(productEntity);

		return this.productMapper.toDomain(productEntity);
	}

	public List<ProductDTO> findAll() {
		var productsEntities = this.productRepository.findAll();
		return productsEntities
						.stream()
						.map(this.productMapper::toDomain)
						.toList();
	}

	public ProductDTO findById(Long id) {
		var productEntity = this.productRepository.findById(id);
		return productEntity.map(this.productMapper::toDomain).orElse(null);
	}
}
