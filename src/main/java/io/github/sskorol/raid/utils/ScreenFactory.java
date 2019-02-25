package io.github.sskorol.raid.utils;

import io.github.sskorol.raid.core.BaseElement;
import io.github.sskorol.raid.core.Image;
import io.github.sskorol.raid.screens.Screen;
import lombok.experimental.UtilityClass;
import lombok.val;
import one.util.streamex.StreamEx;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Optional;

import static io.github.sskorol.raid.utils.ScreenCacheProvider.getScreenCache;
import static org.joor.Reflect.on;

@UtilityClass
public class ScreenFactory {

    @SuppressWarnings("unchecked")
    public static <T extends Screen> T at(final Class<T> screenClass) {
        return (T) getScreenCache()
            .map(cache -> cache.get(screenClass, ScreenFactory::initScreen))
            .orElseThrow(() -> new IllegalStateException("Unable to cache " + screenClass));
    }

    @SuppressWarnings("unchecked")
    private static <T extends Screen> T initScreen(final Class<T> screenClass) {
        final T screen = on(screenClass).create().get();
        StreamEx.iterate(screenClass, cl -> (Class<T>) cl.getSuperclass())
            .takeWhile(cl -> !cl.equals(Object.class))
            .flatMap(cl -> StreamEx.of(cl.getDeclaredFields()).filter(field -> field.isAnnotationPresent(Image.class)))
            .forEach(field -> on(screen).set(field.getName(), initElement(field)));
        return screen;
    }

    private static <T extends BaseElement> T initElement(final Field field) {
        return on(field.getType()).create(getArgs(field)).get();
    }

    private static Object[] getArgs(final Field field) {
        return Optional.ofNullable(field.getDeclaredAnnotation(Image.class))
            .map(annotation ->
                StreamEx.of(annotation.annotationType().getDeclaredMethods())
                    .sortedBy(Method::getName)
                    .map(method -> on(annotation).call(method.getName()).get())
                    .toArray())
            .orElseGet(() -> new Object[0]);
    }
}
