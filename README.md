<pre><code>
#Test using curl
while true; do curl --location --request POST 'http://localhost:8080/ticks' --header 'Content-Type: application/json' --data '{"instrument":"is your otp code","price":33.3,"timestamp":1596097956}';sleep 1; done
</code></pre>
# index-calculator
To build project :

mvn clean package

Then go to target folder and run the following command:

java -jar target/index-calculator-0.0.1-SNAPSHOT.jar

You are ready to go.

Please make sure that the timestamp you put in the post request must me less then 60 second of you system's current time.

If I had more time I can handle exception from single place for all APIs

Write all unite test case like service and repository instead of only trivial controller 

Use standard log like Log4J for logging instead of System.out

Configuration date from configuration file like application.properties

Use data validation

last but not least, I really enjoin the challenge.

Thanks a lot. 
