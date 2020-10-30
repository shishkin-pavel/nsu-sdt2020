(ns parallel-filter-test
    (:use parallel-filter)
    (:require [clojure.test :as test]))
    
(test/deftest base_workflow 
    (test/testing "Parallel-filter mimics normal filter"
        (test/is (= (filter (fn [x] (= 0 (mod x 3))) (range 100)) (parallel-filter (fn [x] (= 0 (mod x 3))) (range 100))))))
    
(test/with-test (def res (filter (fn [x] (= 0 (mod x 3))) (range 100)))
    (test/testing "Parallel-filter should not change result based on thread count"
        (test/is (= res (parallel-filter (fn [x] (= 0 (mod x 3)))  (range 100))))
        (test/is (= res (parallel-filter (fn [x] (= 0 (mod x 3)))  (range 100) 1)))
        (test/is (= res (parallel-filter (fn [x] (= 0 (mod x 3)))  (range 100) 3)))
        (test/is (= res (parallel-filter (fn [x] (= 0 (mod x 3)))  (range 100) 100)))))
    
(test/run-tests 'parallel-filter-test)