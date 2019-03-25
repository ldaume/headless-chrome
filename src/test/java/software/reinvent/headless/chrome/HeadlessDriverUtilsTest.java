package software.reinvent.headless.chrome;

import static org.assertj.core.api.Assertions.assertThat;

import com.google.inject.Inject;
import com.google.inject.Injector;
import java.io.File;
import name.falgout.jeffrey.testing.junit.guice.GuiceExtension;
import name.falgout.jeffrey.testing.junit.guice.IncludeModule;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.chrome.ChromeDriver;
import software.reinvent.headless.chrome.modules.ChromeDriverTestModule;

/**
 * Created on 05.01.2017.
 *
 * @author <a href="mailto:lenny@reinvent.software">Leonard Daume</a>
 */
@ExtendWith(GuiceExtension.class)
@IncludeModule(ChromeDriverTestModule.class)
public class HeadlessDriverUtilsTest {


  private ChromeDriver driver;


  @BeforeEach
  public void setUp(Injector injector) throws Exception {
    driver = injector.getInstance(ChromeDriver.class);
  }

  @AfterEach
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
    final String text = driver.findElementByXPath(".//*[@id='team']//p[@class='text-muted']")
        .getText();
    assertThat(text)
        .isEqualTo("Software Craftsman | DevOp | Agile Evangelist | Responsible Leader");
  }

  @Test
  public void testScreenshot() throws Exception {
    driver.get("https://reinvent-software.de");
    final File pngFile = new File("/tmp/screenshot.png");
    HeadlessDriverUtils.takeFullScreenshot(driver, pngFile);
    assertThat(pngFile).exists();
    assertThat(pngFile.length()).isGreaterThan(1_000_000);
  }


  @Test
  public void testUserAgent() throws Exception {
    driver.get("http://www.useragents.com/");
    final String text = driver.findElementByXPath("./html/body/center/p").getText();
    assertThat(text).contains(
        "Mozilla/5.0 (X11; Linux i686; rv:17.0.1) Gecko/20100101 Arch Linux Firefox/17.0.1");
  }
}

