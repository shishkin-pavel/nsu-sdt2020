(ns data_structures.core_test
  (:require [clojure.test :refer :all]
            [data_structures.words :as one_onelab]
            [data_structures.words2 :as one_twolab]
            [common.utils :refer :all]))

(deftest testlab1_1

  (testing "Testing lab1_1"
     (is (my-contains? (one_onelab/all-words 2 (list "a", "b")) "ba"))
     (is (thrown? IllegalArgumentException (one_onelab/all-words 2 (list "a" "b" 1))))
     (is (my-contains? (one_twolab/all-words 2 (list "a", "b")) "ba"))
     (is (thrown? IllegalArgumentException (one_twolab/all-words 2 (list "a" "b" 1))))
     (is (contains-all? (one_onelab/all-words 2 (list "a", "b")) (one_twolab/all-words 2 (list "a", "b"))))))

