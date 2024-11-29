package com.guzz.ide.actions.name;

import com.guzz.ide.constant.NameSystem;
import com.guzz.ide.utils.UiUtil;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.Presentation;
import org.jetbrains.annotations.NotNull;

public class SnakePascalCaseAction extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        UiUtil.actionPerformedNameStyle(e, NameSystem.SnakePascalCase);

    }





}
