
package com.springcloud;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class WebSecurity {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http.cors();
        http.csrf().disable();
        return http.build();
    }
}



frame-src 'self'; frame-ancestors 'self'; object-src 'none';


7f5d0173d5dc4ad266471164068061030d02ea9f

mvn clean verify sonar:sonar -Dsonar.projectKey=sample -Dsonar.host.url=http://localhost:9000 -Dsonar.login=7f5d0173d5dc4ad266471164068061030d02ea9f



-------------
d69868bf129d0c3fafe7549c61f8c897c378fcbe

mvn sonar:sonar -Dsonar.projectKey=sample -Dsonar.host.url=http://localhost:9000 -Dsonar.login=d69868bf129d0c3fafe7549c61f8c897c378fcbe


<Connector SSLEnabled="true" acceptCount="100" clientAuth="false"
    disableUploadTimeout="true" enableLookups="false" maxThreads="25"
    port="8443" keystoreFile="/home/dmadmin/.keystore" keystorePass="secret"
    protocol="org.apache.coyote.http11.Http11NioProtocol" scheme="https"
    secure="true" sslProtocol="TLS" />


docker run -d -p 8180:8080 -e KEYCLOAK_USER=admin -e KEYCLOAK_PASSWORD=admin jboss/keycloak

keytool -certreq -alias 11.0.0.139 -keystore keycloak.jks > keycloak.careq

sudo curl -L --fail run.sh -o /usr/local/bin/docker-compose


docker run -d -p 9100:9000 \
  -e "MINIO_ACCESS_KEY=miniodocker" \
  -e "MINIO_SECRET_KEY=miniodocker" \
  minio/minio server /data


sudo openssl req -x509 -nodes -days 365 -newkey rsa:2048 -keyout /home/dmadmin/minio/private.key -out /home/dmadmin/minio/public.crt



---------------------------------------------------------------------------------------
*docker-compose.yml of minio (private.crt and public.key files in /home/dmadmin/minio)
-> ref. https://stackoverflow.com/questions/50878454/using-https-for-minio-server
---------------------------------------------------------------------------------------
version: '3'

services:
  minio:
    image: store/minio/minio:RELEASE.2020-12-29T23-29-29Z
    command: server --address ":443" /data
    ports:
      - "440:443"
    environment:
      MINIO_ACCESS_KEY: "miniodocker"
      MINIO_SECRET_KEY: "miniodocker"
    volumes:
      - /home/dmadmin/minio/.:/root/.minio/certs




---------------------------------------------------------------------------------------
*docker-compose.yml of keycloak (client.crt and client.key files in same location of docker-compose.yml file)
-> ref. https://www.youtube.com/watch?v=yq1hzNs1JQU
---------------------------------------------------------------------------------------

services:
        keycloak:
                image: jboss/keycloak
                command: -c standalone.xml
                environment:
                        DB_VENDOR: h2
                        KEYCLOAK_USER: admin
                        KEYCLOAK_PASSWORD: admin
                ports:
                        - 8380:8080
                        - 8440:8443
                volumes:
                        - ./client.crt:/etc/x509/https/tls.crt
                        - ./client.key:/etc/x509/https/tls.key


-------------------------------------------------------------------------------------------
https://stackoverflow.com/questions/3893839/how-do-i-redirect-https-to-http-on-nginx
-------------------------------------------------------------------------------------------




---------------------------------------------------------------------------------------
*https in tomcat
---------------------------------------------------------------------------------------

-> ref. https://www.youtube.com/watch?v=RaEG_DOpNPc






openssl req -new -x509 -days 30 -key vault-tls.key -out vault-tls.cert \
  -subj "/C=/ST=/L=/O=/CN=11.0.0.139" -addext "subjectAltName = IP:11.0.0.139"


openssl req -new -x509 -days 30 -key vault-tls.key -out vault-tls.cert \
>   -subj "C=11.0.0.139" "ST=11.0.0.139" "L=11.0.0.139" "O=11.0.0.139" "CN=11.0.0.139" -addext "subjectAltName = IP:11.0.0.139"

mongod --sslMode requireSSL --sslPEMKeyFile /cert/mongodb.pem




---------------------------------------------------------------------------------------
*minio with keycloak
---------------------------------------------------------------------------------------


mc admin config set myminio identity_openid config_url="http://localhost:8180/auth/realms/RTI/.well-known/openid-configuration" client_id="RTI"


 
