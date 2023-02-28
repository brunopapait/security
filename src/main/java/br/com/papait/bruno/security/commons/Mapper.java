package br.com.papait.bruno.security.commons;

public interface Mapper<T, R> {
		T toPersistence(R objectDTO);
		R toDomain(T objectPersistence);
}
