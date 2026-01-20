## Yatzy

The problem that this code is designed to solve is
explained [here](https://sammancoaching.org/kata_descriptions/yatzy.html)

The codebase contains a Java implementation.


## Getting Started

**Prerequisites**

- Java 17
- Maven 3.9.2 or Gradle
- AssertJ
- Junit5



## Overview

- This project implements Yatzy scoring as a set of interchangeable **Scoring Strategies**.
- Each scoring category (Chance, Yatzy, Ones… Full House) is implemented as a class that conforms to a single interface:
  > **DiceScoringRule#score(int... dice)**

- The client (UI / API / CLI / test harness) uses YatzyGame as the entry point and supplies parameters:
    - a dice roll (int[] dice)
    - a scoring category (Category category)

**YatzyGame** then uses a **RuleRegistry** to resolve the appropriate rule strategy and computes the score.


## Build and Test

**Maven**
- To execute test
  > mvn clean test
- To Run
  > mvn exec:java -Dexec.mainClass="YatzyRunner"

**Gradle**
- To execute test
  > ./gradlew clean test
- To Run
  > ./gradlew run



## Key Concepts

- **Strategy Pattern (Scoring Rules)**
  -- Each scoring rule is a separate class:
    - **ChanceScoringRule**
    - **YatzyScoringRule**
    - **DiceFaceScoringRule**
    - **PairScoringRule**
    - **StraightScoringRule**
    - **FullHouseScoringRule**


- **Registry Pattern (Category → Rule)**
    - **RuleRegistry** maps each **Category** to the correct **DiceScoringRule** strategy.


- **Builder Pattern (Output Model)**
    - A **Roll** is created using **Roll.builder()** to produce an immutable output payload.


- **Output Strategy (Console / File / UI)**
  -- OutputStrategy abstracts how results are displayed.
    - Today we use Console Strategy:
      >**OutputStrategies.consoleOutputStrategy()**



## Architecture

- **High-level components:**
    - **YatzyGame** — entry point used by the client.
    - **Category** — enum representing the scoring category chosen by the player
    - **RuleRegistry** — resolver that returns a **DiceScoringRule** for a category
    - **DiceScoringRule** — common interface for all scoring strategies
    - **Scoring Rules** — concrete strategy implementations listed above
    - **Roll** — value object (builder) containing result data
    - **OutputStrategy** — prints the Roll


## How Scoring Flow Works

- When play(dice, category) is called:
    - **YatzyGame** creates a RuleRegistry
    - The selected **Category** is resolved to a **DiceScoringRule** strategy
    - The strategy computes a score using **score(dice)**
    - A **Roll** object is constructed via Roll.builder()
    - The result is printed via **OutputStrategy**



## Usage

**Client Example**

Client application (CLI/API/UI) should call YatzyGame as below:

    public class ClientApp {
        public static void main(String[] args) {

            int[] dice = {2, 2, 3, 3, 3};
            Category category = Category.FULL_HOUSE;

            YatzyGame game = new YatzyGame();
            game.play(dice, category);
        }
    }

**Sample Output**

Example output (depends on your **consoleOutputStrategy()** implementation and dice values provided):

    ROLL
    You've chosen FULL_HOUSE as score category
    Score: 13
    You've got FULL_HOUSE

OR

    ROLL
    You've chosen FULL_HOUSE as score category
    Score: 0
    You've got NOTHING