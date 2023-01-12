/* Decompiler 2ms, total 283ms, lines 24 */
package io.zhile.research.intellij.ier.common;

import com.intellij.openapi.util.io.FileUtil;
import java.io.File;

public class NormalFileRecord implements EvalRecord {
    private final String type = "FILE";
    private final File file;

    public NormalFileRecord(File file) {
        this.file = file;
    }

    public void reset() throws Exception {
        if (!FileUtil.delete(this.file)) {
            throw new Exception("Remove FILE failed: " + this.file.getAbsolutePath());
        }
    }

    public String toString() {
        return "FILE: " + this.file.getName();
    }
}
