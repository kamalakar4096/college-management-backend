# College Management Application — Backend

## Tech Stack
- Java 17 · Spring Boot 3.2 · Spring Security · JWT (jjwt 0.11.5)
- JPA/Hibernate · MySQL 8 · Maven

## Quick Start

### 1. Database setup
```sql
CREATE DATABASE college_db;
```

### 2. Configure credentials
Edit `src/main/resources/application.properties`:
```
spring.datasource.username=root
spring.datasource.password=your_password
app.jwt.secret=404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970
```

### 3. Run
```bash
mvn spring-boot:run
```
Server starts at **http://localhost:8080**

### 4. Default Admin (auto-seeded on first run)
| Field    | Value              |
|----------|--------------------|
| Email    | admin@college.edu  |
| Password | Admin@123          |

---

## Role Permissions Summary

| Role    | Can Do |
|---------|--------|
| ADMIN   | Create/manage all users, departments, courses. View reports. Send notifications. |
| DEAN    | View reports, send notifications. |
| HOD     | Create/assign subjects to faculty. Send notifications. |
| FACULTY | Mark/update attendance, add/update marks, create assignments. Send notifications. |
| STUDENT | Submit assignments, view own attendance/marks/notifications. |

> **Only ADMIN can create users. No public registration.**

---

## API Endpoints Reference

### Auth
| Method | URL | Access |
|--------|-----|--------|
| POST | `/api/auth/login` | Public |

### Users
| Method | URL | Access |
|--------|-----|--------|
| POST | `/api/users` | ADMIN |
| GET | `/api/users` | ADMIN |
| GET | `/api/users/{id}` | ADMIN |
| GET | `/api/users/role/{role}` | ADMIN |
| GET | `/api/users/department/{id}` | ADMIN |
| PUT | `/api/users/{id}` | ADMIN |
| PUT | `/api/users/{id}/deactivate` | ADMIN |
| PUT | `/api/users/{id}/activate` | ADMIN |

### Departments
| Method | URL | Access |
|--------|-----|--------|
| POST | `/api/departments` | ADMIN |
| GET | `/api/departments` | Authenticated |
| GET | `/api/departments/{id}` | Authenticated |
| PUT | `/api/departments/{id}` | ADMIN |
| PUT | `/api/departments/{id}/assign-hod/{hodId}` | ADMIN |

### Courses
| Method | URL | Access |
|--------|-----|--------|
| POST | `/api/courses` | ADMIN |
| GET | `/api/courses` | Authenticated |
| GET | `/api/courses/{id}` | Authenticated |
| GET | `/api/courses/department/{id}` | Authenticated |
| PUT | `/api/courses/{id}` | ADMIN |
| DELETE | `/api/courses/{id}` | ADMIN |

### Subjects
| Method | URL | Access |
|--------|-----|--------|
| POST | `/api/subjects` | ADMIN, HOD |
| GET | `/api/subjects` | Authenticated |
| GET | `/api/subjects/{id}` | Authenticated |
| GET | `/api/subjects/department/{id}` | Authenticated |
| GET | `/api/subjects/faculty/{id}` | Authenticated |
| PUT | `/api/subjects/{id}` | ADMIN, HOD |
| PUT | `/api/subjects/{id}/assign-faculty/{fid}` | ADMIN, HOD |

### Attendance
| Method | URL | Access |
|--------|-----|--------|
| POST | `/api/attendance` | ADMIN, FACULTY |
| PUT | `/api/attendance/{id}?status=PRESENT` | ADMIN, FACULTY |
| GET | `/api/attendance/student/{id}` | Authenticated |
| GET | `/api/attendance/subject/{id}?date=YYYY-MM-DD` | Authenticated |
| GET | `/api/attendance/percentage?studentId=&subjectId=` | Authenticated |

### Marks
| Method | URL | Access |
|--------|-----|--------|
| POST | `/api/marks` | ADMIN, FACULTY |
| PUT | `/api/marks/{id}` | ADMIN, FACULTY |
| GET | `/api/marks/student/{id}` | Authenticated |
| GET | `/api/marks/student/{id}/subject/{id}` | Authenticated |

### Assignments
| Method | URL | Access |
|--------|-----|--------|
| POST | `/api/assignments` (multipart) | ADMIN, FACULTY |
| GET | `/api/assignments/{id}` | Authenticated |
| GET | `/api/assignments/faculty/{id}` | Authenticated |
| GET | `/api/assignments/subject/{id}` | Authenticated |
| POST | `/api/assignments/{id}/submit` (multipart) | STUDENT |
| GET | `/api/assignments/{id}/submissions` | Authenticated |
| GET | `/api/assignments/{id}/my-submission` | STUDENT |

### Notifications
| Method | URL | Access |
|--------|-----|--------|
| POST | `/api/notifications` | ADMIN, DEAN, HOD, FACULTY |
| GET | `/api/notifications/my-role` | Authenticated |
| GET | `/api/notifications/sent` | Authenticated |

### Reports
| Method | URL | Access |
|--------|-----|--------|
| GET | `/api/reports/summary` | ADMIN, DEAN |
| GET | `/api/reports/department/{id}` | ADMIN, DEAN |

---

## JWT Usage
All protected endpoints require:
```
Authorization: Bearer <token>
```
Token is returned in the login response under `data.accessToken`.  
Default expiry: **24 hours** (configurable via `app.jwt.expiration`).

---

## File Uploads
- Uploaded to `uploads/` directory (auto-created on startup)
- Served at `/uploads/<filename>`
- Max size: 10MB

## Exam Types (Marks)
`INTERNAL_1` · `INTERNAL_2` · `MIDTERM` · `FINAL` · `ASSIGNMENT` · `PRACTICAL`

## Attendance Statuses
`PRESENT` · `ABSENT` · `LATE`

## Submission Statuses
`SUBMITTED` · `GRADED` · `LATE`
