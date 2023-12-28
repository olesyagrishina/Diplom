**Процедура запуска автотестов**

1.Запустить docker контейнеры c MySQL, с PostgreSQL и эмулятором банковских сервисов с помощью команды docker-compose up

2.Запустить jar-приложение (SUT) для использования базы данных 
- PostgreSQL с помощью команды java “-Dspring.datasource.url=jdbc:postgresql://185.119.57.172:5432/db” “-Dspring.datasource.username=app” “-Dspring.datasource.password=pass” -jar ./artifacts/aqa-shop.jar;
- MySQL - java “-Dspring.datasource.url=jdbc:mysql://185.119.57.172:5432/db” “-Dspring.datasource.username=app” “-Dspring.datasource.password=pass” -jar ./artifacts/aqa-shop.jar

3.Запустить тесты для базы данных
- PostgreSQL с помощью команды ./gradlew clean test -Ddb.url=jdbc:mysql://185.119.57.172:5432/db;
- MySQL - ./gradlew clean test -Ddb.url=jdbc:mysql://185.119.57.172:3306/db

