/* Decompiler 6ms, total 317ms, lines 28 */
package io.zhile.research.intellij.ier.common;

import com.intellij.ide.plugins.IdeaPluginDescriptor;
import com.intellij.ide.plugins.PluginManager;
import java.util.List;

public abstract class PluginRecord implements EvalRecord {
    public void test(List<EvalRecord> list) {
        IdeaPluginDescriptor[] var2 = PluginManager.getPlugins();
        int var3 = var2.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            IdeaPluginDescriptor descriptor = var2[var4];
            if (descriptor.getName().equals(this.getName())) {
                list.add(this);
                break;
            }
        }

    }

    public abstract String getName();

    public String toString() {
        return "PLUGIN: " + this.getName();
    }
}
