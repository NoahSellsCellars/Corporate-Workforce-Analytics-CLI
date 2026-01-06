# Corporate Workforce Analytics CLI

**Author:** Noah Sellers  
**Language:** Java  
**Build System:** Make / JDK

## Overview

The **Corporate Workforce Analytics** tool is a Java-based command-line application designed to process, model, and analyze organizational data. 

It demonstrates the application of **Object-Oriented Programming (OOP)** principles to solve real-world business problems. The system ingests raw CSV employee records, maps them into a dynamic object model, and generates actionable insights regarding retention, compensation, and organizational structure.

## Key Features

* **Data Ingestion Pipeline:** robust parsing of CSV datasets (`employee_data.csv`) into memory.
* **OOP Architecture:**
    * **Encapsulation:** Private data fields with public accessors to ensure data integrity.
    * **Object Relationships:** Dynamic association between `Employee` entities and `Department` containers.
* **Analytics Engine:**
    * **Compensation Analysis:** Calculates average salaries per department and identifies minimum salary baselines.
    * **Retention Tracking:** Flags employees with low tenure (< 4 years) for retention risk assessment.
    * **Leadership Mapping:** Identifies and aggregates department leads across the organization.
    * **Tenure logic:** Algorithms to identify the longest-serving employees company-wide.

---

## Technical Design

The application is structured around three core components:

1.  **`Employee.java` (The Model):** Represents the individual unit of data. Handles state (salary, tenure, position) and data formatting.
2.  **`Department.java` (The Container):** Manages collections of employees. Contains the business logic for aggregation (averaging salaries, filtering lists). Uses `StringBuilder` for memory-efficient text processing.
3.  **`Main.java` (The Controller):** Orchestrates file I/O, error handling, and the execution of analysis reports.

---

## How to Run

### Prerequisites
* Java Development Kit (JDK) 8 or higher
* Make (Optional, for build automation)

### 1. Build the Project

**Method 1: Using Make (Recommended)**
```bash
make
