DAY 1
1. Create a project on start.spring.io. Add all dependencies like
   spring web, spring data, jdbc api, lombok etc
2. Import the project and setup application.properties. Add important properties for database,JPA, port number.
3. Turn on your database(Mysql) and create a database.
4. Run the application and check for any errors
5. Create package for POJO,rest,constants,utils,userdao, wrapper
6. Create a user data entity POJO to enter tables for user and check 
whether its reflects in the database.
Add important column such name,email,password etc.
7. Create interface,classes such UserRest,UserService and implementation
for rest api and its function
8. Create a userdao to attach user to database and within
that class, declare function to fetch data
9. In the UserRest, create a rest controller to hit ('/user)
API and create a signup rest controller to signup new user
10. Add the constraints and test the API using postman,

DAY 2
11. Adding functionality of JWT token 
JwtUtils: Handles JWT creation, validation, and extraction of information.
SecurityConfig: Configures Spring Security to enforce security rules.
JwtFilter: A filter that intercepts incoming requests to validate JWT token
12. Adding login functionality that is authenicated by authenicationManger
-the password is checked by Spring Security’s AuthenticationManager, which uses the UserDetailsService 
to load user details from the database and compare the provided password with the stored password

DAY 3
Implementing and adding more APIs
13. Get all users day on by the role admin
14. update the status of the user by the role admin
15. change password functionality for every user

DAY 4
Adding Category POJO and its API
16. Adding new category
17. Update Category
18. Get All Category

