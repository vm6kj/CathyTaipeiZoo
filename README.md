# CathyTaipeiZoo
CathyTaipeiZoo is an sample Android app using [臺北市資料大平臺 (data.taipei)](https://data.taipei/#/) based on MVP architecture.

There is another project [PodcastLite](https://github.com/vm6kj/PodcastLite) (under development) that was created by using MVVM and more popular architectures, libraries or frameworks such as [Koin](https://insert-koin.io/), [Coroutines](https://developer.android.com/kotlin/coroutines), [Epoxy](https://github.com/airbnb/epoxy), [Paging](https://developer.android.com/topic/libraries/architecture/paging) etc.

- 100% Katlin
- MVP architecture
- Reactive programming
- One Activity + 3 Fragment + DrawerLayout

# Tech Stacks

- [Retrofit](https://github.com/square/retrofit) + [OkHttp](https://square.github.io/okhttp/)
- [Data Binding](https://developer.android.com/topic/libraries/data-binding)
- [RxJava](https://github.com/ReactiveX/RxJava)
- [Glide](https://github.com/bumptech/glide)
- [Timber](https://github.com/JakeWharton/timber) + [Logger](https://github.com/orhanobut/logger)
- [LeakCanary](https://square.github.io/leakcanary/)

# TODO



- [x] [Taipei open data](https://data.taipei/#/dataset/detail?id=48c4d6a7-4b09-4d1f-9739-ee837d302bd1) response contains duplicated item of the same plant, that should be filtered out.
- [ ] Add local datasource by using [Room]([Save data in a local database using Room  | Android Developers](https://developer.android.com/training/data-storage/room)).

# ScreenShot

<div align=left>
    <img src=https://github.com/vm6kj/CathyTaipeiZoo/blob/main/images/CathayTaipeiZoo_1.png width=240 />
    <img src=https://github.com/vm6kj/CathyTaipeiZoo/blob/main/images/CathayTaipeiZoo_2.png width=240 />
    <img src=https://github.com/vm6kj/CathyTaipeiZoo/blob/main/images/CathayTaipeiZoo_3.png width=240 />
    <img src=https://github.com/vm6kj/CathyTaipeiZoo/blob/main/images/CathayTaipeiZoo_4.png width=240 />
    <img src=https://github.com/vm6kj/CathyTaipeiZoo/blob/main/images/CathayTaipeiZoo_5.png width=240 />
</div>
