(ns word-frequency-service.integration-test
  (:require
   [clj-http.client :as http]
   [clojure.test :refer [deftest is testing use-fixtures]]
   [com.stuartsierra.component :as component]
   [word-frequency-service.core :as core]
   [cheshire.core :as json]
   [clojure.string :as str]))

(defn start-server [f]
  (let [system (-> (core/base-system :test)
                   component/start)]
    (f)
    (component/stop system)))

(use-fixtures :once start-server)

(defn request-word-frequencies [body & [{top :top}]]
  (-> (http/post (str "http://localhost:8080/word-frequency-count" (when top (str "?top=" top))) {:body body})
      :body
      (json/parse-string true)))

(deftest word-frequency-endpoint

  (testing "word frequencies being returned in json format"
    (is (= {:one 1 :two 1} (request-word-frequencies "one two"))))

  (testing "word frequencies should return two elements when no top param provided"
    (is (= {:one 1 :two 1} (request-word-frequencies "one two three"))))

  (testing "top param should be between 2 and 15"
    (is (= {:one 1 :two 1} (request-word-frequencies "one two three" {:top 1})))
    (is (= 15 (-> (request-word-frequencies (->> (range)
                                                 (take 20)
                                                 (str/join " ")) {:top 16})
                  keys
                  count)))))
