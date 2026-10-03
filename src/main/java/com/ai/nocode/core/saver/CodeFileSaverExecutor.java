package com.ai.nocode.core.saver;

import com.ai.nocode.ai.model.HtmlCodeResult;
import com.ai.nocode.ai.model.MultiFileCodeResult;
import com.ai.nocode.exception.BusinessException;
import com.ai.nocode.exception.ErrorCode;
import com.ai.nocode.model.enums.CodeGenTypeEnum;

import java.io.File;

/**
 * 浠ｇ爜鏂囦欢淇濆瓨鎵ц鍣? * 鏍规嵁浠ｇ爜鐢熸垚绫诲瀷鎵ц鐩稿簲鐨勪繚瀛橀€昏緫
 *
 * @author project-maintainer
 */
public class CodeFileSaverExecutor {

    private static final HtmlCodeFileSaverTemplate htmlCodeFileSaver = new HtmlCodeFileSaverTemplate();

    private static final MultiFileCodeFileSaverTemplate multiFileCodeFileSaver = new MultiFileCodeFileSaverTemplate();

    /**
     * 鎵ц浠ｇ爜淇濆瓨
     *
     * @param codeResult  代码结果对象
     * @param codeGenType 代码生成类型
     * @param appId 搴旂敤 ID
     * @return 淇濆瓨鐨勭洰褰?     */
    public static File executeSaver(Object codeResult, CodeGenTypeEnum codeGenType, Long appId) {
        return switch (codeGenType) {
            case HTML -> htmlCodeFileSaver.saveCode((HtmlCodeResult) codeResult, appId);
            case MULTI_FILE -> multiFileCodeFileSaver.saveCode((MultiFileCodeResult) codeResult, appId);
            default -> throw new BusinessException(ErrorCode.SYSTEM_ERROR, "不支持的代码生成类型: " + codeGenType);
        };
    }
}
