# API Automation Framework Using RestAssured, JUnit, Extent Report, Log4j

# Framework Details
* This solution contains automation scripts for REST API hosted on "https://api.tmsandbox.co.nz". 
* RestAssured Libraries used for REST API Test Automation
* Maven is used as build tool
* JUnit as unit test framework
* Extent report is used to create HTML based report
* Detailed Logging captured using Log4j API.

# How to Setup & Execute from Eclipse?

* Install Java . 
  Set JAVA_HOME variable for the path of jdk (eg. JAVA_HOME="C:\Program Files\Java\jdk1.8.0_92")
  Append Path variable as %PATH%;%JAVA_HOME%/bin
* Download Maven (e.g Apache Maven 3.3.9) and set system variables.
Set M2_HOME system environment variable as (eg. M2_HOME="C:\Program Files\Apache\apache-maven-3.3.9"
* Open Eclipse & Import the project as existing Maven Project
* Right click on pom.xml.
  Run as Maven Clean
  Run as Maven Install
* Verify results and logs in "reports/htmlReport" & "reports/log" folder

# How to run from Command Prompt

* Clone the current git repository into local path
* Navigate to the project directory 
* Type and execute the command  "mvn compile" to download all the related dependencies and jars
* Run the test by executing "mvn test" command 
* Verify results and logs in "reports/htmlReport" & "reports/log" folder
