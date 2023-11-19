all: clean package run


package:
	mvn -f pom.xml package

run:
    cp conf.conf ./target/classes
	java -jar target/Mesh-1.0-SNAPSHOT.jar


clean:
	mvn -f ./pom.xml clean
