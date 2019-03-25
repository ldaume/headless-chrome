package software.reinvent.headless.chrome;

import static com.google.common.io.Resources.getResource;
import static java.lang.System.setProperty;
import static org.apache.commons.io.FileUtils.copyInputStreamToFile;
import static org.apache.commons.lang3.StringUtils.substringAfter;
import static org.apache.commons.lang3.StringUtils.substringBefore;
import static org.apache.commons.lang3.math.NumberUtils.toInt;
import static org.openqa.selenium.chrome.ChromeOptions.CAPABILITY;

import com.typesafe.config.Config;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.LoggerFactory;
import software.reinvent.commons.config.ConfigLoader;

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
        final String osName = configToUse.getString("os.name");
        final String driverBinaryName;
        if (StringUtils.containsIgnoreCase(osName, "windows")) {
          driverBinaryName = "chromedriver_win32-2.33.exe";
        } else if (StringUtils.containsIgnoreCase(osName, "mac")) {
          driverBinaryName = "chromedriver_mac-2.33";
        } else {
          driverBinaryName = "chromedriver_linux_64-73.0.3683.68";
        }

        final File tempDriver = new File(FileUtils.getTempDirectory(), driverBinaryName);

        if (!tempDriver.exists()) {
          copyInputStreamToFile(getResource(this.getClass(), driverBinaryName).openStream(),
              tempDriver);
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
    final Dimension windowSize;
    if (configToUse.hasPath("chrome.window.size")) {
      final String windowSizeString = configToUse.getString("chrome.window.size");
      final int width = toInt(substringBefore(windowSizeString, ",").trim());
      final int height = toInt(substringAfter(windowSizeString, ",").trim());
      if (width > 1 && height > 1) {
        windowSize = new Dimension(width, height);
      } else {
        LoggerFactory.getLogger(HeadlessDriver.class)
            .warn("Width {} and height {} not greater than 1. WIll use default dimension.", width,
                height);
        windowSize = new Dimension(1920, 1200);
      }
    } else {
      windowSize = new Dimension(1920, 1200);
    }
    if (configToUse.hasPath("webdriver.user.agent")) {
      chromeOptions.addArguments("--user-agent=" + configToUse.getString("webdriver.user.agent"));
    }
    if (!configToUse.hasPath("chrome.headless") || (configToUse.hasPath("chrome.headless")
        && configToUse.getBoolean("chrome.headless"))) {
      chromeOptions.addArguments("--headless");
    }
    chromeOptions
        .addArguments("--disable-gpu", "--no-sandbox", "--incognito");

    final DesiredCapabilities capabilities = DesiredCapabilities.chrome();
    capabilities.setCapability(CAPABILITY, chromeOptions);

    try {
      chromeDriver = new ChromeDriver(capabilities);
      chromeDriver.manage().window().setSize(windowSize);
    } catch (Exception e) {
      throw e;
    }
  }

  public ChromeDriver getChromeDriver() {
    return chromeDriver;
  }
}
