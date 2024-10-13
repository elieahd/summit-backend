[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=elieahd_summit-backend&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=elieahd_summit-backend)

## Current backlog

- [x] PostgreSQL database remotely (via supabase)
- [x] Liquibase
- [x] DDD & Hexagonal Architecture
- [x] Unit test with stubs instead of mocking + test data
- [x] Swagger
- [x] Integration test on JDBC with test containers
- [x] Integration test on Rest API with test containers & rest assured
- [x] Checkstyle
- [x] Github Actions dependabot
- [x] Github Actions pull-requests
- [x] [PR template](./.github/pull_request_template.md)
- [ ] Sonar --> need to fix coverage not showing
- [ ] Docker
- [ ] Github Actions push-on-develop -> needs to include docker build/push command 
- [ ] Complete application

## Backlog

- [ ] Security / OAuth2
- [ ] Add Kafka listener
- [ ] Caching with Redis
- [ ] Performance tests (K6 or Gatling)
- [ ] Feature flag
- [ ] Reporting module
- [ ] Audit
- [ ] Communication / Notification
- [ ] Batch capacity (Spring Batch)

## Devops

- [ ] push on `develop` -> deploy on k8s (AWS or GCP or Azure)
- [ ] decide on a git branching strategy -> trunc based or gitflow

## Docs todo 

- Database migration -> liquibase
- Code architecture -> DDD & Hexagonal Architecture
- Unit test with stubs instead of mocking it and with test data
- API documentation via Swagger
- Integration test on Persistence layer with Test Containers
- Integration test on Rest API with Test Containers and Rest Assured
- Pipelines automated dependencies upgrades via GitHub dependabot
- Pipelines automated checks on PRs via GitHub Actions and Sonar
- Pipelines automated deployment on push on develop via GitHub Actions, Docker 
