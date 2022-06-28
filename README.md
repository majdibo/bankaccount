# Bank account kata
Bank account kata Think of your personal bank account experience When in doubt, go for the simplest solution Requirements
* Deposit and Withdrawal
* Account statement (date, amount, balance)
* Statement printing

## User Stories
* US 1:
In order to save money
As a bank client
I want to make a deposit in my account

* US 2:
In order to retrieve some or all of my savings
As a bank client
I want to make a withdrawal from my account

* US 3:
In order to check my operations
As a bank client
this is a bank account kata implementation in java

## Requirement
Implementation in plain java using JDK 11.

## Approach 
Using a Test Driven Design and a Domain Driven Design

### Domain Driven Design
* Domain : 
the Account is the aggregate of the domain 
Amount is a value object
Account statement is an entity contained in the account domain
AccountStatements is a repository

* Adapter : 
the in memory implementation of the repository and the csv printer in outputstream implemenation represents adapter to outside world

in the main class we do the configuration and wireing of different component in sake of simplicity (be KISS as possible).
