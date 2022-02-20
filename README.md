# Bank

## Opis aplikacji

* Aplikacja składa się z dwóch komponentów Customer oraz Credit. 
* Aplikacja korzysta z bazy danych MySQL.
* Komponent Credit komunikuje się z komponentem Customer poprzez RestTemplate.
* Poszczególne komponenty posiadają testy jednostkowe oraz integracyjne. Code coverage min. 80%
* Aplikacja została umieszczona w kontenerze dockerowym
* Komunikacja z bazą danych poprzez Spring Data JPA


## Uruchomienie aplikacji

#### Uruchomienie za pomocą docker-compose:
1. Należy pobrać docker'a na komputer
2. Pobrać repozytorium
3. Przejść do repozytorium które pobraliśmy
4. Za pomocą wiersza poleceń zbudować nasz kontener poprzez: docker-compose up

#### * Uruchomienie lokalnie:
1. Należy utworzyć bazę danych Bank, utworzyć użytkownika z nazwą oraz hasłem które można zanelźć w application.properties, nadać użytkownikowi uprawnienia
2. W obydwóch komponentach w pliku application.properties adres do naszej bazy danych
3. W pierwszej kolejnośći uruchomić aplikację Customer a następnie Credit

## Opis działania komponentu Customer

### Wyszukanie klienta po peselu

* Metoda POST
* Ścieżka /customer/search

Przykładowy request:
```
{
  "firstName": "Jan",
  "lastName": "Kowalski",
  "pesel": "80123456789"
}
```
Przykładowy response:
```
{
  "searchResult": [
    {
      "customerId": 1,
      "firstName": "Jan",
      "lastName": "Kowalski",
      "pesel": "80123456789"
    }
  ]
}
```
### Utworzenie klienta
* Metoda POST
* Ścieżka /customer

Przykładowy request:
```
{
  "firstName": "Jan",
  "lastName": "Kowalski",
  "pesel": "80123456789"
}
```
Przykładowy response:
```
{
  "customerId": 1
}
```
### Pobranie klientów
* Metoda POST
* Ścieżka /customer/filtered

Przykładowy request:
```
{
  "customersIds": [1,2]
}
```
Przykładowy response:
```
{
  "customers": [
    {
      "customerId": 1,
      "firstName": "Jan",
      "lastName": "Kowalski",
      "pesel": "80123456789"
    },
    {
      "customerId": 2,
      "firstName": "Adam",
      "lastName": "Nowak",
      "pesel": "90123456789"
    }
  ]
}
```
## Opis działania komponentu Credit

### Utworzenie nowego kredytu
* Metoda POST
* Ścieżka /createCredit

Przykładowy request:
```
{
  "creditName": "Hipoteczny",
  "value": 500000.0,
  "customer": {
    "firstName": "Jan",
    "lastName": "Kowalski",
    "pesel": "90123456789"
  }
}
```
Przykładowy response:
```
{
  "creditId": 1
}
```
### Pobranie wszystkich kredytów
* Metoda GET
* Ścieżka /getCredits
* Wywołanie nie wymaga podania żadnych argumentów wejściowych

Przykładowy response:
```
{
  "credits": [
    {
      "creditId": 1,
      "creditName": "Hipoteczny",
      "value": 500000.0,
      "customer": {
        "firstName": "Jan",
        "lastName": "Kowalski",
        "pesel": "90123456789"
      }
    },
    {
      "creditId": 2,
      "creditName": "Inwestycyjny",
      "value": 350000.0,
      "customer": {
        "fistName": "Jan",
        "lastName": "Kowalski",
        "pesel": "90123456789"
      }
    },
    {
      "creditId": 3,
      "creditName": "Gotówkowy",
      "value": 20000.0,
      "customer": {
        "firstName": "Adam",
        "lastName": "Nowak",
        "pesel": "95123456789"
      }
    }
  ]
}
```
## Baza Danych

* Obydwa komponenty używają bazy danych Bank. 
* Komponent Customer posiada tabele customers, a komponent Credit posiada tabele credits

Przy uruchamianiu aplikacji poprzez docker-compose schemat bazy danych jest tworzony automatycznie wykorzystując plik data.sql znajdujący się w folderze mysql-init
Przy tworzeniu aplikacji lokalnie należy wykorzystać następujące skrypty:
```
create user 'credit_user'@'localhost' identified by 'credit_password';

grant all priviligies on Bank.* to 'credit_user'@'localhost';

create database Bank;

use Bank;

create table customers (
	ID int unsigned primary key not null unique auto_increment,
    FIRST_NAME varchar(20),
    LAST_NAME varchar(20),
    PESEL varchar(11) not null unique
);

create table credits (
	CREDIT_ID int unsigned primary key not null unique auto_increment,
    CUSTOMER_ID int not null,
    CREDIT_NAME varchar(255),
    VALUE double
);
```
