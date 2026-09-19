/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.audit.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Service
public class AuditFindingClosureService {
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public Result assess(Request request) {
        var blockers = new ArrayList<String>();
        var actions = new ArrayList<String>();
        if (request.findingId() == null || request.findingId().isBlank()) blockers.add("审计发现编号不能为空");
        if (!request.remediationEvidenceComplete()) blockers.add("整改证据不完整");
        if (!request.controlRetested()) blockers.add("整改后控制未重新测试");
        if (!request.residualRiskAccepted()) blockers.add("剩余风险未正式接受");
        if (!request.auditorIndependent()) blockers.add("整改执行与审计复核未保持独立");
        if (!request.managementApproved()) blockers.add("管理层关闭审批缺失");
        if (!request.attachmentsHashed()) blockers.add("审计附件完整性摘要缺失");
        if (!request.auditReady()) blockers.add("发现关闭审计轨迹不完整");
        if (!request.issueOwnerApproved()) actions.add("取得问题责任人确认");
        if (!request.repeatFindingReviewed()) actions.add("完成重复问题分析");
        if (!request.dueDateCurrent()) actions.add("审批整改期限调整");
        var decision = !blockers.isEmpty() ? Decision.BLOCKED : actions.isEmpty() ? Decision.CLOSE : Decision.REVIEW;
        return new Result(decision, List.copyOf(blockers), List.copyOf(actions));
    }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public enum Decision { CLOSE, REVIEW, BLOCKED }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record Request(String findingId, boolean remediationEvidenceComplete, boolean controlRetested,
                          boolean residualRiskAccepted, boolean issueOwnerApproved,
                          boolean auditorIndependent, boolean managementApproved,
                          boolean repeatFindingReviewed, boolean dueDateCurrent,
                          boolean attachmentsHashed, boolean auditReady) {}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record Result(Decision decision, List<String> blockers, List<String> actions) {}
}
