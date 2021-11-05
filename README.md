# ETitleBar
[![](https://jitpack.io/v/RickyHal/ETitleBar.svg)](https://jitpack.io/#RickyHal/ETitleBar)

一个简单的顶部导航栏创建工具。支持大部分情况下的顶部导航栏需求。

ETitleBar将顶部导航栏区域分为了三份，分别是左中右

![未命名文件 (5).jpg](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/27a09bf1836545c78340f4a7af73c2a3~tplv-k3u1fbpfcp-watermark.image?)

其中左边和右边区域的默认宽高等于设置的导航栏的高度，默认是***48dp***，中间区域的宽度等于屏幕宽度减去2*导航栏高度。当左边或右边区域添加了多个按钮或文字时，其区域宽度会自动扩张挤压中间区域的宽度。

左边区域内的按钮或文字默认居左显示，中间区域内的按钮或文字默认居中显示，右边区域内的按钮居默认右显示。

整个界面的树状结构如下：

```kotlin
|-- DecorView
    |-- RelativeLayout // 封装TitleBar和ContentView的Layout
        |-- RelativeLayout // 顶部导航栏View
            ｜-- ConstraintLayout  // 主要是为了方便适配fitSystemWindows
                ｜-- LinearLayout  // 左边区域Layout
                    ｜-- ImageTextView  // 可以设置文字和图片的View
                    ｜-- ... // 可以添加多个
                ｜-- LinearLayout  // 中间区域Layout
                    ｜-- ImageTextView  // 可以设置文字和图片的View
                    ｜-- ... // 可以添加多个
                ｜-- LinearLayout  // 右边区域Layout
                    ｜-- ImageTextView  // 可以设置文字和图片的View
                    ｜-- ... // 可以添加多个
        |-- ShadowView // 阴影View
        |-- ContentView //真正的 ContentView
```

![未命名文件 (6).jpg](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/5c316cbc1dbd4740a7c3252d89c3a9b7~tplv-k3u1fbpfcp-watermark.image?)
# 依赖配置
Project build.gradle
```groovy
allprojects {
    repositories {
        ...
        maven { url 'https://www.jitpack.io' }
        }
}
```

Module build.gradle
```groovy
dependencies {
    implementation 'com.github.RickyHal:ETitleBar:$latest_version'
}

# 使用方法
Activity的Theme需要使用NoActionBar，例如
```xml
<resources xmlns:tools="http://schemas.android.com/tools">
    <!-- Base application theme. -->
    <style name="Theme.ETitleBar" parent="Theme.MaterialComponents.DayNight.NoActionBar">
        <!-- Primary brand color. -->
        <item name="colorPrimary">@color/purple_500</item>
        <item name="colorPrimaryVariant">@color/purple_700</item>
        <item name="colorOnPrimary">@color/white</item>
        <!-- Secondary brand color. -->
        <item name="colorSecondary">@color/teal_200</item>
        <item name="colorSecondaryVariant">@color/teal_700</item>
        <item name="colorOnSecondary">@color/black</item>
        <!-- Status bar color. -->
        <item name="android:statusBarColor" tools:targetApi="l">?attr/colorPrimaryVariant</item>
        <!-- Customize your theme here. -->
    </style>
</resources>
```
### 创建导航栏
让项目的BaseActivity继承自ETitleBarActivity，然后重写createTitleBar方法即可
```kotlin
abstract class BaseActivity : ETitleBarActivity() {
    override fun createTitleBar(): ETitleBar.Builder {
        return ETitleBar.Builder(this)
            .setTitle("ETitleBar Demo")
            .fitsSystemWindows(true)
            .setLeftButtonPadding(left = 15.dp, right = 15.dp)
            .setLeftButtonIcon(R.drawable.icon_titlebar_back)
            .withShadow(true)
    }
}
```
MainActivity中
```kotlin
class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // 更新标题，需要在setContentView之后调用才会有效果
        setTitle("Hello World")
    }
}
```
### 更新导航栏
```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    updateTitleBar {
        // 在这里面设置导航栏属性即可
        setTitle("Hello world")
        setLeftBackgroundColor(Color.RED)
    }
}
```

### 点击事件监听
在Activity中重写下面三个方法即可
```kotlin
class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // 更新标题，需要在setContentView调用才会有效果
        setTitle("Hello World")
    }

    // 左边按钮点击，index是被点击的左边区域内的第几个按钮，view是对应的按钮，layout是对应的左边区域
    override fun onLeftClick(index: Int, view: View, layout: LinearLayout) {
        Toast.makeText(this, "On left click index=$index view=$view layout=$layout", Toast.LENGTH_SHORT).show()
    }

    override fun onCenterClick(index: Int, view: View, layout: LinearLayout) {
        Toast.makeText(this, "On center click index=$index view=$view layout=$layout", Toast.LENGTH_SHORT).show()
    }

    override fun onRightClick(index: Int, view: View, layout: LinearLayout) {
        Toast.makeText(this, "On right click index=$index view=$view layout=$layout", Toast.LENGTH_SHORT).show()
    }
}
```

### 可用属性
```kotlin
ETitleBar.Builder(this)
    .setLeftButtonText("Back")  // 设置左边按钮文字
    .setLeftButtonText(R.string.app_name)   // 设置左边按钮文字
    .setLeftButtonTextSize(16f) //  设置左边按钮文字字体大小
    .setLeftButtonTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)  //  设置左边按钮文字字体大小
    .setLeftButtonIcon(R.drawable.icon_back)    // 设置左边按钮图标，和文字互斥，只能设置一个
    .setLeftButtonIcon(ContextCompat.getDrawable(this, R.drawable.icon_back))   // 设置左边按钮图标
    .setLeftButtonTextColor(Color.BLACK)    // 设置左边按钮文字颜色
    .setLeftButtonTypeface(Typeface.DEFAULT)    // 设置左边按钮文字字体
    .setLeftButtonPadding(left = 10.dp, top = 0.dp, right = 20.dp, bottom = 0.dp)    // 设置左边按钮Padding
    .setLeftButtonMargin(left = 10.dp, top = 0.dp, right = 20.dp, bottom = 0.dp)    // 设置左边按钮Margin
    .applyLeftButton { imageTextView ->
        // 设置左侧按钮属性
        imageTextView.setText("Cancel")
    }
    .applyLeftLayout { layout ->
        // 设置左侧区域属性
        layout.setHorizontalGravity(Gravity.CENTER)
    }
    .setLeftView(TextView(this))    // 设置自定义的左侧按钮，会清空之前的按钮
    .resetLeftView() // 重置左侧区域，会清空左侧区域内的自定义按钮，并添加默认的按钮
    .addLeftView(TextView(this))    // 添加自定义的左侧按钮
    .setLeftGravity(Gravity.START)  // 设置左边区域Gravity
    .setTitle("ETitleBar Demo") // 设置导航栏标题
    .setTitle(R.string.app_name)    // 设置导航栏标题
    .setTitleColor(Color.BLACK)     // 设置标题字体颜色
    .setTitleTextSize(16f)  // 设置标题字体大小
    .setTitleTextSize(TypedValue.COMPLEX_UNIT_SP, 16f) // 设置标题字体大小
    .setTitleTypeface(Typeface.DEFAULT) // 设置标题字体
    .setTitleViewPadding(left = 0, top = 0, right = 0, bottom = 0) // 设置标题View Padding
    .setTitleViewMargin(left = 0, top = 0, right = 0, bottom = 0) // 设置标题View Margin
    .applyTitleView { imageTextView ->
        // 设置标题View属性
        imageTextView.setText("ETitleBar Demo")
    }
    .applyCenterLayout { layout ->
        // 设置中间区域属性
        layout.setHorizontalGravity(Gravity.START)
    }
    .setCenterView(TextView(this)) // 设置自定义标题View
    .resetCenterView() // 重置中间区域，会清空中间区域内的自定义标题，并添加默认的标题
    .addCenterView(TextView(this))  // 添加自定义标题View
    .setCenterGravity(Gravity.START)    // 设置中间区域Gravity
    .setRightButtonText("More")  // 设置右边按钮文字
    .setRightButtonText(R.string.app_name)   // 设置右边按钮文字
    .setRightButtonTextSize(16f) //  设置右边按钮文字字体大小
    .setRightButtonTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)  //  设置右边按钮文字字体大小
    .setRightButtonIcon(R.drawable.icon_back)    // 设置右边按钮图标，和文字互斥，只能设置一个
    .setRightButtonIcon(ContextCompat.getDrawable(this, R.drawable.icon_back))   // 设置右边按钮图标
    .setRightButtonTextColor(Color.BLACK)    // 设置右边按钮文字颜色
    .setRightButtonTypeface(Typeface.DEFAULT)    // 设置右边按钮文字字体
    .setRightButtonPadding(left = 10.dp, top = 0.dp, right = 20.dp, bottom = 0.dp)    // 设置右边按钮Padding
    .setRightButtonMargin(left = 10.dp, top = 0.dp, right = 20.dp, bottom = 0.dp)    // 设置右边按钮Margin
    .applyRightButton { imageTextView ->
        // 设置右侧按钮属性
        imageTextView.setText("More")
    }
    .applyRightLayout { layout ->
        // 设置右侧区域属性
        layout.setHorizontalGravity(Gravity.CENTER)
    }
    .setRightView(TextView(this))    // 设置自定义的右侧按钮，会清空之前的按钮
    .resetRightView() // 重置右侧区域，会清空右侧区域内的自定义按钮，并添加默认的按钮
    .addRightView(TextView(this))    // 添加自定义的右侧按钮
    .setRightGravity(Gravity.START)    // 设置右边区域Gravity
    .setShadowColor(Color.BLACK)    // 设置导航栏阴影颜色
    .setShadowDrawable(R.drawable.shadow_titlebar)  // 设置图片作为导航栏阴影
    .setShadowDrawable(ContextCompat.getDrawable(this, R.drawable.shadow_titlebar)) // 设置图片作为导航栏阴影
    .setShadowHeight(5.dp)  // 设置导航栏阴影高度
    .withGradient(true) // 导航栏阴影是否加上过渡效果
    .withShadow(true)   // 是否显示导航栏阴影
    .setTitleBarBackgroundColor(Color.WHITE) // 设置导航栏背景色
    .setTitleBarBackground(R.drawable.bg_titlebar)  // 设置导航栏背景图片
    .setLeftLayoutBackgroundColor(Color.WHITE)  // 设置左侧区域背景色
    .setLeftLayoutBackground(R.drawable.bg_titlebar)    // 设置左侧区域背景图
    .setCenterLayoutBackgroundColor(Color.WHITE)    // 设置中间区域背景色
    .setCenterLayoutBackground(R.drawable.bg_titlebar)  // 设置中间区域背景图
    .setRightLayoutBackgroundColor(Color.WHITE) // 设置右边区域背景色
    .setRightLayoutBackground(R.drawable.bg_titlebar)   // 设置右边区域背景图
    .setTitleBarAlpha(1f) // 设置导航栏透明度
    .fitsSystemWindows(true)    // 是否在导航栏顶部加上一个状态栏的Padding
    .overlapTitleBar(false) // 导航栏和ContentView是否重叠
    .setTitleBarHeight(48.dp)   // 设置导航栏高度
    .hasLeftButton(true) // 是否显示左侧区域
    .hasCenter(true)    // 是否显示中间区域
    .hasRightButton(true)   // 是否显示右边区域
    .setOnClickListener(object : OnTitleBarClickListener {
        // 左边区域按钮被点击
        override fun onLeftClick(index: Int, view: View, layout: LinearLayout) {

        }

        // 中间区域按钮被点击
        override fun onCenterClick(index: Int, view: View, layout: LinearLayout) {

        }

        // 右边区域按钮被点击
        override fun onRightClick(index: Int, view: View, layout: LinearLayout) {

        }
    })
    .build()
```