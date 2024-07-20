rem batch test
echo A4 batch test
echo username:%USERNAME%
echo CD

start "mysql" cmd /k mysql_start
timeout /t 5 /nobreak >nul

cd rank-spring
call mvn clean package -q
call java -cp target/rank-spring.jar ec.ioc.GradeApp
echo:
call java -cp target/rank-spring.jar ec.ioc.RankApp
echo:
call mvn clean -q

call cd ../ms-spring-boot
call mvn clean package -q
start "ms-spring-boot" cmd /k java -jar target/ms-spring-boot.jar
timeout /t 5 /nobreak >nul

echo:
call curl -v http://localhost:8080/grade/76

echo:
call curl -v http://localhost:8080/rank/76
echo:
call curl http://localhost:8080/model/predict/1114,2200,10,20,165



start "karaf" cmd /k "C:/enterprise/apache-karaf-4.3.3/bin/karaf.bat"
timeout /t 5 /nobreak >nul

cd ../stats-osgi-service
call mvn clean install -q
call xcopy target\stats-osgi-service.jar  C:\enterprise\apache-karaf-4.3.3\deploy

cd ../stats-osgi-consumer
call mvn clean package
call xcopy target\stats-osgi-consumer.jar  C:\enterprise\apache-karaf-4.3.3\deploy  /Y

cd ../stats-osgi-web
call mvn clean package
call xcopy target\stats-osgi-web.jar  C:\enterprise\apache-karaf-4.3.3\deploy  /Y

timeout /t 5 /nobreak >nul
echo:
call curl http://localhost:8181/stats-osgi-web?query=count
echo:
call curl http://localhost:8181/stats-osgi-web?query=mean

call del /F C:\enterprise\apache-karaf-4.3.3\deploy\stats-osgi-web.jar
call del /F C:\enterprise\apache-karaf-4.3.3\deploy\stats-osgi-consumer.jar
call del /F C:\enterprise\apache-karaf-4.3.3\deploy\stats-osgi-service.jar

cd ../stats-osgi-service
call mvn clean -q

cd ../stats-osgi-consumer
call mvn clean -q

cd ../stats-osgi-web
call mvn clean -q

cd ..

call taskkill /FI "WindowTitle eq ms-spring-boot*"  /T /F  >null
taskkill /FI "WindowTitle eq karaf"  /T /F >null
taskkill /FI "WindowTitle eq mysql*"  /T /F >null


echo END

