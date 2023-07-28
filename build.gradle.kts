/*
 *      This file is part of Lunatico project.
 *
 *     Lunatico is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 *     Foobar is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License along with Foobar. If not, see <https://www.gnu.org/licenses/>.
 */

import java.nio.file.Files
import java.nio.file.Paths

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

    //Annotations deps.
    implementation("org.jetbrains:annotations:24.0.1")

    // https://mvnrepository.com/artifact/jakarta.persistence/jakarta.persistence-api
    implementation("jakarta.persistence:jakarta.persistence-api:3.1.0")
    // https://mvnrepository.com/artifact/org.eclipse.persistence/eclipselink
    implementation("org.eclipse.persistence:eclipselink:4.0.1")
    // https://mvnrepository.com/artifact/org.postgresql/postgresql
    implementation("org.postgresql:postgresql:42.6.0")

    //Test dependencies.
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

//Tasks to copy implementation dependencies into /libs/mods directory.
configurations.getByName("implementation").isCanBeResolved = true

tasks.register<DefaultTask>("makeBash") {
    val jarFileName = tasks.jar.get().archiveFileName.get()
    Files.writeString(
            Paths.get("$buildDir", "libs", "lunatico.sh"),
            "java --module-path mods/ --add-modules ALL-MODULE-PATH -jar $jarFileName"
    )
}
tasks.register<DefaultTask>("makeBat") {
    //java --module-path .\mods\ --add-modules ALL-MODULE-PATH -jar .\lunatico-1.0.0.jar
    val jarFileName = tasks.jar.get().archiveFileName.get()
    Files.writeString(
            Paths.get("$buildDir", "libs", "lunatico.bat"),
            "java --module-path .\\mods\\ --add-modules ALL-MODULE-PATH -jar .\\$jarFileName"
    )
}

tasks.register<Copy>("copyMods") {
    into("$buildDir/libs/mods")
    from(configurations.getByName("implementation"))
}

tasks.withType<Jar> {
    dependsOn("copyMods", "makeBash", "makeBat")
    manifest.attributes["Main-Class"] = application.mainClass
    manifest.attributes["Created-By"] = "Infoyupay SACS"
}