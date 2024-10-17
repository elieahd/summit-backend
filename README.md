[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=elieahd_summit-backend&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=elieahd_summit-backend)

## Backlog

### Todo
- [x] PostgreSQL database remotely (via supabase)
- [x] Liquibase
- [x] DDD & Hexagonal Architecture
- [x] Unit test with stubs instead of mocking + test data
- [x] Swagger
- [x] Integration test on JDBC with test containers
- [x] Integration test on Rest API with test containers & rest assured
- [x] Checkstyle
- [x] GitHub Actions dependabot
- [x] GitHub Actions pull-requests
- [x] [PR template](./.github/pull_request_template.md)
- [x] Docker
- [x] GitHub Actions push-on-develop -> needs to include docker build/push command
- [ ] Sonar --> coverage not showing (jacoco with multi-module)
- [ ] Complete application

### Future todo

- [ ] security `oauth2`/`jwt`
- [ ] queues (`kafka` or `rabbitMQ`)
- [ ] caching with `redis`
- [ ] reporting (generate pdf and cvs on some data)
- [ ] audit to track changes happening on an entity
- [ ] object storage (`s3`)
- [ ] communication / notification (maybe `thymeleaf` and send emails)
- [ ] batch (`spring batch` & `k8s cron job`)
- [ ] push on `develop` -> deploy on k8s (`AWS` or `GCP` or `Azure`)
- [ ] performance tests (`k6` or `gatling`)
- [ ] decide on a git branching strategy -> `trunc based` or `gitflow`
- [ ] feature flag
- [ ] decide on if i want to explore gitops (ArgoCD or other options)

## Technical features

- Database migration via liquibase
- Code architecture via DDD & Hexagonal Architecture
- Unit test with stubs instead of mocking it and with test data
- API documentation via Swagger
- Integration test on Persistence layer with Test Containers
- Integration test on Rest API with Test Containers and Rest Assured
- Pipelines automated dependencies upgrades via GitHub dependabot
- Pipelines automated checks on PRs via GitHub Actions and Sonar
- Pipelines automated deployment on push on develop via GitHub Actions, Docker, Tag
