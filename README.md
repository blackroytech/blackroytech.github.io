## Enhancment One - Biometric Feature 

For the **first enhancement**, I chose to improve the login feature for my application. For the original application, the login feature was a simple username and password input field, both of which were hardcoded in the program. To enhance this feature, I implemented the Android Biometric API, which allowed me to develop a biometric authentication feature that allows authorized users to use the Touch ID to access and modify the inventory. For an easier user experience, a successful message appears if access is granted; however, if the user fails to add a fingerprint to their device, the incorrect fingerprint is used, or the fingerprint feature is not enabled in the device settings, a correlating error message appears denying access. With this feature, I reduced security vulnerabilities and modernized the app. I improved my code quality and readability by commenting on the steps in each file that handles the code and permissions, demonstrating my ability to follow the principles of clean coding. I showcased my abilities and skills in implementing UI design, interacting with UI components in Java, and implementing light security through login biometric credentials using an Android security framework. 

**Course Outcomes Achieved**

With this enhancement, I was able to develop a security mindset that anticipates adversarial exploits in software architecture and designs to expose potential vulnerabilities, mitigate design flaws, and ensure privacy and enhanced security of data and resources. I mitigated risks by eliminating the hardcoded strings that were used in the original program prevents user credentials from being potentially exposed if the application was to be reversed engineered. Adding a security layer such as the Biometric API, allows the user's data to be protected and lowers the chance of exploitation by validating the user's identity.

**HOW TO USE**

* On Device: Ensure that there is a security pin set up and fingerprint access is enabled in device / emulator settings
* While running application in the emulator:
    * Press Login on the screen


https://github.com/user-attachments/assets/3843d5cc-f0e9-4ebc-bd49-264f25ccf748



      
   * Once Touch ID pops up touch the 3 dots in the top right corner to access extended controls

https://github.com/user-attachments/assets/88d469aa-f573-4a8f-b13a-f7f16613f06a



   * Extened Controls: Go to the fingerprint tab and select your fingerprint, for my application it is "1" and press Touch Sensor



https://github.com/user-attachments/assets/27e3b880-9a73-492e-bf33-f32bc89398f5




