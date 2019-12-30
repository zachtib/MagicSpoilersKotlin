buildscript {
    repositories {
        mavenCentral()
    }

    dependencies {
        classpath(kotlin("gradle-plugin", version = "1.3.61"))
    }
}


plugins {
    application
    kotlin("jvm") version "1.3.61"
    kotlin("kapt") version "1.3.61"
}

repositories {
    jcenter()
    mavenCentral()
}

dependencies {
    val coroutines_version = "1.3.2"
    val dagger_version = "2.24"
    val retrofit_version = "2.6.2"
    val okhttp_version = "4.2.1"
    val moshi_version = "1.8.0"
    val jasync_sql_version = "1.0.12"

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_version")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:$coroutines_version")

    implementation("com.google.dagger:dagger:$dagger_version")
    kapt("com.google.dagger:dagger-compiler:$dagger_version")
    kaptTest("com.google.dagger:dagger-compiler:$dagger_version")

    implementation("com.squareup.retrofit2:retrofit:$retrofit_version")
    implementation("com.squareup.retrofit2:converter-moshi:$retrofit_version")

    implementation("com.squareup.okhttp3:okhttp:$okhttp_version")
    implementation("com.squareup.okhttp3:logging-interceptor:$okhttp_version")

    implementation("com.squareup.moshi:moshi:$moshi_version")
    implementation("com.squareup.moshi:moshi-kotlin:$moshi_version")
    kapt("com.squareup.moshi:moshi-kotlin-codegen:$moshi_version")

    implementation("com.github.jasync-sql:jasync-postgresql:$jasync_sql_version")

    testImplementation("junit:junit:4.12")
}

application {
    mainClassName = "com.zachtib.spoilers.MainKt"
}

task("stage") {
    dependsOn("build")
}