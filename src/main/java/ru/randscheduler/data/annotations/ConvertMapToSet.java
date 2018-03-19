package ru.randscheduler.data.annotations;

import org.mongodb.morphia.annotations.Property;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by dimaMJ on 22.01.2018
 * Чтобы включить конвертацию Map в коллекцию при сохранении в монгу,
 * нужно повешать на поле c Map аннотацию @ConvertMapToSet и @Property и
 * сделать чтобы у значения в мапе было поле или get метод помеченные аннотацией MapKey
 *
 * @see MapKey
 * @see Property
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface ConvertMapToSet {
}
