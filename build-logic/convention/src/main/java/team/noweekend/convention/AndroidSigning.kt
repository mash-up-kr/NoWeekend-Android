package team.noweekend.convention

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import java.io.FileInputStream
import java.util.Properties

internal fun Project.configureAndroidSigning(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    commonExtension.apply {
        signingConfigs {
            val keystoreProperties = Properties().apply {
                load(FileInputStream("${rootDir}/keystore.properties"))
            }
            create("dev") {
                storeFile = file("${keystoreProperties["storeFileDev"]}")
                storePassword = "${keystoreProperties["storePassword"]}"
                keyAlias = "${keystoreProperties["keyAlias"]}"
                keyPassword = "${keystoreProperties["keyPassword"]}"
            }
            create("qa") {
                storeFile = file("${keystoreProperties["storeFileQa"]}")
                storePassword = "${keystoreProperties["storePassword"]}"
                keyAlias = "${keystoreProperties["keyAlias"]}"
                keyPassword = "${keystoreProperties["keyPassword"]}"
            }
            create("prod") {
                storeFile = file("${keystoreProperties["storeFileProd"]}")
                storePassword = "${keystoreProperties["storePassword"]}"
                keyAlias = "${keystoreProperties["keyAlias"]}"
                keyPassword = "${keystoreProperties["keyPassword"]}"
            }
        }
    }
}