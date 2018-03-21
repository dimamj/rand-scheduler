package ru.randscheduler.repository;

import org.mongodb.morphia.AdvancedDatastore;
import org.mongodb.morphia.query.Query;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by dimaMJ on 14.03.2018
 */
abstract class DefaultRepository<ID, T> {

    private final Class<T> entityClass;
    private String collection;
    final AdvancedDatastore datastore;

    DefaultRepository(AdvancedDatastore datastore) {
        this(datastore, null);
    }

    @SuppressWarnings("ALL")
    public DefaultRepository(AdvancedDatastore datastore, String collection) {
        this.datastore = datastore;
        this.entityClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[1];
    }

    public T get(ID id) {
        return createQuery().field("_id").equal(id).get();
    }

    public boolean exists(ID id) {
        return createQuery().field("_id").equal(id).getKey() != null;
    }

    public void save(T entity) {
        if (collection == null) datastore.save(entity);
        else datastore.save(collection, entity);
    }

    public T get(ID id, Consumer<Query<T>> queryConsumer) {
        Query<T> q = createQuery().field("_id").equal(id);
        queryConsumer.accept(q);
        return q.get();
    }

    protected Query<T> createQuery() {
        if (collection == null) return datastore.createQuery(entityClass);
        else return datastore.createQuery(collection, entityClass);
    }
}
