### Hexlet tests and linter status:
[![Actions Status](https://github.com/Ahiru78/java-project-78/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/Ahiru78/java-project-78/actions)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=Ahiru78_java-project-78&metric=sqale_rating)](https://sonarcloud.io/summary/new_code?id=Ahiru78_java-project-78)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=Ahiru78_java-project-78&metric=coverage)](https://sonarcloud.io/summary/new_code?id=Ahiru78_java-project-78)

### Валидатор данных

Валидатор данных – библиотека, с помощью которой можно проверять корректность любых данных.

## Валидация строк
 Валидация строк включает в себя следующий набор методов:
* **required()** — делает данные обязательными для заполнения. Иными словами добавляет в схему ограничение, которое не позволяет использовать null или пустую строку в качестве значения
* **minLength()** — добавляет в схему ограничение минимальной длины для строки. Строка должна быть равна или длиннее указанного числа
* **contains()** — добавляет в схему ограничение по содержимому строки. Строка должна содержать определённую подстроку
## Валидация чисел
 Валидация чисел включает в себя следующий набор методов:
* **required()** — добавляет в схему ограничение, которое не позволяет использовать null в качестве значения
* **positive()** — добавляет ограничение на знак числа. Число должно быть положительным
* **range()** — добавляет допустимый диапазон, в который должно попадать значение числа включая границы
## Валидация объектов типа Map
 Валидация объектов типа Map включает в себя следующий набор методов:
* **required()** — добавляет в схему ограничение, которое не позволяет использовать null в качестве значения. Требуется тип данных Map
* **sizeof()** — добавляет ограничение на размер мапы. Количество пар ключ-значений в объекте Map должно быть равно заданному
* **shape()** — используется для определения свойств объекта Map и создания схемы для валидации их значений в соответствии с обозначенными выше методами
## Пример использования
```java
Validator v = new Validator();

// Строки
StringSchema schema = v.string().required();

schema.isValid("what does the fox say"); // true
schema.isValid(""); // false

// Числа
NumberSchema schema = v.number().required().positive();

schema.isValid(-10); // false
schema.isValid(10); // true

// Объект Map с поддержкой проверки структуры
Map<String, BaseSchema<String>> schemas = new HashMap<>();
schemas.put("firstName", v.string().required());
schemas.put("lastName", v.string().required().minLength(2));

MapSchema schema = v.map().sizeof(2).shape(schemas);

Map<String, Object> human1 = new HashMap<>();
human1.put("firstName", "John");
human1.put("lastName", "Smith");
schema.isValid(human1); // true

Map<String, Object> human2 = new HashMap<>();
human2.put("firstName", "Anna");
human2.put("lastName", "B");
schema.isValid(human2); // false
```
