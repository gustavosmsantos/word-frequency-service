FROM clojure:openjdk-8-tools-deps

WORKDIR /app

COPY . .

RUN clojure -T:build uber

CMD java -jar target/word-frequency-service-*.jar
