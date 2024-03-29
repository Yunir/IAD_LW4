## Переписать приложение из лабораторной работы №3 с использованием следующих технологий:
- [x] Уровень back-end должен быть основан на Java EE (необходимо использовать EJB).
- [x] Уровень front-end должен быть построен на Vaadin
## Приложение по-прежнему должно включать в себя 2 страницы - стартовую и основную страницу приложения. Обе страницы приложения должны быть адаптированы для отображения в 3 режимах:
- [x] "Десктопный" - для устройств, ширина экрана которых равна или превышает 1217 пикселей.
- [x] "Планшетный" - для устройств, ширина экрана которых равна или превышает 847, но меньше 1217 пикселей.
- [x] "Мобильный" - для устройств, ширина экрана которых меньше 847 пикселей.
## Стартовая страница должна содержать следующие элементы:
- [x] "Шапку", содержащую ФИО студента, номер группы и номер варианта.
- [x] Форму для ввода логина и пароля. 
- [x] Информация о зарегистрированных в системе пользователях должна храниться в отдельной таблице БД (пароль должен храниться в виде хэш-суммы). 
- [x] Доступ неавторизованных пользователей к основной странице приложения должен быть запрещён.
## Основная страница приложения должна содержать следующие элементы:
- [x] Набор полей ввода для задания координат точки и радиуса области в соответствии с вариантом задания: Radio {'-3','-2','-1','0','1','2','3','4','5'} для координаты по оси X, Text (-3 ... 5) для координаты по оси Y, и Radio {'-3','-2','-1','0','1','2','3','4','5'} для задания радиуса области. 
- [x] Если поле ввода допускает ввод заведомо некорректных данных (таких, например, как буквы в координатах точки или отрицательный радиус), то приложение должно осуществлять их валидацию.
- [x] Динамически обновляемую картинку, изображающую область на координатной плоскости в соответствии с номером варианта и точки, координаты которых были заданы пользователем. 
- [x] Клик по картинке должен инициировать сценарий, осуществляющий определение координат новой точки и отправку их на сервер для проверки её попадания в область. 
- [x] Цвет точек должен зависить от факта попадания / непопадания в область. 
- [x] Смена радиуса также должна инициировать перерисовку картинки.
- [x] Таблицу со списком результатов предыдущих проверок.
- [x] Ссылку, по которой аутентифицированный пользователь может закрыть свою сессию и вернуться на стартовую страницу приложения.
## Дополнительные требования к приложению:
- [x] Все результаты проверки должны сохраняться в базе данных под управлением СУБД PostgreSQL.
- [x] Для доступа к БД необходимо использовать JPA.
## Вопросы к защите лабораторной работы:
1. Платформа Java EE. Спецификации и их реализации.
1. Принципы IoC, CDI и Location Transpanency. Компоненты и контейнеры.
1. Управление жизненным циклом компонентов. Дескрипторы развёртывания.
1. Java EE API. Виды компонентов. Профили платформы Java EE.
1. Компоненты EJB. Stateless & Stateful Session Beans. EJB Lite и EJB Full.
1. Работа с электронной почтой в Java EE. JavaMail API.
1. JMS. Реализация очередей сообщений. Способы доставки сообщений до клиента. Message-Driven Beans.
1. Понятие транзакции. Управление транзакциями в Java EE. JTA.
1. Веб-сервисы. Технологии JAX-RS и JAX-WS.
1. Платформа Spring. Сходства и отличия с Java EE.
1. Модули Spring. Архитектура Spring Runtime. Spring Security и Spring Data.
1. Реализация IoC и CDI в Spring. Сходства и отличия с Java EE.
1. Реализация REST API в Java EE и Spring.
1. Google Web Toolkit. Архитектура и основные принципы разработки интерфейсов.
1. Компоненты gwt. Обработка событий UI.
1. Расширения gwt. Vaadin Framework.
1. React JS. Архитектура и основные принципы разработки приложений.
1. Компоненты React. "Умные" и "глупые" компоненты.
1. Разметка страниц в React-приложениях. JSX.
1. Навигация в React-приложениях. ReactRouter.
1. Управление состоянием интерфейса. Redux.
1. AngularJS. Архитектура и основные принципы разработки приложений.
1. Компоненты Angular. Навигация в Angular-приложениях.
1. Отличия Angular 1 от Angular 2 и 3.

## Компилируется на версии:
- jdk 11
- maven 3.8.1