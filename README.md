# QR /Bar code scanner Generator application

This application has two tasks : 

-The first one is to generate a QR code by enterring data and you can save this code on the phone Gallery by clicking on the "save" button.
-The second one is to scan a QR or a Bar code and after this operation , the result will show in the buttom of the screen and it will automatically saved on a Firebase Database

-In the scan, the app asks for permission to activate the camera the first time and then it will display a message

*This message will be : 
           -“PERMISSION_GRANTED” if the user accepts the activation of the camera 
             OR
           -“PERMISSION_DENIED” if the user refuses the activation of the camera 
 -Before scanning, a message will be displayed "scanner started"
 -while scanning, if we do an abrupt exit , a message will be displayed "scanner stopped" and the application will be stopped and the camera will automatically be closed 
 
 
 -When we open this application ,a first activity of progress is going to be displayed at the beginning then the menu activity and here you just have to choose which option if generating a code or scanning a code and the the result will be displayed if in the form of a link or a numeric code (in the case scan) or in the form of a QR code with an image format (in the case of generation).
