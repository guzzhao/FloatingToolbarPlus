package com.guzz.ide.floatingtoolbarplus;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.Messages;

public class BarMain extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        Messages.showMessageDialog(e.getProject(), "You clicked the custom toolbar button!", "Info", Messages.getInformationIcon());

    }
}
