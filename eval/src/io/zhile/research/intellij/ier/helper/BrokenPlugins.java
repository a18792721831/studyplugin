/* Decompiler 4ms, total 296ms, lines 38 */
package io.zhile.research.intellij.ier.helper;

import com.intellij.openapi.application.PathManager;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.util.io.FileUtil;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BrokenPlugins {
    private static final Logger LOG = Logger.getInstance(BrokenPlugins.class);

    public static void fix() {
        String content = "[]";
        String fileName = "brokenPlugins.json";
        Path brokenPluginsPath = Paths.get(PathManager.getPluginsPath(), fileName);
        File brokenPluginsFile = brokenPluginsPath.toFile();
        if (brokenPluginsFile.exists() && (long)content.length() != brokenPluginsFile.length()) {
            File tmp = null;

            try {
                tmp = File.createTempFile(fileName, (String)null);
                FileUtil.writeToFile(tmp, content);
                FileUtil.copy(tmp, brokenPluginsFile);
            } catch (IOException var9) {
                LOG.warn("Set broken plugins failed", var9);
            } finally {
                if (null != tmp) {
                    FileUtil.delete(tmp);
                }

            }

        }
    }
}
