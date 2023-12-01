all: clean package


package:
	mvn -f pom.xml package

clean:
	mvn -f ./pom.xml clean
