plugins {
    java
    id("org.springframework.boot") version "2.1.6.RELEASE"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES))
    compile("org.springframework.boot:spring-boot-starter-data-jpa")
    compile("org.springframework.boot:spring-boot-starter-data-rest")
    testCompile("com.h2database:h2:1.3.148")
    testCompile("junit", "junit", "4.12")
    testCompile("org.springframework.boot:spring-boot-starter-test")
}
