# proxy-reverse

# Ojbjetivo

Proxy HTTPS reverso em JAVA com suporte SNI, que distribui os requests dependendo do host/FQDN requisitado para os correspondentes webservers de backend que suportam apenas HTTP.

#Fluxo proxy Reverse

https://www.lucidchart.com/documents/view/7373e6b0-a67b-4000-bf12-e22123726150/0

# Tecnologias Utilizadas:

Linguagem: Java 9 \
Gerenciador de Dependências: Maven

# FrameWorks' e Lib's:
spring-boot-starter 2.0.4.RELEASE \
spring-boot-starter-web 2.0.4.RELEASE \
spring-boot-starter-jetty
lombok 1.16.22 \
spring-boot-starter-test 2.0.3.RELEASE \
dockerfile-maven-plugin \

# Http Request methods.
curl -X GET \
  https://localhost:8443/test \
  -H 'cache-control: no-cache' \
  -H 'postman-token: b157f58f-729e-89ad-3172-a04345299fc4'
# Passo build's e deploy's das api's
  docker-compose pull myapi
  docker-compose scale myapi=1

