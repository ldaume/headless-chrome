package software.reinvent.headless.chrome.provider;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.typesafe.config.Config;
import org.openqa.selenium.chrome.ChromeDriver;
import software.reinvent.headless.chrome.HeadlessDriver;

/**
 * Created on 16.04.2017.
 *
 * @author <a href="mailto:lenny@reinvent.software">Leonard Daume</a>
 */
public class HeadlessChromeProvider implements Provider<ChromeDriver> {
    private Config config;

    @Inject
    public HeadlessChromeProvider(Config config) {
        this.config = config;
    }

    @Override
    public ChromeDriver get() {
        return new HeadlessDriver(config).getChromeDriver();
    }
}
