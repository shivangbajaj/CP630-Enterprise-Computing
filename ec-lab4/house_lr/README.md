Build house linear regression image
===================================

1. do the following command to build house_lr image

docker build -f house_lr.df -t house_lr .


2. run following command to run the container the house_lr image as container, named the container as house

docker run --name house -i -t house_lr 


The following command can be used to map a volume of local host to container's directory. 

docker run --name house -i -t -v c:/tmp/enterprise/:/enterprise/ house_lr


3. From Ubuntu prompt, run the following commands:

ls

cd enterprise/model

ls

java -jar MyECRegressionPredict.jar -m house_regression.bin -i ../data/house_predict.arff -o result.txt

cat result.txt

java -jar MyECRegressionModel.jar -i ../data/house.arff -o house_regression1.bin 

java -jar MyECRegressionModel.jar -m house_regression1.bin -i ../data/house.arff -t ../data/house_test.arff -o testresult.txt

java -jar MyECRegressionPredict.jar -m house_regression1.bin -i ../data/house_predict.arff -o result.txt


4. open a new cmd console, and run the following command to call and run program of container house

docker exec -it house sh -c "java -jar enterprise/model/MyECRegressionPredict.jar -m enterprise/model/house_regression.bin -i enterprise/data/house_predict.arff -o enterprise/model/result.txt"


5. Under the house container's prompt, type following command to exit

exit


6. remove the house container

docker container rm house









