(ns word-frequency-service.routes
  (:require
   [clojure.java.io :as io]
   [io.pedestal.http :as http]
   [io.pedestal.http.route :as route]
   [io.pedestal.interceptor.helpers :as interceptor]
   [word-frequency-service.controller :as controller])
  (:import [java.io ByteArrayOutputStream]))

(defn word-frequency-count-handler [request]
  (let [{text          :body
         {:keys [top]} :params} request]
    {:status 200 :body (controller/word-frequency-count text (some-> top Integer/valueOf))}))

(def plain-text-params
  (interceptor/on-request (fn [request]
                            (let [baos (ByteArrayOutputStream.)]
                              (io/copy (:body request) baos)
                              (update request :body (constantly (->> baos
                                                                     .toByteArray
                                                                     (map char)
                                                                     (apply str))))))))

(def routes
  (route/expand-routes #{["/word-frequency-count" :post [plain-text-params
                                                         http/json-body
                                                         word-frequency-count-handler] :route-name ::word-frequency-count]}))



