package io.github.sskorol.raid.utils;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import io.github.sskorol.raid.screens.Screen;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

import java.util.Optional;

public class ScreenCacheProvider implements IInvokedMethodListener {

    private static final ThreadLocal<Cache<Class<? extends Screen>, Screen>> SCREEN_CACHE = new ThreadLocal<>();

    public static Optional<Cache<Class<? extends Screen>, Screen>> getScreenCache() {
        return Optional.ofNullable(SCREEN_CACHE.get());
    }

    @Override
    public void beforeInvocation(final IInvokedMethod method, final ITestResult testResult) {
        if (method.isTestMethod()) {
            createScreenCache();
        }
    }

    @Override
    public void afterInvocation(final IInvokedMethod method, final ITestResult testResult) {
        if (method.isTestMethod()) {
            cleanupScreenCache();
        }
    }

    private void createScreenCache() {
        SCREEN_CACHE.set(Caffeine.newBuilder().build());
    }

    private void cleanupScreenCache() {
        getScreenCache().ifPresent(Cache::invalidateAll);
        SCREEN_CACHE.remove();
    }
}
