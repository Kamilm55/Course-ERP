 ## TASKS
 ### 1.REFRESH TOKEN
- [x] You must refresh token when access token expired! To do this create request path auth/refresh , 
- [x] it can't be accessible if access token does not expire (it means we already authenticated with token)
- [x] todo this -> ("/v1/auth/**").anonymous();//accessible to only unauthenticated users
- [x] if token expires or there is no token -> we can request and check this token type is REFRESH_TOKEN or not
- [x] if REFRESH_TOKEN is valid we can send access and refresh token again -> it means we re-login user we authenticate user without credentials. 
- ### some unexpected actions:
  - [x] we can use refresh method or login with refresh token but refresh token is only for refresh our access and refTokens
  - [ ] we did not invalid old tokens when we get new one (IN LOG-OUT)
    - if we have token we cannot send request  getting new one (for this refresh path-> .anonymous())
    - if we logout from the app , invalid tokens 
  ### 2. Refactor
- [x] Create constants package and add prefix bearer or others
- [x] Refactor authorizationFilter 
  ### 3. Logout
- [x] create only controller that send custom response
- [ ] COMPLETE logout logic
  ### 4. Response Model
- [x] add meta(field) as a class in baseResponse model
  ### 5. Exception Handling
- [x] create global exception structure , return response if error throws
- [x] refactor code
- [x] create success response message refactor current code
- [x] create notFoundExceptionType enum 
- [x] make it customizable response -> %s can't find for %s , (user can't find for {email="kasjdnk@gmail.com"}) 
- [x] Create new exception type user_already_registered , refactor exception code it should be accept custom message
 and display its own key and message

  ### 6. Register
- [x] create simple controller and service , then check user exist or not
- [x] convert registerPayload to User for registering , use -> mapStruct add maven dependencies
- [x] create service that get OWNER role with static method
- [x] test => re-register ,  does it throw an error
- [x] phoneNumber must be unique if the same throw err -> "message": "user already registered with this phone_number"
- [x] create response message that can be dynamic it check registered with email:example@gmail.com or  phone_number:121451
- [x] create mapstruct for payload to course
- [x] insert course (with default values and payload info)
- [x] create Branch and insert default branch info
- [x] insert employee as default
- ### some unexpected actions:
  - [ ] i can insert same name courses (how can i differentiate) ?
  - [ ] when user sign up employees_branches relation not change
  

  ### 7. Migrations
- [x] create course table 
- [x] insert default value for OWNER in role table
- [x] create students and countries table
- [x] alter employee table , drop constraint and create additional table that keep 2 keys and type of emp

  ### 8. Project tasks from github