package vn.rikkeisoft.demo.service.mapper;

//used to cast
//<D> DTO type
//<E> Entity type
public interface EntityMapper<D, E> {
    //cast from DTO to ENTITY
    E toEntity(D dto);

    //cast from ENTITY to DTO
    D toDto(E entity);

}
