plugins {
    kotlin("jvm")
    id("net.nemerosa.versioning") version "3.0.0"
    `maven-publish`
}

group = "fe.signify"
version = versioning.info.tag ?: versioning.info.full

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.google.crypto.tink:tink:1.12.0")

//    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = project.group.toString()
            version = project.version.toString()

            from(components["java"])
        }
    }
}
