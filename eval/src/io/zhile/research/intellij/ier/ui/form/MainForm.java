/* Decompiler 60ms, total 354ms, lines 236 */
package io.zhile.research.intellij.ier.ui.form;

import com.intellij.icons.AllIcons.Actions;
import com.intellij.icons.AllIcons.General;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.util.Disposer;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import io.zhile.research.intellij.ier.common.EvalRecord;
import io.zhile.research.intellij.ier.common.Resetter;
import io.zhile.research.intellij.ier.helper.AppHelper;
import io.zhile.research.intellij.ier.helper.PluginHelper;
import io.zhile.research.intellij.ier.helper.ResetTimeHelper;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class MainForm {
    private JPanel rootPanel;
    private JButton btnReset;
    private JList<String> lstMain;
    private JLabel lblLastResetTime;
    private JButton btnReload;
    private JLabel lblFound;
    private JLabel lblLastResetTimeLabel;
    private JCheckBox chkResetAuto;
    private JLabel lblVersion;
    private JCheckBox chkAutoLogout;
    private DialogWrapper dialogWrapper;
    private DefaultListModel<String> listModel;

    public MainForm(Disposable disposable) {
        this(disposable, (DialogWrapper)null);
    }

    public MainForm(Disposable disposable, DialogWrapper wrapper) {
        this.$$$setupUI$$$();
        this.listModel = new DefaultListModel();
        this.dialogWrapper = wrapper;
        Disposer.register(disposable, new Disposable() {
            public void dispose() {
                MainForm.this.rootPanel.removeAll();
                MainForm.this.listModel = null;
                MainForm.this.dialogWrapper = null;
            }
        });
    }

    private static void boldFont(Component component) {
        Font font = component.getFont();
        component.setFont(font.deriveFont(font.getStyle() | 1));
    }

    private static void addActionEventListener(final AbstractButton button, final ActionListener listener, Disposable disposable) {
        button.addActionListener(listener);
        Disposer.register(disposable, new Disposable() {
            public void dispose() {
                button.removeActionListener(listener);
            }
        });
    }

    public JPanel getContent(Disposable disposable) {
        Disposer.register(disposable, new Disposable() {
            public void dispose() {
                MainForm.this.rootPanel.removeAll();
            }
        });
        boldFont(this.lblFound);
        boldFont(this.lblLastResetTimeLabel);
        this.reloadLastResetTime();
        this.lblVersion.setText("v" + PluginHelper.getPluginVersion());
        this.chkAutoLogout.setSelected(Resetter.isAutoLogout());
        addActionEventListener(this.chkAutoLogout, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Resetter.setAutoLogout(MainForm.this.chkAutoLogout.isSelected());
            }
        }, disposable);
        this.chkResetAuto.setSelected(Resetter.isAutoReset());
        addActionEventListener(this.chkResetAuto, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Resetter.setAutoReset(MainForm.this.chkResetAuto.isSelected());
            }
        }, disposable);
        this.lstMain.setModel(this.listModel);
        this.reloadRecordItems();
        this.btnReload.setIcon(Actions.Refresh);
        addActionEventListener(this.btnReload, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainForm.this.reloadLastResetTime();
                MainForm.this.reloadRecordItems();
            }
        }, disposable);
        this.btnReset.setIcon(General.Reset);
        addActionEventListener(this.btnReset, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainForm.this.resetEvalItems();
            }
        }, disposable);
        if (null != this.dialogWrapper) {
            this.dialogWrapper.getRootPane().setDefaultButton(this.btnReset);
            this.rootPanel.setMinimumSize(new Dimension(640, 260));
        }

        return this.rootPanel;
    }

    private void reloadLastResetTime() {
        this.lblLastResetTime.setText(ResetTimeHelper.getLastResetTimeStr());
    }

    private void reloadRecordItems() {
        this.listModel.clear();
        Resetter.touchLicenses();
        List<EvalRecord> recordItemList = Resetter.getEvalRecords();
        Iterator var2 = recordItemList.iterator();

        while(var2.hasNext()) {
            EvalRecord record = (EvalRecord)var2.next();
            this.listModel.addElement(record.toString());
        }

    }

    private void resetEvalItems() {
        if (0 == Messages.showYesNoDialog("Your IDE will restart after reset!\nAre your sure to reset?", PluginHelper.getPluginName(), General.Reset)) {
            Resetter.touchLicenses();
            Resetter.reset(Resetter.getEvalRecords());
            ResetTimeHelper.resetLastResetTime();
            this.listModel.clear();
            if (null != this.dialogWrapper) {
                this.dialogWrapper.close(0);
            }

            AppHelper.restart();
        }
    }

    // $FF: synthetic method
    private void $$$setupUI$$$() {
        JPanel var1 = new JPanel();
        this.rootPanel = var1;
        var1.setLayout(new BorderLayout(0, 0));
        JPanel var2 = new JPanel();
        var2.setLayout(new FlowLayout(0, 5, 5));
        var1.add(var2, "North");
        JLabel var3 = new JLabel();
        this.lblLastResetTimeLabel = var3;
        var3.setText("Last Reset Time：");
        var2.add(var3);
        JLabel var4 = new JLabel();
        this.lblLastResetTime = var4;
        var4.setText("");
        var2.add(var4);
        JPanel var5 = new JPanel();
        var5.setLayout(new BorderLayout(0, 0));
        var1.add(var5, "Center");
        JPanel var6 = new JPanel();
        var6.setLayout(new FlowLayout(0, 5, 5));
        var5.add(var6, "North");
        JLabel var7 = new JLabel();
        this.lblFound = var7;
        var7.setText("Found：");
        var6.add(var7);
        JPanel var8 = new JPanel();
        var8.setLayout(new GridLayoutManager(1, 1, new Insets(5, 5, 5, 5), -1, -1, false, false));
        var5.add(var8, "Center");
        JScrollPane var9 = new JScrollPane();
        var8.add(var9, new GridConstraints(0, 0, 1, 1, 0, 3, 7, 7, (Dimension)null, (Dimension)null, (Dimension)null));
        JList var10 = new JList();
        this.lstMain = var10;
        var10.setSelectionMode(0);
        var9.setViewportView(var10);
        JPanel var11 = new JPanel();
        var11.setLayout(new BorderLayout(0, 0));
        var1.add(var11, "South");
        JPanel var12 = new JPanel();
        var12.setLayout(new FlowLayout(2, 5, 5));
        var11.add(var12, "Center");
        JCheckBox var13 = new JCheckBox();
        this.chkAutoLogout = var13;
        var13.setText("Logout when reset");
        var13.setToolTipText("Logout account when reset(Global)");
        var12.add(var13);
        JCheckBox var14 = new JCheckBox();
        this.chkResetAuto = var14;
        var14.setText("Auto reset before per restart");
        var14.setToolTipText("Auto reset before per restart(For this IDE)");
        var12.add(var14);
        JButton var15 = new JButton();
        this.btnReload = var15;
        var15.setText("Reload");
        var15.setToolTipText("Reload eval records list");
        var12.add(var15);
        JButton var16 = new JButton();
        this.btnReset = var16;
        var16.setText("Reset");
        var16.setToolTipText("Reset eval info and restart IDE");
        var12.add(var16);
        JPanel var17 = new JPanel();
        var17.setLayout(new BorderLayout(0, 0));
        var11.add(var17, "West");
        var17.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5), (String)null, 0, 0, (Font)null, (Color)null));
        JLabel var18 = new JLabel();
        this.lblVersion = var18;
        var18.setEnabled(false);
        var18.setText("v1.0.0");
        var17.add(var18, "Center");
    }

    // $FF: synthetic method
    public JComponent $$$getRootComponent$$$() {
        return this.rootPanel;
    }
}
