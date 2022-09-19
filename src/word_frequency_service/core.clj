(ns word-frequency-service.core
  (:require
   [aero.core :refer [read-config]]
   [clojure.java.io :as io]
   [clojure.string :as s]
   [com.stuartsierra.component :as component]
   [word-frequency-service.components.server :as server])
  (:gen-class))

(defn base-system
  ([] (base-system (or (some-> (System/getenv "PROFILE")
                               s/lower-case
                               keyword) :dev)))
  ([profile]
   (let [config (read-config (io/resource "config.edn") {:profile profile})]
     (component/system-map :server (server/new-server (:server config))))))

(defonce system (base-system))

(defn -main [& args]
  (alter-var-root #'system component/start))

(comment
  ;; utilitary for development purposes (repl evaluation)
  (do
    (alter-var-root #'system component/stop)
    (alter-var-root #'system (constantly (base-system :dev)))
    (alter-var-root #'system component/start)))
