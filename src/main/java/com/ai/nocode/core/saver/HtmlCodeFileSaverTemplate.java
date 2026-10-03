package com.ai.nocode.core.saver;

import cn.hutool.core.util.StrUtil;
import com.ai.nocode.ai.model.HtmlCodeResult;
import com.ai.nocode.exception.BusinessException;
import com.ai.nocode.exception.ErrorCode;
import com.ai.nocode.model.enums.CodeGenTypeEnum;

/**
 * HTML浠ｇ爜鏂囦欢淇濆瓨鍣? *
 * @author project-maintainer
 */
public class HtmlCodeFileSaverTemplate extends CodeFileSaverTemplate<HtmlCodeResult> {

    @Override
    protected CodeGenTypeEnum getCodeType() {
        return CodeGenTypeEnum.HTML;
    }

    @Override
    protected void saveFiles(HtmlCodeResult result, String baseDirPath) {
        writeToFile(baseDirPath, "index.html", result.getHtmlCode());
    }

    @Override
    protected void validateInput(HtmlCodeResult result) {
        super.validateInput(result);
        // HTML 代码不能为空
        if (StrUtil.isBlank(result.getHtmlCode())) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "HTML 代码不能为空");
        }
    }
}
