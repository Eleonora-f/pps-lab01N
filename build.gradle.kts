/*plugins {
    alias(libs.plugins.java)
}*/
plugins {
    java
}

subprojects {
    //apply(plugin = rootProject.libs.plugins.java.get().pluginId)
    apply(plugin = "java")
    group = "it.unibo.pps"

    repositories {
        mavenCentral()
    }

    java {
        toolchain {
            languageVersion.set(
                JavaLanguageVersion.of(
                    rootProject.libs.versions.jdk.get().toString()
                )
            )
        }
    }

    dependencies {
        testImplementation(platform(rootProject.libs.junit.bom))
        testImplementation(rootProject.libs.junit.jupiter)
        testRuntimeOnly(rootProject.libs.junit.launcher)
    }

    tasks.test {
        useJUnitPlatform()

        javaLauncher = javaToolchains.launcherFor {
            languageVersion.set(
                JavaLanguageVersion.of(
                    rootProject.libs.versions.jdk.get().toString()
                )
            )
        }
    }
}