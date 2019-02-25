package io.github.sskorol.raid.listeners;

import org.sikuli.script.App;
import org.testng.*;

import static io.github.sskorol.raid.config.BaseConfig.CONFIG;
import static java.util.Optional.ofNullable;

public class ApplicationManager implements IInvokedMethodListener {

    private static final ThreadLocal<App> APPLICATION = new ThreadLocal<>();

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
            openApp();
        }
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
            closeApp();
        }
    }

    public static App getApp() {
        return APPLICATION.get();
    }

    private static void openApp() {
        if (getApp() == null) {
            APPLICATION.set(new App(CONFIG.appName()).open(CONFIG.startupTimeout()));
        }
    }

    private static void closeApp() {
        ofNullable(getApp()).ifPresent(App::close);
        APPLICATION.remove();
    }
}
