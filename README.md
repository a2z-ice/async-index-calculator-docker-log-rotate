<pre><code>
docker run --log-driver json-file --log-opt max-size=10m --log-opt max-file=3 ...

# find /var/lib/docker/ -name "*.log" -exec ls -sh {} \; | sort -n -r | head -20
# du -aSh /var/lib/docker/ | sort -n -r | head -n 10
# watch ls -l 
/etc/docker/daemon.json
---short----
{
"experimental":true,
"log-driver": "json-file",
"log-opts": {
    "max-size": "10k",
    "max-file": "3"
  }
}
---long----
{
"experimental":true,
"log-driver": "json-file",
"log-opts": {
    "max-size": "10m",
    "max-file": "3",
    "labels": "production_status",
    "env": "os,customer"
  }
}

# systemctl restart docker 
</code></pre>

# index-calculator
To build project :

mvn clean package

Then go to target folder and run the following command:

java -jar target/index-calculator-0.0.1-SNAPSHOT.jar

You are ready to go.

If I had more time I can handle exception from single place for all APIs

Write all unite test case like service and repository instead of only trivial controller 

Use standard log like Log4J for logging instead of System.out

Configuration date from configuration file like application.properties

Use data validation

last but not least, I really enjoin the challenge.

Thanks a lot. 
