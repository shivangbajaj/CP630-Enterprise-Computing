rem A1 batch test
echo A1 batch test
echo username:%USERNAME%
echo CD


cd stats-mvn
call mvn clean -q
call mvn package -q
echo on
call java -jar target/stats-mvn.jar
call mvn clean -q
cd ..

start "wildfly" cmd /k standalone
timeout /t 10 /nobreak > NUL

cd stats-app
call mvn clean install -q
call mvn wildfly:undeploy -q
call mvn wildfly:deploy  -q
echo on
call curl http://127.0.0.1:8080/stats-web/add-data?value=10
call curl http://127.0.0.1:8080/stats-web/insert-data?value=10
call curl http://127.0.0.1:8080/stats-web/get?value=count
cd ..

cd stats-client
call mvn clean package -q
echo on
call java -jar target/stats-client.jar
cd ..

cd stats-app
call mvn wildfly:undeploy -q
call mvn clean -q
cd ..

taskkill /FI "WindowTitle eq wildfly*"  /T /F > NUL

echo END