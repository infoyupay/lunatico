plugins {
    id("java")
    id("org.openjfx.javafxplugin") version "0.0.13"
    application
}

group = "com.yupay"
version = "1.0.0"

repositories {
    mavenCentral()
}

javafx {
    modules("javafx.controls", "javafx.fxml")
    version = "20.0.1"
}

application {
    mainModule.set("lunatico")
    mainClass.set("com.yupay.lunatico.fxforms.FxLunatico")
}

dependencies {

    implementation("org.jetbrains:annotations:24.0.1")

    // https://mvnrepository.com/artifact/jakarta.persistence/jakarta.persistence-api
    implementation("jakarta.persistence:jakarta.persistence-api:3.1.0")
    // https://mvnrepository.com/artifact/org.eclipse.persistence/eclipselink
    implementation("org.eclipse.persistence:eclipselink:4.0.1")
    // https://mvnrepository.com/artifact/org.postgresql/postgresql
    implementation("org.postgresql:postgresql:42.6.0")

    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}