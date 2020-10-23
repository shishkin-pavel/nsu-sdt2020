(ns lab2.core-test
  (:use lab2.common)
  (:require [clojure.test :refer :all]
            [lab2.core :refer :all]))

(deftest trapeze-area-test
  (testing "trapeze-area"
    (is (= (lab2.common/trapeze-area (fn [x] x) 0.0 0.0)) 0.0)
    (is (= (lab2.common/trapeze-area (fn [x] x) 1.0 1.0)) 0.0)
    (is (= (lab2.common/trapeze-area (fn [x] x) 0.0 1.0)) 0.5)
    (is (= (lab2.common/trapeze-area (fn [x] x) 0.0 2.0)) 1.0)
    (is (= (lab2.common/trapeze-area (fn [x] x) 1.0 3.0)) 1.0)
    (is (= (lab2.common/trapeze-area (fn [x] x) 0.0 100.0)) 50.0)))

(deftest sum-memoize-test
  (testing "sum-memoize"
    (is (= (lab2.task1/sum-memoize (fn [x] x) 0.0 0.0) 0.0))
    (is (= (lab2.task1/sum-memoize (fn [x] x) 0.0 1.0) 0.0))
    (is (= (lab2.task1/sum-memoize (fn [x] x) 1.0 1.0) 0.5))
    (is (= (lab2.task1/sum-memoize (fn [x] x) 1.0 2.0) 2.0))
    (is (= (lab2.task1/sum-memoize (fn [x] x) 2.0 4.0) 32.0))))

(deftest task1-test
  (testing "task1"
    (is (= (lab2.task1/task1 (fn [x] x) 0.0 0.0) 0.0))
    (is (= (lab2.task1/task1 (fn [x] x) 1.0 5.0) 12.5))
    (is (= (lab2.task1/task1 (fn [x] x) 2.0 5.0) 12.5))
    (is (= (lab2.task1/task1 (fn [x] x) 2.5 5.0) 12.5))
    (is (= (lab2.task1/task1 (fn [x] x) 1.0 10.0) 50.0))
    (is (= (lab2.task1/task1 (fn [x] x) 2.5 10.0) 50.0))
    (is (= (lab2.task1/task1 (fn [x] x) 5.0 10.0) 50.0))
    (is (= (lab2.task1/task1 (fn [x] x) 7.0 10.0) 50.0))))

(deftest task2-test
  (testing "task2"
    (is (= (lab2.task2/task2 (fn [x] x) 0.0 0.0) 0.0))
    (is (= (lab2.task2/task2 (fn [x] x) 1.0 5.0) 12.5))
    (is (= (lab2.task2/task2 (fn [x] x) 2.0 5.0) 12.5))
    (is (= (lab2.task2/task2 (fn [x] x) 2.5 5.0) 12.5))
    (is (= (lab2.task2/task2 (fn [x] x) 1.0 10.0) 50.0))
    (is (= (lab2.task2/task2 (fn [x] x) 2.5 10.0) 50.0))
    (is (= (lab2.task2/task2 (fn [x] x) 5.0 10.0) 50.0))
    (is (= (lab2.task2/task2 (fn [x] x) 7.0 10.0) 50.0))))
