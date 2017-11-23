package za.co.entelect.bootcamp.services.generic;

import za.co.entelect.bootcamp.domain.generic.IdentifiableDomain;

public interface ReadService<TYPE extends IdentifiableDomain<ID>, ID> {

    TYPE findOne(ID id);

    Iterable<TYPE> findAll();
    // Pageable<TYPE> findAll();
}
