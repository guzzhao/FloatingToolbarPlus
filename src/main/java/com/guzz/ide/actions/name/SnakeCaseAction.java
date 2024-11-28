package com.guzz.ide.actions.name;

import com.guzz.ide.constant.NameSystem;
import com.guzz.ide.utils.UiUtil;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;

public class SnakeCaseAction extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        UiUtil.actionPerformedNameStyle(e, NameSystem.SnakeCase);

    }

    @Override
    public void update(@NotNull AnActionEvent e) {
        e.getPresentation().setText("SnakeCase(aa_bb)");
    }
}
