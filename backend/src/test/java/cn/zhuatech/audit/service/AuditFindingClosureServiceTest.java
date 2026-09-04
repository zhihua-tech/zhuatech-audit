/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.audit.service;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

class AuditFindingClosureServiceTest {
    private final AuditFindingClosureService service = new AuditFindingClosureService();

    @Test void closesVerifiedAuditFinding() {
        var result = service.assess(new AuditFindingClosureService.Request("FND-100", true, true, true,
                true, true, true, true, true, true, true));
        assertThat(result.decision()).isEqualTo(AuditFindingClosureService.Decision.CLOSE);
    }

    @Test void routesAdministrativeGapsToReview() {
        var result = service.assess(new AuditFindingClosureService.Request("FND-101", true, true, true,
                false, true, true, false, false, true, true));
        assertThat(result.actions()).hasSize(3);
        assertThat(result.decision()).isEqualTo(AuditFindingClosureService.Decision.REVIEW);
    }

    @Test void blocksUnverifiedFindingClosure() {
        var result = service.assess(new AuditFindingClosureService.Request("", false, false, false,
                false, false, false, false, false, false, false));
        assertThat(result.blockers()).hasSize(8);
        assertThat(result.decision()).isEqualTo(AuditFindingClosureService.Decision.BLOCKED);
    }
}
