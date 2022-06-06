**Описание**

Создать сервис, который обращается к сервису курсов валют, и отображает gif:

• если курс по отношению к USD за сегодня стал выше вчерашнего, то отдаем рандомную отсюда https://giphy.com/search/rich
• если ниже - отсюда https://giphy.com/search/broke
**Ссылки**
• REST API курсов валют - https://docs.openexchangerates.org/
• REST API гифок - https://developers.giphy.com/docs/api#quick-start-guide
**Must Have**
• Сервис на Spring Boot 2 + Java / Kotlin
• Запросы приходят на HTTP endpoint (должен быть написан в соответствии с rest conventions), туда передается код валюты по отношению с которой сравнивается USD
• Для взаимодействия с внешними сервисами используется Feign
• Все параметры (валюта по отношению к которой смотрится курс, адреса внешних сервисов и т.д.) вынесены в настройки
• На сервис написаны тесты (для мока внешних сервисов можно использовать @mockbean или WireMock)
• Для сборки должен использоваться Gradle
• Результатом выполнения должен быть репо на GitHub с инструкцией по запуску
**Nice to Have**
• Сборка и запуск Docker контейнера с этим сервисом

**Как запустить**
• Проверить наличие jdk 11 или новее.
_java -v_
• Скопировать репозиторий
_git clone https://github.com/afilyukov/CheckCurrency
cd CheckCurrency_
• Собрать проект
./gradlew build
 --при ошибке сделать исполняемым _sudo chmod +x gradlew_
• Запустить проект
_java -jar build/libs/CheckCurrency-1.0.0.jar_

**Как использовать**
Необходимо послать GET запрос по следующему endpoint'у. 
(открыть в браузере http://localhost:8080/check-service/currency)
`GET /money-check-service/currency`
В параметры передается код валюты. Имя параметра `cmpr`.
Запрос для валюты "евро" выглядит так
`GET /money-check-service/currency?cmpr=EUR`
(http://localhost:8080/check-service/currency?cmpr=EUR)
