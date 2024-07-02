pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        maven { setUrl("https://repo1.maven.org/maven2/") }
        maven {
            setUrl("https://maven.aliyun.com/repository/public")
        }
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { setUrl("https://repo1.maven.org/maven2/") }
        maven {
            setUrl("https://jitpack.io")
        }
        maven {
            setUrl("https://maven.aliyun.com/repository/public")
        }
    }
}

rootProject.name = "KotlinApplication"
include(":app")
 