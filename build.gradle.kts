buildscript {
    dependencies {
        classpath("com.google.gms:google-services:4.4.2")
    }
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    id("com.android.application") version "8.9.1" apply false
    id("org.jetbrains.kotlin.android") version "2.0.0" apply false
    id("androidx.navigation.safeargs.kotlin") version "2.5.0" apply false
    id("com.google.gms.google-services") version "4.4.2" apply false
}