(ns word-frequency-service.core
  (:require
   [com.stuartsierra.component :as component]
   [word-frequency-service.components.server :as server])
  (:gen-class))

(defn base-system []
  (-> (component/system-map :server (server/new-server {}))))

(defonce system (base-system))

(defn main- [& args]
  (alter-var-root #'system component/start))

(comment
  ;; utilitary for development purposes (repl evaluation)
  (do
    (alter-var-root #'system component/stop)
    (alter-var-root #'system component/start)))
