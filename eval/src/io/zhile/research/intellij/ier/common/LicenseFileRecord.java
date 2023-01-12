/* Decompiler 8ms, total 299ms, lines 87 */
package io.zhile.research.intellij.ier.common;

import com.intellij.openapi.util.io.FileUtil;
import io.zhile.research.intellij.ier.helper.DateTime;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;

public class LicenseFileRecord implements EvalRecord {
    private final String type = "LICENSE";
    private final File file;
    private String expireDate;

    public LicenseFileRecord(File file) {
        this.file = file;

        try {
            DataInputStream dis = new DataInputStream(new FileInputStream(file));
            Throwable var3 = null;

            try {
                this.expireDate = DateTime.DF_DATETIME.format(new Date(~dis.readLong() + 2592000000L));
            } catch (Throwable var13) {
                var3 = var13;
                throw var13;
            } finally {
                if (dis != null) {
                    if (var3 != null) {
                        try {
                            dis.close();
                        } catch (Throwable var12) {
                            var3.addSuppressed(var12);
                        }
                    } else {
                        dis.close();
                    }
                }

            }
        } catch (Exception var15) {
            this.expireDate = "ERROR";
        }

    }

    public static void touch(File file) throws Exception {
        DataOutputStream dos = new DataOutputStream(new FileOutputStream(file));
        Throwable var2 = null;

        try {
            dos.writeLong(~System.currentTimeMillis());
        } catch (Throwable var11) {
            var2 = var11;
            throw var11;
        } finally {
            if (dos != null) {
                if (var2 != null) {
                    try {
                        dos.close();
                    } catch (Throwable var10) {
                        var2.addSuppressed(var10);
                    }
                } else {
                    dos.close();
                }
            }

        }

    }

    public void reset() throws Exception {
        if (!FileUtil.delete(this.file)) {
            throw new Exception("Remove LICENSE failed: " + this.file.getAbsolutePath());
        } else {
            touch(this.file);
        }
    }

    public String toString() {
        return "LICENSE: " + this.file.getName() + ", UNTIL: " + this.expireDate;
    }
}
