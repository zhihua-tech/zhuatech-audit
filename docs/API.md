# 企业内部审计管理系统 API

所有业务接口默认位于 `/api`，除 `/public/**` 和健康检查外均需要 HTTP Basic 身份认证。生产环境应接入企业 IAM 或统一身份平台。

| 方法 | 路径 | 说明 |
| --- | --- | --- |
| GET | `/public/about` | 产品、公司、官网和许可元数据 |
| GET | `/catalog` | 业务模块、字段标签和状态动作 |
| GET | `/dashboard` | 业务规模、金额、状态和模块统计 |
| GET/POST | `/records` | 业务台账查询与创建 |
| GET/PUT/DELETE | `/records/{id}` | 详情、草稿修改与删除 |
| POST | `/records/{id}/actions` | 执行服务端状态迁移 |
| POST | `/records/{id}/comments` | 增加协作记录 |
| GET | `/records/{id}/timeline` | 查询完整操作时间线 |
| GET | `/records/search` | 组合检索、分页和逾期筛选 |
| GET | `/records/export.csv` | 导出 UTF-8 CSV |
| GET | `/sla-summary` | SLA、逾期、风险和人员工作量 |
| POST | `/domain/decision` | 执行企业内部审计管理系统专属领域规则 |
| GET/POST | `/enterprise/controls` | 企业控制项查询与幂等创建 |
| POST | `/enterprise/controls/{id}/submit` | 提交复核 |
| POST | `/admin/enterprise/controls/{id}/review` | 管理员审批或驳回 |
| POST | `/enterprise/controls/{id}/documents` | 登记附件哈希及存储元数据 |
| POST | `/enterprise/controls/{id}/complete` | 凭证完整后办结 |
| POST | `/admin/enterprise/controls/{id}/sync` | 登记外部系统回执 |

## 领域决策字段

| 字段 | 类型 | 含义 |
| --- | --- | --- |
| `findingNo` | String | 发现编号 |
| `severity` | String | 严重程度(HIGH/MEDIUM/LOW) |
| `evidenceCount` | int | 证据数量 |
| `remediationProgress` | double | 整改进度(%) |
| `overdueDays` | int | 逾期天数 |
| `ownerAssigned` | boolean | 已指定责任人 |
| `managementAccepted` | boolean | 管理层已确认 |

接口统一返回 `ApiResponse`；业务冲突使用 HTTP 409，参数错误使用 400，未认证使用 401，无权限使用 403。

## 专业审计接口

| 方法 | 路径 | 说明 |
| --- | --- | --- |
| GET | `/api/audit-ops/dashboard` | 审计项目、底稿和发现总览 |
| POST | `/api/audit-ops/engagements` | 创建审计项目 |
| POST | `/api/admin/audit-ops/engagements/{id}/approve` | 批准项目 |
| POST | `/api/audit-ops/engagements/{id}/start` | 启动项目 |
| POST | `/api/audit-ops/engagements/{id}/workpapers` | 建立底稿和证据摘要 |
| POST | `/api/admin/audit-ops/workpapers/{id}/review` | 独立复核底稿 |
| POST | `/api/audit-ops/engagements/{id}/findings` | 登记审计发现 |
| POST | `/api/audit-ops/findings/{id}/action-plan` | 提交整改计划 |
| POST | `/api/audit-ops/findings/{id}/remediate` | 提交整改证据 |
| POST | `/api/admin/audit-ops/findings/{id}/verify` | 复核整改有效性 |
| POST | `/api/admin/audit-ops/engagements/{id}/issue-report` | 出具报告 |
| POST | `/api/admin/audit-ops/engagements/{id}/close` | 审计项目结项 |
