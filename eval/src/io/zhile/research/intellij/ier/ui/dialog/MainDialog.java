//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package io.zhile.research.intellij.ier.ui.dialog;

import com.intellij.openapi.ui.DialogWrapper;
import io.zhile.research.intellij.ier.ui.form.MainForm;
import javax.swing.JComponent;

public class MainDialog extends DialogWrapper {
    public MainDialog(String title) {
        super(true);
        this.init();
        this.setTitle(title);
    }

    protected JComponent createCenterPanel() {
        MainForm mainForm = new MainForm(this.getDisposable(), this);
        return mainForm.getContent(this.getDisposable());
    }

    protected JComponent createSouthPanel() {
        return null;
    }
}
