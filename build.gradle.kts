// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    //Added by DG am 24.11.2026
    id("com.google.gms.google-services") version "4.6.0" apply false
}