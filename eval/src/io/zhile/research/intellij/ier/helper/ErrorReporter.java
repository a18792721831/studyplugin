/* Decompiler 6ms, total 345ms, lines 74 */
package io.zhile.research.intellij.ier.helper;

import com.intellij.openapi.diagnostic.ErrorReportSubmitter;
import com.intellij.openapi.diagnostic.IdeaLoggingEvent;
import com.intellij.openapi.diagnostic.SubmittedReportInfo;
import com.intellij.openapi.diagnostic.SubmittedReportInfo.SubmissionStatus;
import com.intellij.util.Consumer;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.net.URI;
import org.jetbrains.annotations.NotNull;

public class ErrorReporter extends ErrorReportSubmitter {
    private static final String NEW_ISSUE_URL = "https://gitee.com/pengzhile/ide-eval-resetter/issues/new";

    public String getReportActionText() {
        return "Open Browser to Submit This Issue...";
    }

    public String getPrivacyNoticeText() {
        return "Click the submit button will <b>write the stacktrace text to your clipboard</b>!<br>So that you can paste it directly when writing the issue.";
    }

    public boolean submit(IdeaLoggingEvent[] events, String additionalInfo, @NotNull Component parentComponent, @NotNull Consumer consumer) {
        if (parentComponent == null) {
            $$$reportNull$$$0(0);
        }

        if (consumer == null) {
            $$$reportNull$$$0(1);
        }

        int length = events.length - 1;
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i <= length; ++i) {
            sb.append(events[i].getThrowableText());
            if (i != length) {
                sb.append("\n\n\n");
            }
        }

        try {
            Desktop.getDesktop().browse(new URI("https://gitee.com/pengzhile/ide-eval-resetter/issues/new"));
            StringSelection selection = new StringSelection(sb.toString());
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, selection);
            consumer.consume(new SubmittedReportInfo(SubmissionStatus.NEW_ISSUE));
            return true;
        } catch (Exception var8) {
            consumer.consume(new SubmittedReportInfo(SubmissionStatus.FAILED));
            return false;
        }
    }

    // $FF: synthetic method
    private static void $$$reportNull$$$0(int var0) {
        Object[] var10001 = new Object[3];
        switch(var0) {
            case 0:
            default:
                var10001[0] = "parentComponent";
                break;
            case 1:
                var10001[0] = "consumer";
        }

        var10001[1] = "io/zhile/research/intellij/ier/helper/ErrorReporter";
        var10001[2] = "submit";
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", var10001));
    }
}
