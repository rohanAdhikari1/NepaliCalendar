# NepaliCalendar

# Features
- AD, BS Calendar (with or without event)
- Date Conversion from AD to BS and vice versa
- Horizontal Calendar (Both AD , BS)
- Highly Customized
- Nepali And English language Full support

# Installation
>Step 1. Add the JitPack repository to your build file

```gradle
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  ```
  
  >Step 2. Add the dependency

```gradle
dependencies {
	        implementation 'com.github.rohanAdhikari1:NepaliCalendar:1.0.0'
	}
```

# Using Event Calendar (For AD or BS)
- **Add MeroCalendarView to your layout file**
```xml
   <com.roan.NepaliCalendar.NepaliCalendarView
    android:id="@+id/mCalendarView"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_margin="8dp"
   />
```
- **Then in your activity or Fragment add Configuration as your requirement as below**
- 1.1 Setup DateClick Listener

```kotlin
   val dateClickListener = object : DateClickListener {
            override fun onDateClick(dateModel: DateModel) {
                Log.d("d", "clicked date is ${dateModel.formattedAdDate}")
            }
        }
```
- 1.2 Setup MonthChange Listener
```kotlin
   val monthChangeListener = object : MonthChangeListener {
            override fun onMonthChange( startDateOfThisMonth: DateModel, endDateOfThisMonth: DateModel, adYear: Int, adMonth: Int) {
            //perform action or set event of corresponding month from here
          }
        }   
 ```
 - 1.3 Set Calendar Type
```kotlin
   val calendarType=CalendarType.BS 
   //for Ad calendar use,  val calendarType=CalendarType.AD
```
   
 - 1.4 Set Language
```kotlin
   val language=LocalizationType.NEPALI_NP
   //for english language use, val language=LocalizationType.ENGLISH_US
```
 

- 1.5 Finally Build calendar using above configuraion as follow
```kotlin 
   mCalendarView.setCalendarType(calendarType)
            .setLanguage(language)
            .setOnDateClickListener(dateClickListener)
            .setOnMonthChangeListener(monthChangeListener)
            .setEvent(fakeEventList)
            .build()
 ```
 
<br />
<br />
 
# Using Date Converter (AD to BS and vice versa)
 
**To convert AD to BS**
- Get (AD) year, month, day and pass to the converter of the library as given below
```kotlin  
   val year:Int=2021
   val month=7  //month number should be from 1 to 12 (eg: 1=jan, 12=dec)
   val day=12
   val convertedAdDate=MeroDateConverter.convertAdToBs(year, month, day)
```
 
**To convert BS to Ad**
- Get (nepali/BS) year, month, day and pass to the converter of the library as below
```kotlin
   val year=2078
   val month=3 //from 1 to 12 (eg: 1=baishakh, 12=chaitra_
   val day=28
   val convertedBsDate=MeroDateConverter.convertBsToAd(year, month, day)
 ```
 
<br />
<br />


# Using Horizontal Calendar
- **Add MeroHorizontalCalendarView on xml layout**
```xml
   <com.rohan.NepaliCalendar.HorizontalNepaliCalendarView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:id="@+id/horizontalCalendarView"
    />

```
- **After that in your activity or fragment, add configuration**

- Set up Date click listener to observe clicked date
```kotlin 
   val dateClickListener = object : DateClickListener {
            override fun onDateClick(dateModel: DateModel) {
                Log.d("d","data is ${dateModel.localizedFormattedDate}")
            }
        }
```
- build horizontal calendar
```kotlin
   var calendarType = CalendarType.AD  //for bs use, CalendarType.BS
   var language = LocalizationType.ENGLISH_US //for nepali language use, LocalizationType.ENGLISH_US
   binding.horizontalCalendarView.setCalendarType(calendarType)
                    .setLanguage(language)
                    .setOnDateClickListener(dateClickListener)
                    .build()
```
- **Additional Things on Horizontal Calendar View**
- To get previous and next month horizontal calendar, if ivNext is view for getting next month, then on clicking this button perform following action
```kotlin
   ivNext.setOnClickListener {
                horizontalCalendarView.setNextMonthDate()
            }
```
- Similarly on clicking previous button, perform following action
```kotlin
   ivPrevious.setOnClickListener {
                horizontalCalendarView.setPreviousMonthDate()
            }
```
 
[![](https://jitpack.io/v/rohanadhikari1/NepaliCalendar.svg)](https://jitpack.io/#rohanadhikari1/NepaliCalendar)
