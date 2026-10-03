package com.ai.nocode.core.saver;

import cn.hutool.core.util.StrUtil;
import com.ai.nocode.ai.model.MultiFileCodeResult;
import com.ai.nocode.exception.BusinessException;
import com.ai.nocode.exception.ErrorCode;
import com.ai.nocode.model.enums.CodeGenTypeEnum;

/**
 * 多文件代码保存器
 *
 * @author project-maintainer
 */
public class MultiFileCodeFileSaverTemplate extends CodeFileSaverTemplate<MultiFileCodeResult> {

    @Override
    protected CodeGenTypeEnum getCodeType() {
        return CodeGenTypeEnum.MULTI_FILE;
    }

    @Override
    protected void saveFiles(MultiFileCodeResult result, String baseDirPath) {
        // 保存 HTML 文件
        writeToFile(baseDirPath, "index.html", result.getHtmlCode());
        // 保存 CSS 文件
        writeToFile(baseDirPath, "style.css", result.getCssCode());
        // 保存 JavaScript 文件
        writeToFile(baseDirPath, "script.js", result.getJsCode());
    }

    @Override
    protected void validateInput(MultiFileCodeResult result) {
        super.validateInput(result);
        // 鑷冲皯瑕佹湁 HTML 浠ｇ爜锛孋SS 鍜?JS 鍙互涓虹┖
        if (StrUtil.isBlank(result.getHtmlCode())) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "HTML浠ｇ爜鍐呭涓嶈兘涓虹┖");
        }
    }
}