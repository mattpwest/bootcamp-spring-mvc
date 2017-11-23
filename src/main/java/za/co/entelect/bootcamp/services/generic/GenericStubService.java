package za.co.entelect.bootcamp.services.generic;

import za.co.entelect.bootcamp.domain.generic.IdentifiableDomain;

import java.util.HashMap;
import java.util.Map;

public abstract class GenericStubService<TYPE extends IdentifiableDomain<ID>, ID>
        implements ReadService<TYPE, ID>, WriteService<TYPE, ID> {

    private Map<ID, TYPE> items;

    protected GenericStubService() {
        items = new HashMap<>();
    }

    @Override
    public TYPE findOne(ID id) {
        return items.get(id);
    }

    @Override
    public Iterable<TYPE> findAll() {
        return items.values();
    }

    @Override
    public TYPE save(TYPE item) {
        if (item == null) return null;

        items.put(item.getId(), item);

        return item;
    }

    @Override
    public void delete(TYPE item) {
        if (item == null) return;

        items.remove(item.getId());
    }

    @Override
    public void delete(ID id) {
        items.remove(id);
    }
}
