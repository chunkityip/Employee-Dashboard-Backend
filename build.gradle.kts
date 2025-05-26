plugins {
	java
	id("org.springframework.boot") version "3.5.0"
	id("io.spring.dependency-management") version "1.1.7"
	id("org.liquibase.gradle") version "2.2.0"
}

group = "employee-management"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")

	implementation("org.liquibase:liquibase-core:4.25.1")

	compileOnly("org.projectlombok:lombok")
	runtimeOnly("org.postgresql:postgresql")
	annotationProcessor("org.projectlombok:lombok")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

liquibase {
	activities.register("main") {
		this.arguments = mapOf(
				"changeLogFile" to "src/main/resources/db/changelog/db.changelog-master.xml",
				"url" to "jdbc:postgresql://localhost:5432/employee_dashboard_db",  // ✅ Replace with your actual DB
				"username" to "user",                            // ✅ Replace with your user
				"password" to "12345",                        // ✅ Replace with your password
				"driver" to "org.postgresql.Driver"
		)
	}
	runList = "main"
}

