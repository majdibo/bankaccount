# Bank account kata
this is a bank account kata implementation in java

## Java version
implementation in plain java using JDK 11.

## approach 
using a Test Driven Design and a Domain Driven Design

### Domain Driven Design
* Domain : 
the Account is the aggregate of the domain 
Amount is a value object
Account statement is an entity contained in the account domain
AccountStatements is a repository

* Adapter : 
the in memory implementation of the repository and the csv printer in outputstream implemenation represents adapter to outside world

in the main class we do the configuration and wireing of different component in sake of simplicity (be KISS as possible).
