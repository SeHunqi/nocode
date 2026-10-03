package com.ai.nocode.controller;

import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import com.ai.nocode.annotation.AuthCheck;
import com.ai.nocode.common.BaseResponse;
import com.ai.nocode.common.ResultUtils;
import com.ai.nocode.constant.UserConstant;
import com.ai.nocode.exception.ErrorCode;
import com.ai.nocode.exception.ThrowUtils;
import com.ai.nocode.model.dto.chathistory.ChatHistoryQueryRequest;
import com.ai.nocode.model.entity.ChatHistory;
import com.ai.nocode.model.entity.User;
import com.ai.nocode.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;
import com.ai.nocode.service.ChatHistoryService;

import java.time.LocalDateTime;

/**
 * 瀵硅瘽鍘嗗彶 鎺у埗灞傘€? *
 * @author <a href="https://github.com">绋嬪簭鍛橀奔鐨?/a>
 */
@RestController
@RequestMapping("/chatHistory")
public class ChatHistoryController {

    @Resource
    private ChatHistoryService chatHistoryService;

    @Resource
    private UserService userService;

    /**
     * 鍒嗛〉鏌ヨ鏌愪釜搴旂敤鐨勫璇濆巻鍙诧紙娓告爣鏌ヨ锛?     *
     * @param appId          搴旂敤ID
     * @param pageSize       椤甸潰澶у皬
     * @param lastCreateTime 鏈€鍚庝竴鏉¤褰曠殑鍒涘缓鏃堕棿
     * @param request        请求
     * @return 瀵硅瘽鍘嗗彶鍒嗛〉
     */
    @GetMapping("/app/{appId}")
    public BaseResponse<Page<ChatHistory>> listAppChatHistory(@PathVariable Long appId,
                                                              @RequestParam(defaultValue = "10") int pageSize,
                                                              @RequestParam(required = false) LocalDateTime lastCreateTime,
                                                              HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        Page<ChatHistory> result = chatHistoryService.listAppChatHistoryByPage(appId, pageSize, lastCreateTime, loginUser);
        return ResultUtils.success(result);
    }

    /**
     * 绠＄悊鍛樺垎椤垫煡璇㈡墍鏈夊璇濆巻鍙?     *
     * @param chatHistoryQueryRequest 鏌ヨ请求
     * @return 瀵硅瘽鍘嗗彶鍒嗛〉
     */
    @PostMapping("/admin/list/page/vo")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Page<ChatHistory>> listAllChatHistoryByPageForAdmin(@RequestBody ChatHistoryQueryRequest chatHistoryQueryRequest) {
        ThrowUtils.throwIf(chatHistoryQueryRequest == null, ErrorCode.PARAMS_ERROR);
        long pageNum = chatHistoryQueryRequest.getPageNum();
        long pageSize = chatHistoryQueryRequest.getPageSize();
        // 鏌ヨ鏁版嵁
        QueryWrapper queryWrapper = chatHistoryService.getQueryWrapper(chatHistoryQueryRequest);
        Page<ChatHistory> result = chatHistoryService.page(Page.of(pageNum, pageSize), queryWrapper);
        return ResultUtils.success(result);
    }
}
