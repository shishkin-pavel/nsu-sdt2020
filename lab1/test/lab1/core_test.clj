(ns lab1.core-test
  (:require [clojure.test :refer :all]
            [lab1.core :refer :all]))

(deftest add-char-test
  (testing "add-char"
    (is (= (lab1.task1/add-char :a `()) `()))
    (is (= (lab1.task1/add-char :a `((:a))) `()))
    (is (= (lab1.task1/add-char :a `((:a) (:b))) `((:a :b))))
    (is (= (lab1.task1/add-char :a `((:a) (:b) (:c))) `((:a :b) (:a :c))))))

(deftest concat-string-list-test
  (testing "concat-string-list"
    (is (= (lab1.task1/concat-string-list `() `()) `()))
    (is (= (lab1.task1/concat-string-list `(:a) `((:a))) `()))
    (is (= (lab1.task1/concat-string-list `(:a :b :c) `((:a) (:b) (:c))) 
            `((:a :b) (:a :c) (:b :a) (:b :c) (:c :a) (:c :b))))
    (is (= (lab1.task1/concat-string-list `(:a :b :c) `((:a :b) (:a :c) (:b :a) (:b :c) (:c :a) (:c :b))) 
            `((:a :b :a) (:a :b :c) (:a :c :a) (:a :c :b) (:b :a :b) (:b :a :c) (:b :c :a) (:b :c :b) (:c :a :b) (:c :a :c) (:c :b :a) (:c :b :c))))))

(deftest task1-test
  (testing "task1"
    (is (= (lab1.task1/task1 `() 1) `()))
    (is (= (lab1.task1/task1 `(:a :b :c) 0) `()))
    (is (= (lab1.task1/task1 `(:a :b :c) 1) `((:a) (:b) (:c))))
    (is (= (lab1.task1/task1 `(:a :b :c) 2) `((:a :b) (:a :c) (:b :a) (:b :c) (:c :a) (:c :b))))
    (is (= (lab1.task1/task1 `(:a :b :c) 3) `((:a :b :a) (:a :b :c) (:a :c :a) (:a :c :b) (:b :a :b) (:b :a :c) (:b :c :a) (:b :c :b) (:c :a :b) (:c :a :c) (:c :b :a) (:c :b :c))))))

(deftest add-char-recur-test
  (testing "add-char-recur"
    (is (= (lab1.task2/add-char-recur :a `()) `()))
    (is (= (lab1.task2/add-char-recur :a `((:a))) `()))
    (is (= (lab1.task2/add-char-recur :a `((:a) (:b))) `((:a :b))))
    (is (= (lab1.task2/add-char-recur :a `((:a) (:b) (:c))) `((:a :b) (:a :c))))))

(deftest concat-string-list-recur-test
  (testing "concat-string-list-recur"
    (is (= (lab1.task2/concat-string-list-recur `() `()) `()))
    (is (= (lab1.task2/concat-string-list-recur `(:a) `((:a))) `()))
    (is (= (lab1.task2/concat-string-list-recur `(:a :b :c) `((:a) (:b) (:c))) 
            `((:a :b) (:a :c) (:b :a) (:b :c) (:c :a) (:c :b))))
    (is (= (lab1.task2/concat-string-list-recur `(:a :b :c) `((:a :b) (:a :c) (:b :a) (:b :c) (:c :a) (:c :b))) 
            `((:a :b :a) (:a :b :c) (:a :c :a) (:a :c :b) (:b :a :b) (:b :a :c) (:b :c :a) (:b :c :b) (:c :a :b) (:c :a :c) (:c :b :a) (:c :b :c))))))

(deftest task2-test
  (testing "task2"
    (is (= (lab1.task2/task2 `() 1) `()))
    (is (= (lab1.task2/task2 `(:a :b :c) 0) `()))
    (is (= (lab1.task2/task2 `(:a :b :c) 1) `((:a) (:b) (:c))))
    (is (= (lab1.task2/task2 `(:a :b :c) 2) `((:a :b) (:a :c) (:b :a) (:b :c) (:c :a) (:c :b))))
    (is (= (lab1.task2/task2 `(:a :b :c) 3) `((:a :b :a) (:a :b :c) (:a :c :a) (:a :c :b) (:b :a :b) (:b :a :c) (:b :c :a) (:b :c :b) (:c :a :b) (:c :a :c) (:c :b :a) (:c :b :c))))))

(deftest task3-test
  (testing "tesk3"
    (is (= (lab1.task3/my-map inc `(1 2 3)) (map inc `(1 2 3))))
    (is (= (lab1.task3/my-map inc `(3 2 1)) (map inc `(3 2 1))))
    (is (= (lab1.task3/my-map dec `(1 2 3)) (map dec `(1 2 3))))
    (is (= (lab1.task3/my-map (fn [x] (* x x)) `(1 2 3)) (map (fn [x] (* x x)) `(1 2 3))))
    (is (= (lab1.task3/my-filter even? (range 10)) (filter even? (range 10))))
    (is (= (lab1.task3/my-filter (fn [x] (< x 5)) (range 10)) (filter (fn [x] (< x 5)) (range 10))))
    (is (= (lab1.task3/my-filter (fn [x] (> x 5)) (range 10)) (filter (fn [x] (> x 5)) (range 10))))
    (is (= (lab1.task3/my-filter (fn [x] (= x :a)) `(:a :b :a)) (filter (fn [x] (= x :a)) `(:a :b :a))))))

(deftest filter-first-test
  (testing "filter-first"
    (is (= (lab1.task4/filter-first :a `()) `()))
    (is (= (lab1.task4/filter-first :a `((:a))) `()))
    (is (= (lab1.task4/filter-first :a `((:a) (:b))) `((:b))))
    (is (= (lab1.task4/filter-first :a `((:a) (:b) (:c))) `((:b) (:c))))))

(deftest add-char-map-test
  (testing "add-char-map"
    (is (= (lab1.task4/add-char-map :a `()) `()))
    (is (= (lab1.task4/add-char-map :a `((:a))) `()))
    (is (= (lab1.task4/add-char-map :a `((:a) (:b))) `((:a :b))))
    (is (= (lab1.task4/add-char-map :a `((:a) (:b) (:c))) `((:a :b) (:a :c))))))

(deftest concat-string-list-map-test
  (testing "concat-string-list-map"
    (is (= (lab1.task4/concat-string-list-map `() `()) `()))
    (is (= (lab1.task4/concat-string-list-map `(:a) `((:a))) `()))
    (is (= (lab1.task4/concat-string-list-map `(:a :b :c) `((:a) (:b) (:c))) 
            `((:a :b) (:a :c) (:b :a) (:b :c) (:c :a) (:c :b))))
    (is (= (lab1.task4/concat-string-list-map `(:a :b :c) `((:a :b) (:a :c) (:b :a) (:b :c) (:c :a) (:c :b))) 
            `((:a :b :a) (:a :b :c) (:a :c :a) (:a :c :b) (:b :a :b) (:b :a :c) (:b :c :a) (:b :c :b) (:c :a :b) (:c :a :c) (:c :b :a) (:c :b :c))))))

(deftest task4-test
  (testing "task4"
    (is (= (lab1.task4/task4 `() 1) `()))
    (is (= (lab1.task4/task4 `(:a :b :c) 0) `()))
    (is (= (lab1.task4/task4 `(:a :b :c) 1) `((:a) (:b) (:c))))
    (is (= (lab1.task4/task4 `(:a :b :c) 2) `((:a :b) (:a :c) (:b :a) (:b :c) (:c :a) (:c :b))))
    (is (= (lab1.task4/task4 `(:a :b :c) 3) `((:a :b :a) (:a :b :c) (:a :c :a) (:a :c :b) (:b :a :b) (:b :a :c) (:b :c :a) (:b :c :b) (:c :a :b) (:c :a :c) (:c :b :a) (:c :b :c))))))
