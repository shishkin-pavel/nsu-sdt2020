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

(deftest task3-test
  (testing "tesk3"
    (is (= (lab1.task3/my-map inc `(1 2 3)) (map inc `(1 2 3))))
    (is (= (lab1.task3/my-map inc `(3 2 1)) (map inc `(3 2 1))))
    (is (= (lab1.task3/my-map dec `(1 2 3)) (map dec `(1 2 3))))
    (is (= (lab1.task3/my-map (fn [x] (* x x)) `(1 2 3)) (map (fn [x] (* x x)) `(1 2 3))))
    (is (= (lab1.task3/my-filter even? (range 10)) (filter even? (range 10))))
    (is (= (lab1.task3/my-filter (fn [x] (< x 5)) (range 10)) (filter (fn [x] (< x 5)) (range 10))))
    (is (= (lab1.task3/my-filter (fn [x] (> x 5)) (range 10)) (filter (fn [x] (> x 5)) (range 10))))
    (is (= (lab1.task3/my-filter (fn [x] (= x "a")) `("a" "b" "a")) (filter (fn [x] (= x "a")) `("a" "b" "a"))))))

(deftest task4-test
  (testing "task4"
    (is (= (lab1.task4/task4 `() 1) `()))
    (is (= (lab1.task4/task4 `("a" "b" "c") 0) `()))
    (is (= (lab1.task4/task4 `("a" "b" "c") 1) `("a" "b" "c")))
    (is (= (lab1.task4/task4 `("a" "b" "c") 2) `("ab" "ac" "ba" "bc" "ca" "cb")))
    (is (= (lab1.task4/task4 `("a" "b" "c") 3) `("aba" "abc" "aca" "acb" "bab" "bac" "bca" "bcb" "cab" "cac" "cba" "cbc")))))
