(ns word-frequency-service.logic-test
  (:require [word-frequency-service.logic :as sut]
            [clojure.test :refer [deftest testing is]]))

(deftest word-frequency-test

  (testing "empty text should throw an assertion error"
    (is (thrown? java.lang.AssertionError (sut/word-frequency "" 3))))

  (testing "return should be limited by the top argument"
    (let [text "one two three four five"]
      (is (= 2 (-> (sut/word-frequency text 2) keys count)))
      (is (= 5 (-> (sut/word-frequency text 6) keys count)))))

  (testing "the order should be given by the frequency"
    (is (= {"three" 3
            "two"   2
            "one"   1} (sut/word-frequency "one two two three three three" 3)))))
