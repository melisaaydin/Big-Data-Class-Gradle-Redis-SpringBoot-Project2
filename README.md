# Big Data Project 2

## Project Description
This project is a Spring Boot application designed for big data processing and management. It integrates various technologies to provide a robust solution for handling large datasets, including data storage, processing, and web-based interaction.

## Technologies Used
- **Spring Boot**: Framework for building the application.
- **Java 11**: Programming language.
- **MySQL**: Relational database for persistent storage.
- **Spring Data JPA**: For database interaction.
- **Redis**: In-memory data structure store, used for caching or fast data access.
- **Apache Hadoop**: Distributed storage and processing of large datasets (HDFS).
- **Apache Spark**: Unified analytics engine for large-scale data processing.
- **Lombok**: To reduce boilerplate code.

## Setup Instructions
1.  **Clone the repository:**
    ```bash
    git clone <repository_url>
    cd bigdata-project2
    ```
2.  **Database Setup:**
    - Ensure MySQL is installed and running.
    - Create a database named `bigdata_project2` (or as configured in `application.properties`).
    - Update `src/main/resources/application.properties` with your MySQL credentials.

3.  **Redis Setup:**
    - Ensure Redis is installed and running.

4.  **Hadoop and Spark Setup (if running locally):**
    - Ensure Apache Hadoop and Apache Spark are properly set up in your environment if you intend to run local Hadoop/Spark jobs. This project assumes a configured environment for these components.

5.  **Build the project:**
    ```bash
    ./gradlew clean build
    ```

## How to Run
1.  **Run the Spring Boot application:**
    ```bash
    java -jar build/libs/bigdata-project2-0.0.1-SNAPSHOT.jar
    ```
    Alternatively, you can run it directly using Gradle:
    ```bash
    ./gradlew bootRun
    ```

2.  The application will be accessible at `http://localhost:8080` (default port).

## API Endpoints
(To be added based on `EmployeeController.java` and other controllers)


