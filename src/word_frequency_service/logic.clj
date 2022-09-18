(ns word-frequency-service.logic
  (:require [clojure.string :as s]))

(defn word-frequency [text top]
  (assert (not-empty text) "text should be filled")
  (let [splitted-words (s/split text #"\s+")]
    (->> splitted-words
         frequencies
         (sort-by val (comparator >))
         (take top)
         (into {}))))

(comment
  (word-frequency "this is a sentence and this is another one" 5))
