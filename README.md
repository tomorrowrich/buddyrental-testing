# 📌 README: Run This Project on IntelliJ IDEA

## 🚀 Prerequisites
- **Java 17+** (Recommended: Java 23)  
- **IntelliJ IDEA** (Community or Ultimate)  
- **Gradle 7+**  
- **Git** (For cloning the repo)  

---

## How to use this repo

### Step 1: Clone the Repository

```sh
git clone <repo_url>
cd <repo_folder>
```

### Step 2: Open the Project in IntelliJ
1.	Open IntelliJ IDEA → Select “Open” → Choose the project folder.
2.	Set Project SDK to Java 17+:
    - File → Project Structure → Project → SDK → Java 17+.

### Step 3: Build the Project & Install Dependencies

```
./gradlew clean build
```
For Windows:

```
gradlew.bat clean build
```

### Step 4: Run Tests

- **Run Tests from IntelliJ** (Recommend)
	1.	Open CucumberTestRunner.kt.
	2.	Click the green play button ▶ next to @RunWith(Cucumber::class).
	3.	View results in the Run tab.

- Run All Tests

```
./gradlew test
```

- Run Specific Tests by Tags

```
./gradlew test --tags "@UserRegistration"
./gradlew test --tags "@UserLogin"
./gradlew test --tags "@UserRegistration or @UserLogin"
```

### Step 5: Debugging & Reports
- View Test Reports:
  - Open any `.html` file in a browser.
  - Stdout terminal output
- Re-run failed tests:

```
./gradlew test --rerun-tasks
```

- Enable Debug Logs: Modify `logback.xml`

```
<root level="DEBUG">
    <appender-ref ref="STDOUT" />
</root>
```

---

## Troubleshooting

### Java Version Issues

If you get a version error:

```
java -version
gradle -version
```

If not Java 17+, update and set JAVA_HOME.

### Gradle Issues

```
./gradlew wrapper --gradle-version 7.5
./gradlew clean build
```

### Feature Files Not Recognized
1.	Right-click src/test/resources → Mark Directory as “Test Resources Root”.
2.	Restart IntelliJ & rebuild the project.


