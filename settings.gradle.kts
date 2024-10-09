pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "quickly"
include(":app")
include(":res")

include(":core-ui")

include(":core-logic:common")
include(":core-logic:analytics")
include(":core-logic:authenticate")
include(":core-logic:notification")
include(":core-logic:http")
include(":core-logic:database")
include(":core-logic:repository")

include(":feature:hello")
include(":feature:firebase")
include(":feature:http")
include(":feature:chat")