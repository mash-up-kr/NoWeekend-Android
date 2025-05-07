import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.dokka.gradle.DokkaExtension
import java.io.File
import java.net.URI

class DokkaConventionPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.plugins.apply("org.jetbrains.dokka")

        project.extensions.configure(DokkaExtension::class.java) {
            dokkaSourceSets.named("main") {
                sourceLink {
                    localDirectory.set(File("src/main/java"))
                    val url = "https://github.com/mash-up-kr/NoWeekend-Android/tree/develop${
                        project.path.replace(
                            ":",
                            "/"
                        )
                    }/src/main/java"

                    remoteUrl.set(URI(url))
                    remoteLineSuffix.set("#L")
                }
            }


            /**
             * dokkaGenerateTask를 사용하여 만든 HTML 문서의 출력 디렉토리 설정
             */
            dokkaPublications.named("html") {
                outputDirectory.set(project.layout.buildDirectory.dir("dokka/html"))
            }

        }

        /**
         * ./gradlew build 시 dokkaGenerateTask가 실행되도록 설정
         */
        project.tasks.named("build") {
            dependsOn(
                project.tasks.named("dokkaGenerate")
            )
        }
    }
}