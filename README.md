# UDP Multicast Server
Introduction to UDP Multicast servers by building a distributed and synchronous network communication system.


## Targeted versions / Requirements
Java 11


## Project architecture
- /classes ->  .class files
- /doc -> javadoc + resources
- /lib -> .jar files
- /src -> .java files

(Note: make sure to correct filepath if you're on Windows)
## Installation
Compile:
```
javac -d bin src/*/*.java
```
and run:
```
java -classpath classes stream.UDPMain <UDP_Multicast_Ip_address(224.1.1.1 for example)> <port_number> <username>
```


## Feature / Services
The UDP Multicast server allows you to join a group and exchange messages with other participants


## Authors
- BEL Corentin
- GALARZA Javier
- KERMANI Benjamin
