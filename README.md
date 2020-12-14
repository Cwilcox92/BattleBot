Program # 6

Name: Carlton Wilcox Cosc 4730

Description:

Download the repository
Open repository in Android Studio
From Android Studio navigate to the top of the the page and find the device manager drop down (If using a physical device)
Use a device with a Screen size of 5.7in and any Android Version 7 & up Recommended: Android version 9.
After conneting device to computer, navigate back to the drop down menu and locate phone.
After locating phone press the Green Play button
Wait for App to build and deploy to phone
Enjoy App!
(If using an emulator)
4. From drop down find 'Open AVD Manager'
5. From the manager create a device with a screen size of 5.7 or 5.5
6. Next choose an API level Recommended:API 28, Android version 9 (DO NOT CHOOSE ANYTHING BELOW 24) 
7. Create and name device 
8. Press the Green Play button 
9. Wait for App to build and deploy to phone 10. Enjoy App!

Build:
App was tested on physical device, LG G6.
----------------------------------------------------------------------------------------------------------------------------------
How-To

How to Connect Bot to Server:
- Start the server in the cmd line by running java -jar BatBot161.jar <NumberOfPlayers> (1:for test, 2:for game) in the project dir
- After starting the server run the app on device (or through emulator or Android Studio)
- Next enter localhost IP address and 3012 for the port
- Press (Ready Up!) Button
- You're connected to the server now!

How to Start a Game:
- Open a new command line
- Navigate to project folder
- cd clients
- dir (allows you to see which bot to play against)
- type java -jar (botname).jar localhost 3012
- Press enter
- Good Luck and Have Fun!

Controls:
- Buttons on the left hand side control movement, press each time to move 1 pixel
- Scan and Fire are on the right hand side
- Movement direction determines fire angle, up:90, down:180 etc.
----------------------------------------------------------------------------------------------------------------------------------


* Everything works as expected
