package software.reinvent.headless.chrome;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.typesafe.config.Config;
import org.jukito.JukitoRunner;
import org.jukito.UseModules;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.chrome.ChromeDriver;
import software.reinvent.commons.config.ConfigProvider;
import software.reinvent.headless.chrome.modules.ChromeDriverTestModule;
import software.reinvent.headless.chrome.provider.HeadlessChromeProvider;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created on 05.01.2017.
 *
 * @author <a href="mailto:lenny@reinvent.software">Leonard Daume</a>
 */
@RunWith(JukitoRunner.class)
@UseModules({ChromeDriverTestModule.class})
public class HeadlessChromeDriverTest {

    @Inject
    private HeadlessChromeDriver headlessChromeDriver;

    @Test
    public void testChromeDriver() throws Exception {
        assertThat(headlessChromeDriver.getDriver()).isNotNull();
    }

    @Test
    public void testExpectedElement() throws Exception {
        final ChromeDriver driver = headlessChromeDriver.getDriver();
        driver.get("https://reinvent-software.de");
        final String text = driver.findElementByXPath(".//*[@id='team']//p[@class='text-muted']").getText();
        assertThat(text).isEqualTo("Head of Development");
    }
}
