# Accounts Microservice. 

# Cards Microservice. 
# Loans Microservice. 

# version1
shows how spring boot config variables are managed, as well as spring profiles. 

For passing variables to the application, we can do the following: 
1. use env 
2. use JVM 
4. use CMD Line args

For activating profiles, we can do the following. 
1. use spring boot profiles. (Application.yaml)
2. use CMD line args as well

### cavets
1. Security vulnerabilities as the app variables can be passed in code, accessible to 
   a number of people 
2. For the config to be applied, we need to restart the app. Which is non-ideal for prodn
3. The passing of variables is hard, as it might require multiple profiles, and 
   configurations 

NB: Solution is to use ```SPRING CLOUD CONFIG```. This is done in version2 of the app. 
