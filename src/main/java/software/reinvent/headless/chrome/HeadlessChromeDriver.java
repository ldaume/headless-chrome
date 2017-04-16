package software.reinvent.headless.chrome;

import com.google.inject.Inject;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import static com.assertthat.selenium_shutterbug.core.Shutterbug.shootPage;
import static com.assertthat.selenium_shutterbug.utils.web.ScrollStrategy.BOTH_DIRECTIONS;

/**
 * Created on 05.01.2017.
 *
 * @author <a href="mailto:lenny@reinvent.software">Leonard Daume</a>
 */
public class HeadlessChromeDriver {
    private final ChromeDriver driver;

    @Inject
    public HeadlessChromeDriver(ChromeDriver driver) {
        this.driver = driver;
    }

    public ChromeDriver getDriver() {
        return driver;
    }

    public File screenshot() {
        final String path = "/tmp/" + UUID.randomUUID().toString();
        shootPage(driver, BOTH_DIRECTIONS).save(path);
        return new File(path).listFiles()[0];
    }

    public File screenshot(File pngFile) throws IOException {
        final File screenshot = screenshot();
        FileUtils.forceMkdirParent(pngFile);
        FileUtils.copyFile(screenshot, pngFile);
        FileUtils.forceDelete(screenshot.getParentFile());
        return pngFile;
    }
}
