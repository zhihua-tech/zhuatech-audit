/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.audit.service;
import jakarta.validation.constraints.*;
import org.springframework.stereotype.Service;
import java.util.*;
@Service public class DomainDecisionService {
 public DecisionResult assess(DecisionRequest request) { int score=100;List<String> actions=new ArrayList<>();if(request.evidenceCount()<2){score-=25;actions.add("补充充分适当的审计证据");}if(request.remediationProgress()<100){score-=20;actions.add("完成剩余整改措施");}if(request.overdueDays()>0){score-=Math.min(30,request.overdueDays());actions.add("升级逾期整改事项");}if(!request.ownerAssigned()){score-=25;actions.add("明确整改责任人");}if(!request.managementAccepted()){score-=20;actions.add("取得管理层书面确认");}if("HIGH".equalsIgnoreCase(request.severity())&&request.remediationProgress()<100)score-=10;return result(score,actions,"READY_TO_CLOSE","KEEP_OPEN","ESCALATE",Map.of("evidenceCount",request.evidenceCount(),"progress",request.remediationProgress(),"overdueDays",request.overdueDays())); }
 private DecisionResult result(int raw,List<String> actions,String good,String warn,String bad,Map<String,Object> metrics) { int score=Math.max(0,Math.min(100,raw));String decision=score>=80?good:score>=50?warn:bad;return new DecisionResult(decision,score,metrics,List.copyOf(actions)); }
 private DecisionResult riskResult(int raw,List<String> actions,String good,String warn,String bad,Map<String,Object> metrics) { int score=Math.max(0,Math.min(100,raw));String decision=score>=70?bad:score>=40?warn:good;return new DecisionResult(decision,score,metrics,List.copyOf(actions)); }
 public record DecisionRequest(
        @NotBlank String findingNo,
        @NotBlank String severity,
        @PositiveOrZero int evidenceCount,
        @DecimalMin("0") @DecimalMax("100") double remediationProgress,
        @PositiveOrZero int overdueDays,
        boolean ownerAssigned,
        boolean managementAccepted) {}
 public record DecisionResult(String decision,int score,Map<String,Object> metrics,List<String> actions) {}
}
