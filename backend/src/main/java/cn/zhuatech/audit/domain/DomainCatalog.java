/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.audit.domain;
import org.springframework.stereotype.Component;
import java.util.*;
@Component
public class DomainCatalog {
    private final Map<String, WorkflowAction> actions = new LinkedHashMap<>();
    public DomainCatalog() {
        actions.put("SUBMIT", new WorkflowAction("SUBMIT", "提交审计复核", List.of("草稿"), "待复核", "OPERATOR"));
        actions.put("ISSUE", new WorkflowAction("ISSUE", "签发审计结果", List.of("待复核"), "整改中", "ADMIN"));
        actions.put("CLOSE", new WorkflowAction("CLOSE", "关闭发现", List.of("整改中"), "已关闭", "ADMIN"));
    }
    public String systemName() { return "知华科技企业内部审计管理系统"; }
    public String scene() { return "审计宇宙、风险评估、计划、项目、底稿、发现、整改、复核与报告"; }
    public String initialStatus() { return "草稿"; }
    public String partyLabel() { return "被审计单位/事项"; }
    public String amountLabel() { return "风险金额"; }
    public String quantityLabel() { return "发现数量"; }
    public String dueLabel() { return "整改期限"; }
    public List<ModuleDefinition> modules() { return List.of(
            new ModuleDefinition("AUDIT_UNIVERSE", "审计宇宙", "维护组织、流程、系统和风险对象"),
            new ModuleDefinition("RISK_ASSESSMENT", "风险评估", "评估影响、可能性和控制成熟度"),
            new ModuleDefinition("ANNUAL_PLAN", "年度计划", "编制项目组合、资源与审计委员会计划"),
            new ModuleDefinition("ENGAGEMENT", "审计项目", "管理范围、团队、里程碑与沟通"),
            new ModuleDefinition("WORKPAPER", "审计底稿", "执行程序、取证、复核和归档"),
            new ModuleDefinition("FINDING", "审计发现", "记录事实、标准、原因、影响和建议"),
            new ModuleDefinition("REMEDIATION", "整改跟踪", "分派措施、到期提醒和升级"),
            new ModuleDefinition("VALIDATION", "整改复核", "验证证据、效果和剩余风险"),
            new ModuleDefinition("REPORT", "审计报告", "生成项目报告、管理层报告和趋势分析")
        ); }
    public Map<String, WorkflowAction> actions() { return Collections.unmodifiableMap(actions); }
    public record ModuleDefinition(String code,String name,String description) {}
    public record WorkflowAction(String code,String label,List<String> from,String to,String requiredRole) {}
}
