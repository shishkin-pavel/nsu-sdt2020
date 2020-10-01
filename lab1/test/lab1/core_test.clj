(ns lab1.core-test
  (:require [clojure.test :refer :all]
            [lab1.core :refer :all]))

(deftest task1-test
  (testing "task1"
    (is (= (lab1.task1/task1 `() 1) `()))
    (is (= (lab1.task1/task1 `("a" "b" "c") 0) `()))
    (is (= (lab1.task1/task1 `("a" "b" "c") 1) `("a" "b" "c")))
    (is (= (lab1.task1/task1 `("a" "b" "c") 2) `("ab" "ac" "ba" "bc" "ca" "cb")))
    (is (= (lab1.task1/task1 `("a" "b" "c") 3) `("aba" "abc" "aca" "acb" "bab" "bac" "bca" "bcb" "cab" "cac" "cba" "cbc")))))

(deftest task2-test
  (testing "task2"
    (is (= (lab1.task2/task2 `() 1) `()))
    (is (= (lab1.task2/task2 `("a" "b" "c") 0) `()))
    (is (= (lab1.task2/task2 `("a" "b" "c") 1) `("a" "b" "c")))
    (is (= (lab1.task2/task2 `("a" "b" "c") 2) `("ab" "ac" "ba" "bc" "ca" "cb")))
    (is (= (lab1.task2/task2 `("a" "b" "c") 3) `("aba" "abc" "aca" "acb" "bab" "bac" "bca" "bcb" "cab" "cac" "cba" "cbc")))))
