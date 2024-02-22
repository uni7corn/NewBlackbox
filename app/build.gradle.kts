plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {

    ndkVersion = (rootProject.ext["ndkVersion"] as String)
    namespace = (rootProject.ext["appid"] as String)
    compileSdk = (rootProject.ext["compileSdk"] as Int)
    defaultConfig {
        javaCompileOptions {
            annotationProcessorOptions {
                argument("includeCompileClasspath", "false")
            }
        }

        applicationId = "top.niunaijun.blackbox"
        minSdk = (rootProject.ext["minSdk"] as Int)
        //noinspection ExpiredTargetSdkVersion
        targetSdk = (rootProject.ext["targetSdk"] as Int)
        versionCode = (rootProject.ext["versioncode"] as Int)
        versionName = (rootProject.ext["appversion"] as String)
        vectorDrawables {
            useSupportLibrary = true
        }
        multiDexEnabled = false
    }
    buildTypes {
        release {
            resValue ("string", "app_name", "BOX-alpha")
            isMinifyEnabled = false
            isJniDebuggable = false
            isDebuggable = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isJniDebuggable = true
            isDebuggable = true
            resValue ("string", "app_name", "BOX-debug")
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures{
        buildConfig = true
        viewBinding = true

    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    buildFeatures {
        aidl = true
        prefab = true
        viewBinding = true
    }

   splits {
        abi {
            isEnable = true
            reset()
            include("armeabi-v7a", "arm64-v8a")
            isUniversalApk = false
        }
    }
}

val ktxversion = rootProject.ext["ktx_version"] as String
val stdlib_version = rootProject.ext["stdlib_version"] as String
val googlematerial = rootProject.ext["googlematerial"] as String

dependencies {
    implementation(project(":Bcore"))
    implementation("androidx.core:core-ktx:$ktxversion")
    //noinspection GradleDependency
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:$stdlib_version")
    implementation("androidx.appcompat:appcompat") {
        exclude(group = "com.android.support", module = "support-compat")
    }
    // implementation("androidx.appcompat:appcompat:$appcompat")
    implementation("com.google.android.material:material:$googlematerial")

    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    implementation("androidx.viewpager2:viewpager2:1.0.0")
    implementation("androidx.activity:activity-ktx:1.8.2")
    implementation("androidx.fragment:fragment-ktx:1.6.2")
    implementation("androidx.preference:preference-ktx:1.2.1")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")

    // ViewModel, LiveData, Lifecycle
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")

    // Third-party libraries
    implementation("com.tbuonomo:dotsindicator:4.3")
    implementation("com.afollestad.material-dialogs:core:3.3.0")
    implementation("com.afollestad.material-dialogs:input:3.3.0")
    implementation("com.github.nukc.stateview:kotlin:2.2.0")
    implementation("com.roger.catloadinglibrary:catloadinglibrary:1.0.9")
    implementation("com.github.Ferfalk:SimpleSearchView:0.2.1")
    implementation("com.github.Othershe:CornerLabelView:1.0.0")

    // osmdroid
    implementation("org.osmdroid:osmdroid-android:6.1.18")

    // Adapter
    implementation("com.gitee.cbfg5210:RVAdapter:0.3.7")

    // Floating view
    implementation("com.imuxuan:floatingview:1.6")

}