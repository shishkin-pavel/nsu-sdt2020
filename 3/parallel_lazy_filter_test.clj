(ns parallel-lazy-filter-test
    (:use parallel-lazy-filter)
    (:require [clojure.test :as test]))
    
(test/deftest base_workflow 
    (test/testing "Parallel-filter mimics normal filter"
        (test/is (= (take 20 (filter (fn [x] (= 0 (mod x 3))) (range))) (take 20 (parallel-filter (fn [x] (= 0 (mod x 3))) (range)))))))
    
(test/run-tests 'parallel-lazy-filter-test)