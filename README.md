# SkillLoop AI — Backend (Modular Spring Boot Monolith)

**Project Title:** Employment Outcomes and Skill-Gap Analysis  
**Department:** Computer Science & Engineering, DSATM (PBL 3rd Sem — Group B3)  
**Backend Team:** Harshit Agarwalla (Backend 1) & Hemanth V (Backend 2)  
**Frontend Team:** Dhanush CS & Christo George

---

## 🌟 Overview & Architecture

SkillLoop AI is an employment-outcomes tracking and skill-gap intelligence platform built as a **modular Spring Boot monolith** in Java 21.

### Shared Sync Architecture (Harshit & Hemanth)

The backend seamlessly synchronizes the core training domain with the post-training employment intelligence domain:

| Domain | Owner | Core Responsibilities |
|---|---|---|
| **Auth & Security** | Harshit | User management, roles (`ADMIN`, `INSTITUTE_STAFF`, `TRAINEE`, `EMPLOYER`), JWT authentication filter, CORS for frontend. |
| **Training & Academics** | Harshit | Courses, batches, consent-based trainee records, enrolments, assessments, certifications. |
| **Employment Outcomes** | Hemanth | Post-training employment outcomes (`EMPLOYED`, `UNEMPLOYED`, `SELF_EMPLOYED`, `APPRENTICESHIP`, `FURTHER_EDUCATION`, `OUTCOME_UNKNOWN`). |
| **Longitudinal Follow-up** | Hemanth | Systematic 3, 6, and 12-month milestone tracking. Non-responders marked as `OUTCOME_UNKNOWN`, never misclassified as unemployed. |
| **Verification & Auditing** | Hemanth | Evidence tracking (`EMPLOYER_CONFIRMED`, `INDEPENDENTLY_CORROBORATED`, `SELF_REPORTED`), conflict flagging, and confidence scoring. |
| **Skill-Gap Analysis & AI** | Hemanth | Course syllabus vs employer job requirement comparison, transparent percentage calculation, missing skills identification, AI mismatch explanations. |
| **Course Recommendations** | Hemanth | Actionable curriculum improvement proposals, approval workflow, hypothesis testing on later cohorts. |
| **Analytics Engine** | Hemanth | Aggregated KPIs, longitudinal retention charts, salary band distributions, and course performance rankings for Recharts frontend. |

---

## 🚀 Quick Start

### Prerequisites
- **Java 21 LTS** or higher
- Embedded Maven wrapper included (`./mvnw.cmd` on Windows, `./mvnw` on Linux/macOS)

### 1. Run the Backend
```powershell
.\mvnw.cmd spring-boot:run
```

By default, the application runs on **`http://localhost:8080`** using an in-memory database with **PostgreSQL compatibility mode** and automatically seeds complete demonstration data.

### 2. Run with PostgreSQL
To connect to a live PostgreSQL database:
```powershell
.\mvnw.cmd spring-boot:run -Dspring-boot.run.profiles=postgres
```
Database credentials can be configured via environment variables or in `application-postgres.properties`:
- `DB_HOST=localhost`
- `DB_PORT=5432`
- `DB_NAME=skillloop_db`
- `DB_USER=postgres`
- `DB_PASSWORD=postgres`

### 3. Run Automated Tests
```powershell
.\mvnw.cmd test
```
All 11 unit and integration tests covering verification rules, skill-matching calculations, and MockMvc REST endpoints run and validate business logic.

---

## 📚 Interactive API Documentation (Swagger UI)

Once the application is running, open:
- **Swagger UI:** `http://localhost:8080/swagger-ui.html`
- **OpenAPI JSON:** `http://localhost:8080/api-docs`
- **H2 Database Console:** `http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:mem:skillloopdb`, User: `sa`, Password: *(empty)*)

---

## 🔌 REST API Endpoints Summary

### 1. Authentication (`/api/auth`)
- `POST /api/auth/register` — Register a new user
- `POST /api/auth/login` — Authenticate and receive JWT token
- `GET /api/auth/me` — Retrieve current authenticated user profile

### 2. Employment Outcomes (`/api/employment`)
- `GET /api/employment` — List all outcomes (filterable by `status` or `courseId`)
- `GET /api/employment/{id}` — Get outcome details
- `GET /api/employment/trainee/{traineeId}` — Get trainee employment record
- `POST /api/employment` — Record new employment outcome (auto-schedules 3, 6, 12 month follow-ups)
- `PUT /api/employment/{id}` — Update outcome details
- `DELETE /api/employment/{id}` — Delete outcome

### 3. Longitudinal Follow-ups (`/api/follow-ups`)
- `GET /api/follow-ups` — List all follow-ups (filterable by `status`)
- `GET /api/follow-ups/trainee/{traineeId}` — Get follow-up timeline for a trainee
- `GET /api/follow-ups/milestone/{milestone}` — Milestone query (`MONTH_3`, `MONTH_6`, `MONTH_12`)
- `POST /api/follow-ups/{id}/respond` — Record trainee response; unanswered follow-ups are marked as `OUTCOME_UNKNOWN`

### 4. Employment Verification (`/api/verifications`)
- `GET /api/verifications` — List all verification records
- `GET /api/verifications/outcome/{outcomeId}` — Get verifications for an outcome
- `GET /api/verifications/pending` — Get pending verification requests
- `GET /api/verifications/conflicts` — Get flagged conflicting records for audit
- `POST /api/verifications/outcome/{outcomeId}` — Submit verification evidence (offer letter, payslip, HR contact)
- `PUT /api/verifications/{id}/review` — Audit and approve/reject/flag verification

### 5. Skills & AI Extraction (`/api/skills`)
- `GET /api/skills` — Search and list industry skill taxonomy
- `POST /api/skills` — Add new skill to taxonomy
- `GET /api/skills/course/{courseId}` — List skills taught in a course
- `POST /api/skills/course/{courseId}` — Map skill with proficiency & hours to course
- `DELETE /api/skills/course/{courseId}/skill/{skillId}` — Unmap skill
- `POST /api/skills/extract` — AI keyword & semantic extraction from raw job description

### 6. Employer Job Requirements (`/api/job-requirements`)
- `GET /api/job-requirements` — List active job requirements
- `GET /api/job-requirements/{id}` — Get job requirement details
- `POST /api/job-requirements` — Create job requirement with required/preferred skills

### 7. Skill-Gap Analysis (`/api/skill-gap`)
- `GET /api/skill-gap` — List all completed skill-gap analysis reports
- `GET /api/skill-gap/course/{courseId}` — Get skill gaps for a course
- `POST /api/skill-gap/analyze/course/{courseId}/job/{jobId}` — Run AI skill gap analysis against a specific job
- `POST /api/skill-gap/analyze/course/{courseId}/all-jobs` — Run batch analysis across all active industry jobs

### 8. Course Recommendations (`/api/recommendations`)
- `GET /api/recommendations` — List reviewable recommendations
- `GET /api/recommendations/{id}` — Get recommendation details
- `POST /api/recommendations` — Propose new curriculum change based on skill-gap data
- `PUT /api/recommendations/{id}/status` — Update review status (`UNDER_REVIEW`, `APPROVED`, `REJECTED`, `IMPLEMENTED`)
- `PUT /api/recommendations/{id}/cohort-result` — Record measured outcomes in next cohort to test hypothesis

### 9. Analytics Engine for Dashboards (`/api/analytics`)
- `GET /api/analytics/overview` — High-level KPIs (total trainees, completed, employed, employment rate %, verified %)
- `GET /api/analytics/longitudinal` — 3, 6, 12 month retention breakdown for Recharts line/bar charts
- `GET /api/analytics/salary-bands` — Trainee monthly salary band distribution for pie/bar charts
- `GET /api/analytics/course-performance` — Course comparison scorecard (placement, retention, relevance, skill match)
- `GET /api/analytics/skill-gap-summary` — Top demanded skills vs top missing curriculum gaps
- `GET /api/analytics/districts` — Trainee outcomes grouped by district

---

## 👥 Demo Logins (Pre-seeded)

| Username | Password | Role |
|---|---|---|
| `admin` | `admin123` | `ADMIN` |
| `hemanth` | `hemanth123` | `ADMIN` (Lead Analyst) |
| `harshit` | `staff123` | `INSTITUTE_STAFF` |
| `dhanush` | `trainee123` | `TRAINEE` |
| `tcs_recruiter` | `employer123` | `EMPLOYER` |

