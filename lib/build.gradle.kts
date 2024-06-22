import fe.plugin.library.LibraryConfig.Companion.library
import fe.plugin.library.bundle

plugins {
    id("com.gitlab.grrfe.common-gradle-plugin")
}

library("fe.signify") {
    jvm.set(17)
}

dependencies {
    bundle("com.google.crypto.tink:tink:1.13.0") {
        minimize = true
    }
}
