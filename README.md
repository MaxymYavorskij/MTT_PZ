# MTT_PZ

Моделювання та проектування ПЗ 2026

Консольний Java-застосунок, що моделює предметну область магазину: перегляд каталогу, оформлення замовлень клієнтами та їх опрацювання відповідальними особами.

Попередня версія — це перша лабораторна робота перед переходом на Java: [MaxymYavorskij/shop-db](https://github.com/MaxymYavorskij/shop-db).

## Збірка і запуск

Потрібна Java 17 (JRE для запуску, JDK для збірки).

Через Maven:

```
mvn package
java -jar target/shop-domain-1.0.jar
```

Без Maven (тільки JDK):

```
javac -encoding UTF-8 -d target/classes $(find src -name "*.java")
jar cfe target/shop-domain-1.0.jar shop.Main -C target/classes .
java -jar target/shop-domain-1.0.jar
```

## Діаграми

У папці `docs/` лежать UML діаграми (UMLetino, `.uxf` + `.png`): станів, послідовності, пакетів, класів, артефактів і розгортання.
