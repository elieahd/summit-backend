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

- [ ] security `oauth2`/`jwt`
- [ ] queues (`kafka` or `rabbitMQ`)
- [ ] caching with `redis`
- [ ] performance tests (`k6` or `gatling`)
- [ ] reporting (generate pdf and cvs on some data)
- [ ] audit to track changes happening on an entity
- [ ] communication / notification (maybe `thymeleaf` and send emails)
- [ ] batch (`spring batch` & `k8s cron job`)

## Devops

- [ ] push on `develop` -> deploy on k8s (`AWS` or `GCP` or `Azure`)
- [ ] decide on a git branching strategy -> `trunc based` or `gitflow`
- [ ] feature flag
- [ ] decide on if i want to explore gitops (ArgoCD or other options)

## Documentations 

- [ ] [Database migration via liquibase](./docs/db-migration-liquibase.md)
- [ ] [Code architecture via DDD & Hexagonal Architecture](./docs/architecture-ddd-hexagonal.md)
- [ ] [Unit test with stubs instead of mocking it and with test data](./docs/unit-tests-stubs.md)
- [ ] [API documentation via Swagger](./docs/api-documentation-swagger.md)
- [ ] [Integration test on Persistence layer with Test Containers](./docs/int-tests-persistence.md)
- [ ] [Integration test on Rest API with Test Containers and Rest Assured](./docs/int-tests-rest-api.md)
- [ ] [Pipelines automated dependencies upgrades via GitHub dependabot](./docs/gha-dependabot.md)
- [ ] [Pipelines automated checks on PRs via GitHub Actions and Sonar](./docs/gha-pr.md)
- [ ] [Pipelines automated deployment on push on develop via GitHub Actions, Docker](./docs/gha-deploy.md)
