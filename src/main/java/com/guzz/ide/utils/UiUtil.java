package com.guzz.ide.utils;

import com.guzz.ide.constant.NameSystem;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.popup.JBPopup;
import com.intellij.openapi.ui.popup.JBPopupFactory;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class UiUtil {

    public static void addPopup(Project project, String text) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel(text), BorderLayout.CENTER);
        JBPopup jbPopup = JBPopupFactory.getInstance().createComponentPopupBuilder(panel, null).createPopup();
        jbPopup.showCenteredInCurrentWindow(project);
    }


    public static void actionPerformedNameStyle(AnActionEvent e, NameSystem name) {
        // 获取当前编辑器
        Editor editor = e.getData(CommonDataKeys.EDITOR);
        Project project = e.getProject();
        if (Objects.isNull(project)) {
            return;
        }

        if (editor != null) {
            // 获取选择区域
            SelectionModel selectionModel = editor.getSelectionModel();

            // 获取选中的文本
            String selectedText = selectionModel.getSelectedText();

            if (StrUtil.isBlank(selectedText) || !StrUtil.isValidVariableName(selectedText)) {
                addPopup(project, "this is not variable");
            } else {
                String newText = switch (name) {
                    case CamelCase -> StrUtil.toCamelCase(StrUtil.cutVariateToArray(selectedText));
                    case PascalCase -> StrUtil.toPascalCase(StrUtil.cutVariateToArray(selectedText));
                    case SnakeCase -> StrUtil.toSnakeCase(StrUtil.cutVariateToArray(selectedText));
                    case SnakePascalCase -> StrUtil.toSnakePascalCase(StrUtil.cutVariateToArray(selectedText));
                    case SnakeUpperCase -> StrUtil.toSnakeUpperCase(StrUtil.cutVariateToArray(selectedText));
                    case LowerCase -> selectedText.toLowerCase();
                    case UpperCase -> selectedText.toUpperCase();
                };

                WriteCommandAction.runWriteCommandAction(project, () -> {
                    int start = selectionModel.getSelectionStart();
                    int end = selectionModel.getSelectionEnd();
                    editor.getDocument().replaceString(start, end, newText);
                });

            }

        }
    }

}
