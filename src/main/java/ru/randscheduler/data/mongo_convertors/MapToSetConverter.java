package ru.randscheduler.data.mongo_convertors;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mongodb.DBObject;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.converters.MapOfValuesConverter;
import org.mongodb.morphia.converters.TypeConverter;
import org.mongodb.morphia.mapping.MappedField;
import org.mongodb.morphia.mapping.cache.EntityCache;
import ru.randscheduler.data.annotations.ConvertMapToSet;
import ru.randscheduler.data.annotations.MapKey;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * Created by dimaMJ on 05.02.2018
 */
public class MapToSetConverter extends TypeConverter {
    private MapOfValuesConverter defConverter = new MapOfValuesConverter();
    private final Datastore datastore;
    private EntityCache entityCache;
    private final Map<Class, Field> cacheFields = Maps.newConcurrentMap();
    private final Map<Class, Method> cacheMethods = Maps.newConcurrentMap();

    public MapToSetConverter(Datastore datastore) {
        super(Map.class);
        this.datastore = datastore;
    }

    public void init() {
        defConverter.setMapper(this.getMapper());
        this.entityCache = getMapper().createEntityCache();
    }

    @Override
    public Object encode(Object value, MappedField info) {
        ConvertMapToSet convertMapToSet = null;
        if (info != null) convertMapToSet = info.getField().getAnnotation(ConvertMapToSet.class);

        if (convertMapToSet != null && value != null) {
            Map<?, ?> map = (Map) value;
            cachePointWithMapKey(info);

            List<Object> res = Lists.newArrayList();
            for (Object o : map.values()) {
                DBObject dbObject = getMapper().toDBObject(o);
                dbObject.removeField("className");
                res.add(dbObject);
            }

            return res.isEmpty() ? null : res;
        } else {
            return defConverter.encode(value, info);
        }

    }

    @Override
    @SuppressWarnings("ALL")
    public Object decode(Class<?> aClass, Object o, MappedField info) {
        ConvertMapToSet convertMapToSet = null;
        if (info != null) convertMapToSet = info.getField().getAnnotation(ConvertMapToSet.class);

        if (convertMapToSet != null) {
            cachePointWithMapKey(info);
            Class valueClass = info.getTypeParameters().get(1).getType();

            Map res = getMapper().getOptions().getObjectFactory().createMap(info);
            List<Object> fromDb = (List) o;

            for (Object obj : fromDb) {
                try {
                    Object val = getMapper().fromDBObject(datastore, valueClass, (DBObject) obj, entityCache);
                    res.put(getKeyForMap(valueClass, val), val);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            return res;
        } else {
            return defConverter.decode(aClass, o, info);
        }
    }

    private void cachePointWithMapKey(MappedField mf) {
        Class valueClass = mf.getTypeParameters().get(1).getType();
        if (cacheFields.containsKey(valueClass) || cacheMethods.containsKey(valueClass)) {
            return;
        }

        for (Field field : valueClass.getDeclaredFields()) {
            if (field.getAnnotation(MapKey.class) != null) {
                field.setAccessible(true);
                cacheFields.put(valueClass, field);
                return;
            }
        }

        for (Method method : valueClass.getDeclaredMethods()) {
            if (method.getAnnotation(MapKey.class) != null) {
                method.setAccessible(true);
                cacheMethods.put(valueClass, method);
                return;
            }
        }

        throw new RuntimeException("Map values must contains @MapKey on field or method to use @ConvertMapToSet " +
                "(class - " + valueClass + ")");
    }


    private Object getKeyForMap(Class valueClass, Object val) {
        Field f;
        Method m;

        try {
            if ((f = cacheFields.get(valueClass)) != null) {
                return f.get(val);
            } else if ((m = cacheMethods.get(valueClass)) != null) {
                return m.invoke(val);
            } else {
                throw new RuntimeException("Map values must contains @MapKey on field or method to use @ConvertMapToSet");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
