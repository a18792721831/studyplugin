/* Decompiler 2ms, total 334ms, lines 25 */
package io.zhile.research.intellij.ier.helper;

import com.intellij.openapi.application.ApplicationInfo;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.util.BuildNumber;

public class AppHelper {
    public static void restart() {
        ApplicationManager.getApplication().invokeLater(new Runnable() {
            public void run() {
                ApplicationManager.getApplication().restart();
            }
        });
    }

    public static String getProductName() {
        String productName = Constants.IDE_NAME;
        return "IDEA".equals(productName) ? productName.toLowerCase() : productName;
    }

    public static BuildNumber getBuildNumber() {
        return ApplicationInfo.getInstance().getBuild();
    }
}
