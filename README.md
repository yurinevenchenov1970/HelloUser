# HelloUser
Just the first Android project

In the project we ask user to input Login, Password and Phone Number.
When the user being registered, that has to input the password and if the input is correct, user gets greeting "Hello, [registered] UserName!' and picture. In case of error android error picture appears. When user push "Back", app asks to confirm or escape it closing.

There are few libs are used in the project:
  1. Input Mask RedMadRobot - for evaluating user input (using Mask on input fields)
  2. Picasso - for insert pictures
  3. ButterKnife - to uncomplicated binding of Views and callbacks injecting (listening to user input)
  4. Jasypt - to safely storing of the Password encrypted
  5. Crouton - to show in-app notifications instead of Toasts
  
  ![howitworks](https://user-images.githubusercontent.com/29121233/28184033-6eb9bffc-681a-11e7-827c-c913ce698e60.gif)
