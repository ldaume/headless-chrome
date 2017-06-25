package software.reinvent.headless.chrome;

import com.google.inject.Inject;
import com.google.inject.Injector;
import org.jukito.JukitoRunner;
import org.jukito.UseModules;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.chrome.ChromeDriver;
import software.reinvent.headless.chrome.modules.ChromeDriverTestModule;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created on 05.01.2017.
 *
 * @author <a href="mailto:lenny@reinvent.software">Leonard Daume</a>
 */
@RunWith(JukitoRunner.class)
@UseModules({ChromeDriverTestModule.class})
public class HeadlessDriverUtilsTest {


    private ChromeDriver driver;

    @Inject
    private Injector injector;


    @Before
    public void setUp() throws Exception {
        driver = injector.getInstance(ChromeDriver.class);
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void testChromeDriver() throws Exception {
        assertThat(driver).isNotNull();
    }

    @Test
    public void testExpectedElement() throws Exception {
        driver.get("https://reinvent-software.de");
        final String text = driver.findElementByXPath(".//*[@id='team']//p[@class='text-muted']").getText();
        assertThat(text).isEqualTo("Head of Development");
    }

    @Test
    public void testScreenshot() throws Exception {
        driver.get("https://reinvent-software.de");
        final File pngFile = new File("/tmp/screenshot.png");
        HeadlessDriverUtils.takeFullScreenshot(driver, pngFile);
        assertThat(pngFile).exists();
        assertThat(pngFile.length()).isGreaterThan(1_000_000);
    }


}

