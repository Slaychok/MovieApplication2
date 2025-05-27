plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
}
kotlin {
    jvmToolchain(21)  // Указываем версию JVM (17 или 21)
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "21"
    }
}

dependencies{
    implementation ("com.squareup.retrofit2:converter-gson:2.11.0")
}