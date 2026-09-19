/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.audit.controller;

import cn.zhuatech.audit.common.ApiResponse;
import cn.zhuatech.audit.service.AuditFindingClosureService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@RestController
@RequestMapping("/api/enterprise/audit")
public class AuditFindingClosureController {
    private final AuditFindingClosureService service;
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public AuditFindingClosureController(AuditFindingClosureService service) { this.service = service; }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @PostMapping("/finding-closure")
    public ApiResponse<?> assess(@RequestBody AuditFindingClosureService.Request request) {
        return ApiResponse.ok(service.assess(request));
    }
}
