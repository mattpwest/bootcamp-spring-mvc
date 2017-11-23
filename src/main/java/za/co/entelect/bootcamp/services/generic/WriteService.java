package za.co.entelect.bootcamp.services.generic;

import za.co.entelect.bootcamp.domain.generic.IdentifiableDomain;

public interface WriteService<TYPE extends IdentifiableDomain<ID>, ID> {

    TYPE save(TYPE object);

    void delete(TYPE object);
    void delete(ID id);
}
