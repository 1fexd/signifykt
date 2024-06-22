rootProject.name = "signify"

pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
        maven { url = uri("https://jitpack.io") }
    }

    resolutionStrategy {
        eachPlugin {
            if (requested.id.id == "com.gitlab.grrfe.common-gradle-plugin") {
                useModule("${requested.id.id}:library:0.0.39")
            }
        }
    }
}

include("lib")

if (System.getenv("JITPACK")?.toBooleanStrictOrNull() != false) {
    include("testing")
}

