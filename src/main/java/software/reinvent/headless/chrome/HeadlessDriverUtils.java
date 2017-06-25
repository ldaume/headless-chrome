package software.reinvent.headless.chrome;

import com.assertthat.selenium_shutterbug.core.PageSnapshot;
import com.assertthat.selenium_shutterbug.core.Shutterbug;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import static com.assertthat.selenium_shutterbug.utils.web.ScrollStrategy.BOTH_DIRECTIONS;

/**
 * Created on 05.01.2017.
 *
 * @author <a href="mailto:lenny@reinvent.software">Leonard Daume</a>
 */
public class HeadlessDriverUtils {
    public static void takeFullScreenshot(WebDriver webDriver, File pngFile) throws IOException {
        takeFullScreenshot(webDriver, pngFile, null);
    }

    public static void takeFullScreenshot(WebDriver webDriver, File pngFile, By... highlights)
            throws IOException {
        final PageSnapshot pageSnapshot = Shutterbug.shootPage(webDriver, BOTH_DIRECTIONS);
        if (ArrayUtils.isNotEmpty(highlights)) {
            Arrays.stream(highlights)
                    .map(webDriver::findElements)
                    .flatMap(Collection::stream)
                    .forEach(pageSnapshot::highlight);
        }
        FileUtils.forceMkdirParent(pngFile);
        pageSnapshot.withName(pngFile.getName());
        pageSnapshot.save(pngFile.getParent());
        FileUtils.deleteQuietly(pngFile);
        FileUtils.moveFile(new File(pngFile.getPath() + ".png"), pngFile);
    }
}
