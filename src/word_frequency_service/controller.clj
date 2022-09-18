(ns word-frequency-service.controller
  (:require
   [word-frequency-service.logic :as logic]))

(defn word-frequency-count
  ""
  [text top]
  (let [top (-> top
                (or 2)
                (max 2)
                (min 15))]
    (logic/word-frequency text top)))
