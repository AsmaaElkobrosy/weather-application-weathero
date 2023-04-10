//package com.example.wethero.localdatabase
//
//import androidx.room.Room
//import androidx.test.core.app.ApplicationProvider
//import androidx.test.ext.junit.runners.AndroidJUnit4
//import androidx.test.filters.SmallTest
//import com.example.wethero.Model.Current
//import com.example.wethero.Model.Welcome
//import kotlinx.coroutines.ExperimentalCoroutinesApi
//import kotlinx.coroutines.flow.first
//import kotlinx.coroutines.test.runBlockingTest
//import org.hamcrest.MatcherAssert
//import org.junit.After
//import org.junit.Before
//import org.junit.Rule
//import org.junit.Test
//import org.junit.runner.RunWith
//
//
//@ExperimentalCoroutinesApi
//@RunWith(AndroidJUnit4::class)
//@SmallTest
//class WeatherDaoTest {
//    @get:Rule
//    var instantExecutorRule = InstantTaskExecutorRule()
//
//
//    lateinit var db:WeatherDatabase
//    lateinit var dao:WeatherDao
//
//    @Before
//    fun setUp() {
//        db = Room.inMemoryDatabaseBuilder(
//            ApplicationProvider.getApplicationContext(),
//            WeatherDatabase::class.java
//        ).
//        allowMainThreadQueries().build()
//
//        dao = db.getWeatherDao()
//    }
//
//    @After
//    fun tearDown() {
//        db.close()
//    }
//
//    @Test
//    fun getAllFavourites()=runBlockingTest {
//        // Given
//        val welcome1 = Welcome(
//            lat = 0.0, lon = 0.0, timezone = "", timezoneOffset = 0, current = Current(
//                dt = 0,
//                sunrise = null,
//                sunset = null,
//                temp = 0.0,
//                feelsLike = 0.0,
//                pressure = 0,
//                humidity = 0,
//                dewPoint = 0.0,
//                uvi = 0.0,
//                clouds = 0,
//                visibility = 0,
//                windSpeed = 0.0,
//                windDeg = 0,
//                windGust = 0.0,
//                weather = listOf(),
//                pop = null
//            ), hourly = listOf(), daily = listOf()
//        )
//        dao.insertWeather(welcome1)
//
//        val welcome2 = Welcome(
//            lat = 0.0, lon = 0.0, timezone = "", timezoneOffset = 0, current = Current(
//                dt = 0,
//                sunrise = null,
//                sunset = null,
//                temp = 0.0,
//                feelsLike = 0.0,
//                pressure = 0,
//                humidity = 0,
//                dewPoint = 0.0,
//                uvi = 0.0,
//                clouds = 0,
//                visibility = 0,
//                windSpeed = 0.0,
//                windDeg = 0,
//                windGust = 0.0,
//                weather = listOf(),
//                pop = null
//            ), hourly = listOf(), daily = listOf()
//        )
//        dao.insertWeather(welcome2)
//
//
//        val welcome3 = Welcome(
//            lat = 0.0, lon = 0.0, timezone = "", timezoneOffset = 0, current = Current(
//                dt = 0,
//                sunrise = null,
//                sunset = null,
//                temp = 0.0,
//                feelsLike = 0.0,
//                pressure = 0,
//                humidity = 0,
//                dewPoint = 0.0,
//                uvi = 0.0,
//                clouds = 0,
//                visibility = 0,
//                windSpeed = 0.0,
//                windDeg = 0,
//                windGust = 0.0,
//                weather = listOf(),
//                pop = null
//            ), hourly = listOf(), daily = listOf()
//        )
//        dao.insertWeather(welcome3)
//
//        // When
//        val results = dao.getAllFavourites().first()
//
//        // Then
//        MatcherAssert.assertThat(results.size, is (3))
//    }
//
//
//
//}