# Salt Spreader Device with electric imp002 Android App and Weather Database

Video presentation of the project: https://youtu.be/LWLyDzw4wiM

This is a salt spreader device that can be set up to spread salt automatically whenever the weather forecast calls for snow. The device is connected to a weather forecast database, and whenever the weather forecast calls for snow the device will spreads salt automatically. The amount of salt it spreads depends on the centimeters of snowfall predicted by the weather forecast. The salt spreader device is controlled by electric imp002 which is an IoT microcontroller.  
The user can setup and control the microcontroller through an Android mobile app. The mobile app called "Device Controller" can be used to set up the location where the salt spreader device is displayed.  This will allow the algorithm running on electric imp002 to get weather forecast data for the correct city. In addition to that, this allow the device to be used anywhere. For example, a person that buys the device in Winnipeg, can use the device in Calgary just by entering the information of the city on the Android app. Also, the user can setup how many times he/she wants the device to query the database per day. This allows users that live in cities with higher snowfall probabilities to set the microcontroller to query the database more often, and users that live in cities with less chances of snowfall can set up the microcontroller to query the database less often. In addition to that, the user can turn on and off the device through the Android app. This will allow to turn on the device during the winter months and then off in during the other seasons. Lastly, but not less important, the user can also make the device spread snow through the Android app. As we know, the weather forecast sometimes is not accurate and we may need to make the device spread salt.

Components required for this project:
- Android mobile application
- Weather database
- Electric imp
- Device to spread salt (including electric circuits and the physical structure to spread salt)
