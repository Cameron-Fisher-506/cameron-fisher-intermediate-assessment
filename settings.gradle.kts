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
        maven { url = uri("https://developer.huawei.com/repo/") }
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        // Configure the Maven repository address for the HMS Core SDK.
        maven { url = uri("https://developer.huawei.com/repo/") }
    }
}

rootProject.name = "TodoApp"
include(":app")
include(":composeCoreLib")