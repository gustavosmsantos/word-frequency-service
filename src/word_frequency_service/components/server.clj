(ns word-frequency-service.components.server
  (:require
   [com.stuartsierra.component :as component]
   [io.pedestal.http :as http]
   [word-frequency-service.routes :as routes]))

(defrecord HttpServer [server config]
  component/Lifecycle
  (start [this]
    (let [service-map {::http/host   "localhost"
                       ::http/port   8080
                       ::http/type   :jetty
                       ::http/routes routes/routes
                       ::http/join?  false}
          server      (-> service-map
                          http/default-interceptors
                          http/create-server
                          http/start)]
      (assoc this :server server)))
  (stop [this]
    (when server
      (http/stop server))
    (assoc this :server nil)))

(defn new-server [config]
  (map->HttpServer {:config config}))
