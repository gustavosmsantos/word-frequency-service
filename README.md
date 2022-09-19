# word-frequency-server

A simple web application that exposes a single entrypoint with a service that receives a text and outputs a json with the word frequencies, sorted by count in descending order. The architecture is based on the (https://github.com/geraldodev/basic-microservice-example)[Nubank's guidelines], relying on the hexagonal pattern.

It is intended just to serve as an example about a way of how to construct a clojure web service.

## Main technologies
- (https://github.com/pedestal/pedestal)[Pedestal] as a web server;
- (https://github.com/stuartsierra/component)[Component] to manage the application lifecycle;
- (https://github.com/juxt/aero)[Aero] to handle the configuration for different profiles;

## Building the application

The application uses the (https://github.com/clojure/tools.build)[tools.build] as a build tool. You can build the application with the following command, that creates an uberjar:

``` 
clj -T:build uber
```
