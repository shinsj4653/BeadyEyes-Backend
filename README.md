# GDSC Solution Challenge - BeadyEyes

## Main Improvements
### 1. Adding Zero-Downtime Deployment
- Before


https://github.com/GDSC-Solution-Challenge-Team-4/BeadyEyes-Backend/assets/49470452/9f44e266-2a8e-4313-8bb7-28191be0e5f5  

In the process of Deploy, there was about `10 ~ 15 secs of downtime`.


- After



https://github.com/GDSC-Solution-Challenge-Team-4/BeadyEyes-Backend/assets/49470452/4a265318-cbb9-4639-90ec-ad8814389283  

By applying `deploy.sh` file and Nginx Web Server's `proxy_pass`, we managed to `decrease the downtime to 0 sec`.

- deploy.sh  
```bash
IS_GREEN=$(sudo docker ps | grep beadyeyes-spring-green) # Check the current running container
DEFAULT_CONF=" /etc/nginx/nginx.conf"

if [ -z $IS_GREEN  ];then # blue라면

  echo "### BLUE => GREEN ###"

  echo "1. tag green image"
  sudo docker tag beadyeyes-spring beadyeyes-spring-green # tag the image to green

  echo "2. green container up"
  sudo docker-compose up -d beadyeyes-spring-green # execute green container

  while [ 1 = 1 ]; do
  echo "3. green health check..."
  sleep 3

  REQUEST=$(curl ${ green-server }) # request to green server
    if [ -n "$REQUEST" ]; then # if the service is available, then cancel health check
            echo "health check success"
            break ;
            fi
  done;

  echo "4. reload nginx"
  sudo cp /etc/nginx/nginx.green.conf /etc/nginx/nginx.conf
  sudo nginx -s reload

  echo "5. blue container down"
  sudo docker-compose stop beadyeyes-spring-blue
else
  echo "### GREEN => BLUE ###"

  echo "1. tag blue image"
  sudo docker tag beadyeyes-spring beadyeyes-spring-blue

  echo "2. blue container up"
  sudo docker-compose up -d beadyeyes-spring-blue
else
  echo "### GREEN => BLUE ###"

  echo "1. tag blue image"
  sudo docker tag beadyeyes-spring beadyeyes-spring-blue

  echo "2. blue container up"
  sudo docker-compose up -d beadyeyes-spring-blue

  while [ 1 = 1 ]; do
    echo "3. blue health check..."
    sleep 3
    REQUEST=$(curl { blue-server }) # request to blue server

    if [ -n "$REQUEST" ]; then 
      echo "health check success"
      break ;
    fi
  done;

  echo "4. reload nginx"
  sudo cp /etc/nginx/nginx.blue.conf /etc/nginx/nginx.conf
  sudo nginx -s reload

  echo "5. green container down"
  sudo docker-compose stop beadyeyes-spring-green
fi
```
1. Check which conatiner is running currently.  
2. If the blue container is running, then pull the green image from Dockerhub and tag the image to green.
3. Health check through port 8080, since the green-server is using this port.(blue-server is using port 8081)
4. Modify the nginx.conf to the required conf, in this case, nginx.green.conf.
5. Stop the blue container.


## Backend repo

### How to run my code on local environment by using SpringBoot Framework
1. Use SpringBoot Framework and open the cloned repository  


![image](https://github.com/GDSC-Solution-Challenge-Team-4/BeadyEyes-Backend/assets/49470452/ccc97d74-6631-4892-90ea-d7167731e258)

2. Then, click the green start button on the right above area, or select the 'Run Pointer Application' option in the Run Menu.  


![image](https://github.com/GDSC-Solution-Challenge-Team-4/BeadyEyes-Backend/assets/49470452/c8a06217-5c7a-4d37-825a-e6b102817d17)

![image](https://github.com/GDSC-Solution-Challenge-Team-4/BeadyEyes-Backend/assets/49470452/23fd330c-2d12-4b78-9795-518fc58a48f7)


### How to run my code on vm instance console by using docker, docker-compose, and DockerHub

1. Make sure you have installed docker and DockerHub repository on your local area. Also you must have docker and docker-compose in your server area.  

2. Click the bootJar button to build the backend project.  
![image](https://github.com/GDSC-Solution-Challenge-Team-4/BeadyEyes-Backend/assets/49470452/c141d028-3163-4758-a22e-810b5f973ad4)


3. Create docker image of the project.
From the project that was build, make a docker image and push it to your own DockerHub repository.  
```bash
docker login -u ${{ secrets.DOCKERHUB_USERNAME }} -p ${{ secrets.DOCKERHUB_PASSWORD }}
docker buildx build --push --platform linux/amd64 -t ${{ secrets.DOCKERHUB_REPOSITORY }}:${GITHUB_SHA::7} .
```

4. Pull the docker image and use docker-compose to deploy.  
Inside the vm instance ssh console, use these process to deploy the image via docker-compose.  
Make sure to tag the correct image name, so that the docker-compose.yml file can detect it.  
Also, deploy.sh file with Nginx feature allows the project to be deployed with zero downtime.  
```bash
sudo docker login -u ${{ secrets.DOCKERHUB_USERNAME }} -p ${{ secrets.DOCKERHUB_PASSWORD }}
sudo docker pull ${{ secrets.DOCKERHUB_REPOSITORY }}:${GITHUB_SHA::7}
sudo docker tag ${{ secrets.DOCKERHUB_REPOSITORY }}:${GITHUB_SHA::7} beadyeyes-spring
sudo chmod 777 ./deploy.sh
./deploy.sh
sudo docker image prune -f
```
