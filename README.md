# Answers for Assigments  

## Assignment 2 (MySQL and H2 Database)  

Selected statments:    
- The properties file that uses H2 should be in src/test/resources   
- Spring Boot will automatically configure and use a JPA implementation if it’s present in the classpath  

Note:   
The last statement “The tests will need different repository classes since they use different database” can be true too, since H2 and MySql are not totally compatible so maybe some queries in repository will not be compatible with H2.  
That’s why I would not suggest H2 for testing. 


## Assignment 5 (Performance)  

First I have an assumption that the size of the GeoIp.db file is not the same in the test environment and in production environment. I assume the file in test environment is much smaller that’s why there are no performance bottlenecks. 

My best guess what can cause the performance degradation is the getGeoIpDbLocation() method, more specifically the getResourceAsStream().
The getResourceAsStream() method returns an InputStream instance (depends on the file which one) which do not offer any further logic just reading the file by bytes which in the case of a big file a lot of disk memory communication.

I would solve the problem by wrapping the whole getResourceAsStream() call into a BufferedInputStream.
The BufferedInputStream extends from InputStream so no further logic change is needed. With a buffer the reading from disk to memory can be more efficient,
because we work with bulk data and while the processing happens from the buffer the stream loads bulk data continuously.
This makes the code more performant since less communication needed between the memory and the disk. Also we can play around with the buffer size to fine tune the reading. 
