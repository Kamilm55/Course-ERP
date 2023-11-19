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
- ### 5. Exception Handling
- [ ] create global exception structure , return response if error throws
- [ ] refactor code