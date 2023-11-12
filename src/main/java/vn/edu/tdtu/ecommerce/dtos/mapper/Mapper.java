package vn.edu.tdtu.ecommerce.dtos.mapper;

public interface Mapper<O, D> {
    public O mapToObject(D dto);
    public D mapToDTO(O object);
}
