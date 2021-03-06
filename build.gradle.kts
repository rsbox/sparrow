plugins {
    kotlin("jvm") version Project.kotlinVersion
    application
}

tasks.withType<Wrapper> {
    gradleVersion = Project.gradleVersion
}

allprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")

    group = "io.rsbox"
    version = Project.version

    repositories {
        mavenLocal()
        mavenCentral()
        jcenter()
    }

    dependencies {
        implementation(kotlin("stdlib"))
        implementation(kotlin("reflect"))

        tinylog()
    }

    tasks {
        compileKotlin {
            kotlinOptions.jvmTarget = Project.jvmVersion.toString()
        }
        compileTestKotlin {
            kotlinOptions.jvmTarget = Project.jvmVersion.toString()
        }
    }
}

apply(plugin = "application")

dependencies {
    implementation(Library.clikt)
    implementation(Library.jgrapht)

    /**
     * Module inclusions
     */
    implementation(project(":deobfuscator"))
    implementation(project(":mapper"))
}

tasks.withType<JavaExec> {
    workingDir = rootProject.projectDir
    main = "io.rsbox.sparrow.Sparrow"
}