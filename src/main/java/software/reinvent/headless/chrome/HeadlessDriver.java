package software.reinvent.headless.chrome;

import com.typesafe.config.Config;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import software.reinvent.commons.config.ConfigLoader;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import static com.google.common.io.Resources.getResource;
import static java.lang.System.setProperty;
import static org.apache.commons.io.FileUtils.copyInputStreamToFile;
import static org.openqa.selenium.chrome.ChromeOptions.CAPABILITY;

/**
 * Created on 25.06.2017.
 *
 * @author <a href="mailto:lenny@reinvent.software">Leonard Daume</a>
 */
public class HeadlessDriver {


    private final ChromeDriver chromeDriver;

    public HeadlessDriver(Config config) {
        Config configToUse = Optional.ofNullable(config).orElse(ConfigLoader.load());

        if (configToUse.hasPath("webdriver.chrome.driver")) {
            setProperty("webdriver.chrome.driver", configToUse.getString("webdriver.chrome.driver"));
        } else {
            try {
                final String osName = config.getString("os.name");
                final String driverBinaryName;
                if (StringUtils.containsIgnoreCase(osName, "windows")) {
                    driverBinaryName = "chromedriver_win32-2.30";
                } else if (StringUtils.containsIgnoreCase(osName, "mac")) {
                    driverBinaryName = "chromedriver_mac-2.30";
                } else {
                    driverBinaryName = "chromedriver_linux64-2.30";
                }

                final File tempDriver = new File("/tmp/" + driverBinaryName);
                if (!tempDriver.exists()) {
                    copyInputStreamToFile(getResource(this.getClass(), driverBinaryName).openStream(), tempDriver);
                    tempDriver.setExecutable(true);
                }
                setProperty("webdriver.chrome.driver", tempDriver.getPath());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        }

        final ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setBinary(configToUse.hasPath("webdriver.chrome.binary")
                ? configToUse.getString("webdriver.chrome.binary")
                : "/usr/bin/google-chrome-unstable");
        final String windowSize;
        if (configToUse.hasPath("chrome.window.size")) {
            windowSize = configToUse.getString("chrome.window.size");
        } else {
            windowSize = "1920,1200";
        }
        chromeOptions.addArguments("--headless", "--disable-gpu", "--no-sandbox", "--incognito", "window-size=" + windowSize);

        final DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(CAPABILITY, chromeOptions);

        try {
            chromeDriver = new ChromeDriver(capabilities);
        } catch (Exception e) {
            throw e;
        }
    }

    public ChromeDriver getChromeDriver() {
        return chromeDriver;
    }
}
