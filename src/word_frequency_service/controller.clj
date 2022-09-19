(ns word-frequency-service.controller
  (:require
   [word-frequency-service.logic :as logic]))

(defn word-frequency-count
  "Returns the word frequencies from a text, sorted by a descendent count.

  Receives an optional top parameter to return an arbitrary amount of words.
  This number should stay between 2 and 15. It considers the lower bound if
  the param is not provided"
  [text top]
  (let [top (-> top
                (or 2)
                (max 2)
                (min 15))]
    (logic/word-frequency text top)))
