# Business Travel Management api

## Development

### Prerequisite

* JDK11
* Docker
* Docker-Compose

### Run Application In Local
- Run dev database in local

```bash
docker-compose -f docker-compose.yml up -d
```
- import `wiremock-data.json` on wiremock, it will host mock server for local development

- Run app

```bash
./gradlew bootRun
```
### Note
Current stub for third party apis using wiremock is only temporary strategy for local development,
Better integration with wiremock or other stub strategy should be discussed and implemented later.
