/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.audit.service;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
class AuditFindingClosureServiceTest {
    private final AuditFindingClosureService service = new AuditFindingClosureService();

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @Test void closesVerifiedAuditFinding() {
        var result = service.assess(new AuditFindingClosureService.Request("FND-100", true, true, true,
                true, true, true, true, true, true, true));
        assertThat(result.decision()).isEqualTo(AuditFindingClosureService.Decision.CLOSE);
    }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @Test void routesAdministrativeGapsToReview() {
        var result = service.assess(new AuditFindingClosureService.Request("FND-101", true, true, true,
                false, true, true, false, false, true, true));
        assertThat(result.actions()).hasSize(3);
        assertThat(result.decision()).isEqualTo(AuditFindingClosureService.Decision.REVIEW);
    }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @Test void blocksUnverifiedFindingClosure() {
        var result = service.assess(new AuditFindingClosureService.Request("", false, false, false,
                false, false, false, false, false, false, false));
        assertThat(result.blockers()).hasSize(8);
        assertThat(result.decision()).isEqualTo(AuditFindingClosureService.Decision.BLOCKED);
    }
}
