# Clojure
* Clojure made simple: основные идеи кложи, рассказанные ричем хикки
  https://youtu.be/VSdnJDO-xdg

* короткий обзор кложуры + пример веб-разработки, можно пропустить:
  https://youtu.be/m_nlBeNIhZo

* Трансдьюсеры:
  https://youtu.be/6mTbuzafcII
  https://youtu.be/4KqUvG8HPYo

# Concurrency/
* Clojure concurrency:
  https://youtu.be/nDAfZK8m5_8

* Core.async (go-like channels, coroutines):
  https://youtu.be/drmNlZVkUeE

* N:M параллелизм
  * Go, coroutines implementation details https://youtu.be/-K11rY57K7k
  * F# + general discussion https://youtu.be/X2AYct_oSP4 

# Software engineering ideas


* railway-oriented-programming, про обработку ошибок и дизайн надёжных систем, F#. возможно, гораздо больше подходит для строго-типизированных языков, но основная идея применима и в кложуре
  https://fsharpforfunandprofit.com/rop/

* Incremental -- UI-framework, OCaml (основная идея будет понятна без кода, но желательно примерно представлять ML-нотацию типов)
  https://youtu.be/R3xX37RGJKE

* Backpressure: про потоки данных в очередях сообщений, обычно требую эту фичу в курсовой с конвеерной обработкой
  https://lucumr.pocoo.org/2020/1/1/async-pressure/
* нечто похожее, кажется, с небольшим залезанием в "теорию очередей" https://youtu.be/m64SWl9bfvk
* и ещё немного про стримы данных https://youtu.be/9RMOc0SwRro

* Transactions: https://youtu.be/5ZjhNTM8XU8
* **в целом про бд**: Что особенного в СУБД для данных в оперативной памяти https://youtu.be/yrTF3qH8ey8


# Темы курсовых

## Объектная модель
* CLOS
* разобраться с interface/mixin/trait/protocol
* signle/multiple dispatch? (clojure multimethods)
* multiple inheritance?
* поддержка исключений или других механизмов обработки ошибок?
  * Condition Systems in an Exceptional Language https://youtu.be/zp0OEDcAro0
  * Beyond Exception Handling: Conditions and Restarts http://www.gigamonkeys.com/book/beyond-exception-handling-conditions-and-restarts.html
* ___конченная идея__ если мы примем идею о том, что вызов метода -- передача управления на адрес функции в памяти, то мы вполне можем (красиво) реализовать объектную модель на чём-то типа спредшитов (эксель?). можно попробовать сделать костыльный прототип на экселе и, если зайдёт, реализовать какой-то свой табличный процессор и уже в нём запрограммировать объектную модель
## Представление данных в S-выражениях
* возможно стоит не думать о парсинге выражений и просто использовать Clojure?
* XML, XSLT, XPath, XML Schema
* запросы к данным
  * язык запросов Cypher (graph db Neo4j) https://neo4j.com/developer/cypher/
  * SPARQL
  * Meander -- трансформации данных в стиле sparql на Clojure https://youtu.be/9fhnJpCgtUw

## Конвейерная обработка
* всё из раздела [Concurrency](#Concurrency)
* Akka Streams (Scala) https://youtu.be/dQ4XsLhatHU
* backpressure
  * I'm not feeling the async pressure https://lucumr.pocoo.org/2020/1/1/async-pressure/
  * Stop Rate Limiting! Capacity Management Done Right https://youtu.be/m64SWl9bfvk

## Облачные вычисления
* передача кода не будет главной проблемой в управляемых средах (java/.net)
* распиливаем данные на кусочки, отправляем разным узлам, параллельно обрабатываем, собираем обратно (старый добрый MPI)
* решаем задачу балансировки нагрузки, задачу о назначениях и много других

## DI-контейнер
* всё, что найдётся по этой теме у Фаулера
* про DI (haskell, f# в качестве примеров) https://blog.ploeh.dk/2017/01/27/from-dependency-injection-to-dependency-rejection/

## Объектная персистентность
* реализуем библиотеку, с помощью которой можно
  * задать какие-нибудь отношения объектов через код (например, горсткой функций и макросов или каким-то другим DSL)
    * отношения могут быть
      * реляционными (можем использовать sqlite для их хранения)
      * графовыми (тут стоит посмотреть на [секцию запросов к данным](##Представление-данных-в-S-выражениях))
      * документными (но как тогда строить запросы?)
  * уметь сохранять, загружать и изменять коллекции этих объектов
  * уметь делать запросы к коллекциям объектов

## Lisp-машина
* "а давайте напишем лисп-интерпретатор на кложуре" + квотейшены и выворот мозга
  https://youtu.be/OyfBQmvr2Hc
* можем не только компилировать в байткод или ассемблер, но и просто интерпретировать, используя хост-язык
  * возможно, это освободит время для реализации в языке каких-то интересных штук

## Persistent data structures
* MIT lectures (playlist): https://youtu.be/T0yzrZL1py0
* https://hypirion.com/musings/understanding-persistent-vector-pt-1
* https://hypirion.com/musings/understanding-persistent-vector-pt-2

## Версионная память
* https://www.microsoft.com/en-us/research/project/concurrent-revisions/
* https://www.microsoft.com/en-us/research/publication/two-for-the-price-of-one-a-model-for-parallel-and-incremental-computation/
* https://www.microsoft.com/en-us/research/publication/eventually-consistent-transactions/
* https://www.rise4fun.com/revisions